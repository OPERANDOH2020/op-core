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

import eu.operando.core.pdb.common.model.AccessReason;
import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import io.swagger.api.OSPApiService;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicyInput;
import eu.operando.core.pdb.common.model.OSPReasonPolicyInput;
import eu.operando.core.pdb.mongo.OSPPrivacyPolicyMongo;
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
import eu.operando.core.cas.client.api.DefaultApi;
//import eu.operando.core.cas.client.ApiException;
import eu.operando.core.cas.client.model.User;
import eu.operando.core.cas.client.model.UserCredential;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-19T10:59:55.638Z")
public class OSPApiServiceImpl extends OSPApiService {

    // LogDB
    ApiClient apiClient;
    LogApi logApi;

    // AAPI
    DefaultApi aapiClient;
    String serviceId = "pdb/OSP/.*";
    String logDBServiceId = "ose/osps/.*";

    public OSPApiServiceImpl() {
        // setup aapi client
        eu.operando.core.cas.client.ApiClient aapiDefaultClient = new eu.operando.core.cas.client.ApiClient();
        aapiDefaultClient.setBasePath("http://integration.operando.esilab.org:8135/operando/interfaces/aapi");
        this.aapiClient = new DefaultApi(aapiDefaultClient);
                
        // setup logdb client
        this.apiClient = new ApiClient();
        this.apiClient.setBasePath("http://integration.operando.esilab.org:8090/operando/core/ldb");
        
        // get service ticket for logdb service
        //String logdbST = getST("yyyyy", "xxxxx");
        //this.apiClient.addDefaultHeader("service-ticket", logdbST);
        
        this.logApi = new LogApi(this.apiClient);
    }
    
    private String getST(String username, String password) {
        String st = null;
        
        UserCredential userCredential = new UserCredential();
        userCredential.setUsername(username);
        userCredential.setPassword(password);
        
        try {
            String tgt = aapiClient.aapiTicketsPost(userCredential);
            System.out.println("pdb service TGT: " + tgt);
            st = aapiClient.aapiTicketsTgtPost(tgt, logDBServiceId);
            System.out.println("logdb service ticket: " + st);
            
        } catch (eu.operando.core.cas.client.ApiException ex) {
            ex.printStackTrace();
        }
        return st;
    }

    private boolean aapiTicketsStValidateGet(String st) {
        try {
            aapiClient.aapiTicketsStValidateGet(st, serviceId);
        } catch (eu.operando.core.cas.client.ApiException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void logRequest(String requesterId, String title, String description,
            LogDataTypeEnum logDataType, LogPriorityEnum logPriority,
            ArrayList<String> keywords) {

        ArrayList<String> words = new ArrayList<String>(Arrays.asList("PDB", "OSP"));
        for (String word : keywords) {
            words.add(word);
        }

        LogRequest logRequest = new LogRequest();
        logRequest.setUserId("PDB-OSP");
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

        } catch (io.swagger.client.ApiException ex) {
            Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.SEVERE, "failed to log", ex);
        }
    }

    @Override
    public Response oSPGet(String filter, SecurityContext securityContext) throws NotFoundException {

        System.out.println("SecurityContext: " + securityContext.toString());
        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP GET (filter) {0}", filter);

        logRequest("PDB OSP", "GET OSP",
                "OSP GET received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("PDB", "OSP", "received")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        String ospString = regdb.getOSPByFilter(filter);

        if (ospString == null) {

            logRequest("PDB OSP", "GET OSP",
                    "OSP GET failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("PDB", "OSP", "failed")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the regulation does not exist")).build();
        }

        logRequest("PDB OSP", "GET OSP",
                "OSP GET ok",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("PDB", "OSP", "ok")));

