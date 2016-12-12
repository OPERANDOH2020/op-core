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

package io.swagger.api.impl;

import eu.operando.core.udb.UserAccountMongo;
import io.swagger.api.*;

import io.swagger.model.UserAccount;

import io.swagger.api.NotFoundException;

import javax.ws.rs.core.MediaType;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-09T15:56:18.842Z")
public class UsersApiServiceImpl extends UsersApiService {
    @Override
    public Response usersPost(UserAccount user, SecurityContext securityContext) throws NotFoundException {

        UserAccountMongo uppMongo = new UserAccountMongo();
        String storeAction = uppMongo.storeUser(user);
        if(storeAction == null) {
            return Response.status(405).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                   "Error. The document (UPP) at this id has previously been created in the database.")).build();
        }
        return Response.status(Response.Status.CREATED).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                storeAction)).build();
    }
    @Override
    public Response usersUserIdDelete(String userId, SecurityContext securityContext) throws NotFoundException {
        UserAccountMongo uppMongo = new UserAccountMongo();
        boolean delAction = uppMongo.deleteUserById(userId);
        if (!delAction) {
            System.out.println("cannot delete UPP " + userId);
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exits to be deleted")).build();
        }

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (UPP) was successfully deleted from the database.")).build();
    }
    @Override
    public Response usersUserIdGet(String userId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        System.out.println("upp get " + userId);
        UserAccountMongo uppMongo = new UserAccountMongo();
        String getString = uppMongo.getUserByFilter("{\"userid\": \"" +userId +"\"}");
        if(getString == null){
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                   "Error - the user does not exist")).build();
        }
        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, getString)).build();
        return Response.ok(getString, MediaType.APPLICATION_JSON).build();
    }
    @Override
    public Response usersUserIdPut(String userId, UserAccount upp, SecurityContext securityContext) throws NotFoundException {
        UserAccountMongo uppMongo = new UserAccountMongo();
        boolean updateAction = uppMongo.updateUser(userId, upp);
        if(!updateAction){
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                   "Error. No document exists to be updated.")).build();
        }

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (UPP) was successfully updated in the database.")).build();
    }
}
