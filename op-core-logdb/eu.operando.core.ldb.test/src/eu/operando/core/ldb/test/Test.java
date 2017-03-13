/*
   	* Copyright (c) 2017 {TECNALIA}.
    * All rights reserved. This program and the accompanying materials
    * are made available under the terms of the The MIT License (MIT).
    * which accompanies this distribution, and is available at
    * http://opensource.org/licenses/MIT
    *
    * Contributors:
    *    Gorka Benguria Elguezabal {TECNALIA}
    *    Gorka Mikel Echevarría {TECNALIA}
    * Initially developed in the context of OPERANDO EU project www.operando.eu
 */
package eu.operando.core.ldb.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.runner.JUnitCore;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Pair;
import io.swagger.client.model.LogRequest;
import io.swagger.client.model.LogRequest.LogDataTypeEnum;
import io.swagger.client.model.LogRequest.LogPriorityEnum;
import io.swagger.client.model.LogRequest.RequesterTypeEnum;

public class Test {

	// @org.junit.Test
	public void test() {
		// GBE added code to get information form a properties file
		Properties props;
		props = loadProperties();

		String basePath = props.getProperty("basePath");
		System.out.println("basePath:" + basePath);

		ApiClient apiClient = new ApiClient();

		apiClient.setBasePath(basePath);
		byte[] postBinaryBody = null;

		// create path and map variables
		String path = "/operando/core/ldb/log";

		// query params
		List<Pair> queryParams = new ArrayList<Pair>();
		Map<String, String> headerParams = new HashMap<String, String>();
		Map<String, Object> formParams = new HashMap<String, Object>();

		final String[] accepts = { "application/json", "application/xml" };
		final String accept = apiClient.selectHeaderAccept(accepts);

		final String[] contentTypes = {

		};
		final String contentType = apiClient.selectHeaderContentType(contentTypes);

		String[] authNames = new String[] {};

		GenericType<String> returnType = new GenericType<String>() {
		};

		LogRequest logRequest = new LogRequest();
		logRequest.setUserId("001");
		logRequest.setDescription("Log on 07/12 for testing purposes");
		logRequest.setLogDataType(LogDataTypeEnum.INFO);
		logRequest.setTitle("Log on 07/12");
		logRequest.setLogPriority(LogPriorityEnum.LOW);
		logRequest.setRequesterId("1007");
		logRequest.setRequesterType(RequesterTypeEnum.MODULE);
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("keywordA");
		keywords.add("keywordB");
		keywords.add("keywordC");
		logRequest.setKeywords(keywords);

		Object postBody = logRequest;
		System.out.println(postBody);
		String str = "";
		try {
			str = apiClient.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, accept,
					contentType, authNames, returnType);
		} catch (ApiException e) {
			System.out.println(e.toString());
		}
		System.out.println(str);
	}

	@org.junit.Test
	public void testTicket() {
		// GBE added code to get information form a properties file
		Properties props;
		props = loadProperties();

		String basePath = props.getProperty("basePath");
		System.out.println("basePath:" + basePath);

		ApiClient apiClient = new ApiClient();

		apiClient.setBasePath(basePath);

		byte[] postBinaryBody = null;

		// create path and map variables
		String path = "/operando/core/ldb/log/logTicket";

		// query params
		List<Pair> queryParams = new ArrayList<Pair>();
		Map<String, String> headerParams = new HashMap<String, String>();
		Map<String, Object> formParams = new HashMap<String, Object>();

		final String[] accepts = { "application/json", "application/xml" };
		final String accept = apiClient.selectHeaderAccept(accepts);

		final String[] contentTypes = {

		};
		final String contentType = apiClient.selectHeaderContentType(contentTypes);

		String[] authNames = new String[] {};

		GenericType<String> returnType = new GenericType<String>() {
		};

		headerParams.put("service-ticket", "ST-8-WFWRUUFZlCTriXBoA9I5-casdotoperandodoteu");

		LogRequest logRequest = new LogRequest();
		logRequest.setUserId("001");
		logRequest.setDescription("Log on 07/12 for testing purposes");
		logRequest.setLogDataType(LogDataTypeEnum.INFO);
		logRequest.setTitle("Log on 07/12");
		logRequest.setLogPriority(LogPriorityEnum.LOW);
		logRequest.setRequesterId("1007");
		logRequest.setRequesterType(RequesterTypeEnum.MODULE);
		ArrayList<String> keywords = new ArrayList<String>();
		keywords.add("keywordA");
		keywords.add("keywordB");
		keywords.add("keywordC");
		logRequest.setKeywords(keywords);

		Object postBody = logRequest;

		String str = "";
		try {
			str = apiClient.invokeAPI(path, "POST", queryParams, postBody, headerParams, formParams, accept,
					contentType, authNames, returnType);
		} catch (ApiException e) {
			System.out.println(e.toString());
		}
		System.out.println(str);
	}

	public static void main(String[] args) throws Exception {
		JUnitCore.main("eu.operando.core.ldb.test.Test");
	}

	private Properties loadProperties() {
		Properties props;
		props = new Properties();

		InputStream fis = null;
		try {
			fis = this.getClass().getClassLoader().getResourceAsStream("/test.properties");
			props.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return props;
	}

}
