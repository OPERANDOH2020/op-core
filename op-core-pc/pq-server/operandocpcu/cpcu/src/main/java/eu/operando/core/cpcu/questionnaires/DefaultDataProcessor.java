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

import java.io.File;
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
 * The Class DefaultDataProcessor. This class will be heavily changed to be integrated into the actual system. This is so it can pull
 * the data out of the live OPERANDO system and return them in the same manner. naturally, these woudnt use
 * the file system but the databases.
 */
public class DefaultDataProcessor {

	/** The type. */
	protected String type;
	
	/** The internal ID. */
	protected int internalID;
	
	/** The data location. */
	private String[] dataLocation;
	
	/** The service ID. */
	private int serviceID;
	
	/**
	 * Instantiates a new data processor. Takes data about the Questionnaire
	 *  it's attached to so it can be used to identify files.
	 *
	 * @param type the type
	 * @param id the id
	 * @param dataInformation the array containing all of the information about the location of the data
	 * @param serviceID the service ID
	 */
	public DefaultDataProcessor(String type, Integer id, String[] dataInformation, int serviceID){
		this.type = type;
		this.internalID = id;
		this.serviceID = serviceID;
		this.dataLocation = dataInformation;
	}

	/**
	 * Retrieves the question pool.
	 *
	 * @return Map<DataHolder, List<Statement>> map of the Category mapped to the Statements within that category.
	 */
	public Map<DataHolder, List<Statement>> retrieveQuestionPool(){
		return retrieveQuestionPool(new File(QuestionnaireHandler.QUESTIONPOOL_FILE_LOC+type+"Questionnaire.json"));
	}

	/**
	 * Retrieve question pool from the file.
	 *
	 * @param f the file to pull the Statements from
	 * @return Map<DataHolder, List<Statement>> map of the Category mapped to the Statements within that category.
	 */
	private Map<DataHolder, List<Statement>> retrieveQuestionPool(File f){

		Map<DataHolder, List<Statement>> questionnaire = new HashMap<>();
		JSONParser parser = new JSONParser();

		try {
			JSONArray array = (JSONArray) parser.parse(new FileReader(f));
			
			for(Object o : array){
				JSONObject question = (JSONObject) o;

				Statement statement = new Statement((String) question.get("questionString"), 
						(Double) question.get("weight"), (String) question.get("category"), 
						((Long) question.get("privacyRanking")).intValue());
				
				statement.setMetadata((String) question.get("metadata"));
				DataHolder st = new DataHolder(statement.getCategory(), statement.getPrivacyRanking());
				if(!questionnaire.containsKey(st))
					questionnaire.put(st, new ArrayList<Statement>());

				List<Statement> q1 = questionnaire.get(st);
				q1.add(statement);
			}
		} catch (ParseException e){
			error("The file " + f  +" is not correctly formatted.", e);
		} catch (FileNotFoundException e) {
			error("File " + f  +" could not be found.", e);
		} catch (IOException e) {
			error("The file " + f  +" could not be read.", e);
		}
		return questionnaire;
	}

	/**
	 * Convert data holder to string.
	 *
	 * @param <T> the generic type of the result
	 * @param statements the statements in DataHolder form
	 * @return the map of the new String mapped to the result.
	 */
	private <T> Map<String, T> convertDataHolderToString(Map<DataHolder, T> statements){
		Map<String, T> converted = new HashMap<>();

		for(DataHolder d: statements.keySet()){
			converted.put(d.getData(), statements.get(d));
		}
		return converted;
	}

	/**
	 * Write statements to XML data holder. This converts the statements to String before processing.
	 *
	 * @param statements the statements mapped to the string representations of their categories
	 * @param Questmeta the metadata for the Questionniare
	 * @param type the type of the Questionniare
	 * @return the string representation of the XML Node
	 */
	public String writeStatementsToJSONDataHolder(Map<DataHolder, List<Statement>> statements, String Questmeta, String type){
		return writeStatementsToJSON(convertDataHolderToString(statements), Questmeta, type);
	}

	/**
	 * Write statements to JSON.
	 *
	 * @param statements the statements
	 * @param questmeta the questmeta
	 * @param type the type
	 * @return the string
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
				statement.put("metadata", st.getMetadata());
				
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
	 * Gets the id of the Questionnaire.
	 *
	 * @return the id
	 */
	public int getID(){
		return internalID;
	}

	/**
	 * Gets the data aquisition method from the dataLocation array. This shows how the data should be pulled from its location.
	 *
	 * @return the data aquisition method
	 */
	public String getDataAquisitionMethod(){
		return dataLocation[0];
	}
	
	/**
	 * Gets the preference category from the dataLocation array. This is used to pull 
	 * preference data for this category from dependent questionnaires.
	 *
	 * @return the preference category
	 */
	public String getPreferenceCategory(){
		return dataLocation[1];
	}
	
	/**
	 * Gets the role location from the dataLocation array. This can be in the form of a File name or other SQL based command
	 *
	 * @return the role location. Does not contain any package information, just the raw file name in string form.
	 */
	public String getRoleLocation(){
		return dataLocation[2];
	}
	
	/**
	 * Gets the data location from the dataLocation array. This can be in the form of a File name or other SQL based command
	 *
	 * @return the data location. Does not contain any package information, just the raw file name in string form.
	 */
	public String getDataLocation(){
		return dataLocation[3];
	}
	
	/**
	 * Logs an info statement to the QuestionnaireHandler logger.
	 *
	 * @param s the s
	 */
	public void log(String s){
		QuestionnaireHandler.getLogger().info("processing questionnaire "+type+":> " + s);
	}
	
	/**
	 * Logs an error to the QuestionnaireHandler logger.
	 *
	 * @param s the s
	 * @param e the e
	 */
	public void error(String s, Exception e){
		QuestionnaireHandler.getLogger().error("processing questionnaire "+type+":> " + s + ">>\n " + e);
	}
	
	/**
	 * Gets the service ID.
	 *
	 * @return the service ID
	 */
	public int getServiceID(){
		return serviceID;
	}
}
