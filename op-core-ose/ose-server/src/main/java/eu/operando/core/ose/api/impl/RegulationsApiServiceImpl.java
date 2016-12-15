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
package eu.operando.core.ose.api.impl;

import io.swagger.model.PrivacyRegulation;
import io.swagger.model.PrivacyRegulationInput;

import io.swagger.api.NotFoundException;

import eu.operando.core.ose.mongo.RegulationsMongo;
import io.swagger.api.ApiResponseMessage;
import io.swagger.api.RegulationsApiService;
import io.swagger.client.ApiClient;
import io.swagger.client.api.LogApi;
import io.swagger.client.ApiException;
import io.swagger.client.model.LogRequest;
import io.swagger.client.model.LogRequest.LogDataTypeEnum;
import io.swagger.client.model.LogRequest.LogPriorityEnum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-25T15:37:02.222Z")
public class RegulationsApiServiceImpl extends RegulationsApiService {

    ApiClient apiClient;
    LogApi logApi;

    public RegulationsApiServiceImpl() {
        this.apiClient = new ApiClient();
        this.apiClient.setBasePath("http://integration.operando.esilab.org:8090/operando/core/ldb");
        this.logApi = new LogApi(this.apiClient);
    }

    private void logRequest(String requesterId, String title, String description,
            LogDataTypeEnum logDataType, LogPriorityEnum logPriority,
            ArrayList<String> keywords) {

        ArrayList<String> words = new ArrayList<String>(Arrays.asList("OSE", "Regulations"));
        for(String word : keywords) {
            words.add(word);
        } 

        LogRequest logRequest = new LogRequest();
        logRequest.setUserId("OSE-Regulations");
        logRequest.setDescription(description);
        logRequest.setLogDataType(logDataType);
        logRequest.setTitle(title);
        logRequest.setLogPriority(logPriority);
        logRequest.setRequesterId(requesterId);
        logRequest.setRequesterType(LogRequest.RequesterTypeEnum.PROCESS);
        logRequest.setKeywords(words);

        try {
            String response = this.logApi.lodDB(logRequest);
            Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.INFO, response);

        } catch (ApiException ex) {
            Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.SEVERE, "failed to log", ex);
        }
    }

    /**
     * Enter a new regulation into the system. Store the regulation in the
     * Policy DB and then call the PC component to do a compliance check. From
     * the output, store an OSP compliance report.
     *
     * @param regulation The new regulation.
     * @param securityContext
     * @return
     * @throws NotFoundException
     */
    @Override
    public Response regulationsPost(PrivacyRegulation regulation, SecurityContext securityContext)
            throws NotFoundException {
        // do some magic!
        Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.INFO, "upp regulations POST privacy regulation {0}", regulation.toString());

        logRequest("regulationsPost", "Regulation: ".concat(regulation.getRegId()),
                "OSE regulations POST received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        RegulationsMongo regMongo = new RegulationsMongo();
        String storeAction = regMongo.storeRegulation(regulation);
        if (storeAction == null) {

            logRequest("regulationsPost", "Regulation".concat(regulation.getRegId()),
                    "OSE regulations POST failed",
                    LogDataTypeEnum.ERROS, LogPriorityEnum.HIGH,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(409).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error occured. The resource already exists, so a new resource cannot be created.")).build();
        }

        logRequest("regulationsPost", "Regulation".concat(regulation.getRegId()),
                "OSE regulations POST complete",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.status(Response.Status.CREATED).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                storeAction)).build();
    }

    /**
     * Update an existing regulation. Store it in the Policy DB, run compliance
     * checks using the PC component, and then create compliance reports stored
     * in the log DB.
     *
     * @param regId
     * @param regulation
     * @param securityContext
     * @return
     * @throws NotFoundException
     */
    @Override
    public Response regulationsRegIdPut(String regId, PrivacyRegulationInput regulation, SecurityContext securityContext)
            throws NotFoundException {

        Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.INFO, "upp regulations PUT privacy regulation {0}", regId);

        logRequest("Regulations PUT", "PUT",
                "PDB regulations PUT received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        RegulationsMongo regMongo = new RegulationsMongo();
        boolean updateAction = regMongo.updateRegulation(regId, regulation);

        if (!updateAction) {

            logRequest("Regulations PUT", "PUT",
                    "PDB regulations PUT failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exists to be updated.")).build();
        }

        logRequest("Regulations PUT", "PUT",
                "PDB regulations PUT complete",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.status(Response.Status.OK).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "Successful response. The regulation has been added.")).build();
    }

}
