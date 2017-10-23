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

package eu.operando.core.ose.services;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import eu.operando.core.cas.client.api.DefaultApi;
import eu.operando.core.cas.client.model.UserCredential;
import eu.operando.core.ose.api.impl.OspsApiServiceImpl;
import eu.operando.core.ose.api.impl.RegulationsApiServiceImpl;
import io.swagger.client.ApiException;
import io.swagger.client.api.LogApi;
import io.swagger.client.model.LogRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * Set of reusable OSE methods across the four API implementations e.g. logging,
 * authentication, etc.
 */
public class HelperMethods {

    /**
     * Get a service ticket from the CAS server
     * @param username
     * @param password
     * @param serviceId
     * @return
     */
    public String getServiceTicket(DefaultApi aapiClient, String username, String password, String serviceId) {
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

    public boolean aapiTicketsStValidateGet(DefaultApi aapiClient, String st, String serviceID) {
        try {
            aapiClient.aapiTicketsStValidateGet(st, serviceID);
        } catch (eu.operando.core.cas.client.ApiException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean validateHeaderSt(HttpHeaders headers) {
        return true;
    }

    public boolean validateHeaderSt1(DefaultApi aapiClient, HttpHeaders headers, String stHeaderName, String serviceID) {
        if (headers != null) {
            List<String> stHeader = headers.getRequestHeader(stHeaderName);
            if (stHeader != null) {
                String st = stHeader.get(0);
                try {
                    aapiClient.aapiTicketsStValidateGet(st, serviceID);
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

    public void logRequest(LogApi logApi, String requesterId, String title, String description,
            LogRequest.LogLevelEnum logDataType, LogRequest.LogPriorityEnum logPriority,
            ArrayList<String> keywords) {

        ArrayList<String> words = new ArrayList<String>(Arrays.asList("OSE", "OSP"));
        for (String word : keywords) {
            words.add(word);
        }

        LogRequest logRequest = new LogRequest();
        logRequest.setUserId("OSE-OSP");
        logRequest.setDescription(description);
        logRequest.setLogLevel(logDataType);
        logRequest.setTitle(title);
        logRequest.setLogPriority(logPriority);
        logRequest.setRequesterId(requesterId);
        logRequest.setRequesterType(LogRequest.RequesterTypeEnum.PROCESS);
        logRequest.setKeywords(words);

        try {
            String response = logApi.lodDB(logRequest);
            Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.INFO, response);

        } catch (ApiException ex) {
            Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.SEVERE, "failed to log", ex);
        }
    }

    /**
     * Send a log message to the LogDB component.
     */
    public void logUserRequest(LogApi logApi, String requesterId, String title, String description,
            LogRequest.LogLevelEnum logLevel, LogRequest.LogPriorityEnum logPriority, LogRequest.LogTypeEnum logType,
            String affectedId, ArrayList<String> keywords) {

        ArrayList<String> words = new ArrayList<String>(Arrays.asList("PDB", "UPP"));
        for (String word : keywords) {
            words.add(word);
        }

        LogRequest logRequest = new LogRequest();
        logRequest.setUserId("OSE-Policy");
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
            String response = logApi.lodDB(logRequest);
            Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.INFO, response + logRequest.toString());
        } catch (ApiException ex) {
            Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.SEVERE, "failed to log", ex);
        }
    }

    /**
     * Load the properties from the server configuration file.
     * @return The parameters as Java properties.
     */
    public Properties loadServiceProperties() {
        Properties props;
        props = new Properties();

        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream("/service.properties");
            props.load(is);
        } catch (IOException e) {
            System.err.println("Error reading Configuration service properties file" + e.getLocalizedMessage());
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                System.err.println("Error reading Configuration service properties file" + ex.getLocalizedMessage());
            }
        }
        return props;
    }

    /**
     * Method to notify users via e-mail of a message. Uses the mailgun API.
     * @param Subject The msg subject line
     * @param email The target email address
     * @param Body The content of the message
     * @return Indication if the email was sent successfully.
     */
    private boolean sendEmail(String Subject, String email, String Body) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", "key-f28ca9730862959738de8b244678e4f9"));

        WebResource webResource = client.resource("https://api.mailgun.net/v3/mg.operando.eu/messages");

        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "privacy@operando.eu");
        formData.add("to", email);
        formData.add("subject", Subject);
        formData.add("html", Body);

        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
        String output = clientResponse.getEntity(String.class);

        return true;
    }

}
