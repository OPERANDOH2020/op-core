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
package eu.operando.core.pfb.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Pair;
import io.swagger.client.model.Deal;
import io.swagger.client.model.Offer;


public class Test {

	//@org.junit.Test
	public void createOffer() {		
		ApiClient apiClient = new ApiClient();
		 
	    byte[] postBinaryBody = null; 
	     
	    // create path and map variables 
	    String path = "/offers"; 
	 
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
	 
	    //TypeRef returnType = new TypeRef<DataUnit>() {}; 
	    GenericType returnType = new GenericType<String>() {};
		

		Offer offer = new Offer();
		offer.setId("-1");
		offer.setDescription("New offer");
		offer.setExpirationDate(null);
		offer.setIsEnabled(new Boolean(true));		
		offer.setOspCallbackUrl("www.operando.pfb.eu");
		offer.setOspId("1");
		offer.setTitle("New Offer");
		offer.setServiceWebsite("www.operando.pfb.eu");
		
		Object postBody = offer;
		try {
			String str = apiClient.invokeAPI(path,"POST", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);			
		} catch (ApiException e) {			
			e.printStackTrace();
		}			
	}
	
	@org.junit.Test
	public void createDeal() {		
		ApiClient apiClient = new ApiClient();
		 
	    byte[] postBinaryBody = null; 
	     
	    // create path and map variables 
	    String path = "/deals"; 
	 
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
	 
	    //TypeRef returnType = new TypeRef<DataUnit>() {}; 
	    GenericType returnType = new GenericType<String>() {};
		

		Deal deal = new Deal();
		deal.setOfferId("1");
		deal.setUserId("1");
				
		Object postBody = deal;
		try {
			String str = apiClient.invokeAPI(path,"POST", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);			
		} catch (ApiException e) {			
			e.printStackTrace();
		}
				
	}
	
	//@org.junit.Test
	public void getOffers() {		
		ApiClient apiClient = new ApiClient();
		 
	    byte[] postBinaryBody = null; 
	     
	    // create path and map variables 
	    String path = "/offers/search"; 
	 
	    // query params 
	    List<Pair> queryParams = new ArrayList<Pair>();
	    queryParams.add(new Pair ("website_url","website_url"));
	    queryParams.add(new Pair ("website_id","website_id"));
	    queryParams.add(new Pair ("osp_id","osp_id"));
	    queryParams.add(new Pair ("user_id","user_id"));
	    
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
		} catch (ApiException e) {			
			e.printStackTrace();
		}
		
		//String str = client.invokeAPI("swagger.json", "GET", new HashMap<String, String>(), null, new HashMap<String, String>(), null, "application/json", null, new String[0]);
	}
	
	@org.junit.Test
	public void getDeals() {		
		ApiClient apiClient = new ApiClient();
		 
	    byte[] postBinaryBody = null; 
	     
	    // create path and map variables 
	    String path = "/deals/search"; 
	 
	    // query params 
	    List<Pair> queryParams = new ArrayList<Pair>();
	    queryParams.add(new Pair ("offer_id","1"));
	    queryParams.add(new Pair ("user_id","1"));
	    queryParams.add(new Pair ("created_from","2016-06-04 08:50:25"));
	    queryParams.add(new Pair ("created_to","2016-06-09 20:50:25"));
	    queryParams.add(new Pair ("canceled","false"));
	    
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
		} catch (ApiException e) {			
			e.printStackTrace();
		}
		
		//String str = client.invokeAPI("swagger.json", "GET", new HashMap<String, String>(), null, new HashMap<String, String>(), null, "application/json", null, new String[0]);
	}


}
