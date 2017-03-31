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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eu.operando.core.cpcu.main.DataHolder;
import eu.operando.core.cpcu.main.QuestionnaireHandler;

/**
 * The Class ServiceDataProcessor.
 */
public class ServiceDataProcessor extends DefaultDataProcessor {

	/** The data list. */
	private Map<DataHolder, String> dataList;

	/** The roles list. */
	private Map<DataHolder, String> rolesList;
	
	/**
	 * Instantiates a new data processor. Takes data about the Questionnaire
	 *  it's attached to so it can be used to identify files.
	 *
	 * @param type the type
	 * @param internalID the internal ID
	 * @param dataLocation the array containing all of the information about the location of the data
	 * @param serviceID the service ID
	 */
	public ServiceDataProcessor(String type, Integer internalID, String[] dataLocation, int serviceID) {
		super(type, internalID, dataLocation, serviceID);
	}

	/**
	 * Retrive roles. If the List is null the the file is polled
	 *
	 * @return the list of Roles
	 */
	public List<DataHolder> retriveRoles() {
		if(getRolesList()==null)
			return retriveRolesFromFile();
		else 
			return new ArrayList<>(getRolesList().keySet());
	}

	/**
	 * Retrieve information.
	 *
	 * @param info the info file to poll
	 * @return the JSON array
	 */
	//Contains all the I/O relating to the role and data aquisition
	private JSONArray retrieveInformation(String info){
		JSONParser parser = new JSONParser();

		try {
			return (JSONArray) parser.parse(new FileReader(QuestionnaireHandler.QUESTIONPOOL_FILE_LOC + info));
		} catch (FileNotFoundException e) {
			error("File" + info + " could not be found.", e);
		} catch (IOException e) {
			error("The File "  + info + " could not be read", e);
		} catch (ParseException e) {
			error("The File " + info + " is not formatted correctly", e);
		}
		return null;
	}

	/**
	 * Retreive roles from file.
	 *
	 * @return the list of Roles from File
	 */
	private List<DataHolder> retriveRolesFromFile(){

		rolesList = new HashMap<>();
		JSONArray array = retrieveInformation(getRoleLocation());

		for(Object o: array){
			JSONObject obj = (JSONObject) o;
			rolesList.put(new DataHolder((String) obj.get("role"), ((Long) obj.get("rank")).intValue()),
					(String) obj.get("metadata"));
		}

		return new ArrayList<>(rolesList.keySet());
	}

	/**
	 * Retrieve data. If the List is null the the file is polled
	 *
	 * @return the list of data
	 */
	public List<DataHolder> retrieveData(){
		if(getDataList()==null)
			return retrieveDataFromFile();
		return  new ArrayList<>(getDataList().keySet());
	}

	/**
	 * Retrieve data from file. 
	 *
	 * @return the list of data from File
	 */
	private List<DataHolder> retrieveDataFromFile(){

		dataList = new HashMap<>();
		JSONArray array = retrieveInformation(getDataLocation());

		for(Object o: array){
			JSONObject obj = (JSONObject) o;
			dataList.put(new DataHolder((String) obj.get("category"), ((Long) obj.get("rank")).intValue()),
					(String) obj.get("metadata"));
		}

		return new ArrayList<>(dataList.keySet());
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
				
				ServiceStatement tst = (ServiceStatement) st;
				
				statement.put("role", tst.getRole());
				statement.put("roleRank", tst.getRoleRank());
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
	 * Creates the metadata for the Statement.
	 *
	 * @param role the role
	 * @param data the data Category
	 * @return the metadata as a string
	 */
	protected String createMetadata(DataHolder role, DataHolder data){
		return this.dataList.get(data) + ". "+ this.rolesList.get(role);
	}

	/**
	 * Gets the data list.
	 *
	 * @return the data list
	 */
	public Map<DataHolder, String> getDataList() {
		return dataList;
	}

	/**
	 * Gets the roles list.
	 *
	 * @return the roles list
	 */
	public Map<DataHolder, String> getRolesList() {
		return rolesList;
	}

}