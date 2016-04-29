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
package io.swagger.client.api;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;

import io.swagger.client.model.Error;
import io.swagger.client.model.InlineResponse2004;
import io.swagger.client.model.OSP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-03-31T13:37:26.696Z")
public class OSPsApi {
  private ApiClient apiClient;

  public OSPsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public OSPsApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  
  /**
   * List of all users and their active deals for the OSP.
   * Request from an OSP to get a list of all users and their active deals for specific OSP (could be also for report) .
   * @param ospId The identifier number of an OSP (required)
   * @return InlineResponse2004
   * @throws ApiException if fails to make API call
   */
  public InlineResponse2004 getAcceptedDealsByOSP(String ospId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'ospId' is set
    if (ospId == null) {
      throw new ApiException(400, "Missing the required parameter 'ospId' when calling getAcceptedDealsByOSP");
    }
    
    // create path and map variables
    String localVarPath = "/osps/{osp_id}/deals".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "osp_id" + "\\}", apiClient.escapeString(ospId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    

    

    

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    
    GenericType<InlineResponse2004> localVarReturnType = new GenericType<InlineResponse2004>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * Registers a new OSP.
   * Request from Administration Console to register a new Online Service Provider.
   * @param osp The OSP data in JSON format. (required)
   * @return String
   * @throws ApiException if fails to make API call
   */
  public String registerOSP(OSP osp) throws ApiException {
    Object localVarPostBody = osp;
    
    // verify the required parameter 'osp' is set
    if (osp == null) {
      throw new ApiException(400, "Missing the required parameter 'osp' when calling registerOSP");
    }
    
    // create path and map variables
    String localVarPath = "/osps".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    

    

    

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    
    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
}
