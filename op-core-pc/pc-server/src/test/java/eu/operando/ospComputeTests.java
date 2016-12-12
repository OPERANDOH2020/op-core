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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pjg
 */
public class ospComputeTests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            PolicyComputerService pCS = new PolicyComputerService();
            String ret = pCS.ospPolicyComputerPost("_demo_user1", "osp1", null, null, new PolicyEvaluationService());
            System.out.print(ret);
        } catch (NotFoundException ex) {
            Logger.getLogger(ospComputeTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
