

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
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * A set of unit tests to check the post/put and delete operations of the
 * PDB User Privacy Policy API.
 *
 * Uses example JSON policies from the yellow pages application.
 *
 */
public class CreateFiveUsers {

    Client client = new Client();
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

    private ClientResponse callAPI(String content){
        WebResource webResourcePDB = client.resource(aapiBasePath);
        ClientResponse policyResponse = webResourcePDB.type("application/json").post(ClientResponse.class,
                    content);
        return policyResponse;
    }

    public static void main(String[] args) {
        CreateFiveUsers obj = new CreateFiveUsers();
        // Create the five users
        ClientResponse callAPI = obj.callAPI(obj.jsonUser("normal", "user", "normal_user"));
        System.out.println("Normal User, POST status code:" + callAPI.getStatus());

        callAPI = obj.callAPI(obj.jsonUser("osp", "admin", "osp_admin"));
        System.out.println("OSP admin User, POST status code:" + callAPI.getStatus());

        callAPI = obj.callAPI(obj.jsonUser("privacy", "analyst", "privacy_analyst"));
        System.out.println("PA User, POST status code:" + callAPI.getStatus());

        callAPI = obj.callAPI(obj.jsonUser("psp", "admin", "psp_admin"));
        System.out.println("PSP admin User, POST status code:" + callAPI.getStatus());

        callAPI = obj.callAPI(obj.jsonUser("ospregulator", "regulator", "regulator"));
        System.out.println("Regulator, POST status code:" + callAPI.getStatus());

    }

}
