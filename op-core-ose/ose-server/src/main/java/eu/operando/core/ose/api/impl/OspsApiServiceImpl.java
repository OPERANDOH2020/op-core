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
import eu.operando.core.cas.client.api.DefaultApi;
import io.swagger.client.ApiClient;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;

import java.util.List;
import io.swagger.api.NotFoundException;

import eu.operando.core.ose.services.HelperMethods;
import eu.operando.core.ose.services.PDBHelperMethods;
import eu.operando.core.pdb.common.model.AccessPolicy;
import eu.operando.core.pdb.common.model.AccessReason;
import eu.operando.core.pdb.common.model.OSPConsents;
import eu.operando.core.pdb.common.model.OSPReasonPolicy;
import eu.operando.core.pdb.common.model.PolicyAttribute;
import eu.operando.core.pdb.common.model.UserPrivacyPolicy;
import io.swagger.api.ApiResponseMessage;
import io.swagger.api.OspsApiService;
import io.swagger.client.api.LogApi;
import io.swagger.client.model.LogRequest;
import io.swagger.client.model.LogRequest.LogLevelEnum;
import io.swagger.client.model.LogRequest.LogPriorityEnum;
import io.swagger.client.ApiException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;


public class OspsApiServiceImpl extends OspsApiService {

    // LogDB
    LogApi logApi;
    // AAPI
    DefaultApi aapiClient;

    private String oseOSPSSId = "ose/osps/.*";
//    private String logdbSId = "pdb/OSP/.*";
    private String aapiBasePath = "http://integration.operando.esilab.org:8135/operando/interfaces/aapi";
    private String logdbBasePath = "http://integration.operando.esilab.org:8090/operando/core/ldb";

    private String ospsLoginName = "panos";
    private String ospsLoginPassword = "operando";
    private String stHeaderName = "Service-Ticket";
    private String logdbST = "";
    long ticketLifeTime = 1000L * 60 * 60;

    Timer timer;

    Properties prop = null;
    private HelperMethods helpServices;
    private PDBHelperMethods pdbServices;

