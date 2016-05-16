/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2016
//
// Copyright in this software belongs to University of Southampton
// IT Innovation Centre of Gamma House, Enterprise Road,
// Chilworth Science Park, Southampton, SO16 7NS, UK.
//
// This software may not be used, sold, licensed, transferred, copied
// or reproduced in whole or in part in any manner or form or in or
// on any media by any person other than in accordance with the terms
// of the Licence Agreement supplied with the software, or otherwise
// without the prior written consent of the copyright owners.
//
// This software is distributed WITHOUT ANY WARRANTY, without even the
// implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
// PURPOSE, except where stated in the Licence Agreement supplied with
// the software.
//
//      Created By :            Panos Melas
//      Created Date :          2016-04-28
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

package io.swagger.client.api;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;

import io.swagger.model.PrivacyRegulation;
import io.swagger.model.PrivacyRegulationInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-04-19T15:50:46.998Z")
public class LegislationApi {
  private ApiClient apiClient;

  public LegislationApi() {
    this(Configuration.getDefaultApiClient());
  }

  public LegislationApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Perform a search query across the collection of regulation.
   * The query contains a json object (names, values) and the request returns \nthe list of documents (regulations) where the filter matches i.e. the \ndocument contains fields with those values.\n
   * @param filter The query filter to be matched - ?filter&#x3D;{json description} (required)
   * @return List<PrivacyRegulation>
   * @throws ApiException if fails to make API call
   */
  public List<PrivacyRegulation> regulationsGet(String filter) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'filter' is set
    if (filter == null) {
      throw new ApiException(400, "Missing the required parameter 'filter' when calling regulationsGet");
    }
    
    // create path and map variables
    String localVarPath = "/regulations/".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "filter", filter));

    
    
    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<List<PrivacyRegulation>> localVarReturnType = new GenericType<List<PrivacyRegulation>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Create a new legislation entry in the database.
   * Called by the policy computation component when a new regulation is added\nto OPERANDO.\n
   * @param regulation The first instance of this regulation document (required)
   * @return PrivacyRegulation
   * @throws ApiException if fails to make API call
   */
  public PrivacyRegulation regulationsPost(PrivacyRegulationInput regulation) throws ApiException {
    Object localVarPostBody = regulation;
    
    // verify the required parameter 'regulation' is set
    if (regulation == null) {
      throw new ApiException(400, "Missing the required parameter 'regulation' when calling regulationsPost");
    }
    
    // create path and map variables
    String localVarPath = "/regulations/".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<PrivacyRegulation> localVarReturnType = new GenericType<PrivacyRegulation>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Remove the PrivacyRegulation entry in the database.
   * Called when by the policy computation component when the regulator api\nrequests that the regulation be deleted.\n
   * @param regId The identifier number of a regulation (required)
   * @throws ApiException if fails to make API call
   */
  public void regulationsRegIdDelete(String regId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'regId' is set
    if (regId == null) {
      throw new ApiException(400, "Missing the required parameter 'regId' when calling regulationsRegIdDelete");
    }
    
    // create path and map variables
    String localVarPath = "/regulations/{reg-id}/".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "reg-id" + "\\}", apiClient.escapeString(regId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };


    apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * Read a given legislation with its ID.
   * Get a specific legislation document via the id. This will return the \nfull legislation document in json format.\n
   * @param regId The identifier number of a regulation (required)
   * @return PrivacyRegulation
   * @throws ApiException if fails to make API call
   */
  public PrivacyRegulation regulationsRegIdGet(String regId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'regId' is set
    if (regId == null) {
      throw new ApiException(400, "Missing the required parameter 'regId' when calling regulationsRegIdGet");
    }
    
    // create path and map variables
    String localVarPath = "/regulations/{reg-id}/".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "reg-id" + "\\}", apiClient.escapeString(regId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<PrivacyRegulation> localVarReturnType = new GenericType<PrivacyRegulation>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Update PrivacyRegulation entry in the database.
   * Called when by the policy computation component when the regulator api\nupdates a regulation. Their new PrivacyRegulation document is stored in\nthe policy DB.\n
   * @param regId The identifier number of a regulation (required)
   * @param regulation The changed instance of this PrivacyRegulation (required)
   * @throws ApiException if fails to make API call
   */
  public void regulationsRegIdPut(String regId, PrivacyRegulationInput regulation) throws ApiException {
    Object localVarPostBody = regulation;
    
    // verify the required parameter 'regId' is set
    if (regId == null) {
      throw new ApiException(400, "Missing the required parameter 'regId' when calling regulationsRegIdPut");
    }
    
    // verify the required parameter 'regulation' is set
    if (regulation == null) {
      throw new ApiException(400, "Missing the required parameter 'regulation' when calling regulationsRegIdPut");
    }
    
    // create path and map variables
    String localVarPath = "/regulations/{reg-id}/".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "reg-id" + "\\}", apiClient.escapeString(regId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    
    final String[] localVarAccepts = {
      
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };


    apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
}
