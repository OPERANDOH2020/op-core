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


import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.swagger.model.OSPDataRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

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
    private static final String PDB_URL = "http://localhost:8080/pdb";

    private static final String PC_URL = "http://integration.operando.esilab.org:8095/operando/core/pc";

    private static final String OSE_URL = "http://localhost:8090/osps";

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
     * The Policy Evaluation Component depends upon multiple entries to different
     * components. Hence, testing and unit testing is impossible without
     * full integration testing. Hence, this component contains a set of
     * demo user preferences.
     *
     * This method loads these demo UPPs into local memory
     *
     * @param name The name of the user id to load into memory.
     * @param fileLoc The filename in the resources directory.
     */
    private void loadDemoUPP(String name, String fileLoc) {

        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(fileLoc);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);
            String urlUser = PDB_URL+"/user_privacy_policy/";
            Client client = new Client();
            WebResource webResourcePDB = client.resource(urlUser);

            ClientResponse policyResponse = webResourcePDB.type("application/json").post(ClientResponse.class,
                    content);

            if (policyResponse.getStatus() != 201) {
                System.err.println(policyResponse.getEntity(String.class));
                throw new RuntimeException("Failed : HTTP error code : "
                        + policyResponse.toString());
            }

            CloseableHttpClient httpclient = HttpClients.createDefault();
                HttpGet httpget = new HttpGet(urlUser+name);
                CloseableHttpResponse response1 = httpclient.execute(httpget);

            HttpEntity entity = response1.getEntity();
                System.out.println(response1.getStatusLine().getStatusCode());
                if(response1.getStatusLine().getStatusCode()==404) {
                    return;
                }

                System.out.println(EntityUtils.toString(entity));

        }
        catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("Error reading Configuration properties file");

            // Add logging code to log an error configuring the API on startup
        }
    }

    private boolean deleteUPP(String userId) {

            String urlUser = PDB_URL+"/user_privacy_policy/" + userId;
            Client client = new Client();
            WebResource webResourcePDB = client.resource(urlUser);

            ClientResponse policyResponse = webResourcePDB.type("application/json").delete(ClientResponse.class);

            if (policyResponse.getStatus() != 200) {
                System.err.println(policyResponse.getEntity(String.class));
                return false;
            }

        return true;
    }

    private boolean updateOSP(String ospId, String fileLoc) {

        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(fileLoc);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);
            String urlUser = OSE_URL+"/"+ospId+"/";
            Client client = new Client();
            WebResource webResourcePDB = client.resource(urlUser);

            ClientResponse policyResponse = webResourcePDB.type("application/json").put(ClientResponse.class,
                    content);
            System.err.println(policyResponse.getEntity(String.class));
            if (policyResponse.getStatus() != 200) {
                System.err.println(policyResponse.getEntity(String.class));
                return false;
            }
        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("Error updating OSP policy via OSE component");

        }

        return true;
    }

    /**
     * This is the workflow that describes and validates the integration
     * of the PC and PDB components.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        YellowPagesTest odpdb = new YellowPagesTest();

        odpdb.loadDemoUPP("pat", "pat.json");
        odpdb.loadDemoUPP("pete", "pete.json");

        odpdb.updateOSP("YellowPages", "yellowpages.json");

    }

}
