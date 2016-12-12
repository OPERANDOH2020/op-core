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

package io.swagger.api.impl;

import eu.operando.PolicyEvaluationService;
import io.swagger.api.*;
import io.swagger.model.UserPreference;
import io.swagger.model.UserPrivacyPolicy;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.swagger.model.ComputationResult;

/**
 * Implementation of the Policy Computer API.
 *
 * This is a computational resource accessed via a REST POST call to carry out a computation
 * on the provided inputs. This results in an resource that is created and stored in the Policy DB
 * and can be accessed by the URL returned as a result of this operation. In OPERANDO, this method
 * is called by the management console when a user registers with OPERANDO. The user simply enters
 * privacy details and answers a general questionnaire.
 *
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class PolicyComputerApiServiceImpl extends PolicyComputerApiService {

    /**
     * The API component only uses one other OPERANDO component the policy database.
     * This stores the reference, so HTTP REST calls can be made.
     */
    private String PDB_BASEURL = null;

    /**
     * Reference to the core implementation of this API
     */
    private final PolicyEvaluationService policyService;

    /**
     * The configuration of local state for the API object. Simply creates
     * the reusable reference to the PDB.
     */
    public PolicyComputerApiServiceImpl() {
        super();
	Properties props;
    	props = loadDbProperties();
        policyService = PolicyEvaluationService.getInstance();
    	PDB_BASEURL = props.getProperty("pdb.baseurl");
    }

    /**
     * Load the configuration properties from the resource file in JAR/WAR and
     * turn then into JAVA properties class.
     * @return The list of JAVA properties reflecting the configuration.
     */
    private Properties loadDbProperties() {
        Properties props;
        props = new Properties();

        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream("/operando.properties");
            props.load(fis);
        }
        catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("Error reading Configuration properties file");

            // Add logging code to log an error configuring the API on startup
        }

        return props;
    }

    /**
     * Compute a new UPP based on an initial set of preferences.
     * @param userId The new user id of the data subject
     * @param generalPreferences The set of preferences the user has provided
     * @param securityContext
     * @return
     * @throws NotFoundException
     */
    @Override
    public Response policyComputerPost(String userId, List<UserPreference> generalPreferences, SecurityContext securityContext)
    throws NotFoundException {

        /**
         * Create the Json data body of the POST call to the PDB
         */
        UserPrivacyPolicy newUPP = new UserPrivacyPolicy();
        newUPP.setUserId(userId);
        newUPP.setUserPreferences(generalPreferences);
        String serializedString = newUPP.toString();

        ComputationResult Cr = new ComputationResult();
        if(userId.startsWith("_demo")) {
            policyService.putUPP(userId, serializedString);
        }
        else {
            /**
             * Create a Jersey client for call the PDB APIs.
             */
            Client client = Client.create();
            WebResource webResourcePDB = client.resource(PDB_BASEURL + "/");

            ClientResponse policyResponse = webResourcePDB.type("application/json").
                    post(ClientResponse.class, serializedString);

            if (policyResponse.getStatus() != 200) {
                Cr.setStatus("false");
                throw new NotFoundException(400, "Failed to create user UPP : "
                        + policyResponse.toString());
            }
        }

        Cr.setStatus("true");
        Cr.setUser(userId);
        Cr.setUrl(PDB_BASEURL+"/"+userId);

        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, Cr.toString())).build();
    }
}
