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

//import io.swagger.model.*;
import eu.operando.core.pdb.common.model.UserPrivacyPolicy;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;

import io.swagger.api.UserPrivacyPolicyApiService;

import eu.operando.core.pdb.mongo.UPPMongo;
import io.swagger.client.ApiClient;
import io.swagger.client.api.LogApi;
import io.swagger.client.model.LogRequest;
import io.swagger.client.ApiException;
import io.swagger.client.model.LogRequest.LogLevelEnum;
import io.swagger.client.model.LogRequest.LogPriorityEnum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import eu.operando.core.cas.client.api.DefaultApi;
import eu.operando.core.cas.client.model.UserCredential;
import eu.operando.core.pdb.common.model.AccessPolicy;
import eu.operando.core.pdb.common.model.OSPConsents;
import eu.operando.core.pdb.common.model.PolicyAttribute;
import io.swagger.client.model.LogRequest.LogTypeEnum;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.HttpHeaders;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-20T12:05:17.950Z")
public class UserPrivacyPolicyApiServiceImpl extends UserPrivacyPolicyApiService {

    // LogDB
    LogApi logApi;
    // AAPI
    DefaultApi aapiClient;

    String pdbUPPSId = "pdb/user_privacy_policy/.*";
    String logdbSId = "ose/osps/.*";
    String aapiBasePath = "http://integration.operando.esilab.org:8135/operando/interfaces/aapi";
    String logdbBasePath = "http://integration.operando.esilab.org:8090/operando/core/ldb";
    String uppLoginName = "xxxxx";
    String uppLoginPassword = "xxxxx";
    String stHeaderName = "Service-Ticket";
    String logdbST = "";
    long ticketLifeTime = 1000L * 60 * 60;

    String mongoServerHost = "localhost";
    int mongoServerPort = 27017;
    UPPMongo uppMongodb = null;
    Timer timer;

    Properties prop = null;

    public UserPrivacyPolicyApiServiceImpl() {
        super();
        //  get service config params
        prop = loadServiceProperties();
        loadParams();

        // setup aapi client
        eu.operando.core.cas.client.ApiClient aapiDefaultClient = new eu.operando.core.cas.client.ApiClient();
        aapiDefaultClient.setBasePath(aapiBasePath);
        this.aapiClient = new DefaultApi(aapiDefaultClient);

        // setup logdb client
        final ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(logdbBasePath);

        TimerTask timerTask = new TimerTask() {
            //@Override
            public void run() {
                Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.INFO, "upp TIMER RUN now");
                logdbST = getServiceTicket(uppLoginName, uppLoginPassword, logdbSId);
                apiClient.addDefaultHeader(stHeaderName, logdbST);
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, ticketLifeTime);

        // get service ticket for logdb service
        logdbST = getServiceTicket(uppLoginName, uppLoginPassword, logdbSId);
        apiClient.addDefaultHeader(stHeaderName, logdbST);
        this.logApi = new LogApi(apiClient);

        // setup mongo part
        uppMongodb = new UPPMongo(mongoServerHost, mongoServerPort);
    }

