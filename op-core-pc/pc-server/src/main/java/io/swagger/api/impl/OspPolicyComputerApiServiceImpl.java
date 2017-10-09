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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import eu.operando.HelperMethods;
import eu.operando.PolicyComputerService;
import eu.operando.PolicyEvaluationService;
import eu.operando.UnknownOSPException;
import eu.operando.UnknownUserException;
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

//import io.swagger.client.api.LogApi;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class OspPolicyComputerApiServiceImpl extends OspPolicyComputerApiService {

    /**
     * The API component only uses one other OPERANDO component the policy database.
     * This stores the reference, so HTTP REST calls can be made.
     */
    public static String PDB_BASEURL = null;
    public static String UPP_BASEURL = null;
    public static String OSP_BASEURL = null;

    public HelperMethods helpMethods;

//    // LogDB endpoint
//    LogApi logApi;
//    // AAPI
//    DefaultApi aapiClient;

    /**
     * Reference to the core implementation of this API
     */
    private final PolicyEvaluationService policyService;
    private final PolicyComputerService policyCompute;

    /**
     * The configuration of local state for the API object. Simply creates
     * the reusable reference to the PDB.
     */
    public OspPolicyComputerApiServiceImpl() {
        super();

        helpMethods = new HelperMethods();
	loadDbProperties();
        policyService = PolicyEvaluationService.getInstance();
        policyCompute = new PolicyComputerService();

        // setup aapi client
//        eu.operando.core.cas.client.ApiClient aapiDefaultClient = new eu.operando.core.cas.client.ApiClient();
//        aapiDefaultClient.setBasePath(aapiBasePath);
//        this.aapiClient = new DefaultApi(aapiDefaultClient);
//
//        // setup logdb client
//        ApiClient apiClient = new ApiClient();
//        apiClient.setBasePath(logdbBasePath);
//
//        // get service ticket for logdb service
//        String logdbST = getServiceTicket(uppLoginName, uppLoginPassword, logdbSId);
//        apiClient.addDefaultHeader(stHeaderName, logdbST);
//        this.logApi = new LogApi(apiClient);
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
        if (props.getProperty("pdb.upp") != null) {
            UPP_BASEURL = props.getProperty("pdb.upp");
        }
        if (props.getProperty("pdb.osp") != null) {
            OSP_BASEURL = props.getProperty("pdb.osp");
        }

        // load aapi client params
//        if (props.getProperty("aapi.basepath") != null) {
//            aapiBasePath = prop.getProperty("aapi.basepath");
//        }
//
//        // load logdb client params
//        if (props.getProperty("logdb.basepath") != null) {
//            logdbBasePath = prop.getProperty("logdb.basepath");
//        }

        return props;
    }

    /**
     * Computes the UPP policy for a specific G2C OSP.
     * This is a computational resource accessed via a REST POST call
     * to carry out a computation on the provided inputs. This results in a
     * resource that is created and stored in the Policy DB and can be
     * accessed by the URL returned as a result of this operation. In OPERANDO,
     * this method is called by the management console when a user subscribes
     * to a new OSP service. The user simply enters answers the specific OSP
     * questionnaire. This will create a simple UPP for that OSP and store
     * this policy within the larger UPP record.

     * @param userId The user who the policy is being created for.
     * @param ospId The OSP Id of the policy being created for. This is the
     * Operando friendly Id, not the unique numerical generated value.
     * @param ospPrefs The set of preferences specific to the policy.
     * @param securityContext
     * @return The HTTP response to the post.
     * @throws NotFoundException
     */
    @Override
    public Response ospPolicyComputerPost(String userId, String ospId, List<UserPreference> ospPrefs, SecurityContext securityContext)
    throws NotFoundException {
        UserPrivacyPolicy uppProfile = null;
        try {
//            String ospNumericId = ospQuerybyFriendlyName(ospId, PDB_BASEURL + "/OSP" );
            String ospNumericId = ospId;
            System.out.println("userid: " + userId + " ospId: "+ ospId + "PDB: " + PDB_BASEURL);

            /**
             * Case for Unit Testing
             */
            if(userId.startsWith("_demo")) {
                String currentUpp = policyService.getUPP(userId);
                ObjectMapper mapper = new ObjectMapper();
                uppProfile = mapper.readValue(currentUpp, UserPrivacyPolicy.class);
            }
            else {
                try {
                    uppProfile = helpMethods.getUserPrivacyPolicy(UPP_BASEURL + "/" + userId);
                } catch (UnknownUserException ex) {
                    return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, ex.getLocalizedMessage())).build();
                }
            }

            // If there is no UPP - we create an instance of the UPP for the user id.
            if(uppProfile == null) {
                uppProfile = new UserPrivacyPolicy();
                uppProfile.setUserId(userId);
            }

            List<OSPConsents> subscribedOspPolicies = uppProfile.getSubscribedOspPolicies();
            System.out.println("Number of polices for this OSP: " + subscribedOspPolicies.size());

            OSPPrivacyPolicy ospPolicy;
            try {
                // Get the access policies for each of the OSP statments
                String urlOSPID = OSP_BASEURL + "/" + ospNumericId;
                System.out.println(urlOSPID);
                ospPolicy = helpMethods.getOSPPolicy(urlOSPID);
            } catch (UnknownOSPException ex) {
                 return Response.status(404).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, ex.getLocalizedMessage())).build();
            }

            /**
             * Create a default policy.
             */
            List<AccessPolicy> policies = ospPolicy.getPolicies();
            for(AccessPolicy aPol : policies) {
                String check = policyCompute.checkPreference(aPol, uppProfile);
                if(check.equalsIgnoreCase("check")){
                    aPol.setPermission(Boolean.TRUE);
                }
                else if(check.equalsIgnoreCase("uncheck")){
                    aPol.setPermission(Boolean.FALSE);
                }
            }

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
