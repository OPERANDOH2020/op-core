package io.swagger.client.api;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;

import io.swagger.client.model.PrivacyRegulation;
import io.swagger.client.model.ComputationResult;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-05-13T11:21:59.111Z")
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
   * 
   * Called by the Regulator API. An updated regulation is entered into\nOPERANDO. The regulation is stored in the policy DB. Existing UPPs are\nthen evaluated to see if they comply with the regulation. The report is then updated at /regulations/{reg_id}/report in the policy db and the url is returned to the user in order that they can retrieve this report.\nPre-condition -- The regulation must have been written to the system.\n
   * @param regId The unqiue regulation ID for the changed regulation. (required)
   * @param regulation The description of the changed regulation. (required)
   * @return ComputationResult
   * @throws ApiException if fails to make API call
   */
  public ComputationResult regulationsRegIdPut(BigDecimal regId, List<PrivacyRegulation> regulation) throws ApiException {
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
    String localVarPath = "/regulations/{reg_id}/".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "reg_id" + "\\}", apiClient.escapeString(regId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    

    

    

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
    return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
}