    private void loadParams() {
        // load aapi client params
        if (prop.getProperty("aapi.basepath") != null) {
            aapiBasePath = prop.getProperty("aapi.basepath");
        }

        // load logdb client params
        if (prop.getProperty("logdb.basepath") != null) {
            logdbBasePath = prop.getProperty("logdb.basepath");
        }

        // get service ticket for logdb service params
        if (prop.getProperty("pdb.upp.service.login") != null) {
            uppLoginName = prop.getProperty("pdb.upp.service.login");
        }
        if (prop.getProperty("pdb.upp.service.password") != null) {
            uppLoginPassword = prop.getProperty("pdb.upp.service.password");
        }
        if (prop.getProperty("logdb.service.id") != null) {
            logdbSId = prop.getProperty("logdb.service.id");
        }

        // setup mongo part params
        if (prop.getProperty("mongo.server.host") != null) {
            mongoServerHost = prop.getProperty("mongo.server.host");
        }
        if (prop.getProperty("mongo.server.port") != null) {
            try {
                mongoServerPort = Integer.parseInt(prop.getProperty("mongo.server.port"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    private Properties loadServiceProperties() {
        Properties props;
        props = new Properties();

        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream("service.properties");
            props.load(is);
        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("Error reading Configuration service properties file");
            // Add logging code to log an error configuring the API on startup        
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return props;
    }

    private String getServiceTicket(String username, String password, String serviceId) {
        String st = null;

        UserCredential userCredential = new UserCredential();
        userCredential.setUsername(username);
        userCredential.setPassword(password);

        try {
            String tgt = aapiClient.aapiTicketsPost(userCredential);
            System.out.println("pdb upp service TGT: " + tgt);
            st = aapiClient.aapiTicketsTgtPost(tgt, serviceId);
            System.out.println("logdb upp service ticket: " + st);

        } catch (eu.operando.core.cas.client.ApiException ex) {
            ex.printStackTrace();
        }
        return st;
    }

    private boolean validateHeaderSt(HttpHeaders headers) {
        return true;
    }

    private boolean validateHeaderSt1(HttpHeaders headers) {
        if (headers != null) {
            List<String> stHeader = headers.getRequestHeader(stHeaderName);
            if (stHeader != null) {
                String st = stHeader.get(0);
                try {
                    aapiClient.aapiTicketsStValidateGet(st, pdbUPPSId);
                    return true;
                } catch (eu.operando.core.cas.client.ApiException ex) {
                    Logger.getLogger(RegulationsApiServiceImpl.class.getName()).log(Level.WARNING,
                            "Service Ticket validation failed: {0}", ex.getMessage());
                    return false;
                }
            }
        }
        return false;
    }

    private void logRequest(String requesterId, String title, String description,
            LogLevelEnum logLevel, LogPriorityEnum logPriority, LogTypeEnum logType,
            String affectedId, ArrayList<String> keywords) {

        ArrayList<String> words = new ArrayList<String>(Arrays.asList("PDB", "UPP"));
        for (String word : keywords) {
            words.add(word);
        }

        LogRequest logRequest = new LogRequest();
        logRequest.setUserId("PDB-UPP");
        logRequest.setRequesterType(LogRequest.RequesterTypeEnum.PROCESS);

        logRequest.setDescription(description);
        logRequest.setLogLevel(logLevel);
        logRequest.setTitle(title);
        logRequest.setLogPriority(logPriority);
        logRequest.setRequesterId(requesterId);
        logRequest.setLogType(logType);
        logRequest.setAffectedUserId(affectedId);

        logRequest.setKeywords(words);

        try {
            String response = this.logApi.lodDB(logRequest);
            Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, response + logRequest.toString());
        } catch (ApiException ex) {
            Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.SEVERE, "failed to log", ex);
        }
    }

    @Override
    public Response userPrivacyPolicyGet(String filter, SecurityContext securityContext, HttpHeaders headers)
            throws NotFoundException {

        Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, "upp GET policy filter {0}", filter);

        if (!validateHeaderSt(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The service ticket failed to validate.")).build();
        }

        logRequest("userPrivacyPolicyGet", "filter: ".concat(filter),
                "PDB user privacy policy received for ".concat(filter),
                LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogTypeEnum.SYSTEM, "",
                new ArrayList<String>(Arrays.asList("one", "two")));

        String getString = uppMongodb.getUPPByFilter(filter);

        if (getString == null) {

            logRequest("userPrivacyPolicyGet", "filter: ".concat(filter),
                    "PDB user privacy policy GET failed",
                    LogLevelEnum.ERROR, LogPriorityEnum.HIGH, LogTypeEnum.SYSTEM, "",
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the user does not exist")).build();
        }

        logRequest("userPrivacyPolicyGet", "filter: ".concat(filter),
                "PDB user privacy policy GET ok",
                LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogTypeEnum.SYSTEM, "",
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok(getString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response userPrivacyPolicyPost(UserPrivacyPolicy upp, SecurityContext securityContext, HttpHeaders headers)
            throws NotFoundException {

        Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, "upp POST policy recv: {0}", upp.toString());

        if (!validateHeaderSt(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The service ticket failed to validate.")).build();
        }

        logRequest("UPP POST request", "Privacy Settings update requested",
                "Your privacy settings were updated because of changes you made through the dashboard.",
                LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogTypeEnum.SYSTEM, upp.getUserId(),
                new ArrayList<String>(Arrays.asList("POST")));

        String userId = uppMongodb.storeUPP(upp);
        //String storedUpp = uppMongodb.getUPPById(userId);

        if (userId == null) {

            logRequest("UPP POST request", "Privacy Settings update request",
                    "Your privacy settings were failed to update.",
                    LogLevelEnum.ERROR, LogPriorityEnum.NORMAL, LogTypeEnum.NOTIFICATION, upp.getUserId(),
                    new ArrayList<String>(Arrays.asList("POST")));

            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The document (UPP) at this id has previously been created in the database.")).build();
        }

        logRequest("UPP POST request", "Privacy Settings updated",
                "Your privacy settings were updated because of changes you made through the dashboard.",
                LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogTypeEnum.NOTIFICATION, upp.getUserId(),
                new ArrayList<String>(Arrays.asList("POST")));

//        return Response.status(Response.Status.CREATED).entity(new ApiResponseMessage(ApiResponseMessage.OK,
//                userId)).build();
        Response resp = null;
        try {
            URI createdURI = new URI("http://integration.operando.esilab.org:8096/operando/core/pdb/upp/" + userId);
            resp = Response.created(createdURI).entity(userId).build();
        } catch (URISyntaxException ex) {
            Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resp;
    }

    @Override
    public Response userPrivacyPolicyUserIdDelete(String userId, SecurityContext securityContext, HttpHeaders headers)
            throws NotFoundException {

        Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, "upp DELET policy {0}", userId);

        if (!validateHeaderSt(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The service ticket failed to validate.")).build();
        }

        logRequest("userPrivacyPolicyDelete", "userId: ".concat(userId),
                "PDB user privacy policy DELETE received",
                LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogTypeEnum.SYSTEM, userId,
                new ArrayList<String>(Arrays.asList("delete", "userId")));

        boolean delAction = uppMongodb.deleteUPPById(userId);

        if (!delAction) {

            logRequest("userPrivacyPolicyDelete", "userId: ".concat(userId),
                    "PDB user privacy policy DELETE failed",
                    LogLevelEnum.ERROR, LogPriorityEnum.HIGH, LogTypeEnum.SYSTEM, userId,
                    new ArrayList<String>(Arrays.asList("delete", "userId")));

            System.out.println("cannot delete UPP " + userId);
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exits to be deleted")).build();
        }

        logRequest("userPrivacyPolicyDelete", "userId: ".concat(userId),
                "PDB user privacy policy DELETE ok",
                LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogTypeEnum.SYSTEM, userId,
                new ArrayList<String>(Arrays.asList("delete", "userId")));

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (UPP) was successfully deleted from the database.")).build();
    }

    @Override
    public Response userPrivacyPolicyUserIdGet(String userId, SecurityContext securityContext, HttpHeaders headers)
            throws NotFoundException {

        Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, "upp GET policy {0}", userId);

        if (!validateHeaderSt(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The service ticket failed to validate.")).build();
        }

        logRequest("userPrivacyPolicyUserIdGet", "userId: ".concat(userId),
                "PDB user privacy policy GET received",
                LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogTypeEnum.SYSTEM, userId,
                new ArrayList<String>(Arrays.asList("one", "two")));

        String getString = uppMongodb.getUPPById(userId);

        if (getString == null) {

            logRequest("userPrivacyPolicyUserIdGet", "userId: ".concat(userId),
                    "PDB user privacy policy GET failed",
                    LogLevelEnum.ERROR, LogPriorityEnum.HIGH, LogTypeEnum.SYSTEM, userId,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the user does not exist")).build();
        }

        logRequest("userPrivacyPolicyUserIdGet", "userId: ".concat(userId),
                "PDB user privacy policy GET ok",
                LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogTypeEnum.SYSTEM, userId,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok(getString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response userPrivacyPolicyUserIdPut(String userId, UserPrivacyPolicy upp, SecurityContext securityContext, HttpHeaders headers)
            throws NotFoundException {

        Logger.getLogger(UserPrivacyPolicyApiServiceImpl.class.getName()).log(Level.INFO, "upp PUT policy {0} {1}", new Object[]{userId, upp.toString()});

        if (!validateHeaderSt(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The service ticket failed to validate.")).build();
        }
        
        logRequest("UPP PUT request", "Privacy Settings update requested",
                "Your privacy settings were updated because of changes you made through the dashboard.",
                LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogTypeEnum.SYSTEM, userId,
                new ArrayList<String>(Arrays.asList("PUT")));
        
        UserPrivacyPolicy oldUpp = uppMongodb.getUPPByIdInt(userId);
        String diff = "Your privacy settings were updated because of changes you made through the dashboard.";
        if (oldUpp != null){
            // there is an old UPP to compare with new
            diff = diffUpps(oldUpp, upp);
        }
        
        boolean updateAction = uppMongodb.updateUPP(userId, upp);

        if (!updateAction) {
            logRequest("UPP PUT request", "Privacy Settings update request",
                    "Your privacy settings were failed to update.",
                    LogLevelEnum.ERROR, LogPriorityEnum.NORMAL, LogTypeEnum.NOTIFICATION, userId,
                    new ArrayList<String>(Arrays.asList("PUT")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exists to be updated.")).build();
        }
        logRequest("UPP PUT REQUEST", "Privacy Settings updated",
                //"Your privacy settings were updated because of changes you made through the dashboard.",
                diff,
                LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogTypeEnum.NOTIFICATION, userId,
                new ArrayList<String>(Arrays.asList("POST")));

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (UPP) was successfully updated in the database.")).build();
    }

    private String diffUpps(UserPrivacyPolicy oldUpp, UserPrivacyPolicy newUpp) {
        //System.out.println("NEW "+newUpp.getUserId());
        StringBuilder sb = new StringBuilder();
        
        for (OSPConsents oldCons : oldUpp.getSubscribedOspPolicies()){
            OSPConsents newCons = getOSPConsents(oldCons.getOspId(), newUpp);
            if(newCons != null) {
                Map<Integer, Boolean> newHM = hashOSPConsents(newCons);
                Boolean headerFlag = false;
                for (AccessPolicy ap : oldCons.getAccessPolicies()) {
                    String apId = ap.getSubject() + ap.getResource() + ap.getAction().name();
                    if (newHM.containsKey(apId.hashCode())) {
                        if (newHM.get(apId.hashCode()) != ap.getPermission()) {
                            if(!headerFlag){
                                sb.append(oldCons.getOspId() + " privacy settings changes: ");
                                headerFlag = true;
                            }                            
                            sb.append("- " + ap.getSubject() + " " + ap.getAction() + " " + getFriendlyName(ap) + 
                                    " has changed to " + newHM.get(apId.hashCode()).toString() + ".\n");
                        } 
                    } 
                }
            }            
        }
        return sb.toString();
    }
    
    private String getFriendlyName(AccessPolicy ap){
        for (PolicyAttribute pa : ap.getAttributes()) {
            if(pa.getAttributeName().equals("friendly_name")){
                return pa.getAttributeValue();
            }
        }
        return ap.getResource();
    }
    
    private Map<Integer, Boolean> hashOSPConsents(OSPConsents ospc){
        Map<Integer, Boolean> ospcHashMap = new HashMap<Integer, Boolean>();
        for(AccessPolicy ap : ospc.getAccessPolicies()) {
            String apId = ap.getSubject() + ap.getResource() + ap.getAction().name();
            ospcHashMap.put(apId.hashCode(), ap.getPermission());
        }
        return ospcHashMap;
    }
    
    private OSPConsents getOSPConsents(String ospId, UserPrivacyPolicy newUpp) {
        OSPConsents ret = null;
        for (OSPConsents cons : newUpp.getSubscribedOspPolicies()) {
            //System.out.println("Searching OSPConsents " + cons.getOspId() + " vs " + ospId);
            if(ospId.equals(cons.getOspId())) {
                //System.out.println("found OSPConsents " + cons.getOspId());
                ret = cons;
                break;
            }
        }
        return ret;
    }
    /*
    @Override
    public Response userPrivacyPolicyGet(String filter, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response userPrivacyPolicyPost(UserPrivacyPolicy upp, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response userPrivacyPolicyUserIdDelete(String userId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response userPrivacyPolicyUserIdGet(String userId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response userPrivacyPolicyUserIdPut(String userId, UserPrivacyPolicy upp, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
     */
}
