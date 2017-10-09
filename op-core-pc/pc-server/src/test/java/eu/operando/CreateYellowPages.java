/////////////////////////////////////////////////////////////////////////
//
// ? University of Southampton IT Innovation Centre, 2016
//
// Copyright in this library belongs to the University of Southampton
// University Road, Highfield, Southampton, UK, SO17 1BJ
//
// This software may not be used, sold, licensed, transferred, copied
// or reproduced in whole or in part in any manner or form or in or
// on any media by any person other than in accordance with the terms
// of the Licence Agreement supplied with the software, or otherwise
// without the prior written consent of the copyright owners.

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
 * A set of unit tests to check the post/put and delete operations of the
 * PDB User Privacy Policy API.
 *
 * Uses example JSON policies from the yellow pages application.
 *
 */
public class CreateYellowPages {
    public static void main(String[] args) {
        TestHelperMethods tMethods = new TestHelperMethods();
        String accessReason = "{\n" +
"        \"reasonid\": \"8\",\n" +
"        \"datauser\": \"doctor\",\n" +
"        \"datasubjecttype\": \"patient\",\n" +
"        \"datatype\": \"Financial\",\n" +
"        \"reason\": \"Medical Treatment\"\n" +
"      }";

//        System.out.println(tMethods.createOSPReason("59dadf47ee358800334d74f7", "reasonPolicy.json"));
//        System.out.println(tMethods.updateOSEReason("YellowPages", accessReason));

        System.out.println(tMethods.changeOSPReason("59dadf47ee358800334d74f7", accessReason, "8"));

//        System.out.println(tMethods.createOSP("yellowpages.json"));
//        System.out.println(tMethods.createOSP("ami.json"));
//        System.out.println(tMethods.createOSP("aslbergamo_il.json"));
//        System.out.println(tMethods.createOSP("aslbergamo_gat.json"));
//        System.out.println(tMethods.createOSP("gaslini.json"));
//        System.out.println(tMethods.deleteOSP("59d2f560ee358800339c929f"));
//        System.out.println(tMethods.deleteOSP("599ff384ee35880033141631"));
//        System.out.println(tMethods.deleteOSP("599fe63fee3588003314162f"));

    }

}
