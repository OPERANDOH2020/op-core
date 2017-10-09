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
//      Created Date :          2017-03-21
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

package eu.operando.core.ose.api.impl;

import io.swagger.api.*;
import io.swagger.api.NotFoundException;
import java.io.IOException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import net.minidev.json.JSONArray;
import com.jayway.jsonpath.JsonPath;
import eu.operando.core.ose.services.HelperMethods;
import java.util.Properties;

/**
 * Set of methods to retrieve the consent records for a given OSP.
 */

public class ConsentApiServiceImpl extends ConsentApiService {

    private static final String UPPALLURL = "/pdb/user_privacy_policy/?filter=";
    private static final String UPPURL = "/pdb/user_privacy_policy/";
    private String pdbBasePath = "http://integration.operando.esilab.org:8096/operando/core";

    Properties prop = null;
    HelperMethods helpServices = new HelperMethods();
    /**
     * JAX-RS constructor that is created when the servlet is loaded. This is
     * done on the first call, not on the deployment of the server.
     */
    public ConsentApiServiceImpl() {
        super();
        //  get service config params
//        prop = helpServices.loadServiceProperties();
//        loadParams();
    }

    /**
     * Get the configuration parameters needed for accessing the policy DB
     */
//    private void loadParams() {
//        // setup aapi client
//        if (prop.getProperty("pdb.upp.basepath") != null) {
//            pdbBasePath = prop.getProperty("pdb.upp.basepath");
//        }
//    }

