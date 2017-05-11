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
 * by the PC, they match the preferences of a scenario user: userid - Pete2.
 *
 */
public class FCSRFoodCoach {

    /**
     * URLs of the endpoints for PC and PDB. The workflow must work with these
     * two endpoints to test that the two modules hosted at these endpoints
     * integrate correctly with one another.
     */
    private static final String PDB_URL = "http://integration.operando.esilab.org:8096/operando/core/pdb/user_privacy_policy";

    private static final String PC_URL = "http://integration.operando.esilab.org:8095/operando/core/pc";

    //private static final String PC_URL = "http://localhost:8081";

    private static String OSPID = "FoodCoach";


     /**
     * Access Field Ids.
     * No hierarchy so we have a flat record of 7 fields. oData done wrong - does
     * not align with any schema. So just assume PC can cope with out any schema (!)
     */
    private static final String FIELDIDS[] = {
            "Weight"};

    private static final String ROLE1 = "Dietician";
    private static final String ACTION = "SELECT";

    /**
     * Constructor for stateful method calls.
     */
    public FCSRFoodCoach() {

    }

    /**
     * A set of tests to check how the PC component evaluates requests
     * to the OPERANDO GAT OSP data
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FCSRFoodCoach odpdb = new FCSRFoodCoach();
        TestHelperMethods tMethods = new TestHelperMethods();

        String accessRequest = tMethods.createRequest(FIELDIDS, ROLE1, OSPID, ACTION);

        System.out.println("Doctor trying to access GAT fields: ");
        System.out.println("-------------------------------------------------");
        System.out.println(tMethods.evaluatePC("panos2", OSPID, accessRequest));
        System.out.println("-------------------------------------------------");

    }

}
