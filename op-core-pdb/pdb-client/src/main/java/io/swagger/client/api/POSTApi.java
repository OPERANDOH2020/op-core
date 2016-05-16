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

import io.swagger.model.OSPPrivacyPolicyInput;
import io.swagger.model.PrivacyRegulationInput;
import io.swagger.model.PrivacyRegulation;
import io.swagger.model.UserPrivacyPolicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-04-19T15:50:46.998Z")
public class POSTApi {
  private ApiClient apiClient;

  public POSTApi() {
    this(Configuration.getDefaultApiClient());
  }

  public POSTApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Create a new OSP entry in the database.
   * Called by the policy computation component when a new regulation is added\nto OPERANDO.\n
   * @param ospPolicy The first instance of this OSP document (required)
   * @throws ApiException if fails to make API call
   */
  public void oSPPost(OSPPrivacyPolicyInput ospPolicy) throws ApiException {
    Object localVarPostBody = ospPolicy;
    
    // verify the required parameter 'ospPolicy' is set
    if (ospPolicy == null) {
      throw new ApiException(400, "Missing the required parameter 'ospPolicy' when calling oSPPost");
    }
    
    // create path and map variables
    String localVarPath = "/OSP/".replaceAll("\\{format\\}","json");

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
}
