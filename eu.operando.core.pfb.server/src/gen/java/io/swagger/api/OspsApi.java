package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.OspsApiService;
import io.swagger.api.factories.OspsApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.Error;
import io.swagger.model.InlineResponse2004;
import io.swagger.model.OSP;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/osps")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the osps API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-03-31T13:37:20.076Z")
public class OspsApi  {
   private final OspsApiService delegate = OspsApiServiceFactory.getOspsApi();

    @GET
    @Path("/{osp_id}/deals")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "List of all users and their active deals for the OSP.", notes = "Request from an OSP to get a list of all users and their active deals for specific OSP (could be also for report) .", response = InlineResponse2004.class, tags={ "Deals", "OSPs",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation (list of offers' ids associated with users' ids).", response = InlineResponse2004.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse2004.class) })

    public Response getAcceptedDealsByOSP(
@ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp_id") String ospId,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getAcceptedDealsByOSP(ospId,securityContext);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Registers a new OSP.", notes = "Request from Administration Console to register a new Online Service Provider.", response = String.class, tags={ "OSPs" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation.", response = String.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = String.class) })

    public Response registerOSP(
@ApiParam(value = "The OSP data in JSON format." ,required=true) OSP osp,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.registerOSP(osp,securityContext);
    }
}
