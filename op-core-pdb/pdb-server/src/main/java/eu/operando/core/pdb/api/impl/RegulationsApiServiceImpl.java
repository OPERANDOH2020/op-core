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
import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;

import io.swagger.api.RegulationsApiService;

import eu.operando.core.pdb.common.model.PrivacyRegulationInput;
import eu.operando.core.pdb.mongo.RegulationsMongo;
import io.swagger.client.ApiClient;
import io.swagger.client.api.LogApi;
import io.swagger.client.model.LogRequest;
import io.swagger.client.ApiException;
import io.swagger.client.model.LogRequest.LogDataTypeEnum;
import io.swagger.client.model.LogRequest.LogPriorityEnum;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import eu.operando.core.cas.client.api.DefaultApi;
import eu.operando.core.cas.client.model.UserCredential;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.HttpHeaders;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-20T12:05:17.950Z")
public class RegulationsApiServiceImpl extends RegulationsApiService {

    // LogDB
    LogApi logApi;
    // AAPI
    DefaultApi aapiClient;

    String pdbRegSId = "pdb/regulations/.*";
    String logdbSId = "ose/osps/.*";
    String aapiBasePath = "http://integration.operando.esilab.org:8135/operando/interfaces/aapi";
    String logdbBasePath = "http://integration.operando.esilab.org:8090/operando/core/ldb";
    String regLoginName = "xxxxx";
    String regLoginPassword = "xxxxx";
    String stHeaderName = "Service-Ticket";

    String mongoServerHost = "localhost";
    int mongoServerPort = 27017;
    RegulationsMongo ospMongodb = null;

    Properties prop = null;

    public RegulationsApiServiceImpl() {
        super();
        //  get service config params
        prop = loadServiceProperties();
        loadParams();

        // setup aapi client
        eu.operando.core.cas.client.ApiClient aapiDefaultClient = new eu.operando.core.cas.client.ApiClient();
        aapiDefaultClient.setBasePath(aapiBasePath);
        this.aapiClient = new DefaultApi(aapiDefaultClient);

        // setup logdb client
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(logdbBasePath);

        // get service ticket for logdb service
        String logdbST = getServiceTicket(regLoginName, regLoginPassword, logdbSId);
        apiClient.addDefaultHeader(stHeaderName, logdbST);
        this.logApi = new LogApi(apiClient);

        // setup mongo part
        ospMongodb = new RegulationsMongo(mongoServerHost, mongoServerPort);
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
        if (prop.getProperty("pdb.reg.service.login") != null) {
            regLoginName = prop.getProperty("pdb.reg.service.login");
        }
        if (prop.getProperty("pdb.reg.service.password") != null) {
            regLoginPassword = prop.getProperty("pdb.reg.service.password");
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
            System.out.println("pdb reg service TGT: " + tgt);
            st = aapiClient.aapiTicketsTgtPost(tgt, serviceId);
            System.out.println("logdb reg service ticket: " + st);

        } catch (eu.operando.core.cas.client.ApiException ex) {
            ex.printStackTrace();
        }
        return st;
    }

