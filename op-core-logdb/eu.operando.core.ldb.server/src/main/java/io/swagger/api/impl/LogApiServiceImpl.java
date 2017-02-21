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

import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.LogApiService;
import io.swagger.api.NotFoundException;
import io.swagger.client.api.DefaultApi;
import io.swagger.model.LogRequest;
import io.swagger.model.LogRequestTicket;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-16T12:28:19.935Z")
public class LogApiServiceImpl extends LogApiService {

	static Logger log = Logger.getLogger(LogApiService.class.getName());
	// AAPI
    DefaultApi aapiClient;
	String logdbSId = "/operando/core/ldb";
	String stHeaderName = "service-ticket";
	/*
	 * (non-Javadoc)
	 * 
	 * @see io.swagger.api.LogApiService#lodDB(io.swagger.model.LogRequest,
	 * javax.ws.rs.core.SecurityContext) This method inserts a new log record in
	 * the log database by using log4j
	 */
	@Override
	public Response log(LogRequest request, SecurityContext securityContext) throws NotFoundException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		// GBE I comment this because in tomcat the file is not there, what I do
		// instead is to put a copy of the file with log4j.properties
		// PropertyConfigurator.configure(classLoader.getResource("config/log4jMySql.properties"));
		// GBE end		
		if ((request.getUserId() == null) || (request.getUserId() == "")) {
			return Response.status(400)
					.entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "UserId parameter cannot be empty"))
					.build();
		}
		MDC.put("userName", request.getUserId());
		if ((request.getRequesterType() == null) || (request.getRequesterType().toString() == "")) {
			return Response.status(400)
					.entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "requesterType parameter cannot be empty"))
					.build();
		}
		MDC.put("requesterType", request.getRequesterType());
		if ((request.getRequesterId() == null) || (request.getRequesterId() == "")) {
			return Response.status(400)
					.entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "requesterId parameter cannot be empty"))
					.build();
		}
		MDC.put("requesterId", request.getRequesterId());
		if ((request.getLogPriority() == null) || (request.getLogPriority().toString() == "")) {
			return Response.status(400)
					.entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "logPriority parameter cannot be empty"))
					.build();
		}
		MDC.put("logPriority", request.getLogPriority());
		
		MDC.put("logLevel", request.getLogDataType());		
		MDC.put("keywords", request.getTitle().toString());
		if ((request.getTitle() == null) || (request.getTitle() == "")) {
			return Response.status(400)
					.entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "title parameter cannot be empty"))
					.build();
		}
		MDC.put("title", request.getTitle());
		if ((request.getDescription() == null) || (request.getDescription() == "")) {
			return Response.status(400)
					.entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "description parameter cannot be empty"))
					.build();
		}
		log.info(request.getDescription());
		return Response.ok()
				.entity(new ApiResponseMessage(ApiResponseMessage.OK, "The log message has been registered!")).build();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see io.swagger.api.LogApiService#lodDB(io.swagger.model.LogRequest,
	 * javax.ws.rs.core.SecurityContext) This method inserts a new log record in
	 * the log database by using log4j
	 */
	@Override
	public Response logTicket(LogRequestTicket request, SecurityContext securityContext, HttpHeaders headers) throws NotFoundException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		
		if (!validateHeaderSt(headers)) {
            return Response.status(403).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The service ticket failed to validate.")).build();
        }
		
		// GBE I comment this because in tomcat the file is not there, what I do
		// instead is to put a copy of the file with log4j.properties
		// PropertyConfigurator.configure(classLoader.getResource("config/log4jMySql.properties"));
		// GBE end
		if ((request.getUserId() == null) || (request.getUserId() == "")) {
			return Response.status(400)
					.entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "UserId parameter cannot be empty"))
					.build();
		}
		MDC.put("userName", request.getUserId());
		if ((request.getRequesterType() == null) || (request.getRequesterType().toString() == "")) {
			return Response.status(400)
					.entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "requesterType parameter cannot be empty"))
					.build();
		}
		MDC.put("requesterType", request.getRequesterType());
		if ((request.getRequesterId() == null) || (request.getRequesterId() == "")) {
			return Response.status(400)
					.entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "requesterId parameter cannot be empty"))
					.build();
		}
		MDC.put("requesterId", request.getRequesterId());
		if ((request.getLogPriority() == null) || (request.getLogPriority().toString() == "")) {
			return Response.status(400)
					.entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "logPriority parameter cannot be empty"))
					.build();
		}
		MDC.put("logPriority", request.getLogPriority());
		
		MDC.put("logLevel", request.getLogLevel());		
		MDC.put("keywords", request.getTitle().toString());
		if ((request.getTitle() == null) || (request.getTitle() == "")) {
			return Response.status(400)
					.entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "title parameter cannot be empty"))
					.build();
		}
		MDC.put("title", request.getTitle());
		if ((request.getDescription() == null) || (request.getDescription() == "")) {
			return Response.status(400)
					.entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "description parameter cannot be empty"))
					.build();
		}
		log.info(request.getDescription());
		return Response.ok()
				.entity(new ApiResponseMessage(ApiResponseMessage.OK, "The log message has been registered!")).build();
	}
	
	 private boolean validateHeaderSt(HttpHeaders headers) {
	        if (headers != null) {
	            List<String> stHeader = headers.getRequestHeader(stHeaderName);
	            if (stHeader != null) {
	                String st = stHeader.get(0);
	                try {
	                    aapiClient.aapiTicketsStValidateGet(st, logdbSId);
	                    return true;
	                } catch (io.swagger.client.ApiException ex) {
	                	log.error("Service Ticket validation failed: {0}");	                            
	                    return false;
	                }
	            }
	        }
	        return false;
	    }	

}
