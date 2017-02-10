package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.api.NotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-12T13:34:24.407Z")
public class ConsentApiServiceImpl extends ConsentApiService {
     /**
     * The API component only uses one other OPERANDO component the policy database.
     * This stores the reference, so HTTP REST calls can be made.
     */
    private String PDB_BASEURL = null;

    /**
     * The configuration of local state for the API object. Simply creates
     * the reusable reference to the PDB.
     */
    public ConsentApiServiceImpl() {
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

    @Override
    public Response consentOspIdGet(String ospId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        System.out.println("Consnet - " + ospId);
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "New Consent Report " + ospId )).build();
    }
}
