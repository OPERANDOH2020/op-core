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
//        System.out.println(tMethods.createOSP("yellowpages.json"));
//        System.out.println(tMethods.createOSP("ami.json"));
        System.out.println(tMethods.createOSP("aslbergamo_gat.json"));
//        System.out.println(tMethods.deleteOSP("59141974ee35880033b27479"));

    }

}
