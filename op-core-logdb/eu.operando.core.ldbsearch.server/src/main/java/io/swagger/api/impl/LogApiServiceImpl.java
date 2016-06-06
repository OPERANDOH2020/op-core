package io.swagger.api.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

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
    public Response getLogs(String dateFrom, String dateTo, String logLevel, String requesterType, String requesterId, String logPriority, String title, List<String> keyWords, SecurityContext securityContext) throws NotFoundException {
    String select = "";
    LogResponse logResponse = new LogResponse();
    ArrayList logResponsesArray = new ArrayList ();
    
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager
			    .getConnection("jdbc:mysql://localhost/operando_logdb?"
			        + "user=root&password=root");
		 statement = connection.createStatement();
		 resultSet = statement
		          .executeQuery("select * from LOGS");		 
		 while (resultSet.next()){
			 logResponse.setLogDate(resultSet.getString("DATED"));			 
			 LogLevelEnum logLevelEnum = LogLevelEnum.valueOf(resultSet.getString("LEVEL"));
			 logResponse.setLogLevel(logLevelEnum);
			 LogPriorityEnum logPriorityEnum = LogPriorityEnum.valueOf(resultSet.getString("LOGPRIORITY"));
			 logResponse.setLogPriority(logPriorityEnum);
			 logResponse.setRequesterId(resultSet.getString("DATED"));
			 RequesterTypeEnum requesterTypeEnum = RequesterTypeEnum.valueOf(resultSet.getString("REQUESTERTYPE"));
			 logResponse.setRequesterType(requesterTypeEnum);
			 logResponse.setTitle(resultSet.getString("DATED"));
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
	return Response.ok().entity(logResponsesArray).build();
    }
}
