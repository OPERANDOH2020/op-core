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
package io.swagger.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.annotations.ApiParam;
import io.swagger.api.factories.LogApiServiceFactory;
import io.swagger.model.InlineResponse200;

@Path("/log")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the log API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-09T07:21:21.621Z")
public class LogApi  {
   private final LogApiService delegate = LogApiServiceFactory.getLogApi();

    @GET
    @Path("/search")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Search logs in database", notes = "Search logs in database by specifying a filter.", response = InlineResponse200.class, tags={ "LogSearch" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = InlineResponse200.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse200.class) })
    public Response getLogs(@ApiParam(value = "Date from which wanted to be recovered the logs.") @QueryParam("dateFrom") String dateFrom
,@ApiParam(value = "Date to which wanted to be recovered the logs.") @QueryParam("dateTo") String dateTo
,@ApiParam(value = "Log level wanted to be recovered.") @QueryParam("logLevel") String logLevel
,@ApiParam(value = "Type of the requester that originated the log entry.") @QueryParam("requesterType") String requesterType
,@ApiParam(value = "Id of the requester that originated the log entry.") @QueryParam("requesterId") String requesterId
,@ApiParam(value = "Priority of the log messages to be recovered.") @QueryParam("logPriority") String logPriority
,@ApiParam(value = "Title of the log messages to be recovered.") @QueryParam("title") String title
,@ApiParam(value = "Keywords to perform the search.") @QueryParam("keyWords") String keyWords
,@ApiParam(value = "Type of the data logged.") @QueryParam("logType") String logType
,@ApiParam(value = "Id of the affected user.") @QueryParam("affectedUserId") String affectedUserId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getLogs(dateFrom,dateTo,logLevel,requesterType,requesterId,logPriority,title,keyWords,logType,affectedUserId,securityContext);
    }
}
