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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import eu.operando.core.watchdog.PrivacySetting;
import eu.operando.core.watchdog.WatchdogApplication;

public class WatchdogApplicationTests
{
	
	private WatchdogClientStub clientStub = new WatchdogClientStub();
	
	@Before
	public void setUp()
	{
		clientStub = new WatchdogClientStub();
	}
	
	@Test
	public void testRunPrivacySettingsCheck_SettingsMatch_RequestToEmailAnalystNotSent()
	{
		//Set up
		Vector<PrivacySetting> settingsCurrent = new Vector<PrivacySetting>();
		settingsCurrent.add(new PrivacySetting(1, "description", "name", "settingKey", "settingValue"));
		clientStub.setPrivacySettingsCurrent(settingsCurrent);
		
		Vector<PrivacySetting> settingsRequired = new Vector<PrivacySetting>();
		settingsRequired.add(new PrivacySetting(1, "description", "name", "settingKey", "settingValue"));
		clientStub.setPrivacySettingsRequired(settingsRequired);
		
		WatchdogApplication application = new WatchdogApplication(clientStub);
		
		//Exercise
		application.runPrivacySettingsCheck(1, 2);
		
		//Verify
		assertThat("Settings did match so no email should have been sent.", clientStub.getEmailSent(), is(false));
	}
	
	@Test
	public void testRunPrivacySettingsCheck_SettingsDoNotMatch_RequestToEmailAnalyst()
	{
		//Set up
		Vector<PrivacySetting> settingsCurrent = new Vector<PrivacySetting>();
		settingsCurrent.add(new PrivacySetting(1, "description", "name", "settingKey", "settingValue"));
		clientStub.setPrivacySettingsCurrent(settingsCurrent);

		Vector<PrivacySetting> settingsRequired = new Vector<PrivacySetting>();
		settingsRequired.add(new PrivacySetting(1, "different description", "name", "settingKey", "settingValue"));
		clientStub.setPrivacySettingsRequired(settingsRequired);

		WatchdogApplication application = new WatchdogApplication(clientStub);
		
		//Exercise
		application.runPrivacySettingsCheck(1, 2);

		//Verify
		assertThat("Settings did not match so an email should have been sent.", clientStub.getEmailSent(), is(true));
	}
}
