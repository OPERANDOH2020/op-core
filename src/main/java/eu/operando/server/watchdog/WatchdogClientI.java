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

public interface WatchdogClientI
{
	/**
	 * Get a user's current privacy settings with an OSP.
	 */
	public Vector<PrivacySetting> getPrivacySettingsCurrent(int userId, int ospId);

	/**
	 * Get a user's last applied privacy settings with an OSP.
	 */
	public Vector<PrivacySetting> getPrivacySettingsRequired(int userId, int ospId);

	/**
	 * Send an email to the privacy analyst, with a message about a mismatch between current and required privacy settings.
	 */
	public void notifyPrivacyAnalystAboutUserPrivacySettingDiscrepancy(int userId, int ospId);

}