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
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.junit.runner.JUnitCore;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
	    //String path = "/operando/core/ldbsearch/log/search"; 
	    String path = "/operando/core/ldbsearch/log/searchExt";
	 
	    // query params
	    ArrayList<String> keyWords = new ArrayList<String>();
	    keyWords.add("keyword1");
	    String jsonKeyWords = new Gson().toJson(keyWords);
	    List<Pair> queryParams = new ArrayList<Pair>();
	    queryParams.add(new Pair ("dateFrom","2016-03-22 08:50:25"));
	    queryParams.add(new Pair ("dateTo","2017-12-30 08:50:25"));
	    queryParams.add(new Pair ("logLevel",""));
	    queryParams.add(new Pair ("requesterType",""));
	    queryParams.add(new Pair ("requesterId",""));
	    queryParams.add(new Pair ("website_id",""));
	    queryParams.add(new Pair ("logPriority",""));
	    queryParams.add(new Pair ("title",""));
	    queryParams.add(new Pair ("logType",""));
	    queryParams.add(new Pair ("keyWords",""));
	    queryParams.add(new Pair ("affectedUserId",""));
	    queryParams.add(new Pair ("ospId","3"));
	    
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
	//@org.junit.Test
	public void parseRequestedFields (){
		String strToParse = "[\"string\",\"dog\",\"cat\"]";
		//String[] requestedFields = new String[];
		ArrayList<String> arrayRequestedFields = new ArrayList<String>();
		if (strToParse != null) {				
			if (strToParse.length() > 0) {
				Gson gson = new Gson();
				TypeToken<ArrayList<String>> token = new TypeToken<ArrayList<String>>() {
				};
				arrayRequestedFields = gson.fromJson(strToParse, token.getType());
				System.out.println(arrayRequestedFields);
				System.out.println(removeNullValue(arrayRequestedFields));
				
			}
		}
		//System.out.println(strToParse);
	}
	
	
		  public static ArrayList<String> removeNullValue( ArrayList<String> arrayRequestedFields ) {
		    ArrayList<String> returnArray = new ArrayList<String>();

		    Iterator<String> iterator = arrayRequestedFields.iterator();
		    while (iterator.hasNext()){
		    	String next = iterator.next();
		    	if (next!=null)
		    		returnArray.add(next);
		    }

		    
		    return(returnArray);
		  }
		
}
