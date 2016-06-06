package io.swagger.client.api;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.model.*;
import io.swagger.client.Pair;

import io.swagger.client.model.InlineResponse200;
import io.swagger.client.model.Error;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-06-06T10:11:04.559Z")
public class LogSearchApi {
  private ApiClient apiClient;

  public LogSearchApi() {
    this(Configuration.getDefaultApiClient());
  }

  public LogSearchApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Search logs in database
   * Search logs in database by specifying a filter.
   * @param dateFrom Date from which wanted to be recovered the logs. (optional)
   * @param dateTo Date to which wanted to be recovered the logs. (optional)
   * @param logLevel Log level wanted to be recovered. (optional)
   * @param requesterType Type of the requester that originated the log entry. (optional)
   * @param requesterId Id of the requester that originated the log entry. (optional)
   * @param logPriority Priority of the log messages to be recovered. (optional)
   * @param title Title of the log messages to be recovered. (optional)
   * @param keyWords Keywords to perform the search. (optional)
   * @return InlineResponse200
   * @throws ApiException if fails to make API call
   */
  public InlineResponse200 getLogs(String dateFrom, String dateTo, String logLevel, String requesterType, String requesterId, String logPriority, String title, List<String> keyWords) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/log/search".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dateFrom", dateFrom));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "dateTo", dateTo));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "logLevel", logLevel));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "requesterType", requesterType));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "requesterId", requesterId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "logPriority", logPriority));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "title", title));
    localVarQueryParams.addAll(apiClient.parameterToPairs("multi", "keyWords", keyWords));

    
    
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
}
