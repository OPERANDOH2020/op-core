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
package eu.operando.core.ose.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import eu.operando.core.cas.client.api.DefaultApi;
import io.swagger.client.ApiClient;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;

import java.util.List;
import io.swagger.api.NotFoundException;

import eu.operando.core.ose.mongo.OspsMongo;
import eu.operando.core.ose.services.HelperMethods;
import eu.operando.core.pdb.common.model.AccessPolicy;
import eu.operando.core.pdb.common.model.AccessReason;
import eu.operando.core.pdb.common.model.OSPConsents;
import eu.operando.core.pdb.common.model.PolicyAttribute;
import eu.operando.core.pdb.common.model.UserPrivacyPolicy;
import io.swagger.api.ApiResponseMessage;
import io.swagger.api.OspsApiService;
import io.swagger.client.ApiException;
import io.swagger.client.api.LogApi;
import io.swagger.client.model.LogRequest;
import io.swagger.client.model.LogRequest.LogLevelEnum;
import io.swagger.client.model.LogRequest.LogPriorityEnum;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import net.minidev.json.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class OspsApiServiceImpl extends OspsApiService {

    // LogDB
    LogApi logApi;
    // AAPI
    DefaultApi aapiClient;

    private String oseOSPSSId = "ose/osps/.*";
//    private String logdbSId = "pdb/OSP/.*";
    private String aapiBasePath = "http://integration.operando.esilab.org:8135/operando/interfaces/aapi";
    private String logdbBasePath = "http://integration.operando.esilab.org:8090/operando/core/ldb";
    private String pdbBasePath = "http://integration.operando.esilab.org:8096/operando/core";
    private String ospsLoginName = "panos";
    private String ospsLoginPassword = "operando";
    private String stHeaderName = "Service-Ticket";
    private String logdbST = "";
    long ticketLifeTime = 1000L * 60 * 60;

    String mongoServerHost = "localhost";
    int mongoServerPort = 27017;
    OspsMongo ospsMongodb = null;
    Timer timer;

    Properties prop = null;
    private HelperMethods helpServices;

    public OspsApiServiceImpl() {
        super();

        helpServices = new HelperMethods();

        //  get service config params
//        prop = loadServiceProperties();
//        loadParams();

        // setup aapi client
        eu.operando.core.cas.client.ApiClient aapiDefaultClient = new eu.operando.core.cas.client.ApiClient();
        aapiDefaultClient.setBasePath(aapiBasePath);
        this.aapiClient = new DefaultApi(aapiDefaultClient);

        // setup logdb client
        final ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(logdbBasePath);

        TimerTask timerTask = new TimerTask() {
            //@Override
            public void run() {
                Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.INFO, "osps TIMER RUN now");
                logdbST = helpServices.getServiceTicket(aapiClient, ospsLoginName, ospsLoginPassword, oseOSPSSId);
                apiClient.addDefaultHeader(stHeaderName, logdbST);
            }
        };

        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, ticketLifeTime);

        // get service ticket for logdb service
        logdbST = helpServices.getServiceTicket(aapiClient, ospsLoginName, ospsLoginPassword, oseOSPSSId);
        apiClient.addDefaultHeader(stHeaderName, logdbST);
        this.logApi = new LogApi(apiClient);

        // setup mongo part
        ospsMongodb = new OspsMongo(mongoServerHost, mongoServerPort);
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
        if (prop.getProperty("ose.osps.service.login") != null) {
            ospsLoginName = prop.getProperty("ose.osps.service.login");
        }
        if (prop.getProperty("ose.osps.service.password") != null) {
            ospsLoginPassword = prop.getProperty("ose.osps.service.password");
        }
