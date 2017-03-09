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
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.swagger.api.NotFoundException;
import io.swagger.model.OSPDataRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import net.minidev.json.JSONArray;

/**
 * The set of methods to perform reusable test actions on Operando modules.
 */
public class TestHelperMethods {

    /**
     * Reference to the PDB module APIs.
     */
    private String PDB_UPP_URL;
    private String PDB_OSP_URL;
    private String PDB_REGS_URL;
    private String PC_URL;

    private final Client client;

    public TestHelperMethods() {
        client = new Client();
        OperandoProperties oPropertiesMethods = new OperandoProperties();
        Properties props = oPropertiesMethods.loadDbProperties();

        if (props.getProperty("pdb.upp") != null) {
            PDB_UPP_URL = props.getProperty("pdb.upp");
        }
        if (props.getProperty("pdb.osp") != null) {
            PDB_OSP_URL = props.getProperty("pdb.osp");
        }
        if (props.getProperty("pdb.reg") != null) {
            PDB_REGS_URL = props.getProperty("pdb.reg");
        }
        if (props.getProperty("pc.api") != null) {
            PC_URL = props.getProperty("pc.api");
        }
    }

    /**
     * Test code to get a UPP from the UPP
     * API of the PDB module.
     *
     * @param userId The Operando UserId of the user
     * @return Indication of whether the called succeeded or failed.
     */
    public boolean getDemoUPP(String userId) {
        WebResource webResourcePDB = client.resource(PDB_UPP_URL + "/" + userId);
        ClientResponse policyResponse = webResourcePDB.type("application/json").get(ClientResponse.class);

        System.out.println("Get " + userId + "status code:" + policyResponse.getStatus());
        if(policyResponse.getStatus() != 200) {
            System.out.println("Get " + userId + "error message:" + policyResponse.getEntity(String.class));
            return false;
        }
        System.out.println("Get " + userId + "message content:" + policyResponse.getEntity(String.class));
        return true;
    }

    /**
     * Test code to post a UPP stored in a json file, to the UPP
     * API of the PDB module.
     *
     * @param fileLoc The filename in the resources directory.
     * @return Indication of whether the called succeeded or failed.
     */
    public boolean postDemoUPP(String fileLoc) {

        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(fileLoc);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);