    /**
     * Get the set of Operando users in the PDB. Uses Apache HTTP to make the
     * call rather than a Swagger Client.
     * @return A JSON string representing their UPPs.
     */
    private String getAllUsers() {
        try {
            /**
             * Invoke the PDB to query for the user consents.
             */
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(pdbBasePath + UPPALLURL);
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
     * Get a specific user policy from the policy DB.
     * @userId The id of the user.
     * @return A JSON string representing their UPPs.
     */
    private String getSpecificUser(String userId) {
        try {
            /**
             * Invoke the PDB to query for the user consents.
             */
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(pdbBasePath + UPPURL + userId);
            CloseableHttpResponse response1 = httpclient.execute(httpget);

            /**
             * If there is no response return null.
             */
            HttpEntity entity = response1.getEntity();
            if(response1.getStatusLine().getStatusCode()==404) {
                return null;
            }
            String userPolicy = EntityUtils.toString(entity);
            httpclient.close();
            response1.close();
            httpget.releaseConnection();

            return userPolicy;
        } catch (IOException ex) {
            System.err.println("OSE-Compliance-Report: Unable to retrieve data from Policy Database");
            return null;
        }
    }

    /**
     * Get the anonymised set of access preferences for all users subcribed to the
     * given OSP. Return a json array string where each element is the user's
     * set of consents.
     *
     * @param ospID The OSP id to find the user consents.
     * @return A JSON array of the complete consents of every user.
     */
    private String getSubscribedUsersViaPDB(String ospID){
        JSONArray accessList = new JSONArray();

        String policyList = getAllUsers();
        if(policyList == null) {
            return accessList.toJSONString();
        }

        JSONArray user_policies = JsonPath.read(policyList, "$");
        for(Object aP: user_policies) {
            JSONArray access_policies = JsonPath.read(aP, "$.subscribed_osp_policies[?(@.osp_id=='"+ospID+"')].access_policies");
            if(!access_policies.isEmpty()){
                accessList.add(access_policies.get(0));
            }
        }

        return accessList.toJSONString();
    }

    /**
     * Get the set of access preferences for a given user id.
     *
     * @param ospID The OSP id to find the user consents.
     * @return A JSON array of the complete consents of every user.
     */
    private String getSubscribedUserViaPDB(String ospID, String userId){
        JSONArray accessList = new JSONArray();

        String policy = getSpecificUser(userId);
        if(policy == null) {
            return accessList.toJSONString();
        }

        JSONArray access_policies = JsonPath.read(policy, "$.subscribed_osp_policies[?(@.osp_id=='"+ospID+"')].access_policies");
        if(!access_policies.isEmpty()){
            accessList.add(access_policies.get(0));
        }

        return accessList.toJSONString();
    }

    /**
     * Get the anonymised set of access preferences for all users for a given
     * field name.
     *
     * @param ospID The OSP id to find the user consents.
     * @param field The data resource to find consents for,
     * @return A JSON array of the complete consents of every user for the field.
     */
    private String getSubscribedFieldConsents(String ospID, String field){
        JSONArray accessList = new JSONArray();

        String policyList = getAllUsers();
        if(policyList == null) {
            return accessList.toJSONString();
        }

        JSONArray user_policies = JsonPath.read(policyList, "$");
        for(Object aP: user_policies) {
            JSONArray access_policies = JsonPath.read(aP, "$.subscribed_osp_policies[?(@.osp_id=='"+ospID+"')].access_policies[?(@.resource=='"+field+"')]");
            if(!access_policies.isEmpty()){
                for(int index = 0; index < access_policies.size(); index++) {
                    accessList.add(access_policies.get(index));
                }
            }
        }
        System.out.println("Test");
        return accessList.toJSONString();
    }

    /**
     * Get a user's preferences for a given field name.
     *
     * @param ospID The OSP to get the consents of.
     * @param userId The specific user to find the consents of.
     * @param field The data resource to find consents for,
     * @return A JSON array of the user's consents for this field.
     */
    private String getSubscribedUserFieldConsents(String ospID, String userId, String field){
        JSONArray accessList = new JSONArray();

        String policy = getSpecificUser(userId);
        if(policy == null) {
            return accessList.toJSONString();
        }

        JSONArray access_policies = JsonPath.read(policy, "$.subscribed_osp_policies[?(@.osp_id=='"+ospID+"')].access_policies[?(@.resource=='"+field+"')]");
        if(!access_policies.isEmpty()){
            for(int index = 0; index < access_policies.size(); index++) {
                accessList.add(access_policies.get(index));
            }
        }

        return accessList.toJSONString();
    }

     /**
     * Get the anonymised set of access preferences for all users for a given
     * role name.
     *
     * @param ospID The OSP id to find the user consents.
     * @param role The data role to find consents for.
     * @return A JSON array of the complete consents of every user for the role.
     */
    private String getSubscribedRoleConsents(String ospID, String role){
        JSONArray accessList = new JSONArray();

        String policyList = getAllUsers();
        if(policyList == null) {
            return accessList.toJSONString();
        }

        JSONArray user_policies = JsonPath.read(policyList, "$");
        for(Object aP: user_policies) {
            JSONArray access_policies = JsonPath.read(aP, "$.subscribed_osp_policies[?(@.osp_id=='"+ospID+"')].access_policies[?(@.subject=='"+role+"')]");
            if(!access_policies.isEmpty()){
                for(int index = 0; index < access_policies.size(); index++) {
                    accessList.add(access_policies.get(index));
                }
            }
        }

        return accessList.toJSONString();
    }

    /**
     * Get the set of access preferences for a specific user for a given
     * role name
     *
     * @param ospID The OSP id to find the user consents.
     * @param userId The id of the specific user.
     * @param role The data role to find consents for.
     * @return A JSON array of the complete consents of every user for the field.
     */
    private String getSubscribedUserRoleConsents(String ospID, String userId, String role){
        JSONArray accessList = new JSONArray();

        String policy = getSpecificUser(userId);
        if(policy == null) {
            return accessList.toJSONString();
        }
        JSONArray access_policies = JsonPath.read(policy, "$.subscribed_osp_policies[?(@.osp_id=='"+ospID+"')].access_policies[?(@.subject=='"+role+"')]");
        if(!access_policies.isEmpty()){
            for(int index = 0; index < access_policies.size(); index++) {
                accessList.add(access_policies.get(index));
            }
        }

        return accessList.toJSONString();
    }

     /**
     * Get the anonymised set of access preferences for all users for a given
     * role name and a particular field.
     *
     * @param ospID The OSP id to find the user consents.
     * @param field The data field to find consents for.
     * @param role The data subject to find consents for.
     * @return A JSON array of the complete consents.
     */
    private String getSubscribedRoleandFieldConsents(String ospID, String role, String field){
        JSONArray accessList = new JSONArray();

        String policyList = getAllUsers();
        if(policyList == null) {
            return accessList.toJSONString();
        }

        JSONArray user_policies = JsonPath.read(policyList, "$");
        for(Object aP: user_policies) {
            JSONArray access_policies = JsonPath.read(aP, "$.subscribed_osp_policies[?(@.osp_id=='"+ospID+"')].access_policies[?(@.subject=='"+role+"' && @.resource=='"+field+"')]");
            if(!access_policies.isEmpty()){
                for(int index = 0; index < access_policies.size(); index++) {
                    accessList.add(access_policies.get(index));
                }
            }
        }

        return accessList.toJSONString();
    }

    /**
     * Get the access preferences for a given user for a given
     * role name and a particular field.
     *
     * @param ospID The OSP id to find the user consents.
     * @param userId The specific user id.
     * @param field The data field to find consents for.
     * @param role The data subject to find consents for.
     * @return A JSON array of the complete consents of every user for the field.
     */
    private String getSubscribedUserRoleandFieldConsents(String ospID, String userId, String role, String field){
        JSONArray accessList = new JSONArray();

        String policy = getSpecificUser(userId);
        if(policy == null) {
            return accessList.toJSONString();
        }

        JSONArray access_policies = JsonPath.read(policy, "$.subscribed_osp_policies[?(@.osp_id=='"+ospID+"')].access_policies[?(@.subject=='"+role+"' && @.resource=='"+field+"')]");
        if(!access_policies.isEmpty()){
            for(int index = 0; index < access_policies.size(); index++) {
                accessList.add(access_policies.get(index));
            }
        }

        return accessList.toJSONString();
    }

    /**
     * The REST method that allows a remote call to get a consent report based
     * on a set of input parameters:
     * 1) Just an OSP ID - the list of all user consents for this OSP anonymised by removing user ids.
     * 2) OSP ID and field - the list of all user consents for this OSP for a specific field.
     * 3) OSP ID and role - the list of all user consents for this OSP for a specific role.
     * 4) All fields - the list of all user consents for this OSP for a specific role.
     * @param ospId The OSP identifier.
     * @param field The name of the specific data field element.
     * @param role The name of the data subject who accesses the data.
     * @param securityContext The JAXRX context information for the request.
     * @return A JSON message with the consent report as the HTTP entity.
     * @throws io.swagger.api.NotFoundException 404 error when data is not found.
     */
    @Override
    public Response consentOspIdGet(String ospId, String field, String role, SecurityContext securityContext) throws NotFoundException {
//        try{
            return Response.ok().entity("test message").build();
            /**
             * From the OSP get a list of all subscribed user policies.
             */
//            if ((field == null) && (role == null)) {
//                String responseMessage = getSubscribedUsersViaPDB(ospId);
//                return Response.ok().entity(responseMessage).build();
//            }
//            else if ((field != null) && (role == null)) {
//                String responseMessage = getSubscribedFieldConsents(ospId, field);
//                return Response.ok().entity(responseMessage).build();
//            }
//            else if ((field == null) && (role != null)) {
//                String responseMessage = getSubscribedRoleConsents(ospId, role);
//                return Response.ok().entity(responseMessage).build();
//            }
//            else {
//                String responseMessage = getSubscribedRoleandFieldConsents(ospId, role, field);
//                return Response.ok().entity(responseMessage).build();
//            }
//        }
//        catch(Exception ex) {
//            return Response.ok().entity(ex.getMessage()).build();
//        }
    }

     /**
     * The REST method that allows a remote call to get a consent report based
     * on a set of input parameters:
     * 1) Just an OSP ID - the list of a user's consents for this OSP
     * 2) OSP ID and field - the list of a user's consent for this OSP for a specific field.
     * 3) OSP ID and role - the list of a user's consent for this OSP for a specific role.
     * 4) All fields - the list of a user's consent this OSP for a specific role.
     * @param ospId The OSP identifier.
     * @param field The name of the specific data field element.
     * @param role The name of the data subject who accesses the data.
     * @param securityContext The JAXRX context information for the request.
     * @return A JSON message with the consent report as the HTTP entity.
     * @throws io.swagger.api.NotFoundException 404 error when data is not found.
     */
    @Override
    public Response consentOspIdUserIdGet(String ospId, String userId, String field, String role, SecurityContext securityContext) throws NotFoundException {
        if ((field == null) && (role == null)) {
            String responseMessage = getSubscribedUserViaPDB(ospId, userId);
            return Response.ok().entity(responseMessage).build();
        }
        else if ((field != null) && (role == null)) {
            String responseMessage = getSubscribedUserFieldConsents(ospId, userId, field);
            return Response.ok().entity(responseMessage).build();
        }
        else if ((field == null) && (role != null)) {
            String responseMessage = getSubscribedUserRoleConsents(ospId, userId, role);
            return Response.ok().entity(responseMessage).build();
        }
        else {
            String responseMessage = getSubscribedUserRoleandFieldConsents(ospId, userId, role, field);
            return Response.ok().entity(responseMessage).build();
        }
    }

}
