package eu.operando.core.annonymization.test;

import java.io.IOException;
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

import org.deidentifier.arx.ARXAnonymizer;
import org.deidentifier.arx.ARXConfiguration;
import org.deidentifier.arx.ARXResult;
import org.deidentifier.arx.AttributeType;
import org.deidentifier.arx.AttributeType.Hierarchy;
import org.deidentifier.arx.AttributeType.Hierarchy.DefaultHierarchy;
import org.deidentifier.arx.Data;
import org.deidentifier.arx.DataHandle;
import org.deidentifier.arx.DataSource;
import org.deidentifier.arx.DataType;
import org.deidentifier.arx.criteria.KAnonymity;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Pair;
import io.swagger.client.model.DataUnit;
import io.swagger.client.model.DataUnitValuePerAccessLevel;

public class Test {
	private Connection connect = null;
	private Statement statement = null;	
	private ResultSet resultSet = null;

	@org.junit.Test
	public void invocationTest() {		
		ApiClient apiClient = new ApiClient();
		 
	    byte[] postBinaryBody = null; 
	     
	    // create path and map variables 
	    String path = "/dataUnit"; 
	 
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
		dataUnit.setValuesPerAccessLevel(valuesPerAccessLevel);
		
		Object postBody = dataUnit;
		try {
			String str = apiClient.invokeAPI("/dataUnit","POST", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
			System.out.println(str);
		} catch (ApiException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
				
	}
	
	@org.junit.Test
	public void arxAnonymizationTest() throws ClassNotFoundException, SQLException, IOException {
		// Load JDBC driver
        Class.forName("com.mysql.jdbc.Driver");        
        // Configuration for JDBC source
        DataSource source = DataSource.createJDBCSource("jdbc:mysql://localhost:3306/operando_personaldatadb?user=root&password=root","operando_personaldata_view");    
        
        source.addColumn("NAME", DataType.STRING);
        source.addColumn("SURNAME", DataType.STRING);
        source.addColumn("IDENTIFICATION_NUMBER", DataType.STRING); 
        source.addColumn("CELL_PHONE_NUMBER", DataType.STRING);
        source.addColumn("EMAIL_ADDRESS", DataType.STRING);
        source.addColumn("GENDER", DataType.STRING); 
        source.addColumn("RACE", DataType.STRING);
        source.addColumn("DATE_OF_BIRTH", DataType.STRING);        
        source.addColumn("COUNTRY", DataType.STRING); 
        source.addColumn("MARITAL_STATUS", DataType.STRING);
        source.addColumn("NUMBER_OF_CHILDREN", DataType.INTEGER);
        source.addColumn("EDUCATION", DataType.STRING);         
        source.addColumn("WORK_CLASS", DataType.STRING);
        source.addColumn("OCCUPATION", DataType.STRING);
        source.addColumn("SALARY_CLASS", DataType.STRING);
        
        // This will load the MySQL driver, each DB has its own driver
 		Class.forName("com.mysql.jdbc.Driver");
 		// Setup the connection with the DB
 		connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/operando_personaldatadb?user=root&password=root"); 		
        
        DefaultHierarchy NATIVE_COUNTRY = getHierarchyNativeCountry (connect);        
        DefaultHierarchy EDUCATION = getHierarchyEducation (connect);        
        DefaultHierarchy WORK_CLASS = getHierarchyWorkClass (connect);
        DefaultHierarchy OCCUPATION = getHierarchyOccupation (connect);
        DefaultHierarchy SALARY_CLASS = getHierarchySalaryClass (connect);
                
        connect.close();

        // Create data object
        Data data = Data.create(source);
        
        data.getDefinition().setAttributeType("NAME", AttributeType.IDENTIFYING_ATTRIBUTE);
        data.getDefinition().setAttributeType("SURNAME", AttributeType.IDENTIFYING_ATTRIBUTE);
        data.getDefinition().setAttributeType("IDENTIFICATION_NUMBER", AttributeType.IDENTIFYING_ATTRIBUTE);
        data.getDefinition().setAttributeType("CELL_PHONE_NUMBER", AttributeType.IDENTIFYING_ATTRIBUTE);
        data.getDefinition().setAttributeType("EMAIL_ADDRESS", AttributeType.IDENTIFYING_ATTRIBUTE);
        data.getDefinition().setAttributeType("GENDER", AttributeType.INSENSITIVE_ATTRIBUTE);
        data.getDefinition().setAttributeType("RACE", AttributeType.INSENSITIVE_ATTRIBUTE);
        data.getDefinition().setAttributeType("DATE_OF_BIRTH", AttributeType.IDENTIFYING_ATTRIBUTE);        
        data.getDefinition().setAttributeType("COUNTRY", NATIVE_COUNTRY); 
        data.getDefinition().setAttributeType("MARITAL_STATUS", AttributeType.INSENSITIVE_ATTRIBUTE);
        data.getDefinition().setAttributeType("NUMBER_OF_CHILDREN", AttributeType.INSENSITIVE_ATTRIBUTE);
        data.getDefinition().setAttributeType("EDUCATION", EDUCATION);        
        data.getDefinition().setAttributeType("WORK_CLASS", WORK_CLASS);
        data.getDefinition().setAttributeType("OCCUPATION", OCCUPATION);
        data.getDefinition().setAttributeType("SALARY_CLASS", SALARY_CLASS);                
        
        ARXAnonymizer anonymizer = new ARXAnonymizer();
        ARXConfiguration config = ARXConfiguration.create();
        config.addCriterion(new KAnonymity(2));
        ARXResult result = anonymizer.anonymize(data, config);

        Iterator<String[]> transformed = result.getOutput().iterator();
        int i =0;
        while (transformed.hasNext()) {
            System.out.print("   ");
            i++;
            System.out.println(Arrays.toString(transformed.next()));
        }        
	}
	
	private DefaultHierarchy getHierarchyOccupation(Connection connect) throws ClassNotFoundException, SQLException {
		DefaultHierarchy occupationHierarchy = Hierarchy.create();
		// Statements allow to issue SQL queries to the database
		Statement statement = connect.createStatement();
		// Result set get the result of the SQL query
		resultSet = statement
          .executeQuery("select DESCRIPTION_0,DESCRIPTION_1,DESCRIPTION_2 FROM occupation");
		while (resultSet.next()){
			occupationHierarchy.add(resultSet.getString("DESCRIPTION_0"), resultSet.getString("DESCRIPTION_1"), resultSet.getString("DESCRIPTION_2"));			
		}
		resultSet.close();
		statement.close();		
		return occupationHierarchy;
	}

	private DefaultHierarchy getHierarchySalaryClass(Connection connect) throws ClassNotFoundException, SQLException {
		DefaultHierarchy salaryClassHierarchy = Hierarchy.create();
		// Statements allow to issue SQL queries to the database
		Statement statement = connect.createStatement();
		// Result set get the result of the SQL query
		resultSet = statement
          .executeQuery("select DESCRIPTION_0,DESCRIPTION_1,DESCRIPTION_2 FROM salary_class");
		while (resultSet.next()){
			salaryClassHierarchy.add(resultSet.getString("DESCRIPTION_0"), resultSet.getString("DESCRIPTION_1"), resultSet.getString("DESCRIPTION_2"));			
		}
		resultSet.close();
		statement.close();		
		return salaryClassHierarchy;
	}

	private DefaultHierarchy getHierarchyWorkClass(Connection connect) throws ClassNotFoundException, SQLException {
		DefaultHierarchy workClassHierarchy = Hierarchy.create();
		// Statements allow to issue SQL queries to the database
		Statement statement = connect.createStatement();
		// Result set get the result of the SQL query
		resultSet = statement
          .executeQuery("select DESCRIPTION_0,DESCRIPTION_1,DESCRIPTION_2 FROM work_class");
		while (resultSet.next()){
			workClassHierarchy.add(resultSet.getString("DESCRIPTION_0"), resultSet.getString("DESCRIPTION_1"), resultSet.getString("DESCRIPTION_2"));			
		}
		resultSet.close();
		statement.close();		
		return workClassHierarchy;
	}	

	private DefaultHierarchy getHierarchyEducation(Connection connect) throws ClassNotFoundException, SQLException {
		DefaultHierarchy educationHierarchy = Hierarchy.create();
		// Statements allow to issue SQL queries to the database
		Statement statement = connect.createStatement();
		// Result set get the result of the SQL query
		resultSet = statement
          .executeQuery("select DESCRIPTION_0,DESCRIPTION_1,DESCRIPTION_2 FROM education");
		while (resultSet.next()){
			educationHierarchy.add(resultSet.getString("DESCRIPTION_0"), resultSet.getString("DESCRIPTION_1"), resultSet.getString("DESCRIPTION_2"));			
		}
		resultSet.close();
		statement.close();		
		return educationHierarchy;
	}

	private DefaultHierarchy getHierarchyNativeCountry(Connection connect) throws ClassNotFoundException, SQLException {
		DefaultHierarchy nativeCountryHierarchy = Hierarchy.create();
		// Statements allow to issue SQL queries to the database
		Statement statement = connect.createStatement();
		// Result set get the result of the SQL query
		resultSet = statement
          .executeQuery("select DESCRIPTION_0,DESCRIPTION_1,DESCRIPTION_2 FROM countries");
		while (resultSet.next()){
			nativeCountryHierarchy.add(resultSet.getString("DESCRIPTION_0"), resultSet.getString("DESCRIPTION_1"), resultSet.getString("DESCRIPTION_2"));			
		}
		resultSet.close();
		statement.close();		
		return nativeCountryHierarchy;
		
	}

	/**
     * Prints a given data handle.
     *
     * @param handle
     */
    protected static void print(DataHandle handle) {
        final Iterator<String[]> itHandle = handle.iterator();
        print(itHandle);
    }

    /**
     * Prints a given iterator.
     *
     * @param iterator
     */
    protected static void print(Iterator<String[]> iterator) {
        while (iterator.hasNext()) {
            System.out.print("   ");
            System.out.println(Arrays.toString(iterator.next()));
        }
    }  

}
