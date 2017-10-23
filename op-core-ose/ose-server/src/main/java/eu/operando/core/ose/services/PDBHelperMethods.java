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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import eu.operando.core.pdb.common.model.AccessPolicy;
import eu.operando.core.pdb.common.model.OSPConsents;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;
import eu.operando.core.pdb.common.model.OSPReasonPolicy;
import eu.operando.core.pdb.common.model.UserPrivacyPolicy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minidev.json.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author pjg
 */
public class PDBHelperMethods {

    private String pdbBasePath = "http://integration.operando.esilab.org:8096/operando/core";

    /**
     * Get the set of Operando users in the PDB. Uses Apache HTTP to make the
     * call rather than a Swagger Client.
     * @return A JSON string representing their UPPs.
     */
    public String getAllUsers() {
        try {
            /**
             * Invoke the PDB to query for the user consents.
             */
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = pdbBasePath + "/pdb/user_privacy_policy/?filter=";
            System.out.println(url);
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response1 = httpclient.execute(httpget);

            /**
             * If there is no response return null.
             */
            HttpEntity entity = response1.getEntity();
            if(response1.getStatusLine().getStatusCode()==404) {
                return null;
            }
            String policyList = EntityUtils.toString(entity);
            httpclient.close();
            response1.close();
            httpget.releaseConnection();

            return policyList;
        } catch (IOException ex) {
            System.err.println("OSE-Compliance-Report: Unable to retrieve data from Policy Database");
            return null;
        }
    }

