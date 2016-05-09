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

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The class representing the application which will actually be run.
 */
public class WatchdogLauncher
{
	private static final String PROPERTIES_FILE = "config.properties";
	
	public static final String PROPERTY_NAME_PROTOCOL_AND_HOST_USER_DEVICE_ENFORCEMENT = "protocolAndHostUserDeviceEnforcement";
	public static final String PROPERTY_NAME_PROTOCOL_AND_HOST_OSP_ENFORCEMENT = "protocolAndHostOspEnforcement";
	public static final String PROPERTY_NAME_PROTOCOL_AND_HOST_EMAIL_SERVICES = "protocolAndHostEmailServices";
	public static final String PROPERTY_NAME_EMAIL_ADDRESS_PRIVACY_ANALYST = "emailAddressPrivacyAnalyst";
	public static final String PROPERTY_NAME_USER_ID = "userId";
	public static final String PROPERTY_NAME_OSP_ID = "ospId";

	public static void main(String[] args)
	{
		//Initialise the system.
		String protocolAndHostUserDeviceEnforcement = loadPropertyFromPropertiesFileString(PROPERTY_NAME_PROTOCOL_AND_HOST_USER_DEVICE_ENFORCEMENT);
		String protocolAndHostOspEnforcement = loadPropertyFromPropertiesFileString(PROPERTY_NAME_PROTOCOL_AND_HOST_OSP_ENFORCEMENT);
		String protocolAndHostEmailServices = loadPropertyFromPropertiesFileString(PROPERTY_NAME_PROTOCOL_AND_HOST_EMAIL_SERVICES);
		String emailAddressPrivacyAnalyst = loadPropertyFromPropertiesFileString(PROPERTY_NAME_EMAIL_ADDRESS_PRIVACY_ANALYST);
		WatchdogClient client = new WatchdogClient(protocolAndHostUserDeviceEnforcement, protocolAndHostOspEnforcement, protocolAndHostEmailServices, emailAddressPrivacyAnalyst);
		WatchdogApplication application = new WatchdogApplication(client);
		
		//Which user, which OSP?
		//TODO - in the next prototype this should be a list of all userId-ospId pairs. Perhaps read from the DB and put into a hashmap. 
		int userId = loadPropertyFromPropertiesFileInt(PROPERTY_NAME_USER_ID);
		int ospId = loadPropertyFromPropertiesFileInt(PROPERTY_NAME_OSP_ID);
		
		//Perform a privacy settings check for the given user and osp.
		application.runPrivacySettingsCheck(userId, ospId);		
	}
	
	/**
	 * Returns the value of the property from PROPERTIES_FILE with name equal to propertyName parsed as an int.
	 */
	private static int loadPropertyFromPropertiesFileInt(String propertyName)
	{
		String propertyStr = loadPropertyFromPropertiesFileString(propertyName);
		return Integer.parseInt(propertyStr);
	}
	
	/**
	 * Returns the value of the property from PROPERTIES_FILE with name equal to propertyName.
	 * 
	 * If there is no property with the given name, the returned string is null.
	 */
	private static String loadPropertyFromPropertiesFileString(String propertyName)
	{
		String propertyValue = "";
		
		Properties properties = new Properties();
		InputStream inputStream = null;

		try
		{
			//Load the file into an input stream.
			inputStream = WatchdogLauncher.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
			
			//Load the properties from the file's stream.
			properties.load(inputStream);
			
			//Read the property for the given name.
			propertyValue = properties.getProperty(propertyName);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (inputStream != null)
			{
				try
				{
					inputStream.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return propertyValue;
	}
}
