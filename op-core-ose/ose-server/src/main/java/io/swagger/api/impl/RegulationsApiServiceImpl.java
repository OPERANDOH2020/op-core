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

import io.swagger.model.PrivacyRegulation;
import io.swagger.model.PrivacyRegulationInput;

import io.swagger.api.NotFoundException;

import eu.operando.core.ose.RegulationsMongo;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-25T15:37:02.222Z")
public class RegulationsApiServiceImpl extends RegulationsApiService {
    
    @Override
    public Response regulationsPost(PrivacyRegulation regulation, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        RegulationsMongo regMongo = new RegulationsMongo();
        String storeAction = regMongo.storeRegulation(regulation);
        if (storeAction == null) {
            return Response.status(409).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error occured. The resource already exists, so a new resource cannot be created.")).build();
        }
        return Response.status(Response.Status.CREATED).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                storeAction)).build();
    }
    
    @Override
    public Response regulationsRegIdPut(String regId, PrivacyRegulationInput regulation, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        RegulationsMongo regMongo = new RegulationsMongo();
        boolean updateAction = regMongo.updateRegulation(regId, regulation);
        if (!updateAction) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exists to be updated.")).build();
        }

        return Response.status(Response.Status.OK).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "Successful response. The regulation has been added.")).build();
    }
    
}
