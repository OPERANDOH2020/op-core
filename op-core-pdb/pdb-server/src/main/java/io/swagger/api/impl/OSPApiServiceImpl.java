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
import io.swagger.model.OSPReasonPolicyInput;
import io.swagger.model.OSPPrivacyPolicyInput;

import io.swagger.api.NotFoundException;



import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


import eu.operando.core.pdb.OSPPrivacyPolicyMongo;
import javax.ws.rs.core.MediaType;


@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-10-28T08:28:40.436Z")
public class OSPApiServiceImpl extends OSPApiService {

    @Override
    public Response oSPGet(String filter, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!
        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        String ospString = regdb.getOSPByFilter(filter);
        if(ospString == null){
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                   "Error - the regulation does not exist")).build();
        }

        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, ospString)).build();
        return Response.ok(ospString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response oSPOspIdDelete(String ospId, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!
        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        boolean delAction = regdb.deleteOSPById(ospId);
        if (!delAction) {
            System.out.println("cannot delete regulation " + ospId);
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exits to be deleted")).build();
        }

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (OSPBehaviour) was successfully deleted from the database.")).build();
    }

    @Override
    public Response oSPOspIdGet(String ospId, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        String ospString = regdb.getOSPById(ospId);
        if(ospString == null){
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                   "Error - the regulation does not exist")).build();
        }

        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, ospString)).build();
        return Response.ok(ospString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response oSPOspIdPrivacyPolicyGet(String ospId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        String ospString = regdb.getPolicyOSPById(ospId);
        if(ospString == null){
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                   "Error - the reason policy does not exist")).build();
        }

        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, ospString)).build();
        return Response.ok(ospString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response oSPOspIdPrivacyPolicyPut(String ospId, OSPReasonPolicyInput ospPolicy, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        boolean updateAction = regdb.updatePolicyOSP(ospId, ospPolicy);
        if (!updateAction) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exists to be updated.")).build();
        }

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (OSPBehaviour) was successfully updated in the database.")).build();
    }
    @Override
    public Response oSPOspIdPut(String ospId, OSPPrivacyPolicyInput ospPolicy, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        boolean updateAction = regdb.updateOSP(ospId, ospPolicy);
        if (!updateAction) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exists to be updated.")).build();
        }

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (OSPBehaviour) was successfully updated in the database.")).build();
    }

    @Override
    public Response oSPPost(OSPPrivacyPolicyInput ospPolicy, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        String storeAction = regdb.storeOSP(ospPolicy);
        if(storeAction == null) {
            return Response.status(405).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                   "Error. The document (OSPBehaviour) at this id has previously been created in the database.")).build();
        }
        return Response.status(Response.Status.CREATED).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                storeAction)).build();
    }

}
