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
package eu.operando.core.watchdog;

import java.util.Vector;

public class WatchdogApplication
{
	private WatchdogClientI client = null;

	public WatchdogApplication(WatchdogClientI client)
	{
		this.client = client;		
	}
	
	/**
	 * Retrieve the current and required privacy settings for this user with this OSP.
	 * Compare them, and if there's a mismatch, notify an analyst.
	 */
	public void runPrivacySettingsCheck(int userId, int ospId)
	{
		//Get the user's current and required privacy settings with this OSP.
		Vector<PrivacySetting> privacySettingsCurrent = client.getPrivacySettingsCurrent(userId, ospId);
		Vector<PrivacySetting> privacySettingsRequired = client.getPrivacySettingsRequired(userId, ospId);
		
		//If there's a mismatch, send an email.
		if (!privacySettingsCurrent.equals(privacySettingsRequired))
		{
			client.notifyPrivacyAnalystAboutUserPrivacySettingDiscrepancy(userId, ospId);
		}
	}
}