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

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
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
 *
 * @author pjg
 */
public class TestHelperMethods {

    private Client client;

    public TestHelperMethods() {
        client = new Client();
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
     * @param PDB_URL The PDB url reference.
     */
    public void loadDemoUPP(String name, String fileLoc, String PDB_URL) {

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

    public boolean deleteUPP(String userId, String PDB_URL) {

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

    public boolean deleteOSP(String ospId, String PDB_URL) {

            String urlUser = PDB_URL+"/OSP/" + ospId;
            Client client = new Client();
            WebResource webResourcePDB = client.resource(urlUser);

            ClientResponse policyResponse = webResourcePDB.type("application/json").delete(ClientResponse.class);

            if (policyResponse.getStatus() != 200) {
                System.err.println(policyResponse.getEntity(String.class));
                return false;
            }

        return true;
    }

    public boolean updateUPP(String user, String fileLoc, String PDB_URL) {

        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(fileLoc);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);
            String urlUser = PDB_URL+"/user_privacy_policy/"+user;
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

    public String ospQuerybyFriendlyName(String ospId, String PDB_URL) {
        String ospAPI = PDB_URL+"/OSP/?filter=%7B%27policyText%27:%27"+ospId+"%27%7D" ;
        WebResource webResourcePDB = client.resource(ospAPI);
        ClientResponse policyResponse = webResourcePDB.type("application/json").get(ClientResponse.class);
        if(policyResponse.getStatus() != 200) {
            return null;
        }
        String filterResults = policyResponse.getEntity(String.class);
        JSONArray access_policies = JsonPath.read(filterResults, "$..[?(@.policy_text=='"+ospId+"')]");
        for(Object aP: access_policies) {
            String id = JsonPath.read(aP, "$.osp_policy_id");
            if(id != null)
                return id;
        }
        return null;
    }

    /**
     * Post regulation to PC
     */
    public String postPCRegulation(String regID, String PC_URL) {
        String ospAPI = PC_URL + "/regulations/" + regID;

        WebResource webResourcePDB = client.resource(ospAPI);
        ClientResponse policyResponse = webResourcePDB.type("application/json").post(ClientResponse.class,
                "{}");

        if (policyResponse.getStatus() != 201) {
            System.out.println(policyResponse.getStatus());
            System.out.println(policyResponse.getEntity(String.class));
            return null;
        }
        return policyResponse.getEntity(String.class);
    }

    /**
     * Add the policy to OSP.
     * @param fileLoc The json file with the content
     * @param PDB_URL The URL of the PDB.
     * @return t/f if the OSP is now in the PDB
     */
    public String createOSP(String fileLoc, String PDB_URL) {

        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(fileLoc);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);

            String ospAPI = PDB_URL + "/OSP";

            WebResource webResourcePDB = client.resource(ospAPI);
            ClientResponse policyResponse = webResourcePDB.type("application/json").post(ClientResponse.class,
                    content);

            if (policyResponse.getStatus() != 201) {
                System.err.println(policyResponse.getEntity(String.class));
                return null;
            }
            String id = JsonPath.parse(content).read("$.policy_text", String.class);
            return ospQuerybyFriendlyName(id, PDB_URL);

        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("Error creating UPP in db");
            return null;
        }
    }

}
