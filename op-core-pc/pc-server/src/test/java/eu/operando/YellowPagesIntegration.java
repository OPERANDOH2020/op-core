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

/**
 * Integration test for Yellow Pages:
 *
 * One User - flat field id scheme
 * Doctor can read weight but cannot read name
 */
public class YellowPagesIntegration {

     /**
     * UserIds
     */
    private static final String USER = "302";

    /**
     * UPP JSON
     */
    private static final String USER_UPP = "302.json";

    /**
     * Access Field Ids.
     * No hierarchy so we have a flat record of 7 fields. oData done wrong - does
     * not align with any schema. So just assume PC can cope with out any schema (!)
     */
    private static final String[] FIELDIDS = {"personalInfo.anthropometric.weight"};
    private static final String FIELDIDS2[] = {"personalInfo.anthropometric.weight", "personalInfo.patient.name"};
    private static final String FIELDIDS3[] = {"personalInfo.patient.name",
            "personalInfo.anthropometric.weight",
            "personalInfo.anthropometric.age",
            "personalInfo.patient.surname",
            "personalInfo.anthropometric.height",
            "contactInfo.patient.cityOfResidence",
            "contactInfo.patient.cellPhoneNumber",
            "personalInfo.patient.dateOfBirth",
            "personalInfo.patient.placeOfBirth",
            "contactInfo.patient.email"};

    private static final String OSPID = "YellowPages";
    private static final String ROLE1 = "Doctor";
    private static final String ROLE2 = "Receptionist";

    private static final String ACTION = "ACCESS";

    /**
     * A set of tests to check how the PC component evaluates requests
     * to the OPERANDO GAT OSP data
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        TestHelperMethods tMethods = new TestHelperMethods();
        if(tMethods.deleteUPP(USER)){
            System.out.println(USER + " UPP was in PDB - now deleted");
        }
        else {
            System.out.println("PDB does not contain " + USER + "UPP");
        }

        tMethods.postDemoUPP(USER_UPP);
        String accessRequest = tMethods.createRequest(FIELDIDS, ROLE1, OSPID, ACTION);

        /**
         * First call the PC API to evaluate a doctor's request to read 302's data record.
         * Returns the list of fields they can and cannot see.
         */

        String jsonResponse = tMethods.evaluatePC(USER, OSPID, accessRequest);
        tMethods.assertPolicyResult(jsonResponse, "true", "VALID");

        /**
         * Call the PC API to evaluate a receptionist's request to read 302's data record.
         * Returns the list of fields they can and cannot see.
         */
        accessRequest = tMethods.createRequest(FIELDIDS, ROLE2, OSPID, ACTION);
        jsonResponse = tMethods.evaluatePC(USER, OSPID, accessRequest);
        tMethods.assertPolicyResult(jsonResponse, "false", "PREFS_CONFLICT");

        accessRequest = tMethods.createRequest(FIELDIDS2, ROLE1, OSPID, ACTION);
        jsonResponse = tMethods.evaluatePC(USER, OSPID, accessRequest);
        System.out.println(jsonResponse);
        tMethods.assertPolicyResult(jsonResponse, "false", "PREFS_CONFLICT");

        accessRequest = tMethods.createRequest(FIELDIDS2, ROLE2, OSPID, ACTION);
        jsonResponse = tMethods.evaluatePC(USER, OSPID, accessRequest);
        System.out.println(jsonResponse);
        tMethods.assertPolicyResult(jsonResponse, "false", "PREFS_CONFLICT");

        accessRequest = tMethods.createRequest(FIELDIDS3, ROLE1, OSPID, ACTION);
        jsonResponse = tMethods.evaluatePC(USER, OSPID, accessRequest);
        System.out.println(jsonResponse);
        tMethods.assertPolicyResult(jsonResponse, "false", "PREFS_CONFLICT");

        accessRequest = tMethods.createRequest(FIELDIDS3, ROLE2, OSPID, ACTION);
        jsonResponse = tMethods.evaluatePC(USER, OSPID, accessRequest);
        System.out.println(jsonResponse);
        tMethods.assertPolicyResult(jsonResponse, "false", "PREFS_CONFLICT");

        System.out.println("All integration tests passed");

    }
}
