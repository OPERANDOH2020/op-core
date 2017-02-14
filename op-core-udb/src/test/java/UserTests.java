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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;

/**
 * Set of tests for the user account information. When the UI registers
 * a new user call this API. When the UI needs information about the
 * account, call this API.
 *
 */
public class UserTests {

    private static String UDB_URL = "http://localhost:8080/operando/core/udb";

    /**
     * The configuration of local state for the API object. Simply creates
     * the reusable reference to the PDB.
     */
    public UserTests() {
        super();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String user_two = "{\n" +
            "  \"userid\": \"user2\",\n" +
            "  \"fullname\": \"User One\",\n" +
            "  \"address\": \"Gotham\",\n" +
            "  \"usertype\": \"normal-user\",\n" +
            "  \"emailaddress\": \"b@io.eu\",\n" +
            "  \"city\": \"LowerVille\",\n" +
            "  \"country\": \"UK\"\n" +
            "}";

        String user_two_update = "{\n" +
            "  \"userid\": \"user2\",\n" +
            "  \"fullname\": \"User One\",\n" +
            "  \"address\": \"Gotham\",\n" +
            "  \"usertype\": \"admin-user\",\n" +
            "  \"emailaddress\": \"b@io.eu\",\n" +
            "  \"city\": \"LowerVille\",\n" +
            "  \"country\": \"USA\"\n" +
            "}";

        /**
         * Create a Jersey client for call the UDB API.
         */
        Client client = Client.create();

        /**
         * Post a new user to the UDB
         */
        WebResource usersService = client.resource(UDB_URL + "/users");

        ClientResponse udbResponse = usersService.type(MediaType.APPLICATION_JSON).post(ClientResponse.class, user_two);
        if (udbResponse.getStatus() != 201) {
           System.err.println("Unable to create user");
           System.err.println(udbResponse.getStatus() + ":" + udbResponse.getEntity(String.class));
        }

        /**
         * Retrieve the User Account Information
         */
        WebResource usersGetService = client.resource(UDB_URL + "/users/user2");

        ClientResponse udbResponse2 = usersGetService.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (udbResponse2.getStatus() != 200) {
           System.err.println("Unable to get user");
           System.err.println(udbResponse2.getStatus() + ":" + udbResponse2.getEntity(String.class));
           System.exit(-1);
        }
        else {
            System.out.println("User Account Information: ");
            String jsonToken = udbResponse2.getEntity(String.class);
            System.out.println(jsonToken);
        }

        /**
         * Update the account info
         */
        WebResource usersPutService = client.resource(UDB_URL + "/users/user2");

        ClientResponse udbResponse4 = usersPutService.type(MediaType.APPLICATION_JSON).put(ClientResponse.class, user_two_update);
        if (udbResponse4.getStatus() != 204) {
           System.err.println("Unable to change user");
           System.err.println(udbResponse4.getStatus() + ":" + udbResponse2.getEntity(String.class));
           System.exit(-1);
        }
        else {
            System.out.println("User Information updated: ");
        }

        /**
         * Test that the update happened
         */
        udbResponse2 = usersGetService.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (udbResponse2.getStatus() != 200) {
           System.err.println("Unable to get user");
           System.err.println(udbResponse2.getStatus() + ":" + udbResponse2.getEntity(String.class));
           System.exit(-1);
        }
        else {
            System.out.println("User Account Information: ");
            String jsonToken = udbResponse2.getEntity(String.class);
            System.out.println(jsonToken);
        }

        /**
         * Delete the user account
         */
        WebResource usersDeleteService = client.resource(UDB_URL + "/users/user2");

        ClientResponse udbResponse3 = usersDeleteService.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
        if (udbResponse3.getStatus() != 204) {
            System.err.println("Unable to delete user");
            System.err.println(udbResponse3.getStatus() + ":" + udbResponse2.getEntity(String.class));
            System.exit(-1);
        }

        /**
         * Test that it has gone
         */
        udbResponse2 = usersGetService.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (udbResponse2.getStatus() != 404) {
           System.err.println("User not deleted");
           System.err.println(udbResponse2.getStatus() + ":" + udbResponse2.getEntity(String.class));
           System.exit(-1);
        }
        else {
            System.out.println("User Account Deleted: ");
            String jsonToken = udbResponse2.getEntity(String.class);
            System.out.println(jsonToken);
        }
    }

}
