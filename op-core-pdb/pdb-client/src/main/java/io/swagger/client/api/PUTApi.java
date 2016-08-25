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


import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;

import io.swagger.model.OSPPrivacyPolicyInput;
import io.swagger.model.PrivacyRegulationInput;
import io.swagger.model.UserPrivacyPolicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-04-19T15:50:46.998Z")
public class PUTApi {
  private ApiClient apiClient;

  public PUTApi() {
    this(Configuration.getDefaultApiClient());
  }

  public PUTApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Update OSPBehaviour entry in the database.
   * Called when by the policy computation component when the regulator api\nupdates a regulation. Their new OSPRequest document is stored in the\npolicy DB.\n
   * @param ospId The identifier number of an OSP (required)
   * @param ospPolicy The changed instance of this OSPRequest (required)
   * @throws ApiException if fails to make API call
   */
  public void oSPOspIdPut(String ospId, OSPPrivacyPolicyInput ospPolicy) throws ApiException {
    Object localVarPostBody = ospPolicy;
    
    // verify the required parameter 'ospId' is set
    if (ospId == null) {
      throw new ApiException(400, "Missing the required parameter 'ospId' when calling oSPOspIdPut");
    }
    
    // verify the required parameter 'ospPolicy' is set
    if (ospPolicy == null) {
      throw new ApiException(400, "Missing the required parameter 'ospPolicy' when calling oSPOspIdPut");
    }
    
    // create path and map variables
    String localVarPath = "/OSP/{osp-id}/".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "osp-id" + "\\}", apiClient.escapeString(ospId.toString()));

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
