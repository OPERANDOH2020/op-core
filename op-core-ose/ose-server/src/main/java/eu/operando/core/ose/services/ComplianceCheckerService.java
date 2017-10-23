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
package eu.operando.core.ose.services;

import eu.operando.core.ose.mongo.RegulationsMongo;
import eu.operando.core.pdb.common.model.AccessReason;
import io.swagger.api.NotFoundException;
import eu.operando.core.pdb.common.model.OSPReasonPolicy;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;
import eu.operando.core.pdb.common.model.PrivacyRegulation;
import eu.operando.core.pdb.common.model.ComplianceEvaluation;
import java.util.ArrayList;
import java.util.List;

/**
 * Core implementation of compliance check functionality.
 * Essentially, checking that a given OSP privacy policy in terms of
 * statements about usage of data is in line with the relevant privacy
 * regulations.

 */
public class ComplianceCheckerService {

    public ComplianceCheckerService() {

    }
    /**
     *

     * @return
     * @throws NotFoundException
     */
    public List<ComplianceEvaluation> ospComplianceCheck(OSPReasonPolicy ospRequest, OSPPrivacyPolicy ospAccess)
        throws NotFoundException {

        String sector = "healthcare";
        List<ComplianceEvaluation> comEvals = new ArrayList<ComplianceEvaluation>();

        // Evaluate if the Policy in the reason policy matches the regulation rule
        List<AccessReason> policies = ospRequest.getPolicies();
        RegulationsMongo rMon = new RegulationsMongo();

        /**
         * Find all the regulations that match A - Sector and B - Data type (everything else is irrelevant.
         * This is a state machine of valid actions on data by role. Rule check of that transition.
         */
        for(AccessReason accReas: policies) {
            String data = accReas.getDatatype();
            String reason = accReas.getReason();
            String user = accReas.getDatauser();
            ComplianceEvaluation cE = new ComplianceEvaluation();
            cE.setDatauser(user);
            cE.setAction("access");
            cE.setDatafield(data);
            cE.setReason(reason);

            List<PrivacyRegulation> regulations = rMon.getRegulations(data, sector);
            boolean allowed = false;
            for(PrivacyRegulation reg: regulations) {
                if(reg.getReason().equalsIgnoreCase(reason)) {
                    allowed = true;
                    break;
                }
            }
            if(allowed) {
                cE.setResult(true);
            }
            else {
                cE.setResult(false);
            }
            comEvals.add(cE);
        }

        return comEvals;

    }
}
