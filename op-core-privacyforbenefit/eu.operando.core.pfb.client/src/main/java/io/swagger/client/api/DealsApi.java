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
import io.swagger.client.model.InlineResponse200;
import io.swagger.client.model.InlineResponse2004;
import io.swagger.client.model.InlineResponse2005;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-03-31T13:37:26.696Z")
public class DealsApi {
  private ApiClient apiClient;

  public DealsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public DealsApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  
  /**
   * Cancel a deal.
   * Request from Privacy Dashboard to cancel a deal. The deal is not deleted, but updated by setting the current date value to canceled_at field.
   * @param dealId The unique identifier number of a deal. (required)
   * @return String
   * @throws ApiException if fails to make API call
   */
  public String cancelDeal(String dealId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'dealId' is set
    if (dealId == null) {
      throw new ApiException(400, "Missing the required parameter 'dealId' when calling cancelDeal");
    }
    
    // create path and map variables
    String localVarPath = "/deals/{deal_id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "deal_id" + "\\}", apiClient.escapeString(dealId.toString()));

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
    return apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * Gets the status of a given deal.
   * Get the status of a given deal.
   * @param dealId The unique identifier number of a deal. (required)
   * @return InlineResponse200
   * @throws ApiException if fails to make API call
   */
  public InlineResponse200 dealsDealIdGet(String dealId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'dealId' is set
    if (dealId == null) {
      throw new ApiException(400, "Missing the required parameter 'dealId' when calling dealsDealIdGet");
    }
    
    // create path and map variables
    String localVarPath = "/deals/{deal_id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "deal_id" + "\\}", apiClient.escapeString(dealId.toString()));

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

    
    GenericType<InlineResponse200> localVarReturnType = new GenericType<InlineResponse200>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
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
   * Returns list of offers accepted by user.
   * Request from privacy dashboard to get list of offers accepted by user (and acknowledged by OSP).
   * @param userId The user identifier number (required)
   * @param status filter deals by category / status (optional)
   * @return InlineResponse2005
   * @throws ApiException if fails to make API call
   */
  public InlineResponse2005 getAcceptedDealsByUser(String userId, String status) throws ApiException {
    Object localVarPostBody = status;
    
    // verify the required parameter 'userId' is set
    if (userId == null) {
      throw new ApiException(400, "Missing the required parameter 'userId' when calling getAcceptedDealsByUser");
    }
    
    // create path and map variables
    String localVarPath = "/users/{user_id}/deals".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "user_id" + "\\}", apiClient.escapeString(userId.toString()));

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

    
    GenericType<InlineResponse2005> localVarReturnType = new GenericType<InlineResponse2005>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * Acknoledge a deal by OSP.
   * This API call is used by the OSP to acknowledge a deal and approve that offer was awarded to user
   * @param dealId The unique identifier number of a deal. (required)
   * @param ospId Osp Id. (required)
   * @return String
   * @throws ApiException if fails to make API call
   */
  public String offerAccepted(String dealId, String ospId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'dealId' is set
    if (dealId == null) {
      throw new ApiException(400, "Missing the required parameter 'dealId' when calling offerAccepted");
    }
    
    // verify the required parameter 'ospId' is set
    if (ospId == null) {
      throw new ApiException(400, "Missing the required parameter 'ospId' when calling offerAccepted");
    }
    
    // create path and map variables
    String localVarPath = "/deals/{deal_id}/acknowledgement".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "deal_id" + "\\}", apiClient.escapeString(dealId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "ospId", ospId));
    

    

    

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    
    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
}
