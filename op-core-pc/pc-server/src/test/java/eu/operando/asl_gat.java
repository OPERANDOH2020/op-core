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
public class asl_gat {

    private static String OSPID = "ASLBG_GA";


     /**
     * Access Field Ids.
     * No hierarchy so we have a flat record of 7 fields. oData done wrong - does
     * not align with any schema. So just assume PC can cope with out any schema (!)
     */
    private static final String GAPATIENT_FIELDIDS[] = {"ID",
            "datainserimento",
            "username",
            "nome",
            "cognome",
            "codiceFiscale",
            "sesso",
            "dataNascita",
            "PaeseNascita",
            "ProvinciaNascita",
            "comuneNascita",
            "ProvinciaResidenza",
            "comuneResidenza",
            "indirizzoResidenza",
            "numeroCivico",
            "cap",
            "email",
            "cellulare",
            "debiti",
            "debitiDuranteGioco",
            "usoAlcohol",
            "usoTabacco",
            "usoDroghe",
            "famigliaUsoAlcohol",
            "famigliaUsoTabacco",
            "famigliaUsoDroghe"};

    private static final String PAOTHER_FIELDIDS[] = {"ID",
            "datainserimento",
            "place",
            "fasciaGiorna",
            "tipoPatologi",
            "alcoholDurante",
            "tabaccoDurante",
            "drogaDurante",
            "etaPrimiDisturbi"};

    private static final String PAPATHOLOGY_FIELDIDS[] = {"ID",
            "datainserimento",
            "descrizione"};


    private static final String ROLE1 = "ASL Bergamo - GAT - Doctor";
    private static final String ROLE2 = "ASL Bergamo - GAT - Patient";
    private static final String ACTION = "SELECT";
    private static final String USER = "78";


    /**
     * Constructor for stateful method calls.
     */
    public asl_gat() {

    }

    /**
     * A set of tests to check how the PC component evaluates requests
     * to the OPERANDO GAT OSP data
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestHelperMethods tMethods = new TestHelperMethods();

        /**
         * Simulate a user signing up and subscribing to the asl_gat
         */
        String jsonResponse = tMethods.computePC(USER, OSPID);
        System.out.println(jsonResponse);

        String accessRequest = tMethods.createRequest(GAPATIENT_FIELDIDS, ROLE2, USER, ACTION);

        System.out.println("Patient trying to access own GAT fields: ");
        System.out.println("-------------------------------------------------");
        System.out.println(tMethods.evaluatePC(USER, OSPID, accessRequest));
        System.out.println("-------------------------------------------------");

        accessRequest = tMethods.createRequest(GAPATIENT_FIELDIDS, ROLE1, OSPID, ACTION);
        System.out.println("Doctor trying to access GAT fields: ");
        System.out.println("-------------------------------------------------");
        System.out.println(tMethods.evaluatePC(USER, OSPID, accessRequest));
        System.out.println("-------------------------------------------------");

        accessRequest = tMethods.createRequest(PAOTHER_FIELDIDS, ROLE1, OSPID, ACTION);
        System.out.println("Doctor trying to access PA_OTHER fields: ");
        System.out.println("-------------------------------------------------");
        System.out.println(tMethods.evaluatePC(USER, OSPID, accessRequest));
        System.out.println("-------------------------------------------------");

        accessRequest = tMethods.createRequest(PAPATHOLOGY_FIELDIDS, ROLE1, OSPID, ACTION);
        System.out.println("Doctor trying to access PA_** fields: ");
        System.out.println("-------------------------------------------------");
        System.out.println(tMethods.evaluatePC(USER, OSPID, accessRequest));
        System.out.println("-------------------------------------------------");

    }

}
