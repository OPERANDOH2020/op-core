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

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import com.jayway.jsonpath.JsonPath;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import net.minidev.json.JSONArray;

/**
 * The set of methods to perform reusable test actions on Operando modules.
 */
public class TestHelperMethods {

    /**
     * Reference to the PDB module APIs.
     */
    private String PDB_OSP_URL = "http://integration.operando.esilab.org:8096/operando/core/pdb/OSP";
//    private String OSE_URL = "http://integration.operando.esilab.org:8094/operando/core/ose/osps/"; //59e56a91ee35880033f8f62c/reason";
    private String OSE_URL = "http://localhost:8090/osps"; //59e56a91ee35880033f8f62c/reason";
    /**
     * Jersey client for making calls.
     */
    private final Client client;

    public TestHelperMethods() {
        client = new Client();
    }

    /**
     * Query for an OSP policy using a friendly keyword e.g. foodcoach versus
     * the ID 4534534.
     *
     * @param friendlyName The keywords to search for.
     * @return The Operando ospID for the OSP with this friendly data.
     */
    public String ospQuerybyFriendlyName(String friendlyName) {
        String ospAPI = PDB_OSP_URL+"?filter=";
        WebResource webResourcePDB = client.resource(ospAPI);
        ClientResponse policyResponse = webResourcePDB.type("application/json").get(ClientResponse.class);
        if(policyResponse.getStatus() != 200) {
            return null;
        }
        String filterResults = policyResponse.getEntity(String.class);
        JSONArray access_policies = JsonPath.read(filterResults, "$..[?(@.policy_url=='" + friendlyName + "')]");
        for(Object aP: access_policies) {
            String id = JsonPath.read(aP, "$.osp_policy_id");
            if(id != null)
                return id;
        }
        return null;
    }


    /**
     * Add the OSP policy to PDB module via the OSP API.
     * @param fileLoc The json file with the content
     * @return The ID of the created OSP policy
     */
    public boolean createOSPReason(String ospId, String fileLoc) {

        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(fileLoc);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);

            WebResource webResourcePDB = client.resource(PDB_OSP_URL + "/" + ospId + "/privacy-policy/");
            ClientResponse policyResponse = webResourcePDB.type("application/json").put(ClientResponse.class,
                    content);

            System.out.println("POST " + fileLoc + "status code:" + policyResponse.getStatus());

            if (policyResponse.getStatus() != 204) {
                System.err.println("POST " + fileLoc + "error message:" + policyResponse.getEntity(String.class));
                return false;
            }

            return true;
        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("POST " + fileLoc + "Error creating UPP in pdb - " + e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * Add the OSP policy to PDB module via the OSP API.
     * @param fileLoc The json file with the content
     * @return The ID of the created OSP policy
     */
    public String changeOSPReason(String ospId, String reason, String reasonId) {


            WebResource webResourcePDB = client.resource(PDB_OSP_URL + "/" + ospId + "/privacy-policy/access-reasons/" + reasonId);
            ClientResponse policyResponse = webResourcePDB.type("application/json").put(ClientResponse.class,
                    reason);

            System.out.println("POST " + reason + "status code:" + policyResponse.getStatus());

            if (policyResponse.getStatus() != 201) {
                System.err.println("POST " + reason + "error message:" + policyResponse.getEntity(String.class));
                return null;
            }

            return ospQuerybyFriendlyName(JsonPath.parse(reason).read("$.policy_url", String.class));

    }

    /**
     * Add the OSP policy to PDB module via the OSP API.
     * @param ospPolicy The json file with the content
     * @return The ID of the created OSP policy
     */
    public boolean changeOSPPolicy(String ospId, String ospPolicy) {
        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(ospPolicy);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);

            WebResource webResourcePDB = client.resource(PDB_OSP_URL + "/" + ospId );
            ClientResponse policyResponse = webResourcePDB.type("application/json").put(ClientResponse.class,
                    content);

            System.out.println("status code:" + policyResponse.getStatus());

            if (policyResponse.getStatus() != 201) {
                System.err.println("error message:" + policyResponse.getEntity(String.class));
                return false;
            }

            return true;
        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("POST " + ospPolicy + "Error updating OSP in pdb - " + e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * Add the OSP policy to PDB module via the OSP API.
     * @param ospPolicy The json file with the content
     * @return The ID of the created OSP policy
     */
    public boolean changeOSEOSPPolicy(String ospId, String ospPolicy) {
        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(ospPolicy);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);

            WebResource webResourcePDB = client.resource(OSE_URL + "/" + ospId );
            ClientResponse policyResponse = webResourcePDB.type("application/json").put(ClientResponse.class,
                    content);

            System.out.println("status code:" + policyResponse.getStatus());

            if (policyResponse.getStatus() != 201) {
                System.err.println("error message:" + policyResponse.getEntity(String.class));
                return false;
            }

            return true;
        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("POST " + ospPolicy + "Error updating OSP in pdb - " + e.getLocalizedMessage());
            return false;
        }
    }


}
