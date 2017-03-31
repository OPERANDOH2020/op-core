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
package eu.operando.core.cpcu.servlet.configurations;

import org.json.simple.JSONObject;

/**
 * The Class ServiceConfiguration.
 */
public class ServiceConfiguration extends Configuration{

	/** The id. */
	private int id;
	
	/** The aquisition method. */
	private String aquisitionMethod;
	
	/** The role location. */
	private String roleLocation;
	
	/** The data location. */
	private String dataLocation;
	
	/** The preference. */
	private String preference;
	
	/** The metadata. */
	private String metadata;
	
	/**
	 * Gets the metadata.
	 *
	 * @return the metadata
	 */
	public String getMetadata() {
		return metadata;
	}
	
	/**
	 * Sets the metadata.
	 *
	 * @param metadata the new metadata
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the aquisition method.
	 *
	 * @return the aquisition method
	 */
	public String getAquisitionMethod() {
		return aquisitionMethod;
	}
	
	/**
	 * Sets the aquisition method.
	 *
	 * @param aquisitionMethod the new aquisition method
	 */
	public void setAquisitionMethod(String aquisitionMethod) {
		this.aquisitionMethod = aquisitionMethod;
	}
	
	/**
	 * Gets the role location.
	 *
	 * @return the role location
	 */
	public String getRoleLocation() {
		return roleLocation;
	}
	
	/**
	 * Sets the role location.
	 *
	 * @param roleLocation the new role location
	 */
	public void setRoleLocation(String roleLocation) {
		this.roleLocation = roleLocation;
	}
	
	/**
	 * Gets the data location.
	 *
	 * @return the data location
	 */
	public String getDataLocation() {
		return dataLocation;
	}
	
	/**
	 * Sets the data location.
	 *
	 * @param dataLocation the new data location
	 */
	public void setDataLocation(String dataLocation) {
		this.dataLocation = dataLocation;
	}
	
	/**
	 * Gets the preference.
	 *
	 * @return the preference
	 */
	public String getPreference() {
		return preference;
	}
	
	/**
	 * Sets the preference.
	 *
	 * @param preference the new preference
	 */
	public void setPreference(String preference) {
		this.preference = preference;
	}
	
	/* (non-Javadoc)
	 * @see eu.operando.core.cpcu.servlet.Configuration#convert(org.json.simple.JSONObject)
	 */
	@Override
	public void convert(JSONObject obj) {
		setId(((Long) obj.get("id")).intValue());
		setAquisitionMethod((String) obj.get("aquisitionMethod"));
		setRoleLocation((String) obj.get("roleLocation"));
		setDataLocation((String) obj.get("dataLocation"));
		setPreference((String) obj.get("preference"));
		setPreference((String) obj.get("metadata"));
	}
	
	
}
