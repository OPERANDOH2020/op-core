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
package eu.operando.core.ldbsearch.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.JUnitCore;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Pair;
import io.swagger.client.model.LogResponse.LogPriorityEnum;
import io.swagger.client.model.LogResponse.RequesterTypeEnum;


public class Test {
	
	public static void main(String[] args) throws Exception {                    
	       JUnitCore.main(
	         "eu.operando.core.ldbsearch.test.Test");            
	}
	
	@org.junit.Test
	public void getLogs() {		
		ApiClient apiClient = new ApiClient();
		 
	    byte[] postBinaryBody = null; 
	     
	    // create path and map variables 
	    String path = "/log/search"; 
	 
	    // query params 
	    List<Pair> queryParams = new ArrayList<Pair>();
	    queryParams.add(new Pair ("dateFrom","2016-06-04 08:50:25,170"));
	    queryParams.add(new Pair ("dateTo",""));
	    queryParams.add(new Pair ("logLevel",""));
	    queryParams.add(new Pair ("requesterType",""));
	    queryParams.add(new Pair ("requesterId",""));
	    queryParams.add(new Pair ("website_id",""));
	    queryParams.add(new Pair ("logPriority",""));
	    queryParams.add(new Pair ("title",""));
	    queryParams.add(new Pair ("keyWords",null));
	    
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
	 
	    //TypeRef returnType = new TypeRef<DataUnit>() {}; 
	    GenericType returnType = new GenericType<String>() {};
		
		
		
		Object postBody = null;
		try {
			String str = apiClient.invokeAPI(path,"GET", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
			System.out.println(str);
		} catch (ApiException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
		//String str = client.invokeAPI("swagger.json", "GET", new HashMap<String, String>(), null, new HashMap<String, String>(), null, "application/json", null, new String[0]);
	}


}
