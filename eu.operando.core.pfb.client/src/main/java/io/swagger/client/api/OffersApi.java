package io.swagger.client.api;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;

import io.swagger.client.model.Offer;
import io.swagger.client.model.Error;
import io.swagger.client.model.InlineResponse2002;
import io.swagger.client.model.InlineResponse2003;
import io.swagger.client.model.InlineResponse2001;
import io.swagger.client.model.InlineResponse200;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-03-31T13:37:26.696Z")
public class OffersApi {
  private ApiClient apiClient;

  public OffersApi() {
    this(Configuration.getDefaultApiClient());
  }

  public OffersApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  
  /**
   * Creates a new offer for an OSP .
   * Request from Administration Console to create a new offer for an OSP.
   * @param offer The offer data in JSON format. (required)
   * @return InlineResponse2002
   * @throws ApiException if fails to make API call
   */
  public InlineResponse2002 createOffer(Offer offer) throws ApiException {
    Object localVarPostBody = offer;
    
    // verify the required parameter 'offer' is set
    if (offer == null) {
      throw new ApiException(400, "Missing the required parameter 'offer' when calling createOffer");
    }
    
    // create path and map variables
    String localVarPath = "/offers".replaceAll("\\{format\\}","json");

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

    
    GenericType<InlineResponse2002> localVarReturnType = new GenericType<InlineResponse2002>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * Gets the status of a given offer.
   * Get the status of a given offer.
   * @param offerId The offer identifier number (required)
   * @return InlineResponse2003
   * @throws ApiException if fails to make API call
   */
  public InlineResponse2003 getOfferStatus(String offerId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'offerId' is set
    if (offerId == null) {
      throw new ApiException(400, "Missing the required parameter 'offerId' when calling getOfferStatus");
    }
    
    // create path and map variables
    String localVarPath = "/offers/{offer_id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "offer_id" + "\\}", apiClient.escapeString(offerId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    

    

    

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    
    GenericType<InlineResponse2003> localVarReturnType = new GenericType<InlineResponse2003>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * Search offers in databse
   * Extension or management console send request to PfB service to get a list of offers by specific search terms (e.g Gets offers related to a website  when signup page is detected)
   * @param websiteUrl URL of the website to get offers related to specific URL (optional)
   * @param websiteId ID of the website to get offers related to specific website ID (optional)
   * @param ospId ID of the OSP to list of all offers created by specific OSP.. (optional)
   * @param userId ID of the user to limit offers to those apllicable to specific user. (optional)
   * @return InlineResponse2001
   * @throws ApiException if fails to make API call
   */
  public InlineResponse2001 getOffers(String websiteUrl, String websiteId, String ospId, String userId) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/offers".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "website_url", websiteUrl));
    
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "website_id", websiteId));
    
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "osp_id", ospId));
    
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "user_id", userId));
    

    

    

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    
    GenericType<InlineResponse2001> localVarReturnType = new GenericType<InlineResponse2001>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * Create a new deal. Indicates the user&#39;s acceptance for an offer.
   * Triggered by the extension to PfB service to indicate that the user has chosen to accept the offer (i.e. initiated login with Social Network button)
   * @param userId User Id. (required)
   * @param offerId Offer ID. (required)
   * @return InlineResponse200
   * @throws ApiException if fails to make API call
   */
  public InlineResponse200 requestOffer(String userId, String offerId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'userId' is set
    if (userId == null) {
      throw new ApiException(400, "Missing the required parameter 'userId' when calling requestOffer");
    }
    
    // verify the required parameter 'offerId' is set
    if (offerId == null) {
      throw new ApiException(400, "Missing the required parameter 'offerId' when calling requestOffer");
    }
    
    // create path and map variables
    String localVarPath = "/deals".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "user_id", userId));
    
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "offer_id", offerId));
    

    

    

    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    
    GenericType<InlineResponse200> localVarReturnType = new GenericType<InlineResponse200>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
  /**
   * Update an offer for an OSP .
   * Request from Administration Console to update existing offer. (limited to specific fields - TBD).
   * @param offerId The offer identifier number (required)
   * @param offer The offer data in JSON format. (required)
   * @return InlineResponse2003
   * @throws ApiException if fails to make API call
   */
  public InlineResponse2003 updateOffer(String offerId, Offer offer) throws ApiException {
    Object localVarPostBody = offer;
    
    // verify the required parameter 'offerId' is set
    if (offerId == null) {
      throw new ApiException(400, "Missing the required parameter 'offerId' when calling updateOffer");
    }
    
    // verify the required parameter 'offer' is set
    if (offer == null) {
      throw new ApiException(400, "Missing the required parameter 'offer' when calling updateOffer");
    }
    
    // create path and map variables
    String localVarPath = "/offers/{offer_id}".replaceAll("\\{format\\}","json")
      .replaceAll("\\{" + "offer_id" + "\\}", apiClient.escapeString(offerId.toString()));

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

    
    GenericType<InlineResponse2003> localVarReturnType = new GenericType<InlineResponse2003>() {};
    return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
    
  }
  
}
