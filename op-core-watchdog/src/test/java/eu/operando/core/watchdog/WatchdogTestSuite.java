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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import eu.operando.moduleclients.ClientEmailServicesTests;
import eu.operando.moduleclients.ClientOspEnforcementTests;
import eu.operando.moduleclients.ClientUserDeviceEnforcementTests;

@RunWith(Suite.class)
@Suite.SuiteClasses
({
	WatchdogApplicationTests.class,
	ClientUserDeviceEnforcementTests.class,
	ClientOspEnforcementTests.class,
	ClientEmailServicesTests.class
})
public class WatchdogTestSuite
{

}
