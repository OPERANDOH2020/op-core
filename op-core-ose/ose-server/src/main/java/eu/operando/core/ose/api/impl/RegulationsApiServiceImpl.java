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



import io.swagger.api.NotFoundException;
import eu.operando.core.cas.client.api.DefaultApi;
import eu.operando.core.cas.client.model.UserCredential;
import eu.operando.core.ose.mongo.RegulationsMongo;
import eu.operando.core.ose.services.RegulationWorkflow;
import eu.operando.core.pdb.common.model.PrivacyRegulation;
import eu.operando.core.pdb.common.model.PrivacyRegulationInput;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.RegulationsApiService;
import io.swagger.client.api.LogApi;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.model.LogRequest;
import io.swagger.client.model.LogRequest.LogLevelEnum;
import io.swagger.client.model.LogRequest.LogPriorityEnum;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Timer;
import java.util.TimerTask;
import javax.ws.rs.core.HttpHeaders;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-25T15:37:02.222Z")
public class RegulationsApiServiceImpl extends RegulationsApiService {

    // LogDB
    LogApi logApi;
    // AAPI
    DefaultApi aapiClient;

    String oseRegSId = "ose/osps/.*";
    String logdbSId = "ose/osps/.*";
    String aapiBasePath = "http://integration.operando.esilab.org:8135/operando/interfaces/aapi";
    String logdbBasePath = "http://integration.operando.esilab.org:8090/operando/core/ldb";
    String regLoginName = "xxxxx";
    String regLoginPassword = "xxxxx";
    String stHeaderName = "Service-Ticket";
    String logdbST = "";
    long ticketLifeTime = 1000L * 60 * 60;

    String mongoServerHost = "localhost";
    int mongoServerPort = 27017;
    RegulationsMongo ospsMongodb = null;
    Timer timer;

    Properties prop = null;
    
    public RegulationsApiServiceImpl() {
//        this.apiClient = new ApiClient();
//        this.apiClient.setBasePath("http://integration.operando.esilab.org:8090/operando/core/ldb");
//        this.logApi = new LogApi(this.apiClient);
    
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
                Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.INFO, "reg TIMER RUN now");
                logdbST = getServiceTicket(regLoginName, regLoginPassword, logdbSId);
                apiClient.addDefaultHeader(stHeaderName, logdbST);
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, ticketLifeTime);

        // get service ticket for logdb service
        logdbST = getServiceTicket(regLoginName, regLoginPassword, logdbSId);
        apiClient.addDefaultHeader(stHeaderName, logdbST);
        this.logApi = new LogApi(apiClient);

        // setup mongo part
        //ospsMongodb = new RegulationsMongo(mongoServerHost, mongoServerPort);
    }

    private void loadParams() {
        // setup aapi client
        if (prop.getProperty("aapi.basepath") != null) {
            aapiBasePath = prop.getProperty("aapi.basepath");
        }

        // setup logdb client
        if (prop.getProperty("logdb.basepath") != null) {
            logdbBasePath = prop.getProperty("logdb.basepath");
        }

        // get service ticket for logdb service
        if (prop.getProperty("ose.regulations.service.login") != null) {
            regLoginName = prop.getProperty("ose.regulations.service.login");
        }
        if (prop.getProperty("ose.regulations.service.password") != null) {
            regLoginPassword = prop.getProperty("ose.regulations.service.password");
        }
        if (prop.getProperty("logdb.service.id") != null) {
            logdbSId = prop.getProperty("logdb.service.id");
        }

        // setup mongo part
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
            System.out.println("ose osps service TGT: " + tgt);
            st = aapiClient.aapiTicketsTgtPost(tgt, serviceId);
            System.out.println("logdb osps service ticket: " + st);

        } catch (eu.operando.core.cas.client.ApiException ex) {
            ex.printStackTrace();
        }
        return st;
    }

    private boolean aapiTicketsStValidateGet(String st) {
        try {
            aapiClient.aapiTicketsStValidateGet(st, oseRegSId);
        } catch (eu.operando.core.cas.client.ApiException ex) {
            ex.printStackTrace();
        }
        return false;
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
                    aapiClient.aapiTicketsStValidateGet(st, oseRegSId);
                    Logger.getLogger(RegulationsApiServiceImpl.class.getName()).log(Level.INFO,
                            "Service Ticket validation succeeded");
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
            LogLevelEnum logDataType, LogPriorityEnum logPriority,
            ArrayList<String> keywords) {

        ArrayList<String> words = new ArrayList<String>(Arrays.asList("OSE", "Regulations"));
        for(String word : keywords) {
            words.add(word);
        }

        LogRequest logRequest = new LogRequest();
        logRequest.setUserId("OSE-Regulations");
        logRequest.setDescription(description);
        logRequest.setLogLevel(logDataType);
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

        //Debugging code
        System.out.println("Regulation Post API called: " + regulation.getRegId());

        Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.INFO, "upp regulations POST privacy regulation {0}", regulation.toString());

        logRequest("regulationsPost", "Regulation: ".concat(regulation.getRegId()),
                "OSE regulations POST received",
                LogLevelEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        RegulationWorkflow services = new RegulationWorkflow();
        String storeAction = services.addRegulation(regulation);
        if (storeAction == null) {

//            logRequest("regulationsPost", "Regulation".concat(regulation.getRegId()),
//                    "OSE regulations POST failed",
//                    LogLevelEnum.ERROR, LogPriorityEnum.HIGH,
//                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(409).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error occured. The resource already exists, so a new resource cannot be created.")).build();
        }

        logRequest("regulationsPost", "Regulation".concat(regulation.getRegId()),
                "OSE regulations POST complete",
                LogRequest.LogLevelEnum.INFO, LogRequest.LogPriorityEnum.NORMAL,
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
                LogLevelEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        RegulationsMongo regMongo = new RegulationsMongo();
        boolean updateAction = regMongo.updateRegulation(regId, regulation);

        if (!updateAction) {

            logRequest("Regulations PUT", "PUT",
                    "PDB regulations PUT failed",
                    LogLevelEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exists to be updated.")).build();
        }

        logRequest("Regulations PUT", "PUT",
                "PDB regulations PUT complete",
                LogLevelEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.status(Response.Status.OK).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "Successful response. The regulation has been added.")).build();
    }

}
