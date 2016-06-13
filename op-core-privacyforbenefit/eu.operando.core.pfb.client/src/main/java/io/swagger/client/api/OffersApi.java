package io.swagger.client.api;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiException;
import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.model.*;
import io.swagger.client.Pair;

import io.swagger.client.model.OfferRequest;
import io.swagger.client.model.Error;
import io.swagger.client.model.InlineResponse2002;
import io.swagger.client.model.InlineResponse2001;
import io.swagger.client.model.InlineResponse2003;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-06-10T08:21:55.264Z")
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
  public InlineResponse2002 createOffer(OfferRequest offer) throws ApiException {
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
   * Search deals in databse
   * Extension or management console send request to PfB service to get a list of deals by specific search terms (e.g Gets deals related to a specific OSP, or to a concrete user)
   * @param offerId ID of the offer implied in the deal. (optional)
   * @param userId ID of the user implied in the deal. (optional)
   * @param createdFrom Date from which it&#39;s wanted to search the deals.. (optional)
   * @param createdTo Date to which it&#39;s wanted to search the deals. (optional)
   * @param canceled Specify if the deal has been canceled or not (possible values true or false). (optional)
   * @return InlineResponse2001
   * @throws ApiException if fails to make API call
   */
  public InlineResponse2001 getDeals(String offerId, String userId, String createdFrom, String createdTo, String canceled) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/deals/search".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "offer_id", offerId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "user_id", userId));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "created_from", createdFrom));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "created_to", createdTo));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "canceled", canceled));

    
    
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
    String localVarPath = "/offers/search".replaceAll("\\{format\\}","json");

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
   * Update an offer for an OSP .
   * Request from Administration Console to update existing offer. (limited to specific fields - TBD).
   * @param offerId The offer identifier number (required)
   * @param offer The offer data in JSON format. (required)
   * @return InlineResponse2003
   * @throws ApiException if fails to make API call
   */
  public InlineResponse2003 updateOffer(String offerId, OfferRequest offer) throws ApiException {
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
