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
//package eu.operando.core.ose.services;
//
//import java.awt.PageAttributes.MediaType;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.DateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Properties;
//import java.util.Timer;
//import java.util.TimerTask;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import javax.ws.rs.core.HttpHeaders;
//import javax.ws.rs.core.SecurityContext;
//import javax.xml.ws.Response;
//
//import eu.operando.core.cas.client.api.DefaultApi;
//import eu.operando.core.cas.client.model.UserCredential;
//import eu.operando.core.ose.api.impl.RegulationsApiServiceImpl;
//import eu.operando.core.ose.mongo.OspsMongo;
//import eu.operando.core.pdb.common.model.OSPDataRequest;
//import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;
//import eu.operando.core.pdb.common.model.PrivacySetting;
//import io.swagger.api.NotFoundException;
//import io.swagger.api.OspsApiService;
//
//import io.swagger.client.ApiClient;
//import io.swagger.client.api.LogApi;
//import io.swagger.client.model.LogRequest;
//import io.swagger.client.model.LogRequest.LogLevelEnum;
//import io.swagger.client.model.LogRequest.LogPriorityEnum;
//
//
//@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-25T15:37:02.222Z")
//public class OspsApiServiceImplback extends OspsApiService {
//
//    // LogDB
//    LogApi logApi;
//    // AAPI
//    DefaultApi aapiClient;
//
//    String oseOSPSSId = "ose/osps/.*";
//    String logdbSId = "ose/osps/.*";
//    String aapiBasePath = "http://integration.operando.esilab.org:8135/operando/interfaces/aapi";
//    String logdbBasePath = "http://integration.operando.esilab.org:8090/operando/core/ldb";
//    String ospsLoginName = "xxxxx";
//    String ospsLoginPassword = "xxxxx";
//    String stHeaderName = "Service-Ticket";
//    String logdbST = "";
//    long ticketLifeTime = 1000L * 60 * 60;
//
//    String mongoServerHost = "localhost";
//    int mongoServerPort = 27017;
//    OspsMongo ospsMongodb = null;
//    Timer timer;
//
//    Properties prop = null;
//
//    public OspsApiServiceImplback() {
//        super();
//
//        //  get service config params
//        prop = loadServiceProperties();
//        loadParams();
//
//        // setup aapi client
//        eu.operando.core.cas.client.ApiClient aapiDefaultClient = new eu.operando.core.cas.client.ApiClient();
//        aapiDefaultClient.setBasePath(aapiBasePath);
//        this.aapiClient = new DefaultApi(aapiDefaultClient);
//
//        // setup logdb client
//        final ApiClient apiClient = new ApiClient();
//        apiClient.setBasePath(logdbBasePath);
//
//        TimerTask timerTask = new TimerTask() {
//            //@Override
//            public void run() {
//                Logger.getLogger(OspsApiServiceImplback.class.getName()).log(Level.INFO, "osps TIMER RUN now");
//                logdbST = getServiceTicket(ospsLoginName, ospsLoginPassword, logdbSId);
//                apiClient.addDefaultHeader(stHeaderName, logdbST);
//            }
//        };
//
//        timer = new Timer();
//        timer.scheduleAtFixedRate(timerTask, 0, ticketLifeTime);
//
//        // get service ticket for logdb service
//        logdbST = getServiceTicket(ospsLoginName, ospsLoginPassword, logdbSId);
//        apiClient.addDefaultHeader(stHeaderName, logdbST);
//        this.logApi = new LogApi(apiClient);
//
//        // setup mongo part
//        ospsMongodb = new OspsMongo(mongoServerHost, mongoServerPort);
//    }
//
//    private void loadParams() {
//        // setup aapi client
//        if (prop.getProperty("aapi.basepath") != null) {
//            aapiBasePath = prop.getProperty("aapi.basepath");
//        }
//
//        // setup logdb client
//        if (prop.getProperty("logdb.basepath") != null) {
//            logdbBasePath = prop.getProperty("logdb.basepath");
//        }
//
//        // get service ticket for logdb service
//        if (prop.getProperty("ose.osps.service.login") != null) {
//            ospsLoginName = prop.getProperty("ose.osps.service.login");
//        }
//        if (prop.getProperty("ose.osps.service.password") != null) {
//            ospsLoginPassword = prop.getProperty("ose.osps.service.password");
//        }
//        if (prop.getProperty("logdb.service.id") != null) {
//            logdbSId = prop.getProperty("logdb.service.id");
//        }
//
//        // setup mongo part
//        if (prop.getProperty("mongo.server.host") != null) {
//            mongoServerHost = prop.getProperty("mongo.server.host");
//        }
//        if (prop.getProperty("mongo.server.port") != null) {
//            try {
//                mongoServerPort = Integer.parseInt(prop.getProperty("mongo.server.port"));
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private Properties loadServiceProperties() {
//        Properties props;
//        props = new Properties();
//
//        InputStream is = null;
//        try {
//            is = this.getClass().getClassLoader().getResourceAsStream("service.properties");
//            props.load(is);
//        } catch (IOException e) {
//            // Display to console for debugging purposes.
//            System.err.println("Error reading Configuration service properties file");
//            // Add logging code to log an error configuring the API on startup
//        } finally {
//            try {
//                is.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//        return props;
//    }
//
//    private String getServiceTicket(String username, String password, String serviceId) {
//        String st = null;
//
//        UserCredential userCredential = new UserCredential();
//        userCredential.setUsername(username);
//        userCredential.setPassword(password);
//
//        try {
//            String tgt = aapiClient.aapiTicketsPost(userCredential);
//            System.out.println("ose osps service TGT: " + tgt);
//            st = aapiClient.aapiTicketsTgtPost(tgt, serviceId);
//            System.out.println("logdb osps service ticket: " + st);
//
//        } catch (eu.operando.core.cas.client.ApiException ex) {
//            ex.printStackTrace();
//        }
//        return st;
//    }
//
//    private boolean aapiTicketsStValidateGet(String st) {
//        try {
//            aapiClient.aapiTicketsStValidateGet(st, oseOSPSSId);
//        } catch (eu.operando.core.cas.client.ApiException ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }
//
//    private boolean validateHeaderSt(HttpHeaders headers) {
//        return true;
//    }
//
//    private boolean validateHeaderSt1(HttpHeaders headers) {
//        if (headers != null) {
//            List<String> stHeader = headers.getRequestHeader(stHeaderName);
//            if (stHeader != null) {
//                String st = stHeader.get(0);
//                try {
//                    aapiClient.aapiTicketsStValidateGet(st, oseOSPSSId);
//                    Logger.getLogger(RegulationsApiServiceImpl.class.getName()).log(Level.INFO,
//                            "Service Ticket validation succeeded");
//                    return true;
//                } catch (eu.operando.core.cas.client.ApiException ex) {
//                    Logger.getLogger(RegulationsApiServiceImpl.class.getName()).log(Level.WARNING,
//                            "Service Ticket validation failed: {0}", ex.getMessage());
//                    return false;
//                }
//            }
//        }
//        return false;
//    }
//
//    private void logRequest(String requesterId, String title, String description,
//            LogLevelEnum logDataType, LogPriorityEnum logPriority,
//            ArrayList<String> keywords) {
//
//        ArrayList<String> words = new ArrayList<String>(Arrays.asList("OSE", "OSP"));
//        for (String word : keywords) {
//            words.add(word);
//        }
//
//        LogRequest logRequest = new LogRequest();
//        logRequest.setUserId("OSE-OSP");
//        logRequest.setDescription(description);
//        logRequest.setLogLevel(logDataType);
//        logRequest.setTitle(title);
//        logRequest.setLogPriority(logPriority);
//        logRequest.setRequesterId(requesterId);
//        logRequest.setRequesterType(LogRequest.RequesterTypeEnum.PROCESS);
//        logRequest.setKeywords(words);
//
//        try {
//            String response = this.logApi.lodDB(logRequest);
//            Logger.getLogger(OspsApiServiceImplback.class.getName()).log(Level.INFO, response);
//
//        } catch (ApiException ex) {
//            Logger.getLogger(OspsApiServiceImplback.class.getName()).log(Level.SEVERE, "failed to log", ex);
//        }
//    }
//
//    @Override
//    public Response ospsOspIdPrivacySettingsGet(String ospId, String userId, SecurityContext securityContext)
//            throws NotFoundException {
//
//        Logger.getLogger(OspsApiServiceImplback.class.getName()).log(Level.INFO, "upp GET policy filter {0}", ospId);
//
//        logRequest("ospsPrivacyPolicyGet", ospId,
//                "PDB osp privacy settings GET received",
//                LogLevelEnum.INFO, LogPriorityEnum.NORMAL,
//                new ArrayList<String>(Arrays.asList("ospId", "userId")));
//
//        OspsMongo ospsMongo = new OspsMongo();
//        String ospString = ospsMongo.ospsOspIdPrivacySettingsGet(ospId, userId);
//
//        logRequest("ospsPrivacyPolicyGet", ospId,
//                "PDB osp privacy settings GET complete",
//                LogLevelEnum.INFO, LogPriorityEnum.NORMAL,
//                new ArrayList<String>(Arrays.asList("ospId", "userId")));
//
//        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK,
//        //        "Successful response. The privacy settings information for this user at the given OSP is returned.")).build();
//        return Response.ok(ospString, MediaType.APPLICATION_JSON).build();
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
//        // do some magic!
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
//    @Override
//    public Response ospsOspIdAuditGet(String ospId, String start, String end, SecurityContext securityContext) throws NotFoundException {
//        class  Report{
//            ArrayList<DataAccessLog> validLogs= ArrayList<DataAccessLog>();
//            ArrayList<DataAccessLog> invalidLogs= ArrayList<DataAccessLog>();
//            Boolean checkValid()
//            {
//                if(invalidLogs.Count == 0)
//                {
//                   return true;
//                }
//                 return false;
//            }
//        }
//
///*        string baseurl = ConfigurationManager.AppSettings["ldbBasePath"];
//        //instance to get from the log database
//
//        //gets the list of OSPs
//        List<OSPPrivacyPolicy> ospList = GetOspList();*/
//
//        //creates datetime objects for the start date and end date selected by the user on the html page
//        LdbClient instance = new eu.operando.core.ldb.LdbClient(baseurl);
//        DateFormat startDate = new DateFormat(start);
//        DateFormat endDate = new DateFormat(end);
//
//
//        OSPPrivacyPolicy matchingOsp = ospList.Where(o => o.OspPolicyId.Equals(ospId)).First();
//        HashSet<String> roles = new HashSet<String>();
//
//        foreach (AccessPolicy policy in matchingOsp.Policies)
//        {
//            roles.Add(policy.Subject);
//        }
//
//        //get the data access logs
//        ArrayList<DataAccessLog> logs = instance.GetDataAccessLogs(OspPolicyId);
//        ArrayList<DataAccessLog> logsToCheck = new ArrayList<DataAccessLog>();
//        //sorts by logs that fall within the region for the dates
//        foreach (DataAccessLog log in logs)
//        {
//            if (log.logDate >= startDate && log.logDate <= endDate && log.requesterId.Equals(matchingOsp.OspPolicyId))
//            {
//                logsToCheck.Add(log);
//            }
//        }
//        //creates a report object
//        Report report = new Report();
//        //goes through each log that matches the dates
//        foreach (DataAccessLog log in logsToCheck)
//        {
//            bool found = false;
//            string matchedrole = "nothing";
//            //checks if there is a role matching the role accessed according to the logs
//            foreach (String role in roles)
//            {
//                if (log.description.contains(role))
//                {
//                    found = true;
//                    matchedrole = role;
//                    break;
//                }
//            }
//            //runs if there is a matching role
//            if (found)
//            {
//            	bool policymatch = false;
//                //checks if there is a resource and role which matches (the policy for this log)
//            	foreach(AccessPolicy p in matchingOsp.Policies){
//            		if(p.Subject..Equals(matchedrole) && p.Resource.Equals(log.title))
//            		{
//            			policymatch  = true;
//            			break;
//            		}
//            	}
//                if (policymatch)
//                {
//                    report.validLogs.Add(log);
//                }
//                    else
//                {
//                    report.invalidLogs.Add(log);
//                }
//            }
//            else
//            {
//                report.invalidLogs.Add(log);
//            }
//        }
//        String response = "Status: ";
//        if(report.checkValid()) {
//        	 response = "All logs for OSP " + ospId + " are valid for the selected date period";
//        } else {
//        	 foreach (DataAccessLog log in report.invalidLogs)
//             {
//                 response += OSPId + " has made an invalid request with the resource " + log.title + ". ";
//             }
//        }
//
//        return Response.ok().entity(response).build();
//    }
//
//}
