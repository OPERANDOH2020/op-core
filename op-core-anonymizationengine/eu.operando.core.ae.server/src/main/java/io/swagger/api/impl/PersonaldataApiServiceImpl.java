package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.Error;
import io.swagger.model.InlineResponse2003;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.swagger.api.NotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.deidentifier.arx.ARXAnonymizer;
import org.deidentifier.arx.ARXConfiguration;
import org.deidentifier.arx.ARXResult;
import org.deidentifier.arx.AttributeType;
import org.deidentifier.arx.Data;
import org.deidentifier.arx.DataHandle;
import org.deidentifier.arx.DataSource;
import org.deidentifier.arx.DataType;
import org.deidentifier.arx.AttributeType.Hierarchy;
import org.deidentifier.arx.AttributeType.Hierarchy.DefaultHierarchy;
import org.deidentifier.arx.criteria.KAnonymity;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-10T09:36:15.284Z")
public class PersonaldataApiServiceImpl extends PersonaldataApiService {
	
	private Connection connect = null;		
	private ResultSet resultSet = null;
    static Logger mainLogger = null;
    static {
        mainLogger = Logger.getLogger("eu.operando.core.ae.server");
    }
    
    @Override
    public Response getPersonalData(String requesterId, String query, SecurityContext securityContext)
    throws NotFoundException {    	
        // do some magic!    
    	mainLogger.logp(Level.INFO, "getPersonalData", "main", "Testing logging in tomcat docker ... ");
    	String strAnnonymizedData = "";
    	System.out.println(query);
    	System.out.println(requesterId);
        //if (query.equalsIgnoreCase("operando_personaldata_view")){
        	
        	try {
        		strAnnonymizedData = arxAnonymizationPersonalData();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        //}
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, strAnnonymizedData)).build();        	
    }
    
private String arxAnonymizationPersonalData() throws ClassNotFoundException, SQLException, IOException {
		
		String strAnnonymizedData="";
		
    	Properties props;
    	props = loadDbProperties();

    	// Load JDBC driver
        Class.forName(props.getProperty("jdbc.driverClassName"));        
        // Configuration for JDBC source
        DataSource source = DataSource.createJDBCSource(props.getProperty("jdbc.url"),
	    		props.getProperty("jdbc.username"),
	    		props.getProperty("jdbc.password"),
	    		"operando_personaldata_view");        
        //DataSource source = DataSource.createJDBCSource("jdbc:mysql://localhost:3306/operando_personaldatadb?user=root&password=root","operando_personaldata_view");    
        
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

    	mainLogger.logp(Level.INFO, "getPersonalData", "main", "Database source success  ... ");

        // This will load the MySQL driver, each DB has its own driver
		Class.forName(props.getProperty("jdbc.driverClassName"));
 		// Setup the connection with the DB
 		connect = DriverManager.getConnection(props.getProperty("jdbc.url"),
	    		props.getProperty("jdbc.username"),
	    		props.getProperty("jdbc.password"));
 		
        DefaultHierarchy NATIVE_COUNTRY = getHierarchyNativeCountry (connect);        
        DefaultHierarchy EDUCATION = getHierarchyEducation (connect);        
        DefaultHierarchy WORK_CLASS = getHierarchyWorkClass (connect);
        DefaultHierarchy OCCUPATION = getHierarchyOccupation (connect);
        DefaultHierarchy SALARY_CLASS = getHierarchySalaryClass (connect);
                
        connect.close();
        
    	mainLogger.logp(Level.INFO, "getPersonalData", "main", "Default Hierarchies success  ... ");

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
        StringBuffer strBufferAnnonymizedData = new StringBuffer("");
        while (transformed.hasNext()) {
            System.out.print("   ");
            i++;
            String outputmessage = Arrays.toString(transformed.next());
            strBufferAnnonymizedData.append(outputmessage);
            //System.out.println(outputmessage);
        	mainLogger.logp(Level.INFO, "getPersonalData", "main", outputmessage);
        }        
        strAnnonymizedData = strBufferAnnonymizedData.toString();
        return (strAnnonymizedData);
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
            String outputmessage = Arrays.toString(iterator.next());
            System.out.println(outputmessage);
        	mainLogger.logp(Level.INFO, "getPersonalData", "main", "PRINT FUNCTION: " + outputmessage);
       }
    }  
    
	private Properties loadDbProperties() {
		Properties props;
		props = new Properties();
		
		InputStream fis = null;
		try {
		    fis = this.getClass().getClassLoader().getResourceAsStream("/db.properties");
		    props.load(fis);
		}     catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		return props;
	}

    
}
