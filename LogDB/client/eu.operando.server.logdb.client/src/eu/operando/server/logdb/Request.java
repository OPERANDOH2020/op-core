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

public class Request {	
	private String requestComponentId;
	private String requestId;
	private String iPAddress;
	private String mACAddress;
	private String proxyId;
	private String requesterId;
	private String requestedURL;
	private String postedData;
	private boolean accessGranted;
	private String navigatorUserAgent;	
	private boolean isMobile;
	private String mobileType;
	private String hashLogRM;
	private String hashLogDB;
	public String getRequestComponentId() {
		return requestComponentId;
	}
	public void setRequestComponentId(String requestComponentId) {
		this.requestComponentId = requestComponentId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getiPAddress() {
		return iPAddress;
	}
	public void setiPAddress(String iPAddress) {
		this.iPAddress = iPAddress;
	}
	public String getmACAddress() {
		return mACAddress;
	}
	public void setmACAddress(String mACAddress) {
		this.mACAddress = mACAddress;
	}
	public String getProxyId() {
		return proxyId;
	}
	public void setProxyId(String proxyId) {
		this.proxyId = proxyId;
	}
	public String getRequesterId() {
		return requesterId;
	}
	public void setRequesterId(String requesterId) {
		this.requesterId = requesterId;
	}
	public String getRequestedURL() {
		return requestedURL;
	}
	public void setRequestedURL(String requestedURL) {
		this.requestedURL = requestedURL;
	}
	public String getPostedData() {
		return postedData;
	}
	public void setPostedData(String postedData) {
		this.postedData = postedData;
	}
	public boolean isAccessGranted() {
		return accessGranted;
	}
	public void setAccessGranted(boolean accessGranted) {
		this.accessGranted = accessGranted;
	}	
	public String getNavigatorUserAgent() {
		return navigatorUserAgent;
	}
	public void setNavigatorUserAgent(String navigatorUserAgent) {
		this.navigatorUserAgent = navigatorUserAgent;
	}
	public boolean isMobile() {
		return isMobile;
	}
	public void setIsMobile(boolean isMobile) {
		this.isMobile = isMobile;
	}	
	public String getMobileType() {
		return mobileType;
	}
	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}
	public String getHashLogRM() {
		return hashLogRM;
	}
	public void setHashLogRM(String hashLogRM) {
		this.hashLogRM = hashLogRM;
	}
	public String getHashLogDB() {
		return hashLogDB;
	}
	public void setHashLogDB(String hashLogDB) {
		this.hashLogDB = hashLogDB;
	}
	
	
}
