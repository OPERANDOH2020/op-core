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
package eu.operando.core.pdb.api.impl;

import io.swagger.api.*;

import io.swagger.api.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import eu.operando.core.pdb.common.model.OSPPrivacyPolicyInput;
import eu.operando.core.pdb.common.model.OSPReasonPolicyInput;
import eu.operando.core.pdb.mongo.OSPPrivacyPolicyMongo;
import io.swagger.client.ApiClient;
import io.swagger.client.api.LogApi;
import io.swagger.client.model.LogRequest;
import io.swagger.client.ApiException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-10-28T08:28:40.436Z")
public class OSPApiServiceImpl extends OSPApiService {

    ApiClient apiClient;
    LogApi logApi;

    public OSPApiServiceImpl() {
        this.apiClient = new ApiClient();
        this.apiClient.setBasePath("http://integration.operando.esilab.org:8090/operando/core/ldb");
        this.logApi = new LogApi(this.apiClient);
    }

    private void logRequest(String requesterId, String title, String description,
            ArrayList<String> keywords) {

        LogRequest logRequest = new LogRequest();
        logRequest.setUserId("003");
        logRequest.setDescription(description);
        logRequest.setLogDataType(LogRequest.LogDataTypeEnum.INFO);
        logRequest.setTitle("PDB" + title);
        logRequest.setLogPriority(LogRequest.LogPriorityEnum.LOW);
        logRequest.setRequesterId(requesterId);
        logRequest.setRequesterType(LogRequest.RequesterTypeEnum.PROCESS);

        logRequest.setKeywords(keywords);

        try {
            String response = this.logApi.lodDB(logRequest);
            Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, response);

        } catch (ApiException ex) {
            Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.SEVERE, "failed to log", ex);
        }
    }

    @Override
    public Response oSPGet(String filter, SecurityContext securityContext)
            throws NotFoundException {

        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP GET {0}", filter);

        logRequest("PDB OSP", "GET OSP",
                "OSP GET received",
                new ArrayList<String>(Arrays.asList("PDB", "OSP", "received")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        String ospString = regdb.getOSPByFilter(filter);

        if (ospString == null) {

            logRequest("PDB OSP", "GET OSP",
                    "OSP GET failed",
                    new ArrayList<String>(Arrays.asList("PDB", "OSP", "failed")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the regulation does not exist")).build();
        }

        logRequest("PDB OSP", "GET OSP",
                "OSP GET ok",
                new ArrayList<String>(Arrays.asList("PDB", "OSP", "ok")));

        return Response.ok(ospString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response oSPOspIdDelete(String ospId, SecurityContext securityContext)
            throws NotFoundException {

        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP DELETE {0}", ospId);

        logRequest("OSP DELETE", "DELETE",
                "OSP DELETE received",
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        boolean delAction = regdb.deleteOSPById(ospId);

        if (!delAction) {

            System.out.println("cannot delete regulation " + ospId);

            logRequest("OSP DELETE", "DELETE",
                    "OSP DELETE failed",
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exits to be deleted")).build();
        }

        logRequest("OSP DELETE", "DELETE",
                "OSP DELETE complete",
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (OSPBehaviour) was successfully deleted from the database.")).build();
    }

    @Override
    public Response oSPOspIdGet(String ospId, SecurityContext securityContext)
            throws NotFoundException {

        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP GET {0}", ospId);

        logRequest("OSP GET", "GET",
                "OSP GET received",
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        String ospString = regdb.getOSPById(ospId);

        if (ospString == null) {

            logRequest("OSP GET", "GET",
                    "OSP GET failed",
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the regulation does not exist")).build();
        }

        logRequest("OSP GET", "GET",
                "OSP GET complete",
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok(ospString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response oSPOspIdPrivacyPolicyGet(String ospId, SecurityContext securityContext) throws NotFoundException {

        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP GET {0}", ospId);

        logRequest("OSP GET", "GET",
                "OSP GET received",
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        String ospString = regdb.getPolicyOSPById(ospId);

        if (ospString == null) {

            logRequest("OSP GET", "GET",
                    "OSP GET failed",
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the reason policy does not exist")).build();
        }

        logRequest("OSP GET", "GET",
                "OSP GET complete",
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok(ospString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response oSPOspIdPrivacyPolicyPut(String ospId, OSPReasonPolicyInput ospPolicy,
            SecurityContext securityContext) throws NotFoundException {

        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP PUT {0}", ospId);

        logRequest("OSP PUT", "PUT",
                "OSP PUT received",
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        boolean updateAction = regdb.updatePolicyOSP(ospId, ospPolicy);

        if (!updateAction) {

            logRequest("OSP PUT", "PUT",
                    "OSP PUT received",
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exists to be updated.")).build();
        }

        logRequest("OSP PUT", "PUT",
                "OSP PUT received",
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (OSPBehaviour) was successfully updated in the database.")).build();
    }

    @Override
    public Response oSPOspIdPut(String ospId, OSPPrivacyPolicyInput ospPolicy, SecurityContext securityContext)
            throws NotFoundException {

        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP PUT {0}", ospId);

        logRequest("OSP PUT", "PUT",
                "OSP PUT received",
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        boolean updateAction = regdb.updateOSP(ospId, ospPolicy);

        if (!updateAction) {

            logRequest("OSP PUT", "PUT",
                    "OSP PUT failed",
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exists to be updated.")).build();
        }

        logRequest("OSP PUT", "PUT",
                "OSP PUT complete",
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (OSPBehaviour) was successfully updated in the database.")).build();
    }

    @Override
    public Response oSPPost(OSPPrivacyPolicyInput ospPolicy, SecurityContext securityContext)
            throws NotFoundException {

        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP POST {0}", ospPolicy.toString());

        logRequest("OSP POST", "POST",
                "OSP POST received",
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        String storeAction = regdb.storeOSP(ospPolicy);

        if (storeAction == null) {

            logRequest("OSP POST", "POST",
                    "OSP POST failed",
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(405).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The document (OSPBehaviour) at this id has previously been created in the database.")).build();
        }

        logRequest("OSP POST", "POST",
                "OSP POST complete",
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.status(Response.Status.CREATED).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                storeAction)).build();
    }

}
