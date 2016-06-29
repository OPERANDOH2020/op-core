package io.swagger.api.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Properties;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.LogApiService;
import io.swagger.api.NotFoundException;
import io.swagger.model.LogResponse;
import io.swagger.model.LogResponse.LogLevelEnum;
import io.swagger.model.LogResponse.LogPriorityEnum;
import io.swagger.model.LogResponse.RequesterTypeEnum;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-06T10:10:57.937Z")
public class LogApiServiceImpl extends LogApiService {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	
	
    @Override
    public Response getLogs(String dateFrom, String dateTo, String logLevel, String requesterType, String requesterId, String logPriority, String title, String keyWords, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
    	
    	String strSelect;
    	strSelect = composeSqlQuery(dateFrom, dateTo, logLevel, requesterType, requesterId, logPriority, title, keyWords);
    	
    	Properties props;
    	props = loadDbProperties();

    	
    	
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic! " + strSelect)).build();
    }

	/**
	 * @param dateFrom
	 * @param dateTo
	 * @param logLevel
	 * @param requesterType
	 * @param requesterId
	 * @param logPriority
	 * @param title
	 * @param keyWords
	 */
	private String composeSqlQuery(String dateFrom, String dateTo, String logLevel, String requesterType,
			String requesterId, String logPriority, String title, String keyWords) {
		String strSelect;
		strSelect = "select * from operando_logdb.LOGS";    
        StringBuffer strBufferSelect = new StringBuffer(strSelect);
        String keyValue = "";
        boolean boolAnd = false;
        boolean boolOr = false;
        ArrayList<String> arrayListKeyWords = null;
        
        if (true) return strSelect;
        
        if (!((dateFrom=="") && (dateTo=="") && (logLevel=="") && (requesterType=="") && (requesterId=="") && (logPriority=="") && (title=="") && (keyWords==null))){
        	strBufferSelect.append(" WHERE ");
        	if ((dateFrom!=null)&(!dateFrom.equals(""))){
        		strBufferSelect.append("DATED >= '"+dateFrom+"'");
        		boolAnd = true;
        	}
        	if ((dateTo!=null)&(!dateTo.equals(""))){
        		if (boolAnd)
        			strBufferSelect.append(" AND ");
        		strBufferSelect.append("DATED <= '"+dateTo+"'");
        		boolAnd = true;
        	}
        	if ((logLevel!=null)&(!logLevel.equals(""))){
        		if (boolAnd)
        			strBufferSelect.append(" AND ");
        		strBufferSelect.append("LEVEL == "+logLevel+"");
        		boolAnd = true;
        	}
        	if ((requesterType!=null)&(!requesterType.equals(""))){
        		if (boolAnd)
        			strBufferSelect.append(" AND ");
        		strBufferSelect.append("REQUESTERTYPE == "+requesterType+"");
        		boolAnd = true;
        	}
        	if ((requesterId!=null)&(!requesterId.equals(""))){
        		if (boolAnd)
        			strBufferSelect.append(" & ");
        		strBufferSelect.append("REQUESTERID == "+requesterId+"");
        		boolAnd = true;
        	}
        	if ((logPriority!=null)&(!logPriority.equals(""))){
        		if (boolAnd)
        			strBufferSelect.append(" AND ");
        		strBufferSelect.append("LOGPRIORITY == "+logPriority+"");
        		boolAnd = true;
        	}    	
        	if (keyWords!=null){    		
        		Gson gson=new Gson();
        		TypeToken<ArrayList<String>> token = new TypeToken<ArrayList<String>>() {};
        		arrayListKeyWords = gson.fromJson(keyWords, token.getType());
        		ListIterator<String> listIterator = arrayListKeyWords.listIterator();
        		if (boolAnd)
        			strBufferSelect.append(" AND ");    		
        		while (listIterator.hasNext()){
        			keyValue = listIterator.next();
        			if (boolOr)
        				strBufferSelect.append(" || "); 
        			strBufferSelect.append("KEYWORDS LIKE '%"+keyValue+"%'");
        			boolOr = true;
        		}
        		    		
        	}    	    	
        	strSelect = strBufferSelect.toString();
        	System.out.println(strSelect);
        }
        return strSelect;
	}
    
    /* (non-Javadoc)
     * @see io.swagger.api.LogApiService#getLogs(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, javax.ws.rs.core.SecurityContext)
     * This method returns 0 to n log records that are stored in the log database depending on a filter (log4j is used internally) 
     */
    //@Override
    public Response getLogsWeird(String dateFrom, String dateTo, String logLevel, String requesterType, String requesterId, String logPriority, String title, String keyWords, SecurityContext securityContext) throws NotFoundException {
		String strSelect;
		strSelect = composeSqlQuery(dateFrom, dateTo, logLevel, requesterType, requesterId, logPriority, title, keyWords);    	
	    
	     //GBE added code to get db information form a properties file
		Properties props;
		props = loadDbProperties();
 
    	LogResponse logResponse;
        ArrayList<LogResponse> logResponsesArray = new ArrayList<LogResponse> ();   	
	    try {  	
/*		Class.forName(props.getProperty("jdbc.driverClassName"));
		connection = DriverManager
			    .getConnection(props.getProperty("jdbc.url"),
			    		props.getProperty("jdbc.username"),
			    		props.getProperty("jdbc.password"));
	    //GBE end
*/
    	Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager
			    .getConnection("jdbc:mysql://10.0.0.5:3306/operando_logdb",
			    		"root",
			    		"root");	
		 statement = connection.createStatement();
		 resultSet = statement
		          .executeQuery(strSelect);		 
		 while (resultSet.next()){
			 logResponse = new LogResponse();
			 logResponse.setLogDate(resultSet.getString("DATED"));			 
			 LogLevelEnum logLevelEnum = LogLevelEnum.valueOf(resultSet.getString("LEVEL"));
			 logResponse.setLogLevel(logLevelEnum);
			 LogPriorityEnum logPriorityEnum = LogPriorityEnum.valueOf(resultSet.getString("LOGPRIORITY"));
			 logResponse.setLogPriority(logPriorityEnum);
			 logResponse.setRequesterId(resultSet.getString("REQUESTERID"));
			 RequesterTypeEnum requesterTypeEnum = RequesterTypeEnum.valueOf(resultSet.getString("REQUESTERTYPE"));
			 logResponse.setRequesterType(requesterTypeEnum);
			 logResponse.setTitle(resultSet.getString("TITLE"));
			 logResponse.setDescription(resultSet.getString("MESSAGE"));
			 logResponsesArray.add(logResponse);
		 }		 		 
	}	
	catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}        		
	catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	finally{
		try {
			resultSet.close();		
			statement.close();
			connection.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	return Response.ok().entity(logResponsesArray).build();
    }

	/**
	 * 
	 */
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
