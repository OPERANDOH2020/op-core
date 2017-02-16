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

import io.swagger.api.OSPApiService;
import eu.operando.core.pdb.api.factories.OSPApiServiceFactory;

import io.swagger.annotations.ApiParam;

import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;
import eu.operando.core.pdb.common.model.OSPReasonPolicy;
import eu.operando.core.pdb.common.model.AccessReason;
import eu.operando.core.pdb.common.model.OSPReasonPolicyInput;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicyInput;

import io.swagger.api.NotFoundException;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.*;

@Path("/OSP")


@io.swagger.annotations.Api(description = "the OSP API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-19T10:59:55.638Z")
public class OSPApi  {
   private final OSPApiService delegate = OSPApiServiceFactory.getOSPApi();

    @GET
    @Path("/")


    @io.swagger.annotations.ApiOperation(value = "Perform a search query across the collection of OSP behaviour.", notes = "The query contains a json object (names, values) and the request returns the list of documents (regulations) where the filter matches i.e. the document contains fields with those values. ", response = OSPPrivacyPolicy.class, responseContainer = "List", tags={ "OSP","GET", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "The list of OSP documents that match the query are returned in full.", response = OSPPrivacyPolicy.class, responseContainer = "List"),

        @io.swagger.annotations.ApiResponse(code = 405, message = "Error in request. There is an invalid input in the query field.", response = OSPPrivacyPolicy.class, responseContainer = "List") })
    public Response oSPGet(@ApiParam(value = "The query filter to be matched - ?filter={json description}",required=true) @QueryParam("filter") String filter
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.oSPGet(filter,securityContext,headers);
    }
    @DELETE
    @Path("/{osp-id}/")


    @io.swagger.annotations.ApiOperation(value = "Remove the OSPRequest entry in the database.", notes = "Called when by the policy computation component when the regulator api requests that the regulation be deleted. ", response = void.class, tags={ "OSP","DELETE", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 204, message = "The document (OSPBehaviour) was successfully deleted from the database.", response = void.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No document exists to be deleted.", response = void.class) })
    public Response oSPOspIdDelete(@ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.oSPOspIdDelete(ospId,securityContext,headers);
    }
    @GET
    @Path("/{osp-id}/")


    @io.swagger.annotations.ApiOperation(value = "Read a given OSP behaviour policy.", notes = "Get a specific OSP document via the id. This will return the full OSP document in json format. ", response = OSPPrivacyPolicy.class, tags={ "OSP","GET", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "The OSP document requested to be read is returned in full", response = OSPPrivacyPolicy.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error - the osp does not have a policy in the db.", response = OSPPrivacyPolicy.class) })
    public Response oSPOspIdGet(@ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.oSPOspIdGet(ospId,securityContext,headers);
    }
    @GET
    @Path("/{osp-id}/privacy-policy/access-reasons")


    @io.swagger.annotations.ApiOperation(value = "Get the list of access reason policy statements.", notes = "List of policy statements.  ", response = OSPReasonPolicy.class, responseContainer = "List", tags={ "OSP","GET", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "The list of OSP privacy policy statements are returned as a JSON object.", response = OSPReasonPolicy.class, responseContainer = "List"),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error - the OSP does not have a policy stored in the db.", response = OSPReasonPolicy.class, responseContainer = "List") })
    public Response oSPOspIdPrivacyPolicyAccessReasonsGet(@ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.oSPOspIdPrivacyPolicyAccessReasonsGet(ospId,securityContext,headers);
    }
    @POST
    @Path("/{osp-id}/privacy-policy/access-reasons")


    @io.swagger.annotations.ApiOperation(value = "Create a new access reason statement in the privacy policy.", notes = "Called by the UI when OSP updating the policy statements ", response = void.class, tags={ "OSP","POST", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 201, message = "The statements (OSPBehaviour) was successfully created in the list.", response = void.class),

        @io.swagger.annotations.ApiResponse(code = 405, message = "Error. The document (OSPBehaviour) at this id has previously been created in the list.", response = void.class) })
    public Response oSPOspIdPrivacyPolicyAccessReasonsPost(@ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId
,@ApiParam(value = "The first instance of this new statement." ,required=true) AccessReason ospPolicy
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.oSPOspIdPrivacyPolicyAccessReasonsPost(ospId,ospPolicy,securityContext,headers);
    }
    @DELETE
    @Path("/{osp-id}/privacy-policy/access-reasons/{reason-id}")


    @io.swagger.annotations.ApiOperation(value = "Remove the AccessReason entry in the list.", notes = "Remove an identified value. ", response = void.class, tags={ "OSP","DELETE", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 204, message = "The document (OSPBehaviour) was successfully deleted from the database.", response = void.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No document exists to be deleted.", response = void.class) })
    public Response oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete(@ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId
,@ApiParam(value = "The identifier of a statement in a policy, is only unique to the policy.",required=true) @PathParam("reason-id") String reasonId
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete(ospId,reasonId,securityContext,headers);
    }
    @PUT
    @Path("/{osp-id}/privacy-policy/access-reasons/{reason-id}")


    @io.swagger.annotations.ApiOperation(value = "Update an access reason statement in the privacy policy.", notes = "Called by the UI when OSP updating the policy statements ", response = void.class, tags={ "OSP","POST", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 204, message = "The statements (OSPBehaviour) was successfully updated in the list.", response = void.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No statement in list to update, use post.", response = void.class) })
    public Response oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut(@ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId
,@ApiParam(value = "The identifier of a statement in a policy, is only unique to the policy.",required=true) @PathParam("reason-id") String reasonId
,@ApiParam(value = "The updated instance of this OSP policy statement." ,required=true) AccessReason ospPolicy
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut(ospId,reasonId,ospPolicy,securityContext,headers);
    }
    @GET
    @Path("/{osp-id}/privacy-policy/")


    @io.swagger.annotations.ApiOperation(value = "Get the current set of privacy policy statements about the usage of data for stated reasons.", notes = "This is a machine readable version of a G2C privacy policy statement entered using the OSP Admin dashboard; and retrieved by both the OSP & PSP analyst dashboard for display purposes and also by the OSE component for checking regulation compliance.  ", response = OSPReasonPolicy.class, tags={ "OSP","GET", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "The list of OSP privacy policy statements are returned as a JSON object.", response = OSPReasonPolicy.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error - the OSP does not have a policy stored in the db.", response = OSPReasonPolicy.class) })
    public Response oSPOspIdPrivacyPolicyGet(@ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.oSPOspIdPrivacyPolicyGet(ospId,securityContext,headers);
    }
    @PUT
    @Path("/{osp-id}/privacy-policy/")


    @io.swagger.annotations.ApiOperation(value = "Update OSP text policy entry in the database.", notes = "Called when by the watchdog detects a change in the textual policy and wants to update the current policy. ", response = void.class, tags={ "OSP","PUT", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 204, message = "The document (policy text) was successfully updated in the database.", response = void.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No document exists to be updated.", response = void.class) })
    public Response oSPOspIdPrivacyPolicyPut(@ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId
,@ApiParam(value = "The changed instance of this OSPRequest" ,required=true) OSPReasonPolicyInput ospPolicy
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.oSPOspIdPrivacyPolicyPut(ospId,ospPolicy,securityContext,headers);
    }
    @PUT
    @Path("/{osp-id}/")


    @io.swagger.annotations.ApiOperation(value = "Update OSPBehaviour entry in the database.", notes = "Called when by the policy computation component when the regulator api updates a regulation. Their new OSPRequest document is stored in the policy DB. ", response = void.class, tags={ "OSP","PUT", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 204, message = "The document (OSPBehaviour) was successfully updated in the database.", response = void.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No document exists to be updated.", response = void.class) })
    public Response oSPOspIdPut(@ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId
,@ApiParam(value = "The changed instance of this OSPRequest" ,required=true) OSPPrivacyPolicyInput ospPolicy
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.oSPOspIdPut(ospId,ospPolicy,securityContext,headers);
    }
    @POST
    @Path("/")


    @io.swagger.annotations.ApiOperation(value = "Create a new OSP entry in the database.", notes = "Called by the policy computation component when a new regulation is added to OPERANDO. ", response = void.class, tags={ "OSP","POST", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 201, message = "The document (OSPBehaviour) was successfully created in the database.", response = void.class),

        @io.swagger.annotations.ApiResponse(code = 405, message = "Error. The document (OSPBehaviour) at this id has previously been created in the database.", response = void.class) })
    public Response oSPPost(@ApiParam(value = "The first instance of this OSP document" ,required=true) OSPPrivacyPolicyInput ospPolicy
,@Context SecurityContext securityContext, @Context HttpHeaders headers)
    throws NotFoundException {
        return delegate.oSPPost(ospPolicy,securityContext,headers);
    }
}
