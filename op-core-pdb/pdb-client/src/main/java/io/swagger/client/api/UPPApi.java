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

import io.swagger.model.UserPrivacyPolicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-04-19T15:50:46.998Z")
public class UPPApi {
  private ApiClient apiClient;

  public UPPApi() {
    this(Configuration.getDefaultApiClient());
  }

  public UPPApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Perform a search query across the collection of UPPs.
   * The query contains a json object (names, values) and the request returns the list of documents (UPPs) where the filter matches i.e. each document contains fields with those values.\n
   * @param filter The query filter to be matched - ?filter&#x3D;{json description} (required)
   * @return List<UserPrivacyPolicy>
   * @throws ApiException if fails to make API call
   */
  public List<UserPrivacyPolicy> userPrivacyPolicyGet(String filter) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'filter' is set
    if (filter == null) {
      throw new ApiException(400, "Missing the required parameter 'filter' when calling userPrivacyPolicyGet");
    }
    
    // create path and map variables
    String localVarPath = "/user_privacy_policy/".replaceAll("\\{format\\}","json");

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

    GenericType<List<UserPrivacyPolicy>> localVarReturnType = new GenericType<List<UserPrivacyPolicy>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Create a new UPP entry in the database for the user.
   * Called when a new user is registered with operando. Their new privacy\npreferences are passed in the UPP document and stored in the policy DB.\n
   * @param upp The first instance of this user&#39;s UPP (required)
   * @throws ApiException if fails to make API call
   */
  public void userPrivacyPolicyPost(UserPrivacyPolicy upp) throws ApiException {
    Object localVarPostBody = upp;
    
    // verify the required parameter 'upp' is set
    if (upp == null) {
      throw new ApiException(400, "Missing the required parameter 'upp' when calling userPrivacyPolicyPost");
    }
    
    // create path and map variables
    String localVarPath = "/user_privacy_policy/".replaceAll("\\{format\\}","json");

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


    apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * Remove the UPP entry in the database for the user.
   * Called when a user leaves operando. Their privacy preferences and\npolicies are deleted from the database.\n
   * @param userId The user identifier number (required)
   * @throws ApiException if fails to make API call
   */
  public void userPrivacyPolicyUserIdDelete(String userId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'userId' is set
    if (userId == null) {
      throw new ApiException(400, "Missing the required parameter 'userId' when calling userPrivacyPolicyUserIdDelete");
    }
    
    // create path and map variables
    String localVarPath = "/user_privacy_policy/{user-id}/".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "user-id" + "\\}", apiClient.escapeString(userId.toString()));

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
   * Read the user privacy policy for the given user id.
   * Get a specific UPP document via the user&#39;s id. This will return the full user privacy policy document in json format.\n
   * @param userId The user identifier number (required)
   * @return UserPrivacyPolicy
   * @throws ApiException if fails to make API call
   */
  public UserPrivacyPolicy userPrivacyPolicyUserIdGet(String userId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'userId' is set
    if (userId == null) {
      throw new ApiException(400, "Missing the required parameter 'userId' when calling userPrivacyPolicyUserIdGet");
    }
    
    // create path and map variables
    String localVarPath = "/user_privacy_policy/{user-id}/".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "user-id" + "\\}", apiClient.escapeString(userId.toString()));

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

    GenericType<UserPrivacyPolicy> localVarReturnType = new GenericType<UserPrivacyPolicy>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Update UPP entry in the database for the user.
   * Called when a user makes a change to the UPP registered with operando.\nTheir new privacy preferences are passed in the UPP document and stored\nin the policy DB.\n
   * @param userId The user identifier number (required)
   * @param upp The changed instance of this user&#39;s UPP (required)
   * @throws ApiException if fails to make API call
   */
  public void userPrivacyPolicyUserIdPut(String userId, UserPrivacyPolicy upp) throws ApiException {
    Object localVarPostBody = upp;
    
    // verify the required parameter 'userId' is set
    if (userId == null) {
      throw new ApiException(400, "Missing the required parameter 'userId' when calling userPrivacyPolicyUserIdPut");
    }
    
    // verify the required parameter 'upp' is set
    if (upp == null) {
      throw new ApiException(400, "Missing the required parameter 'upp' when calling userPrivacyPolicyUserIdPut");
    }
    
    // create path and map variables
    String localVarPath = "/user_privacy_policy/{user-id}/".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "user-id" + "\\}", apiClient.escapeString(userId.toString()));

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
