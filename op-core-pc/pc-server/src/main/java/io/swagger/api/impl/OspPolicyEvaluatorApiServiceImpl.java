/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2016
//
// Copyright in this library belongs to the University of Southampton
// University Road, Highfield, Southampton, UK, SO17 1BJ
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
// Created By : Paul Grace
// Created for Project : OPERANDO (http://www.operando.eu)
//
/////////////////////////////////////////////////////////////////////////
//
//  License : GNU Lesser General Public License, version 3
//
/////////////////////////////////////////////////////////////////////////

package io.swagger.api.impl;

//import eu.operando.OperandoModules;
import eu.operando.PolicyEvaluationService;
//import eu.operando.core.cas.client.api.DefaultApi;
import io.swagger.api.*;


import eu.operando.core.pdb.common.model.OSPDataRequest;
import io.swagger.model.PolicyEvaluationReport;

import java.util.List;
import java.util.Properties;

import io.swagger.api.NotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import io.swagger.client.api.LogApi;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

/**
 * Implementation of the Policy Evaluation API of the Policy Computation
 * Component. This is a single method, that evaluates a given set of access
 * requests to user data, that is evaluated against user preferences to
 * then provide feedback about whether the request should be granted or not.
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class OspPolicyEvaluatorApiServiceImpl extends OspPolicyEvaluatorApiService {

    /**
     * The API component uses other OPERANDO components [PDB, AAPI, LDB].
     * This stores the reference, so HTTP REST calls can be made to them.
     */
    private String PDB_UPP_URL = null;
    private String LDB_URL = null;
    private String AAPI_URL = null;

    /**
     * This service calls the Log DB to log evaluation calls on this module. This
     * is the client object for calling the Log DB.
     */
    LogApi logApi;
//
//    /**
//     * AAPI client. The service calls the AAPI module for authentication and
//     * authorization of usage of this API. This is the client object for calling the AAPI.
//     */
//    DefaultApi aapiClient;

    /**
     * Reference to the core implementation of this API
     */
    private final PolicyEvaluationService policyService;
