/*
 * Copyright (c) 2016 Oxford Computer Consultants Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the The MIT License (MIT).
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *
 * Contributors:
 *    Matthew Gallagher (Oxford Computer Consultants) - Creation.
 * Initially developed in the context of OPERANDO EU project www.operando.eu
 */
package eu.operando.server.watchdog;

import java.util.Vector;

public class WatchdogClientStub implements WatchdogClientI
{
	//For setup.
	private Vector<PrivacySetting> privacySettingsCurrent;
	private Vector<PrivacySetting> privacySettingsRequired;
	//For peeking.
	private boolean emailSent = false;

	/**
	 * For setup.
	 */
	public void setPrivacySettingsCurrent(Vector<PrivacySetting> privacySettingsCurrent)
	{
		this.privacySettingsCurrent = privacySettingsCurrent;		
	}
	public void setPrivacySettingsRequired(Vector<PrivacySetting> priivacySettingsRequired)
	{
		this.privacySettingsRequired = priivacySettingsRequired;		
	}
	
	/**
	 * For peeking.
	 */
	public boolean getEmailSent()
	{
		return emailSent ;
	}
	
	/**
	 * For execution.
	 */
	@Override
	public Vector<PrivacySetting> getPrivacySettingsCurrent(int userId, int ospId)
	{
		//Return the preset value.
		return privacySettingsCurrent;
	}
	@Override
	public Vector<PrivacySetting> getPrivacySettingsRequired(int userId, int ospId)
	{
		//Return the preset value.
		return privacySettingsRequired;
	}
	@Override
	public void emailPrivacyAnalystAboutUserPrivacySettingDiscrepancy(int userId, int ospId)
	{
		//Change the value of this boolean to indicate that this method has been called.
		emailSent = true;
	}
}
