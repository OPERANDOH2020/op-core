/*
   	* Copyright (c) 2017 {TECNALIA}.
    * All rights reserved. This program and the accompanying materials
    * are made available under the terms of the The MIT License (MIT).
    * which accompanies this distribution, and is available at
    * http://opensource.org/licenses/MIT
    *
    * Contributors:
    *    Gorka Benguria Elguezabal {TECNALIA}
    *    Gorka Mikel Echevarría {TECNALIA}
    * Initially developed in the context of OPERANDO EU project www.operando.eu
 */
package eu.operando.core.ae.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.deidentifier.arx.AttributeType.Hierarchy;
import org.deidentifier.arx.AttributeType.Hierarchy.DefaultHierarchy;
import org.deidentifier.arx.DataHandle;
import org.junit.runner.JUnitCore;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Pair;
import io.swagger.client.model.AEDataType;
import io.swagger.client.model.DataUnit;
import io.swagger.client.model.DataUnitValuePerAccessLevel;
import io.swagger.client.model.SearchRequest;

public class Test {
	private static Connection connect = null;		
	private static ResultSet resultSet = null;
	static Logger mainLogger = null;
	static Properties props = null;	
    static {
        mainLogger = Logger.getLogger("eu.operando.core.ae.test");
    }

	//@org.junit.Test
	public void postInvocationTest() {		
		ApiClient apiClient = new ApiClient();
		 
	    byte[] postBinaryBody = null; 
	     
	    // create path and map variables 
	    String path = "/operando/core/ae/dataUnit"; 
	 
	    // query params 
	    List<Pair> queryParams = new ArrayList<Pair>(); 
	    Map<String, String> headerParams = new HashMap<String, String>(); 
	    Map<String, Object> formParams = new HashMap<String, Object>(); 
	 
	    final String[] accepts = { 
	      "application/json", "application/xml" 
	    }; 
	    final String accept = apiClient.selectHeaderAccept(accepts); 
	 
	    final String[] contentTypes = { 
	       
	    }; 
	    final String contentType = apiClient.selectHeaderContentType(contentTypes); 
	 
	    String[] authNames = new String[] {  }; 
	 	    
	    GenericType returnType = new GenericType<String>() {};
				
		DataUnit dataUnit = new DataUnit();
		List<DataUnitValuePerAccessLevel> valuesPerAccessLevel = new ArrayList<>();
		DataUnitValuePerAccessLevel dataUnitValuePerAccessLevel = new DataUnitValuePerAccessLevel();
		dataUnitValuePerAccessLevel.setDataUnitId("1");
		dataUnitValuePerAccessLevel.setAccessLevelId("0");
		dataUnitValuePerAccessLevel.setValue("Complete");
		valuesPerAccessLevel.add(0, dataUnitValuePerAccessLevel);
		dataUnit.setId("1");
		dataUnit.setDescription("Education");		
		
		Object postBody = dataUnit;
		try {
			String str = apiClient.invokeAPI(path,"POST", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
			System.out.println(str);			
		} catch (ApiException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
				
	}
	
	
	@SuppressWarnings("unchecked")
	@org.junit.Test	
	public void getInvocationTest() throws ClassNotFoundException, SQLException, JsonProcessingException {		
		//the annonymization process is configured by giving:
		//queryName 
		//querySentence to obtain the data to be annonymized
		//data types
		//attribute types
		//kanonynimity
		String queryName = "personalData";
		String sentence = "SELECT DISTINCT"
			       +"`operando_personaldatadb`.`occupation`.`DESCRIPTION_0` AS `OCCUPATION`,"
			       +"`operando_personaldatadb`.`salary_class`.`DESCRIPTION_0` AS `SALARY_CLASS`,"
			       +"`operando_personaldatadb`.`genders`.`DESCRIPTION_0` AS `GENDER`,"
			       +"`operando_personaldatadb`.`education`.`DESCRIPTION_0` AS `EDUCATION`,"
			       +"`operando_personaldatadb`.`countries`.`DESCRIPTION_0` AS `COUNTRY`,"
			       +"`operando_personaldatadb`.`race`.`DESCRIPTION_0` AS `RACE`,"
			       +"`operando_personaldatadb`.`generic_personal_data`.`EMAIL_ADDRESS` AS `EMAIL_ADDRESS`,"
			       +"`operando_personaldatadb`.`generic_personal_data`.`CELL_PHONE_NUMBER`"
			        +"  AS `CELL_PHONE_NUMBER`,"
			       +"`operando_personaldatadb`.`generic_personal_data`.`SURNAME` AS `SURNAME`,"
			       +"`operando_personaldatadb`.`generic_personal_data`.`NUMBER_OF_CHILDREN`"
			        +"  AS `NUMBER_OF_CHILDREN`,"
			       +"`operando_personaldatadb`.`generic_personal_data`.`RESIDENCE_POST_CODE`"
			       +"   AS `RESIDENCE_POST_CODE`,"
			       +"`operando_personaldatadb`.`generic_personal_data`.`NAME` AS `NAME`,"
			       +"`operando_personaldatadb`.`generic_personal_data`.`IDENTIFICATION_NUMBER`"
			        +"  AS `IDENTIFICATION_NUMBER`,"
			       +"`operando_personaldatadb`.`generic_personal_data`.`DATE_OF_BIRTH` AS `DATE_OF_BIRTH`,"
			       +"`operando_personaldatadb`.`generic_personal_data`.`SSN` AS `SSN`,"
			       +"`operando_personaldatadb`.`marital_status`.`DESCRIPTION_0` AS `MARITAL_STATUS`,"
			       +"`operando_personaldatadb`.`work_class`.`DESCRIPTION_0` AS `WORK_CLASS`"
			       +"FROM ((((((((`operando_personaldatadb`.`occupation` JOIN `operando_personaldatadb`.`salary_class`)"
			       +"       JOIN `operando_personaldatadb`.`genders`)"
			       +"      JOIN `operando_personaldatadb`.`education`)"
			       +"     JOIN `operando_personaldatadb`.`countries`)"
			        +"   JOIN `operando_personaldatadb`.`race`)"
			        +"  JOIN `operando_personaldatadb`.`generic_personal_data`"
			        +"     ON ((    (`operando_personaldatadb`.`occupation`.`ID` ="
			        +"                 `operando_personaldatadb`.`generic_personal_data`.`OCCUPATION_ID`)"
			+"          AND (`operando_personaldatadb`.`salary_class`.`ID` ="
			          +"                `operando_personaldatadb`.`generic_personal_data`.`SALARY_CLASS_ID`)"
			         +"         AND (`operando_personaldatadb`.`genders`.`ID` ="
			                		  +"                 `operando_personaldatadb`.`generic_personal_data`.`GENDER_ID`)"
			          +"        AND (`operando_personaldatadb`.`education`.`ID` ="
			          +"`operando_personaldatadb`.`generic_personal_data`.`EDUCATION_ID`)"
			+"        AND (`operando_personaldatadb`.`countries`.`ID` ="
			+"              `operando_personaldatadb`.`generic_personal_data`.`NATIVE_COUNTRY_ID`)"
			+"         AND (`operando_personaldatadb`.`race`.`ID` ="
			+"                        `operando_personaldatadb`.`generic_personal_data`.`RACE_ID`))))"
			+" JOIN `operando_personaldatadb`.`marital_status`"
			           +" ON ((`operando_personaldatadb`.`marital_status`.`ID` ="
			           +"        `operando_personaldatadb`.`generic_personal_data`.`MARITAL_STATUS_ID`)))"
			+"JOIN `operando_personaldatadb`.`work_class`"
			           +"ON ((`operando_personaldatadb`.`work_class`.`ID` ="
			         +"          `operando_personaldatadb`.`generic_personal_data`.`WORK_CLASS_ID`)))";
		HashMap<String, String> dataTypeMap = new HashMap<String, String>();		
		
		//it is used AEDatatype specific bean due to some serialization issues when using org.deidentifier.arx.DataType 
		dataTypeMap.put("NAME", AEDataType.STRING);
		dataTypeMap.put("SURNAME", AEDataType.STRING);
		dataTypeMap.put("IDENTIFICATION_NUMBER", AEDataType.STRING);
		dataTypeMap.put("CELL_PHONE_NUMBER", AEDataType.STRING);
		dataTypeMap.put("EMAIL_ADDRESS", AEDataType.STRING);
		dataTypeMap.put("GENDER", AEDataType.STRING);
		dataTypeMap.put("RACE", AEDataType.STRING);
		dataTypeMap.put("DATE_OF_BIRTH", AEDataType.STRING);
		dataTypeMap.put("COUNTRY", AEDataType.STRING);
		dataTypeMap.put("MARITAL_STATUS", AEDataType.STRING);
		dataTypeMap.put("NUMBER_OF_CHILDREN", AEDataType.INTEGER);
		dataTypeMap.put("EDUCATION", AEDataType.STRING);
		dataTypeMap.put("WORK_CLASS", AEDataType.STRING);
		dataTypeMap.put("OCCUPATION", AEDataType.STRING);
		dataTypeMap.put("SALARY_CLASS", AEDataType.STRING);				
		
		props = loadDbProperties();
		Class.forName(props.getProperty("jdbc.driverClassName"));		
		connect = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
		
		ArrayList<ArrayList<String>> NATIVE_COUNTRY = getHierarchyNativeCountryArrayList(connect);        
        ArrayList<ArrayList<String>> EDUCATION = getHierarchyEducationArrayList(connect);        
        ArrayList<ArrayList<String>> WORK_CLASS = getHierarchyWorkClassArrayList (connect);
        ArrayList<ArrayList<String>> OCCUPATION = getHierarchyOccupationArrayList (connect);
        ArrayList<ArrayList<String>> SALARY_CLASS = getHierarchySalaryClassArrayList (connect);
		
		HashMap<String, Object> attributeTypeMap = new HashMap<String, Object>();		
		
		attributeTypeMap.put("NAME", "IDENTIFYING_ATTRIBUTE");
		attributeTypeMap.put("SURNAME", "IDENTIFYING_ATTRIBUTE");
		attributeTypeMap.put("IDENTIFICATION_NUMBER", "IDENTIFYING_ATTRIBUTE");
		attributeTypeMap.put("CELL_PHONE_NUMBER", "IDENTIFYING_ATTRIBUTE");
		attributeTypeMap.put("EMAIL_ADDRESS", "IDENTIFYING_ATTRIBUTE");
		attributeTypeMap.put("GENDER", "INSENSITIVE_ATTRIBUTE");
		attributeTypeMap.put("RACE", "INSENSITIVE_ATTRIBUTE");
		attributeTypeMap.put("DATE_OF_BIRTH","IDENTIFYING_ATTRIBUTE");
		attributeTypeMap.put("COUNTRY", NATIVE_COUNTRY);
		attributeTypeMap.put("MARITAL_STATUS", "INSENSITIVE_ATTRIBUTE");
		attributeTypeMap.put("NUMBER_OF_CHILDREN", "INSENSITIVE_ATTRIBUTE");
		attributeTypeMap.put("EDUCATION", EDUCATION);
		attributeTypeMap.put("WORK_CLASS", WORK_CLASS);
		attributeTypeMap.put("OCCUPATION", OCCUPATION);
		attributeTypeMap.put("SALARY_CLASS", SALARY_CLASS);
		
		
		int kAnonymity = 2;
		
		ApiClient apiClient = new ApiClient();
		Object localVarPostBody = null;		
				
	
		String path = "/operando/core/ae/personaldata/search";		

	    // query params
	    List<Pair> localVarQueryParams = new ArrayList<Pair>();
	    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
	    Map<String, Object> localVarFormParams = new HashMap<String, Object>();
	    	    	    
	 
	    ObjectMapper mapper = new ObjectMapper();	    
        String JSONDataTypeMap = mapper.writeValueAsString(dataTypeMap); 
        System.out.println("JSONDataTypeMap "+JSONDataTypeMap);
                   
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
       
        mapper = new ObjectMapper();     
        
        String JSONAttributeTypeMap = mapper.writeValueAsString(attributeTypeMap);
        	       
	    
	    SearchRequest searchRequest = new SearchRequest();
	    //the requester id just in case it is neccesary to filter by requester
	    searchRequest.setRequesterId("1");
	    searchRequest.setQuery(sentence);
	    searchRequest.setQueryName(queryName);
	    searchRequest.setDataTypeMap(JSONDataTypeMap);
	    searchRequest.setAttributeTypeMap(JSONAttributeTypeMap);
	    searchRequest.setKAnonymity(new Integer(kAnonymity));
	    	    	    	    	    
	    
	    String JSONSearchRequest = mapper.writeValueAsString(searchRequest);
	    
	    
	    localVarPostBody = JSONSearchRequest;
	    
	    final String[] localVarAccepts = {
	      "application/xml", "application/json"
	    };
	    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

	    final String[] localVarContentTypes = {
	      
	    };
	    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

	    String[] localVarAuthNames = new String[] {  };

	    GenericType returnType = new GenericType<String>() {};
	     try {
	    	     	
	    	 String str = apiClient.invokeAPI(path, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
	    	 System.out.println(str);
	    	 //next an example of a value returned
	    	 //{"code":4,"type":"ok","message":"[SURNAME, NUMBER_OF_CHILDREN, MARITAL_STATUS, EDUCATION, NAME, CELL_PHONE_NUMBER, SALARY_CLASS, COUNTRY, EMAIL_ADDRESS, RACE, OCCUPATION, GENDER, WORK_CLASS, DATE_OF_BIRTH, IDENTIFICATION_NUMBER][*, 2, Married-civ-spouse, Bachelors, *, *, <=50K, United-States, *, White, Tech-suppo, Male, Private, *, *][*, 2, Married-civ-spouse, Bachelors, *, *, <=50K, United-States, *, Asian-Pac-Islander, Tech-suppo, Male, Private, *, *][*, 2, Married-civ-spouse, Bachelors, *, *, <=50K, United-States, *, Amer-Indian-Eskimo, Tech-suppo, Male, Private, *, *][*, 2, Married-civ-spouse, Bachelors, *, *, <=50K, United-States, *, Other, Tech-suppo, Male, Private, *, *][*, 2, Married-civ-spouse, Bachelors, *, *, <=50K, United-States, *, Black, Tech-suppo, Male, Private, *, *]"}
	    	 
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 				
	}

	
	
	
	public static void main(String[] args) throws Exception {                    
	       JUnitCore.main(
	         "eu.operando.core.ae.test.Test");		
	}	
		
	
	private static ArrayList<ArrayList<String>> getHierarchyOccupationArrayList(Connection connect) throws ClassNotFoundException, SQLException {
		ArrayList<ArrayList<String>> occupationHierarchy = new ArrayList<ArrayList<String>>();
		ArrayList<String> occupationHierarchyRecord;
		// Statements allow to issue SQL queries to the database
		Statement statement = connect.createStatement();
		// Result set get the result of the SQL query
		resultSet = statement
          .executeQuery("select DESCRIPTION_0,DESCRIPTION_1,DESCRIPTION_2 FROM occupation");
		while (resultSet.next()){			
			occupationHierarchyRecord = new ArrayList<String>(3);
			occupationHierarchyRecord.add(resultSet.getString("DESCRIPTION_0"));
			occupationHierarchyRecord.add(resultSet.getString("DESCRIPTION_1"));
			occupationHierarchyRecord.add(resultSet.getString("DESCRIPTION_2"));
			occupationHierarchy.add(occupationHierarchyRecord);
		}
		resultSet.close();
		statement.close();		
		return occupationHierarchy;
	}	
	
	private static ArrayList<ArrayList<String>> getHierarchySalaryClassArrayList(Connection connect) throws ClassNotFoundException, SQLException {
		ArrayList<ArrayList<String>> salaryClassHierarchy = new ArrayList<ArrayList<String>>();
		ArrayList<String> salaryClassHierarchyRecord;
		// Statements allow to issue SQL queries to the database
		Statement statement = connect.createStatement();
		// Result set get the result of the SQL query
		resultSet = statement
          .executeQuery("select DESCRIPTION_0,DESCRIPTION_1,DESCRIPTION_2 FROM salary_class");
		while (resultSet.next()){		
			salaryClassHierarchyRecord = new ArrayList<String>(3);
			salaryClassHierarchyRecord.add(resultSet.getString("DESCRIPTION_0"));
			salaryClassHierarchyRecord.add(resultSet.getString("DESCRIPTION_1"));
			salaryClassHierarchyRecord.add(resultSet.getString("DESCRIPTION_2"));
			salaryClassHierarchy.add(salaryClassHierarchyRecord);
		}
		resultSet.close();
		statement.close();		
		return salaryClassHierarchy;
	}	
	
	private static ArrayList<ArrayList<String>> getHierarchyWorkClassArrayList(Connection connect) throws ClassNotFoundException, SQLException {
		ArrayList<ArrayList<String>> workClassHierarchy = new ArrayList<ArrayList<String>>();
		ArrayList<String> workClassHierarchyRecord;
		// Statements allow to issue SQL queries to the database
		Statement statement = connect.createStatement();
		// Result set get the result of the SQL query
		resultSet = statement
          .executeQuery("select DESCRIPTION_0,DESCRIPTION_1,DESCRIPTION_2 FROM work_class");
		while (resultSet.next()){
			workClassHierarchyRecord = new ArrayList<String>(3);
			workClassHierarchyRecord.add(resultSet.getString("DESCRIPTION_0"));
			workClassHierarchyRecord.add(resultSet.getString("DESCRIPTION_1"));
			workClassHierarchyRecord.add(resultSet.getString("DESCRIPTION_2"));
			workClassHierarchy.add(workClassHierarchyRecord);
		}
		resultSet.close();
		statement.close();		
		return workClassHierarchy;
	}	
	
	private static ArrayList<ArrayList<String>> getHierarchyEducationArrayList(Connection connect) throws ClassNotFoundException, SQLException {
		ArrayList<ArrayList<String>> educationHierarchy = new ArrayList<ArrayList<String>>();
		ArrayList<String> educationHierarchyRecord;
		// Statements allow to issue SQL queries to the database
		Statement statement = connect.createStatement();
		// Result set get the result of the SQL query
		resultSet = statement
          .executeQuery("select DESCRIPTION_0,DESCRIPTION_1,DESCRIPTION_2 FROM education");
		while (resultSet.next()){			
			educationHierarchyRecord = new ArrayList<String>(3);
			educationHierarchyRecord.add(resultSet.getString("DESCRIPTION_0"));
			educationHierarchyRecord.add(resultSet.getString("DESCRIPTION_1"));
			educationHierarchyRecord.add(resultSet.getString("DESCRIPTION_2"));
			educationHierarchy.add(educationHierarchyRecord);
		}
		resultSet.close();
		statement.close();		
		return educationHierarchy;
	}	
	
	private static ArrayList<ArrayList<String>> getHierarchyNativeCountryArrayList(Connection connect) throws ClassNotFoundException, SQLException {		
		//String[][] array = new String[][3];
		ArrayList<ArrayList<String>> nativeCountryHierarchy = new ArrayList<ArrayList<String>>();
		ArrayList<String> nativeCountryRecord;
		// Statements allow to issue SQL queries to the database
		Statement statement = (Statement) connect.createStatement();
		// Result set get the result of the SQL query
		ResultSet resultSet = statement
          .executeQuery("select DESCRIPTION_0,DESCRIPTION_1,DESCRIPTION_2 FROM countries");
		while (resultSet.next()){
			nativeCountryRecord = new ArrayList<String>(3);
			nativeCountryRecord.add(resultSet.getString("DESCRIPTION_0"));
			nativeCountryRecord.add(resultSet.getString("DESCRIPTION_1"));
			nativeCountryRecord.add(resultSet.getString("DESCRIPTION_2"));
			nativeCountryHierarchy.add(nativeCountryRecord);			
		}
		resultSet.close();
		statement.close();		
		return nativeCountryHierarchy;
		
	}
	
    
	private static Properties loadDbProperties() {
		Properties props;
		props = new Properties();
		
		InputStream fis = null;
		try {
		    fis = Test.class.getResourceAsStream("db.properties");
		    props.load(fis);
		}     catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		return props;
	}

}
