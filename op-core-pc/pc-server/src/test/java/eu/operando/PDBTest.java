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

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
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
public class PDBTest {

    private final String PDB_URL = "http://integration.operando.esilab.org:8096/operando/core/pdb/user_privacy_policy/";

    /**
     * Method to post a UPP policy
     *
     * @param name The name of the user id of the UPP to post.
     * @param fileLoc The filename in the resources of the json UPP.
     */
    private int postUPP(String name, String fileLoc) {

        InputStream fis = null;
        try {
            /**
             * Read the UPP policy named into a Java String.
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

            if (policyResponse.getStatus() != 201) {
                System.out.println("Error posting UPP for user id: " + name);
                System.out.println(policyResponse.getEntity(String.class));
                return policyResponse.getStatus();
            }
            return policyResponse.getStatus();
        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.out.println("Error reading json upp file");
            return 500;
            // Add logging code to log an error configuring the API on startup
        }
    }

    /**
     * Check UPP - see if the record exists
     * @param name user id to check
     * @return true if exists else false
     */
    public boolean checkUPP(String name){
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(PDB_URL+name);
            CloseableHttpResponse response1 = httpclient.execute(httpget);

            if(response1.getStatusLine().getStatusCode()==404) {
                return false;
            }

            return true;
        } catch (IOException ex) {
            System.out.println("Error connecting to PDB server to get UPP: "+ name);
            return false;
        }
    }

    /**
    * Method to read (GET) a UPP policy
    *
    * @param name The name of the user id of the UPP to post.
    * @return the json of the UPP.
    */
    public String getUPP(String name){
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(PDB_URL+name);
            CloseableHttpResponse response1 = httpclient.execute(httpget);

            HttpEntity entity = response1.getEntity();
            System.out.println(response1.getStatusLine().getStatusCode());
            if(response1.getStatusLine().getStatusCode()==404) {
                return "Unknown user";
            }

            return EntityUtils.toString(entity);
        } catch (IOException ex) {
            System.out.println("Error connecting to PDB server to get UPP: "+ name);
            return "Connection error";
        }
    }

    /**
     * Delete a UPP on the database.
     * @param userId The UPP to delete
     * @return indication if delete succeeded or not
     */
    private boolean deleteUPP(String userId) {

        String urlUser = PDB_URL + userId;
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
     * @param user User ID to update
     * @param fileLoc The new json to change to
     * @return whether it succeeded or not.
     */
    private boolean updateUPP(String user, String fileLoc) {

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
            String urlUser = PDB_URL + user;
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
    public boolean assertPermission(String upp, String subject, String resource, boolean req) {

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
     * of the PC and PDB components.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PDBTest testObj = new PDBTest();

        /**
         * Initial setup - check that the database is clean of the test UPPs
         */
        if(testObj.checkUPP("pat2"))
            testObj.deleteUPP("pat2");
        if(testObj.checkUPP("pete2"))
            testObj.deleteUPP("pete2");

        /**
         * POST with 2 users. Assert that two users are added correctly
         */
        if(testObj.postUPP("pat2", "pat.json") != 201) {
            System.err.println("Test failed - adding pat");
        }

        if(testObj.postUPP("pete2", "pete.json") != 201) {
            System.err.println("Test failed - adding pete");
        }

        /**
         * Get the UPP
         */
        String pat = testObj.getUPP("pat2");
        String pete = testObj.getUPP("pete2");

        Object document = Configuration.defaultConfiguration().jsonProvider().parse(pat);
        Object document2 = Configuration.defaultConfiguration().jsonProvider().parse(pete);

        // Assert that the JSON id is correct
        String userId1 = JsonPath.read(document, "$.user_id");
        if(!userId1.equalsIgnoreCase("pat2")) {
            System.err.println("Test failed - upp is not correct for pat");
        }

        String userId2 = JsonPath.read(document2, "$.user_id");
        if(!userId2.equalsIgnoreCase("pete2")) {
            System.err.println("Test failed - upp is not correct for pete");
        }

        if (!testObj.assertPermission(pat, "doctor", "/personal_information/full_name/given_name", true)){
            System.err.println("Test failed - access policy is incorrect");
        }

        /**
         * Update Pat
         */
        if(!testObj.updateUPP("pat2", "pat_update.json")) {
            System.err.println("Test failed - pat2 failed to update");
        }

        if (!testObj.assertPermission(testObj.getUPP("pat2"), "doctor", "/personal_information/full_name/given_name", false)){
            System.err.println("Test failed - pat access policy not updated");
        }

        /**
         * Delete the UPPs
         *
         */
        if(!testObj.deleteUPP("pat2")){
            System.err.println("Test failed - failed to delete pat upp");
        }
        if(testObj.checkUPP("pat2"))
            System.err.println("Test failed - failed to delete pat upp");

        if(!testObj.deleteUPP("pete2")){
            System.err.println("Test failed - failed to delete pete upp");
        }
        if(testObj.checkUPP("pete2"))
            System.err.println("Test failed - failed to delete pete upp");
    }

}
