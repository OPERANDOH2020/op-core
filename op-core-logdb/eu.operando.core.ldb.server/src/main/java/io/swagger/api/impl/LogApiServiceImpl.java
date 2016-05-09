/*******************************************************************************
 *  * Copyright (c) 2016 {TECNALIA}.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the The MIT License (MIT).
 *  * which accompanies this distribution, and is available at
 *  * http://opensource.org/licenses/MIT
 *  *
 *  * Contributors:
 *  *    Gorka Mikel Echevarría {TECNALIA}
 *  * Initially developed in the context of OPERANDO EU project www.operando.eu
 *******************************************************************************/
package io.swagger.api.impl;

import java.util.Properties;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.PropertyConfigurator;

import com.google.gson.Gson;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.LogApiService;
import io.swagger.api.NotFoundException;
import io.swagger.model.LogRequest;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-07T07:27:45.925Z")
public class LogApiServiceImpl extends LogApiService {
    
	static Logger log = Logger.getLogger(LogApiService.class.getName());
	
    /* (non-Javadoc)
     * @see io.swagger.api.LogApiService#lodDB(io.swagger.model.LogRequest, javax.ws.rs.core.SecurityContext)
     * This method inserts a new log record in the log database by using log4j 
     */
    @Override
    public Response lodDB(LogRequest request, SecurityContext securityContext)
    throws NotFoundException {    	    	
    	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    	PropertyConfigurator.configure(classLoader.getResource("config/log4jMySql.properties"));    	
  	  	Gson gson = new Gson();
  	  	MDC.put("username", "username");
  	  	String requestDataInJsonFormat = gson.toJson(request);  	  	
  	  	log.info(requestDataInJsonFormat);
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "The log message has been registered!")).build();
    }
    
}
