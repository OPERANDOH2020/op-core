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
public class BuildReasonPolicies {

    private static final String YELLOWPAGES = "YellowPages";
    private static final String YELLOWPAGESFILE = "yellowPagesReason.json";

    private static final String AMI = "ami";
    private static final String AMIFILE = "amiReason.json";

    private static final String ASLBERG = "YellowPages";
    private static final String ASLBERGFILE = "yellowPagesReason.json";

    private static final String GASLINI = "YellowPages";
    private static final String GASLINIFILE = "yellowPagesReason.json";

    private static final String ASLIL = "YellowPages";
    private static final String ASLILFILE = "yellowPagesReason.json";

    private void addReasonPolicy(String name, String file, TestHelperMethods tMethods) {

        String currentOSPID = tMethods.ospQuerybyFriendlyName(name);
        System.out.println("The " + name + " OSP ID field is: " + currentOSPID);

        if(tMethods.createOSPReason(currentOSPID, file)){
            System.out.println("The " + name + " reason policy was added successfully");
        } else {
            System.out.println("The " + name + " reason policy was not added");
        }
    }

    public static void main(String[] args) {
        TestHelperMethods tMethods = new TestHelperMethods();
        BuildReasonPolicies policies = new BuildReasonPolicies();
        /**
         * Yellow Pages
         */
         policies.addReasonPolicy(YELLOWPAGES, YELLOWPAGESFILE, tMethods);

//        /**
//         * AMI
//         */
//        policies.addReasonPolicy(AMI, AMIFILE, tMethods);
//
//        /**
//         * ASL-Bergamo
//         */
//        policies.addReasonPolicy(ASLBERG, ASLBERGFILE, tMethods);
//
//        /**
//         * GASLINI
//         */
//        policies.addReasonPolicy(GASLINI, GASLINIFILE, tMethods);
//
//        /**
//         * ASLIL
//         */
//        policies.addReasonPolicy(ASLIL, ASLILFILE, tMethods);

    }

}
