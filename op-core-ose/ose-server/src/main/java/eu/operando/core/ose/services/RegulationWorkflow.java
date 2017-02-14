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

import eu.operando.core.ose.mongo.OspsMongo;
import eu.operando.core.ose.mongo.RegulationsMongo;
import io.swagger.api.NotFoundException;
import eu.operando.core.pdb.common.model.PrivacyRegulation;
import java.util.List;

/**
 * Core implementation of compliance check functionality.
 * Essentially, checking that a given OSP privacy policy in terms of
 * statements about usage of data is in line with the relevant privacy
 * regulations.

 */
public class RegulationWorkflow {

    public RegulationWorkflow() {

    }

    /**
     * Regulation to add.
     * @param ospRequest
     * @return
     * @throws NotFoundException
     */
    public String addRegulation(PrivacyRegulation ospRequest)
        throws NotFoundException {

        String response = "";

        RegulationsMongo regMongo = new RegulationsMongo();
        String storeAction = regMongo.storeRegulation(ospRequest);
        if(storeAction == null)
            return "error";
        /**
         * Execute the workflow to check OSP services do not violate the regulation.
         */

        // Step 1. Query the DB to get a list of the OSEs in the legislation sector.
        OspsMongo ospMongo = new OspsMongo();
        List<String> osps = ospMongo.getOSPbySector(ospRequest.getLegislationSector());
        System.out.println("Number of OSPS - " + osps.size());
        for(String osp: osps) {
            System.out.println("OSP - " + osp);
        }

        /**
         * For each OSP begin a workflow to check the OSP reason policy against the regulation
         */

        return response;

    }
}