//        if (prop.getProperty("logdb.service.id") != null) {
//            logdbSId = prop.getProperty("logdb.service.id");
//        }

        if (prop.getProperty("pdb.upp.basepath") != null) {
            pdbBasePath = prop.getProperty("pdb.upp.basepath");
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

    /**
     * Get a specific user policy from the policy DB.
     * @userId The id of the user.
     * @return A JSON string representing their UPPs.
     */
    private OSPPrivacyPolicy getSpecificOSP(String ospId) {
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
    private UserPrivacyPolicy getUserPrivacyPolicy(String userId) {
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

    private boolean putUserPrivacyPolicy(String userId, String upp) {
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

    private String resetCategoryPolicies(String Category, String role, UserPrivacyPolicy upp, String userId, String ospId, String friendlyId) {
        /**
         * Find the fields specific to this category and role
         */
        List<String> resources = new ArrayList<String>();
        OSPPrivacyPolicy osp_access_policies = getSpecificOSP(ospId);
        List<AccessPolicy> policies = osp_access_policies.getPolicies();
        for(AccessPolicy accPol: policies) {
            List<PolicyAttribute> attributes = accPol.getAttributes();
            for (PolicyAttribute attVel: attributes) {
                if( attVel.getAttributeName().equalsIgnoreCase("category")) {
                    if( attVel.getAttributeValue().equalsIgnoreCase(Category)) {
                        if(accPol.getSubject().equalsIgnoreCase(role)) {
                            resources.add(accPol.getResource());
                        }
                    }
                    break;
                }
            }
        }

        /**
         * Search through the UPP
         */
        for(String resourceData: resources){
            System.out.println("Resource = " + resourceData);
            List<OSPConsents> subscribedOspPolicies = upp.getSubscribedOspPolicies();
            for(OSPConsents consents: subscribedOspPolicies) {
                System.out.println(consents.getOspId() + " = " + friendlyId);
                if (consents.getOspId().equalsIgnoreCase(friendlyId)) {
                    List<AccessPolicy> accessPolicies = consents.getAccessPolicies();
                    for (AccessPolicy acPolicy: accessPolicies) {
                        System.out.println(resourceData + " = " + acPolicy.getResource());
                        if(resourceData.equalsIgnoreCase(acPolicy.getResource())) {
                             System.out.println(acPolicy.getSubject() + " = " + role);
                            if(acPolicy.getSubject().equalsIgnoreCase(role)) {
                                acPolicy.setPermission(false);
                            }
                        }
                    }
                }
            }
        }
        String uppJsonString = upp.toString();
        return uppJsonString;

    }


    @Override
    public Response ospsOspIdPrivacytextPut(String ospId, AccessReason ospPrivacyText, SecurityContext securityContext)
            throws NotFoundException {

        OSPPrivacyPolicy osp_access_policies = getSpecificOSP(ospId);

        String changeMessage = "The OSP (" + osp_access_policies.getPolicyText() + ") has updated their privacy policy. The following is their"
                + " new reason for accessing your data: ";

            changeMessage += "A " + ospPrivacyText.getDatauser() + " can use a " + ospPrivacyText.getDatasubjecttype() + "s " +
                    ospPrivacyText.getDatatype() + " data for the purpose of " + ospPrivacyText.getReason() + ". ";


        changeMessage += "Your access preferences have been updated to prevent access to this data until you "
                + "consent to this change. Please visit the access preferences page and review your settings.";

        /**
         * Create a set of logs about each change. The user needs to review. First
         * get all of the user policies.
         */
        String all_user_policies =  getAllUsers();

        if(all_user_policies == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Could not notify users of changed policy").build();
        }


        String friendlyId = osp_access_policies.getPolicyUrl();

        List<String> jsonUsers = getSubscribedUsersList(friendlyId, all_user_policies);

        for(String userId: jsonUsers) {
            logUserRequest(ospId, "OSP Privacy Policy Change",
                 changeMessage,
                LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogRequest.LogTypeEnum.NOTIFICATION, userId,
                new ArrayList<String>(Arrays.asList("POST")));
            UserPrivacyPolicy upp = getUserPrivacyPolicy(userId);
            String uppJson = resetCategoryPolicies(ospPrivacyText.getDatatype(), ospPrivacyText.getDatauser(), upp, userId, ospId, friendlyId);
            ObjectMapper mapper = new ObjectMapper();

            try {
                //JSON from file to Object
                upp = mapper.readValue(uppJson, UserPrivacyPolicy.class);
            } catch (IOException ex) {
                Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            putUserPrivacyPolicy(userId, uppJson);
        }

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "Successful response, The privacy text update analysis has begun.")).build();
    }


    /**
     * Get the array of user's UPPs for a given OSP.
     *
     */
    private String getSubscribedUserPolicies(String ospId, String pdb_policies) {
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

    private List<String> getSubscribedUsersList(String ospId, String pdb_policies){
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

    @Override
    public Response ospsOspIdPut(String ospId, OSPPrivacyPolicy ospPolicy, SecurityContext securityContext)
            throws NotFoundException {

        /**
         * Create a set of logs about each change. The user needs to review. First
         * get all of the user policies.
         */
        String all_user_policies =  getAllUsers();
        if(all_user_policies == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Could not notify users of changed policy").build();
        }

        String jsonUsers = getSubscribedUserPolicies(ospId, all_user_policies);
        System.out.println(jsonUsers);
        /**
         * Get the current policy from the PDB. Analyse and find the set of access policies that have changed.
         * New role, new field, new data resource.
         */
        OSPPrivacyPolicy ospNewPolicy = getSpecificOSP(ospId);
        List<AccessPolicy> oldPolicies = ospNewPolicy.getPolicies();
        List<AccessPolicy> newPolicies = ospNewPolicy.getPolicies();
        List<AccessPolicy> changedPolicies = new ArrayList<AccessPolicy>();

        for(AccessPolicy newPolicy: newPolicies) {
            boolean change = false;
            boolean match = true;
            for(AccessPolicy oldPolicy: oldPolicies) {
                // check for complete match - an access policy is unique by resource, subject, action
                if( (oldPolicy.getResource().equals(newPolicy.getResource())) &&
                     (oldPolicy.getSubject().equals(newPolicy.getSubject())) &&
                       (oldPolicy.getAction().equals(newPolicy.getAction())) ) {

                    match = true;

                    /**
                     * If the permission has changed.
                     */
                    if(!oldPolicy.getPermission().equals(newPolicy.getPermission())) {
                        change = true;
                        break;
                    }
                    /**
                     * If the category/reason has changed.
                     */
                    List<PolicyAttribute> attributes = oldPolicy.getAttributes();
                    String categoryVal = "";
                    for(PolicyAttribute polA: attributes) {
                        if(polA.getAttributeName().equalsIgnoreCase("category")) {
                            categoryVal = polA.getAttributeValue();
                            break;
                        }
                    }
                    List<PolicyAttribute> nAttributes = newPolicy.getAttributes();
                    for(PolicyAttribute polA: nAttributes) {
                        if(polA.getAttributeName().equalsIgnoreCase("category")) {
                            if(!categoryVal.equalsIgnoreCase(polA.getAttributeValue())) {
                                change = true;
                                break;
                            }
                        }
                    }
                }
                if(match){
                    oldPolicies.remove(oldPolicy);
                }
            }
            if(match && !change){
                    newPolicies.remove(newPolicy);
            }
            else if (match && change) {
                changedPolicies.add(newPolicy);
                newPolicies.remove(newPolicy);
            }
        }

        /**
         * For each deleted policy
         */
        for(AccessPolicy oldPolicy: oldPolicies) {
            // Get the affected users and delete this policy statment from their UPP

        }
        // do some magic!
        return Response.status(Response.Status.OK).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "Successful response. The privacy policy has been received and being processed. Information will be sent via other operation sequences."))
                .build();

    }

//    private String getUserIds (String jsonPolicies, String ) {
//
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

//    @Override
//    public Response ospsOspIdWorkflowsPut(String ospId, OSPDataRequest ospWorkflow, SecurityContext securityContext)
//            throws NotFoundException {
//        // do some magic!
//        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
//    }

    @Override
    public Response ospsOspIdAuditGet(String ospId, String start, String end, SecurityContext securityContext) throws NotFoundException {
        String response = "{\"osp_id\": \"" + ospId + "\"," +
                "\"start\": \"" + start + "\"," +
                "\"end\": \"" + end + "\"}";

        return Response.ok().entity(response).build();
    }

    //    @Override
//    public Response ospsOspIdAuditGet(String ospId, String start, String end, SecurityContext securityContext) throws NotFoundException {
//        class  Report{
//            ArrayList<DataAccessLog> validLogs= ArrayList<DataAccessLog>();
//            ArrayList<DataAccessLog> invalidLogs= ArrayList<DataAccessLog>();
//            Boolean checkValid()
//            {
//                if(invalidLogs.Count == 0)
//                {
//                   return true;
//                }
//                 return false;
//            }
//        }
//
///*        string baseurl = ConfigurationManager.AppSettings["ldbBasePath"];
//        //instance to get from the log database
//
//        //gets the list of OSPs
//        List<OSPPrivacyPolicy> ospList = GetOspList();*/
//
//        //creates datetime objects for the start date and end date selected by the user on the html page
//        LdbClient instance = new eu.operando.core.ldb.LdbClient(baseurl);
//        DateFormat startDate = new DateFormat(start);
//        DateFormat endDate = new DateFormat(end);
//
//
//        OSPPrivacyPolicy matchingOsp = ospList.Where(o => o.OspPolicyId.Equals(ospId)).First();
//        HashSet<String> roles = new HashSet<String>();
//
//        foreach (AccessPolicy policy in matchingOsp.Policies)
//        {
//            roles.Add(policy.Subject);
//        }
//
//        //get the data access logs
//        ArrayList<DataAccessLog> logs = instance.GetDataAccessLogs(OspPolicyId);
//        ArrayList<DataAccessLog> logsToCheck = new ArrayList<DataAccessLog>();
//        //sorts by logs that fall within the region for the dates
//        foreach (DataAccessLog log in logs)
//        {
//            if (log.logDate >= startDate && log.logDate <= endDate && log.requesterId.Equals(matchingOsp.OspPolicyId))
//            {
//                logsToCheck.Add(log);
//            }
//        }
//        //creates a report object
//        Report report = new Report();
//        //goes through each log that matches the dates
//        foreach (DataAccessLog log in logsToCheck)
//        {
//            bool found = false;
//            string matchedrole = "nothing";
//            //checks if there is a role matching the role accessed according to the logs
//            foreach (String role in roles)
//            {
//                if (log.description.contains(role))
//                {
//                    found = true;
//                    matchedrole = role;
//                    break;
//                }
//            }
//            //runs if there is a matching role
//            if (found)
//            {
//            	bool policymatch = false;
//                //checks if there is a resource and role which matches (the policy for this log)
//            	foreach(AccessPolicy p in matchingOsp.Policies){
//            		if(p.Subject..Equals(matchedrole) && p.Resource.Equals(log.title))
//            		{
//            			policymatch  = true;
//            			break;
//            		}
//            	}
//                if (policymatch)
//                {
//                    report.validLogs.Add(log);
//                }
//                    else
//                {
//                    report.invalidLogs.Add(log);
//                }
//            }
//            else
//            {
//                report.invalidLogs.Add(log);
//            }
//        }
//        String response = "Status: ";
//        if(report.checkValid()) {
//        	 response = "All logs for OSP " + ospId + " are valid for the selected date period";
//        } else {
//        	 foreach (DataAccessLog log in report.invalidLogs)
//             {
//                 response += OSPId + " has made an invalid request with the resource " + log.title + ". ";
//             }
//        }
//
//        return Response.ok().entity(response).build();
//    }
//
//}

    public void logUserRequest(String requesterId, String title, String description,
            LogRequest.LogLevelEnum logLevel, LogRequest.LogPriorityEnum logPriority, LogRequest.LogTypeEnum logType,
            String affectedId, ArrayList<String> keywords) {

        ArrayList<String> words = new ArrayList<String>(Arrays.asList("OSE", "OSP"));
        for (String word : keywords) {
            words.add(word);
        }

        LogRequest logRequest = new LogRequest();
        logRequest.setUserId("ose_osps");
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
            System.out.println(response);
            Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.INFO, response + logRequest.toString());
        } catch (ApiException ex) {
            Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.SEVERE, "failed to log", ex);
        }
    }
}
