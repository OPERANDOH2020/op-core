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

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import eu.operando.api.model.PrivacySetting;
import eu.operando.moduleclients.ClientEmailServices;
import eu.operando.moduleclients.ClientOspEnforcement;
import eu.operando.moduleclients.ClientUserDeviceEnforcement;

@RunWith(MockitoJUnitRunner.class)
public class WatchdogApplicationTests
{
	
	@Mock
	private ClientUserDeviceEnforcement clientUserDeviceEnforcement;
	
	@Mock
	private ClientOspEnforcement clientOspEnforcement;
	
	@Mock
	private ClientEmailServices clientEmailServices;
	
	@InjectMocks
	private WatchdogApplication application;
	
	@Test
	public void testRunPrivacySettingsCheck_SettingsMatch_RequestToEmailAnalystNotSent()
	{
		//Set up
		int userId = 1;
		int ospId = 2;
		
		Vector<PrivacySetting> settingsCurrent = new Vector<PrivacySetting>();
		settingsCurrent.add(new PrivacySetting(1, "description", "name", "settingKey", "settingValue"));
		when(clientUserDeviceEnforcement.getPrivacySettingsCurrent(userId, ospId)).thenReturn(settingsCurrent);
		
		Vector<PrivacySetting> settingsRequired = new Vector<PrivacySetting>();
		settingsRequired.add(new PrivacySetting(1, "description", "name", "settingKey", "settingValue"));
		when(clientOspEnforcement.getPrivacySettingsRequired(userId, ospId)).thenReturn(settingsRequired);
		
		//Exercise
		application.runPrivacySettingsCheck(1, 2);
		
		//Verify
		verify(clientEmailServices, never()).sendEmail(anyString(), anyString(), anyString());
	}
	
	@Test
	public void testRunPrivacySettingsCheck_SettingsDoNotMatch_RequestToEmailAnalyst()
	{
		//Set up
		int userId = 1;
		int ospId = 2;
		
		Vector<PrivacySetting> settingsCurrent = new Vector<PrivacySetting>();
		settingsCurrent.add(new PrivacySetting(1, "description", "name", "settingKey", "settingValue"));
		when(clientUserDeviceEnforcement.getPrivacySettingsCurrent(userId, ospId)).thenReturn(settingsCurrent);

		Vector<PrivacySetting> settingsRequired = new Vector<PrivacySetting>();
		settingsRequired.add(new PrivacySetting(1, "different description", "name", "settingKey", "different settingValue"));
		when(clientOspEnforcement.getPrivacySettingsRequired(userId, ospId)).thenReturn(settingsRequired);

		//Exercise
		application.runPrivacySettingsCheck(userId, ospId);

		//Verify
		verify(clientEmailServices).sendEmail(anyString(), anyString(), anyString());
	}
}
