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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Vector;

import javax.ws.rs.HttpMethod;

import org.junit.Test;

import eu.operando.ClientOperandoModuleTests;
/**
 * TODO - handle HTTP errors being returned
 */
public class WatchdogClientTests extends ClientOperandoModuleTests
{
	private String emailAddressPrivacyAnalyst = "";
	private WatchdogClient client = new WatchdogClient(PROTOCOL_AND_HOST_HTTP_LOCALHOST, PROTOCOL_AND_HOST_HTTP_LOCALHOST,
			PROTOCOL_AND_HOST_HTTP_LOCALHOST, emailAddressPrivacyAnalyst);
		
	/**
	 * UDE
	 */
	@Test
	public void testGetPrivacySettingsCurrent_CorrectHttpRequest()
	{
		//Set Up
		String endpoint = ENDPOINT_USER_DEVICE_ENFORCEMENT_PRIVACY_SETTINGS;
		stub(HttpMethod.GET, endpoint);
		
		int userId = 1;
		int ospId = 2;
		
		//Exercise
		client.getPrivacySettingsCurrent(userId, ospId);
		
		//Verify
		HashMap<String, String> queriesParamToValue = new HashMap<String, String>();
		queriesParamToValue.put("user_id", "" + userId);
		queriesParamToValue.put("osp_id", "" + ospId);
		verifyCorrectHttpRequestWithoutBody(HttpMethod.GET, endpoint, queriesParamToValue);
	}
	@Test
	public void testGetCurrentPrivacySettings_HandlesSuccessfulResponseCorrectly()
	{
		//Set up
		Vector<PrivacySetting> settingsExpected = createTestVectorPrivacySettings();
		stub(HttpMethod.GET, ENDPOINT_USER_DEVICE_ENFORCEMENT_PRIVACY_SETTINGS, settingsExpected);
		
		//Exercise
		Vector<PrivacySetting> settingsActual = client.getPrivacySettingsCurrent(3, 4);
		
		//Verify
		assertThat(settingsActual, is(equalTo(settingsExpected)));
	}
	
	/**
	 * OSE
	 */
	@Test
	public void testGetPrivacySettingsRequired_CorrectHttpRequest()
	{
		//Set Up
		int userId = 1;
		int ospId = 2;

		String endpoint = String.format(ENDPOINT_OSP_ENFORCEMENT_PRIVACY_SETTINGS_VARIABLE_OSP_ID, ospId);
		stub(HttpMethod.GET, endpoint);
	
		//Exercise
		client.getPrivacySettingsRequired(userId, ospId);
		
		//Verify
		HashMap<String, String> queriesParamToValue = new HashMap<String, String>();
		queriesParamToValue.put("user_id", "" + userId);
		verifyCorrectHttpRequestWithoutBody(HttpMethod.GET, endpoint, queriesParamToValue);
	}
	@Test
	public void testGetRequiredPrivacySettings_HandlesSuccessfulResponseCorrectly()
	{
		//Set Up
		Vector<PrivacySetting> settingsExpected = createTestVectorPrivacySettings();
		int ospId = 4;
		
		String endpoint = String.format(ENDPOINT_OSP_ENFORCEMENT_PRIVACY_SETTINGS_VARIABLE_OSP_ID, ospId);
		stub(HttpMethod.GET, endpoint, settingsExpected);
		
		//Exercise
		int userId = 3;
		Vector<PrivacySetting> settingsActual = client.getPrivacySettingsRequired(userId, ospId);
		
		//Verify
		assertThat(settingsActual, is(equalTo(settingsExpected)));
	}
	
	/**
	 * Privacy Settings helper methods.
	 */
	private Vector<PrivacySetting> createTestVectorPrivacySettings()
	{
		//Create a vector of multiple privacy settings.
		Vector<PrivacySetting> vSettingsExpected = new Vector<PrivacySetting>();
		PrivacySetting settingOne = new PrivacySetting(1, "descOne", "nameOne", "keyOne", "valueOne");
		PrivacySetting settingTwo = new PrivacySetting(2, "descTwo", "nameTwo", "keyTwo", "valueTwo");
		vSettingsExpected.add(settingOne);
		vSettingsExpected.add(settingTwo);
		return vSettingsExpected;
	}
	
	/**
	 * ES
	 */
	@Test
	public void testNotifyPrivacyAnalystAboutUserPrivacySettingDiscrepancy_CorrectHttpRequest()
	{
		//Set Up
		int userId = 1;
		int ospId = 2;
				
		//Exercise
		client.notifyPrivacyAnalystAboutUserPrivacySettingDiscrepancy(userId, ospId);

		//Verify
		Vector<String> to = new Vector<String>();
		to.add(emailAddressPrivacyAnalyst);
		String content = "The privacy settings for user " + userId + " with OSP " + ospId + " are not as required. This requires action.";
		String subject = "Privacy settings discrepancy";
		
		EmailNotification emailNotificationExpected = new EmailNotification(to, new Vector<String>(), new Vector<String>(), content, subject, new Vector<Attachment>());
		verifyCorrectHttpRequestWithoutQueryParams(HttpMethod.POST, ENDPOINT_EMAIL_SERVICES_EMAIL_NOTIFICATION, emailNotificationExpected);
	}
	
	/**
	 * Web crawler
	 */
	@Test
	public void testGetOspPrivacyPolicies_CorrectHttpRequest()
	{
		//Set up
		stub(HttpMethod.GET, ENDPOINT_WEB_SERVICES_PRIVACY_POLICIES);
		
		//Exercise
		client.getOspPrivacyPolicies();
		
		//Verify
		verifyCorrectHttpRequest(HttpMethod.GET, ENDPOINT_WEB_SERVICES_PRIVACY_POLICIES);
	}
	@Test
	public void testGetOspPrivacyPolicies_ResponseHandledCorrectly()
	{
		//Set up
		Vector<PrivacyPolicy> policiesExpected = new Vector<PrivacyPolicy>();
		policiesExpected.add(new PrivacyPolicy(1, "some text"));
		policiesExpected.add(new PrivacyPolicy(2, "some other text"));
		stub(HttpMethod.GET, ENDPOINT_WEB_SERVICES_PRIVACY_POLICIES, policiesExpected);
		
		//Exercise
		Vector<PrivacyPolicy> policiesActual = client.getOspPrivacyPolicies();
		
		//Verify
		//TODO - this fails at the moment because equals has not been overriden for privacy policy. It should be as we'll use it in the production code (WatchdogApplication) later. 
		assertThat("The policies returned from the web crawler were incorrectly interpreted by the WatchdogClient", policiesActual, is(equalTo(policiesExpected)));
	}
	@Test
	public void testGetOspPrivacyOptions_CorrectHttpRequest()
	{
		//Exercise
		client.getOspPrivacyOptions();

		//Verify
		verifyCorrectHttpRequest(HttpMethod.GET, ENDPOINT_WEB_SERVICES_PRIVACY_OPTIONS);
	}
}