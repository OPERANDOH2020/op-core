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

import io.swagger.client.ApiException;
import io.swagger.client.api.UPPApi;
import io.swagger.model.UserPreference;
import io.swagger.model.UserPrivacyPolicy;
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

        try {
            String category = XSDParser.getElementDataType("http://services.odata.org/g2cosp/patient('russellwhyte')/personal_information/full_name");
            System.out.println("cat =" +  category );
        } catch (InvalidPreferenceException ex) {
            Logger.getLogger(ManagementConsoleSim.class.getName()).log(Level.SEVERE, null, ex);
        }

//        PolicyEvaluationService pS = new PolicyEvaluationService();
//        // Set the userId and OspId of the requests
//            String userId = "pjgrace";
//            String ospId = "Hospital OSP";
//
//            // Create a single request
//            List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
//            OSPDataRequest osD = new OSPDataRequest();
//            osD.setAction(OSPDataRequest.ActionEnum.ACCESS);
//            osD.setRequesterId("Hospital OSP");
//            osD.setSubject("Doctor");
//            osD.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('russellwhyte')/Gender");
//            ospRequest.add(osD);
//
//            String reply = pS.evaluate(ospId, userId, ospRequest);

        // The user subscribes to OPERANDO. The MC calls the PDB to create a UPP
        UPPApi manConRecp = new UPPApi();
        UserPrivacyPolicy upp = new UserPrivacyPolicy();
        upp.setUserId("operandoUser1278546");
        try {
            manConRecp.userPrivacyPolicyPost(upp);
        } catch (ApiException ex) {
            System.err.println("Post fail when creating a new user: " + ex.getLocalizedMessage());
        }

        // User enters preferences into the privacy dashboard.
        UserPreference nPref = new UserPreference();

        nPref.setPreference(UserPreference.PreferenceEnum.MEDIUM);

    }

}
