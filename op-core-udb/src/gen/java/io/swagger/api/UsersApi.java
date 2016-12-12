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
//      Created By :            Panos Melas/Paul Grace
//      Created Date :          2016-10-28
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////
package io.swagger.api;

import io.swagger.api.UsersApiService;
import io.swagger.api.factories.UsersApiServiceFactory;

import io.swagger.annotations.ApiParam;

import io.swagger.model.UserAccount;

import io.swagger.api.NotFoundException;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/users")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the users API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-10T09:30:45.275Z")
public class UsersApi  {
   private final UsersApiService delegate = UsersApiServiceFactory.getUsersApi();

    @POST
    @Path("/")

    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create user account entry in the database for the user. ", notes = "Called when a user registers their account information ", response = void.class, tags={ "Users","POST", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 201, message = "The document (account info) was successfully created in the database.", response = void.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No document exists to be updated.", response = void.class) })
    public Response usersPost(@ApiParam(value = "The instance of this user's account information" ,required=true) UserAccount user
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.usersPost(user,securityContext);
    }
    @DELETE
    @Path("/{user-id}/")

    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Remove the account entry in the database for the user.", notes = "Called when a user leaves operando. Their privacy preferences and policies are deleted from the database. ", response = void.class, tags={ "Users","DELETE", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 204, message = "The document (account info) was successfully deleted from the database.", response = void.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No document exists to be deleted.", response = void.class) })
    public Response usersUserIdDelete(@ApiParam(value = "The user identifier number",required=true) @PathParam("user-id") String userId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.usersUserIdDelete(userId,securityContext);
    }
    @GET
    @Path("/{user-id}/")

    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Read the user account information for the given user id.", notes = "Get the registered account information e.g. name, user type etc. ", response = UserAccount.class, tags={ "Users","GET", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "The UPP document requested to be read is returned in full", response = UserAccount.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error - the user does not exist.", response = UserAccount.class) })
    public Response usersUserIdGet(@ApiParam(value = "The user identifier number",required=true) @PathParam("user-id") String userId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.usersUserIdGet(userId,securityContext);
    }
    @PUT
    @Path("/{user-id}/")

    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update user account entry in the database for the user.", notes = "Called when a user makes a change to their account information ", response = void.class, tags={ "Users","PUT", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 204, message = "The document (account info) was successfully updated in the database.", response = void.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. No document exists to be updated.", response = void.class) })
    public Response usersUserIdPut(@ApiParam(value = "The user identifier number",required=true) @PathParam("user-id") String userId
,@ApiParam(value = "The changed instance of this user's account information" ,required=true) UserAccount upp
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.usersUserIdPut(userId,upp,securityContext);
    }
}
