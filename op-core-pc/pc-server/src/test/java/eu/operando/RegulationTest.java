/////////////////////////////////////////////////////////////////////////
//
// ? University of Southampton IT Innovation Centre, 2016
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
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import eu.operando.core.pdb.common.model.PrivacyRegulation;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import net.minidev.json.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * A set of unit tests to check the post/put and delete operations of the
 * PDB User Privacy Policy API.
 *
 * Uses example JSON policies from the yellow pages application.
 *
 */
public class RegulationTest {

    private final String PDB_URL = "http://integration.operando.esilab.org:8096/operando/core/pdb/regulations/";

    /**
     * Method to post a regulation
     *
     * @param regId The id of the regulation.
     * @param fileLoc The filename in the resources of the json regulation.
     * @return RegID
     */
    private String postRegulation(String fileLoc) {
        String regId = "";
        InputStream fis = null;
        try {
            /**
             * Read the regulation policy named into a Java String.
             */
            fis = this.getClass().getClassLoader().getResourceAsStream(fileLoc);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);

            /**
             * Perform a simple post with json content.
             */
            Client client = new Client();
            WebResource webResourcePDB = client.resource(PDB_URL);
            ClientResponse policyResponse = webResourcePDB.type("application/json").post(ClientResponse.class,
                    content);

            ObjectMapper mapper = new ObjectMapper();
            try {
                PrivacyRegulation reg = mapper.readValue(policyResponse.getEntity(String.class), PrivacyRegulation.class);
                regId = reg.getRegId();
            } catch (IOException ex) {

            }

            String URL = policyResponse.getHeaders().getFirst("location");

            if (policyResponse.getStatus() != 201) {
                System.out.println("Error posting UPP for user id: " + regId);
                System.out.println(policyResponse.getEntity(String.class));
                return null;
            }
            return URL;
        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.out.println("Error reading json upp file");
            return null;
            // Add logging code to log an error configuring the API on startup
        }
    }

    /**
    * Method to read (GET) a regulation.
    *
    * @param regId The name of the user id of the UPP to post.
    * @return the json of the UPP.
    */
    public String getRegulation(String regId){
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(regId);
            CloseableHttpResponse response1 = httpclient.execute(httpget);

            HttpEntity entity = response1.getEntity();
            System.out.println(response1.getStatusLine().getStatusCode());
            if(response1.getStatusLine().getStatusCode()==404) {
                return "Unknown user";
            }

            return EntityUtils.toString(entity);
        } catch (IOException ex) {
            System.out.println("Error connecting to PDB server to get UPP: "+ regId);
            return "Connection error";
        }
    }

    /**
     * Delete a UPP on the database.
     * @param userId The UPP to delete
     * @return indication if delete succeeded or not
     */
    private boolean deleteRegulation(String regId) {

        String urlUser = PDB_URL + regId;
        Client client = new Client();
        WebResource webResourcePDB = client.resource(urlUser);

        ClientResponse policyResponse = webResourcePDB.type("application/json").delete(ClientResponse.class);

        if (policyResponse.getStatus() != 200) {
            System.err.println(policyResponse.getEntity(String.class));
            return false;
        }

        return true;
    }

    /**
     * Change a UPP of a given Id using a PUT operation.
     * @param regId User ID to update
     * @param fileLoc The new json to change to
     * @return whether it succeeded or not.
     */
    private boolean updateRegulation(String regId, String fileLoc) {

        InputStream fis = null;
        try {

            /**
             * Get the JSON file.
             */
            fis = this.getClass().getClassLoader().getResourceAsStream(fileLoc);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);

            /**
             * Perform the PUT operation
             */
            String urlUser = PDB_URL + regId;
            Client client = new Client();
            WebResource webResourcePDB = client.resource(urlUser);
            ClientResponse policyResponse = webResourcePDB.type("application/json").put(ClientResponse.class,
                    content);

            if (policyResponse.getStatus() != 200) {
                System.err.println(policyResponse.getEntity(String.class));
                return false;
            }
        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("Error creating UPP in db");

        }
        return true;
    }

    /**
     * Check that an access policy in the json upp matches the input parameters
     * of the test
     * @param upp The UPP of the user (in json)
     * @param subject The subject accessing the data
     * @param resource The private data resource (odata endpoint)
     * @param req The required permission
     * @return True if the assertion is correct, otherwise false.
     */
    public boolean assertRegulationChange(String upp, String subject, String resource, boolean req) {

        JSONArray access_policies = JsonPath.read(upp, "$.subscribed_osp_policies[?(@.osp_id=='YellowPages')].access_policies[?(@.resource=='"+resource+"')]");
        for(Object aP: access_policies) {
            String subjectPolicy = JsonPath.read(aP, "$.subject");
            if(subjectPolicy.equalsIgnoreCase(subject)) {
                 boolean perm = Boolean.parseBoolean(JsonPath.read(aP, "$.permission").toString());
                 if(perm == req)
                     return true;
            }
        }
        return false;
    }

    /**
     * This is the workflow that describes and validates the integration
     * of the PC and PDB components in order to provide the functionalities
     * relevant to the management and computation of privacy regulations from
     * individual OSPs.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        RegulationTest testObj = new RegulationTest();

        /**
         * POST regulation and check that the json object is retrieved
         * correctly.
         */
        String regURL = testObj.postRegulation("regulation.json");
        if(regURL == null) {
            System.err.println("Test failed - adding reg1");
        }

        /**
         * Get the regulation
         */
        String reg = testObj.getRegulation(regURL);
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(reg);
        String regId = JsonPath.read(document, "$.reg_id");
        System.out.println(reg);
        if(!regURL.equalsIgnoreCase(testObj.PDB_URL+regId)) {
            System.err.println("Test failed - regId is not correct for created");
        }

        /**
         * Update the regulation
         */


        /**
         * Delete the UPPs
         *
         */
        if(!testObj.deleteRegulation(regId)){
            System.err.println("Test failed - failed to delete pat upp");
        }
        if(testObj.getRegulation(regId) != null)
            System.err.println("Test failed - failed to delete pat upp");

    }

}
