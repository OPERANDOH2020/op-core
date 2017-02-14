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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.swagger.model.OSPDataRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The PC to PDB integration test, for the user data access workflow. These
 * tests validate that when requests from an OSP to access data are evaluated
 * by the PC, they match the preferences of the two scenario users: Pat and Pete.
 *
 */
public class YellowPagesTest {

    /**
     * URLs of the endpoints for PC and PDB. The workflow must work with these
     * two endpoints to test that the two modules hosted at these endpoints
     * integrate correctly with one another.
     */
    private static final String PDB_URL = "http://integration.operando.esilab.org:8096/operando/core/pdb";

    private static final String PC_URL = "http://integration.operando.esilab.org:8095/operando/core/pc";

    /**
     * Constructor for stateful method calls.
     */
    public YellowPagesTest() {

    }

    /**
     * Read PolicyEvaluation Report Field.
     */
    private static String readPolicyReport(String field, String report) {

        try {
            JsonFactory f = new JsonFactory();
            JsonParser jp = f.createParser(report);
            jp.nextToken();
            while (jp.hasCurrentToken()){
                if(jp.nextToken() == JsonToken.FIELD_NAME) {
                    String fieldname = jp.getCurrentName();
                    if(fieldname.equalsIgnoreCase(field)) {
                        jp.nextToken();
                        return jp.getValueAsString();
                    }
                }
            }
            jp.close();
            return null;
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * Invoke the PC API to evaluate a given access request
     * @param userId The user whose data is being requested.
     * @param ospId The OSP requesting the data.
     * @param accessRequest The access to data request (as a json object)
     * @return The policyEvaluation report
     */
    private static String evaluatePC(String userId, String ospId, String accessRequest) {
        /**
         * Create a Jersey client for call the FIESTA APIs.
         */
        Client client = Client.create();

        String urlInv = "/osp_policy_evaluator?user_id=" + userId +"&osp_id=" + ospId;
        /**
         * Make a call to the IoT-Service API without credentials and get
         * and unauthorized response.
         */
        WebResource pcService = client.resource(PC_URL + urlInv);

        ClientResponse pcServiceResponse = pcService.type("application/json").post(ClientResponse.class, accessRequest);
        if (pcServiceResponse.getStatus() != 200) {
           System.err.println("Error: the evaluation call produced an error");
           System.err.println(pcServiceResponse.getStatus() + ":" + pcServiceResponse.getEntity(String.class));
        }
        return pcServiceResponse.getEntity(String.class);
    }

    private static String toJSONRequest(List<OSPDataRequest> request) {
        String jsonRequest = "[";
        for(OSPDataRequest dReq: request) {
            jsonRequest += "{";
            jsonRequest += "\"requester_id\": \""+ dReq.getRequesterId() + "\", ";
            jsonRequest += "\"subject\": \""+ dReq.getSubject() + "\", ";
            jsonRequest += "\"requested_url\": \""+ dReq.getRequestedUrl()+"\", ";
            jsonRequest += "\"action\": \""+ dReq.getAction().toString() +"\", ";
            jsonRequest += "\"attributes\": []";
            jsonRequest += "},";
        }
        jsonRequest = jsonRequest.substring(0, jsonRequest.length()-1);
        jsonRequest += "]";
        return jsonRequest;
    }

     /**
     * Build a request for a doctor at yellow pages to access
     * the user name data
     */
    private static String createRequest() {
        List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
        OSPDataRequest osD = new OSPDataRequest();
        osD.setAction(OSPDataRequest.ActionEnum.ACCESS);
        osD.setRequesterId("YellowPages");
        osD.setSubject("Doctor");
        osD.requestedUrl("/personal_information/full_name/given_name");
        ospRequest.add(osD);

        return toJSONRequest(ospRequest);
    }

    /**
     * Build a request for a doctor at yellow pages to access
     * the user data - both
     */
    private static String createRequestOne() {
        List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
            OSPDataRequest osD = new OSPDataRequest();
            osD.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD.setRequesterId("YellowPages");
            osD.setSubject("Doctor");
            osD.requestedUrl("/personal_information/full_name/given_name");
            ospRequest.add(osD);

            OSPDataRequest osD2 = new OSPDataRequest();
            osD2.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD2.setRequesterId("YellowPages");
            osD2.setSubject("Doctor");
            osD2.requestedUrl("/personal_information/anthropometric_data");
            ospRequest.add(osD2);

        return toJSONRequest(ospRequest);
    }

    /**
     * Personal information - 2 request
     * @return The data values list.
     */
    private static String createRequestTwo() {
        List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
            OSPDataRequest osD = new OSPDataRequest();
            osD.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD.setRequesterId("yellowpages");
            osD.setSubject("Receptionist");
            osD.requestedUrl("/personal_information/full_name/given_name");
            ospRequest.add(osD);

            OSPDataRequest osD2 = new OSPDataRequest();
            osD2.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD2.setRequesterId("yellowpages");
            osD2.setSubject("Receptionist");
            osD2.requestedUrl("/personal_information/anthropometric_data");
            ospRequest.add(osD2);
        return toJSONRequest(ospRequest);
    }

    /**
     * This is the workflow that describes and validates the integration
     * of the PC and PDB components.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        YellowPagesTest odpdb = new YellowPagesTest();
        TestHelperMethods tMethods = new TestHelperMethods();

        if(tMethods.deleteUPP("pete2", PDB_URL)){
            System.out.println("Pete2 UPP was in PDB - now deleted");
        }
        else {
            System.out.println("PDB does not contain Pete UPP");
        }

        if(tMethods.deleteUPP("pat2", PDB_URL)){
            System.out.println("Pat2 UPP was in PDB - now deleted");
        }
        else {
            System.out.println("PDB does not contain Pat2 UPP");
        }

        /**
         * First call the PC API to evaluate a request to use Pat's data.
         * Pat has no UPP in the database, therefore assert that the
         * response is a no user response.
         */
        String accessRequest = createRequest();
        System.out.println(accessRequest);

        String jsonResponse = evaluatePC("pat2", "YellowPages", accessRequest);
        System.out.println(jsonResponse);

        if(!readPolicyReport("status", jsonResponse).equalsIgnoreCase("false")){
            System.err.println("Integration test faild: Status must be false");
            System.exit(-1);
        }

        if(!readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("NO_POLICY")){
            System.err.println("Integration test faild: Compliance must be NO_POLICY");
            System.exit(-1);
        }

        // Directly using the CORE service

        tMethods.loadDemoUPP("pat2", "pat.json", PDB_URL);
        tMethods.loadDemoUPP("pete2", "pete.json", PDB_URL);

        /**
         * Single Request
         */
        jsonResponse = evaluatePC("pete2", "YellowPages", accessRequest);
        System.out.println(jsonResponse);

        if(!readPolicyReport("status", jsonResponse).equalsIgnoreCase("true")){
            System.err.println("Integration test faild: Status must be true");
            System.exit(-1);
        }

        if(!readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("VALID")){
            System.err.println("Integration test faild: Compliance must be VALID");
            System.exit(-1);
        }

        jsonResponse = evaluatePC("pat2", "YellowPages", accessRequest);
        System.out.println(jsonResponse);

        if(!readPolicyReport("status", jsonResponse).equalsIgnoreCase("true")){
            System.err.println("Integration test faild: Status must be true");
            System.exit(-1);
        }

        if(!readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("VALID")){
            System.err.println("Integration test faild: Compliance must be VALID");
            System.exit(-1);
        }

        /**
         * Double request by doctor.
         */

        String accessRequest2 = createRequestOne();
        System.out.println(accessRequest2);

        jsonResponse = evaluatePC("pete2", "YellowPages", accessRequest2);
        System.out.println(jsonResponse);

        if(!readPolicyReport("status", jsonResponse).equalsIgnoreCase("true")){
            System.err.println("Integration test faild: Status must be true");
            System.exit(-1);
        }

        if(!readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("VALID")){
            System.err.println("Integration test faild: Compliance must be VALID");
            System.exit(-1);
        }

        jsonResponse = evaluatePC("pat2", "YellowPages", accessRequest2);
        System.out.println(jsonResponse);

        if(!readPolicyReport("status", jsonResponse).equalsIgnoreCase("true")){
            System.err.println("Integration test faild: Status must be true");
            System.exit(-1);
        }

        if(!readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("VALID")){
            System.err.println("Integration test faild: Compliance must be VALID");
            System.exit(-1);
        }

        /**
         * Double request by receptionist.
         */

        String accessRequest3 = createRequestTwo();
        System.out.println(accessRequest3);

        jsonResponse = evaluatePC("pete2", "YellowPages", accessRequest3);
        System.out.println(jsonResponse);

        if(!readPolicyReport("status", jsonResponse).equalsIgnoreCase("true")){
            System.err.println("Integration test faild: Status must be true");
            System.exit(-1);
        }

        if(!readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("VALID")){
            System.err.println("Integration test faild: Compliance must be VALID");
            System.exit(-1);
        }

        jsonResponse = evaluatePC("pat2", "YellowPages", accessRequest3);
        System.out.println(jsonResponse);

        if(!readPolicyReport("status", jsonResponse).equalsIgnoreCase("false")){
            System.err.println("Integration test faild: Status must be false");
            System.exit(-1);
        }

        if(!readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("PREFS_CONFLICT")){
            System.err.println("Integration test faild: Compliance must be PREFS_CONFLICT");
            System.exit(-1);
        }

        /**
         * Update the UPPs.
         */
        tMethods.updateUPP("pat2", "pat_update.json", PDB_URL);
        tMethods.updateUPP("pete2", "pete_update.json", PDB_URL);

         /**
         * Double request by receptionist.
         */

        jsonResponse = evaluatePC("pat2", "YellowPages", accessRequest3);
        System.out.println(jsonResponse);

        if(!readPolicyReport("status", jsonResponse).equalsIgnoreCase("true")){
            System.err.println("Integration test faild: Status must be true");
            System.exit(-1);
        }

        if(!readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("VALID")){
            System.err.println("Integration test faild: Compliance must be VALID");
            System.exit(-1);
        }

        jsonResponse = evaluatePC("pete2", "YellowPages", accessRequest3);
        System.out.println(jsonResponse);

        if(!readPolicyReport("status", jsonResponse).equalsIgnoreCase("false")){
            System.err.println("Integration test faild: Status must be false");
            System.exit(-1);
        }

        if(!readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("PREFS_CONFLICT")){
            System.err.println("Integration test faild: Compliance must be PREFS_CONFLICT");
            System.exit(-1);
        }
    }

}
