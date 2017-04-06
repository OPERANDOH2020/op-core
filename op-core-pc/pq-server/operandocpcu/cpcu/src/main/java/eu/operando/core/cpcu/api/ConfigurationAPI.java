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
package eu.operando.core.cpcu.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eu.operando.core.cpcu.exceptions.ConfigNotInitialised;
import eu.operando.core.cpcu.exceptions.NoSuchQuestionnaireException;
import eu.operando.core.cpcu.exceptions.NoSuchServiceException;
import eu.operando.core.cpcu.main.QuestionnaireHandler;
import eu.operando.core.cpcu.servlet.configurations.Configuration;
import eu.operando.core.cpcu.servlet.configurations.ConfigurationFactory;
import eu.operando.core.cpcu.servlet.configurations.QuestionConfiguration;
import eu.operando.core.cpcu.servlet.configurations.QuestionnaireConfiguration;
import eu.operando.core.cpcu.servlet.configurations.ServiceConfiguration;


/**
 * The Class ConfigurationAPI.
 */
public class ConfigurationAPI {

	private ReadWriteLock rwlock = new ReentrantReadWriteLock();


	/** The handler. */
	private QuestionnaireHandler handler;

	/**
	 * Instantiates a new configuration API.
	 *
	 * @param handler the handler
	 */
	public ConfigurationAPI(QuestionnaireHandler handler){
		this.handler = handler;
	}

	/**
	 * Creates the questionnaire from the QuestionnaireConfiguration passed, and writes it to the Question Config file.
	 * JSON Simple parser doesn't parameterise it's implementation, so have to deal with type warnings.
	 *
	 * @param qc the QuestionnaireConfiguration
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 * @throws ConfigNotInitialised 
	 */
	public void createQuestionnaire(QuestionnaireConfiguration qc) throws FileNotFoundException, IOException, ConfigNotInitialised, ParseException{
		JSONArray arr = getQuestionnaireConfiguration();
		arr.add(createJSONFromQuestionnaire(qc));
		writeQuestionnaireConfiguration(arr);

	}

	/**
	 * Deletes a questionnaire with a given ID.
	 *
	 * @param id the id to be deleted
	 * @throws Exception the exception
	 */
	public void deleteQuestionnaire(int id) throws Exception{

		JSONArray arr = getQuestionnaireConfiguration();
		arr.remove(getJSONQuestionnaire(id));
		writeQuestionnaireConfiguration(arr);
	}

	public void updateQuestionnaire(QuestionnaireConfiguration qc, int id) throws Exception{
		JSONArray arr = getQuestionnaireConfiguration();
		arr.remove(getJSONQuestionnaire(id));
		arr.add(createJSONFromQuestionnaire(qc));
		writeQuestionnaireConfiguration(arr);
	}
	
