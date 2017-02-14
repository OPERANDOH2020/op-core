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
package io.swagger.client.api;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;

import io.swagger.client.model.Error;
import io.swagger.client.model.AccessLevel;
import io.swagger.client.model.InlineResponse2001;
import io.swagger.client.model.DataUnitRequest;
import io.swagger.client.model.InlineResponse2002;
import io.swagger.client.model.InlineResponse200;
import io.swagger.client.model.DataUnit;
import io.swagger.client.model.InlineResponse2003;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-05-10T09:36:21.749Z")
public class DataUnitsApi {
  private ApiClient apiClient;

  public DataUnitsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public DataUnitsApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  
  /**
   * Sets the granted access level for a data unit related to a person and for a specific requester.
   * Sets the granted access level for a data unit related to a person and for a specific requester.
   * @param dataunitId The dataunit identifier number (required)
   * @param personId The person identifier number (required)
   * @param requesterId The requester identifier number (required)
   * @param accessLevel The access level data in JSON format. (required)
   * @throws ApiException if fails to make API call
   */
  public void addAccessLevelsByDataUnitByPersonAndRequester(String dataunitId, String personId, String requesterId, AccessLevel accessLevel) throws ApiException {
    Object localVarPostBody = accessLevel;
    
    // verify the required parameter 'dataunitId' is set
    if (dataunitId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataunitId' when calling addAccessLevelsByDataUnitByPersonAndRequester");
    }
    
    // verify the required parameter 'personId' is set
    if (personId == null) {
      throw new ApiException(400, "Missing the required parameter 'personId' when calling addAccessLevelsByDataUnitByPersonAndRequester");
    }
    
    // verify the required parameter 'requesterId' is set
    if (requesterId == null) {
      throw new ApiException(400, "Missing the required parameter 'requesterId' when calling addAccessLevelsByDataUnitByPersonAndRequester");
    }
    
    // verify the required parameter 'accessLevel' is set
    if (accessLevel == null) {
      throw new ApiException(400, "Missing the required parameter 'accessLevel' when calling addAccessLevelsByDataUnitByPersonAndRequester");
    }
    
    // create path and map variables
    String localVarPath = "/dataUnit/{dataunit_id}/person/{person_id}/requester/{requester_id}/accessLevel".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dataunit_id" + "\\}", apiClient.escapeString(dataunitId.toString()))
      .replaceAll("\\{" + "person_id" + "\\}", apiClient.escapeString(personId.toString()))
      .replaceAll("\\{" + "requester_id" + "\\}", apiClient.escapeString(requesterId.toString()));

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

    
    apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
    
  }
  
  /**
   * Creates a new data unit.
   * Creates a new data unit.
   * @param dataUnit The Data Unit data in JSON format. (required)
   * @return InlineResponse2001
   * @throws ApiException if fails to make API call
   */
  public InlineResponse2001 createDataUnit(DataUnitRequest dataUnit) throws ApiException {
    Object localVarPostBody = dataUnit;
    
    // verify the required parameter 'dataUnit' is set
    if (dataUnit == null) {
      throw new ApiException(400, "Missing the required parameter 'dataUnit' when calling createDataUnit");
    }
    
    // create path and map variables
    String localVarPath = "/dataUnit".replaceAll("\\{format\\}","json");

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

    
    GenericType<InlineResponse2001> localVarReturnType = new GenericType<InlineResponse2001>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * Deletes an existing data unit.
   * Deletes an existing data unit.
   * @param dataunitId The dataunit identifier number (required)
   * @param dataunitId2 ID of the data unit that needs to be deleted (required)
   * @throws ApiException if fails to make API call
   */
  public void deleteDataUnit(String dataunitId, String dataunitId2) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'dataunitId' is set
    if (dataunitId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataunitId' when calling deleteDataUnit");
    }
    
    // verify the required parameter 'dataunitId2' is set
    if (dataunitId2 == null) {
      throw new ApiException(400, "Missing the required parameter 'dataunitId2' when calling deleteDataUnit");
    }
    
    // create path and map variables
    String localVarPath = "/dataUnit/{dataunit_id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dataunit_id" + "\\}", apiClient.escapeString(dataunitId.toString()))
      .replaceAll("\\{" + "dataunit_id" + "\\}", apiClient.escapeString(dataunitId2.toString()));

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
   * Gets the available access levels for a specific data unit.
   * Gets the available access levels for a specific data unit.
   * @param dataunitId The dataunit identifier number (required)
   * @return InlineResponse2002
   * @throws ApiException if fails to make API call
   */
  public InlineResponse2002 getAccessLevelsByDataUnit(String dataunitId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'dataunitId' is set
    if (dataunitId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataunitId' when calling getAccessLevelsByDataUnit");
    }
    
    // create path and map variables
    String localVarPath = "/dataUnit/{dataunit_id}/accessLevels/search".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dataunit_id" + "\\}", apiClient.escapeString(dataunitId.toString()));

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

    
    GenericType<InlineResponse2002> localVarReturnType = new GenericType<InlineResponse2002>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * Gets the granted access levels for a specific data unit according to countries laws.
   * Gets the granted access levels for a specific data unit according to countries laws.
   * @param dataunitId The dataunit identifier number (required)
   * @param countryId The country identifier number (required)
   * @return InlineResponse2002
   * @throws ApiException if fails to make API call
   */
  public InlineResponse2002 getAccessLevelsByDataUnitAndCountry(String dataunitId, String countryId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'dataunitId' is set
    if (dataunitId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataunitId' when calling getAccessLevelsByDataUnitAndCountry");
    }
    
    // verify the required parameter 'countryId' is set
    if (countryId == null) {
      throw new ApiException(400, "Missing the required parameter 'countryId' when calling getAccessLevelsByDataUnitAndCountry");
    }
    
    // create path and map variables
    String localVarPath = "/dataUnit/{dataunit_id}/country/{country_id}/accessLevels/search".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dataunit_id" + "\\}", apiClient.escapeString(dataunitId.toString()))
      .replaceAll("\\{" + "country_id" + "\\}", apiClient.escapeString(countryId.toString()));

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

    
    GenericType<InlineResponse2002> localVarReturnType = new GenericType<InlineResponse2002>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * Gets the granted access levels for a specific data unit according the privacy authority.
   * Gets the granted access levels for a specific data unit according the privacy authority.
   * @param dataunitId The dataunit identifier number (required)
   * @return InlineResponse2002
   * @throws ApiException if fails to make API call
   */
  public InlineResponse2002 getAccessLevelsByDataUnitAndPA(String dataunitId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'dataunitId' is set
    if (dataunitId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataunitId' when calling getAccessLevelsByDataUnitAndPA");
    }
    
    // create path and map variables
    String localVarPath = "/dataUnit/{dataunit_id}/pa/accessLevels/search".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dataunit_id" + "\\}", apiClient.escapeString(dataunitId.toString()));

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

    
    GenericType<InlineResponse2002> localVarReturnType = new GenericType<InlineResponse2002>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * Gets the granted access level for a data unit related to a person and for a specific requester.
   * Gets the granted access level for a data unit related to a person and for a specific requester.
   * @param dataunitId The dataunit identifier number (required)
   * @param personId The person identifier number (required)
   * @param requesterId The requester identifier number (required)
   * @return InlineResponse200
   * @throws ApiException if fails to make API call
   */
  public InlineResponse200 getAccessLevelsByDataUnitPersonAndRequester(String dataunitId, String personId, String requesterId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'dataunitId' is set
    if (dataunitId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataunitId' when calling getAccessLevelsByDataUnitPersonAndRequester");
    }
    
    // verify the required parameter 'personId' is set
    if (personId == null) {
      throw new ApiException(400, "Missing the required parameter 'personId' when calling getAccessLevelsByDataUnitPersonAndRequester");
    }
    
    // verify the required parameter 'requesterId' is set
    if (requesterId == null) {
      throw new ApiException(400, "Missing the required parameter 'requesterId' when calling getAccessLevelsByDataUnitPersonAndRequester");
    }
    
    // create path and map variables
    String localVarPath = "/dataUnit/{dataunit_id}/person/{person_id}/requester/{requester_id}/accessLevel/search".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dataunit_id" + "\\}", apiClient.escapeString(dataunitId.toString()))
      .replaceAll("\\{" + "person_id" + "\\}", apiClient.escapeString(personId.toString()))
      .replaceAll("\\{" + "requester_id" + "\\}", apiClient.escapeString(requesterId.toString()));

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
   * Finds a data unit by ID.
   * Finds a data unit by ID.
   * @param dataunitId The dataunit identifier number (required)
   * @param dataunitId2 ID of the data unit that needs to be fetched. (required)
   * @throws ApiException if fails to make API call
   */
  public void getDataUnit(String dataunitId, String dataunitId2) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'dataunitId' is set
    if (dataunitId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataunitId' when calling getDataUnit");
    }
    
    // verify the required parameter 'dataunitId2' is set
    if (dataunitId2 == null) {
      throw new ApiException(400, "Missing the required parameter 'dataunitId2' when calling getDataUnit");
    }
    
    // create path and map variables
    String localVarPath = "/dataUnit/{dataunit_id}/search".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dataunit_id" + "\\}", apiClient.escapeString(dataunitId.toString()))
      .replaceAll("\\{" + "dataunit_id" + "\\}", apiClient.escapeString(dataunitId2.toString()));

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
   * Gets existing data units.
   * Gets existing data units.
   * @return List<DataUnit>
   * @throws ApiException if fails to make API call
   */
  public List<DataUnit> getDataUnits() throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/dataUnit/search".replaceAll("\\{format\\}","json");

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

    
    GenericType<List<DataUnit>> localVarReturnType = new GenericType<List<DataUnit>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * Gets individual or colective personal data by receiving as parameter a query especifiyng the data wanted to be recovered and the requester id.
   * Gets individual or colective personal data by receiving as parameter a query especifiyng the data wanted to be recovered and the requester id.
   * @param requesterId The requester identifier number (required)
   * @param query query encapsulating the set of data units and the personal ids of the people whose personal data is wanted to be received. (required)
   * @return InlineResponse2003
   * @throws ApiException if fails to make API call
   */
  public InlineResponse2003 getPersonalData(String requesterId, String query) throws ApiException {
    Object localVarPostBody = query;
    
    // verify the required parameter 'requesterId' is set
    if (requesterId == null) {
      throw new ApiException(400, "Missing the required parameter 'requesterId' when calling getPersonalData");
    }
    
    // verify the required parameter 'query' is set
    if (query == null) {
      throw new ApiException(400, "Missing the required parameter 'query' when calling getPersonalData");
    }
    
    // create path and map variables
    String localVarPath = "/personaldata/{requester_id}/search".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "requester_id" + "\\}", apiClient.escapeString(requesterId.toString()));

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

    
    GenericType<InlineResponse2003> localVarReturnType = new GenericType<InlineResponse2003>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * Sets the available access levels for a specific data unit.
   * Sets the available access levels for a specific data unit.
   * @param dataunitId The dataunit identifier number (required)
   * @param accesslevels List of Access Levels. (required)
   * @throws ApiException if fails to make API call
   */
  public void setAccessLevelsByDataUnit(String dataunitId, List<AccessLevel> accesslevels) throws ApiException {
    Object localVarPostBody = accesslevels;
    
    // verify the required parameter 'dataunitId' is set
    if (dataunitId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataunitId' when calling setAccessLevelsByDataUnit");
    }
    
    // verify the required parameter 'accesslevels' is set
    if (accesslevels == null) {
      throw new ApiException(400, "Missing the required parameter 'accesslevels' when calling setAccessLevelsByDataUnit");
    }
    
    // create path and map variables
    String localVarPath = "/dataUnit/{dataunit_id}/accessLevels".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dataunit_id" + "\\}", apiClient.escapeString(dataunitId.toString()));

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

    
    apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
    
  }
  
  /**
   * Updates the available access levels for a specific data unit.
   * Updates the available access levels for a specific data unit.
   * @param dataunitId The dataunit identifier number (required)
   * @param accesslevels List of Access Levels. (required)
   * @throws ApiException if fails to make API call
   */
  public void updateAccessLevelsByDataUnit(String dataunitId, List<AccessLevel> accesslevels) throws ApiException {
    Object localVarPostBody = accesslevels;
    
    // verify the required parameter 'dataunitId' is set
    if (dataunitId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataunitId' when calling updateAccessLevelsByDataUnit");
    }
    
    // verify the required parameter 'accesslevels' is set
    if (accesslevels == null) {
      throw new ApiException(400, "Missing the required parameter 'accesslevels' when calling updateAccessLevelsByDataUnit");
    }
    
    // create path and map variables
    String localVarPath = "/dataUnit/{dataunit_id}/accessLevels".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dataunit_id" + "\\}", apiClient.escapeString(dataunitId.toString()));

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

    
    apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
    
  }
  
  /**
   * Updates the granted access level for a data unit related to a person and for a specific requester.
   * Updates the granted access level for a data unit related to a person and for a specific requester.
   * @param dataunitId The dataunit identifier number (required)
   * @param personId The person identifier number (required)
   * @param requesterId The requester identifier number (required)
   * @param accessLevel The access level data in JSON format. (required)
   * @throws ApiException if fails to make API call
   */
  public void updateAccessLevelsByDataUnitByPersonAndRequester(String dataunitId, String personId, String requesterId, AccessLevel accessLevel) throws ApiException {
    Object localVarPostBody = accessLevel;
    
    // verify the required parameter 'dataunitId' is set
    if (dataunitId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataunitId' when calling updateAccessLevelsByDataUnitByPersonAndRequester");
    }
    
    // verify the required parameter 'personId' is set
    if (personId == null) {
      throw new ApiException(400, "Missing the required parameter 'personId' when calling updateAccessLevelsByDataUnitByPersonAndRequester");
    }
    
    // verify the required parameter 'requesterId' is set
    if (requesterId == null) {
      throw new ApiException(400, "Missing the required parameter 'requesterId' when calling updateAccessLevelsByDataUnitByPersonAndRequester");
    }
    
    // verify the required parameter 'accessLevel' is set
    if (accessLevel == null) {
      throw new ApiException(400, "Missing the required parameter 'accessLevel' when calling updateAccessLevelsByDataUnitByPersonAndRequester");
    }
    
    // create path and map variables
    String localVarPath = "/dataUnit/{dataunit_id}/person/{person_id}/requester/{requester_id}/accessLevel".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dataunit_id" + "\\}", apiClient.escapeString(dataunitId.toString()))
      .replaceAll("\\{" + "person_id" + "\\}", apiClient.escapeString(personId.toString()))
      .replaceAll("\\{" + "requester_id" + "\\}", apiClient.escapeString(requesterId.toString()));

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

    
    apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
    
  }
  
  /**
   * Updates an existing data unit.
   * Updates an existing data unit.
   * @param dataunitId The dataunit identifier number (required)
   * @param dataUnit The Data Unit data in JSON format. (required)
   * @return InlineResponse2001
   * @throws ApiException if fails to make API call
   */
  public InlineResponse2001 updateDataUnit(String dataunitId, DataUnitRequest dataUnit) throws ApiException {
    Object localVarPostBody = dataUnit;
    
    // verify the required parameter 'dataunitId' is set
    if (dataunitId == null) {
      throw new ApiException(400, "Missing the required parameter 'dataunitId' when calling updateDataUnit");
    }
    
    // verify the required parameter 'dataUnit' is set
    if (dataUnit == null) {
      throw new ApiException(400, "Missing the required parameter 'dataUnit' when calling updateDataUnit");
    }
    
    // create path and map variables
    String localVarPath = "/dataUnit/{dataunit_id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "dataunit_id" + "\\}", apiClient.escapeString(dataunitId.toString()));

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

    
    GenericType<InlineResponse2001> localVarReturnType = new GenericType<InlineResponse2001>() {};
    return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
}
