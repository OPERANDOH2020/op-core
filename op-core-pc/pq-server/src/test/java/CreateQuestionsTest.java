/////////////////////////////////////////////////////////////////////////
//
// � University of Southampton IT Innovation Centre, 2016
//
// Copyright in this software belongs to University of Southampton
// IT Innovation Centre of Gamma House, Enterprise Road,
// Chilworth Science Park, Southampton, SO16 7NS, UK.
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
//      Created By :            Paul Grace
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////




//import eu.operando.core.pdb.common.model.AccessReason;
import eu.operando.pq.PrivacyQuestionsService;
import eu.operando.core.pdb.client1.model.AccessReason;
import io.swagger.model.Questionobject;

import java.util.ArrayList;
import java.util.List;

/**
 * Set of unit tests for the using the /user_privacy_policy endpoint of the
 * PDB module.
 *
 * Check that UPP records are created, updated and deleted correctly.
 */
public class CreateQuestionsTest {


     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        PrivacyQuestionsService pS = new PrivacyQuestionsService();
        List<AccessReason> reasonsFromFile = pS.getReasonsFromFile("reasonPolicy.json");
        List<Questionobject> questions = new ArrayList<>();
        /**
         * Add the general
         */
        questions.addAll(pS.getGeneralQuestions("EN"));
        /**
         * Add the category
         */
        questions.addAll(pS.getCategoryQuestions(reasonsFromFile, "EN"));
        /**
         * Add the roles
         */
        questions.addAll(pS.getTrustQuestions(reasonsFromFile, "EN"));

        for(Questionobject question: questions) {
            System.out.println(question.getCategory() + ":" + question.getQuestion());
        }
    }

}
