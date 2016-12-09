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
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.swagger.api.NotFoundException;
import io.swagger.model.OSPDataRequest;
import io.swagger.model.PolicyEvaluationReport;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Set of 16 tests on three example users for policy evaluation. Each test
 * is an incoming request to evaluate a policy.
 */
public class oDataPDB {

    private static final String PDB_URL = "http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database";

    /**
     * Constructor for stateful method calls.
     */
    public oDataPDB() {

    }

    /**
     * Personal information - 1 request
     * @return The data values list.
     */
    private static List<OSPDataRequest> createRequestOne() {
        List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
            OSPDataRequest osD = new OSPDataRequest();
            osD.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD.setRequesterId("osp1");
            osD.setSubject("doctor");
            osD.requestedUrl("http://operando.eu/osp1/patient('user1')/personal_information/gender");
            ospRequest.add(osD);
        return ospRequest;
    }

    /**
     * Personal information - 2 request
     * @return The data values list.
     */
    private static List<OSPDataRequest> createRequestTwo() {
        List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
            OSPDataRequest osD = new OSPDataRequest();
            osD.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD.setRequesterId("osp1");
            osD.setSubject("doctor");
            osD.requestedUrl("http://operando.eu/osp1/patient('user1')//personal_information/full_name");
            ospRequest.add(osD);

            OSPDataRequest osD2 = new OSPDataRequest();
            osD2.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD2.setRequesterId("osp1");
            osD2.setSubject("pharmacist");
            osD2.requestedUrl("http://operando.eu/osp1/patient('user1')//contact_information/emails/email");
            ospRequest.add(osD2);
        return ospRequest;
    }

    /**
     * Multiple anthropometric_data examples. Nurse collects and writes two examples
     * and a doctor and admin then reads this information.
     * @return
     */
    private static List<OSPDataRequest> createRequestThree() {
        // Create a single request
            List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
            OSPDataRequest osD = new OSPDataRequest();
            osD.setAction(OSPDataRequest.ActionEnum.COLLECT);
            osD.setRequesterId("osp1");
            osD.setSubject("nurse");
            osD.requestedUrl("http://operando.eu/osp1/patient('user1')/personal_information/anthropometric_data/weight");
            ospRequest.add(osD);

            OSPDataRequest osD2 = new OSPDataRequest();
            osD2.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD2.setRequesterId("osp1");
            osD2.setSubject("nurse");
            osD2.requestedUrl("http://operando.eu/osp1/patient('user1')/personal_information/anthropometric_data/body_circumeference");
            ospRequest.add(osD2);

            OSPDataRequest osD3 = new OSPDataRequest();
            osD3.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD3.setRequesterId("osp1");
            osD3.setSubject("admin");
            osD3.requestedUrl("http://operando.eu/osp1/patient('user1')/personal_information/anthropometric_data/weight");
            ospRequest.add(osD3);

            OSPDataRequest osD4 = new OSPDataRequest();
            osD4.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD4.setRequesterId("osp1");
            osD4.setSubject("doctor");
            osD4.requestedUrl("http://operando.eu/osp1/patient('user1')/personal_information/anthropometric_data/body_circumeference");
            ospRequest.add(osD4);
        return ospRequest;
    }

    /**
     * Multiple medical examples. Nurse collects and writes two examples
     * and a doctor and admin then reads this information.
     * @return
     */
    private static List<OSPDataRequest> createRequestFour() {
        // Create a single request
            List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
            OSPDataRequest osD = new OSPDataRequest();
            osD.setAction(OSPDataRequest.ActionEnum.COLLECT);
            osD.setRequesterId("osp2");
            osD.setSubject("doctor");
            osD.requestedUrl("http://operando.eu/osp1/patient('user1')/medical_problems/problems/problem");
            ospRequest.add(osD);

            OSPDataRequest osD2 = new OSPDataRequest();
            osD2.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD2.setRequesterId("osp2");
            osD2.setSubject("nurse");
            osD2.requestedUrl("http://operando.eu/osp1/patient('user1')/medical_problems/problems/problem/problem_description");
            ospRequest.add(osD2);

            OSPDataRequest osD3 = new OSPDataRequest();
            osD3.setAction(OSPDataRequest.ActionEnum.COLLECT);
            osD3.setRequesterId("osp2");
            osD3.setSubject("doctor");
            osD3.requestedUrl("http://operando.eu/osp1/patient('user1')/medication_summary/medicine");
            ospRequest.add(osD3);

            OSPDataRequest osD4 = new OSPDataRequest();
            osD4.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD4.setRequesterId("osp2");
            osD4.setSubject("pharmacist");
            osD4.requestedUrl("http://operando.eu/osp1/patient('user1')/medication_summary/medicine");
            ospRequest.add(osD4);
        return ospRequest;
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
    private void loadDemoUPP(String name, String fileLoc, String PDB_URL) {

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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Directly using the CORE service
            PolicyEvaluationService pS = new PolicyEvaluationService();
            oDataPDB odpdb = new oDataPDB();
            odpdb.loadDemoUPP("demo_user1", "upp1.json", PDB_URL);
            odpdb.loadDemoUPP("demo_user2", "upp2.json", PDB_URL);
            odpdb.loadDemoUPP("demo_user3", "upp3.json", PDB_URL);

            /**
             * The first data subject is a pragmatist.
             */
            String userId = "demo_user1";
            String ospId = "osp1";
            PolicyEvaluationReport reply = pS.evaluate(ospId, userId, createRequestOne(), PDB_URL);
            System.out.println(reply.toString());

            reply = pS.evaluate(ospId, userId, createRequestTwo(), PDB_URL);
            System.out.println(reply.toString());

            reply = pS.evaluate(ospId, userId, createRequestThree(), PDB_URL);
            System.out.println(reply.toString());

            ospId = "osp2";
            reply = pS.evaluate(ospId, userId, createRequestFour(), PDB_URL);
            System.out.println(reply.toString());

            /**
             * The second data subject is unconcerned.
             */
            userId = "demo_user2";
            reply = pS.evaluate(ospId, userId, createRequestOne(), PDB_URL);
            System.out.println(reply.toString());

            reply = pS.evaluate(ospId, userId, createRequestTwo(), PDB_URL);
            System.out.println(reply.toString());

            reply = pS.evaluate(ospId, userId, createRequestThree(), PDB_URL);
            System.out.println(reply.toString());

            ospId = "osp2";
            reply = pS.evaluate(ospId, userId, createRequestFour(), PDB_URL);
            System.out.println(reply.toString());

            /**
             * The third data subject is a fundamentalist.
             */
            userId = "demo_user3";
            reply = pS.evaluate(ospId, userId, createRequestOne(), PDB_URL);
            System.out.println(reply.toString());

            reply = pS.evaluate(ospId, userId, createRequestTwo(), PDB_URL);
            System.out.println(reply.toString());

            reply = pS.evaluate(ospId, userId, createRequestThree(), PDB_URL);
            System.out.println(reply.toString());

            ospId = "osp2";
            reply = pS.evaluate(ospId, userId, createRequestFour(), PDB_URL);
            System.out.println(reply.toString());

            ospId = "osp3";
            reply = pS.evaluate(ospId, userId, createRequestOne(), PDB_URL);
            System.out.println(reply.toString());
        } catch (NotFoundException ex) {
            Logger.getLogger(oDataPDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
