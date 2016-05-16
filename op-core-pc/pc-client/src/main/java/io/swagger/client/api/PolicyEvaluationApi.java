package io.swagger.client.api;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;

import io.swagger.client.model.OSPDataRequest;
import java.util.UUID;
import io.swagger.client.model.PolicyEvaluationReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-05-13T11:21:59.111Z")
public class PolicyEvaluationApi {
  private ApiClient apiClient;

  public PolicyEvaluationApi() {
    this(Configuration.getDefaultApiClient());
  }

  public PolicyEvaluationApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  
  /**
   * Evaluate a set of OSP requests against the User Privacy Policy
   * Called by the Rights Management Component when evaluating if an OSP\nrequest or set of requests matches the user preferences stored in the\nuser&#39;s UPP.\n
   * @param userId Unique identifier representing a specific user (required)
   * @param ospId OSP unique identifier (required)
   * @param ospRequest The set of requests that the OSP wants to perform on a user&#39;s private data (required)
   * @return PolicyEvaluationReport
   * @throws ApiException if fails to make API call
   */
  public PolicyEvaluationReport ospPolicyEvaluatorPost(UUID userId, String ospId, List<OSPDataRequest> ospRequest) throws ApiException {
    Object localVarPostBody = ospRequest;
    
    // verify the required parameter 'userId' is set
    if (userId == null) {
      throw new ApiException(400, "Missing the required parameter 'userId' when calling ospPolicyEvaluatorPost");
    }
    
    // verify the required parameter 'ospId' is set
    if (ospId == null) {
      throw new ApiException(400, "Missing the required parameter 'ospId' when calling ospPolicyEvaluatorPost");
    }
    
    // verify the required parameter 'ospRequest' is set
    if (ospRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'ospRequest' when calling ospPolicyEvaluatorPost");
    }
    
    // create path and map variables
    String localVarPath = "/osp_policy_evaluator".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "user_id", userId));
    
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "osp_id", ospId));
    

    

    

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    
    GenericType<PolicyEvaluationReport> localVarReturnType = new GenericType<PolicyEvaluationReport>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
}
