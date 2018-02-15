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
package eu.operando.core.ldb.server.api.impl;

import java.net.URL;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.PropertyConfigurator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import eu.operando.core.ldb.server.api.ApiResponseMessage;
import eu.operando.core.ldb.server.api.LogApiService;
import eu.operando.core.ldb.server.api.NotFoundException;
import eu.operando.core.ldb.server.model.LogRequest;
import eu.operando.core.ldb.server.model.LogRequestExt.LogLevelEnum;
import eu.operando.core.ldb.server.model.LogRequestExt;
import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.UserCredential;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-16T12:28:19.935Z")
public class LogApiServiceImpl extends LogApiService {

	static Logger log = Logger.getLogger(LogApiService.class.getName());

	/**
	 * 
	 */
	public LogApiServiceImpl() {
		super();
		// setup aapi client
		/*
		 * ApiClient aapiDefaultClient= new ApiClient();
		 * aapiDefaultClient.setBasePath(aapiBasePath); this.aapiClient = new
		 * DefaultApi(aapiDefaultClient); String logdbST =
		 * getServiceTicket(ldbLoginName, ldbLoginPassword, logdbSId);
		 */
		// GEV commented due to some problems appeared on 2017-03-20
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.operando.core.ldb.server.api.LogApiService#lodDB(eu.operando.core.ldb.
	 * server.model.LogRequest, javax.ws.rs.core.SecurityContext) This method
	 * inserts a new log record in the log database by using log4j
	 */
	@Override
	public Response log(LogRequestExt request, SecurityContext securityContext) throws NotFoundException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		// GBE I comment this because in tomcat the file is not there, what I do
		// instead is to put a copy of the file with log4j.properties
		URL configResource = classLoader.getResource("config/log4jMySql.properties");
		if (configResource==null){
			return Response.status(500).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Failed to read log4j properties")).build();									
		}
		
