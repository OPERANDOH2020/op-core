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
//      Created By :            Paul Grace
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////


package eu.operando;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Set of unit tests for the using the /user_privacy_policy endpoint of the
 * PDB module.
 *
 * Check that UPP records are created, updated and deleted correctly.
 */
public class UPPUnitTests {
    private static final String PDB_URL = "http://localhost:8080/pdb/";

    public UPPUnitTests() {

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
    private boolean createUPP(String fileLoc) {

        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(fileLoc);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);
            String urlUser = PDB_URL+"user_privacy_policy/";
            Client client = new Client();
            WebResource webResourcePDB = client.resource(urlUser);

            ClientResponse policyResponse = webResourcePDB.type("application/json").post(ClientResponse.class,
                    content);

            if (policyResponse.getStatus() != 201) {
                System.err.println(policyResponse.getEntity(String.class));
                return false;
            }
        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("Error creating UPP in db");

        }
        return true;
    }

    private boolean updateUPP(String fileLoc, String user) {

        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(fileLoc);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);
            String urlUser = PDB_URL+"user_privacy_policy/"+user;
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

    private boolean deleteUPP(String userId) {

            String urlUser = PDB_URL+"user_privacy_policy/" + userId;
            Client client = new Client();
            WebResource webResourcePDB = client.resource(urlUser);

            ClientResponse policyResponse = webResourcePDB.type("application/json").delete(ClientResponse.class);

            if (policyResponse.getStatus() != 200) {
                System.err.println(policyResponse.getEntity(String.class));
                return false;
            }

        return true;
    }



    private String getUPP(String userId) {
        try {
            String urlUser = PDB_URL+"user_privacy_policy/";

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(urlUser+userId);
            CloseableHttpResponse response1 = httpclient.execute(httpget);

            HttpEntity entity = response1.getEntity();
            System.out.println(response1.getStatusLine().getStatusCode());
            if(response1.getStatusLine().getStatusCode()==404) {
                return null;
            }
            String getResponse = EntityUtils.toString(entity);
            System.out.println(getResponse);
            return getResponse;
        } catch (IOException ex) {
            // Display to console for debugging purposes.
            System.err.println("Error reading user pp file");
            return null;
        }
    }

     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

            UPPUnitTests odpdb = new UPPUnitTests();
            if (!odpdb.createUPP("upp1.json")){
                System.out.println("Could not create UPP");
                System.exit(-1);
            }
            String getResponse = odpdb.getUPP("demo_user1");
            if(getResponse!=null){
                System.out.println(getResponse);
            }
            else {
                System.err.println("Error reading user");
                System.exit(-1);
            }

            if (!odpdb.createUPP("upp1.json")){
                System.out.println("Correctly failed to add duplicate");
            }
            else {
                System.err.println("Error posted upp twice");
                System.exit(-1);
            }

            if (!odpdb.updateUPP("upp1_1.json", "demo_user1")){
                System.out.println("Could not update UPP");
                System.exit(-1);
            }
            getResponse = odpdb.getUPP("demo_user1");
            if(getResponse!=null){
                System.out.println(getResponse);
            }
            else {
                System.err.println("Error reading user");
                System.exit(-1);
            }

            if (!odpdb.deleteUPP("demo_user1")){
                System.err.println("Error deleting user");
                System.exit(-1);
            }

            getResponse = odpdb.getUPP("demo_user1");
            if(getResponse!=null){
                System.err.println("Error reading user");
                System.exit(-1);
            }
            else {
                System.err.println("User correctly deleted");
            }
            if (!odpdb.createUPP("pat.json")){
                System.out.println("Could not create UPP");
                System.exit(-1);
            }
            if (!odpdb.createUPP("pete.json")){
                System.out.println("Could not create UPP");
                System.exit(-1);
            }

//            odpdb.createUPP("upp2.json");
//            String json1 = odpdb.getUPP("demo_user2");


//            odpdb.createUPP("demo_user3", "upp3.json", PDB_URL);


    }
}
