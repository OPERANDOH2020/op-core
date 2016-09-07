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

import eu.operando.Utils;

/**
 * The class representing the application which will actually be run.
 */
public class WatchdogLauncher
{
	private static final String PROPERTIES_FILE = "config.properties";
	
	private static final String PROPERTY_NAME_USER_ID = "userId";
	private static final String PROPERTY_NAME_OSP_ID = "ospId";

	public static void main(String[] args)
	{
		WatchdogApplication application = new WatchdogApplication();
		
		//Which user, which OSP?
		//TODO - in the next prototype this should be a list of all userId-ospId pairs. Perhaps read from the DB and put into a hashmap. 
		int userId = Utils.loadPropertyInt(PROPERTIES_FILE, PROPERTY_NAME_USER_ID);
		int ospId = Utils.loadPropertyInt(PROPERTIES_FILE, PROPERTY_NAME_OSP_ID);
		
		//Perform a privacy settings check for the given user and osp.
		application.runPrivacySettingsCheck(userId, ospId);		
	}
}
