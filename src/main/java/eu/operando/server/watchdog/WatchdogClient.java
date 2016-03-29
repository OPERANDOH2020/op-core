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

import java.lang.reflect.Type;
import java.util.Vector;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * The WatchdogClient is used by the WatchdogApplication to make API calls
 */
public class WatchdogClient implements WatchdogClientI
{

	private static final String RELATIVE_PATH_USER_DEVICE_ENFORCEMENT_PRIVACY_SETTINGS = "/privacy_settings";
	private static final String RELATIVE_PATH_OSP_ENFORCEMENT_PRIVACY_SETTINGS = "osps/%d/privacy_settings"; //%d is {osp_id}
	private static final String RELATIVE_PATH_EMAIL_SERVICES_EMAILS = "/emails";
	
	private static final int SENDER_ID_EMAIL_WATCHDOG = 4; 
	
	private String urlUserDeviceEnforcement = "";
	private String urlOspEnforcement = "";
	private String urlEmailServices = "";
	private int userIdPrivacyAnalyst = -1;

	private Client client = ClientBuilder.newClient();
	
	public WatchdogClient(String urlUserDeviceEnforcement, String urlOspEnforcement, String urlEmailServices)
	{
		this.urlUserDeviceEnforcement = urlUserDeviceEnforcement;
		this.urlOspEnforcement = urlOspEnforcement;
		this.urlEmailServices = urlEmailServices;	
	}

	/**
	 * Gets a user's current PrivacySettings via the User Device Enforcement module.
	 */
	@Override
	public Vector<PrivacySetting> getPrivacySettingsCurrent(int userId, int ospId)
	{
		//Create a web target for the correct url.
		WebTarget target = client.target(urlUserDeviceEnforcement);
		target = target.path(RELATIVE_PATH_USER_DEVICE_ENFORCEMENT_PRIVACY_SETTINGS);
		target = target.queryParam("user_id", userId);
		target = target.queryParam("osp_id", ospId);

		return sendRequestForPrivacySettingsAndConvertResponseJsonToPrivacySettings(target);
	}
	
	/**
	 * Gets the user's PrivacySettings last applied by the OSP Enforcement module.
	 */
	@Override
	public Vector<PrivacySetting> getPrivacySettingsRequired(int userId, int ospId)
	{
		//Create a web target for the correct end-point.
		WebTarget target = client.target(urlOspEnforcement);
		String relativePath = String.format(RELATIVE_PATH_OSP_ENFORCEMENT_PRIVACY_SETTINGS, ospId);
		target = target.path(relativePath);
		target = target.queryParam("user_id", userId);

		return sendRequestForPrivacySettingsAndConvertResponseJsonToPrivacySettings(target);
	}
	
	/**
	 * Send a request, which will return a body of JSON representing an array of privacy settings, to the appropriate target.
	 * Interpret the response and return the result. 
	 */
	private Vector<PrivacySetting> sendRequestForPrivacySettingsAndConvertResponseJsonToPrivacySettings(WebTarget target)
	{
		//Send the request an get the response.
		Builder requestBuilder = target.request();
		String strJson = requestBuilder.get(String.class);
		
		return convertJsonToPrivacySettingsVector(strJson);
	}
	
	/**
	 * Convert JSON representing an array of privacy settings to a Java vector of privacy settings.
	 */
	private Vector<PrivacySetting> convertJsonToPrivacySettingsVector(String strJson)
	{
		//JSON is in snake_case, but object fields are camelCase. 
		GsonBuilder gsonBuilder = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
		Gson gson = gsonBuilder.create();
		
		//Tell gson to turn the array into a vector of privacy settings.
		Type type = new TypeToken<Vector<PrivacySetting>>(){}.getType();
		Vector<PrivacySetting> settings = gson.fromJson(strJson, type);
		return settings;
	}
	
	/**
	 * Use the Email Services module to notify a privacy analyst about a discrepancy in current and required privacy settings. 
	 */
	@Override
	public void emailPrivacyAnalystAboutUserPrivacySettingDiscrepancy(int userId, int ospId)
	{
		Vector<Integer> to = new Vector<Integer>();
		to.add(userIdPrivacyAnalyst);
		String content = "The privacy settings for user " + userId+ " with OSP " + ospId + " are not as required. This requires action.";
		Email email = new Email(SENDER_ID_EMAIL_WATCHDOG, to, new Vector<Integer>(), new Vector<Integer>(), content, new Vector<Attachment>());
		
		//Create a web target for the correct end-point.
		WebTarget target = client.target(urlEmailServices);
		target = target.path(RELATIVE_PATH_EMAIL_SERVICES_EMAILS);
		
		//Send the request with the email encoded as JSON in the body.
		Builder requestBuilder = target.request();
		Entity<Email> json = Entity.json(email);
		requestBuilder.post(json);
	}

	public void setUserIdPrivacyAnalyst(int userIdPrivacyAnalyst)
	{
		this.userIdPrivacyAnalyst = userIdPrivacyAnalyst;
	}
}
