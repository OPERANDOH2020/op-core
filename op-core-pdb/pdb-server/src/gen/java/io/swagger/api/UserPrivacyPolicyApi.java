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

import io.swagger.api.UserPrivacyPolicyApiService;
import eu.operando.core.pdb.api.factories.UserPrivacyPolicyApiServiceFactory;

import io.swagger.annotations.ApiParam;

import eu.operando.core.pdb.common.model.UserPrivacyPolicy;

import io.swagger.api.NotFoundException;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/user_privacy_policy")


@io.swagger.annotations.Api(description = "the user_privacy_policy API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-10-28T08:28:40.436Z")
public class UserPrivacyPolicyApi  {
   private final UserPrivacyPolicyApiService delegate = UserPrivacyPolicyApiServiceFactory.getUserPrivacyPolicyApi();

    @GET
    @Path("/")


    @io.swagger.annotations.ApiOperation(value = "Perform a search query across the collection of UPPs.", notes = "The query contains a json object (names, values) and the request returns the list of documents (UPPs) where the filter matches i.e. each document contains fields with those values. ", response = UserPrivacyPolicy.class, responseContainer = "List", tags={ "UPP","GET", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "The list of UPP documents that match the query are returned in full.", response = UserPrivacyPolicy.class, responseContainer = "List"),

        @io.swagger.annotations.ApiResponse(code = 405, message = "Error in request. There is an invalid input in the query field.", response = UserPrivacyPolicy.class, responseContainer = "List") })
    public Response userPrivacyPolicyGet(@ApiParam(value = "The query filter to be matched - ?filter={json description}",required=true) @QueryParam("filter") String filter
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.userPrivacyPolicyGet(filter,securityContext);
    }
    @POST
    @Path("/")


    @io.swagger.annotations.ApiOperation(value = "Create a new UPP entry in the database for the user.", notes = "Called when a new user is registered with operando. Their new privacy preferences are passed in the UPP document and stored in the policy DB. ", response = void.class, tags={ "UPP","POST", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 201, message = "The document (UPP) was successfully created in the database.", response = void.class),

        @io.swagger.annotations.ApiResponse(code = 405, message = "Error. The document (UPP) at this id has previously been created in the database.", response = void.class) })
    public Response userPrivacyPolicyPost(@ApiParam(value = "The first instance of this user's UPP" ,required=true) UserPrivacyPolicy upp
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.userPrivacyPolicyPost(upp,securityContext);
    }
    @DELETE
    @Path("/{user-id}/")


    @io.swagger.annotations.ApiOperation(value = "Remove the UPP entry in the database for the user.", notes = "Called when a user leaves operando. Their privacy preferences and policies are deleted from the database. ", response = void.class, tags={ "UPP","DELETE", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 204, message = "The document (UPP) was successfully deleted from the database.", response = void.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No document exists to be deleted.", response = void.class) })
    public Response userPrivacyPolicyUserIdDelete(@ApiParam(value = "The user identifier number",required=true) @PathParam("user-id") String userId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.userPrivacyPolicyUserIdDelete(userId,securityContext);
    }
    @GET
    @Path("/{user-id}/")


    @io.swagger.annotations.ApiOperation(value = "Read the user privacy policy for the given user id.", notes = "Get a specific UPP document via the user's id. This will return the full user privacy policy document in json format. ", response = UserPrivacyPolicy.class, tags={ "UPP","GET", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "The UPP document requested to be read is returned in full", response = UserPrivacyPolicy.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error - the user does not exist.", response = UserPrivacyPolicy.class) })
    public Response userPrivacyPolicyUserIdGet(@ApiParam(value = "The user identifier number",required=true) @PathParam("user-id") String userId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.userPrivacyPolicyUserIdGet(userId,securityContext);
    }
    @PUT
    @Path("/{user-id}/")


    @io.swagger.annotations.ApiOperation(value = "Update UPP entry in the database for the user.", notes = "Called when a user makes a change to the UPP registered with operando. Their new privacy preferences are passed in the UPP document and stored in the policy DB. ", response = void.class, tags={ "UPP","PUT", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 204, message = "The document (UPP) was successfully updated in the database.", response = void.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No document exists to be updated.", response = void.class) })
    public Response userPrivacyPolicyUserIdPut(@ApiParam(value = "The user identifier number",required=true) @PathParam("user-id") String userId
,@ApiParam(value = "The changed instance of this user's UPP" ,required=true) UserPrivacyPolicy upp
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.userPrivacyPolicyUserIdPut(userId,upp,securityContext);
    }
}
