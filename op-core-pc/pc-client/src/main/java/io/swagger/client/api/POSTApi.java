package io.swagger.client.api;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.model.*;
import io.swagger.client.Pair;

import io.swagger.client.model.PolicyEvaluationReport;
import io.swagger.client.model.ComputationResult;
import io.swagger.client.model.UserPreference;
import io.swagger.client.model.OSPDataRequest;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-06-27T09:46:52.939Z")
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
  /**
   * Computes the UPP policy for a specific G2C OSP.
   * This is a computational resource accessed via a REST POST call to carry out a computation on the provided inputs. This results in an resource that is created and stored in the Policy DB and can be accessed by the URL returned as a result of this operation. In OPERANDO, this method is called by the management console when a user subscribes to a new OSP service. The user simply enters answers the specific OSP questionnaire. This will create a simple UPP for that OSP and store this policy within the larger UPP record. 
   * @param userId user unique identifier of the UPP to compute (required)
   * @param ospId user unique identifier of the UPP to compute (required)
   * @param ospPrefs The set of privacy preferences. This is a JSON object with the answers to the specific questionnaire. These can be changed and updated.   (required)
   * @return ComputationResult
   * @throws ApiException if fails to make API call
   */
  public ComputationResult ospPolicyComputerPost(String userId, String ospId, List<UserPreference> ospPrefs) throws ApiException {
    Object localVarPostBody = ospPrefs;
    
    // verify the required parameter 'userId' is set
    if (userId == null) {
      throw new ApiException(400, "Missing the required parameter 'userId' when calling ospPolicyComputerPost");
    }
    
    // verify the required parameter 'ospId' is set
    if (ospId == null) {
      throw new ApiException(400, "Missing the required parameter 'ospId' when calling ospPolicyComputerPost");
    }
    
    // verify the required parameter 'ospPrefs' is set
    if (ospPrefs == null) {
      throw new ApiException(400, "Missing the required parameter 'ospPrefs' when calling ospPolicyComputerPost");
    }
    
    // create path and map variables
    String localVarPath = "/osp_policy_computer".replaceAll("\\{format\\}","json");

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

    GenericType<ComputationResult> localVarReturnType = new GenericType<ComputationResult>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Evaluate a set of OSP requests against the User Privacy Policy
   * Called by the Rights Management Component when evaluating if an OSP request or set of requests matches the user preferences stored in the user&#39;s UPP. 
   * @param userId Unique identifier representing a specific user (required)
   * @param ospId OSP unique identifier (required)
   * @param ospRequest The set of requests that the OSP wants to perform on a user&#39;s private data (required)
   * @return PolicyEvaluationReport
   * @throws ApiException if fails to make API call
   */
  public PolicyEvaluationReport ospPolicyEvaluatorPost(String userId, String ospId, List<OSPDataRequest> ospRequest) throws ApiException {
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
  /**
   * Creates the UPP when the user registers with OPERANDO.
   * This is a computational resource accessed via a REST POST call to carry out a computation on the provided inputs. This results in an resource that is created and stored in the Policy DB and can be accessed by the URL returned as a result of this operation. In OPERANDO, this method is called by the management console when a user registers with OPERANDO. The user simply enters privacy details and answers a general questionnaire.  
   * @param userId user unique identifier of the UPP to compute (required)
   * @param generalPreferences The set of privacy preferences. This is a JSON object with the answers to the initial questionnaire. These can be changed and updated.   (required)
   * @return ComputationResult
   * @throws ApiException if fails to make API call
   */
  public ComputationResult policyComputerPost(String userId, List<UserPreference> generalPreferences) throws ApiException {
    Object localVarPostBody = generalPreferences;
    
    // verify the required parameter 'userId' is set
    if (userId == null) {
      throw new ApiException(400, "Missing the required parameter 'userId' when calling policyComputerPost");
    }
    
    // verify the required parameter 'generalPreferences' is set
    if (generalPreferences == null) {
      throw new ApiException(400, "Missing the required parameter 'generalPreferences' when calling policyComputerPost");
    }
    
    // create path and map variables
    String localVarPath = "/policy_computer".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "user_id", userId));

    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<ComputationResult> localVarReturnType = new GenericType<ComputationResult>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
