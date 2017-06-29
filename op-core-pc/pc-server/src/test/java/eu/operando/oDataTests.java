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

import io.swagger.api.NotFoundException;
import eu.operando.core.pdb.common.model.OSPDataRequest;
import io.swagger.model.PolicyEvaluationReport;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Set of 16 tests on three example users for policy evaluation. Each test
 * is an incoming request to evaluate a policy.
 */
public class oDataTests {

    /**
     * Personal information - 1 request
     * @return The data values list.
     */
    private static List<OSPDataRequest> createRequestOne() {
        List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
            OSPDataRequest osD = new OSPDataRequest();
            osD.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD.setRequesterId("osp1");
            osD.setSubject("doctor");
            osD.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/personal_information/gender");
            ospRequest.add(osD);
        return ospRequest;
    }

    /**
     * Personal information - 2 request
     * @return The data values list.
     */
    private static List<OSPDataRequest> createRequestTwo() {
        List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
            OSPDataRequest osD = new OSPDataRequest();
            osD.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD.setRequesterId("osp1");
            osD.setSubject("doctor");
            osD.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/personal_information/full_name");
            ospRequest.add(osD);

            OSPDataRequest osD2 = new OSPDataRequest();
            osD2.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD2.setRequesterId("osp1");
            osD2.setSubject("pharmacist");
            osD2.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/contact_information/emails/email");
            ospRequest.add(osD2);
        return ospRequest;
    }

    /**
     * Multiple anthropometric_data examples. Nurse collects and writes two examples
     * and a doctor and admin then reads this information.
     * @return
     */
    private static List<OSPDataRequest> createRequestThree() {
        // Create a single request
            List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
            OSPDataRequest osD = new OSPDataRequest();
            osD.setAction(OSPDataRequest.ActionEnum.COLLECT);
            osD.setRequesterId("osp1");
            osD.setSubject("nurse");
            osD.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/personal_information/anthropometric_data/weight");
            ospRequest.add(osD);

            OSPDataRequest osD2 = new OSPDataRequest();
            osD2.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD2.setRequesterId("osp1");
            osD2.setSubject("nurse");
            osD2.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/personal_information/anthropometric_data/body_circumeference");
            ospRequest.add(osD2);

            OSPDataRequest osD3 = new OSPDataRequest();
            osD3.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD3.setRequesterId("osp1");
            osD3.setSubject("admin");
            osD3.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/personal_information/anthropometric_data/weight");
            ospRequest.add(osD3);

            OSPDataRequest osD4 = new OSPDataRequest();
            osD4.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD4.setRequesterId("osp1");
            osD4.setSubject("doctor");
            osD4.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/personal_information/anthropometric_data/body_circumeference");
            ospRequest.add(osD4);
        return ospRequest;
    }

    /**
     * Multiple medical examples. Nurse collects and writes two examples
     * and a doctor and admin then reads this information.
     * @return
     */
    private static List<OSPDataRequest> createRequestFour() {
        // Create a single request
            List<OSPDataRequest> ospRequest = new ArrayList<OSPDataRequest>();
            OSPDataRequest osD = new OSPDataRequest();
            osD.setAction(OSPDataRequest.ActionEnum.COLLECT);
            osD.setRequesterId("osp2");
            osD.setSubject("doctor");
            osD.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/medical_problems/problems/problem");
            ospRequest.add(osD);

            OSPDataRequest osD2 = new OSPDataRequest();
            osD2.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD2.setRequesterId("osp2");
            osD2.setSubject("nurse");
            osD2.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/medical_problems/problems/problem/problem_description");
            ospRequest.add(osD2);

            OSPDataRequest osD3 = new OSPDataRequest();
            osD3.setAction(OSPDataRequest.ActionEnum.COLLECT);
            osD3.setRequesterId("osp2");
            osD3.setSubject("doctor");
            osD3.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/medication_summary/medicine");
            ospRequest.add(osD3);

            OSPDataRequest osD4 = new OSPDataRequest();
            osD4.setAction(OSPDataRequest.ActionEnum.ACCESS);
            osD4.setRequesterId("osp2");
            osD4.setSubject("pharmacist");
            osD4.requestedUrl("http://services.odata.org/TripPinRESTierService/patient('user1')/medication_summary/medicine");
            ospRequest.add(osD4);
        return ospRequest;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Directly using the CORE service
            PolicyEvaluationService pS = new PolicyEvaluationService();

            /**
             * The first data subject is a pragmatist.
             */
            String userId = "_demo_user1";
            String ospId = "osp1";
            PolicyEvaluationReport reply = pS.evaluate(ospId, userId, createRequestOne(), null, null);
            System.out.println(reply.toString());

            reply = pS.evaluate(ospId, userId, createRequestTwo(), null, null);
            System.out.println(reply.toString());

            reply = pS.evaluate(ospId, userId, createRequestThree(), null, null);
            System.out.println(reply.toString());

            ospId = "osp2";
            reply = pS.evaluate(ospId, userId, createRequestFour(), null, null);
            System.out.println(reply.toString());

            /**
             * The second data subject is unconcerned.
             */
            userId = "_demo_user2";
            reply = pS.evaluate(ospId, userId, createRequestOne(), null, null);
            System.out.println(reply.toString());

            reply = pS.evaluate(ospId, userId, createRequestTwo(), null, null);
            System.out.println(reply.toString());

            reply = pS.evaluate(ospId, userId, createRequestThree(), null, null);
            System.out.println(reply.toString());

            ospId = "osp2";
            reply = pS.evaluate(ospId, userId, createRequestFour(), null, null);
            System.out.println(reply.toString());

            /**
             * The third data subject is a fundamentalist.
             */
            userId = "_demo_user3";
            reply = pS.evaluate(ospId, userId, createRequestOne(), null, null);
            System.out.println(reply.toString());

            reply = pS.evaluate(ospId, userId, createRequestTwo(), null, null);
            System.out.println(reply.toString());

            reply = pS.evaluate(ospId, userId, createRequestThree(), null, null);
            System.out.println(reply.toString());

            ospId = "osp2";
            reply = pS.evaluate(ospId, userId, createRequestFour(), null, null);
            System.out.println(reply.toString());

            ospId = "osp3";
            reply = pS.evaluate(ospId, userId, createRequestOne(), null, null);
            System.out.println(reply.toString());
        } catch (NotFoundException ex) {
            Logger.getLogger(oDataTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