	public String isValidQuestionID(int quid, int vid) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("isvalidid", isValidQID(quid, vid));
		return obj.toJSONString();
	}
	
	public String isValidQuestionnaireID(int vid) throws Exception {
		JSONObject obj = new JSONObject();
		obj.put("isvalidid", isValidQuID(vid));
		return obj.toJSONString();
	}
	
	private boolean isValidQID(int qid, int vid) throws Exception{
		String type = (String) getJSONQuestionnaire(qid).get("type");
		JSONArray arr = this.getQuestionPool(type);
		
		for(Object obj: arr){
			JSONObject o = (JSONObject) obj;
			if(((Long) o.get("id")).intValue() == vid)
				return false;
		}
		return true;
	}
	
	private boolean isValidQuID(int vid) throws Exception{
		JSONArray arr = getQuestionnaireConfiguration();
		
		for(Object obj: arr){
			JSONObject o = (JSONObject) obj;
			if(((Long) o.get("id")).intValue() == vid)
				return false;
		}
		return true;
	}

	/*
	 * HELPER METHOD
	 */
	private JSONObject createJSONFromQuestionnaire(QuestionnaireConfiguration qc){
		JSONObject obj = new JSONObject();
		obj.put("id", qc.getId());
		obj.put("type", qc.getType());
		obj.put("dependancies", qc.getDependancies());
		obj.put("processor", qc.getProcessor());
		obj.put("classLoader", qc.getClassLoader());
		obj.put("metadata", qc.getMetadata());
		obj.put("generatedQuestions", qc.getGeneratedQuestions());
		return obj;
	}

	/**
	 * HELPER METHOD:
	 * Is this ID contained within the Array. This is complex because of the casting needed to compare the values, as if the string
	 * representation "1" is compared to the int field id 1, they will not be equal, so contains() cannot be used. 
	 *
	 * @param id the id to search for
	 * @param arr the JSONArray to search through
	 * @return the int
	 * @throws Exception the if the Questionnaire is not contained within the array.
	 */
	private int doesContain(int id, JSONArray arr) throws Exception{
		for(int i =0; i < arr.size(); i++){
			JSONObject obj = (JSONObject) arr.get(i);
			if(((Long) obj.get("id")).intValue()==id){
				return i;
			}
		}
		throw new NoSuchQuestionnaireException();
	}

	/**
	 * Adds the question to a given Questionnaire. the ID identifies the Questionnaire, and the QuestionConfiguration outlines the fields of the
	 * Question.
	 *
	 * @param id the id of the Questionnaire to be added to
	 * @param qc the QuestionConfiguration to be added
	 * @throws Exception the exception
	 */
	public void addQuestion(int id, QuestionConfiguration qc) throws Exception{
		String type = (String) getJSONQuestionnaire(id).get("type");
		JSONArray arr = getQuestionPool(type);

		JSONObject obj = new JSONObject();
		obj.put("id", qc.getId());
		obj.put("questionString", qc.getQuestionString());
		obj.put("weight", qc.getWeight());
		obj.put("category", qc.getCategory());
		obj.put("privacyRanking", qc.getPrivacyRanking());
		obj.put("metadata", qc.getMetadata());
		arr.add(obj);

		writeQuestionPool(type, arr);
	}

	/**
	 * Gets the questionnaires. ONLY returns the Configuration representation of the Questionnaires, not the Questions contained in them.
	 *
	 * @return the questionnaires
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 * @throws ConfigNotInitialised 
	 */
	public List<QuestionnaireConfiguration> getQuestionnaire() throws FileNotFoundException, IOException, ParseException, ConfigNotInitialised{	
		return convertFromJSONArray(getQuestionnaireConfiguration(), "qn");
	}

	/**
	 * HELPER METHOD:
	 * Convert from JSON array. This method converts the JSON string returned into a valid Configuration format, which can be used by
	 * Spring's marshalling. Although this is extra processing, it means that all Outputs from the system MUST be in a VALID 
	 * configuration format, making processing at the other end far easier.
	 * 
	 * The method takes an identifier (either qn, qs or se) to indetify which Configuration needs to be used. The JSON objects
	 * are then converted into that Type and returned as a List.
	 *
	 * @param <T> the generic type of Configuration
	 * @param arr the JSONArray of JSONObjects
	 * @param ident the ident to identify which Configuration type should be used
	 * @return the list of Configuration types
	 */
	//Assumes a valid obj has been passed.
	private <T extends Configuration> List<T> convertFromJSONArray(JSONArray arr, String ident){
		List<T> ret = new ArrayList<>();
		for(Object o: arr){
			ret.add(new ConfigurationFactory().convertAndCreate((JSONObject) o, ident));
		}
		return ret;
	}

	/**
	 * Deletes a question.
	 *
	 * @param id the id of the Questionnaire to delete a Question from
	 * @param questionid the ID of the Question in that Questionnaire
	 * @throws Exception the exception
	 */
	public void deleteQuestion(int id, int questionid) throws Exception{
		String type = (String) getJSONQuestionnaire(id).get("type");
		JSONArray arr = getQuestionPool(type);

		arr.remove(getJSONQuestion(arr, questionid));

		writeQuestionPool(type, arr);
	}

	/**
	 * Gets the services in Configuration format.
	 *
	 * @return the services
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 * @throws ConfigNotInitialised 
	 */
	public List<ServiceConfiguration> getServices() throws FileNotFoundException, IOException, ParseException, ConfigNotInitialised{
		return convertFromJSONArray(getServiceConfiguration(), "se");
	}

	/**
	 * Updates a question. Must be a FULL configuration.
	 *
	 * @param id the id of the Questionnaire 
	 * @param questionid the ID of the question within the Questionnaire
	 * @param qc the QuestionConfiguration to update.
	 * @return true, if successful
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 * @throws Exception the exception
	 */
	public boolean updateQuestion(int id, int questionid, QuestionConfiguration qc) throws FileNotFoundException, IOException, ParseException, Exception{
		String type = (String) getJSONQuestionnaire(id).get("type");
		JSONArray arr = getQuestionPool(type);
		JSONObject obj = getJSONQuestion(arr, questionid);
		
		obj.put("id", qc.getId());
		obj.put("questionString", qc.getQuestionString());
		obj.put("weight", qc.getWeight());
		obj.put("category", qc.getCategory());
		obj.put("privacyRanking", qc.getPrivacyRanking());
		obj.put("metadata", qc.getMetadata());

		writeQuestionPool(type, arr);

		return true;
	}

	/**
	 * Updates a service.
	 *
	 * @param id the id of the servce
	 * @param sc the ServiceConfiguration
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean updateService(int id, ServiceConfiguration sc) throws Exception{
		JSONArray arr = getServiceConfiguration();
		JSONObject obj = getJSONQuestion(arr, id);

		obj.put("id", sc.getId());
		obj.put("aquisitionMethod", sc.getAquisitionMethod());
		obj.put("roleLocation", sc.getRoleLocation());
		obj.put("dataLocation", sc.getDataLocation());
		obj.put("preferences", sc.getPreference());
		obj.put("metadata", sc.getMetadata());

		writeServiceConfiguration(arr);
		return true;
	}

	/**
	 * Adds a service.
	 *
	 * @param sc the ServiceConfiguratino
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean addService(ServiceConfiguration sc) throws Exception{
		JSONArray arr = getServiceConfiguration();
		JSONObject obj = new JSONObject();

		obj.put("id", sc.getId());
		obj.put("aquisitionMethod", sc.getAquisitionMethod());
		obj.put("roleLocation", sc.getRoleLocation());
		obj.put("dataLocation", sc.getDataLocation());
		obj.put("preferences", sc.getPreference());
		obj.put("metadata", sc.getMetadata());

                arr.add(obj);
                
		writeServiceConfiguration(arr);
		return true;
	}

	/**
	 * Deletes a service.
	 *
	 * @param id the id of the service to be deleted
	 * @return true, if successful
	 * @throws Exception the exception
	 */
	public boolean deleteService(int id) throws Exception{
		JSONArray arr = getServiceConfiguration();

		arr.remove(getJSONQuestion(arr, id));
		writeServiceConfiguration(arr);
		return true;
	}

	/**
	 * Search services. Will return the List of Configurations that match the pattern.
	 *
	 * @param search the search
	 * @param field the field to search through
	 * @return the list of Configurations
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchServiceException the no such service exception
	 * @throws NoSuchFieldException the no such field exception
	 * @throws ParseException the parse exception
	 * @throws ConfigNotInitialised 
	 */
	public List<ServiceConfiguration> searchServices(String search, String field) throws IOException, NoSuchServiceException, NoSuchFieldException, ParseException, ConfigNotInitialised{
		JSONArray arr = getServiceConfiguration();
		JSONObject object = (JSONObject) arr.get(0);
		if(!object.containsKey(field))
			throw new NoSuchFieldException();
		JSONArray result;
		if(object.get(field) == long.class){
			if((result = new Searcher<Long>().find(arr, search, field))==null)
				throw new NoSuchServiceException();
			return convertFromJSONArray(result, "se");
		} else {
			if((result = new Searcher<String>().find(arr, search, field))==null)
				throw new NoSuchServiceException();
			return convertFromJSONArray(result, "se");
		}
	}

	/**
	 * Search questionnaires. Will return the List of Configurations that match the pattern.
	 *
	 * @param search the search
	 * @param field the field to search through
	 * @return the list of Configurations
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws NoSuchQuestionnaireException the no such questionnaire exception
	 * @throws NoSuchFieldException the no such field exception
	 * @throws ParseException the parse exception
	 * @throws ConfigNotInitialised 
	 */
	public List<QuestionnaireConfiguration> searchQuestionnaires(String search, String field) throws IOException, NoSuchQuestionnaireException, NoSuchFieldException, ParseException, ConfigNotInitialised{
		JSONArray arr = getQuestionnaireConfiguration();
		JSONObject object = (JSONObject) arr.get(0);
		if(!object.containsKey(field))
			throw new NoSuchFieldException();
		JSONArray result;
		if(object.get(field) == long.class){
			if((result = new Searcher<Long>().find(arr, search, field))==null)
				throw new NoSuchQuestionnaireException();
			return convertFromJSONArray(result, "qn");
		} else {
			if((result = new Searcher<String>().find(arr, search, field))==null)
				throw new NoSuchQuestionnaireException();
			return convertFromJSONArray(result, "qn");
		}
	}

	/**
	 * The Class Searcher.
	 *
	 * @param <T> the generic type
	 */
	/*
	 * Needed to cast the objects from JSONObject to correct values. The objects can ONLY be cast to either a String or Long, with 
	 * Long being the general representation for an Integer. As JSON Values can only be String or a number as a base value, this will
	 * cast the primitive value to the correct java representation. So if the field result is not an instance of long.class, then it
	 * must be castable to a string.
	 */
	class Searcher<T> {

		/**
		 * Finds (if present) any Objects that satisfy the search parameters
		 *
		 * @param arr the JSON array to search through
		 * @param search the search
		 * @param field the field
		 * @return the JSON array
		 */
		public JSONArray find(JSONArray arr, String search, String field){
			JSONArray resultArray = new JSONArray();
			for(Object ob: arr){
				JSONObject obj = (JSONObject) ob;

				if(search.equals(((T) obj.get(field)).toString())){
					resultArray.add(obj);
				}
			}
			return resultArray;
		}

	}

	/**
	 * Gets the QuestionPool for a given Questionnaire as a List of QuestionConfigurations.
	 *
	 * @param questionnaireID the questionnaire ID
	 * @return the Question Pool as a Configuration of Questions
	 * @throws Exception the exception
	 */
	public List<QuestionConfiguration> getQP(int questionnaireID) throws Exception{
		String type = (String) getJSONQuestionnaire(questionnaireID).get("type");
		return convertFromJSONArray(getQuestionPool(type), "qs");
	}


	/**
	 * HELPER METHOD:
	 * Gets the question pool as a JSON Array
	 *
	 * @param type the type of the Questionnaire
	 * @return the question pool as a JSON Array
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 * @throws ConfigNotInitialised 
	 */
	private JSONArray getQuestionPool(String type) throws FileNotFoundException, IOException, ParseException, ConfigNotInitialised{
		return read(QuestionnaireHandler.QUESTIONPOOL_FILE_LOC+type+"Questionnaire.json");
	}

	/**
	 * HELPER METHOD:
	 * Write question pool to the file
	 *
	 * @param type the type
	 * @param arr the arr
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ConfigNotInitialised 
	 */
	private void writeQuestionPool(String type, JSONArray arr) throws IOException, ConfigNotInitialised{
		write(QuestionnaireHandler.QUESTIONPOOL_FILE_LOC+type+"Questionnaire.json", arr);
	}

	/**
	 * HELPER METHOD:
	 * Gets the JSONObject question from a QuestionPool
	 *
	 * @param questionpool the QuestionPool
	 * @param id the id
	 * @return the JSON Object representing the Question
	 * @throws Exception the exception
	 */
	private JSONObject getJSONQuestion(JSONArray questionpool, int id) throws Exception{
		return (JSONObject) questionpool.get(doesContain(id, questionpool));
	}

	/**
	 * HELPER METHOD:
	 * Gets the JSON questionnaire.
	 *
	 * @param id the id of the Questionnaire
	 * @return the JSONObject questionnaire
	 * @throws Exception the exception
	 */
	private JSONObject getJSONQuestionnaire(int id) throws Exception{
		JSONArray arr = getQuestionnaireConfiguration();
		return (JSONObject) arr.get(doesContain(id, arr));
	}

	/**
	 * HELPER METHOD:
	 * Gets the questionnaire configuration from the file.
	 *
	 * @return the JSONArray questionnaire configuration
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 * @throws ConfigNotInitialised 
	 */
	private JSONArray getQuestionnaireConfiguration() throws FileNotFoundException, IOException, ParseException, ConfigNotInitialised{
		return read(QuestionnaireHandler.CONFIG_FILE_LOC +"Questionnaires.json");
	}

	/**
	 * HELPER METHOD:
	 * Gets the service configuration from the File
	 *
	 * @return the JSONArray service configuration
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 * @throws ConfigNotInitialised 
	 */
	private JSONArray getServiceConfiguration() throws FileNotFoundException, IOException, ParseException, ConfigNotInitialised{
		return read(QuestionnaireHandler.CONFIG_FILE_LOC +"Service.json");
	}

	/**
	 * HELPER METHOD:
	 * Write service configuration back to the file
	 *
	 * @param arr the arr
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ConfigNotInitialised 
	 */
	private void writeServiceConfiguration(JSONArray arr) throws IOException, ConfigNotInitialised{
		write(QuestionnaireHandler.CONFIG_FILE_LOC +"Service.json", arr);
	}

	/**
	 * HELPER METHOD:
	 * Write questionnaire configuration back to the file
	 *
	 * @param arr the arr
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ConfigNotInitialised 
	 */
	private void writeQuestionnaireConfiguration(JSONArray arr) throws IOException, ConfigNotInitialised{
		write(QuestionnaireHandler.CONFIG_FILE_LOC +"Questionnaires.json", arr);
	}

	/**
	 * HELPER METHOD:
	 * Write a given JSONArray to a given file
	 *
	 * @param file the file
	 * @param arr the JSONArray to write to the file
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ConfigNotInitialised 
	 */
	private void write(String file, JSONArray arr) throws IOException, ConfigNotInitialised{
		rwlock.writeLock().lock();
		File writeFile = new File(file);
		try {
			FileWriter writer = new FileWriter(writeFile);
			writer.write(arr.toJSONString());
			writer.close();
		} finally {
			rwlock.writeLock().unlock();
		}
	}

	/**
	 * HELPER METHOD:
	 * Read a file.
	 *
	 * @param file the file to read from
	 * @return the JSON array
	 * @throws FileNotFoundException the file not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ParseException the parse exception
	 * @throws ConfigNotInitialised 
	 */
	private JSONArray read(String file) throws IOException, ParseException, ConfigNotInitialised{
		rwlock.readLock().lock();
		JSONArray arr = null; 
		File readFile = new File(file);
		try {
			try{
				File parent = readFile.getParentFile();
				if(!parent.exists())
					parent.mkdirs();
				if(!readFile.exists()){
					readFile.createNewFile();
					return new JSONArray();
				}
				arr = (JSONArray) new JSONParser().parse(new FileReader(readFile));
			}	catch (ParseException e){
				//Assumes file is blank, so return a blank array so it can be written to.
				return new JSONArray();
			}
		} finally {
			rwlock.readLock().unlock();
		}
		return arr;
	}

	/**
	 * Gets the questionnaire handler.
	 *
	 * @return the questionnaire handler
	 */
	public QuestionnaireHandler getQuestionnaireHandler(){
		return handler;
	}
}
