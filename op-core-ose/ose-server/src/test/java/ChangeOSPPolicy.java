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


/**
 * Initiate the reason policies for the integration environment.
 *
 */
public class ChangeOSPPolicy {

    private static final String YELLOWPAGES = "YellowPages";
    private static final String POLICYFILE = "yellowPagesChangeDelete.json";
    private static final String ORIGINAL = "yellowPages.json";
    private static final String CHANGE = "yellowPagesChangeEdit.json";
    private static final String MULTIPLE = "yellowPagesChangeMultiple.json";


    public static void main(String[] args) {
        TestHelperMethods tMethods = new TestHelperMethods();

        /**
         * Yellow Pages
         */
        String currentOSPID = tMethods.ospQuerybyFriendlyName(YELLOWPAGES);
        System.out.println("The " + YELLOWPAGES + " OSP ID field is: " + currentOSPID);
        /**
         * First we delete an access policy and test
         */
//        tMethods.changeOSEOSPPolicy(currentOSPID, POLICYFILE);
//
//        /**
//         * Next we add the policy back
//         */
////        tMethods.changeOSEOSPPolicy(currentOSPID, ORIGINAL);
//
//
//        /**
//         * Change a policy category value
//         */
//        tMethods.changeOSEOSPPolicy(currentOSPID, CHANGE);
////        tMethods.changeOSEOSPPolicy(currentOSPID, ORIGINAL);
        /**
         * Do a test with multiple changes.
         * Two deletes, Three additions, and two changes
         */
        tMethods.changeOSEOSPPolicy(currentOSPID, MULTIPLE);
        /**
         * Finally return to the original policy.
         */
//        tMethods.changeOSEOSPPolicy(currentOSPID, ORIGINAL);
    }

}
