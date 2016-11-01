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

import com.jayway.jsonpath.JsonPath;
import eu.operando.InvalidPreferenceException;
import eu.operando.XSDParser;
import io.swagger.api.*;

import io.swagger.model.OSPDataRequest;
import io.swagger.model.PolicyEvaluationReport;

import java.util.List;
import java.util.Properties;

import io.swagger.api.NotFoundException;
import io.swagger.model.RequestEvaluation;
import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import net.minidev.json.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Implementation of the Policy Evaluation API of the Policy Computation
 * Component. This is a single method, that evaluates a given set of access
 * requests to user data, that is evaluated against user preferences to
 * then provide feedback about whether the request should be granted or not.
 *
 */

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class OspPolicyEvaluatorApiServiceImpl extends OspPolicyEvaluatorApiService {

    /**
     * The API component only uses one other OPERANDO component the policy database.
     * This stores the reference, so HTTP REST calls can be made.
     */
    private String PDB_BASEURL = null;

    /**
     * The configuration of local state for the API object. Simply creates
     * the reusable referece to the PDB.
     */
    public OspPolicyEvaluatorApiServiceImpl() {
        super();

	Properties props;
    	props = loadDbProperties();

    	PDB_BASEURL = props.getProperty("pdb.baseurl");
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

        try {
            System.out.println("New Evaluation Request");
            System.out.println("--------------------------------------------------");
            System.out.println("Evaluating User Policy: " + userId);
            System.out.println("Request from: " + ospId);

            /**
             * The response to be sent - yes/no along with a report of why something
             * has been denied.
             */
            PolicyEvaluationReport rp = new PolicyEvaluationReport();

            /**
             * Get the UPP from the PDB.
             */
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(PDB_BASEURL + userId);
            CloseableHttpResponse response1 = httpclient.execute(httpget);

            /**
             * If there is no UPP, then it returns an non-compliance report
             * with a NO_POLICY statement.
             */
            HttpEntity entity = response1.getEntity();
            System.out.println(response1.getStatusLine().getStatusCode());
            if(response1.getStatusLine().getStatusCode()==404) {
                rp.setStatus("false");
                rp.setCompliance("NO_POLICY");
                return Response.ok(rp.toString(), MediaType.APPLICATION_JSON).build();
            }
            String msg = EntityUtils.toString(entity);
            System.out.println(msg);

            boolean permit = true;
            /**
             * Evaluate the oData field request against the UPP user access policies
             */
            for (OSPDataRequest r: ospRequest) {
                String oDataURL = r.getRequestedUrl();
                String Category = XSDParser.getElementDataType(oDataURL);
                System.out.println("OSP - "+ospId+" Category - "+Category);
                JSONArray access_policies = JsonPath.read(msg, "$.subscribed_osp_policies[?(@.osp_id=='"+ospId+"')].access_policies[?(@.resource=='"+ Category +"')]");
                boolean found = false;
                // For each of the access requests in the list
                for(Object aP: access_policies) {
                    String subject = JsonPath.read(aP, "$.subject");
                    System.out.println("Data user in request - " + subject);
                    System.out.println("Data user in the UPP - " + r.getSubject());
                    if(subject.equalsIgnoreCase(r.getSubject())) { // Check the subject
                        found=true;

                        if (JsonPath.read(aP, "$.action").toString().equalsIgnoreCase(r.getAction().name())){ // Check the action
                            boolean perm = JsonPath.read(aP, "$.permission");
                            RequestEvaluation rEv = new RequestEvaluation();
                                rEv.setDatauser(r.getSubject());
                                rEv.setDatafield(oDataURL);
                                rEv.setAction(r.getAction().name());
                            if(!perm) {
                                rEv.setResult(false);
                                rp.addEvaluationsItem(rEv);
                            }
                            else{
                                rEv.setResult(true);
                                rp.addEvaluationsItem(rEv);
                            }
                        }
                        else {
                            permit = false;
                            RequestEvaluation rEv = new RequestEvaluation();
                                rEv.setDatauser(r.getSubject());
                                rEv.setDatafield(oDataURL);
                                rEv.setAction(r.getAction().name());
                                rEv.setResult(false);
                                rp.addEvaluationsItem(rEv);
                        }
                    }
                }
                if(!found){
                    permit = false;
                    RequestEvaluation rEv = new RequestEvaluation();
                                rEv.setDatauser(r.getSubject());
                                rEv.setDatafield(oDataURL);
                                rEv.setAction(r.getAction().name());
                                rEv.setResult(false);
                                rp.addEvaluationsItem(rEv);
                }
            }

            if(permit) {
                rp.setStatus("true");
                rp.setCompliance("VALID");
            }
            else {
                rp.setStatus("false");
                rp.setCompliance("PREFS_CONFLICT");
            }

            String policyReport = rp.toString();
            System.out.println(policyReport);
            return Response.ok(policyReport, MediaType.APPLICATION_JSON).build();
        } catch (IOException ex) {
            return Response.serverError().build();
        }   catch (InvalidPreferenceException ex) {
                return Response.serverError().build();
            }
    }
}
