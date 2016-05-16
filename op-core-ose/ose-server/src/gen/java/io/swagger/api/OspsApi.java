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

import io.swagger.api.factories.OspsApiServiceFactory;

import io.swagger.annotations.ApiParam;


import io.swagger.model.PrivacySetting;
import io.swagger.model.OSPPrivacyPolicy;
import io.swagger.model.OSPDataRequest;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/osps")


@io.swagger.annotations.Api(description = "the osps API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-25T15:37:02.222Z")
public class OspsApi  {
   private final OspsApiService delegate = OspsApiServiceFactory.getOspsApi();

    @GET
    @Path("/{osp-id}/privacy_settings/")
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "Simple retrieval of an OPERANDO registered OSP's privacy settings.\nThis method is called by the watchdog component when it requests the settings last applied by the OSE component.\n\nPre condition -- An OPERANDO user must have registered with the OPERANDO platform and subscribed to the OSP service in question.\n\nPre condition --The user's UPP must be stored in the Policy DB component and contain the privacy settings for the OSP service in question.\n\nWhen the query includes a user id; that user's settings are returned. For a request with no user id as a query parameter, the operation returns the general set of settings covered by this OSP.\n", response = PrivacySetting.class, responseContainer = "List", tags={ "Privacy Settings",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response. The privacy settings information for this user at the given OSP is returned.", response = PrivacySetting.class, responseContainer = "List"),
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error response. The settings resource does not exist. Or the OSP does not exist. Or the user is not subscribed to the OSP in OPERANDO.", response = PrivacySetting.class, responseContainer = "List") })
    public Response ospsOspIdPrivacySettingsGet(
        @ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId,
        @ApiParam(value = "The user identifier number",required=true) @QueryParam("user-id") String userId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ospsOspIdPrivacySettingsGet(ospId,userId,securityContext);
    }
    @PUT
    @Path("/{osp-id}/privacy_settings/")
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "Called when a change in privacy settings is detected at a specific OSP. The OSE evaluates the impact of the changed settings and computes the required new settings and ensures that they are enforced at the OSP and the new settings stored in the policy DB.\n", response = void.class, tags={ "Privacy Settings",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response. The privacy settings have been agreed and applied via the OSE and the browser extension software. Note, the response here only needs to indicate that the method worked not whether the settings have been applied in practice.", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error response. The user does not exist. Or the OSP does not exist. Or the user is not subscribed to the OSP in OPERANDO.", response = void.class) })
    public Response ospsOspIdPrivacySettingsPut(
        @ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId,
        @ApiParam(value = "The user identifier number",required=true) @QueryParam("user-id") String userId,
        @ApiParam(value = "The set of settings that have now changed. This is the complete OSP settings list to be compared with the existing stored settings for this OSP for the user." ,required=true) List<PrivacySetting> ospSettings,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ospsOspIdPrivacySettingsPut(ospId,userId,ospSettings,securityContext);
    }
    @PUT
    @Path("/{osp-id}/privacytext/")
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "Notify the OSE of a change in policy text\n", response = void.class, tags={ "Privacy Policy",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Successful response, The privacy text update analysis has begun.", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 409, message = "Error occured. The resource already exists, so a new resource cannot be created.", response = void.class) })
    public Response ospsOspIdPrivacytextPut(
        @ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId,
        @ApiParam(value = "The complete privacy policy text of the OSP.",required=true) @QueryParam("osp_privacy_text") String ospPrivacyText,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ospsOspIdPrivacytextPut(ospId,ospPrivacyText,securityContext);
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
    @PUT
    @Path("/{osp-id}/workflows/")
    
    
    @io.swagger.annotations.ApiOperation(value = "", notes = "Notify the OSE of a change in an OSP's workflow\n", response = void.class, tags={ "Privacy Policy" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 204, message = "Successful response, The workflow update analysis has begun.", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 409, message = "Error occured. The resource already exists, so a new resource cannot be created.", response = void.class) })
    public Response ospsOspIdWorkflowsPut(
        @ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId,
        @ApiParam(value = "The workflow changes of the OSP." ,required=true) OSPDataRequest ospWorkflow,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ospsOspIdWorkflowsPut(ospId,ospWorkflow,securityContext);
    }
}
