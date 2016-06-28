/////////////////////////////////////////////////////////////////////////
//
// � University of Southampton IT Innovation Centre, 2016
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

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.PolicyEvaluationApi;
import io.swagger.client.model.OSPDataRequest;
import io.swagger.client.model.PolicyEvaluationReport;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simulation of the Management Consoles usage of the PC and related OPERANDO
 * modules
 */
public class DemoClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {

//             GETApi pdbClient = new GETApi(new ApiClient().setBasePath("http://192.9.206.155:8080/pdb-server/policy_database"));
//                UserPrivacyPolicy userPrivacyPolicyUserIdGet;
//                try {
//                    userPrivacyPolicyUserIdGet = pdbClient.userPrivacyPolicyUserIdGet("pjgrace");
//                    System.out.println(userPrivacyPolicyUserIdGet.getUserId());
//                } catch (io.swagger.client.ApiException ex) {
//                    // The request can be allowed because there is no policy in the database to contradict the request.
//                    ex.printStackTrace();
//                }

            ApiClient cli = new ApiClient();
            cli.setBasePath("http://localhost:8081/pc");
            PolicyEvaluationApi pApi = new PolicyEvaluationApi(cli);

            // Set the userId to be the unique email address
            String userId = "pjgrace@mail.com";
            String ospId = "Hosptial OSP";

            List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
            OSPDataRequest osD = new OSPDataRequest();
            osD.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD.setRequesterId("Hospital OSP");
            osD.requestedUrl("User.MedicalRecord.Heartrate");
            ospRequest.add(osD);

            PolicyEvaluationReport response = pApi.ospPolicyEvaluatorPost(userId, ospId, ospRequest);
            System.out.println("Status (t/f) = " + response.getCompliance());
            System.out.println("Status Report = " + response.getStatus());
        } catch (ApiException ex) {
            Logger.getLogger(DemoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
