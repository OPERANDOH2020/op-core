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

import io.swagger.api.*;
import io.swagger.model.PrivacyRegulationInput;

import io.swagger.api.NotFoundException;

import eu.operando.core.pdb.RegulationsMongo;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public class RegulationsApiServiceImpl extends RegulationsApiService {

    @Override
    public Response regulationsGet(String filter, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!
        RegulationsMongo regdb = new RegulationsMongo();
        String regList = regdb.getRegulationByFilter(filter);

        if (regList == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the regulation does not exist")).build();
        }

        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, regList)).build();
        return Response.ok(regList, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response regulationsPost(PrivacyRegulationInput regulation, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!

        RegulationsMongo regdb = new RegulationsMongo();
        String storeAction = regdb.storeRegulation(regulation);
        if (storeAction == null) {
            return Response.status(405).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The document (PrivacyRegulation) at this id has previously been created in the database.")).build();
        }
        return Response.status(Response.Status.CREATED).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                storeAction)).build();
        //return Response.ok(storeAction, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response regulationsRegIdDelete(String regId, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!
        RegulationsMongo regdb = new RegulationsMongo();
        boolean delAction = regdb.deleteRegulationById(regId);
        if (!delAction) {
            System.out.println("cannot delete regulation " + regId);
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exits to be deleted")).build();
        }

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (PrivacyRegulation) was successfully deleted from the database.")).build();
    }

    @Override
    public Response regulationsRegIdGet(String regId, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!

        RegulationsMongo regdb = new RegulationsMongo();
        String prString = regdb.getRegulationById(regId);
        if (prString == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the regulation does not exist")).build();
        }

        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, prString)).build();
        return Response.ok(prString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response regulationsRegIdPut(String regId, PrivacyRegulationInput regulation, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!

        RegulationsMongo regdb = new RegulationsMongo();
        boolean updateAction = regdb.updateRegulation(regId, regulation);
        if (!updateAction) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exists to be updated.")).build();
        }

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (PrivacyRegulation) was successfully updated in the database.")).build();
    }

}
