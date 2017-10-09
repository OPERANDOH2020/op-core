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
 * Three roles (doctor, receptionist and website admin)
 */
public class GasliniTest {

     /**
     * UserIds
     */
    private static final String USER = "Dottore200";


    /**
     * Access Field Ids.
     * No hierarchy so we have a flat record of 7 fields. oData done wrong - does
     * not align with any schema. So just assume PC can cope with out any schema (!)
     */
    private static final String FIELDIDS[] = {"nome",
            "cognome",
            "indirizzoResidenza"};


    private static final String OSPID = "GASLINI";
    private static final String ROLE1 = "Gaslini - Tutor";
    private static final String ROLE2 = "Gaslini - Doctor";

    private static final String ACTION = "SELECT";

    /**
     * A set of tests to check how the PC component evaluates requests
     * to the OPERANDO GAT OSP data
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        TestHelperMethods tMethods = new TestHelperMethods();
        long time1 = System.currentTimeMillis();

                String accessRequest = tMethods.createRequest(FIELDIDS, ROLE1, OSPID, ACTION);
                System.out.println("Tutor trying to access fields: ");
                System.out.println("-------------------------------------------------");
                System.out.println(tMethods.evaluatePC(USER, OSPID, accessRequest));
                System.out.println("-------------------------------------------------");

            long time2 = System.currentTimeMillis();
            System.out.println("The time taken = " + (time2-time1));

        accessRequest = tMethods.createRequest(FIELDIDS, ROLE2, OSPID, ACTION);
        System.out.println("Doctor trying to access fields: ");
        System.out.println("-------------------------------------------------");
        System.out.println(tMethods.evaluatePC(USER, OSPID, accessRequest));
        System.out.println("-------------------------------------------------");


    }
}
