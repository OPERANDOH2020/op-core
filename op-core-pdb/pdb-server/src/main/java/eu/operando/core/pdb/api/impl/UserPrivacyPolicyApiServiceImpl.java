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

import eu.operando.core.pdb.common.model.UserPrivacyPolicy;

import io.swagger.api.NotFoundException;

import eu.operando.core.pdb.mongo.UPPMongo;
import io.swagger.client.ApiClient;
import io.swagger.client.api.LogApi;
import io.swagger.client.model.LogRequest;
import io.swagger.client.ApiException;
import io.swagger.client.model.LogRequest.LogDataTypeEnum;
import io.swagger.client.model.LogRequest.LogPriorityEnum;
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

    private void logRequest(String requesterId, String title, String description,
            LogDataTypeEnum logDataType, LogPriorityEnum logPriority,
            ArrayList<String> keywords) {
        
        ArrayList<String> words = new ArrayList<String>(Arrays.asList("PDB", "UPP"));
        for(String word : keywords) {
            words.add(word);
        } 

        LogRequest logRequest = new LogRequest();
        logRequest.setUserId("PDB-UPP");
        logRequest.setDescription(description);
        logRequest.setLogDataType(logDataType);
        logRequest.setTitle(title);
        logRequest.setLogPriority(logPriority);
        logRequest.setRequesterId(requesterId);
        logRequest.setRequesterType(LogRequest.RequesterTypeEnum.PROCESS);

        logRequest.setKeywords(words);

        try {
            String response = this.logApi.lodDB(logRequest);
            Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, response);
        } catch (ApiException ex) {
            Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.SEVERE, "failed to log", ex);
        }
    }

    @Override
    public Response userPrivacyPolicyGet(String filter, SecurityContext securityContext)
            throws NotFoundException {

        Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, "upp GET policy filter {0}", filter);

        logRequest("userPrivacyPolicyGet", "filter: ".concat(filter),
                "PDB user privacy policy received for ".concat(filter),
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        UPPMongo uppMongo = new UPPMongo();
        String getString = uppMongo.getUPPByFilter(filter);

        if (getString == null) {

            logRequest("userPrivacyPolicyGet", "filter: ".concat(filter),
                    "PDB user privacy policy GET failed",
                LogDataTypeEnum.ERROR, LogPriorityEnum.HIGH,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the user does not exist")).build();
        }

        logRequest("userPrivacyPolicyGet", "filter: ".concat(filter),
                "PDB user privacy policy GET ok",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok(getString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response userPrivacyPolicyPost(UserPrivacyPolicy upp, SecurityContext securityContext)
            throws NotFoundException {

        Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, "upp POST policy {0}", upp.toString());

        logRequest("userPrivacyPolicyPost", "upp: ".concat(upp.getUserId()),
                "PDB user privacy policy POST received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        UPPMongo uppMongo = new UPPMongo();
        String storeAction = uppMongo.storeUPP(upp);

        if (storeAction == null) {

            logRequest("userPrivacyPolicyPost", "upp: ".concat(upp.getUserId()),
                    "PDB user privacy policy POST failed",
                LogDataTypeEnum.ERROR, LogPriorityEnum.HIGH,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(405).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The document (UPP) at this id has previously been created in the database.")).build();
        }

        logRequest("userPrivacyPolicyPost", "upp: ".concat(upp.getUserId()),
                "PDB user privacy policy POST failed",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.status(Response.Status.CREATED).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                storeAction)).build();
    }

    @Override
    public Response userPrivacyPolicyUserIdDelete(String userId, SecurityContext securityContext)
            throws NotFoundException {

        logRequest("userPrivacyPolicyDelete", "userId: ".concat(userId),
                "PDB user privacy policy DELETE received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("delete", "userId")));

        UPPMongo uppMongo = new UPPMongo();
        boolean delAction = uppMongo.deleteUPPById(userId);

        if (!delAction) {

            logRequest("userPrivacyPolicyDelete", "userId: ".concat(userId),
                    "PDB user privacy policy DELETE failed",
                LogDataTypeEnum.ERROR, LogPriorityEnum.HIGH,
                    new ArrayList<String>(Arrays.asList("delete", "userId")));

            System.out.println("cannot delete UPP " + userId);
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exits to be deleted")).build();
        }

        logRequest("userPrivacyPolicyDelete", "userId: ".concat(userId),
                "PDB user privacy policy DELETE ok",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("delete", "userId")));
        
        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (UPP) was successfully deleted from the database.")).build();
    }

    @Override
    public Response userPrivacyPolicyUserIdGet(String userId, SecurityContext securityContext)
            throws NotFoundException {

        Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, "upp GET policy {0}", userId);

        logRequest("userPrivacyPolicyUserIdGet", "userId: ".concat(userId),
                "PDB user privacy policy GET received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        UPPMongo uppMongo = new UPPMongo();
        String getString = uppMongo.getUPPById(userId);

        if (getString == null) {

            logRequest("userPrivacyPolicyUserIdGet", "userId: ".concat(userId),
                    "PDB user privacy policy GET failed",
                LogDataTypeEnum.ERROR, LogPriorityEnum.HIGH,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the user does not exist")).build();
        }

        logRequest("userPrivacyPolicyUserIdGet", "userId: ".concat(userId),
                "PDB user privacy policy GET ok",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok(getString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response userPrivacyPolicyUserIdPut(String userId, UserPrivacyPolicy upp, SecurityContext securityContext)
            throws NotFoundException {

        Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, "upp PUT policy {0} {1}", new Object[]{userId, upp.toString()});

        logRequest("userPrivacyPolicyPut", "userId: ".concat(userId),
                "PDB user privacy policy PUT received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        UPPMongo uppMongo = new UPPMongo();
        boolean updateAction = uppMongo.updateUPP(userId, upp);

        if (!updateAction) {
            logRequest("userPrivacyPolicyPut", "userId: ".concat(userId),
                    "PDB user privacy policy PUT failed",
                LogDataTypeEnum.ERROR, LogPriorityEnum.HIGH,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exists to be updated.")).build();
        }

        logRequest("userPrivacyPolicyPut", "userId: ".concat(userId),
                "PDB user privacy policy PUT ok",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (UPP) was successfully updated in the database.")).build();
    }

}