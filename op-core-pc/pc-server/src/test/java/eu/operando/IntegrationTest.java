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

public class IntegrationTest {

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


    /**
     * Unit test to evaluate UPPs against OSP data requests. Test the Evaluate
     * method is working.
     */
    @Test
    public void testEvaluate() {

        TestHelperMethods tMethods = new TestHelperMethods();

        /**
         * Setup the test to clean the environment of PAT AND PETE
         */
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

        boolean patSuccess = tMethods.postDemoUPP(PAT_UPP);
        Assert.assertEquals(patSuccess, true);

        boolean peteSuccess = tMethods.postDemoUPP(PETE_UPP);
        Assert.assertEquals(peteSuccess, true);

         /**
         * First call the PC API to evaluate a request to use Pat's data.
         * Pat has no UPP in the database, therefore assert that the
         * response is a no user response.
         */

        List<OSPDataRequest> accessRequest = tMethods.createBuildRequest(FIELDIDS, ROLE1, OSPID);

        String jsonResponse = tMethods.evaluateBuildPC(PETE, OSPID, accessRequest);
        Assert.assertEquals("true", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("VALID", tMethods.readPolicyReport("compliance", jsonResponse));

        jsonResponse = tMethods.evaluateBuildPC(PAT, OSPID, accessRequest);
        Assert.assertEquals("VALID", tMethods.readPolicyReport("compliance", jsonResponse));
        Assert.assertEquals("true", tMethods.readPolicyReport("status", jsonResponse));

        List<OSPDataRequest> accessRequest2 = tMethods.createBuildRequest(FIELDIDS2, ROLE1, OSPID);
        jsonResponse = tMethods.evaluateBuildPC(PETE, OSPID, accessRequest2);
        Assert.assertEquals("VALID", tMethods.readPolicyReport("compliance", jsonResponse));
        Assert.assertEquals("true", tMethods.readPolicyReport("status", jsonResponse));

        jsonResponse = tMethods.evaluateBuildPC(PAT, OSPID, accessRequest2);
        Assert.assertEquals("VALID", tMethods.readPolicyReport("compliance", jsonResponse));
        Assert.assertEquals("true", tMethods.readPolicyReport("status", jsonResponse));

        List<OSPDataRequest> accessRequest3 = tMethods.createBuildRequest(FIELDIDS2, ROLE2, OSPID);
        jsonResponse = tMethods.evaluateBuildPC(PETE, OSPID, accessRequest3);
        Assert.assertEquals("VALID", tMethods.readPolicyReport("compliance", jsonResponse));
        Assert.assertEquals("true", tMethods.readPolicyReport("status", jsonResponse));

        jsonResponse = tMethods.evaluateBuildPC(PAT, OSPID, accessRequest3);
        Assert.assertEquals("PREFS_CONFLICT", tMethods.readPolicyReport("compliance", jsonResponse));
        Assert.assertEquals("false", tMethods.readPolicyReport("status", jsonResponse));

        /**
         * Update the UPPs.
         */
        boolean response = tMethods.updateUPP(PAT, PAT_UPP_UPDATE);
        Assert.assertEquals(true, response);
        response = tMethods.updateUPP(PETE, PETE_UPP_UPDATE);
        Assert.assertEquals(true, response);

         /**
         * Double request by receptionist now that UPP has changed.
         */
        jsonResponse = tMethods.evaluateBuildPC(PAT, OSPID, accessRequest3);
        Assert.assertEquals("true", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("VALID", tMethods.readPolicyReport("compliance", jsonResponse));

        jsonResponse = tMethods.evaluateBuildPC(PETE, OSPID, accessRequest3);
        Assert.assertEquals("false", tMethods.readPolicyReport("status", jsonResponse));
        Assert.assertEquals("PREFS_CONFLICT", tMethods.readPolicyReport("compliance", jsonResponse));
    }
}
