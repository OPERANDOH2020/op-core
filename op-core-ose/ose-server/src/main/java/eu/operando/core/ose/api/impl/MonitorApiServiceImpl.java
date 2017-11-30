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

package eu.operando.core.ose.api.impl;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import io.swagger.api.*;
import io.swagger.api.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Implementation of the monitor API method - that is used by
 * an Operando PSP platform admin to determine if the server is running
 * or not.
 */

public class MonitorApiServiceImpl extends MonitorApiService {

    /**
     * reason policies
     */
    private static final String YELLOWPAGES = "YellowPages";
    private static final String YELLOWPAGESFILE = "/yellowPagesReason.json";

    private static final String AMI = "ami";
    private static final String AMIFILE = "amiReason.json";

    private static final String ASLBERG = "YellowPages";
    private static final String ASLBERGFILE = "yellowPagesReason.json";

    private static final String GASLINI = "YellowPages";
    private static final String GASLINIFILE = "yellowPagesReason.json";

    private static final String ASLIL = "YellowPages";
    private static final String ASLILFILE = "yellowPagesReason.json";


    /**
     * Authentication API endpoint.
     */
    String aapiBasePath = "http://integration.operando.esilab.org:8135/operando/interfaces/aapi/aapi/user/register";

    private String jsonUser(String username, String password, String userType) {
        String content = "{\n" +
        "  \"optionalAttrs\": [\n" +
        "    {\n" +
        "      \"attrName\": \"user_type\",\n" +
        "      \"attrValue\": \""+ userType + "\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"password\": \""+ password + "\",\n" +
        "  \"privacySettings\": [\n" +
        "    {\n" +
        "      \"settingName\": \"string\",\n" +
        "      \"settingValue\": \"string\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"requiredAttrs\": [\n" +
        "    {\n" +
        "      \"attrName\": \"string\",\n" +
        "      \"attrValue\": \"string\"\n" +
        "    }\n" +
        "  ],\n" +
        "  \"username\": \""+ username + "\"\n" +
        "}";
        return content;
    }

    /**
     * Make the Authentication registration call.
     * @param content The json content with the user information to register.
     * @return The response from the Web API
     */
    private ClientResponse callAPI(String content){
        Client client = new Client();
        WebResource webResourcePDB = client.resource(aapiBasePath);
        ClientResponse policyResponse = webResourcePDB.type("application/json").post(ClientResponse.class,
                    content);
        return policyResponse;
    }

    private void buildCoreUsers(){
        ClientResponse callAPI = callAPI(jsonUser("normal", "user", "normal_user"));
        callAPI = callAPI(jsonUser("osp", "admin", "osp_admin"));
        callAPI = callAPI(jsonUser("privacy", "analyst", "privacy_analyst"));
        callAPI = callAPI(jsonUser("psp", "admin", "psp_admin"));
        callAPI = callAPI(jsonUser("ospregulator", "regulator", "regulator"));
    }

    private void addReasonPolicy(String name, String file, HelperMethods tMethods) {

        String currentOSPID = tMethods.ospQuerybyFriendlyName(name);
        System.out.println("The " + name + " OSP ID field is: " + currentOSPID);

        if(tMethods.createOSPReason(currentOSPID, file)){
            System.out.println("The " + name + " reason policy was added successfully");
        } else {
            System.out.println("The " + name + " reason policy was not added");
        }
    }

    /**
     * HTTP GET method to perform a simple monitoring operation on the OSE
     * component. Returns 200 OK if server is up. Connection error or a 404
     * will be returned if the service fails.
     * @param securityContext The JAX-RS context information passed to the method implementation.
     * @return A HTTP response with a status code and status body message.
     * @throws NotFoundException
     */

    @Override
    public Response monitorGet(SecurityContext securityContext) throws NotFoundException {
        /**
         * Simple method to respond with the stated response message and a HTTP
         * 200 OK code.
         */
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "The service is currently running")).build();
    }

    /**
     * HTTP GET method to retrieve a HTTP status code that
     * @param securityContext The JAX-RS context information passed to the method implementation.
     * @return A HTTP response with a status code and status body message.
     * @throws NotFoundException
     */

    @Override
    public Response startGet(SecurityContext securityContext) throws NotFoundException {
        /**
         * Create the core users.
         */
        buildCoreUsers();

        HelperMethods tMethods = new HelperMethods();
        addReasonPolicy(YELLOWPAGES, YELLOWPAGESFILE, tMethods);
        /**
         * Add the reason policies
         */
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "The service is initiated")).build();
    }
}
