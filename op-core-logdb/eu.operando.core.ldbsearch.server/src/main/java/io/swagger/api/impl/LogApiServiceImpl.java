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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.LogApiService;
import io.swagger.api.NotFoundException;
import io.swagger.model.LogResponse;
import io.swagger.model.LogResponseExt;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-06T10:10:57.937Z")
public class LogApiServiceImpl extends LogApiService {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	// @Override
	public Response getLogsTest(String dateFrom, String dateTo, String logLevel, String requesterType,
			String requesterId, String logPriority, String title, String keyWords, String logType,
			String affectedUserId, SecurityContext securityContext) throws NotFoundException {
		// do some magic!

		String strSelect;
		strSelect = composeSqlQuery(dateFrom, dateTo, logLevel, requesterType, requesterId, logPriority, title,
				keyWords, logType, affectedUserId,"");

		Properties props;
		props = loadDbProperties();

		connection = getDbConnection(props);
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(strSelect);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<LogResponse> logResponsesArray = null;
		try {
			// resultSet.next();
			// value=resultSet.getString("DATED");
			logResponsesArray = composeResultsFromResultSet();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic! " + logResponsesArray.size()))
				.build();
	}

	/**
	 * @throws SQLException
	 */
	private ArrayList<LogResponse> composeResultsFromResultSet() throws SQLException {
		ArrayList<LogResponse> logResponsesArray = new ArrayList<LogResponse>();
		LogResponse logResponse;

		while (resultSet.next()) {
			logResponse = new LogResponse();
			logResponse.setLogDate(resultSet.getString("DATED"));
			// GBE: Attention!!! if the value is in lowercase it crashes, we add
			// a uppercase function
			if (resultSet.getString("LEVEL") != null)
				logResponse.setLogLevel(resultSet.getString("LEVEL").toUpperCase());

			if (resultSet.getString("LOGPRIORITY") != null)
				logResponse.setLogPriority(resultSet.getString("LOGPRIORITY").toUpperCase());
			logResponse.setRequesterId(resultSet.getString("REQUESTERID"));
			if (resultSet.getString("REQUESTERTYPE") != null)
				logResponse.setRequesterType(resultSet.getString("REQUESTERTYPE").toUpperCase());
			logResponse.setTitle(resultSet.getString("TITLE"));
			logResponse.setDescription(resultSet.getString("MESSAGE"));
			if (resultSet.getString("LOGTYPE") != null)
				logResponse.setLogType(resultSet.getString("LOGTYPE").toUpperCase());
			logResponse.setAffectedUserId(resultSet.getString("AFFECTEDUSERID"));

			logResponsesArray.add(logResponse);
		}

		/*
		 * | USER_ID | DATED | LOGGER | LEVEL | REQUESTERTYPE | REQUESTERID |
		 * LOGPRIORITY | KEYWORDS | TITLE | MESSAGE
		 */
		return logResponsesArray;
	}
	
	private ArrayList<LogResponseExt> composeResultsFromResultSetExt() throws SQLException {
		ArrayList<LogResponseExt> logResponsesArray = new ArrayList<LogResponseExt>();
		LogResponseExt logResponseExt;

		while (resultSet.next()) {
			logResponseExt = new LogResponseExt();
			logResponseExt.setLogDate(resultSet.getString("DATED"));
			// GBE: Attention!!! if the value is in lowercase it crashes, we add
			// a uppercase function
			if (resultSet.getString("LEVEL") != null)
				logResponseExt.setLogLevel(resultSet.getString("LEVEL").toUpperCase());

			if (resultSet.getString("LOGPRIORITY") != null)
				logResponseExt.setLogPriority(resultSet.getString("LOGPRIORITY").toUpperCase());
			logResponseExt.setRequesterId(resultSet.getString("REQUESTERID"));
			if (resultSet.getString("REQUESTERTYPE") != null)
				logResponseExt.setRequesterType(resultSet.getString("REQUESTERTYPE").toUpperCase());
			logResponseExt.setTitle(resultSet.getString("TITLE"));
			logResponseExt.setDescription(resultSet.getString("MESSAGE"));
			if (resultSet.getString("LOGTYPE") != null)
				logResponseExt.setLogType(resultSet.getString("LOGTYPE").toUpperCase());
			logResponseExt.setAffectedUserId(resultSet.getString("AFFECTEDUSERID"));
			if (resultSet.getString("OSP") != null)
				logResponseExt.setOspId(resultSet.getString("OSP"));
			if (resultSet.getString("REQUESTEDFIELDS") != null)
				logResponseExt.setArrayRequestedFields(parseToArray(resultSet.getString("REQUESTEDFIELDS")));
			if (resultSet.getString("GRANTEDFIELDS") != null)
				logResponseExt.setArrayGrantedFields(parseToArray(resultSet.getString("GRANTEDFIELDS")));
			
			
			logResponsesArray.add(logResponseExt);
		}

		/*
		 * | USER_ID | DATED | LOGGER | LEVEL | REQUESTERTYPE | REQUESTERID |
		 * LOGPRIORITY | KEYWORDS | TITLE | MESSAGE
		 */
		return logResponsesArray;
	}

	private ArrayList<String> parseToArray(String strToParse) {
		ArrayList<String> arrayToReturn = new ArrayList<String>();
		if (strToParse != null) {				
			if (strToParse.length() > 0) {
				Gson gson = new Gson();
				TypeToken<ArrayList<String>> token = new TypeToken<ArrayList<String>>() {
				};
				arrayToReturn = gson.fromJson(strToParse, token.getType());				
			}
		}
		return(arrayToReturn);
	}

	private Connection getDbConnection(Properties props) {
		Connection connection = null;

		try {
			Class.forName(props.getProperty("jdbc.driverClassName"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"),
					props.getProperty("jdbc.password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}	
	
	@Override
	public Response getLogs(String dateFrom, String dateTo, String logLevel, String requesterType, String requesterId,
			String logPriority, String title, String keyWords, String logType, String affectedUserId, String ospId,
			SecurityContext securityContext) throws NotFoundException {
		String strSelect;
		strSelect = composeSqlQuery(dateFrom, dateTo, logLevel, requesterType, requesterId, logPriority, title,
				keyWords, logType, affectedUserId,ospId);

		// GBE added code to get db information form a properties file
		Properties props;
		props = loadDbProperties();

		connection = getDbConnection(props);
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(strSelect);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<LogResponseExt> logResponsesArray = null;
		try {
			// resultSet.next();
			// value=resultSet.getString("DATED");
			logResponsesArray = composeResultsFromResultSetExt();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
	 * @param dateFrom
	 * @param dateTo
	 * @param logLevel
	 * @param requesterType
	 * @param requesterId
	 * @param logPriority
	 * @param title
	 * @param keyWords
	 * @param logType
	 * @param affectedUserId
	 * @param ospId
	 * @return
	 */
	private String composeSqlQuery(String dateFrom, String dateTo, String logLevel, String requesterType,
			String requesterId, String logPriority, String title, String keyWords, String logType,
			String affectedUserId, String ospId) {
		String strSelect = "select * from operando_logdb.LOGS";
		StringBuffer strBufferSelect = new StringBuffer(strSelect);
		String keyValue = "";
		boolean boolAnd = false;
		boolean boolOr = false;
		ArrayList<String> arrayListKeyWords = null;

		if (!(((dateFrom == "") || (dateFrom == null)) && ((dateTo == "") || (dateTo == null))
				&& ((logLevel == "") || (logLevel == null)) && ((requesterType == "") || (requesterType == null))
				&& ((requesterId == "") || (requesterId == null)) && ((logPriority == "") || (logPriority == null))
				&& ((title == "") || (title == null)) && (keyWords == null) && ((logType == "") || (logType == null))
				&& ((affectedUserId == "") || (affectedUserId == null)))) {
			strBufferSelect.append(" WHERE ");
			if (dateFrom != null){
				if (!dateFrom.equals("")) {			
					strBufferSelect.append("DATED >= '" + dateFrom + "'");
					boolAnd = true;
				}
			}
			if (dateTo != null) {
				if (!dateTo.equals("")) {
					if (boolAnd)
						strBufferSelect.append(" AND ");
				strBufferSelect.append("DATED <= '" + dateTo + "'");
				boolAnd = true;
				}
			}
			if (logLevel != null) {
				if (!logLevel.equals("")) {
					if (boolAnd)
						strBufferSelect.append(" AND ");
				strBufferSelect.append("LEVEL='" + logLevel + "'");
				boolAnd = true;
				}
			}
			if (requesterType != null) {
				if (!requesterType.equals("")) {
					if (boolAnd)
						strBufferSelect.append(" AND ");
				strBufferSelect.append("REQUESTERTYPE='" + requesterType + "'");
				boolAnd = true;
				}
			}
			if (requesterId != null) {
				if (!requesterId.equals("")) {
					if (boolAnd)
						strBufferSelect.append(" AND ");
				strBufferSelect.append("REQUESTERID='" + requesterId + "'");
				boolAnd = true;
				}
			}
			if (logPriority != null) {				
				if (!logPriority.equals("")) {
					if (boolAnd)
						strBufferSelect.append(" AND ");
				strBufferSelect.append("LOGPRIORITY='" + logPriority + "'");
				boolAnd = true;
				}
			}
			if (title != null) { 
				if (!title.equals("")) {
					if (boolAnd)
						strBufferSelect.append(" AND ");
				strBufferSelect.append("TITLE LIKE '%" + title + "%'");
				boolAnd = true;
				}
			}
			if (keyWords != null) {				
				if (keyWords.length() > 0) {
					Gson gson = new Gson();
					TypeToken<ArrayList<String>> token = new TypeToken<ArrayList<String>>() {
					};
					arrayListKeyWords = gson.fromJson(keyWords, token.getType());
					ListIterator<String> listIterator = arrayListKeyWords.listIterator();
					if (boolAnd)
						strBufferSelect.append(" AND ");
					while (listIterator.hasNext()) {
						keyValue = listIterator.next();
						if (boolOr)
							strBufferSelect.append(" || ");
						strBufferSelect.append("KEYWORDS LIKE '%" + keyValue + "%'");
						boolOr = true;
					}
				}
			}
			if (logType != null) {
				if (!logType.equals("")) {
					if (boolAnd)
						strBufferSelect.append(" AND ");
					strBufferSelect.append("LOGTYPE='" + logType + "'");
					boolAnd = true;
				}
			}
			if (affectedUserId != null) {
				if (!affectedUserId.equals("")) {
					if (boolAnd)
						strBufferSelect.append(" AND ");
					strBufferSelect.append("AFFECTEDUSERID='" + affectedUserId + "'");
					boolAnd = true;
				}
			}
			if (ospId != null) {
				if (!ospId.equals("")) {
					if (boolAnd)
						strBufferSelect.append(" AND ");
					strBufferSelect.append("OSP='" + ospId + "'");
					boolAnd = true;
				}
			}
			strSelect = strBufferSelect.toString();
		}

		return strSelect;
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return props;
	}

}