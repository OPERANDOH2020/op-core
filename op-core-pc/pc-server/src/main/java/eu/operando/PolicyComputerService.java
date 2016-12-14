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
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.swagger.api.NotFoundException;
import eu.operando.core.pdb.common.model.AccessPolicy;

import eu.operando.core.pdb.common.model.OSPConsents;
import io.swagger.model.OSPDataRequest;
import io.swagger.model.OSPDataRequest.ActionEnum;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;
import io.swagger.model.PolicyEvaluationReport;
import io.swagger.model.UserPreference;
import eu.operando.core.pdb.common.model.UserPrivacyPolicy;
import java.io.IOException;
import java.util.ArrayList;
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
    public String ospPolicyComputerPost(String userId, String ospId, List<UserPreference> ospPrefs, String PDB_URL, PolicyEvaluationService policyService)
        throws NotFoundException {

        try {
            String currentUpp = null;
            String ospPolicy = null;
            if(userId.startsWith("_demo")) {
                currentUpp = policyService.getUPP(userId);
                ospPolicy = policyService.getUPP(ospId);
            }
            else {
                try {
                    /**
                     * Get the UPP from the PDB.
                     */
                    CloseableHttpClient httpclient = HttpClients.createDefault();
                    HttpGet httpget = new HttpGet(PDB_URL + "/" + userId);
                    CloseableHttpResponse response1 = httpclient.execute(httpget);

                    /**
                     * If there is no UPP, then it returns an non-compliance report
                     * with a NO_POLICY statement.
                     */
                    HttpEntity entity = response1.getEntity();
                    System.out.println(response1.getStatusLine().getStatusCode());
                    if(response1.getStatusLine().getStatusCode()==404) {
                        throw new NotFoundException(400, "UserId doesn't exist");
                    }
                    currentUpp = EntityUtils.toString(entity);
                    System.out.println(currentUpp);

                    httpget = new HttpGet(PDB_URL + "/osp/?filter=" + ospId);
                    response1 = httpclient.execute(httpget);

                    /**
                     * If there is no UPP, then it returns an non-compliance report
                     * with a NO_POLICY statement.
                     */
                    entity = response1.getEntity();
                    System.out.println(response1.getStatusLine().getStatusCode());
                    if(response1.getStatusLine().getStatusCode()==404) {
                        throw new NotFoundException(400, "OSP Policy doesn't exist");
                    }
                    currentUpp = EntityUtils.toString(entity);
                    System.out.println(currentUpp);
                } catch (IOException ex) {
                    throw new NotFoundException(400, "UserId doesn't exist");
                }
            }
            // Create a subscribed policy statement and store it in the UPP
            ObjectMapper mapper = new ObjectMapper();
            UserPrivacyPolicy obj = mapper.readValue(currentUpp, UserPrivacyPolicy.class);
            OSPPrivacyPolicy    obj2 = mapper.readValue(ospPolicy, OSPPrivacyPolicy.class);

            List<OSPConsents> subscribedOspPolicies = obj.getSubscribedOspPolicies();
            for (OSPConsents ospPol: subscribedOspPolicies) {
                if(ospPol.getOspId().equalsIgnoreCase(ospId)) {
                    // remove the policies and recompute
                    subscribedOspPolicies.remove(ospPol);
                    break;
                }
            }

            // For each of the access policies compute the
            List<AccessPolicy> policies = obj2.getPolicies();
            for(AccessPolicy aPol: policies) {
                List<OSPDataRequest> newDR = new ArrayList<OSPDataRequest>();
                OSPDataRequest osd = new OSPDataRequest();
                osd.setAction(ActionEnum.valueOf(aPol.getAction().name()));
                osd.setRequestedUrl(aPol.getResource());
                osd.setSubject(aPol.getSubject());
                PolicyEvaluationReport evaluate = policyService.evaluate(ospId, userId, newDR, PDB_URL);
                aPol.setPermission(Boolean.valueOf(evaluate.getStatus()));
            }
            OSPConsents oC = new OSPConsents();
            oC.setAccessPolicies(policies);
            oC.setOspId(ospId);
            subscribedOspPolicies.add(oC);

            /**
             * Update the UPP with the new information
             */
            if(userId.startsWith("_demo")) {
                policyService.putUPP(userId, obj.toString());
            }
            else {
                Client client = new Client();
                WebResource webResourcePDB = client.resource(PDB_URL+"/user_privacy_policy/"+userId);

            ClientResponse policyResponse = webResourcePDB.type("application/json").put(ClientResponse.class,
                    obj.toString());

            if (policyResponse.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + policyResponse.toString());
            }
            }

            return  subscribedOspPolicies.toString();
        } catch (IOException ex) {
            Logger.getLogger(PolicyComputerService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
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
