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

import eu.operando.core.ose.api.factories.OspsApiServiceFactory;
import eu.operando.core.pdb.common.model.AccessReason;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;

import io.swagger.annotations.ApiParam;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/osps")

@io.swagger.annotations.Api(description = "The set of OSP methods to handle changes made by an OSP to its privacy policy")

public class OspsApi  {
   private final OspsApiService delegate = OspsApiServiceFactory.getOspsApi();


    @PUT
    @Path("/{osp-id}/reason/")

    @io.swagger.annotations.ApiOperation(value = "", notes = "Notify the OSE of a change in policy text\n", response = void.class, tags={ "Privacy Policy",  })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 204, message = "Successful response, The privacy text update analysis has begun.", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 409, message = "Error occured. The resource already exists, so a new resource cannot be created.", response = void.class) })
    public Response ospsOspIdPrivacytextPut(
        @ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId,
        @ApiParam(value = "The set of individual policies that have now compose the OSP's new privacy policy. This is the complete OSP list of the policies to be compared with the existing stored policy for this OSP." ,required=true) AccessReason ospPolicy,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ospsOspIdPrivacytextPut(ospId,ospPolicy,securityContext);
    }
    @PUT
    @Path("/{osp-id}/")

    @io.swagger.annotations.ApiOperation(value = "", notes = "Called when a change in an OSP's privacy policy detected by OPERANDO (or a new OSP is registered). OSE computes whether the OSP policy complies with regulations; complies with UPP. It updates UPPs where appropriate and notifies users and OSP if there are issues with the updated privacy policy.\n  \nPre-condition -- The OSP must be registered with OPERANDO and it must have an existing policy stored in the policy DB.\n  \n", response = void.class, tags={ "Privacy Policy",  })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response. The privacy policy has been received and being processed. Information will be sent via other operation sequences.", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error response. The OSP does not exist. It has not been registered with OPERANDO.", response = void.class) })
    public Response ospsOspIdPut(
        @ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId,
        @ApiParam(value = "The set of individual policies that have now compose the OSP's new privacy policy. This is the complete OSP list of the policies to be compared with the existing stored policy for this OSP." ,required=true) OSPPrivacyPolicy ospPolicy,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ospsOspIdPut(ospId,ospPolicy,securityContext);
    }

    @GET
    @Path("/{osp-id}/audit/")

    @io.swagger.annotations.ApiOperation(value = "", notes = "Audit an OSP's behaviour based on logs\n", response = String.class, tags={ "Privacy Policy" })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response, The audit is returned.", response = String.class),
        @io.swagger.annotations.ApiResponse(code = 409, message = "Error occured. The resource already exists, so a new resource cannot be created.", response = String.class) })
    public Response ospsOspIdAuditGet(
        @ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId,
        @QueryParam("start") String start, @QueryParam("end") String end,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ospsOspIdAuditGet(ospId, start, end,securityContext);
    }
}
