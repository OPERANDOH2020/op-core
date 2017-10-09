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
package eu.operando;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;
import eu.operando.core.pdb.common.model.UserPrivacyPolicy;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * The set of methods to perform reusable  actions on Operando modules.
 */
public class HelperMethods {

    /**
     * From a the UPP URL get the UserPrivacyPolicy object.
     * @param url The full HTTP url to retrieve the UPP from.
     * @return The java object version of the UPP. Null if not found.
     * @throws eu.operando.UnknownUserException Exception thrown when error
     * retrieving the user policy.
     */
    public UserPrivacyPolicy getUserPrivacyPolicy(String url)
            throws UnknownUserException {

        UserPrivacyPolicy returnUPP = null;
        try {
            /**
             * Get the UPP from the PDB.
             */
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response1 = httpclient.execute(httpget);
            /**
             * Convert the response to a UPP object.
             */
            HttpEntity entity = response1.getEntity();
            if(response1.getStatusLine().getStatusCode()!= 200) {
                throw new UnknownUserException("User could not be found");
            } else {
                String currentUpp = EntityUtils.toString(entity);
                ObjectMapper objMapper = new ObjectMapper();
                returnUPP = objMapper.readValue(currentUpp, UserPrivacyPolicy.class);
            }
        } catch (IOException ex) {
            throw new UnknownUserException("User could not be found", ex);
        }
        return returnUPP;
    }

    /**
     * Get the OSP policy from the PDB.
     * @param ospURL the URL with the numeric reference to the OSP policy
     * @return The OSP policy object in java format.
     * @throws eu.operando.UnknownOSPException When OSP isn't found.
     */
    public OSPPrivacyPolicy getOSPPolicy(String ospURL)
            throws UnknownOSPException {

        try {
            /**
             * Get the OSP from the PDB.
             */
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(ospURL);
            CloseableHttpResponse response1 = httpclient.execute(httpget);

            /**
             * If there is no OSP, then complete fail.
             */
            HttpEntity entity = response1.getEntity();
            if(response1.getStatusLine().getStatusCode()!= 200) {
                throw new UnknownOSPException("OSP could not be found");
            }

            ObjectMapper mapper = new ObjectMapper();
            OSPPrivacyPolicy ospPolicy = mapper.readValue(EntityUtils.toString(entity), OSPPrivacyPolicy.class);
            return ospPolicy;
        } catch (IOException ex) {
            throw new UnknownOSPException("Error retrieving OSP policy", ex);
        }

    }
}