            WebResource webResourcePDB = client.resource(PDB_UPP_URL);
            ClientResponse policyResponse = webResourcePDB.type("application/json").post(ClientResponse.class,
                    content);
            System.out.println("Post " + fileLoc + "status code:" + policyResponse.getStatus());
            if (policyResponse.getStatus() != 201) {
                System.out.println("Post " + fileLoc + "error message:" + policyResponse.getEntity(String.class));
                return false;
            }
        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.out.println("Post " + fileLoc + "error:" + "Post invocation failed - " + e.getLocalizedMessage());
            return false;
        }
        return true;
    }

    /**
     * Test code to delete a UPP stored in a stored in the PDB via the UPP
     * API of the PDB module.
     *
     * @param userId The Operando userId to delete.
     * @return Indication of whether the called succeeded or failed.
     */
    public boolean deleteUPP(String userId) {

        WebResource webResourcePDB = client.resource(PDB_UPP_URL+"/"+ userId);
        ClientResponse policyResponse = webResourcePDB.type("application/json").delete(ClientResponse.class);

        if (policyResponse.getStatus() != 204) {
            System.err.println(policyResponse.getEntity(String.class));
            return false;
        }
        return true;
    }

    /**
     * Update the UPP of a given Operando userId in the PDB using the UPP API.
     * @param user The operando userId of the UPP to change.
     * @param fileLoc The Json with the UPP update.
     * @return Indication if the update succeeded or not.
     */
    public boolean updateUPP(String user, String fileLoc) {

        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(fileLoc);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);

            String urlUser = PDB_UPP_URL + "/" + user;
            WebResource webResourcePDB = client.resource(urlUser);
            ClientResponse policyResponse = webResourcePDB.type("application/json").put(ClientResponse.class,
                    content);

            System.out.println("PUT " + fileLoc + "status code:" + policyResponse.getStatus());
            if (policyResponse.getStatus() != 204) {
                System.out.println("PUT " + fileLoc + "error message:" + policyResponse.getEntity(String.class));
                return false;
            }
        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.out.println("Put " + user + "error:" + "Put invocation failed - " + e.getLocalizedMessage());
            return false;
        }
        return true;
    }

    /**
     * Delete an OSP policy from the PDB using the OSP policy db API.
     * @param ospId The operando OSP id.
     * @return Indication of whether the delete succeeded.
     */
    public boolean deleteOSP(String ospId) {

            String urlUser = PDB_OSP_URL + "/" + ospId;
            WebResource webResourcePDB = client.resource(urlUser);

            ClientResponse policyResponse = webResourcePDB.type("application/json").delete(ClientResponse.class);
            System.out.println("DELETE " + ospId + "status code:" + policyResponse.getStatus());
            if (policyResponse.getStatus() != 204) {
                System.err.println("DELETE " + ospId + "error msg:" + policyResponse.getEntity(String.class));
                return false;
            }

        return true;
    }

    /**
     * Query for an OSP policy using a friendly keyword e.g. foodcoach versus
     * the ID 4534534.
     *
     * @param friendlyName The keywords to search for.
     * @return The Operando ospID for the OSP with this friendly data.
     */
    public String ospQuerybyFriendlyName(String friendlyName) {
        String ospAPI = PDB_OSP_URL+"/OSP/?filter=%7B%27policyText%27:%27" + friendlyName + "%27%7D" ;
        WebResource webResourcePDB = client.resource(ospAPI);
        ClientResponse policyResponse = webResourcePDB.type("application/json").get(ClientResponse.class);
        if(policyResponse.getStatus() != 200) {
            return null;
        }
        String filterResults = policyResponse.getEntity(String.class);
        JSONArray access_policies = JsonPath.read(filterResults, "$..[?(@.policy_text=='" + friendlyName + "')]");
        for(Object aP: access_policies) {
            String id = JsonPath.read(aP, "$.osp_policy_id");
            if(id != null)
                return id;
        }
        return null;
    }

     /**
     * Add the OSP policy to PDB module via the OSP API.
     * @param fileLoc The json file with the content
     * @return The ID of the created OSP policy
     */
    public String createOSP(String fileLoc) {

        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(fileLoc);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);

            WebResource webResourcePDB = client.resource(PDB_OSP_URL);
            ClientResponse policyResponse = webResourcePDB.type("application/json").post(ClientResponse.class,
                    content);
            System.out.println("POST " + fileLoc + "status code:" + policyResponse.getStatus());
            if (policyResponse.getStatus() != 201) {
                System.err.println("POST " + fileLoc + "error message:" + policyResponse.getEntity(String.class));
                return null;
            }
            return ospQuerybyFriendlyName(JsonPath.parse(content).read("$.policy_text", String.class));
        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("POST " + fileLoc + "Error creating UPP in pdb - " + e.getLocalizedMessage());
            return null;
        }
    }

    /**
     * Post regulation to Policy DB
     */
    public String postPCRegulation(String regID) {
        String reg = PDB_REGS_URL + "/" + regID;
        WebResource webResourcePDB = client.resource(reg);
        ClientResponse policyResponse = webResourcePDB.type("application/json").post(ClientResponse.class, "{}");

        if (policyResponse.getStatus() != 201) {
            System.out.println(policyResponse.getStatus());
            System.out.println(policyResponse.getEntity(String.class));
            return null;
        }
        return policyResponse.getEntity(String.class);
    }

    /**
     * Read PolicyEvaluation Report Field from the result of the PC evaluation.
     * @param field The field value to read from the Policy Evaluation
     * @param report The PC report to read from.
     * @return The value of the field.
     */
    public String readPolicyReport(String field, String report) {
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
    public String evaluatePC(String userId, String ospId, String accessRequest) {
        /**
         * Create a Jersey client for call the FIESTA APIs.
         */
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

    /**
     * Invoke the PC API to evaluate a given access request
     * @param userId The user whose data is being requested.
     * @param ospId The OSP requesting the data.
     * @param accessRequest The access to data request (as a json object)
     * @return The policyEvaluation report
     */
    public String evaluateBuildPC(String userId, String ospId, List<OSPDataRequest> accessRequest) {
        try {
            PolicyEvaluationService localObject = new PolicyEvaluationService();
            return localObject.evaluate(ospId, userId, accessRequest, PDB_UPP_URL).toString();
        } catch (NotFoundException ex) {
            return null;
        }
    }

    /**
     * Build a request for a doctor at asl to access the debt information
     *  in the GA_PAtient record.
     */
    public String createRequest(String[] elements, String subject, String osp) {
        List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
        for(String request: elements) {
            ospRequest.add(OSPDataRequest(request, subject, osp));
        }

        return toJSONRequest(ospRequest);
    }

    /**
     * Build a request for a doctor at asl to access the debt information
     *  in the GA_PAtient record.
     */
    public List<OSPDataRequest> createBuildRequest(String[] elements, String subject, String osp) {
        List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
        for(String request: elements) {
            ospRequest.add(OSPDataRequest(request, subject, osp));
        }

        return ospRequest;
    }

    public OSPDataRequest OSPDataRequest(String resource, String subject, String ospId){
        OSPDataRequest osD = new OSPDataRequest();
        osD.setAction(OSPDataRequest.ActionEnum.ACCESS);
        osD.setRequesterId(ospId);
        osD.setSubject(subject);
        osD.requestedUrl(resource);
        return osD;
    }

    /**
     * Change a OSP data request into json format for sending to REST API
     * @param request The array of access requests
     * @return The JSON string.
     */
    public String toJSONRequest(List<OSPDataRequest> request) {
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
}
