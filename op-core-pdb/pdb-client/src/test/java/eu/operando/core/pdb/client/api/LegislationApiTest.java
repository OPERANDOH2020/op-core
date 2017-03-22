/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2016
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
//      Created By :            Panos Melas
//      Created Date :          2017-03-21
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

/*
 * Policy DB
 * The Policy Database that stores three types of documents in dedicated collections.   1) User Privacy Policy. Each OPERANDO user has one UPP document describing their privacy policies for each of the OSP services they are subscribed to. The UPP contains the current B2C privacy settings for a subscribed to OSP. The UPP contains the users privacy preferences. The UPP contains the G2C access policies for the OSP with justification for access.   2) The legislation policies. The regulations entered into OPERANDO using the OPERANDO regulation API.   3) The OSP descriptions and data requests. For each OSP its privacy policy, its access control rules, and the data it requests (as a workflow). For B2C, the set of privacy settings is stored. 
 *
 * OpenAPI spec version: 1.0.0
 * Contact: support@operando.eu
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package eu.operando.core.pdb.client.api;

import eu.operando.core.pdb.client.api.LegislationApi;
import eu.operando.core.pdb.client.ApiException;
import eu.operando.core.pdb.common.model.PrivacyRegulation;
import eu.operando.core.pdb.common.model.PrivacyRegulationInput;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for LegislationApi
 */
@Ignore
public class LegislationApiTest {

    private final LegislationApi api = new LegislationApi();

    
    /**
     * Perform a search query across the collection of regulation.
     *
     * The query contains a json object (names, values) and the request returns  the list of documents (regulations) where the filter matches i.e. the  document contains fields with those values. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void regulationsGetTest() throws ApiException {
        String filter = null;
        List<PrivacyRegulation> response = api.regulationsGet(filter);

        // TODO: test validations
    }
    
    /**
     * Create a new legislation entry in the database.
     *
     * Called by the policy computation component when a new regulation is added to OPERANDO. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void regulationsPostTest() throws ApiException {
        PrivacyRegulationInput regulation = null;
        PrivacyRegulation response = api.regulationsPost(regulation);

        // TODO: test validations
    }
    
    /**
     * Remove the PrivacyRegulation entry in the database.
     *
     * Called when by the policy computation component when the regulator api requests that the regulation be deleted. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void regulationsRegIdDeleteTest() throws ApiException {
        String regId = null;
        api.regulationsRegIdDelete(regId);

        // TODO: test validations
    }
    
    /**
     * Read a given legislation with its ID.
     *
     * Get a specific legislation document via the id. This will return the  full legislation document in json format. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void regulationsRegIdGetTest() throws ApiException {
        String regId = null;
        PrivacyRegulation response = api.regulationsRegIdGet(regId);

        // TODO: test validations
    }
    
    /**
     * Update PrivacyRegulation entry in the database.
     *
     * Called when by the policy computation component when the regulator api updates a regulation. Their new PrivacyRegulation document is stored in the policy DB. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void regulationsRegIdPutTest() throws ApiException {
        String regId = null;
        PrivacyRegulationInput regulation = null;
        api.regulationsRegIdPut(regId, regulation);

        // TODO: test validations
    }
    
}