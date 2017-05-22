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

import eu.operando.core.pdb.common.model.OSPDataRequest;
import io.swagger.api.NotFoundException;
import java.io.IOException;
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
 *
 * @author pjg
 */
public class ospComputeTests {

    private static final String ROLE1 = "Doctor";
    private static final String ACTION = "SELECT";

    private static final String FIELDIDS[] = {"personalInfo.patient.name",
            "personalInfo.anthropometric.weight",
            "personalInfo.anthropometric.age",
            "personalInfo.patient.surname",
            "personalInfo.anthropometric.height",
            "contactInfo.patient.cityOfResidence",
            "contactInfo.patient.cellPhoneNumber",
            "personalInfo.patient.dateOfBirth",
            "personalInfo.patient.placeOfBirth",
            "contactInfo.patient.email"};

     static final String OSPID = "YellowPages";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            TestHelperMethods tMethods = new TestHelperMethods();


            String uppsURL = "http://integration.operando.esilab.org:8096/operando/core/pdb/user_privacy_policy/?filter=%7B%27subscribed_osp_policies.osp_id%27:%27"+OSPID+"%27%7D";
            String uppProfile = null;
                /**
                 * Get the UPP from the PDB.
                 */
                CloseableHttpClient httpclient = HttpClients.createDefault();
                HttpGet httpget = new HttpGet(uppsURL);
                CloseableHttpResponse response1 = httpclient.execute(httpget);

                /**
                 * If there is no UPP, then it returns an non-compliance report
                 * with a NO_POLICY statement.
                 */
                HttpEntity entity = response1.getEntity();
                if(response1.getStatusLine().getStatusCode()==404) {
                    System.exit(0);
                }
                uppProfile = EntityUtils.toString(entity);
                httpclient.close();
                response1.close();
                httpget.releaseConnection();


            PolicyEvaluationService pCS = new PolicyEvaluationService();
            List<OSPDataRequest> calls = tMethods.createBuildRequest(FIELDIDS, ROLE1, OSPID, ACTION);
            String ret = pCS.batchEvaluate("YellowPages", calls, uppProfile);
//            String ret = pCS.ospPolicyComputerPost("_demo_user1", "osp1", null, null, new PolicyEvaluationService());
            System.out.print(ret);
        } catch (NotFoundException ex) {
            Logger.getLogger(ospComputeTests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ospComputeTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
