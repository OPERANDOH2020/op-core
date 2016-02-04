/*
 * Copyright (c) 2016 {TECNALIA}.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the The MIT License (MIT).
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *
 * Contributors:
 *    {Gorka Mikel Echevarría} ({TECNALIA}
 * Initially developed in the context of OPERANDO EU project www.operando.eu
 */

package eu.operando.server.logdb;

import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.apache.log4j.PropertyConfigurator;

import com.google.gson.Gson;

public class OperandoLog {
	
	static Logger log = Logger.getLogger(OperandoLog.class.getName());

	/** Inserts received data to the database by using Log4j
	 * @param requestComponentId
	 * @param requestId
	 * @param iPAddress
	 * @param mACAddress
	 * @param proxyId
	 * @param requesterId
	 * @param requestedURL
	 * @param postedData
	 * @param accessGranted
	 * @param navigatorUserAgent
	 * @param isMobile
	 * @param mobileType
	 * @param hashLogRM
	 * @param hashLogDB
	 */
	public static void logToDB(String requestComponentId, String requestId, String iPAddress, String mACAddress,
			String proxyId, String requesterId, String requestedURL, String postedData, boolean accessGranted,
			String navigatorUserAgent, boolean isMobile, String mobileType, String hashLogRM, String hashLogDB) {
	
	  //PropertyConfigurator.configure("config\\lo4jMySql.properties");
	  PropertyConfigurator.configure("config\\lo4jDerby.properties");
	  Gson gson = new Gson();
	  MDC.put("username", "username");
	  Request request = new Request ();
	  request.setRequestComponentId(requestComponentId);
	  request.setRequestId(requestId);
	  request.setiPAddress(iPAddress);
	  request.setmACAddress(mACAddress);
	  request.setProxyId(proxyId);
	  request.setRequesterId(requesterId);
	  request.setRequestedURL(requestedURL);
	  request.setPostedData(postedData);
	  request.setAccessGranted(accessGranted);
	  request.setNavigatorUserAgent(navigatorUserAgent);
	  request.setIsMobile(isMobile);
	  request.setMobileType(mobileType);
	  request.setHashLogRM(hashLogRM);
	  request.setHashLogDB(hashLogDB);
	  String requestDataInJsonFormat = gson.toJson(request);      
      log.info(requestDataInJsonFormat);		
	}
}
