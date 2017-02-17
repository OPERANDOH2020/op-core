package io.swagger.client.api;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.model.*;
import io.swagger.client.Pair;

import io.swagger.client.model.PolicyEvaluationReport;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-06-27T09:46:52.939Z")
public class PolicyComplianceApi {
  private ApiClient apiClient;

  public PolicyComplianceApi() {
    this(Configuration.getDefaultApiClient());
  }

  public PolicyComplianceApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Evaluate a set of OSP requests against the current legislation
   * Method to run a new OSP registers with OPERANDO; or a an OSP changes its policy. The computational resource reads the sector information for the OSP (from the policy db) and then finds all the relevant regulations. Given the policy input, it then checks that this against the discovered regulation. 
   * @param ospId OSP unique identifier (required)
   * @param ospRequest The set of requests that the OSP will carry out on a user&#39;s private data (required)
   * @return PolicyEvaluationReport
   * @throws ApiException if fails to make API call
   */
  public PolicyEvaluationReport ospComplianceCheckerPost(String ospId, Object ospRequest) throws ApiException {
    Object localVarPostBody = ospRequest;
    
    // verify the required parameter 'ospId' is set
    if (ospId == null) {
      throw new ApiException(400, "Missing the required parameter 'ospId' when calling ospComplianceCheckerPost");
    }
    
    // verify the required parameter 'ospRequest' is set
    if (ospRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'ospRequest' when calling ospComplianceCheckerPost");
    }
    
    // create path and map variables
    String localVarPath = "/osp_compliance_checker".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

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
