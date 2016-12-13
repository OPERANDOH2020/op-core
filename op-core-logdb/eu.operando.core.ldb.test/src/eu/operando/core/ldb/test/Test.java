/*******************************************************************************
 *  * Copyright (c) 2016 {TECNALIA}.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the The MIT License (MIT).
 *  * which accompanies this distribution, and is available at
 *  * http://opensource.org/licenses/MIT
 *  *
 *  * Contributors:
 *  *    Gorka Mikel Echevarría {TECNALIA}
 *  * Initially developed in the context of OPERANDO EU project www.operando.eu
 *******************************************************************************/
package eu.operando.core.ldb.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@org.junit.Test
	public void test() {		
		ApiClient apiClient = new ApiClient();
		 
	    byte[] postBinaryBody = null; 
	     
	    // create path and map variables 
	    String path = "/operando/core/ldb/log"; 
	 
	    // query params 
	    List<Pair> queryParams = new ArrayList<Pair>(); 
	    Map<String, String> headerParams = new HashMap<String, String>(); 
	    Map<String, Object> formParams = new HashMap<String, Object>(); 
	 
	    final String[] accepts = { 
	      "application/json", "application/xml" 
	    }; 
	    final String accept = apiClient.selectHeaderAccept(accepts); 
	 
	    final String[] contentTypes = { 
	       
	    }; 
	    final String contentType = apiClient.selectHeaderContentType(contentTypes); 
	 
	    String[] authNames = new String[] {  }; 
	 	     
	    GenericType<String> returnType = new GenericType<String>() {};
		

	    LogRequest logRequest = new LogRequest();
	    logRequest.setUserId("003");
	    logRequest.setDescription("Log on 07/12 for testing purposes");
	    logRequest.setLogDataType(LogDataTypeEnum.INFO);
	    logRequest.setTitle("Log on 07/12");
	    logRequest.setLogPriority(LogPriorityEnum.LOW);
	    logRequest.setRequesterId("1007");
	    logRequest.setRequesterType(RequesterTypeEnum.MODULE);
	    ArrayList<String> keywords = new ArrayList<String> ();
	    keywords.add("keywordA");
	    keywords.add("keywordB");
	    keywords.add("keywordC");
		logRequest.setKeywords(keywords );
		
		Object postBody = logRequest;
		System.out.println(postBody);
		String str = "";
		try {
			str = apiClient.invokeAPI(path,"POST", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);			
		} catch (ApiException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		System.out.println(str);
	}
	
	public static void main(String[] args) throws Exception {                    
	       JUnitCore.main(
	         "eu.operando.core.ldb.test.Test");            
	}

}