		try{
			PropertyConfigurator.configure(configResource);
		} catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Failed to read log4j properties")).build();						
		}


		// GBE end
		
		Object userId=request.getUserId();
		Object requesterType=request.getRequesterType();
		Object requesterId=request.getRequesterId();
		Object logPriority=request.getLogPriority();
		Object logLevel=request.getLogLevel();
		Object keywords=request.getKeywords();
		Object title=request.getTitle();
		Object logType=request.getLogType();
		Object affectedUserId=request.getAffectedUserId();
		Object description=request.getDescription();
		Object osp=request.getOsp();
		Object requestedFields=request.getRequestedFields();
		Object grantedFields=request.getGrantedFields();

		try{
			checkProperty(userId, "UserId", true, true);
			checkProperty(requesterType, "RequesterType", true, true);
			checkProperty(requesterId, "RequesterId", true, true);
			checkProperty(logPriority, "LogPriority", true, true);
			checkProperty(logLevel, "LogLevel", true, true);
			checkProperty(keywords, "Keywords", true, true);
			checkProperty(title, "Title", true, true);
			checkProperty(logType, "LogType", true, true);
			checkProperty(affectedUserId, "AffectedUserId", false, false);
			checkProperty(description, "Description", true, true);
			checkProperty(osp, "Osp", false, false);
			checkProperty(requestedFields, "RequestedFields", false, false);
			checkProperty(grantedFields, "GrantedFields", false, false);
		} catch(Exception e)
		{
			return Response.status(400)
					.entity(new ApiResponseMessage(ApiResponseMessage.ERROR, e.getMessage()))
					.build();			
		}
		
		if ((affectedUserId == null) || (affectedUserId == "")) affectedUserId="";
		if ((osp == null) || (osp == "")) osp="";
		if (requestedFields == null) requestedFields="";
		if (grantedFields == null) grantedFields="";
		
		String userIdString;
		String requesterTypeString;
		String requesterIdString;
		String logPriorityString;
		String logLevelString;
		String keywordsString;
		String titleString;
		String logTypeString;
		String affectedUserIdString;
		String descriptionString;
		String ospString;
		String requestedFieldsString;
		String grantedFieldsString;
		try {
			userIdString = toString(userId);
			requesterTypeString = toString(requesterType);
			requesterIdString = toString(requesterId);
			logPriorityString = toString(logPriority);
			logLevelString = toString(logLevel);
			keywordsString = toString(keywords);
			titleString = toString(title);
			logTypeString = toString(logType);
			affectedUserIdString = toString(affectedUserId);
			descriptionString = toString(description);
			ospString = toString(osp);
			requestedFieldsString = toString(requestedFields);
			grantedFieldsString = toString(grantedFields);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return Response.status(400)
					.entity(new ApiResponseMessage(ApiResponseMessage.ERROR, e.getMessage()))
					.build();			
		}
		
		
		/* take into account the database structure to truncate data
		  `USER_ID` varchar(20) DEFAULT NULL,
		  `DATED` varchar(50) DEFAULT NULL,
		  `LOGGER` varchar(100) DEFAULT NULL,
		  `LEVEL` varchar(10) DEFAULT NULL,
		  `REQUESTERTYPE` varchar(20) DEFAULT NULL,
		  `REQUESTERID` varchar(50) DEFAULT NULL,
		  `LOGPRIORITY` varchar(10) DEFAULT NULL,
		  `KEYWORDS` varchar(50) DEFAULT NULL,
		  `TITLE` varchar(500) DEFAULT NULL,
		  `MESSAGE` varchar(1000) DEFAULT NULL,
		  `LOGTYPE` varchar(50) DEFAULT NULL,
		  `AFFECTEDUSERID` varchar(50) DEFAULT NULL,
		  `OSP` varchar(50) DEFAULT NULL,
		  `REQUESTEDFIELDS` varchar(500) DEFAULT NULL,
		  `GRANTEDFIELDS` varchar(500) DEFAULT NULL		
		 */
		userIdString=trim(userIdString, 20);
		requesterTypeString=trim(requesterTypeString, 20);
		requesterIdString=trim(requesterIdString, 100);
		logPriorityString=trim(logPriorityString, 20);
		logLevelString=trim(logLevelString, 20);
		keywordsString=trim(keywordsString, 50);
		titleString=trim(titleString, 500);
		logTypeString=trim(logTypeString, 50);
		affectedUserIdString=trim(affectedUserIdString, 50);
		descriptionString=trim(descriptionString, 1000);
		ospString=trim(ospString, 50);
		requestedFieldsString=trim(requestedFieldsString, 500);
		grantedFieldsString=trim(grantedFieldsString, 500);
		
		MDC.put("userName", userIdString);
		MDC.put("requesterType", requesterTypeString);
		MDC.put("requesterId", requesterIdString);
		MDC.put("logPriority", logPriorityString);
		MDC.put("logLevel", logLevelString);
		MDC.put("keywords", keywordsString);
		MDC.put("title", titleString);
		MDC.put("logType", logTypeString);
		MDC.put("affectedUserId", affectedUserIdString);
		MDC.put("osp", ospString);
		MDC.put("requestedFields", requestedFieldsString);
		MDC.put("grantedFields", grantedFieldsString);
		
		try{
			switch ((LogLevelEnum)logLevel){
			case INFO: 
				log.info(description);
				break;
			case WARN: 
				log.warn(description);
				break;
			case ERROR: 
				log.error(description);
				break;
			case FATAL: 
				log.fatal(description);
				break;
			};
		}
		catch (Exception e){
			e.printStackTrace();
			return Response.status(500)
					.entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Failed to save log in log datastore"))
					.build();						
		}
				
		return Response.ok()
				.entity(new ApiResponseMessage(ApiResponseMessage.OK, "The log message has been registered!")).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * eu.operando.core.ldb.server.api.LogApiService#lodDB(eu.operando.core.ldb.
	 * server.model.LogRequest, javax.ws.rs.core.SecurityContext) This method
	 * inserts a new log record in the log database by using log4j
	 */
	@Override
	//@Secured No efect here 
	public Response logTicket(LogRequestExt request, SecurityContext securityContext) throws NotFoundException {
			return log( request,  securityContext);
	}

	private String toString (Object object) throws JsonProcessingException  {
		if (object instanceof List) {
			String jsonString="";
			ObjectMapper mapper = new ObjectMapper();
			jsonString = mapper.writeValueAsString(object);
			return jsonString;			
		} else{
			return object.toString();
		}
	}
	
	private String trim (String string, int maxlength){
		if (string.length()>maxlength) string=string.substring(0, maxlength - 1);
		return string;
	}
	
	private void checkProperty(Object value, String propertyName, boolean required, boolean notEmpty)
			throws RequiredNotSet, NotEmptyNotValue {
		if (required || notEmpty) {
			if (value == null) {
				throw new RequiredNotSet(propertyName + " parameter cannot be null");
			}
			if (value instanceof String && notEmpty && value == "") {
				throw new NotEmptyNotValue(propertyName + " parameter cannot be empty");
			}
		}
	}

}
