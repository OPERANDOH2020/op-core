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

import io.swagger.client.model.InlineResponse200;
import io.swagger.client.model.Error;
import io.swagger.client.model.AccessLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-03-11T13:10:20.376Z")
public class AccessLevelsApi {
  private ApiClient apiClient;

  public AccessLevelsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public AccessLevelsApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  
  /**
   * Creates a new access level.
   * Creates a new access level.
   * @param accessLevel The Access Level data in JSON format.
   * @return InlineResponse200
   * @throws ApiException if fails to make API call
   */
  public InlineResponse200 createAccessLevel(AccessLevel accessLevel) throws ApiException {
    Object localVarPostBody = accessLevel;
    
    // verify the required parameter 'accessLevel' is set
    if (accessLevel == null) {
      throw new ApiException(400, "Missing the required parameter 'accessLevel' when calling createAccessLevel");
    }
    
    // create path and map variables
    String localVarPath = "/accessLevel".replaceAll("\\{format\\}","json");

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

    
    GenericType<InlineResponse200> localVarReturnType = new GenericType<InlineResponse200>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * Deletes an existing access level.
   * Deletes an existing access level.
   * @param accessLevelId The access level identifier number
   * @param accessLevelId2 ID of the access level that needs to be deleted
   * @throws ApiException if fails to make API call
   */
  public void deleteAccessLevel(String accessLevelId, String accessLevelId2) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'accessLevelId' is set
    if (accessLevelId == null) {
      throw new ApiException(400, "Missing the required parameter 'accessLevelId' when calling deleteAccessLevel");
    }
    
    // verify the required parameter 'accessLevelId2' is set
    if (accessLevelId2 == null) {
      throw new ApiException(400, "Missing the required parameter 'accessLevelId2' when calling deleteAccessLevel");
    }
    
    // create path and map variables
    String localVarPath = "/accessLevel/{accessLevel-id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "accessLevel-id" + "\\}", apiClient.escapeString(accessLevelId.toString()))
      .replaceAll("\\{" + "accessLevel-id" + "\\}", apiClient.escapeString(accessLevelId2.toString()));

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

    
    apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
    
  }
  
  /**
   * Finds a access level by ID.
   * Finds a access level by ID.
   * @param accessLevelId The access level identifier number
   * @param accessLevelId2 ID of the access level that needs to be fetched.
   * @throws ApiException if fails to make API call
   */
  public void getAccessLevel(String accessLevelId, String accessLevelId2) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'accessLevelId' is set
    if (accessLevelId == null) {
      throw new ApiException(400, "Missing the required parameter 'accessLevelId' when calling getAccessLevel");
    }
    
    // verify the required parameter 'accessLevelId2' is set
    if (accessLevelId2 == null) {
      throw new ApiException(400, "Missing the required parameter 'accessLevelId2' when calling getAccessLevel");
    }
    
    // create path and map variables
    String localVarPath = "/accessLevel/{accessLevel-id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "accessLevel-id" + "\\}", apiClient.escapeString(accessLevelId.toString()))
      .replaceAll("\\{" + "accessLevel-id" + "\\}", apiClient.escapeString(accessLevelId2.toString()));

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

    
    apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
    
  }
  
  /**
   * Gets existing access levels.
   * Gets existing access levels.
   * @return List<AccessLevel>
   * @throws ApiException if fails to make API call
   */
  public List<AccessLevel> getAccessLevels() throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/accessLevel".replaceAll("\\{format\\}","json");

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

    
    GenericType<List<AccessLevel>> localVarReturnType = new GenericType<List<AccessLevel>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * Updates an existing access level.
   * Updates an existing access level.
   * @param accessLevelId The access level identifier number
   * @param accessLevel The Access Level data in JSON format.
   * @return InlineResponse200
   * @throws ApiException if fails to make API call
   */
  public InlineResponse200 updateAccessLevel(String accessLevelId, AccessLevel accessLevel) throws ApiException {
    Object localVarPostBody = accessLevel;
    
    // verify the required parameter 'accessLevelId' is set
    if (accessLevelId == null) {
      throw new ApiException(400, "Missing the required parameter 'accessLevelId' when calling updateAccessLevel");
    }
    
    // verify the required parameter 'accessLevel' is set
    if (accessLevel == null) {
      throw new ApiException(400, "Missing the required parameter 'accessLevel' when calling updateAccessLevel");
    }
    
    // create path and map variables
    String localVarPath = "/accessLevel/{accessLevel-id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "accessLevel-id" + "\\}", apiClient.escapeString(accessLevelId.toString()));

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

    
    GenericType<InlineResponse200> localVarReturnType = new GenericType<InlineResponse200>() {};
    return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
}
