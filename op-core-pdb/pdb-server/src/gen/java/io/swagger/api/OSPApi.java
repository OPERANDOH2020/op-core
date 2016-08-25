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
//      Created Date :          2016-04-28
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

package io.swagger.api;

import io.swagger.api.factories.OSPApiServiceFactory;

import io.swagger.annotations.ApiParam;


import io.swagger.model.OSPPrivacyPolicy;
import io.swagger.model.OSPPrivacyPolicyInput;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/OSP")


@io.swagger.annotations.Api(description = "the OSP API")

public class OSPApi  {
   private final OSPApiService delegate = OSPApiServiceFactory.getOSPApi();

    @GET
    @Path("/")
    
    
    @io.swagger.annotations.ApiOperation(value = "Perform a search query across the collection of OSP behaviour.", notes = "The query contains a json object (names, values) and the request returns the list of documents (regulations) where the filter matches i.e. the document contains fields with those values.\n", response = OSPPrivacyPolicy.class, responseContainer = "List", tags={ "OSP", "GET",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "The list of OSP documents that match the query are returned in full.", response = OSPPrivacyPolicy.class, responseContainer = "List"),
        @io.swagger.annotations.ApiResponse(code = 405, message = "Error in request. There is an invalid input in the query field.", response = OSPPrivacyPolicy.class, responseContainer = "List") })
    public Response oSPGet(
        @ApiParam(value = "The query filter to be matched - ?filter={json description}",required=true) @QueryParam("filter") String filter,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.oSPGet(filter,securityContext);
    }
    @DELETE
    @Path("/{osp-id}/")
    
    
    @io.swagger.annotations.ApiOperation(value = "Remove the OSPRequest entry in the database.", notes = "Called when by the policy computation component when the regulator api\nrequests that the regulation be deleted.\n", response = void.class, tags={ "OSP", "DELETE",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "The document (OSPBehaviour) was successfully deleted from the database.", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No document exists to be deleted.", response = void.class) })
    public Response oSPOspIdDelete(
        @ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.oSPOspIdDelete(ospId,securityContext);
    }
    @GET
    @Path("/{osp-id}/")
    
    
    @io.swagger.annotations.ApiOperation(value = "Read a given OSP behaviour policy.", notes = "Get a specific OSP document via the id. This will return the full OSP document in json format.\n", response = OSPPrivacyPolicy.class, tags={ "OSP", "GET",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "The OSP document requested to be read is returned in full", response = OSPPrivacyPolicy.class),
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error - the regulation does not exist.", response = OSPPrivacyPolicy.class) })
    public Response oSPOspIdGet(
        @ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.oSPOspIdGet(ospId,securityContext);
    }
    @PUT
    @Path("/{osp-id}/")
    
    
    @io.swagger.annotations.ApiOperation(value = "Update OSPBehaviour entry in the database.", notes = "Called when by the policy computation component when the regulator api\nupdates a regulation. Their new OSPRequest document is stored in the\npolicy DB.\n", response = void.class, tags={ "OSP", "PUT",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "The document (OSPBehaviour) was successfully updated in the database.", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No document exists to be updated.", response = void.class) })
    public Response oSPOspIdPut(
        @ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId,
        @ApiParam(value = "The changed instance of this OSPRequest" ,required=true) OSPPrivacyPolicyInput ospPolicy,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.oSPOspIdPut(ospId,ospPolicy,securityContext);
    }
    @POST
    @Path("/")
    
    
    @io.swagger.annotations.ApiOperation(value = "Create a new OSP entry in the database.", notes = "Called by the policy computation component when a new regulation is added\nto OPERANDO.\n", response = void.class, tags={ "OSP", "POST" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "The document (OSPBehaviour) was successfully created in the database.", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 405, message = "Error. The document (OSPBehaviour) at this id has previously been created in the database.", response = void.class) })
    public Response oSPPost(
        @ApiParam(value = "The first instance of this OSP document" ,required=true) OSPPrivacyPolicyInput ospPolicy,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.oSPPost(ospPolicy,securityContext);
    }
}
