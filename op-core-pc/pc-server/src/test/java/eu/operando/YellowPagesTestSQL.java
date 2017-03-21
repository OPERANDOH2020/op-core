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

import eu.operando.core.pdb.common.model.OSPDataRequest;
import io.swagger.api.NotFoundException;
import io.swagger.model.PolicyEvaluationReport;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
         * First call the PC API to evaluate Doctor select weight
         */
        PolicyEvaluationService pS = new PolicyEvaluationService();
        List<OSPDataRequest> accessRequest = tMethods.createBuildRequest(FIELDIDS, ROLE1, OSPID, ACTION);
        System.out.println(accessRequest);

        PolicyEvaluationReport jsonResponse = null;
        try {
            jsonResponse = pS.evaluate(OSPID, PAT, accessRequest, tMethods.getUPPURL());
        } catch (NotFoundException ex) {
            Logger.getLogger(YellowPagesTestSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
//                tMethods.evaluatePC(PAT, "YellowPages", accessRequest);
        String response = jsonResponse.toString();

        accessRequest = tMethods.createBuildRequest(FIELDIDS, ROLE2, OSPID, ACTION);
        System.out.println(accessRequest);

        try {
            jsonResponse = pS.evaluate(OSPID, PAT, accessRequest, tMethods.getUPPURL());
        } catch (NotFoundException ex) {
            Logger.getLogger(YellowPagesTestSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
//                tMethods.evaluatePC(PAT, "YellowPages", accessRequest);
        response = jsonResponse.toString();

        accessRequest = tMethods.createBuildRequest(FIELDIDS, ROLE1, OSPID, ACTION1);
        System.out.println(accessRequest);

        try {
            jsonResponse = pS.evaluate(OSPID, PAT, accessRequest, tMethods.getUPPURL());
        } catch (NotFoundException ex) {
            Logger.getLogger(YellowPagesTestSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
//                tMethods.evaluatePC(PAT, "YellowPages", accessRequest);
        response = jsonResponse.toString();

        accessRequest = tMethods.createBuildRequest(FIELDIDS, ROLE1, OSPID, ACTION2);
        System.out.println(accessRequest);

        try {
            jsonResponse = pS.evaluate(OSPID, PAT, accessRequest, tMethods.getUPPURL());
        } catch (NotFoundException ex) {
            Logger.getLogger(YellowPagesTestSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
//                tMethods.evaluatePC(PAT, "YellowPages", accessRequest);
        response = jsonResponse.toString();

        accessRequest = tMethods.createBuildRequest(FIELDIDS, ROLE1, OSPID, ACTION3);
        System.out.println(accessRequest);

        try {
            jsonResponse = pS.evaluate(OSPID, PAT, accessRequest, tMethods.getUPPURL());
        } catch (NotFoundException ex) {
            Logger.getLogger(YellowPagesTestSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
//                tMethods.evaluatePC(PAT, "YellowPages", accessRequest);
        response = jsonResponse.toString();

        accessRequest = tMethods.createBuildRequest(FIELDIDS, ROLE2, OSPID, ACTION1);
        System.out.println(accessRequest);

        try {
            jsonResponse = pS.evaluate(OSPID, PAT, accessRequest, tMethods.getUPPURL());
        } catch (NotFoundException ex) {
            Logger.getLogger(YellowPagesTestSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
//                tMethods.evaluatePC(PAT, "YellowPages", accessRequest);
        response = jsonResponse.toString();

        accessRequest = tMethods.createBuildRequest(FIELDIDS, ROLE2, OSPID, ACTION2);
        System.out.println(accessRequest);

        try {
            jsonResponse = pS.evaluate(OSPID, PAT, accessRequest, tMethods.getUPPURL());
        } catch (NotFoundException ex) {
            Logger.getLogger(YellowPagesTestSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
//                tMethods.evaluatePC(PAT, "YellowPages", accessRequest);
        response = jsonResponse.toString();

        accessRequest = tMethods.createBuildRequest(FIELDIDS, ROLE2, OSPID, ACTION3);
        System.out.println(accessRequest);

        try {
            jsonResponse = pS.evaluate(OSPID, PAT, accessRequest, tMethods.getUPPURL());
        } catch (NotFoundException ex) {
            Logger.getLogger(YellowPagesTestSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
//                tMethods.evaluatePC(PAT, "YellowPages", accessRequest);
        response = jsonResponse.toString();
    }
}

//        // Directly using the CORE service
//        tMethods.postDemoUPP(PAT_UPP);
//
//        /**
//         * Single Request
//         */
//
//        jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest);
//        System.out.println(jsonResponse);
//
//        if(!tMethods.readPolicyReport("status", jsonResponse).equalsIgnoreCase("true")){
//            System.err.println("Integration test faild: Status must be true");
//            System.exit(-1);
//        }
//
//        if(!tMethods.readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("VALID")){
//            System.err.println("Integration test faild: Compliance must be VALID");
//            System.exit(-1);
//        }
//
//        /**
//         * Double request by doctor.
//         */
//
//        String accessRequest2 = tMethods.createRequest(FIELDIDS2, ROLE1, OSPID, ACTION2);
//        System.out.println(accessRequest2);
//
//
//        jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest2);
//        System.out.println(jsonResponse);
//
//        if(!tMethods.readPolicyReport("status", jsonResponse).equalsIgnoreCase("true")){
//            System.err.println("Integration test faild: Status must be true");
//            System.exit(-1);
//        }
//
//        if(!tMethods.readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("VALID")){
//            System.err.println("Integration test faild: Compliance must be VALID");
//            System.exit(-1);
//        }
//
//        /**
//         * Double request by receptionist.
//         */
//
//        String accessRequest3 = tMethods.createRequest(FIELDIDS2, ROLE2, OSPID, ACTION2);
//        System.out.println(accessRequest3);
//
//        jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest3);
//        System.out.println(jsonResponse);
//
//        if(!tMethods.readPolicyReport("status", jsonResponse).equalsIgnoreCase("false")){
//            System.err.println("Integration test faild: Status must be false");
//            System.exit(-1);
//        }
//
//        if(!tMethods.readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("PREFS_CONFLICT")){
//            System.err.println("Integration test faild: Compliance must be PREFS_CONFLICT");
//            System.exit(-1);
//        }
//
//         /**
//         * Double request by receptionist now that UPP has changed.
//         */
//        String accessRequest4 = tMethods.createRequest(FIELDIDS2, ROLE1, OSPID, ACTION3);
//        System.out.println(accessRequest4);
//        jsonResponse = tMethods.evaluatePC(PAT, OSPID, accessRequest4);
//        System.out.println(jsonResponse);
//
//        if(!tMethods.readPolicyReport("status", jsonResponse).equalsIgnoreCase("true")){
//            System.err.println("Integration test faild: Status must be true");
//            System.exit(-1);
//        }
//
//        if(!tMethods.readPolicyReport("compliance", jsonResponse).equalsIgnoreCase("VALID")){
//            System.err.println("Integration test faild: Compliance must be VALID");
//            System.exit(-1);
//        }
//
//    }
//}
