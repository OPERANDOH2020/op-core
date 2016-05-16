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

import io.swagger.client.ApiException;
import io.swagger.client.api.UPPApi;
import io.swagger.model.ComputationResult;
import io.swagger.model.UserPreference;
import io.swagger.model.UserPrivacyPolicy;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 *
 * @author pjg
 */
public class PolicyComputerService {

    /**
     * Stateless computation to update the UPP of a given user who has registered
     * with the
     * @param userId The Operando ID of the user to compute the policy for
     * @param generalPreferences The list of initial preferences
     * @return
     * @throws eu.operando.UnknownUserException
     * @throws eu.operando.InvalidPreferenceException
     */
    public static String computeNewPolicy(String userId, List<UserPreference> generalPreferences)
        throws UnknownUserException, InvalidPreferenceException{
        try {
            /**
             * Read the preferences and then build the User General Preferences
             */
            UPPApi dbase = new UPPApi();

            // Based on the inputs generate the preferences. For now this is a straight copy

             /**
             * Update the UPP in the policy DB
             */
            UserPrivacyPolicy uPP = dbase.userPrivacyPolicyUserIdGet(userId);
            uPP.setUserPreferences(generalPreferences);

            dbase.userPrivacyPolicyUserIdPut(userId, uPP);

            /**
             * Return the URL to the updated policy
             */
            URL urlResponse = new URL("http://127.0.0.1:8081/policy_database/"+userId);
            ComputationResult cr = new ComputationResult();
            cr.setUrl(urlResponse.toExternalForm());
            cr.setStatus("true");
            cr.setUser(userId);
            return cr.toString();
        } catch (ApiException ex) {
            throw new UnknownUserException("Error computing");
        } catch (MalformedURLException ex) {
            throw new UnknownUserException("Error computing");
        }
    }

    public static String computeOSPPolicy(String userId, String ospID, List<UserPreference> generalPreferences)
        throws UnknownUserException, InvalidPreferenceException{
        try {
            /**
             * Read the preferences and then build the User General Preferences
             */
            UPPApi dbase = new UPPApi();

            // Based on the inputs generate the preferences. For now this is a straight copy

             /**
             * Update the UPP in the policy DB
             */
            UserPrivacyPolicy uPP = dbase.userPrivacyPolicyUserIdGet(userId);
            uPP.setUserPreferences(generalPreferences);

            dbase.userPrivacyPolicyUserIdPut(userId, uPP);

            /**
             * Return the URL to the updated policy
             */
            URL urlResponse = new URL("http://127.0.0.1:8081/policy_database/"+userId);
            ComputationResult cr = new ComputationResult();
            cr.setUrl(urlResponse.toExternalForm());
            cr.setStatus("true");
            cr.setUser(userId);
            return cr.toString();
        } catch (ApiException ex) {
            throw new UnknownUserException("Error computing");
        } catch (MalformedURLException ex) {
            throw new UnknownUserException("Error computing");
        }
    }

}
