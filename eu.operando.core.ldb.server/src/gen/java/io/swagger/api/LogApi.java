package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.LogApiService;
import io.swagger.api.factories.LogApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.LogRequest;
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
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-07T07:27:45.925Z")
public class LogApi  {
   private final LogApiService delegate = LogApiServiceFactory.getLogApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Inserts received data to the database.", notes = "Inserts received data to the database by using Log4j.", response = String.class, tags={ "Log" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = String.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = String.class) })
    public Response lodDB(
        @ApiParam(value = "The request data in JSON format to be inserted in the database by using Log4j" ,required=true) LogRequest request,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.lodDB(request,securityContext);
    }
}
