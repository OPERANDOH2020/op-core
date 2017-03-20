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

import io.swagger.api.NotFoundException;
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UPPApi;
import io.swagger.model.OSPDataRequest;
import io.swagger.model.PolicyEvaluationReport;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Simulation of the Management Consoles usage of the PC and related OPERANDO
 * modules
 */
public class ManagementConsoleSim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PolicyEvaluationService pS = PolicyEvaluationService.getInstance();

        try {
            ODATAPolicies odata = new ODATAPolicies();
            String category = odata.getElementDataPath("http://services.odata.org/g2cosp/patient('user1')/personal_information/gender");
            int weighting = odata.getPreferenceRank(pS.getUPP("_demo_user1"), category, "health_care_professional");
            weighting = odata.getPreferenceRank(pS.getUPP("_demo_user1"), "/valid_data/contact", "health_care_professional");
            boolean grant = odata.grantOnWeighting(pS.getUPP("_demo_user1"), "Medical", "other", 6);
            grant = odata.grantOnWeighting(pS.getUPP("_demo_user1"), "Medical", "health_care_professional", 6);
            System.out.println("cat =" +  category + " weighting = " + weighting + grant);
        } catch (InvalidPreferenceException ex) {
            Logger.getLogger(ManagementConsoleSim.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Set the userId and OspId of the requests
            String userId = "user1";
            String ospId = "osp1";

            // Create a single request
            List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
            OSPDataRequest osD = new OSPDataRequest();
            osD.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD.setRequesterId("osp1");
            osD.setSubject("doctor");
            osD.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/personal_information/gender");
            ospRequest.add(osD);

            OSPDataRequest osD2 = new OSPDataRequest();
            osD2.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD2.setRequesterId("osp1");
            osD2.setSubject("pharmacist");
            osD2.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/personal_information/anthropometric_data/weight");
            ospRequest.add(osD2);

            OSPDataRequest osD3 = new OSPDataRequest();
            osD3.setAction(OSPDataRequest.ActionEnum.COLLECT);
            osD3.setRequesterId("osp1");
            osD3.setSubject("pharmacist");
            osD3.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/personal_information/anthropometric_data/weight");
            ospRequest.add(osD3);

            OSPDataRequest osD4 = new OSPDataRequest();
            osD4.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD4.setRequesterId("osp1");
            osD4.setSubject("pharmacist");
            osD4.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/personal_information/anthropometric_data/height");
            ospRequest.add(osD4);

            OSPDataRequest osD5 = new OSPDataRequest();
            osD5.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD5.setRequesterId("osp1");
            osD5.setSubject("admin");
            osD5.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/personal_information/anthropometric_data/height");
            ospRequest.add(osD5);

        try {
            PolicyEvaluationReport reply = pS.evaluate(ospId, userId, ospRequest, null);
            System.out.println(reply.toString());
        } catch (NotFoundException ex) {
            Logger.getLogger(ManagementConsoleSim.class.getName()).log(Level.SEVERE, null, ex);
        }

        // The user subscribes to OPERANDO. The MC calls the PDB to create a UPP
//        UPPApi manConRecp = new UPPApi();
//        UserPrivacyPolicy upp = new UserPrivacyPolicy();
//        upp.setUserId("operandoUser1278546");
//        try {
//            manConRecp.userPrivacyPolicyPost(upp);
//        } catch (ApiException ex) {
//            System.err.println("Post fail when creating a new user: " + ex.getLocalizedMessage());
//        }
//
//        // User enters preferences into the privacy dashboard.
//        UserPreference nPref = new UserPreference();
//
//        nPref.setPreference(UserPreference.PreferenceEnum.MEDIUM);

    }

}
