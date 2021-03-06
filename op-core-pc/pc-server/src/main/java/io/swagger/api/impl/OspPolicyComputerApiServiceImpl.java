/////////////////////////////////////////////////////////////////////////
//
// � University of Southampton IT Innovation Centre, 2016
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import eu.operando.PolicyEvaluationService;
//import eu.operando.core.cas.client.api.DefaultApi;
import eu.operando.core.pdb.common.model.AccessPolicy;
import io.swagger.api.*;
import io.swagger.model.UserPreference;

import java.util.List;
import io.swagger.api.NotFoundException;
import eu.operando.core.pdb.common.model.OSPConsents;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;
import eu.operando.core.pdb.common.model.UserPrivacyPolicy;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import net.minidev.json.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

//import io.swagger.client.api.LogApi;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class OspPolicyComputerApiServiceImpl extends OspPolicyComputerApiService {

    /**
     * The API component only uses one other OPERANDO component the policy database.
     * This stores the reference, so HTTP REST calls can be made.
     */
    public static String PDB_BASEURL = null;

    /**
     * Reference to the core implementation of this API
     */
    private final PolicyEvaluationService policyService;

    /**
     * The configuration of local state for the API object. Simply creates
     * the reusable reference to the PDB.
     */
    public OspPolicyComputerApiServiceImpl() {
        super();
	Properties props = loadDbProperties();
        policyService = PolicyEvaluationService.getInstance();
    }

    /**
     * Query for an OSP policy using a friendly keyword e.g. foodcoach versus
     * the ID 4534534.
     *
     * @param friendlyName The keywords to search for.
     * @return The Operando ospID for the OSP with this friendly data.
     */
    public static String ospQuerybyFriendlyName(String friendlyName, String URL) {
        Client client = new Client();
        String ospAPI = URL + "/?filter=%7B%27policy_url%27:%27"+friendlyName+"%27%7D";
        System.out.println(ospAPI);
        WebResource webResourcePDB = client.resource(ospAPI);
        ClientResponse policyResponse = webResourcePDB.type("application/json").get(ClientResponse.class);
        if(policyResponse.getStatus() != 200) {
            return null;
        }
        String filterResults = policyResponse.getEntity(String.class);
        JSONArray access_policies = JsonPath.read(filterResults, "$..[?(@.policy_url=='" + friendlyName + "')]");
        for(Object aP: access_policies) {
            String id = JsonPath.read(aP, "$.osp_policy_id");
            if(id != null)
                return id;
        }
        return null;
    }

    /**
     * Load the configuration properties from the resource file in JAR/WAR and
     * turn then into JAVA properties class.
     * @return The list of JAVA properties reflecting the configuration.
     */
    private Properties loadDbProperties() {
        Properties props = new Properties();

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

        if (props.getProperty("pdb.baseurl") != null) {
            PDB_BASEURL = props.getProperty("pdb.baseurl");
        }

        return props;
    }

    @Override
    public Response ospPolicyComputerPost(String userId, String ospId, List<UserPreference> ospPrefs, SecurityContext securityContext)
    throws NotFoundException {

        try {

            String currentUpp = null;
            String ospNumericId = ospQuerybyFriendlyName(ospId, PDB_BASEURL);
            System.out.println("userid: " + userId + " ospId: "+ ospId + "PDB: " + PDB_BASEURL);
            if(userId.startsWith("_demo")) {
                currentUpp = policyService.getUPP(userId);
            }
            else {
                try {
                    /**
                     * Get the UPP from the PDB.
                     */
                    CloseableHttpClient httpclient = HttpClients.createDefault();
                    HttpGet httpget = new HttpGet(PDB_BASEURL + "/user_privacy_policy/" + userId);
                    CloseableHttpResponse response1 = httpclient.execute(httpget);

                    /**
                     * If there is no UPP, then create a new UPP
                     */
                    HttpEntity entity = response1.getEntity();
                    System.out.println(response1.getStatusLine().getStatusCode());
                    if(response1.getStatusLine().getStatusCode()==404) {
                        currentUpp = null;
                    } else {
                        currentUpp = EntityUtils.toString(entity);
                    }

                } catch (IOException ex) {
                    return Response.status(400).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "UserId doesn't exist")).build();
                }
            }
            // Create a subscribed policy statement and store it in the UPP
            ObjectMapper mapper = new ObjectMapper();
            UserPrivacyPolicy uppProfile = null;
            if(currentUpp != null) {
                 uppProfile = mapper.readValue(currentUpp, UserPrivacyPolicy.class);
            }
            else {
                uppProfile = new UserPrivacyPolicy();
                uppProfile.setUserId(userId);
            }
            List<OSPConsents> subscribedOspPolicies = uppProfile.getSubscribedOspPolicies();
            System.out.println("Number of polices for this OSP: " + subscribedOspPolicies.size());

            // Get the access policies for each of the OSP statments
            /**
             * Get the OSP from the PDB.
             */
            String currentOSP = null;
            try {
                /**
                 * Get the OSP from the PDB.
                 */
                CloseableHttpClient httpclient = HttpClients.createDefault();
                String ospURL = PDB_BASEURL + "/OSP/" + ospNumericId;
                HttpGet httpget = new HttpGet(ospURL);
                CloseableHttpResponse response1 = httpclient.execute(httpget);

                /**
                 * If there is no OSP, then complete fail.
                 */
                HttpEntity entity = response1.getEntity();
                System.out.println(response1.getStatusLine().getStatusCode());
                if(response1.getStatusLine().getStatusCode()==404) {
                    return Response.status(400).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "OSP doesn't exist")).build();
                }
                currentOSP = EntityUtils.toString(entity);
                System.out.println(currentOSP);
            } catch (IOException ex) {
                return Response.status(400).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "OSP doesn't exist")).build();
            }

            OSPPrivacyPolicy ospPolicy = mapper.readValue(currentOSP, OSPPrivacyPolicy.class);
            List<AccessPolicy> policies = ospPolicy.getPolicies();
            System.out.println("Number of policies: " + policies.size());
            OSPConsents sConsents = new OSPConsents();
            sConsents.setOspId(ospId);
            sConsents.setAccessPolicies(policies);
            subscribedOspPolicies.add(sConsents);
            uppProfile.setSubscribedOspPolicies(subscribedOspPolicies);

            System.out.println("\nNew UPP : " + uppProfile.toString() + "\n");

            /**
             * Update the UPP
             */
            try {
                /**
                 * Get the UPP from the PDB.
                 */
                Client client = new Client();
                WebResource webResourcePDB = client.resource(PDB_BASEURL + "/user_privacy_policy/" + userId);
                ClientResponse policyResponse = webResourcePDB.type("application/json").get(ClientResponse.class);
                if (policyResponse.getStatus() != 200) {
                    WebResource webResourcePDB2 = client.resource(PDB_BASEURL + "/user_privacy_policy/");
                    policyResponse = webResourcePDB2.type("application/json").post(ClientResponse.class,
                        uppProfile.toString());

                    if (policyResponse.getStatus() != 201) {
                        throw new RuntimeException("Failed : HTTP error code : " + policyResponse.toString());
                    }
                }
                else {
                    policyResponse = webResourcePDB.type("application/json").put(ClientResponse.class,
                           uppProfile.toString());

                   /**
                    * If there is no UPP, then complete fail.
                    */
                   if(policyResponse.getStatus()==404) {
                       return Response.status(400).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "User doesn't exist")).build();
                   }
                }
                return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "UPP updated for OSP: ")).build();
            } catch (UniformInterfaceException | ClientHandlerException ex) {
                System.err.println("error - " + ex.getMessage());
                return Response.status(400).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Compute failed")).build();
            }

        } catch (IOException ex) {
            System.err.println("error - " + ex.getMessage());
            Logger.getLogger(OspPolicyComputerApiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(400).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Compute failed")).build();
        }
    }
}
