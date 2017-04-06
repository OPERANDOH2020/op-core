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
//      Created By :            Robbie Anderson
//      Created Date :          2016-09-02
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////


/*
 * @author ra16 <ra16@it-innovation.soton.ac.uk
 */

package eu.operando.core.cpcu.servlet.springwrappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

/**
 * The Class UserPrivacyPolicy.
 */
@Generated("org.jsonschema2pojo")
public class UserPrivacyPolicy {

    /** The user id. */
    private String userId;
    
    /** The user preferences. */
    private List<UserPreference> userPreferences = new ArrayList<UserPreference>();
    
    /** The subscribed osp policies. */
    private List<SubscribedOspPolicy> subscribedOspPolicies = new ArrayList<SubscribedOspPolicy>();
    
    /** The subscribed osp settings. */
    private List<SubscribedOspSetting> subscribedOspSettings = new ArrayList<SubscribedOspSetting>();
    
    /** The additional properties. */
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Gets the user id.
     *
     * @return     The userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user id.
     *
     * @param userId     The user_id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the user preferences.
     *
     * @return     The userPreferences
     */
    public List<UserPreference> getUserPreferences() {
        return userPreferences;
    }

    /**
     * Sets the user preferences.
     *
     * @param userPreferences     The user_preferences
     */
    public void setUserPreferences(List<UserPreference> userPreferences) {
        this.userPreferences = userPreferences;
    }

    /**
     * Gets the subscribed osp policies.
     *
     * @return     The subscribedOspPolicies
     */
    public List<SubscribedOspPolicy> getSubscribedOspPolicies() {
        return subscribedOspPolicies;
    }

    /**
     * Sets the subscribed osp policies.
     *
     * @param subscribedOspPolicies     The subscribed_osp_policies
     */
    public void setSubscribedOspPolicies(List<SubscribedOspPolicy> subscribedOspPolicies) {
        this.subscribedOspPolicies = subscribedOspPolicies;
    }

    /**
     * Gets the subscribed osp settings.
     *
     * @return     The subscribedOspSettings
     */
    public List<SubscribedOspSetting> getSubscribedOspSettings() {
        return subscribedOspSettings;
    }

    /**
     * Sets the subscribed osp settings.
     *
     * @param subscribedOspSettings     The subscribed_osp_settings
     */
    public void setSubscribedOspSettings(List<SubscribedOspSetting> subscribedOspSettings) {
        this.subscribedOspSettings = subscribedOspSettings;
    }

    /**
     * Gets the additional properties.
     *
     * @return the additional properties
     */
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Sets the additional property.
     *
     * @param name the name
     * @param value the value
     */
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
