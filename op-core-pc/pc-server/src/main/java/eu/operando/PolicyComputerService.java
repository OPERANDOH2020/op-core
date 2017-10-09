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
package eu.operando;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import io.swagger.api.NotFoundException;
import eu.operando.core.pdb.common.model.AccessPolicy;

import eu.operando.core.pdb.common.model.OSPConsents;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;
import eu.operando.core.pdb.common.model.UserPreference;
import eu.operando.core.pdb.common.model.UserPrivacyPolicy;
import io.swagger.api.impl.OspPolicyComputerApiServiceImpl;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Set of operations for the User Privacy Policy Computation. Based upon a
 * set of inputs from questionnaires, and information from OSP about their
 * workflow and behaviour, the UPP entry is computed and updated in the PDB.
 *
 */
public class PolicyComputerService {

    /**
     * Placeholder for service state
     */
    public PolicyComputerService() {

    }
    /**
     *
     * @param userId
     * @param ospId
     * @param ospPrefs
     * @param PDB_URL
     * @param policyService
     * @return
     * @throws NotFoundException
     */
    public String ospPolicyComputerPost(String userId, String ospId, List<UserPreference> ospPrefs, String UPP_URL, String PDB_URL, PolicyEvaluationService policyService)
        throws NotFoundException {
         try {
            String currentUpp = null;
            String ospNumericId = OspPolicyComputerApiServiceImpl.ospQuerybyFriendlyName(ospId, PDB_URL);
            System.out.println("userid: " + userId + " ospId: "+ ospNumericId + "PDB: " + PDB_URL);
            if(userId.startsWith("_demo")) {
                currentUpp = policyService.getUPP(userId);
            }
            else {
                try {
                    /**
                     * Get the UPP from the PDB.
                     */
                    CloseableHttpClient httpclient = HttpClients.createDefault();
                    HttpGet httpget = new HttpGet(UPP_URL + userId);
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
                    return "UserId doesn't exist";
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
                String ospURL = PDB_URL + "/" + ospNumericId;
                HttpGet httpget = new HttpGet(ospURL);
                CloseableHttpResponse response1 = httpclient.execute(httpget);

                /**
                 * If there is no OSP, then complete fail.
                 */
                HttpEntity entity = response1.getEntity();
                System.out.println(response1.getStatusLine().getStatusCode());
                if(response1.getStatusLine().getStatusCode()==404) {
                    return "OSP doesn't exist";
                }
                currentOSP = EntityUtils.toString(entity);
                System.out.println(currentOSP);
            } catch (IOException ex) {
                return "OSP doesn't exist";
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
                WebResource webResourcePDB = client.resource(UPP_URL + userId);
                ClientResponse policyResponse = webResourcePDB.type("application/json").get(ClientResponse.class);
                if (policyResponse.getStatus() != 200) {
                    WebResource webResourcePDB2 = client.resource(UPP_URL);
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
                       return "User doesn't exist";
                   }
                }
                return "Success";
            } catch (UniformInterfaceException | ClientHandlerException ex) {
                System.err.println("error - " + ex.getMessage());
                return "Compute failed";
            }
        } catch (IOException ex) {
            System.err.println("error - " + ex.getMessage());
            Logger.getLogger(OspPolicyComputerApiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return "Compute failed";
        }
    }

//    /**
//     * Stateless computation to update the UPP of a given user who has registered
//     * with the
//     * @param userId The Operando ID of the user to compute the policy for
//     * @param generalPreferences The list of initial preferences
//     * @return
//     * @throws eu.operando.UnknownUserException
//     * @throws eu.operando.InvalidPreferenceException
//     */
//    public static String computeNewPolicy(String userId, List<UserPreference> generalPreferences)
//        throws UnknownUserException, InvalidPreferenceException{
//        try {
//            /**
//             * Read the preferences and then build the User General Preferences
//             */
//            UPPApi dbase = new UPPApi();
//
//            // Based on the inputs generate the preferences. For now this is a straight copy
//
//             /**
//             * Update the UPP in the policy DB
//             */
//            UserPrivacyPolicy uPP = dbase.userPrivacyPolicyUserIdGet(userId);
//            uPP.setUserPreferences(generalPreferences);
//
//            dbase.userPrivacyPolicyUserIdPut(userId, uPP);
//
//            /**
//             * Return the URL to the updated policy
//             */
//            URL urlResponse = new URL("http://127.0.0.1:8081/policy_database/"+userId);
//            ComputationResult cr = new ComputationResult();
//            cr.setUrl(urlResponse.toExternalForm());
//            cr.setStatus("true");
//            cr.setUser(userId);
//            return cr.toString();
//        } catch (ApiException ex) {
//            throw new UnknownUserException("Error computing");
//        } catch (MalformedURLException ex) {
//            throw new UnknownUserException("Error computing");
//        }
//    }
//
//    public static String computeOSPPolicy(String userId, String ospID, List<UserPreference> generalPreferences)
//        throws UnknownUserException, InvalidPreferenceException{
//        try {
//            /**
//             * Read the preferences and then build the User General Preferences
//             */
//            UPPApi dbase = new UPPApi();
//
//            // Based on the inputs generate the preferences. For now this is a straight copy
//
//             /**
//             * Update the UPP in the policy DB
//             */
//            UserPrivacyPolicy uPP = dbase.userPrivacyPolicyUserIdGet(userId);
//            uPP.setUserPreferences(generalPreferences);
//
//            dbase.userPrivacyPolicyUserIdPut(userId, uPP);
//
//            /**
//             * Return the URL to the updated policy
//             */
//            URL urlResponse = new URL("http://127.0.0.1:8081/policy_database/"+userId);
//            ComputationResult cr = new ComputationResult();
//            cr.setUrl(urlResponse.toExternalForm());
//            cr.setStatus("true");
//            cr.setUser(userId);
//            return cr.toString();
//        } catch (ApiException ex) {
//            throw new UnknownUserException("Error computing");
//        } catch (MalformedURLException ex) {
//            throw new UnknownUserException("Error computing");
//        }
//    }

}
