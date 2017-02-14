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

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import eu.operando.core.ose.mongo.OspsMongo;
import io.swagger.api.NotFoundException;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;
import java.util.List;
import javax.ws.rs.core.MediaType;

/**
 * Set of operations for the User Privacy Policy Computation. Based upon a
 * set of inputs from questionnaires, and information from OSP about their
 * workflow and behaviour, the UPP entry is computed and updated in the PDB.
 *
 */
public class PolicyComputerService {

    /**
     * Placeholder for service state
     */
    public PolicyComputerService() {

    }

    private boolean sendEmail(String Subject, String Body) {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api", "key-f28ca9730862959738de8b244678e4f9"));

        WebResource webResource = client.resource("https://api.mailgun.net/v3/mg.operando.eu/messages");

        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "privacybot@operando.eu");
        formData.add("to", "pjgrace@gmail.com");
        formData.add("subject", Subject);
        formData.add("html", Body);

        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
        String output = clientResponse.getEntity(String.class);

        System.out.println("Email sent successfully : " + output);
        return true;
    }

    /**
     *
     * @param newPolicy The changed policy
     * @return The data object with the result of the operation
     * @throws NotFoundException
     */
    public OSPAPIResponse ospPolicyChange(String ospid, OSPPrivacyPolicy newPolicy)
        throws NotFoundException {

        /**
         * Notify the privacy analyst that there has been a change in the policy.
         * This needs a proper notification service, which will select the
         * appropriate e-mail to send the message to. This should be the email
         * service component - however, since this doesn't appear to be implemented
         * nor is it going to be: hard-code a single e-mail to a fixed address.
         */

//        if (!sendEmail(ospid + ": review policy change", "Click to review")) {
//            return new OSPAPIResponse ("Failed to notify privacy analyst of change", 405, null);
//        }

        /**
         * Find all the user policies subscribed to this OSP
         */
        OspsMongo dbClient = new OspsMongo();
        List<String> result = dbClient.getUserIdsSubscribedToOSP(ospid);

        /**
         * Recompute the UPPs for each user
         */
        for (String user: result) {
            System.out.println("user id = " + user);
        }
        /**
         * Check that it complies with the regulations - store the compliance report
         * in the database, and then notify privacy analyst of the update.
         */

        return new OSPAPIResponse("Update success", 200, null);
    }

}
