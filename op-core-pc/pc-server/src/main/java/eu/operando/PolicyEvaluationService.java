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
import io.swagger.api.NotFoundException;
import eu.operando.core.pdb.common.model.OSPDataRequest;
import eu.operando.core.pdb.common.model.OSPDataRequest.ActionEnum;
import io.swagger.model.PolicyEvaluationReport;
import io.swagger.model.RequestEvaluation;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minidev.json.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * The core service methods that implement the evaluation methods of the
 * web service.
 */
public class PolicyEvaluationService {

    private class EvalStatus {
        public boolean found;
        public boolean permit;
        public RequestEvaluation rEv;

        public EvalStatus(boolean found, boolean permit, RequestEvaluation rEv) {
            this.found = found;
            this.permit = permit;
            this.rEv = rEv;
        }

    }

    /**
     * This is a singleton class, that can be used as one instance by all
     * REST APIs of the PC component.
     */
    private static PolicyEvaluationService instance = null;
    private UPPMongo uppMongodb = null;

    /**
     * Operation to use to enforce singleton pattern.
     * @return The singleton instance.
     */
    public static PolicyEvaluationService getInstance() {
      if(instance == null) {
         instance = new PolicyEvaluationService();
      }
      return instance;
   }

    /**
     * The set of demo UPP profiles; that can be queried for unit testing of
     * the evaluation service.
     */
    private final HashMap<String, String> UppDB;

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
            UppDB.put(name, content);
        }
        catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("Error reading Configuration properties file");

            // Add logging code to log an error configuring the API on startup
        }
    }

    /**
     * Initiate the evaluation service component, and create a set of three
     * example users for unit testing.
     */
    protected  PolicyEvaluationService() {
        UppDB = new HashMap<String, String>();
        Properties props = loadDbProperties();

        uppMongodb = new UPPMongo(props.getProperty("mongo.server.host"), Integer.parseInt(props.getProperty("mongo.server.port")));
        try{
            loadDemoUPP("_demo_user1", "upp1.json");
            loadDemoUPP("_demo_user2", "upp2.json");
            loadDemoUPP("_demo_user3", "upp3.json");
            loadDemoUPP("osp1", "osp1.json");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Load the configuration properties from the resource file in JAR/WAR and
     * turn then into JAVA properties class.
     * @return The list of JAVA properties reflecting the configuration.
     */
    private Properties loadDbProperties() {
        Properties props;
        props = new Properties();

        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream("operando.properties");
            props.load(fis);
        }
        catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("Error reading Configuration properties file");

            // Add logging code to log an error configuring the API on startup
        }

        return props;
    }

    /**
     * Retrieve a demo user privacy profile (UPP) from the set of in-memory
     * test cases.
     * @param id The user id of the UPP requested
     * @return The UPP file in json format.
     */
    public String getUPP(String id) {
        return UppDB.get(id);
    }

    /**
     * Update a demo user privacy profile (UPP) from the set of in-memory
     * test cases.
     * @param id The user id of the
     * @param upp The new policy
     * @return
     */
    public String putUPP(String id, String upp) {
        return UppDB.put(id, upp);
    }


    private OSPDataRequest actionCheck(OSPDataRequest ospRequest) {
        if(ospRequest.getAction().compareTo(OSPDataRequest.ActionEnum.SELECT)==0){
            ospRequest.setAction(OSPDataRequest.ActionEnum.ACCESS);
        }
        else if(ospRequest.getAction().compareTo(OSPDataRequest.ActionEnum.INSERT)==0){
            ospRequest.setAction(OSPDataRequest.ActionEnum.CREATE);
        }
        return ospRequest;
    }

    private EvalStatus evaluateRequest(String ospId, String uppProfile, OSPDataRequest ospRequestInput)  {
        try {
            OSPDataRequest ospRequest = actionCheck(ospRequestInput);
            ODATAPolicies odata = new ODATAPolicies();
            String oDataURL = ospRequest.getRequestedUrl();
            String Category = odata.getElementDataPath(oDataURL);

            JSONArray access_policies = JsonPath.read(uppProfile, "$.subscribed_osp_policies[?(@.osp_id=='"+ospId+"')].access_policies[?(@.resource=='"+ Category +"')]");

            boolean found = false;
            boolean permit = true;
            RequestEvaluation rEv = new RequestEvaluation();

            // For each of the access requests in the list
            for(Object aP: access_policies) {
                String subject = JsonPath.read(aP, "$.subject");
                if(subject.equalsIgnoreCase(ospRequest.getSubject())) { // Check the subject
                    found=true;
                    if (JsonPath.read(aP, "$.action").toString().equalsIgnoreCase(ospRequest.getAction().name())){ // Check the action
                        boolean perm = Boolean.parseBoolean(JsonPath.read(aP, "$.permission").toString());
                        rEv.setDatauser(ospRequest.getSubject());
                        rEv.setDatafield(oDataURL);
                        rEv.setAction(ospRequest.getAction().name());
                        if(!perm) {
                            permit = false;
                            rEv.setResult(false);
                        }
                        else{
                            rEv.setResult(true);
                        }
                    }
                    else {
                        permit = false;

                        rEv.setDatauser(ospRequest.getSubject());
                        rEv.setDatafield(oDataURL);
                        rEv.setAction(ospRequestInput.getAction().name());
                        rEv.setResult(false);
                    }
                }
            }
            EvalStatus response = new EvalStatus(found, permit, rEv);
            return response;
        } catch (InvalidPreferenceException ex) {
            Logger.getLogger(PolicyEvaluationService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private String getUPPviaPDB(String userId, String pdbURL){
        String uppProfile = null;
        try {

            /**
             * Get the UPP from the PDB.
             */
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(pdbURL +"/" + userId);
            CloseableHttpResponse response1 = httpclient.execute(httpget);

            /**
             * If there is no UPP, then it returns an non-compliance report
             * with a NO_POLICY statement.
             */
            HttpEntity entity = response1.getEntity();
            if(response1.getStatusLine().getStatusCode()==404) {
                return null;
            }
            uppProfile = EntityUtils.toString(entity);

        } catch (IOException ex) {
            return null;
        }
        return uppProfile;
    }

    private String getUPPviaMongo(String userId){
        long time1 = System.currentTimeMillis();
        String upp = uppMongodb.getUPPById(userId);
        System.out.println("time = " + (System.currentTimeMillis() - time1));
        return upp;
    }

    /**
     * Core implementation of the policy evaluation service. Evaluates if a set
     * of requests matches a user's privacy preferences.
     *
     * @param ospId The ID of the OSP. This is used to identify existing user policies already computed.
     * @param userId The unique OPERANDO id of the user, obtained when they register with the OPERANDO dashboard.
     * @param ospRequest The array of individual ODATA field requests.
     * @param pdbURL The URL of the PDB server where UPPs are deployed.
     * @return
     * @throws NotFoundException
     */
    public PolicyEvaluationReport evaluate(String ospId, String userId, List<OSPDataRequest> ospRequest, String pdbURL) throws NotFoundException {

        try {

            System.out.println("New Evaluation Request");
            System.out.println("--------------------------------------------------");
            System.out.println("Evaluating User Policy: " + userId);
            System.out.println("Request from: " + ospId);

            /**
             * The response to be sent - yes/no along with a report of why something
             * has been denied.
             */
            PolicyEvaluationReport rp = new PolicyEvaluationReport();
            String uppProfile = "";
            if(userId.startsWith("_demo_")) {
                uppProfile = getUPP(userId);
                /**
                 * If someone sends an idiot demo request then fail the request
                 */
                if (uppProfile==null) {
                    rp.setStatus("false");
                    rp.setCompliance("NO_POLICY");
                    return rp;
                }
            }
            else{
                uppProfile = getUPPviaPDB(userId, pdbURL);
                if(uppProfile==null) {
                    rp.setStatus("false");
                    rp.setCompliance("NO_POLICY");
                    return rp;
                }
            }

            boolean permit = true;
            ODATAPolicies odata = new ODATAPolicies();
            /**
             * Evaluate the oData field request against the UPP user access policies
             */
            for (OSPDataRequest rIn: ospRequest) {
                ActionEnum actionInput = rIn.getAction();
                OSPDataRequest r = actionCheck(rIn);
                String oDataURL = r.getRequestedUrl();
                String Category = odata.getElementDataPath(oDataURL);
                JSONArray access_policies = JsonPath.read(uppProfile, "$.subscribed_osp_policies[?(@.osp_id=='"+ospId+"')].access_policies[?(@.resource=='"+ Category +"')]");
                while((access_policies.size() == 0) && (Category.length() > 0)) {
                    try{
                        Category = Category.substring(0, Category.lastIndexOf("/"));
                        System.out.println("Category 22 = " + Category);
                        access_policies = JsonPath.read(uppProfile, "$.subscribed_osp_policies[?(@.osp_id=='"+ospId+"')].access_policies[?(@.resource=='"+ Category +"')]");
                        System.out.println("Acces policies 22 = " + access_policies.size());
                    }
                    catch(Exception e){
                        break;
                    }

                }

                boolean found = false;
                // For each of the access requests in the list
                for(Object aP: access_policies) {
                    String subject = JsonPath.read(aP, "$.subject");
                    if(subject.equalsIgnoreCase(r.getSubject())) { // Check the subject
                        if (JsonPath.read(aP, "$.action").toString().equalsIgnoreCase(r.getAction().name())){ // Check the action
                            found=true;
                            boolean perm = Boolean.parseBoolean(JsonPath.read(aP, "$.permission").toString());
                            RequestEvaluation rEv = new RequestEvaluation();
                                rEv.setDatauser(r.getSubject());
                                rEv.setDatafield(oDataURL);
                                rEv.setAction(actionInput.name());
                            if(!perm) {
                                permit = false;
                                rEv.setResult(false);
                                rp.addEvaluationsItem(rEv);
                            }
                            else{
                                rEv.setResult(true);
                                rp.addEvaluationsItem(rEv);
                            }
                        }
//                        else {
//                            permit = false;
//                            RequestEvaluation rEv = new RequestEvaluation();
//                                rEv.setDatauser(r.getSubject());
//                                rEv.setDatafield(oDataURL);
//                                rEv.setAction(r.getAction().name());
//                                rEv.setResult(false);
//                                rp.addEvaluationsItem(rEv);
//                        }
                    }
                }
                if(!found){
                    /**
                     * If no policy is found then we use the preferences
                     */
                    String role = odata.getEPSOSRole(r.getSubject());
                    int prefWeighting = odata.getPreferenceRank(uppProfile, Category, role);
                    permit = odata.grantOnWeighting(uppProfile, "Medical", role, prefWeighting);

                    RequestEvaluation rEv = new RequestEvaluation();
                                rEv.setDatauser(r.getSubject());
                                rEv.setDatafield(oDataURL);
                                rEv.setAction(actionInput.name());
                                rEv.setResult(permit);
                                rp.addEvaluationsItem(rEv);
                }
            }

            if(permit) {
                rp.setStatus("true");
                rp.setCompliance("VALID");
            }
            else {
                rp.setStatus("false");
                rp.setCompliance("PREFS_CONFLICT");
            }

            String policyReport = rp.toString();
            System.out.println(policyReport);
            return rp;
        } catch (InvalidPreferenceException | ParseException ex) {
            System.err.println("Evaluation error - " + ex.getMessage());
            PolicyEvaluationReport rp = new PolicyEvaluationReport();
            rp.setStatus("false");
            rp.setCompliance("NO_POLICY");
            return rp;
        }
    }
}
