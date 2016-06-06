package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.LogApiService;
import io.swagger.api.factories.LogApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.InlineResponse200;
import io.swagger.model.Error;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/log")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the log API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-06T10:10:57.937Z")
public class LogApi  {
   private final LogApiService delegate = LogApiServiceFactory.getLogApi();

    @GET
    @Path("/search")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Search logs in database", notes = "Search logs in database by specifying a filter.", response = InlineResponse200.class, tags={ "LogSearch" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = InlineResponse200.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse200.class) })
    public Response getLogs(
        @ApiParam(value = "Date from which wanted to be recovered the logs.") @QueryParam("dateFrom") String dateFrom,
        @ApiParam(value = "Date to which wanted to be recovered the logs.") @QueryParam("dateTo") String dateTo,
        @ApiParam(value = "Log level wanted to be recovered.") @QueryParam("logLevel") String logLevel,
        @ApiParam(value = "Type of the requester that originated the log entry.") @QueryParam("requesterType") String requesterType,
        @ApiParam(value = "Id of the requester that originated the log entry.") @QueryParam("requesterId") String requesterId,
        @ApiParam(value = "Priority of the log messages to be recovered.") @QueryParam("logPriority") String logPriority,
        @ApiParam(value = "Title of the log messages to be recovered.") @QueryParam("title") String title,
        @ApiParam(value = "Keywords to perform the search.") @QueryParam("keyWords") List<String> keyWords,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getLogs(dateFrom,dateTo,logLevel,requesterType,requesterId,logPriority,title,keyWords,securityContext);
    }
}
