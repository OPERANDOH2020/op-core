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

import com.jayway.jsonpath.JsonPath;
import java.net.MalformedURLException;
import java.net.URL;
import net.minidev.json.JSONArray;

/**
 * Set of operations to support the calculations of mappings.
 */

public class ODATAPolicies {

    /**
     * Empty constructor as this is a stateless object. Cannot use static methods
     * because of the use of JsonPath Library.
     */
    public ODATAPolicies() {
    }

    /**
     * Use Westing privacy index statistics to determine the type of user
     * concerning privacy i.e. the user is one of "pragmatist, fundamentalist,
     * or unconcerned"
     *
     * @param ranking A value between 1 and 10. Any value not between 1 and 10
     * defaults to either fundamentalist or unconcerned.
     * @return The privacy classification.
     */
    public String getPrivacyRole(int ranking){
        if(ranking<3){
            return "fundamentalist";
        } else if (ranking < 8) {
            return "pragmatist";
        } else {
            return "unconcerned";
        }
    }

    /**
     * Based on a general weighting concerning a category, return a grant/deny
     * decision.
     * @param upp
     * @param DataCategory
     * @param weighting
     * @return the grant/deny. Note if no preference information is available then
     * a false is automatically generated.
     */
    public boolean grantOnWeighting(String upp, String DataCategory, String role, int weighting) {
        JSONArray userPrefs = JsonPath.read(upp, "$.user_preferences[?(@.information_type=='ANY' && @.role=='"+role+"' && @.category=='"+DataCategory+"')]");
        int rank = 0;
        for(Object aP: userPrefs) {
            String dUser = JsonPath.read(aP, "$.role");
            if(dUser.equalsIgnoreCase(role)){
                String pref = JsonPath.read(aP, "$.preference");
                rank =  Integer.parseInt(pref);
                String pClass = getPrivacyRole(rank);
                switch(pClass) {
                    case "fundamentalist":  rank = weighting - 4;
                                            break;
                    case "pragmatist":  rank = weighting -2;
                                            break;
                    case "unconcerned": rank=weighting;
                                            break;
                }
                return rank>3;
            }
        }
        return false;
    }

    /**
     *
     * @param upp The Upp to check against
     * @param DataCategory The general data category. As we've fixed on EPSOS this has to be medical
     * @param role The role of the data user. In epsos they are either a health care practicioner or not.
     * @return a grant or deny result
     */
    public boolean grantOnGeneralType(String upp, String DataCategory, String role) {
        JSONArray userPrefs = JsonPath.read(upp, "$.user_preferences[?(@.information_type==ANY'][?(@.category=="+DataCategory+"')]");
        int rank = 0;
        for(Object aP: userPrefs) {
            String dUser = JsonPath.read(aP, "$.role");
            if(dUser.equalsIgnoreCase(role)){
                String pref = JsonPath.read(aP, "$.preference");
                rank =  Integer.parseInt(pref);
            }
        }
        return rank>3;
    }

    /**
     * Extract the Data Path from the oDATA URL.
     * @param resourcePath The resource URL
     * @return A data path from the oData schema
     * @throws InvalidPreferenceException
     */
    public String getElementDataPath(String resourcePath) throws InvalidPreferenceException {
        try {
            String path = "";
            if(resourcePath.startsWith("http")){
                URL url = new URL(resourcePath);
                path = url.getPath();
            } else {
                path = resourcePath;
            }
            if (path.contains("(")) {
                int idLocation = path.indexOf(")/");
                return path.substring(idLocation + 1);
            } else {
                return path;
            }
        } catch (MalformedURLException ex) {
            throw new InvalidPreferenceException(ex.getMessage());
        }
    }

    /**
     * From the oData path calculate the privacy ranking based upon the user
     * preferences from the UPP
     * @param upp The full UPP of the user being evaluated.
     * @param oDataPath The oData path e.g. /personal_information/gender
     * @param dataUser The role of the person using the data
     * @return The user ranking for this action - between 0 and 10.
     * @throws InvalidPreferenceException
     */
    public int getPreferenceRank(String upp, String oDataPath, String dataUser) throws InvalidPreferenceException {
        /**
         * We iterate over the data field path to find matching preferences e.g. /personal_information/gender then if
         * no matching preference, try /personal_information.
         */
        String informationType = oDataPath;
        boolean prefNotFoumd = true;
        while(prefNotFoumd) {
            JSONArray userPrefs = JsonPath.read(upp, "$.user_preferences[?(@.information_type=='"+informationType+"')]");
            for(Object aP: userPrefs) {
                String dUser = JsonPath.read(aP, "$.role");
                if(dUser.equalsIgnoreCase(dataUser)){
                    String pref = JsonPath.read(aP, "$.preference");
                    return Integer.parseInt(pref);
                }
            }
            if(informationType.contains("/")){
                informationType = informationType.substring(0, informationType.lastIndexOf("/"));
            }
            if (informationType.length() == 0) {
                return 0;
            }
        }
        return 0;
    }

    /**
     * Map an incoming Request role to an EPSOS role, the schema accounts for
     * only type of role, health care practitioner and hence we map onto two
     * types: health_care_professional and other.
     * @param requestRole The type of data user.
     * @return The EPSOS role
     */
    public String getEPSOSRole(String requestRole){
        if(requestRole.equalsIgnoreCase("doctor")) {
            return "health_care_professional";
        }
        else if (requestRole.equalsIgnoreCase("nurse")) {
            return "health_care_professional";
        }
        else if (requestRole.equalsIgnoreCase("pharmacist")) {
            return "health_care_professional";
        }
        else
            return "other";
    }
}