    /**
     * Get a specific OSP policy from the policy DB.
     * @userId The id of the OSP.
     * @return A JSON string representing their UPPs.
     */
    public OSPPrivacyPolicy getSpecificOSP(String ospId) {
        try {
            /**
             * Invoke the PDB to query for the user consents.
             */
            CloseableHttpClient httpclient = HttpClients.createDefault();
            String url = pdbBasePath + "/pdb/OSP/" + ospId;
            System.out.println(url);
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response1 = httpclient.execute(httpget);

            /**
             * If there is no response return null.
             */
            HttpEntity entity = response1.getEntity();
            if(response1.getStatusLine().getStatusCode()==404) {
                return null;
            }
            String jsonOspPolicy = EntityUtils.toString(entity);
            System.out.println(jsonOspPolicy);
            httpclient.close();
            response1.close();
            httpget.releaseConnection();

            ObjectMapper mapper = new ObjectMapper();
            OSPPrivacyPolicy ospPolicy = mapper.readValue(jsonOspPolicy, OSPPrivacyPolicy.class);

            return ospPolicy;
        } catch (IOException ex) {
            System.err.println("OSE-Compliance-Report: Unable to retrieve data from Policy Database");
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Get a specific user policy from the policy DB.
     * @userId The id of the user.
     * @return A JSON string representing their UPPs.
     */
    public UserPrivacyPolicy getUserPrivacyPolicy(String userId) {
        try {
            /**
             * Invoke the PDB to query for the user consents.
             */
            CloseableHttpClient httpclient = HttpClients.createDefault();
            System.out.println(pdbBasePath + "/pdb/user_privacy_policy/" + userId);
            HttpGet httpget = new HttpGet(pdbBasePath + "/pdb/user_privacy_policy/" + userId);
            CloseableHttpResponse response1 = httpclient.execute(httpget);

            /**
             * If there is no response return null.
             */
            HttpEntity entity = response1.getEntity();
            if(response1.getStatusLine().getStatusCode()==404) {
                return null;
            }
            String jsonUPPPolicy = EntityUtils.toString(entity);
            System.out.println(jsonUPPPolicy.toString());
            httpclient.close();
            response1.close();
            httpget.releaseConnection();


            ObjectMapper mapper = new ObjectMapper();
            UserPrivacyPolicy uppPolicy = mapper.readValue(jsonUPPPolicy, UserPrivacyPolicy.class);

            return uppPolicy;
        } catch (IOException ex) {
            System.err.println("OSE-Compliance-Report: Unable to retrieve data from Policy Database");
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Update the user policy
     * @param userId The user's id
     * @param upp The new upp data
     * @return Indication of operation success
     */
    public boolean putUserPrivacyPolicy(String userId, String upp) {
        try {
            /**
             * Invoke the PDB to query for the user consents.
             */
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPut httpput = new HttpPut(pdbBasePath + "/pdb/user_privacy_policy/" + userId);
            httpput.setHeader("Content-type", "application/json");
            httpput.setEntity(new StringEntity(upp));
            CloseableHttpResponse response1 = httpclient.execute(httpput);

            if(response1.getStatusLine().getStatusCode()!=204) {
                return false;
            }

            httpclient.close();
            response1.close();
            httpput.releaseConnection();

            return true;
        } catch (IOException ex) {
            System.err.println("OSE-Compliance-Report: Unable to retrieve data from Policy Database");
            return false;
        }
    }

     /**
     * Get the array of user's UPPs for a given OSP.
     *
     */
    public String getSubscribedUserPolicies(String ospId, String pdb_policies) {
        JSONArray uppSet = new JSONArray();
        JSONArray user_policies = JsonPath.read(pdb_policies, "$");
        for(Object aP: user_policies) {
            JSONArray access_policies = JsonPath.read(aP, "$..subscribed_osp_policies[?(@.osp_id=='"+ospId+"')]");
            if(!access_policies.isEmpty()){
                uppSet.add(aP);
            }
        }

        return uppSet.toJSONString();
    }

    public List<String> getSubscribedUsersList(String ospId, String pdb_policies){
        ArrayList<String> users = new ArrayList<String>();
        JSONArray user_policies = JsonPath.read(pdb_policies, "$");
        for(Object aP: user_policies) {
            JSONArray access_policies = JsonPath.read(aP, "$..subscribed_osp_policies[?(@.osp_id=='"+ospId+"')]");
            if(!access_policies.isEmpty()){
                users.add(JsonPath.read(aP, "$.user_id").toString());
            }
        }
        return users;
    }

    public OSPReasonPolicy getOSPReasonPolicy(String ospId) {
        try {
            /**
             * Invoke the PDB to query for the user consents.
             */
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpput = new HttpGet(pdbBasePath + "/pdb/OSP/" + ospId + "/privacy-policy");
            CloseableHttpResponse response1 = httpclient.execute(httpput);

            /**
             * If there is no response return null.
             */
            HttpEntity entity = response1.getEntity();
            if(response1.getStatusLine().getStatusCode()!=200) {
                return null;
            }
            String jsonOspPolicy = EntityUtils.toString(entity);
            System.out.println(jsonOspPolicy);

            httpclient.close();
            response1.close();
            httpput.releaseConnection();

            ObjectMapper mapper = new ObjectMapper();
            OSPReasonPolicy ospPolicy = mapper.readValue(jsonOspPolicy, OSPReasonPolicy.class);

            return ospPolicy;
        } catch (IOException ex) {
            Logger.getLogger(PDBHelperMethods.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

     /**
     * Delete the policy from the UPP
     */
    public String deleteAccessPolicy(AccessPolicy policy, UserPrivacyPolicy upp, String userId, String friendlyId) {
        /**
         * Search through the UPP
         */

        System.out.println("Resource = " + policy.getResource());
        List<OSPConsents> subscribedOspPolicies = upp.getSubscribedOspPolicies();
        for(OSPConsents consents: subscribedOspPolicies) {
            System.out.println(consents.getOspId() + " = " + friendlyId);
            if (consents.getOspId().equalsIgnoreCase(friendlyId)) {
                List<AccessPolicy> accessPolicies = consents.getAccessPolicies();
                for (AccessPolicy acPolicy: accessPolicies) {
                    System.out.println(policy.getResource() + " = " + acPolicy.getResource());
                    if(policy.getResource().equalsIgnoreCase(acPolicy.getResource())) {
                        System.out.println(acPolicy.getSubject() + " = " + policy.getResource());
                        if(acPolicy.getSubject().equalsIgnoreCase(policy.getResource())) {
                            accessPolicies.remove(acPolicy);
                            break;
                        }
                    }
                }
            }
        }

        String uppJsonString = upp.toString();
        return uppJsonString;
    }

    /**
     * Change the policy from the UPP
     */
    public String changeAccessPolicy(AccessPolicy policy, UserPrivacyPolicy upp, String userId, String friendlyId) {
        /**
         * Search through the UPP
         */

        System.out.println("Resource = " + policy.getResource());
        List<OSPConsents> subscribedOspPolicies = upp.getSubscribedOspPolicies();
        for(OSPConsents consents: subscribedOspPolicies) {
            System.out.println(consents.getOspId() + " = " + friendlyId);
            if (consents.getOspId().equalsIgnoreCase(friendlyId)) {
                List<AccessPolicy> accessPolicies = consents.getAccessPolicies();
                for (AccessPolicy acPolicy: accessPolicies) {
                    System.out.println(policy.getResource() + " = " + acPolicy.getResource());
                    if(policy.getResource().equalsIgnoreCase(acPolicy.getResource())) {
                        System.out.println(acPolicy.getSubject() + " = " + policy.getResource());
                        if(acPolicy.getSubject().equalsIgnoreCase(policy.getResource())) {
                            acPolicy.setPermission(false);
                            break;
                        }
                    }
                }
            }
        }

        String uppJsonString = upp.toString();
        return uppJsonString;
    }

}
