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
package eu.operando.core.ldbsearch.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.JUnitCore;

import com.google.gson.Gson;
import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Pair;


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
	    String path = "/operando/core/ldbsearch/log/search"; 
	 
	    // query params
	    ArrayList<String> keyWords = new ArrayList<String>();
	    keyWords.add("keyword1");
	    String jsonKeyWords = new Gson().toJson(keyWords);
	    List<Pair> queryParams = new ArrayList<Pair>();
	    queryParams.add(new Pair ("dateFrom","2016-07-04 08:50:25"));
	    queryParams.add(new Pair ("dateTo","2016-07-10 08:50:25"));
	    queryParams.add(new Pair ("logLevel",""));
	    queryParams.add(new Pair ("requesterType",""));
	    queryParams.add(new Pair ("requesterId",""));
	    queryParams.add(new Pair ("website_id",""));
	    queryParams.add(new Pair ("logPriority",""));
	    queryParams.add(new Pair ("title",""));
	    queryParams.add(new Pair ("keyWords",jsonKeyWords));
	    
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
	 	    
	    GenericType returnType = new GenericType<String>() {};				
		
		Object postBody = null;
		String str = "";
		try {
			str = apiClient.invokeAPI(path,"GET", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);			
		} catch (ApiException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		System.out.println(str);
	}


}