    private void logRequest(String requesterId, String title, String description,
            LogDataTypeEnum logDataType, LogPriorityEnum logPriority,
            ArrayList<String> keywords) {

        ArrayList<String> words = new ArrayList<String>(Arrays.asList("PDB", "Regulations"));
        for (String word : keywords) {
            words.add(word);
        }
        LogRequest logRequest = new LogRequest();
        logRequest.setUserId("PDB-Regulations");
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

    private boolean aapiTicketsStValidateGet(String st) {
        try {
            aapiClient.aapiTicketsStValidateGet(st, pdbRegSId);
            return true;
        } catch (eu.operando.core.cas.client.ApiException ex) {
            ex.printStackTrace();
            return false;
        }
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
                    aapiClient.aapiTicketsStValidateGet(st, pdbRegSId);
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

    @Override
    public Response regulationsGet(String filter, SecurityContext securityContext, HttpHeaders headers)
            throws NotFoundException {
        Logger.getLogger(RegulationsApiServiceImpl.class.getName()).log(Level.INFO, "regulations GET {0}", filter);

        if (!validateHeaderSt(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The service ticket failed to validate.")).build();
        }

        logRequest("regulations GET", "GET",
                "regulations GET received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        String regList = ospMongodb.getRegulationByFilter(filter);

        if (regList == null) {
            logRequest("regulations GET", "GET",
                    "regulations GET failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the regulation does not exist")).build();
        }
        logRequest("regulations GET", "GET",
                "regulations GET ok",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok(regList, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response regulationsPost(PrivacyRegulationInput regulation, SecurityContext securityContext, HttpHeaders headers)
            throws NotFoundException {
        Logger.getLogger(RegulationsApiServiceImpl.class.getName()).log(Level.INFO, "regulations POST {0}", regulation);

        if (!validateHeaderSt(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The service ticket failed to validate.")).build();
        }

        logRequest("regulations POST", "POST",
                "regulations POST received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        if (regulation.getLegislationSector() == null) {
            Logger.getLogger(RegulationsApiServiceImpl.class.getName()).log(Level.INFO, "regulations has empty legislation sector");
            return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The document (PrivacyRegulation) does not provide legislation_sector.")).build();
        }

        String regId = ospMongodb.storeRegulation(regulation);
        String storedReg = ospMongodb.getRegulationById(regId);
        
        if (storedReg == null) {

            logRequest("regulations POST", "POST",
                    "regulations POST failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(405).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The document (PrivacyRegulation) at this id has previously been created in the database.")).build();
        }

        logRequest("regulations POST", "POST",
                "regulations POST ok",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        Logger.getLogger(RegulationsApiServiceImpl.class.getName()).log(Level.INFO, "regulations POST stored id: {0}", storedReg);

        Response resp = null;
        try {
            URI createdURI = new URI("http://integration.operando.esilab.org:8096/operando/core/pdb/regulations/" + regId);
            resp = Response.created(createdURI).entity(storedReg).build();
        } catch (URISyntaxException ex) {
            Logger.getLogger(OSPApiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resp;
    }

    @Override
    public Response regulationsRegIdDelete(String regId, SecurityContext securityContext, HttpHeaders headers)
            throws NotFoundException {
        Logger.getLogger(RegulationsApiServiceImpl.class.getName()).log(Level.INFO, "regulations DELETE {0}", regId);

        if (!validateHeaderSt(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The service ticket failed to validate.")).build();
        }

        logRequest("regulations DELETE", "DELETE",
                "regulations DELETE received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        boolean delAction = ospMongodb.deleteRegulationById(regId);

        if (!delAction) {

            System.out.println("cannot delete regulation " + regId);

            logRequest("regulations DELETE", "DELETE",
                    "regulations DELETE failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exits to be deleted")).build();
        }

        logRequest("regulations DELETE", "DELETE",
                "regulations DELETE ok",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (PrivacyRegulation) was successfully deleted from the database.")).build();
    }

    @Override
    public Response regulationsRegIdGet(String regId, SecurityContext securityContext, HttpHeaders headers)
            throws NotFoundException {

        Logger.getLogger(RegulationsApiServiceImpl.class.getName()).log(Level.INFO, "regulations GET {0}", regId);

        if (!validateHeaderSt(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The service ticket failed to validate.")).build();
        }

        logRequest("regulations GET", "GET",
                "regulations GET received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        String prString = ospMongodb.getRegulationById(regId);

        if (prString == null) {

            logRequest("regulations GET", "GET",
                    "regulations GET failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));

            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error - the regulation does not exist")).build();
        }

        logRequest("regulations GET", "GET",
                "regulations GET ok",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.ok(prString, MediaType.APPLICATION_JSON).build();
    }

    @Override
    public Response regulationsRegIdPut(String regId, PrivacyRegulationInput regulation, SecurityContext securityContext, HttpHeaders headers)
            throws NotFoundException {

        Logger.getLogger(RegulationsApiServiceImpl.class.getName()).log(Level.INFO, "regulations PUT {0}", regId);

        if (!validateHeaderSt(headers)) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. The service ticket failed to validate.")).build();
        }

        logRequest("regulations PUT", "PUT",
                "regulations PUT received",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        boolean updateAction = ospMongodb.updateRegulation(regId, regulation);

        if (!updateAction) {
            logRequest("regulations PUT", "PUT",
                    "regulations PUT failed",
                    LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                    new ArrayList<String>(Arrays.asList("one", "two")));
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR,
                    "Error. No document exists to be updated.")).build();
        }

        logRequest("regulations PUT", "PUT",
                "regulations PUT ok",
                LogDataTypeEnum.INFO, LogPriorityEnum.NORMAL,
                new ArrayList<String>(Arrays.asList("one", "two")));

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "The document (PrivacyRegulation) was successfully updated in the database.")).build();
    }

    /*
    @Override
    public Response regulationsGet(String filter, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response regulationsPost(PrivacyRegulationInput regulation, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response regulationsRegIdDelete(String storedReg, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response regulationsRegIdGet(String storedReg, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response regulationsRegIdPut(String storedReg, PrivacyRegulationInput regulation, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
     */
}
