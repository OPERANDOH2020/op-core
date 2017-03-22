///////////////////////////////////////////////////////////////////////////
////
//// © University of Southampton IT Innovation Centre, 2016
////
//// Copyright in this software belongs to University of Southampton
//// IT Innovation Centre of Gamma House, Enterprise Road,
//// Chilworth Science Park, Southampton, SO16 7NS, UK.
////
//// This software may not be used, sold, licensed, transferred, copied
//// or reproduced in whole or in part in any manner or form or in or
//// on any media by any person other than in accordance with the terms
//// of the Licence Agreement supplied with the software, or otherwise
//// without the prior written consent of the copyright owners.
////
//// This software is distributed WITHOUT ANY WARRANTY, without even the
//// implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
//// PURPOSE, except where stated in the Licence Agreement supplied with
//// the software.
////
////      Created By :            Panos Melas
////      Created Date :          2016-04-28
////      Created for Project :   OPERANDO
////
///////////////////////////////////////////////////////////////////////////
//package eu.operando.core.ose.api.impl;
//
//
//import java.util.List;
//import io.swagger.api.NotFoundException;
//
//import eu.operando.core.ose.mongo.OspsMongo;
//import eu.operando.core.ose.services.PolicyComputerService;
//import eu.operando.core.pdb.common.model.OSPDataRequest;
//import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;
//import eu.operando.core.pdb.common.model.PrivacySetting;
//import io.swagger.api.ApiResponseMessage;
//import io.swagger.api.OspsApiService;
//import io.swagger.client.ApiClient;
//import io.swagger.client.api.LogApi;
//import io.swagger.client.ApiException;
//import io.swagger.client.model.LogRequest;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.SecurityContext;
//
//@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-25T15:37:02.222Z")
//public class OspsApiServiceImpl extends OspsApiService {
//
//    ApiClient apiClient;
//    LogApi logApi;
//
//    public OspsApiServiceImpl() {
//        this.apiClient = new ApiClient();
//        this.apiClient.setBasePath("http://integration.operando.esilab.org:8090/operando/core/ldb");
//        this.logApi = new LogApi(this.apiClient);
//    }
//
//    private void logRequest(String requesterId, String title, String description,
//            ArrayList<String> keywords) {
//
//        LogRequest logRequest = new LogRequest();
//        logRequest.setUserId("003");
//        logRequest.setDescription(description);
//        logRequest.setLogLevel(LogRequest.LogLevelEnum.INFO);
//        logRequest.setTitle("OSE" + title);
//        logRequest.setLogPriority(LogRequest.LogPriorityEnum.LOW);
//        logRequest.setRequesterId(requesterId);
//        logRequest.setRequesterType(LogRequest.RequesterTypeEnum.PROCESS);
//
//        logRequest.setKeywords(keywords);
//
//        try {
//            String response = this.logApi.lodDB(logRequest);
//            Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.INFO, response);
//
//        } catch (ApiException ex) {
//            Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.SEVERE, "failed to log", ex);
//        }
//    }
//
//
//    @Override
//    public Response ospsOspIdPrivacySettingsGet(String ospId, String userId, SecurityContext securityContext)
//            throws NotFoundException {
//
//        Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.INFO, "upp GET policy filter {0}", ospId);
//
//        logRequest("ospsPrivacyPolicyGet", "GET",
//                "PDB osp privacy settings GET received",
//                new ArrayList<String>(Arrays.asList("one", "two")));
//
//        OspsMongo ospsMongo = new OspsMongo();
//        ospsMongo.ospsOspIdPrivacySettingsGet(ospId, userId);
//
//        logRequest("ospsPrivacyPolicyGet", "GET",
//                "PDB osp privacy settings GET complete",
//                new ArrayList<String>(Arrays.asList("one", "two")));
//
//        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK,
//                "Successful response. The privacy settings information for this user at the given OSP is returned.")).build();
//        //return Response.ok(ospString, MediaType.APPLICATION_JSON).build();
//    }
//
//    @Override
//    public Response ospsOspIdPrivacySettingsPut(String ospId, String userId, List<PrivacySetting> ospSettings, SecurityContext securityContext)
//            throws NotFoundException {
//        // do some magic!
//        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK,
//                "Successful response. The privacy settings have been agreed and applied via the OSE "
//                + "and the browser extension software. Note, the response here only needs to "
//                + "indicate that the method worked not whether the settings have been applied in practice.")).build();
//    }
//
//    @Override
//    public Response ospsOspIdPrivacytextPut(String ospId, String ospPrivacyText, SecurityContext securityContext)
//            throws NotFoundException {
//        // do some magic!
//        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
//                "Successful response, The privacy text update analysis has begun.")).build();
//
//    }
//
//    @Override
//    public Response ospsOspIdPut(String ospId, OSPPrivacyPolicy ospPolicy, SecurityContext securityContext)
//            throws NotFoundException {
//
//        /**
//         *
//         */
//        System.out.println("Debug: before");
//        PolicyComputerService pCS = new PolicyComputerService();
//        pCS.ospPolicyChange(ospId, ospPolicy);
//         System.out.println("Debug: after");
//        return Response.status(Response.Status.OK).entity(new ApiResponseMessage(ApiResponseMessage.OK,
//                "Successful response. The privacy policy has been received and being processed. Information will be sent via other operation sequences."))
//                .build();
//
//    }
//
//    @Override
//    public Response ospsOspIdWorkflowsPut(String ospId, OSPDataRequest ospWorkflow, SecurityContext securityContext)
//            throws NotFoundException {
//        // do some magic!
//        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
//    }
//
//}
