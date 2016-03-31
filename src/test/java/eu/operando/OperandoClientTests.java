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
package eu.operando;

public interface OperandoClientTests
{
	public static final String PREFIX_HTTP = "http://";
	public static final int PORT_WIREMOCK = 8089;
	public static final String HOST_WIREMOCK = "localhost:" + PORT_WIREMOCK;
	public static final String PROOTOCOL_AND_HOST = PREFIX_HTTP + HOST_WIREMOCK;
	
	public static final String PATH_DATA_ACCESS_NODE = "/operando/pdr/data_access_node";
	
	public static final String PATH_REPORT_GENERATOR = "/operando/interfaces/report_generator";
	public static final String PATH_USER_DEVICE_ENFORCEMENT = "/operando/core/ude";
	public static final String PATH_EMAIL_SERVICES = "/operando/interfaces/email_services";
	
	public static final String PATH_AUTHENTICATION_SERVICE = "/operando/core/authentication_service";
	public static final String PATH_RIGHTS_MANAGEMENT = "/operando/core/rights_management";
	public static final String PATH_POLICY_COMPUTATION = "/operando/core/policy_computation";
	public static final String PATH_OSP_ENFORCEMENT = "/operando/core/osp_enforcement";
	public static final String PATH_LOG_DB = "/operando/core/log_db";
	
	public static final String URL_AUTHENTICATION_SERVICE = PROOTOCOL_AND_HOST + PATH_AUTHENTICATION_SERVICE;
	public static final String URL_RIGHTS_MANAGEMENT = PROOTOCOL_AND_HOST + PATH_RIGHTS_MANAGEMENT;
	public static final String URL_DATA_ACCESS_NODE = PROOTOCOL_AND_HOST + PATH_DATA_ACCESS_NODE;
	public static final String URL_LOG_DB = PROOTOCOL_AND_HOST + PATH_LOG_DB;
	public static final String URL_REPORT_GENERATOR = PROOTOCOL_AND_HOST + PATH_REPORT_GENERATOR;
	public static final String URL_POLICY_COMPUTATION = PROOTOCOL_AND_HOST + PATH_POLICY_COMPUTATION;
	public static final String URL_OSP_ENFORCEMENT = PROOTOCOL_AND_HOST + PATH_OSP_ENFORCEMENT;
	public static final String URL_USER_DEVICE_ENFORCEMENT = PROOTOCOL_AND_HOST + PATH_USER_DEVICE_ENFORCEMENT;
	public static final String URL_EMAIL_SERVICES = PROOTOCOL_AND_HOST + PATH_EMAIL_SERVICES;
	
	public static final String ENDPOINT_POLICY_COMPUTATION_REGULATIONS = PATH_POLICY_COMPUTATION + "/regulations";
	public static final String ENDPOINT_OSP_ENFORCEMENT_REGULATIONS = PATH_OSP_ENFORCEMENT + "/regulations";
	public static final String ENDPOINT_OSP_ENFORCEMENT_PRIVACY_SETTINGS = PATH_OSP_ENFORCEMENT + "/osps/%d/privacy_settings";
	public static final String ENDPOINT_USER_DEVICE_ENFORCEMENT_PRIVACY_SETTINGS = PATH_USER_DEVICE_ENFORCEMENT + "/privacy_settings";
	public static final String ENDPOINT_EMAIL_SERVICES_EMAIL = PATH_EMAIL_SERVICES + "/email";
}
