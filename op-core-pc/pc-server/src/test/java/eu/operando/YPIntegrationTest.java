/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2017
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
//  Created By :			Paul Grace
//  Created for Project :		Operando
//
/////////////////////////////////////////////////////////////////////////
//
//  License : GNU Lesser General Public License, version 3
//
/////////////////////////////////////////////////////////////////////////
package eu.operando;

import io.swagger.model.OSPDataRequest;
import java.util.List;
import junit.framework.Assert;
import org.junit.Test;

/**
 * JUnit Tests that form the first part of the integration tests
 * for the Operando Platform: Based upon the Yellow Pages OSP integration
 * test scenario.
 */

public class YPIntegrationTest {

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

    /**
     * Unit test to evaluate UPPs against OSP data requests. Test the Evaluate
     * method is working.
     */
    @Test
    public void testEvaluate() {

        TestHelperMethods tMethods = new TestHelperMethods();

        if(tMethods.deleteUPP(USER)){
            System.out.println(USER + " UPP was in PDB - now deleted");
        }
        else {
            System.out.println("PDB does not contain " + USER + "UPP");
        }

        boolean response = tMethods.postDemoUPP(USER_UPP);
        Assert.assertEquals(response, true);

        List<OSPDataRequest> accessRequest = tMethods.createBuildRequest(FIELDIDS, ROLE1, OSPID);

        /**
         * First call the PC API to evaluate a doctor's request to read 302's data record.
         * Returns the list of fields they can and cannot see.
         */

        String jsonResponse = tMethods.evaluateBuildPC(USER, OSPID, accessRequest);
        Assert.assertEquals("true", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("VALID", tMethods.readPolicyReport("compliance", jsonResponse));

        /**
         * Call the PC API to evaluate a receptionist's request to read 302's data record.
         * Returns the list of fields they can and cannot see.
         */
        accessRequest = tMethods.createBuildRequest(FIELDIDS, ROLE2, OSPID);
        jsonResponse = tMethods.evaluateBuildPC(USER, OSPID, accessRequest);
        Assert.assertEquals("false", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("PREFS_CONFLICT", tMethods.readPolicyReport("compliance", jsonResponse));

        accessRequest = tMethods.createBuildRequest(FIELDIDS2, ROLE1, OSPID);
        jsonResponse = tMethods.evaluateBuildPC(USER, OSPID, accessRequest);
        Assert.assertEquals("false", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("PREFS_CONFLICT", tMethods.readPolicyReport("compliance", jsonResponse));

        accessRequest = tMethods.createBuildRequest(FIELDIDS2, ROLE2, OSPID);
        jsonResponse = tMethods.evaluateBuildPC(USER, OSPID, accessRequest);
        Assert.assertEquals("false", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("PREFS_CONFLICT", tMethods.readPolicyReport("compliance", jsonResponse));

        accessRequest = tMethods.createBuildRequest(FIELDIDS3, ROLE1, OSPID);
        jsonResponse = tMethods.evaluateBuildPC(USER, OSPID, accessRequest);
        Assert.assertEquals("false", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("PREFS_CONFLICT", tMethods.readPolicyReport("compliance", jsonResponse));

        accessRequest = tMethods.createBuildRequest(FIELDIDS3, ROLE2, OSPID);
        jsonResponse = tMethods.evaluateBuildPC(USER, OSPID, accessRequest);
        Assert.assertEquals("false", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("PREFS_CONFLICT", tMethods.readPolicyReport("compliance", jsonResponse));

        System.out.println("All integration tests passed");
    }
}
