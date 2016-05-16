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

import io.swagger.model.OSPPrivacyPolicy;
import io.swagger.model.OSPPrivacyPolicyInput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-04-19T15:50:46.998Z")
public class OSPApi {
  private ApiClient apiClient;

  public OSPApi() {
    this(Configuration.getDefaultApiClient());
  }

  public OSPApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Perform a search query across the collection of OSP behaviour.
   * The query contains a json object (names, values) and the request returns the list of documents (regulations) where the filter matches i.e. the document contains fields with those values.\n
   * @param filter The query filter to be matched - ?filter&#x3D;{json description} (required)
   * @return List<OSPPrivacyPolicy>
   * @throws ApiException if fails to make API call
   */
  public List<OSPPrivacyPolicy> oSPGet(String filter) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'filter' is set
    if (filter == null) {
      throw new ApiException(400, "Missing the required parameter 'filter' when calling oSPGet");
    }
    
    // create path and map variables
    String localVarPath = "/OSP/".replaceAll("\\{format\\}","json");

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

    GenericType<List<OSPPrivacyPolicy>> localVarReturnType = new GenericType<List<OSPPrivacyPolicy>>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Remove the OSPRequest entry in the database.
   * Called when by the policy computation component when the regulator api\nrequests that the regulation be deleted.\n
   * @param ospId The identifier number of an OSP (required)
   * @throws ApiException if fails to make API call
   */
  public void oSPOspIdDelete(String ospId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'ospId' is set
    if (ospId == null) {
      throw new ApiException(400, "Missing the required parameter 'ospId' when calling oSPOspIdDelete");
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


    apiClient.invokeAPI(localVarPath, "DELETE", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, null);
  }
  /**
   * Read a given OSP behaviour policy.
   * Get a specific OSP document via the id. This will return the full OSP document in json format.\n
   * @param ospId The identifier number of an OSP (required)
   * @return OSPPrivacyPolicy
   * @throws ApiException if fails to make API call
   */
  public OSPPrivacyPolicy oSPOspIdGet(String ospId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'ospId' is set
    if (ospId == null) {
      throw new ApiException(400, "Missing the required parameter 'ospId' when calling oSPOspIdGet");
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

    GenericType<OSPPrivacyPolicy> localVarReturnType = new GenericType<OSPPrivacyPolicy>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
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
}