    public OspsApiServiceImpl() {
        super();

        helpServices = new HelperMethods();
        pdbServices = new PDBHelperMethods();
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
     * Where a category reason has changed for a role, this goes through a UPP
     * and updates their policies to false. So they
     */
    private String resetCategoryPolicies(String Category, String role, UserPrivacyPolicy upp, String userId, String ospId, String friendlyId) {
        /**
         * Find the fields specific to this category and role
         */
        List<String> resources = new ArrayList<String>();
        OSPPrivacyPolicy osp_access_policies = pdbServices.getSpecificOSP(ospId);
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

    /**
     * The Web REST API operation to handle a change of the reason policy by
     * the OSP. This is typically called by the PDB to perform a check before
     * a direct change of the database.
     * @param ospId The unique generated OSP ID (not the friendly id).
     * @param ospPrivacyText The new privacy reason described in the access reason json object.
     * @param securityContext The Jax-rs context information.
     * @return The HTTP response.
     * @throws NotFoundException
     */
    @Override
    public Response ospsOspIdPrivacytextPut(String ospId, AccessReason ospPrivacyText, SecurityContext securityContext)
            throws NotFoundException {

        /**
         * Find the Access Policy for the OSP
         */
        OSPPrivacyPolicy osp_access_policies = pdbServices.getSpecificOSP(ospId);

        /**
         * Build a change message to use in notifications.
         */
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
        String all_user_policies =  pdbServices.getAllUsers();

        if(all_user_policies == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Could not notify users of changed policy").build();
        }
        String friendlyId = osp_access_policies.getPolicyUrl();
        /**
         * Calculate the users subscribed to this OSP.
         */
        List<String> jsonUsers = pdbServices.getSubscribedUsersList(friendlyId, all_user_policies);

        /**
         * For every user notify them of the change.
         */
        for(String userId: jsonUsers) {
            logUserRequest(ospId, "OSP Privacy Policy Change",
                 changeMessage,
                LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogRequest.LogTypeEnum.NOTIFICATION, userId,
                new ArrayList<String>(Arrays.asList("POST")));
            UserPrivacyPolicy upp = pdbServices.getUserPrivacyPolicy(userId);
            String uppJson = resetCategoryPolicies(ospPrivacyText.getDatatype(), ospPrivacyText.getDatauser(), upp, userId, ospId, friendlyId);
            ObjectMapper mapper = new ObjectMapper();

            try {
                //JSON from file to Object
                upp = mapper.readValue(uppJson, UserPrivacyPolicy.class);
            } catch (IOException ex) {
                Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            /**
             * Make a change to opt them out - i.e. make sure they reconsent to this change.
             */
            pdbServices.putUserPrivacyPolicy(userId, uppJson);
        }

        return Response.status(Response.Status.NO_CONTENT).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "Successful response, The privacy text update analysis has begun.")).build();
    }

    /**
     * The Web REST API operation to handle a change of the access policy by
     * the OSP. This is typically called by the PDB to perform a check before
     * a direct change of the database.
     * @param ospId The unique generated OSP ID (not the friendly id).
     * @param ospPolicy The new privacy policy described in the policy json object.
     * @param securityContext The Jax-rs context information.
     * @return The HTTP response.
     * @throws NotFoundException
     */
    @Override
    public Response ospsOspIdPut(String ospId, OSPPrivacyPolicy ospPolicy, SecurityContext securityContext)
            throws NotFoundException {

        OSPReasonPolicy reasonPol = pdbServices.getOSPReasonPolicy(ospId);
        List<AccessReason> reasonPolicies = reasonPol.getPolicies();
        /**
         * Check there is a reason for each policy
         */
        List<AccessPolicy> policies = ospPolicy.getPolicies();
        for(AccessPolicy policy: policies){
            List<PolicyAttribute> attributes = policy.getAttributes();
            boolean hasCategory = false;
            boolean hasReason = false;
            for (PolicyAttribute attVel: attributes) {
                if( attVel.getAttributeName().equalsIgnoreCase("category")) {
                    hasCategory = true;
                    String category = attVel.getAttributeValue();
                    // Search for reason policy to match
                    for(AccessReason reason: reasonPolicies) {
                        if(reason.getDatauser().equalsIgnoreCase(policy.getSubject()) &&
                                reason.getDatatype().equalsIgnoreCase(category) ){
                            hasReason = true;
                        }
                    }
                }
            }
            if(!hasCategory) {
                String PAchangeMessage = ospId + " has changed their policy. " + policy.getSubject() + " " + policy.getAction().toString() + " " + policy.getResource() + " is not assigned a reason category.";
                logUserRequest(ospId, "OSP Privacy Policy Change", PAchangeMessage, LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogRequest.LogTypeEnum.NOTIFICATION, "PrivacyAnalyst", new ArrayList<String>(Arrays.asList("POST")));

                String OSPchangeMessage = ospId + "Review your policy change " + policy.getSubject() + " " + policy.getAction().toString() + " " + policy.getResource() + " is not assigned a reason category.";
                logUserRequest(ospId, "OSP Privacy Policy Change", OSPchangeMessage, LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogRequest.LogTypeEnum.NOTIFICATION, "PrivacyAnalyst", new ArrayList<String>(Arrays.asList("POST")));
            }
            if(!hasReason) {
                String PAchangeMessage = ospId + " has changed their policy. " + policy.getSubject() + " " + policy.getAction().toString() + " " + policy.getResource() + " does not have a reason in their reason policy.";
                logUserRequest(ospId, "OSP Privacy Policy Change", PAchangeMessage, LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogRequest.LogTypeEnum.NOTIFICATION, "PrivacyAnalyst", new ArrayList<String>(Arrays.asList("POST")));

                String OSPchangeMessage = ospId + "Review your policy change " + policy.getSubject() + " " + policy.getAction().toString() + " " + policy.getResource() + " does not have a reason for access in the reason policy.";
                logUserRequest(ospId, "OSP Privacy Policy Change", OSPchangeMessage, LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogRequest.LogTypeEnum.NOTIFICATION, "PrivacyAnalyst", new ArrayList<String>(Arrays.asList("POST")));
            }
        }
        /**
         * Find users subscribed to this OSP so we can notify them of changes.
         */
        String all_user_policies =  pdbServices.getAllUsers();
        if(all_user_policies == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Could not notify users of changed policy").build();
        }
        String friendlyId = ospPolicy.getPolicyUrl();

        List<String> jsonUsers = pdbServices.getSubscribedUsersList(friendlyId, all_user_policies);

        /**
         * Get the current policy from the PDB. Analyse and find the set of access policies that have changed.
         * New role, new field, new data resource.
         */
        OSPPrivacyPolicy ospOldPolicy = pdbServices.getSpecificOSP(ospId);
        List<AccessPolicy> oldPolicies = (List<AccessPolicy>) ((ArrayList<AccessPolicy>) ospOldPolicy.getPolicies()).clone();
        List<AccessPolicy> oldPoliciesContent = (List<AccessPolicy>) ((ArrayList<AccessPolicy>) ospOldPolicy.getPolicies()).clone();
        List<AccessPolicy> newPolicies = (List<AccessPolicy>) ((ArrayList<AccessPolicy>) ospPolicy.getPolicies()).clone();
        List<AccessPolicy> newPoliciesContent = (List<AccessPolicy>) ((ArrayList<AccessPolicy>) ospPolicy.getPolicies()).clone();
        List<AccessPolicy> changedPolicies = new ArrayList<AccessPolicy>();
        List<AccessPolicy> deletedPolicies = new ArrayList<AccessPolicy>();

        System.out.println("Old Content array@ elements: " + oldPoliciesContent.size());
        System.out.println("New Content array@ elements: " + newPoliciesContent.size());
        for(AccessPolicy newPolicy: newPolicies) {
            boolean change = false;
            boolean match = false;
//            oldPoliciesContent = (List<AccessPolicy>) ((ArrayList<AccessPolicy>) ospOldPolicy.getPolicies()).clone();
            for(AccessPolicy oldPolicy: oldPolicies) {
                // check for complete match - an access policy is unique by resource, subject, action
                if( (oldPolicy.getResource().equals(newPolicy.getResource())) &&
                     (oldPolicy.getSubject().equals(newPolicy.getSubject())) &&
                       (oldPolicy.getAction().equals(newPolicy.getAction())) ) {
                    System.out.println("Match: " + oldPolicy.getSubject() + " : " + oldPolicy.getAction().toString() + ": " + oldPolicy.getResource());
                    match = true;

                    /**
                     * If the permission has changed.
                     */
                    if(!oldPolicy.getPermission().equals(newPolicy.getPermission())) {
                        System.out.println("Match: " + oldPolicy.getSubject() + " : " + oldPolicy.getAction().toString() + ": " + oldPolicy.getResource() + " Permisson changed");
                        change = true;
                    }
                    /**
                     * If the category/reason has changed.
                     */
                    List<PolicyAttribute> attributes = oldPolicy.getAttributes();
                    String categoryVal = "";
                    for(PolicyAttribute polA: attributes) {
                        if(polA.getAttributeName().equalsIgnoreCase("category")) {
                            categoryVal = polA.getAttributeValue();
                        }
                    }
                    List<PolicyAttribute> nAttributes = newPolicy.getAttributes();
                    for(PolicyAttribute polA: nAttributes) {
                        if(polA.getAttributeName().equalsIgnoreCase("category")) {
                            if(!categoryVal.equalsIgnoreCase(polA.getAttributeValue())) {
                                change = true;
                            }
                        }
                    }
                    oldPoliciesContent.remove(oldPolicy);
                    System.out.println("Old policy: " + oldPoliciesContent.size());
                }

            }
            if(match && !change){
                newPoliciesContent.remove(newPolicy);
            }
            else if (match && change) {
                changedPolicies.add(newPolicy);
                newPoliciesContent.remove(newPolicy);
            }

        }

        /**
         * For each deleted policy
         */
        System.out.println("Delete Content array@ elements: " + oldPoliciesContent.size());
        for(AccessPolicy oldPolicy: oldPoliciesContent) {
            System.out.println("Policy to delete: " + oldPolicy.getSubject() + " : " + oldPolicy.getAction().toString() + ": " + oldPolicy.getResource() + " Permisson changed");

            String changeMessage = "The OSP (" + ospPolicy.getPolicyText() + ") has updated their privacy policy. They have deleted the access policy statement: ";
            changeMessage += "A " + oldPolicy.getSubject() + " can " + oldPolicy.getAction().toString() + " your " + oldPolicy.getResource() + " data field.";
            changeMessage += "Your access preferences have been updated to delete all consents given to this policy statement.";

            // Get the affected users and delete this policy statment from their UPP
            for(String userId: jsonUsers) {
                logUserRequest(ospId, "OSP Privacy Policy Change",
                     changeMessage,
                    LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogRequest.LogTypeEnum.NOTIFICATION, userId,
                    new ArrayList<String>(Arrays.asList("POST")));
                UserPrivacyPolicy upp = pdbServices.getUserPrivacyPolicy(userId);
                String uppJson = pdbServices.deleteAccessPolicy(oldPolicy, upp, userId, friendlyId);
                ObjectMapper mapper = new ObjectMapper();

                try {
                    //JSON from file to Object
                    upp = mapper.readValue(uppJson, UserPrivacyPolicy.class);
                } catch (IOException ex) {
                    Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
//                pdbServices.putUserPrivacyPolicy(userId, uppJson);
            }
        }

        /**
         * For each new policy
         */
        for(AccessPolicy newPolicy: newPoliciesContent) {
            System.out.println("Policy to add: " + newPolicy.getSubject() + " : " + newPolicy.getAction().toString() + ": " + newPolicy.getResource() + " Permisson changed");

            String changeMessage = "The OSP (" + ospPolicy.getPolicyText() + ") has updated their privacy policy. They have added the access policy statement: ";
            changeMessage += "A " + newPolicy.getSubject() + " can " + newPolicy.getAction().toString() + " your " + newPolicy.getResource() + " data field.";
            changeMessage += "Please visit your access preferences to review these changes.";

            // Get the affected users and notify the user
            for(String userId: jsonUsers) {
                logUserRequest(ospId, "OSP Privacy Policy Change",
                     changeMessage,
                    LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogRequest.LogTypeEnum.NOTIFICATION, userId,
                    new ArrayList<String>(Arrays.asList("POST")));
            }
        }

        /**
         * For each changed policy
         */
        for(AccessPolicy oldPolicy: changedPolicies) {
            System.out.println("Policy to change: " + oldPolicy.getSubject() + " : " + oldPolicy.getAction().toString() + ": " + oldPolicy.getResource() + " Permisson changed");

            String changeMessage = "The OSP (" + ospPolicy.getPolicyText() + ") has updated their privacy policy. They have changed the access policy statement: ";
            changeMessage += "A " + oldPolicy.getSubject() + " can " + oldPolicy.getAction().toString() + " your " + oldPolicy.getResource() + " data field.";
            changeMessage += "Your access preferences have been updated to remove consents given to this policy statement. Please visit your access preferences to update your consent";

            // Get the affected users and delete this policy statment from their UPP
            for(String userId: jsonUsers) {
                logUserRequest(ospId, "OSP Privacy Policy Change",
                     changeMessage,
                    LogLevelEnum.INFO, LogPriorityEnum.NORMAL, LogRequest.LogTypeEnum.NOTIFICATION, userId,
                    new ArrayList<String>(Arrays.asList("POST")));
                UserPrivacyPolicy upp = pdbServices.getUserPrivacyPolicy(userId);
                String uppJson = pdbServices.changeAccessPolicy(oldPolicy, upp, userId, friendlyId);
                ObjectMapper mapper = new ObjectMapper();

                try {
                    //JSON from file to Object
                    upp = mapper.readValue(uppJson, UserPrivacyPolicy.class);
                } catch (IOException ex) {
                    Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                pdbServices.putUserPrivacyPolicy(userId, uppJson);
            }
        }

        return Response.status(Response.Status.OK).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "Successful response. The privacy policy has been received and being processed. Information will be sent via other operation sequences."))
                .build();

    }


//    @Override
//    public Response ospsOspIdAuditGet(String ospId, String start, String end, SecurityContext securityContext) throws NotFoundException {
//        String response = "{\"osp_id\": \"" + ospId + "\"," +
//                "\"start\": \"" + start + "\"," +
//                "\"end\": \"" + end + "\"}";
//
//        return Response.ok().entity(response).build();
//    }

    @Override
    public Response ospsOspIdAuditGet(String ospId, String start, String end, SecurityContext securityContext) throws NotFoundException {

        try {
            class  Report{
                ArrayList<LogResponse> validLogs =  new ArrayList<LogResponse>();
                ArrayList<LogResponse> invalidLogs= new ArrayList<LogResponse>();
                Boolean checkValid()
                {
                    if(invalidLogs.size() == 0)
                    {
                        return true;
                    }
                    return false;
                }
            }

            //creates datetime objects for the start date and end date selected by the user on the html page
            Object instance = null;
            DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
            Date startDate = df.parse(start);
            Date endDate = df.parse(end);

            OSPPrivacyPolicy matchingOsp = pdbServices.getSpecificOSP(ospId);

            HashSet<String> roles = new HashSet<String>();

            for (AccessPolicy policy: matchingOsp.getPolicies())
            {
                roles.add(policy.getSubject());
            }

            //get the data access logs
            ArrayList<LogResponse> logs = new ArrayList<LogResponse>(); //instance.GetDataAccessLogs(OspPolicyId);
            ArrayList<LogResponse> logsToCheck = new ArrayList<LogResponse>();

            //sorts by logs that fall within the region for the dates
            for (LogResponse log: logs)
            {
                Date currentLogDate = df.parse(log.getLogDate());
                if ( (currentLogDate.compareTo(startDate)>0) &&
                        (currentLogDate.compareTo(endDate) <0) &&
                        log.getAffectedUserId().equalsIgnoreCase(matchingOsp.getOspPolicyId()))
                {
                    logsToCheck.add(log);
                }
            }
            //creates a report object
            Report report = new Report();
            //goes through each log that matches the dates
            for (LogResponse log: logsToCheck)
            {
                boolean found = false;
                String matchedrole = "nothing";
                //checks if there is a role matching the role accessed according to the logs
                for (String role: roles)
                {
                    if (log.getDescription().contains(role))
                    {
                        found = true;
                        matchedrole = role;
                        break;
                    }
                }
                //runs if there is a matching role
                if (found)
                {
                    boolean policymatch = false;
                    //checks if there is a resource and role which matches (the policy for this log)
                    for(AccessPolicy p: matchingOsp.getPolicies()){
                        if(p.getSubject().equals(matchedrole) && p.getResource().equals(log.getTitle()))
                        {
                            policymatch  = true;
                            break;
                        }
                    }
                    if (policymatch)
                    {
                        report.validLogs.add(log);
                    }
                    else
                    {
                        report.invalidLogs.add(log);
                    }
                }
                else
                {
                    report.invalidLogs.add(log);
                }
            }
            String response = "Status: ";
            if(report.checkValid()) {
                response = "All logs for OSP " + ospId + " are valid for the selected date period";
            } else {
                for (LogResponse log: report.invalidLogs)
                {
                    response += matchingOsp.getPolicyUrl() + " has made an invalid request with the resource " + log.getTitle() + ". ";
                }
            }

            return Response.ok().entity(response).build();
        } catch (ParseException ex) {
            Logger.getLogger(OspsApiServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity(new ApiResponseMessage(ApiResponseMessage.OK,
                "Error response, audit failed.")).build();
        }
    }


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
