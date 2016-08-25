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

import java.lang.reflect.Type;
import java.util.Vector;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import com.google.common.reflect.TypeToken;

import eu.operando.ClientOperandoModule;

/**
 * The WatchdogClient is used by the WatchdogApplication to make API calls
 */
public class WatchdogClient extends ClientOperandoModule
implements WatchdogClientI
{
	private String protocolAndHostUserDeviceEnforcement = "";
	private String protocolAndHostOspEnforcement = "";
	private String protocolAndHostEmailServices = "";
	private String emailAddressPrivacyAnalyst = "";

	private Client client = ClientBuilder.newClient();

	public WatchdogClient(String protocolAndHostUserDeviceEnforcement, String protocolAndHostOspEnforcement, String protocolAndHostEmailServices, String emailAddressPrivacyAnalyst)
	{
		this.protocolAndHostUserDeviceEnforcement = protocolAndHostUserDeviceEnforcement;
		this.protocolAndHostOspEnforcement = protocolAndHostOspEnforcement;
		this.protocolAndHostEmailServices = protocolAndHostEmailServices;
		this.emailAddressPrivacyAnalyst = emailAddressPrivacyAnalyst;	
	}

	/**
	 * Gets a user's current PrivacySettings via the User Device Enforcement module.
	 */
	@Override
	public Vector<PrivacySetting> getPrivacySettingsCurrent(int userId, int ospId)
	{
		//Create a web target for the correct url.
		WebTarget target = client.target(protocolAndHostUserDeviceEnforcement);
		target = target.path(ENDPOINT_USER_DEVICE_ENFORCEMENT_PRIVACY_SETTINGS);
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
		WebTarget target = client.target(protocolAndHostOspEnforcement);
		String relativePath = String.format(ENDPOINT_OSP_ENFORCEMENT_PRIVACY_SETTINGS_VARIABLE_OSP_ID, ospId);
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

		@SuppressWarnings("serial")
		Type type = new TypeToken<Vector<PrivacySetting>>(){}.getType();
		return createObjectFromJsonFollowingOperandoConventions(strJson, type);
	}

	/**
	 * Use the Email Services module to notify a privacy analyst about a discrepancy in current and required privacy settings. 
	 */
	@Override
	public void notifyPrivacyAnalystAboutUserPrivacySettingDiscrepancy(int userId, int ospId)
	{
		Vector<String> to = new Vector<String>();
		to.add(emailAddressPrivacyAnalyst);
		String content = "The privacy settings for user " + userId+ " with OSP " + ospId + " are not as required. This requires action.";
		String subject = "Privacy settings discrepancy";
		EmailNotification emailNotification = new EmailNotification(to, new Vector<String>(), new Vector<String>(), content, subject , new Vector<Attachment>());

		//Create a web target for the correct end-point.
		WebTarget target = client.target(protocolAndHostEmailServices);
		target = target.path(ENDPOINT_EMAIL_SERVICES_EMAIL_NOTIFICATION);

		//Send the request with the email encoded as JSON in the body.
		Builder requestBuilder = target.request();
		requestBuilder.post(createEntityStringJsonFromObject(emailNotification));
	}

	public Vector<PrivacyPolicy> getOspPrivacyPolicies()
	{
		//Create a web target for the correct end-point.
		WebTarget target = client.target(protocolAndHostEmailServices);
		target = target.path(ENDPOINT_WEB_SERVICES_PRIVACY_POLICIES);

		//Send the request.
		Builder requestBuilder = target.request();
		String stringJson = requestBuilder.get(String.class);
		
		@SuppressWarnings("serial")
		Type type = new TypeToken<Vector<PrivacyPolicy>>(){}.getType();
		return createObjectFromJsonFollowingOperandoConventions(stringJson, type);
	}

	public void getOspPrivacyOptions()
	{
		//Create a web target for the correct end-point.
		WebTarget target = client.target(protocolAndHostEmailServices);
		target = target.path(ENDPOINT_WEB_SERVICES_PRIVACY_OPTIONS);

		//Send the request.
		Builder requestBuilder = target.request();
		requestBuilder.get();
	}
}
