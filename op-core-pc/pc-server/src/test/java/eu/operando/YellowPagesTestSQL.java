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

import junit.framework.Assert;

/**
 * The PC to PDB integration test, for the user data access workflow. This time
 * using SQL like actions as input to the PC evaluation i.e.:
 * INSERT, SELECT, UPDATE & DELETE.
 *
 */
public class YellowPagesTestSQL {

    /**
     * UserIds
     */
    private static final String PAT = "pat_sql";

    /**
     * UPP JSON
     */
    private static final String PAT_UPP = "pat_sql.json";


    /**
     * Access Field Ids
     */
    /**
     * Access Field Ids.
     * No hierarchy so we have a flat record of 7 fields. oData done wrong - does
     * not align with any schema. So just assume PC can cope with out any schema (!)
     */
    private static final String[] FIELDIDS = {"personalInfo.patient.name",
                                                "personalInfo.anthropometric.weight",
                                                "personalInfo.anthropometric.age"};

    private static final String OSPID = "YellowPages";
    private static final String ROLE1 = "Doctor";
    private static final String ROLE2 = "receptionist";

    private static final String ACTION = "SELECT";
    private static final String ACTION1 = "INSERT";
    private static final String ACTION2 = "UPDATE";
    private static final String ACTION3 = "DELETE";

    /**
     * Constructor for stateful method calls.
     */
    public YellowPagesTestSQL() {
    }

    /**
     * This is the workflow that describes and validates the integration
     * of the PC and PDB components.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        YellowPagesTestSQL odpdb = new YellowPagesTestSQL();
        TestHelperMethods tMethods = new TestHelperMethods();

        if(tMethods.deleteUPP(PAT)){
            System.out.println(PAT + "UPP was in PDB - now deleted");
        }
        else {
            System.out.println("PDB does not contain " + PAT + "UPP");
        }
        tMethods.postDemoUPP(PAT_UPP);
        /**
         * Doctor, Receptionist SELECT fields
         */
        String accessRequest = tMethods.createRequest(FIELDIDS, ROLE1, OSPID, ACTION);

        String jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest);
        Assert.assertEquals("false", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("PREFS_CONFLICT", tMethods.readPolicyReport("compliance", jsonResponse));
        System.out.println("DOCTOR SELECTs: ");
        System.out.println(jsonResponse);

        accessRequest = tMethods.createRequest(FIELDIDS, ROLE2, OSPID, ACTION);
        jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest);
        Assert.assertEquals("false", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("PREFS_CONFLICT", tMethods.readPolicyReport("compliance", jsonResponse));
        System.out.println("RECEPS SELECTs: ");
        System.out.println(jsonResponse);

        /**
         * Doctor, Receptionist INSERT fields
         */
        accessRequest = tMethods.createRequest(FIELDIDS, ROLE1, OSPID, ACTION1);

        jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest);
        Assert.assertEquals("false", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("PREFS_CONFLICT", tMethods.readPolicyReport("compliance", jsonResponse));
        System.out.println("DOCTOR INSERTS: ");
        System.out.println(jsonResponse);

        accessRequest = tMethods.createRequest(FIELDIDS, ROLE2, OSPID, ACTION1);
        jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest);
        Assert.assertEquals("false", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("PREFS_CONFLICT", tMethods.readPolicyReport("compliance", jsonResponse));
        System.out.println("RECEPS INSERTS: ");
        System.out.println(jsonResponse);

        /**
         * Doctor, Receptionist UPDATE fields
         */
        accessRequest = tMethods.createRequest(FIELDIDS, ROLE1, OSPID, ACTION2);

        jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest);
        Assert.assertEquals("false", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("PREFS_CONFLICT", tMethods.readPolicyReport("compliance", jsonResponse));
        System.out.println("DOCTOR UPDATES: ");
        System.out.println(jsonResponse);

        accessRequest = tMethods.createRequest(FIELDIDS, ROLE2, OSPID, ACTION2);
        jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest);
        Assert.assertEquals("false", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("PREFS_CONFLICT", tMethods.readPolicyReport("compliance", jsonResponse));
        System.out.println("RECEPS UPDATES: ");
        System.out.println(jsonResponse);

        /**
         * Doctor, Receptionist DELETE fields
         */
        accessRequest = tMethods.createRequest(FIELDIDS, ROLE1, OSPID, ACTION3);

        jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest);
        Assert.assertEquals("false", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("PREFS_CONFLICT", tMethods.readPolicyReport("compliance", jsonResponse));
        System.out.println("DOCTOR DELETES: ");
        System.out.println(jsonResponse);

        accessRequest = tMethods.createRequest(FIELDIDS, ROLE2, OSPID, ACTION3);
        jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest);
        Assert.assertEquals("false", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("PREFS_CONFLICT", tMethods.readPolicyReport("compliance", jsonResponse));
        System.out.println("RECEPS DELETES: ");
        System.out.println(jsonResponse);

    }
}

