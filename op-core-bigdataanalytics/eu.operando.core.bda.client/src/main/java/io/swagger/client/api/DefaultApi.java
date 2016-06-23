package io.swagger.client.api;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.model.*;
import io.swagger.client.Pair;

import io.swagger.client.model.RequestHeader;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-06-23T18:56:53.980Z")
public class DefaultApi {
  private ApiClient apiClient;

  public DefaultApi() {
    this(Configuration.getDefaultApiClient());
  }

  public DefaultApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * 
   * It returns the list of jobs subscribed for that user
   * @param requestHeader Identification of the requesting end user (required)
   * @param jobId Identification of the job to get the status about (required)
   * @throws ApiException if fails to make API call
   */
  public void operandoBdaAcEventChangeGet(RequestHeader requestHeader, String jobId) throws ApiException {
    Object localVarPostBody = requestHeader;
    
    // verify the required parameter 'requestHeader' is set
    if (requestHeader == null) {
      throw new ApiException(400, "Missing the required parameter 'requestHeader' when calling operandoBdaAcEventChangeGet");
    }
    
    // verify the required parameter 'jobId' is set
    if (jobId == null) {
      throw new ApiException(400, "Missing the required parameter 'jobId' when calling operandoBdaAcEventChangeGet");
    }
    
    // create path and map variables
    String localVarPath = "/operando/bda/ac/event/change".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "jobId", jobId));

    
    
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
   * 
   * It returns the list of jobs subscribed for that user
   * @param requestHeader Identification of the requesting end user (required)
   * @param jobId Identification of the job to get the status about (required)
   * @throws ApiException if fails to make API call
   */
  public void operandoBdaAcJobChangeGet(RequestHeader requestHeader, String jobId) throws ApiException {
    Object localVarPostBody = requestHeader;
    
    // verify the required parameter 'requestHeader' is set
    if (requestHeader == null) {
      throw new ApiException(400, "Missing the required parameter 'requestHeader' when calling operandoBdaAcJobChangeGet");
    }
    
    // verify the required parameter 'jobId' is set
    if (jobId == null) {
      throw new ApiException(400, "Missing the required parameter 'jobId' when calling operandoBdaAcJobChangeGet");
    }
    
    // create path and map variables
    String localVarPath = "/operando/bda/ac/job/change".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "jobId", jobId));

    
    
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
   * 
   * It forces the information loading for a given job
   * @param requestHeader Identification of the requesting end user (required)
   * @param jobId Identification of the job to get the status about (required)
   * @throws ApiException if fails to make API call
   */
  public void operandoBdaAcJobInfoLoadGet(RequestHeader requestHeader, String jobId) throws ApiException {
    Object localVarPostBody = requestHeader;
    
    // verify the required parameter 'requestHeader' is set
    if (requestHeader == null) {
      throw new ApiException(400, "Missing the required parameter 'requestHeader' when calling operandoBdaAcJobInfoLoadGet");
    }
    
    // verify the required parameter 'jobId' is set
    if (jobId == null) {
      throw new ApiException(400, "Missing the required parameter 'jobId' when calling operandoBdaAcJobInfoLoadGet");
    }
    
    // create path and map variables
    String localVarPath = "/operando/bda/ac/job/info/load".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "jobId", jobId));

    
    
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
   * 
   * It forces the information updating for a given job, it only load last updates
   * @param requestHeader Identification of the requesting end user (required)
   * @param jobId Identification of the job to get the status about (required)
   * @throws ApiException if fails to make API call
   */
  public void operandoBdaAcJobInfoUpdateGet(RequestHeader requestHeader, String jobId) throws ApiException {
    Object localVarPostBody = requestHeader;
    
    // verify the required parameter 'requestHeader' is set
    if (requestHeader == null) {
      throw new ApiException(400, "Missing the required parameter 'requestHeader' when calling operandoBdaAcJobInfoUpdateGet");
    }
    
    // verify the required parameter 'jobId' is set
    if (jobId == null) {
      throw new ApiException(400, "Missing the required parameter 'jobId' when calling operandoBdaAcJobInfoUpdateGet");
    }
    
    // create path and map variables
    String localVarPath = "/operando/bda/ac/job/info/update".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "jobId", jobId));

    
    
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
   * 
   * It returns the list of jobs available for that user
   * @param requestHeader Identification of the requesting end user (required)
   * @param jobId Identification of the job to get the status about (required)
   * @throws ApiException if fails to make API call
   */
  public void operandoBdaAcJobListGet(RequestHeader requestHeader, String jobId) throws ApiException {
    Object localVarPostBody = requestHeader;
    
    // verify the required parameter 'requestHeader' is set
    if (requestHeader == null) {
      throw new ApiException(400, "Missing the required parameter 'requestHeader' when calling operandoBdaAcJobListGet");
    }
    
    // verify the required parameter 'jobId' is set
    if (jobId == null) {
      throw new ApiException(400, "Missing the required parameter 'jobId' when calling operandoBdaAcJobListGet");
    }
    
    // create path and map variables
    String localVarPath = "/operando/bda/ac/job/list".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "jobId", jobId));

    
    
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
   * 
   * Allows to get the status of a job
   * @param requestHeader Identification of the requesting end user (required)
   * @param jobId Identification of the job to get the status about (required)
   * @throws ApiException if fails to make API call
   */
  public void operandoBdaAcJobStatusGet(RequestHeader requestHeader, String jobId) throws ApiException {
    Object localVarPostBody = requestHeader;
    
    // verify the required parameter 'requestHeader' is set
    if (requestHeader == null) {
      throw new ApiException(400, "Missing the required parameter 'requestHeader' when calling operandoBdaAcJobStatusGet");
    }
    
    // verify the required parameter 'jobId' is set
    if (jobId == null) {
      throw new ApiException(400, "Missing the required parameter 'jobId' when calling operandoBdaAcJobStatusGet");
    }
    
    // create path and map variables
    String localVarPath = "/operando/bda/ac/job/status".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "jobId", jobId));

    
    
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
   * 
   * It returns the list of jobs subscribed for that user
   * @param requestHeader Identification of the requesting end user (required)
   * @param jobId Identification of the job to get the status about (required)
   * @throws ApiException if fails to make API call
   */
  public void operandoBdaAcUserRightsAccessGet(RequestHeader requestHeader, String jobId) throws ApiException {
    Object localVarPostBody = requestHeader;
    
    // verify the required parameter 'requestHeader' is set
    if (requestHeader == null) {
      throw new ApiException(400, "Missing the required parameter 'requestHeader' when calling operandoBdaAcUserRightsAccessGet");
    }
    
    // verify the required parameter 'jobId' is set
    if (jobId == null) {
      throw new ApiException(400, "Missing the required parameter 'jobId' when calling operandoBdaAcUserRightsAccessGet");
    }
    
    // create path and map variables
    String localVarPath = "/operando/bda/ac/user/rights/access".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "jobId", jobId));

    
    
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
   * 
   * It returns the list of jobs subscribed for that user
   * @param requestHeader Identification of the requesting end user (required)
   * @param jobId Identification of the job to get the status about (required)
   * @throws ApiException if fails to make API call
   */
  public void operandoBdaAcUserRightsExecutionGet(RequestHeader requestHeader, String jobId) throws ApiException {
    Object localVarPostBody = requestHeader;
    
    // verify the required parameter 'requestHeader' is set
    if (requestHeader == null) {
      throw new ApiException(400, "Missing the required parameter 'requestHeader' when calling operandoBdaAcUserRightsExecutionGet");
    }
    
    // verify the required parameter 'jobId' is set
    if (jobId == null) {
      throw new ApiException(400, "Missing the required parameter 'jobId' when calling operandoBdaAcUserRightsExecutionGet");
    }
    
    // create path and map variables
    String localVarPath = "/operando/bda/ac/user/rights/execution".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "jobId", jobId));

    
    
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
   * 
   * It returns the list of jobs subscribed for that user
   * @param requestHeader Identification of the requesting end user (required)
   * @param jobId Identification of the job to get the status about (required)
   * @throws ApiException if fails to make API call
   */
  public void operandoBdaAcUserSubscribedGet(RequestHeader requestHeader, String jobId) throws ApiException {
    Object localVarPostBody = requestHeader;
    
    // verify the required parameter 'requestHeader' is set
    if (requestHeader == null) {
      throw new ApiException(400, "Missing the required parameter 'requestHeader' when calling operandoBdaAcUserSubscribedGet");
    }
    
    // verify the required parameter 'jobId' is set
    if (jobId == null) {
      throw new ApiException(400, "Missing the required parameter 'jobId' when calling operandoBdaAcUserSubscribedGet");
    }
    
    // create path and map variables
    String localVarPath = "/operando/bda/ac/user/subscribed".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "jobId", jobId));

    
    
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
}
