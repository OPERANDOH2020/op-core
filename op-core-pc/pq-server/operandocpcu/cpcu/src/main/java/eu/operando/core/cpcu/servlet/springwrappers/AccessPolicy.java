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
 * The Class AccessPolicy.
 */
@Generated("org.jsonschema2pojo")
public class AccessPolicy {

    /** The properties. */
    private String properties;
    
    /** The permission. */
    private String permission;
    
    /** The action. */
    private String action;
    
    /** The resource. */
    private String resource;
    
    /** The attributes. */
    private List<Attribute> attributes = new ArrayList<Attribute>();
    
    /** The additional properties. */
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Gets the properties.
     *
     * @return     The properties
     */
    public String getProperties() {
        return properties;
    }

    /**
     * Sets the properties.
     *
     * @param properties     The properties
     */
    public void setProperties(String properties) {
        this.properties = properties;
    }

    /**
     * Gets the permission.
     *
     * @return     The permission
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Sets the permission.
     *
     * @param permission     The permission
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * Gets the action.
     *
     * @return     The action
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the action.
     *
     * @param action     The action
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Gets the resource.
     *
     * @return     The resource
     */
    public String getResource() {
        return resource;
    }

    /**
     * Sets the resource.
     *
     * @param resource     The resource
     */
    public void setResource(String resource) {
        this.resource = resource;
    }

    /**
     * Gets the attributes.
     *
     * @return     The attributes
     */
    public List<Attribute> getAttributes() {
        return attributes;
    }

    /**
     * Sets the attributes.
     *
     * @param attributes     The attributes
     */
    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
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