        return Response.ok(ospString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response oSPOspIdPrivacyPolicyAccessReasonsGet(String ospId, SecurityContext securityContext) throws NotFoundException {

        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP GET Access Reasons(id) {0}", ospId);

        logRequest("OSP GET access reasons", "GET",
                "OSP GET access reasons received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        String ospString = regdb.getOSPAccessReasonsById(ospId);

        if (ospString == null) {

            logRequest("OSP GET access reasons", "GET",
                    "OSP GET access reasons failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the OSP access policies does not exist")).build();
        }

        logRequest("OSP GET access reasons", "GET",
                "OSP GET access reasons complete",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok(ospString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response oSPOspIdDelete(String ospId, SecurityContext securityContext) throws NotFoundException {
        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP DELETE {0}", ospId);

        logRequest("OSP DELETE", "DELETE",
                "OSP DELETE received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        boolean delAction = regdb.deleteOSPById(ospId);

        if (!delAction) {

            System.out.println("cannot delete regulation " + ospId);

            logRequest("OSP DELETE", "DELETE",
                    "OSP DELETE failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exits to be deleted")).build();
        }

        logRequest("OSP DELETE", "DELETE",
                "OSP DELETE complete",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (OSPBehaviour) was successfully deleted from the database.")).build();
    }

    @Override
    public Response oSPOspIdGet(String ospId, SecurityContext securityContext) throws NotFoundException {

        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP GET (id) {0}", ospId);

        logRequest("OSP GET", "GET",
                "OSP GET received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        String ospString = regdb.getOSPById(ospId);

        if (ospString == null) {

            logRequest("OSP GET", "GET",
                    "OSP GET failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the regulation does not exist")).build();
        }

        logRequest("OSP GET", "GET",
                "OSP GET complete",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok(ospString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response oSPOspIdPrivacyPolicyGet(String ospId, SecurityContext securityContext) throws NotFoundException {

        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP GET {0}", ospId);

        logRequest("OSP GET", "GET",
                "OSP GET received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        String ospString = regdb.getPolicyOSPById(ospId);

        if (ospString == null) {

            logRequest("OSP GET", "GET",
                    "OSP GET failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the reason policy does not exist")).build();
        }

        logRequest("OSP GET", "GET",
                "OSP GET complete",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok(ospString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response oSPOspIdPrivacyPolicyAccessReasonsPost(String ospId, AccessReason ospPolicy, SecurityContext securityContext) throws NotFoundException {

        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP POST Access Reasons(id) {0}", ospId);

        logRequest("OSP POST access reasons", "POST",
                "OSP POST access reasons received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        boolean ospString = regdb.privacyPolicyAccessReasonsPost(ospId, ospPolicy);

        if (!ospString) {

            logRequest("OSP POST access reasons", "POST",
                    "OSP POST access reasons failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the OSP access policies does not exist")).build();
        }

        logRequest("OSP POST access reasons", "POST",
                "OSP POST access reasons complete",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok(ospString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete(String ospId, String reasonId, SecurityContext securityContext) throws NotFoundException {

        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP DELETE Access Reason(id) {0}", ospId);

        logRequest("OSP DELETE access reason", "DELETE",
                "OSP DELETE access reason received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        boolean response = regdb.accessReasonIdDelete(ospId, reasonId);

        if (!response) {

            logRequest("OSP DELETE access reason", "DELETE",
                    "OSP DELETE access reason failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the OSP access policies does not exist")).build();
        }

        logRequest("OSP DELETE access reason", "DELETE",
                "OSP DELETE access reason complete",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok("OK", MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut(String ospId, String reasonId, AccessReason ospPolicy, SecurityContext securityContext) throws NotFoundException {

        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP PUT Access Reason(id) {0}", ospId);

        logRequest("OSP PUT access reason", "PUT",
                "OSP PUT access reason received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        boolean response = regdb.accessReasonIdUpdate(ospId, reasonId, ospPolicy);

        if (!response) {

            logRequest("OSP PUT access reason", "PUT",
                    "OSP PUT access reason failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the OSP access policies does not exist")).build();
        }

        logRequest("OSP PUT access reason", "PUT",
                "OSP PUT access reason complete",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok("OK", MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response oSPOspIdPrivacyPolicyPut(String ospId, OSPReasonPolicyInput ospPolicy, SecurityContext securityContext) throws NotFoundException {

        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP PUT {0}", ospId);

        logRequest("OSP PUT", "PUT",
                "OSP PUT received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        boolean ret = regdb.updatePolicyOSP(ospId, ospPolicy);

        if (ret) {

            logRequest("OSP PUT", "PUT",
                    "OSP PUT failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the reason policy does not exist")).build();
        }

        logRequest("OSP PUT", "PUT",
                "OSP PUT complete",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok("OK", MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response oSPOspIdPut(String ospId, OSPPrivacyPolicyInput ospPolicy, SecurityContext securityContext) throws NotFoundException {

        Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "OSP PUT {0}", ospId);

        logRequest("OSP PUT", "PUT",
                "OSP PUT received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        boolean updateAction = regdb.updateOSP(ospId, ospPolicy);

        if (!updateAction) {

            logRequest("OSP PUT", "PUT",
                    "OSP PUT failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exists to be updated.")).build();
        }

        logRequest("OSP PUT", "PUT",
                "OSP PUT complete",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
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
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        OSPPrivacyPolicyMongo regdb = new OSPPrivacyPolicyMongo();
        String storeAction = regdb.storeOSP(ospPolicy);

        if (storeAction == null) {

            logRequest("OSP POST", "POST",
                    "OSP POST failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(405).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The document (OSPBehaviour) at this id has previously been created in the database.")).build();
        }

        logRequest("OSP POST", "POST",
                "OSP POST complete",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.status(Response.Status.CREATED).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                storeAction)).build();
    }

}
