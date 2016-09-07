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

import eu.operando.Utils;
import eu.operando.api.model.PrivacySetting;
import eu.operando.moduleclients.ClientEmailServices;
import eu.operando.moduleclients.ClientOspEnforcement;
import eu.operando.moduleclients.ClientUserDeviceEnforcement;

public class WatchdogApplication
{
	// Location of properties file.
	private static final String PROPERTIES_FILE_RAPI = "config.properties";

	// Properties file property names.
	private static final String PROPERTY_NAME_ORIGIN_USER_DEVICE_ENFORCEMENT = "OriginUserDeviceEnforcement";
	private static final String PROPERTY_NAME_ORIGIN_OSP_ENFORCEMENT = "OriginOspEnforcement";
	private static final String PROPERTY_NAME_ORIGIN_EMAIL_SERVICES = "OriginEmailServices";
	private static final String PROPERTY_NAME_EMAIL_ADDRESS_FOR_NOTIFICATIONS = "EmailAddressForNotifications";

	// Properties file property values.
	private static final String ORIGIN_USER_DEVICE_ENFORCEMENT = Utils.loadPropertyString(PROPERTIES_FILE_RAPI, PROPERTY_NAME_ORIGIN_USER_DEVICE_ENFORCEMENT);
	private static final String ORIGIN_OSP_ENFORCEMENT = Utils.loadPropertyString(PROPERTIES_FILE_RAPI, PROPERTY_NAME_ORIGIN_OSP_ENFORCEMENT);
	private static final String ORIGIN_EMAIL_SERVICES = Utils.loadPropertyString(PROPERTIES_FILE_RAPI, PROPERTY_NAME_ORIGIN_EMAIL_SERVICES);
	private static final String EMAIL_ADDRESS_FOR_NOTIFICATIONS = Utils.loadPropertyString(PROPERTIES_FILE_RAPI, PROPERTY_NAME_EMAIL_ADDRESS_FOR_NOTIFICATIONS);

	private ClientUserDeviceEnforcement clientUserDeviceEnforcement = new ClientUserDeviceEnforcement(ORIGIN_USER_DEVICE_ENFORCEMENT);
	private ClientOspEnforcement clientOspEnforcement = new ClientOspEnforcement(ORIGIN_OSP_ENFORCEMENT);
	private ClientEmailServices clientEmailServices = new ClientEmailServices(ORIGIN_EMAIL_SERVICES);

	/**
	 * Retrieve the current and required privacy settings for this user with this OSP. Compare them, and if there's a mismatch, notify an analyst.
	 */
	public void runPrivacySettingsCheck(int userId, int ospId)
	{
		// Get the user's current and required privacy settings with this OSP.
		Vector<PrivacySetting> privacySettingsCurrent = clientUserDeviceEnforcement.getPrivacySettingsCurrent(userId, ospId);
		Vector<PrivacySetting> privacySettingsRequired = clientOspEnforcement.getPrivacySettingsRequired(userId, ospId);

		// If there's a mismatch, send an email.
		if (!privacySettingsCurrent.equals(privacySettingsRequired))
		{
			String content = "The privacy settings for user " + userId + " with OSP " + ospId + " are not as required. This requires action.";
			String subject = "Privacy settings discrepancy";

			clientEmailServices.sendEmail(EMAIL_ADDRESS_FOR_NOTIFICATIONS, subject, content);
		}
	}
}
