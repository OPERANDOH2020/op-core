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

package io.swagger.api.impl;

import eu.operando.core.pdb.LogDBCall;
import io.swagger.api.*;

import io.swagger.model.UserPrivacyPolicy;


import io.swagger.api.NotFoundException;

import eu.operando.core.pdb.UPPMongo;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


public class UserPrivacyPolicyApiServiceImpl extends UserPrivacyPolicyApiService {

    @Override
    public Response userPrivacyPolicyGet(String filter, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        System.out.println("upp get " + filter);

        UPPMongo uppMongo = new UPPMongo();
        String getString = uppMongo.getUPPByFilter(filter);
        if(getString == null){
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                   "Error - the user does not exist")).build();
        }

        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, getString)).build();
        return Response.ok(getString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response userPrivacyPolicyPost(UserPrivacyPolicy upp, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        //System.out.println("UPP:" + upp.toString());
        UPPMongo uppMongo = new UPPMongo();
        String storeAction = uppMongo.storeUPP(upp);
        if(storeAction == null) {
            return Response.status(405).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                   "Error. The document (UPP) at this id has previously been created in the database.")).build();
        }
        return Response.status(Response.Status.CREATED).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                storeAction)).build();
    }

    @Override
    public Response userPrivacyPolicyUserIdDelete(String userId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!

        UPPMongo uppMongo = new UPPMongo();
        boolean delAction = uppMongo.deleteUPPById(userId);
        if (!delAction) {
            System.out.println("cannot delete UPP " + userId);
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exits to be deleted")).build();
        }

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (UPP) was successfully deleted from the database.")).build();
    }

    @Override
    public Response userPrivacyPolicyUserIdGet(String userId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!

        System.out.println("upp get " + userId);

        LogDBCall ldbC = new LogDBCall();
        System.out.println("upp get " + ldbC);
        ldbC.test();

        UPPMongo uppMongo = new UPPMongo();
        String getString = uppMongo.getUPPById(userId);
        if(getString == null){
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                   "Error - the user does not exist")).build();
        }

        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, getString)).build();
        return Response.ok(getString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response userPrivacyPolicyUserIdPut(String userId, UserPrivacyPolicy upp, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!

        UPPMongo uppMongo = new UPPMongo();
        boolean updateAction = uppMongo.updateUPP(userId, upp);
        if(!updateAction){
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                   "Error. No document exists to be updated.")).build();
        }

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (UPP) was successfully updated in the database.")).build();
    }

}
