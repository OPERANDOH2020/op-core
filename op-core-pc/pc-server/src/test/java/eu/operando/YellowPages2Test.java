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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.swagger.model.OSPDataRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for the Yellow Pages App. There is a user 302. He has a record
 * of 10 elements on the server. Evaluate which fields a doctor and receptionist
 * can access based upon the UPP registered at the PDB.
 *
 */
public class YellowPages2Test {

    /**
     * URLs of the endpoint for PC
     */
    private static final String PC_URL = "http://integration.operando.esilab.org:8095/operando/core/pc";


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
     * Build a request for a doctor at asl to access the debt information
     *  in the GA_PAtient record.
     */
    private static String createRequest(String[] elements, String subject, String osp) {
        List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
        for(String request: elements) {
            ospRequest.add(OSPDataRequest(request, subject, osp));
        }

        return toJSONRequest(ospRequest);
    }


    private static OSPDataRequest OSPDataRequest(String resource, String subject, String ospId){
        OSPDataRequest osD = new OSPDataRequest();
        osD.setAction(OSPDataRequest.ActionEnum.ACCESS);
        osD.setRequesterId(ospId);
        osD.setSubject(subject);
        osD.requestedUrl(resource);
        return osD;
    }

    /**
     * A set of tests to check how the PC component evaluates requests
     * to the OPERANDO GAT OSP data
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /**
         * No hierarchy so we have a flat record of 7 fields.
         */
        String[] record = {"personalInfo.patient.name",
            "personalInfo.anthropometric.weight",
            "personalInfo.anthropometric.age",
            "personalInfo.patient.surname",
            "personalInfo.anthropometric.height",
            "contactInfo.patient.cityOfResidence",
            "contactInfo.patient.cellPhoneNumber",
            "personalInfo.patient.dateOfBirth",
            "personalInfo.patient.placeOfBirth",
            "contactInfo.patient.email"
        };

        String accessRequest = createRequest(record, "doctor", "YellowPages");

        /**
         * First call the PC API to evaluate a doctor's request to read 302's data record.
         * Returns the list of fields they can and cannot see.
         */
        System.out.println(accessRequest);

        String jsonResponse = evaluatePC("302", "YellowPages", accessRequest);

        System.out.println(jsonResponse);

        /**
         * Call the PC API to evaluate a receptionist's request to read 302's data record.
         * Returns the list of fields they can and cannot see.
         */
        accessRequest = createRequest(record, "receptionist", "YellowPages");
        System.out.println(accessRequest);
        jsonResponse = evaluatePC("302", "YellowPages", accessRequest);
        System.out.println(jsonResponse);

    }
}
