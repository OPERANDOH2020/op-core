package io.swagger.api.impl;

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

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-16T12:28:19.935Z")
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
