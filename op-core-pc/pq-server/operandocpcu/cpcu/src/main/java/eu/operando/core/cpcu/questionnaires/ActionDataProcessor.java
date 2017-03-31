/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2016
//
// Copyright in this software belongs to University of Southampton
// IT Innovation Centre of Gamma House, Enterprise Road,
// Chilworth Science Park, Southampton, SO16 7NS, UK.
//
// This software may not be used, sold, licensed, transferred, copied
// or reproduced in whole or in part in any manner or form or in or
// on any media by any person other than in accordance with the terms
// of the Licence Agreement supplied with the software, or otherwise
// without the prior written consent of the copyright owners.
//
// This software is distributed WITHOUT ANY WARRANTY, without even the
// implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
// PURPOSE, except where stated in the Licence Agreement supplied with
// the software.
//
//      Created By :            Robbie Anderson
//      Created Date :          2016-09-02
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////


/*
 * @author ra16 <ra16@it-innovation.soton.ac.uk
 */
package eu.operando.core.cpcu.questionnaires;

import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import eu.operando.core.cpcu.main.DataHolder;
import eu.operando.core.cpcu.main.QuestionnaireHandler;

/**
 * The Class ActionDataProcessor.
 */
public class ActionDataProcessor extends ServiceDataProcessor {
	
	/** The actions list. */
	private Map<DataHolder, String> actionsList; 
	
	/**
	 * Instantiates a new data processor. Takes data about the Questionnaire
	 *  it's attached to so it can be used to identify files.
	 *
	 * @param type, the String name of the Questionnaire this DataProcessor is attached to
	 * @param id, the Integer internal ID of the Questionnaire
	 * @param dataLocation contains the information about where the data is stored.
	 */
	public ActionDataProcessor(String type, Integer id, String[] dataLocation, int serviceID) {
		super(type, id, dataLocation, serviceID);
	}

	/**
	 * Gets the actions from file.
	 *
	 * @return the actions from file in DataHolder
	 */
	private List<DataHolder> getActionsFromFile(){

		JSONParser parser = new JSONParser();
		actionsList = new HashMap<>();

		try {
			JSONArray array = (JSONArray) parser.parse(new FileReader(QuestionnaireHandler.QUESTIONPOOL_FILE_LOC + "actions.json"));

			for(Object o: array){
				JSONObject obj = (JSONObject) o;
				getActionsList().put(new DataHolder((String) obj.get("Action"), ((Long) obj.get("Rank")).intValue()),
						(String) obj.get("Metadata"));
			}

		} catch (IOException e) {
			error("The file actions.json cannot be read. ",e);
		} catch (ParseException e){
			error("The file actions.json is not correctly formatted. ",e);
		}
		return new ArrayList<>(getActionsList().keySet());
	}

	/**
	 * Retrieve actions from the List, if not initialised then the file is parsed
	 *
	 * @return the list of DataHolder objects (Containing the actions)
	 */
	public List<DataHolder> retrieveActions(){
		if(getActionsList()==null)
			return getActionsFromFile();
		return  new ArrayList<>(getActionsList().keySet());
	}

	/* (non-Javadoc)
	 * @see com.initialDevelopment.CPCU.Questionnaires.ServiceDataProcessor#retrieveResults(java.lang.String, boolean)
	 */
	public Map<Statement, Double> retrieveResults(String xmlString, boolean bool){

		Map<Statement, Double> res = new HashMap<>();

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(new InputSource(new ByteArrayInputStream(xmlString.getBytes("utf-8"))));
			doc.getDocumentElement().normalize();

			NodeList category = doc.getElementsByTagName("Category");
			for(int i = 0; i < category.getLength(); i++){
				Element cat = (Element)category.item(i);
				DataHolder categoryKey = new DataHolder(cat.getElementsByTagName("title").item(0).getTextContent(), 0);

				NodeList statements = cat.getElementsByTagName("Statement");
				for(int j = 0; j < statements.getLength(); j++){
					Element st = (Element) statements.item(j);

					res.put(new ActionStatement(st.getElementsByTagName("statementString").item(0).getTextContent(),
							categoryKey.getData(),
							Integer.parseInt(st.getElementsByTagName("privacyRanking").item(0).getTextContent()),
							new DataHolder(st.getElementsByTagName("role").item(0).getTextContent(),
									Integer.parseInt(st.getElementsByTagName("roleRank").item(0).getTextContent())),
							new DataHolder(st.getElementsByTagName("action").item(0).getTextContent(), 
									Integer.parseInt(st.getElementsByTagName("actionRank").item(0).getTextContent())))
							, Double.parseDouble(st.getElementsByTagName("rating").item(0).getTextContent()));
				}
			}
		} catch (ParserConfigurationException e) {
			error("The XML Input is not correctly formatted.", e);
		} catch (SAXException e) {
			error("There was an error with the XML parser", e);
		} catch (IOException e) {
			error("The input could not be read.", e);
		}
		return res;
	}
		
	/* (non-Javadoc)
	 * @see eu.operando.core.cpcu.main.DataProcessor#writeStatementsToJSON(java.util.Map, java.lang.String, java.lang.String)
	 */
	public String writeStatementsToJSON(Map<String, List<Statement>> statements, String questmeta, String type){

		JSONObject obj = new JSONObject();
		JSONArray catArray = new JSONArray();
		for(Entry<String, List<Statement>> entry: statements.entrySet()){
			JSONObject cat = new JSONObject();
			JSONArray state = new JSONArray();
			
			for(Statement st: entry.getValue()){
				JSONObject statement = new JSONObject();
				statement.put("statementString", st.getStatementString());
				statement.put("rating", -1);
				statement.put("weight", st.getWeight());
				statement.put("privacyRanking", st.getPrivacyRanking());
				statement.put("Metadata", st.getMetadata());
				
				ActionStatement tst = (ActionStatement) st;
				
				statement.put("role", tst.getRole());
				statement.put("roleRank", tst.getRoleRank());
				statement.put("action", tst.getRole());
				statement.put("actionRank", tst.getRoleRank());
				state.add(statement);
			}
			cat.put("title", entry.getKey());
			cat.put("statements", state);
			catArray.add(cat);
		}
		obj.put("category", catArray);
		obj.put("type", Integer.toString(getID()));
		obj.put("title", type + " Questionnaire");
		obj.put("serviceID", Integer.toString(getServiceID()));
		obj.put("metadata", questmeta);
		
		return obj.toJSONString();
	}

	/**
	 * Creates the metadata for the Statement
	 *
	 * @param role the role
	 * @param data the data Category
	 * @param action the action
	 * @return the metadata as a string
	 */
	public String createMetadata(DataHolder role, DataHolder data, DataHolder action){
		return getRolesList().get(role) + " \n " + getDataList().get(data) + " \n " + actionsList.get(action);
	}

	public Map<DataHolder, String> getActionsList() {
		return actionsList;
	}

}
