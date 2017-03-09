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
 * Two users
 *
 */
public class YellowPagesIntegration {

    /**
     * A set of tests to check how the PC component evaluates requests
     * to the OPERANDO GAT OSP data
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        TestHelperMethods operandoMethods = new TestHelperMethods();

        /**
         * No hierarchy so we have a flat record of 7 fields. This is a string
         * array of the ids of each field.
         */
        String[] record = {"personalInfo.patient.name",
            "personalInfo.anthropometric.weight",
            "personalInfo.anthropometric.age",
            "personalInfo.patient.surname",
            "personalInfo.anthropometric.height",
            "contactInfo.patient.cityOfResidence",
            "contactInfo.patient.cellPhoneNumber",
            "personalInfo.patient.dateOfBirth",
            "personalInfo.patient.placeOfBirth",
            "contactInfo.patient.email"
        };

        String accessRequest = operandoMethods.createRequest(record, "doctor", "YellowPages");

        /**
         * First call the PC API to evaluate a doctor's request to read 302's data record.
         * Returns the list of fields they can and cannot see.
         */
//        System.out.println(accessRequest);
//
////        String jsonResponse = operandoMethods.evaluatePC("302", "YellowPages", accessRequest);
//
//        System.out.println(jsonResponse);
//
//        /**
//         * Call the PC API to evaluate a receptionist's request to read 302's data record.
//         * Returns the list of fields they can and cannot see.
//         */
//        accessRequest = operandoMethods.createRequest(record, "receptionist", "YellowPages");
//        System.out.println(accessRequest);
////        jsonResponse = operandoMethods.evaluatePC("302", "YellowPages", accessRequest);
//        System.out.println(jsonResponse);

    }
}
