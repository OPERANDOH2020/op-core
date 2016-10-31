package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.RegulationsApiService;
import io.swagger.api.factories.RegulationsApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.PrivacyRegulation;
import io.swagger.model.PrivacyRegulationInput;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/regulations")


@io.swagger.annotations.Api(description = "the regulations API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-10-28T08:28:40.436Z")
public class RegulationsApi  {
   private final RegulationsApiService delegate = RegulationsApiServiceFactory.getRegulationsApi();

    @GET
    @Path("/")
    
    
    @io.swagger.annotations.ApiOperation(value = "Perform a search query across the collection of regulation.", notes = "The query contains a json object (names, values) and the request returns  the list of documents (regulations) where the filter matches i.e. the  document contains fields with those values. ", response = PrivacyRegulation.class, responseContainer = "List", tags={ "Legislation","GET", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "The list of regulations documents that match the query are returned in full.", response = PrivacyRegulation.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Error in request. There is an invalid input in the query field.", response = PrivacyRegulation.class, responseContainer = "List") })
    public Response regulationsGet(@ApiParam(value = "The query filter to be matched - ?filter={json description}",required=true) @QueryParam("filter") String filter
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.regulationsGet(filter,securityContext);
    }
    @POST
    @Path("/")
    
    
    @io.swagger.annotations.ApiOperation(value = "Create a new legislation entry in the database.", notes = "Called by the policy computation component when a new regulation is added to OPERANDO. ", response = PrivacyRegulation.class, tags={ "Legislation","POST", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "The document (PrivacyRegulation) was successfully created in the database and the location field in the HTTP header contains the full URL of the resource. The unique ID reg_id is stored in the body entity.", response = PrivacyRegulation.class) })
    public Response regulationsPost(@ApiParam(value = "The first instance of this regulation document" ,required=true) PrivacyRegulationInput regulation
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.regulationsPost(regulation,securityContext);
    }
    @DELETE
    @Path("/{reg-id}/")
    
    
    @io.swagger.annotations.ApiOperation(value = "Remove the PrivacyRegulation entry in the database.", notes = "Called when by the policy computation component when the regulator api requests that the regulation be deleted. ", response = void.class, tags={ "Legislation","DELETE", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "The document (PrivacyRegulation) was successfully deleted from the database.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No document exists to be deleted.", response = void.class) })
    public Response regulationsRegIdDelete(@ApiParam(value = "The identifier number of a regulation",required=true) @PathParam("reg-id") String regId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.regulationsRegIdDelete(regId,securityContext);
    }
    @GET
    @Path("/{reg-id}/")
    
    
    @io.swagger.annotations.ApiOperation(value = "Read a given legislation with its ID.", notes = "Get a specific legislation document via the id. This will return the  full legislation document in json format. ", response = PrivacyRegulation.class, tags={ "Legislation","GET", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "The regulation document requested to be read is returned in full", response = PrivacyRegulation.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error - the regulation does not exist.", response = PrivacyRegulation.class) })
    public Response regulationsRegIdGet(@ApiParam(value = "The identifier number of a regulation",required=true) @PathParam("reg-id") String regId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.regulationsRegIdGet(regId,securityContext);
    }
    @PUT
    @Path("/{reg-id}/")
    
    
    @io.swagger.annotations.ApiOperation(value = "Update PrivacyRegulation entry in the database.", notes = "Called when by the policy computation component when the regulator api updates a regulation. Their new PrivacyRegulation document is stored in the policy DB. ", response = void.class, tags={ "Legislation","PUT", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "The document (PrivacyRegulation) was successfully updated in the database.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No document exists to be updated.", response = void.class) })
    public Response regulationsRegIdPut(@ApiParam(value = "The identifier number of a regulation",required=true) @PathParam("reg-id") String regId
,@ApiParam(value = "The changed instance of this PrivacyRegulation" ,required=true) PrivacyRegulationInput regulation
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.regulationsRegIdPut(regId,regulation,securityContext);
    }
}
