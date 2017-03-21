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
 * The PC to PDB integration test, for the user data access workflow. These
 * tests validate that when requests from an OSP to access data are evaluated
 * by the PC, they match the preferences of the two scenario users: Pat and Pete.
 *
 */
public class YellowPagesTest {

    /**
     * Reference to the set of testing helper methods.
     */
    TestHelperMethods operandoMethods;

    /**
     * UserIds
     */
    private static final String PAT = "pat";
    private static final String PETE = "pete";

    /**
     * UPP JSON
     */
    private static final String PAT_UPP = "pat.json";
    private static final String PETE_UPP = "pete.json";
    private static final String PAT_UPP_UPDATE = "pat_update.json";
    private static final String PETE_UPP_UPDATE = "pete_update.json";

    /**
     * Access Field Ids
     */
    private static final String[] FIELDIDS = {"/personal_information/full_name/given_name"};
    private static final String FIELDIDS2[] = {"/personal_information/full_name/given_name", "/personal_information/anthropometric_data"};

    private static final String OSPID = "YellowPages";
    private static final String ROLE1 = "Doctor";
    private static final String ROLE2 = "receptionist";

    private static final String ACTION = "ACCESS";

    /**
     * Constructor for stateful method calls.
     */
    public YellowPagesTest() {
        operandoMethods = new TestHelperMethods();
    }

    /**
     * This is the workflow that describes and validates the integration
     * of the PC and PDB components.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        YellowPagesTest odpdb = new YellowPagesTest();
        TestHelperMethods tMethods = new TestHelperMethods();

        if(tMethods.deleteUPP(PETE)){
            System.out.println(PETE + " UPP was in PDB - now deleted");
        }
        else {
            System.out.println("PDB does not contain " + PETE + "UPP");
        }

        if(tMethods.deleteUPP(PAT)){
            System.out.println(PAT + "UPP was in PDB - now deleted");
        }
        else {
            System.out.println("PDB does not contain " + PAT + "UPP");
        }

        /**
         * First call the PC API to evaluate a request to use Pat's data.
         * Pat has no UPP in the database, therefore assert that the
         * response is a no user response.
         */

        String accessRequest = tMethods.createRequest(FIELDIDS, ROLE1, OSPID, ACTION);
        System.out.println(accessRequest);

        String jsonResponse = tMethods.evaluatePC(PAT, "YellowPages", accessRequest);
        System.out.println(jsonResponse);

        if(!tMethods.readPolicyReport("status", jsonResponse).equalsIgnoreCase("false")){
            System.err.println("Integration test faild: Status must be false");
            System.exit(-1);
        }

        if(!tMethods.readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("NO_POLICY")){
            System.err.println("Integration test faild: Compliance must be NO_POLICY");
            System.exit(-1);
        }

        // Directly using the CORE service
        tMethods.postDemoUPP(PAT_UPP);
        tMethods.postDemoUPP(PETE_UPP);

        /**
         * Single Request
         */
        jsonResponse = tMethods.evaluatePC(PETE, OSPID, accessRequest);
        System.out.println(jsonResponse);

        if(!tMethods.readPolicyReport("status", jsonResponse).equalsIgnoreCase("true")){
            System.err.println("Integration test faild: Status must be true");
            System.exit(-1);
        }

        if(!tMethods.readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("VALID")){
            System.err.println("Integration test faild: Compliance must be VALID");
            System.exit(-1);
        }

        jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest);
        System.out.println(jsonResponse);

        if(!tMethods.readPolicyReport("status", jsonResponse).equalsIgnoreCase("true")){
            System.err.println("Integration test faild: Status must be true");
            System.exit(-1);
        }

        if(!tMethods.readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("VALID")){
            System.err.println("Integration test faild: Compliance must be VALID");
            System.exit(-1);
        }

        /**
         * Double request by doctor.
         */

        String accessRequest2 = tMethods.createRequest(FIELDIDS2, ROLE1, OSPID, ACTION);
        System.out.println(accessRequest2);

        jsonResponse = tMethods.evaluatePC(PETE, OSPID, accessRequest2);
        System.out.println(jsonResponse);

        if(!tMethods.readPolicyReport("status", jsonResponse).equalsIgnoreCase("true")){
            System.err.println("Integration test faild: Status must be true");
            System.exit(-1);
        }

        if(!tMethods.readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("VALID")){
            System.err.println("Integration test faild: Compliance must be VALID");
            System.exit(-1);
        }

        jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest2);
        System.out.println(jsonResponse);

        if(!tMethods.readPolicyReport("status", jsonResponse).equalsIgnoreCase("true")){
            System.err.println("Integration test faild: Status must be true");
            System.exit(-1);
        }

        if(!tMethods.readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("VALID")){
            System.err.println("Integration test faild: Compliance must be VALID");
            System.exit(-1);
        }

        /**
         * Double request by receptionist.
         */

        String accessRequest3 = tMethods.createRequest(FIELDIDS2, ROLE2, OSPID, ACTION);
        System.out.println(accessRequest3);

        jsonResponse = tMethods.evaluatePC(PETE, OSPID, accessRequest3);
        System.out.println(jsonResponse);

        if(!tMethods.readPolicyReport("status", jsonResponse).equalsIgnoreCase("true")){
            System.err.println("Integration test faild: Status must be true");
            System.exit(-1);
        }

        if(!tMethods.readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("VALID")){
            System.err.println("Integration test faild: Compliance must be VALID");
            System.exit(-1);
        }

        jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest3);
        System.out.println(jsonResponse);

        if(!tMethods.readPolicyReport("status", jsonResponse).equalsIgnoreCase("false")){
            System.err.println("Integration test faild: Status must be false");
            System.exit(-1);
        }

        if(!tMethods.readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("PREFS_CONFLICT")){
            System.err.println("Integration test faild: Compliance must be PREFS_CONFLICT");
            System.exit(-1);
        }

        /**
         * Update the UPPs.
         */
        tMethods.updateUPP(PAT, PAT_UPP_UPDATE);
        tMethods.updateUPP(PETE, PETE_UPP_UPDATE);

         /**
         * Double request by receptionist now that UPP has changed.
         */
        jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest3);
        System.out.println(jsonResponse);

        if(!tMethods.readPolicyReport("status", jsonResponse).equalsIgnoreCase("true")){
            System.err.println("Integration test faild: Status must be true");
            System.exit(-1);
        }

        if(!tMethods.readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("VALID")){
            System.err.println("Integration test faild: Compliance must be VALID");
            System.exit(-1);
        }

        jsonResponse = tMethods.evaluatePC(PETE, OSPID, accessRequest3);
        System.out.println(jsonResponse);

        if(!tMethods.readPolicyReport("status", jsonResponse).equalsIgnoreCase("false")){
            System.err.println("Integration test faild: Status must be false");
            System.exit(-1);
        }

        if(!tMethods.readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("PREFS_CONFLICT")){
            System.err.println("Integration test faild: Compliance must be PREFS_CONFLICT");
            System.exit(-1);
        }
    }
}
