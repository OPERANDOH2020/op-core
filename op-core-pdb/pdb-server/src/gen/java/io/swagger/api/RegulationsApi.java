/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2016
//
// Copyright in this software belongs to University of Southampton
// IT Innovation Centre of Gamma House, Enterprise Road,
// Chilworth Science Park, Southampton, SO16 7NS, UK.
//
// This software may not be used, sold, licensed, transferred, copied
// or reproduced in whole or in part in any manner or form or in or
// on any media by any person other than in accordance with the terms
// of the Licence Agreement supplied with the software, or otherwise
// without the prior written consent of the copyright owners.
//
// This software is distributed WITHOUT ANY WARRANTY, without even the
// implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
// PURPOSE, except where stated in the Licence Agreement supplied with
// the software.
//
//      Created By :            Panos Melas
//      Created Date :          2016-10-28
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.RegulationsApiService;
import eu.operando.core.pdb.api.factories.RegulationsApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import eu.operando.core.pdb.common.model.PrivacyRegulation;
import eu.operando.core.pdb.common.model.PrivacyRegulationInput;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;

@Path("/regulations")


@io.swagger.annotations.Api(description = "the regulations API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-20T12:05:17.950Z")
public class RegulationsApi  {
   private final RegulationsApiService delegate = RegulationsApiServiceFactory.getRegulationsApi();

    @GET
    //@Path("/")
    
    
    @io.swagger.annotations.ApiOperation(value = "Perform a search query across the collection of regulation.", notes = "The query contains a json object (names, values) and the request returns  the list of documents (regulations) where the filter matches i.e. the  document contains fields with those values. ", response = PrivacyRegulation.class, responseContainer = "List", tags={ "Legislation","GET", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "The list of regulations documents that match the query are returned in full.", response = PrivacyRegulation.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 405, message = "Error in request. There is an invalid input in the query field.", response = PrivacyRegulation.class, responseContainer = "List") })
    public Response regulationsGet(@ApiParam(value = "The query filter to be matched - ?filter={json description}",required=true) @QueryParam("filter") String filter
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.regulationsGet(filter,securityContext,headers);
    }
    @POST
    //@Path("/")
    
    
    @io.swagger.annotations.ApiOperation(value = "Create a new legislation entry in the database.", notes = "Called by the policy computation component when a new regulation is added to OPERANDO. ", response = PrivacyRegulation.class, tags={ "Legislation","POST", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "The document (PrivacyRegulation) was successfully created in the database and the location field in the HTTP header contains the full URL of the resource. The unique ID reg_id is stored in the body entity.", response = PrivacyRegulation.class) })
    public Response regulationsPost(@ApiParam(value = "The first instance of this regulation document" ,required=true) PrivacyRegulationInput regulation
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.regulationsPost(regulation,securityContext,headers);
    }
    @DELETE
    @Path("/{reg-id}/")
    
    
    @io.swagger.annotations.ApiOperation(value = "Remove the PrivacyRegulation entry in the database.", notes = "Called when by the policy computation component when the regulator api requests that the regulation be deleted. ", response = void.class, tags={ "Legislation","DELETE", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "The document (PrivacyRegulation) was successfully deleted from the database.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No document exists to be deleted.", response = void.class) })
    public Response regulationsRegIdDelete(@ApiParam(value = "The identifier number of a regulation",required=true) @PathParam("reg-id") String regId
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.regulationsRegIdDelete(regId,securityContext,headers);
    }
    @GET
    @Path("/{reg-id}/")
    
    
    @io.swagger.annotations.ApiOperation(value = "Read a given legislation with its ID.", notes = "Get a specific legislation document via the id. This will return the  full legislation document in json format. ", response = PrivacyRegulation.class, tags={ "Legislation","GET", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "The regulation document requested to be read is returned in full", response = PrivacyRegulation.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error - the regulation does not exist.", response = PrivacyRegulation.class) })
    public Response regulationsRegIdGet(@ApiParam(value = "The identifier number of a regulation",required=true) @PathParam("reg-id") String regId
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.regulationsRegIdGet(regId,securityContext,headers);
    }
    @PUT
    @Path("/{reg-id}/")
    
    
    @io.swagger.annotations.ApiOperation(value = "Update PrivacyRegulation entry in the database.", notes = "Called when by the policy computation component when the regulator api updates a regulation. Their new PrivacyRegulation document is stored in the policy DB. ", response = void.class, tags={ "Legislation","PUT", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "The document (PrivacyRegulation) was successfully updated in the database.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No document exists to be updated.", response = void.class) })
    public Response regulationsRegIdPut(@ApiParam(value = "The identifier number of a regulation",required=true) @PathParam("reg-id") String regId
,@ApiParam(value = "The changed instance of this PrivacyRegulation" ,required=true) PrivacyRegulationInput regulation
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.regulationsRegIdPut(regId,regulation,securityContext,headers);
    }
}
