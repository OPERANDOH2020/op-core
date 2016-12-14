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

import eu.operando.core.pdb.LogDBCall;
import io.swagger.api.*;

import eu.operando.core.pdb.common.model.UserPrivacyPolicy;

import io.swagger.api.NotFoundException;

import eu.operando.core.pdb.mongo.UPPMongo;
import io.swagger.client.ApiClient;
import io.swagger.client.api.LogApi;
import io.swagger.client.ApiException;
import io.swagger.client.model.LogRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public class UserPrivacyPolicyApiServiceImpl extends UserPrivacyPolicyApiService {

    ApiClient apiClient;
    LogApi logApi;

    public UserPrivacyPolicyApiServiceImpl() {
        this.apiClient = new ApiClient();
        this.apiClient.setBasePath("http://integration.operando.esilab.org:8090/operando/core/ldb");
        this.logApi = new LogApi(this.apiClient);
    }

    @Override
    public Response userPrivacyPolicyGet(String filter, SecurityContext securityContext)
            throws NotFoundException {

        Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, "upp GET policy filter {0}", filter);

        logRequest("userPrivacyPolicyGet", "GET",
                "PDB user privacy policy GET received",
                new ArrayList<String>(Arrays.asList("one", "two")));

        UPPMongo uppMongo = new UPPMongo();
        String getString = uppMongo.getUPPByFilter(filter);

        if (getString == null) {

            logRequest("userPrivacyPolicyGet", "GET",
                    "PDB user privacy policy GET failed",
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the user does not exist")).build();
        }

        logRequest("userPrivacyPolicyGet", "GET",
                "PDB user privacy policy GET ok",
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok(getString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response userPrivacyPolicyPost(UserPrivacyPolicy upp, SecurityContext securityContext)
            throws NotFoundException {

        Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, "upp POST policy {0}", upp.toString());

        logRequest("userPrivacyPolicyPost", "POST",
                "PDB user privacy policy POST received",
                new ArrayList<String>(Arrays.asList("one", "two")));

        UPPMongo uppMongo = new UPPMongo();
        String storeAction = uppMongo.storeUPP(upp);

        if (storeAction == null) {

            logRequest("userPrivacyPolicyPost", "POST",
                    "PDB user privacy policy POST failed",
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(405).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The document (UPP) at this id has previously been created in the database.")).build();
        }

        logRequest("userPrivacyPolicyPost", "POST",
                "PDB user privacy policy POST failed",
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.status(Response.Status.CREATED).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                storeAction)).build();
    }

    @Override
    public Response userPrivacyPolicyUserIdDelete(String userId, SecurityContext securityContext)
            throws NotFoundException {

        logRequest("userPrivacyPolicyDelete", "DELETE",
                "PDB user privacy policy DELETE received",
                new ArrayList<String>(Arrays.asList("one", "two")));

        UPPMongo uppMongo = new UPPMongo();
        boolean delAction = uppMongo.deleteUPPById(userId);

        if (!delAction) {

            logRequest("userPrivacyPolicyDelete", "DELETE",
                    "PDB user privacy policy DELETE failed",
                    new ArrayList<String>(Arrays.asList("one", "two")));

            System.out.println("cannot delete UPP " + userId);
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exits to be deleted")).build();
        }

        logRequest("userPrivacyPolicyDelete", "DELETE",
                "PDB user privacy policy DELETE ok",
                new ArrayList<String>(Arrays.asList("one", "two")));
        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (UPP) was successfully deleted from the database.")).build();
    }

    @Override
    public Response userPrivacyPolicyUserIdGet(String userId, SecurityContext securityContext)
            throws NotFoundException {

        Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, "upp GET policy {0}", userId);

        logRequest("userPrivacyPolicyUserIdGet", "GET",
                "PDB user privacy policy GET received",
                new ArrayList<String>(Arrays.asList("one", "two")));

        UPPMongo uppMongo = new UPPMongo();
        String getString = uppMongo.getUPPById(userId);

        if (getString == null) {

            logRequest("userPrivacyPolicyUserIdGet", "GET",
                    "PDB user privacy policy GET failed",
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the user does not exist")).build();
        }

        logRequest("userPrivacyPolicyUserIdGet", "GET",
                "PDB user privacy policy GET ok",
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok(getString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response userPrivacyPolicyUserIdPut(String userId, UserPrivacyPolicy upp, SecurityContext securityContext)
            throws NotFoundException {

        Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, "upp PUT policy {0} {1}", new Object[]{userId, upp.toString()});

        logRequest("userPrivacyPolicyPut", "PUT",
                "PDB user privacy policy PUT received",
                new ArrayList<String>(Arrays.asList("one", "two")));

        UPPMongo uppMongo = new UPPMongo();
        boolean updateAction = uppMongo.updateUPP(userId, upp);

        if (!updateAction) {
            logRequest("userPrivacyPolicyPut", "PUT",
                    "PDB user privacy policy PUT failed",
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exists to be updated.")).build();
        }

        logRequest("userPrivacyPolicyPut", "PUT",
                "PDB user privacy policy PUT ok",
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (UPP) was successfully updated in the database.")).build();
    }

    private void logRequest(String userId, String operation, String description,
            ArrayList<String> keyword2s) {

//        LogRequest logReq = new LogRequest();
//        logReq.setRequesterType(LogRequest.RequesterTypeEnum.PROCESS);
//        logReq.setRequesterId(userId);
//        logReq.setLogPriority(LogRequest.LogPriorityEnum.LOW);
//        logReq.setTitle("PDB user privacy policy" + operation);
//        logReq.setDescription(description);
//        logReq.setKeywords(keywords);


            LogRequest logRequest = new LogRequest();
	    logRequest.setUserId("003");
	    logRequest.setDescription(description + " user: " + userId);
	    logRequest.setLogDataType(LogRequest.LogDataTypeEnum.INFO);
	    logRequest.setTitle("PDB user privacy policy" + operation);
	    logRequest.setLogPriority(LogRequest.LogPriorityEnum.LOW);
	    logRequest.setRequesterId(userId);
	    logRequest.setRequesterType(LogRequest.RequesterTypeEnum.PROCESS);
	    ArrayList<String> keywords = new ArrayList<String> ();
	    keywords.add("UPP");
	    keywords.add("keywordB");
	    keywords.add("keywordC");
		logRequest.setKeywords(keywords );

        try {
            LogDBCall ldbC = new LogDBCall();
            ldbC.pushLog(logRequest);
            String response = this.logApi.lodDB(logRequest);
            Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, response);

        } catch (ApiException ex) {
            Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.SEVERE, "failed to log", ex);
        }

    }
}
