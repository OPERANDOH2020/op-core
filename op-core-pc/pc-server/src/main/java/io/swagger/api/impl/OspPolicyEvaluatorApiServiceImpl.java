package io.swagger.api.impl;

import io.swagger.api.*;

import io.swagger.model.OSPDataRequest;
import io.swagger.model.PolicyEvaluationReport;

import java.util.List;
import io.swagger.api.NotFoundException;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class OspPolicyEvaluatorApiServiceImpl extends OspPolicyEvaluatorApiService {

    private final static String PDB_BASEURL = "http://192.9.206.155:8080/pdb-server/policy_database";

    @Override
    public Response ospPolicyEvaluatorPost(String userId, String ospId, List<OSPDataRequest> ospRequest, SecurityContext securityContext)
            throws NotFoundException {

        System.out.println("New Evaluation Request");
        System.out.println("--------------------------------------------------");
        System.out.println("Evaluating User Policy: " + userId);
        System.out.println("Request from: " + ospId);

        /**
         * The response to be sent - yes/no along with a report of why something
         * has been denied.
         */
        PolicyEvaluationReport rp = new PolicyEvaluationReport();
        rp.setStatus("true");
            rp.setCompliance("Unknown user: no policy preferences in policy db");
            return Response.ok(rp.toString(), MediaType.APPLICATION_JSON).build();

//        UserPrivacyPolicy userPrivacyPolicyUserIdGet;
//        try {
//            // Read the User Privacy Policy from the Policy DB
//            GETApi pdbClient = new GETApi(new ApiClient().setBasePath(PDB_BASEURL));
//            userPrivacyPolicyUserIdGet = pdbClient.userPrivacyPolicyUserIdGet(userId);
//            System.out.println(userPrivacyPolicyUserIdGet.getUserId());
//        } catch (io.swagger.client.ApiException ex) {
//            // The request can be allowed because there is no policy in the database to contradict the request.
//            rp.setStatus("true");
//            rp.setCompliance("Unknown user: no policy preferences in policy db");
//            return Response.ok(rp.toString(), MediaType.APPLICATION_JSON).build();
//        }
//        catch (Exception ex) {
//            // The request can be allowed because there is no policy in the database to contradict the request.
//            rp.setStatus("true");
//            rp.setCompliance("Unknown user: no policy preferences in policy db");
//            return Response.ok(rp.toString(), MediaType.APPLICATION_JSON).build();
//        }
//
//        // Evaluate each of the data requests in the list against the UPP.
//
//        /**
//         * Hard coded classification. Maps data types on privacy categories.
//         */
//        HashMap<String, String> dataClassMap = new HashMap<String, String>();
//        dataClassMap.put("FirstName","Basic Information");
//        dataClassMap.put("LastName","Basic Information");
//        dataClassMap.put("Sex","Basic Information");
//        dataClassMap.put("DateOfBirth","Basic Information");
//        dataClassMap.put("Weight","Medical Information");
//        dataClassMap.put("Height","Medical Information");
//
//        // Get the access rules for this OSP
//        List<OSPConsents> subscribedOspPolicies = userPrivacyPolicyUserIdGet.getSubscribedOspPolicies();
//        List<AccessPolicy> accessPolicies = new ArrayList<AccessPolicy>();
//        for (OSPConsents oc: subscribedOspPolicies) {
//            if(oc.getOspId().equalsIgnoreCase(ospId)) {
//                accessPolicies = oc.getAccessPolicies();
//            }
//        }
//
//        // Evaluate the Request against the OSP access policies
//        boolean permit = true;
//        String errorString = "";
//        for (OSPDataRequest r: ospRequest) {
//            String Category = dataClassMap.get(r.getRequestedUrl());
//            for(AccessPolicy aP: accessPolicies) {
//                if(aP.getResource().equalsIgnoreCase(Category)) { // Check the category
//                    if(aP.getSubject().equalsIgnoreCase(r.getSubject())) { // Check the subject
//                        if (aP.getAction().equals(r.getAction())){ // Check the action
//                            if(!aP.getPermission()) {
//                                permit = false;
//                                errorString+="User doesn't permit ["+r.getAction().toString()+"] to ["+Category+"] data\n";
//                            }
//                            break;
//                        }
//                    }
//                }
//            }
//        }
//
//        if(permit) {
//            rp.setStatus("true");
//            rp.setCompliance("fully complies");
//        }
//        else {
//            rp.setStatus("false");
//            rp.setCompliance(errorString);
//        }
//
//        String policyReport = rp.toString();
//        System.out.println(policyReport);
//
//        return Response.ok(policyReport, MediaType.APPLICATION_JSON).build();
    }
}