//    private final OperandoModules helperMethods;

    /**
     * The configuration of local state for the API object. Simply creates
     * the reusable reference to the PDB.
     */
    public OspPolicyEvaluatorApiServiceImpl() {
        super();
        policyService = PolicyEvaluationService.getInstance();
	Properties props = loadDbProperties();

        if (props.getProperty("pdb.upp") != null) {
            PDB_UPP_URL = props.getProperty("pdb.upp");
        }
        if (props.getProperty("aapi.basepath") != null) {
            AAPI_URL = props.getProperty("aapi.basepath");
        }
        if (props.getProperty("ldb.baseurl") != null) {
            LDB_URL = props.getProperty("ldb.baseurl");
        }


        // setup aapi client
//        eu.operando.core.cas.client.ApiClient aapiDefaultClient = new eu.operando.core.cas.client.ApiClient();
//        aapiDefaultClient.setBasePath(AAPI_URL);
//        this.aapiClient = new DefaultApi(aapiDefaultClient);
//
//        // setup logdb client
//        ApiClient apiClient = new ApiClient();
//        apiClient.setBasePath(LDB_URL);
//
//        helperMethods = new OperandoModules();
//
//        // get service ticket for logdb service
//        String logdbST = helperMethods.getServiceTicket(uppLoginName, uppLoginPassword, logdbSId);
//        apiClient.addDefaultHeader(stHeaderName, logdbST);
//        this.logApi = new LogApi(apiClient);
    }

    /**
     * Load the configuration properties from the resource file in JAR/WAR and
     * turn then into JAVA properties class.
     * @return The list of JAVA properties reflecting the configuration.
     */
    private Properties loadDbProperties() {
        Properties props;
        props = new Properties();

        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream("/operando.properties");
            props.load(fis);
        }
        catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("Error reading Configuration properties file");

            // Add logging code to log an error configuring the API on startup
        }

        return props;
    }

    @Override
    /**
     * Implementation of the Evaluation method.
     * POST REST Method - pass information about the user, the service, and
     * the requests that the services wishes to carry out on the user's data.
     * Returns a report as a JSON object that lists all of the requests with
     * grant/deny reasons.
     * @param userId The OPERANDO unique user identifier.
     * @param ospId The OPERANDO unique service (OSP) identifier.
     * @param ospRequest The list of data requests by the OSP on this user data.
     * @return The policy evaluation report.
     */
    public Response ospPolicyEvaluatorPost(String userId, String ospId, List<OSPDataRequest> ospRequest, SecurityContext securityContext)
            throws NotFoundException {

        if((userId==null)||(ospId==null)){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        /**
         * Check the ST in the request. Only valid tickets can call the evaluation API.
         */

        /**
         * Log the request in the LogDB
         */
        logRequest(userId, ospId,ospRequest.get(0).getRequesterId());

        /**
         * Carry out the evaluation
         */
        PolicyEvaluationReport rp = policyService.evaluate(ospId, userId, ospRequest, PDB_UPP_URL);

        /**
         * Add to the notification API to inform the user of the evaluation.
         */

        /**
         * Send the evaluation report
         */
         return Response.ok(rp.toString(), MediaType.APPLICATION_JSON).build();
    }

    @Override
    /**
     * Implementation of the Evaluation method.
     * POST REST Method - pass information about the user, the service, and
     * the requests that the services wishes to carry out on the user's data.
     * Returns a report as a JSON object that lists all of the requests with
     * grant/deny reasons.
     * @param userId The OPERANDO unique user identifier.
     * @param ospId The OPERANDO unique service (OSP) identifier.
     * @param ospRequest The list of data requests by the OSP on this user data.
     * @return The policy evaluation report.
     */
    public Response ospPolicyBatchEvaluatorPost(String ospId, List<OSPDataRequest> ospRequest, SecurityContext securityContext)
            throws NotFoundException {

        if(ospId==null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        /**
         * Check the ST in the request. Only valid tickets can call the evaluation API.
         */

        /**
         * Log the request in the LogDB
         */

        String uppsURL = "http://integration.operando.esilab.org:8096/operando/core/pdb/user_privacy_policy/?filter=%7B%27subscribed_osp_policies.osp_id%27:%27"+ospId+"%27%7D";
        String uppProfile = null;
        try {

            /**
             * Get the UPP from the PDB.
             */
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(uppsURL);
            CloseableHttpResponse response1 = httpclient.execute(httpget);

            /**
             * If there is no UPP, then it returns an non-compliance report
             * with a NO_POLICY statement.
             */
            HttpEntity entity = response1.getEntity();
            if(response1.getStatusLine().getStatusCode()==404) {
                return null;
            }
            uppProfile = EntityUtils.toString(entity);
            httpclient.close();
            response1.close();
            httpget.releaseConnection();

        } catch (IOException ex) {
            return null;
        }

        /**
         * Carry out the evaluation
         */
        String rp = policyService.batchEvaluate(ospId, ospRequest, uppProfile);

        /**
         * Add to the notification API to inform the user of the evaluation.
         */

        /**
         * Send the evaluation report
         */
         return Response.ok(rp, MediaType.APPLICATION_JSON).build();
    }

    private void logRequest(String userid, String ospId, String requestID) {
        try {
            System.out.println("Logging - " + userid);
            StringEntity msgBody = new StringEntity("{\n" +
                    "  \"userId\": \" "+ userid + "\",\n" +
                    "  \"requesterType\": \"PROCESS\",\n" +
                    "  \"requesterId\": \"string\",\n" +
                    "  \"logPriority\": \"LOW\",\n" +
                    "  \"logLevel\": \"INFO\",\n" +
                    "  \"title\": \" PC Eval \",\n" +
                    "  \"description\": \" Logging:" +userid +" OSP: "+ ospId + " is requesting with requesting user id: "+ requestID+ "\",\n" +
                    "  \"keywords\": [\n" +
                    "    \"string\"\n" +
                    "  ],\n" +
                    "  \"logType\": \"DATA_ACCESS\",\n" +
                    "  \"affectedUserId\": \"string\"\n" +
                    "}");

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost post = new HttpPost(LDB_URL);
            post.addHeader("Content-Type", "application/json");
            post.setEntity(msgBody);
            CloseableHttpResponse response1 = httpclient.execute(post);
            System.out.println("Logging response - " + response1.getEntity().toString());
        } catch (IOException ex) {
            Logger.getLogger(OspPolicyEvaluatorApiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
