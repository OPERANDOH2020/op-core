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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.swagger.api.*;
import io.swagger.model.PrivacyRegulation;

import java.util.List;
import io.swagger.api.NotFoundException;
import io.swagger.model.ComputationResult;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class RegulationsApiServiceImpl extends RegulationsApiService {

    /**
     * The API component only uses one other OPERANDO component the policy database.
     * This stores the reference, so HTTP REST calls can be made.
     */
    private String PDB_BASEURL = null;

    /**
     * The configuration of local state for the API object. Simply creates
     * the reusable reference to the PDB.
     */
    public RegulationsApiServiceImpl() {
        super();
	Properties props;
    	props = loadDbProperties();
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
     * Called by the OSE to create a compliance check for a given
     * @param regId
     * @param securityContext
     * @return
     * @throws NotFoundException
     */
    @Override
    public Response regulationsRegIdPost(String regId, SecurityContext securityContext)
        throws NotFoundException {

        Client client = Client.create();
        WebResource webResourcePDB = client.resource(PDB_BASEURL + "/regulations/" + regId);

        ClientResponse regResponse = webResourcePDB.type("application/json").get(ClientResponse.class);

        ComputationResult nRes = new ComputationResult();
        if (regResponse.getStatus() != 200) {
            nRes.setStatus("RegId not stored in PDB");
        }
        else {
            nRes.setStatus("RegID is stored in PDB. Evaluation Report Implementation incomplete");
            nRes.setUrl(PDB_BASEURL+"/regulations/" + regId + "/report");
        }
        return Response.ok().entity(nRes).build();
    }
    @Override
    public Response regulationsRegIdPut(String regId, List<PrivacyRegulation> regulation, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        Client client = Client.create();
        WebResource webResourcePDB = client.resource(PDB_BASEURL + "/regulations/" + regId);

        ClientResponse regResponse = webResourcePDB.type("application/json").get(ClientResponse.class);

        ComputationResult nRes = new ComputationResult();
        if (regResponse.getStatus() != 200) {
            nRes.setStatus("RegId not stored in PDB");
        }
        else {
            nRes.setStatus("RegID is stored in PDB. Evaluation Report Implementation incomplete");
            nRes.setUrl(PDB_BASEURL+"/regulations/" + regId + "/report");
        }
        return Response.ok().entity(nRes).build();
    }
}
