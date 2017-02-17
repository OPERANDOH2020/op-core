# LegislationApi

All URIs are relative to *http://operando.eu/policy_database*

Method | HTTP request | Description
------------- | ------------- | -------------
[**regulationsGet**](LegislationApi.md#regulationsGet) | **GET** /regulations/ | Perform a search query across the collection of regulation.
[**regulationsPost**](LegislationApi.md#regulationsPost) | **POST** /regulations/ | Create a new legislation entry in the database.
[**regulationsRegIdDelete**](LegislationApi.md#regulationsRegIdDelete) | **DELETE** /regulations/{reg-id}/ | Remove the PrivacyRegulation entry in the database.
[**regulationsRegIdGet**](LegislationApi.md#regulationsRegIdGet) | **GET** /regulations/{reg-id}/ | Read a given legislation with its ID.
[**regulationsRegIdPut**](LegislationApi.md#regulationsRegIdPut) | **PUT** /regulations/{reg-id}/ | Update PrivacyRegulation entry in the database.


<a name="regulationsGet"></a>
# **regulationsGet**
> List&lt;PrivacyRegulation&gt; regulationsGet(filter)

Perform a search query across the collection of regulation.

The query contains a json object (names, values) and the request returns  the list of documents (regulations) where the filter matches i.e. the  document contains fields with those values. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.LegislationApi;


LegislationApi apiInstance = new LegislationApi();
String filter = "filter_example"; // String | The query filter to be matched - ?filter={json description}
try {
    List<PrivacyRegulation> result = apiInstance.regulationsGet(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling LegislationApi#regulationsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | **String**| The query filter to be matched - ?filter&#x3D;{json description} |

### Return type

[**List&lt;PrivacyRegulation&gt;**](PrivacyRegulation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="regulationsPost"></a>
# **regulationsPost**
> PrivacyRegulation regulationsPost(regulation)

Create a new legislation entry in the database.

Called by the policy computation component when a new regulation is added to OPERANDO. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.LegislationApi;


LegislationApi apiInstance = new LegislationApi();
PrivacyRegulationInput regulation = new PrivacyRegulationInput(); // PrivacyRegulationInput | The first instance of this regulation document
try {
    PrivacyRegulation result = apiInstance.regulationsPost(regulation);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling LegislationApi#regulationsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **regulation** | [**PrivacyRegulationInput**](PrivacyRegulationInput.md)| The first instance of this regulation document |

### Return type

[**PrivacyRegulation**](PrivacyRegulation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="regulationsRegIdDelete"></a>
# **regulationsRegIdDelete**
> regulationsRegIdDelete(regId)

Remove the PrivacyRegulation entry in the database.

Called when by the policy computation component when the regulator api requests that the regulation be deleted. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.LegislationApi;


LegislationApi apiInstance = new LegislationApi();
String regId = "regId_example"; // String | The identifier number of a regulation
try {
    apiInstance.regulationsRegIdDelete(regId);
} catch (ApiException e) {
    System.err.println("Exception when calling LegislationApi#regulationsRegIdDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **regId** | **String**| The identifier number of a regulation |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="regulationsRegIdGet"></a>
# **regulationsRegIdGet**
> PrivacyRegulation regulationsRegIdGet(regId)

Read a given legislation with its ID.

Get a specific legislation document via the id. This will return the  full legislation document in json format. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.LegislationApi;


LegislationApi apiInstance = new LegislationApi();
String regId = "regId_example"; // String | The identifier number of a regulation
try {
    PrivacyRegulation result = apiInstance.regulationsRegIdGet(regId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling LegislationApi#regulationsRegIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **regId** | **String**| The identifier number of a regulation |

### Return type

[**PrivacyRegulation**](PrivacyRegulation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="regulationsRegIdPut"></a>
# **regulationsRegIdPut**
> regulationsRegIdPut(regId, regulation)

Update PrivacyRegulation entry in the database.

Called when by the policy computation component when the regulator api updates a regulation. Their new PrivacyRegulation document is stored in the policy DB. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.LegislationApi;


LegislationApi apiInstance = new LegislationApi();
String regId = "regId_example"; // String | The identifier number of a regulation
PrivacyRegulationInput regulation = new PrivacyRegulationInput(); // PrivacyRegulationInput | The changed instance of this PrivacyRegulation
try {
    apiInstance.regulationsRegIdPut(regId, regulation);
} catch (ApiException e) {
    System.err.println("Exception when calling LegislationApi#regulationsRegIdPut");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **regId** | **String**| The identifier number of a regulation |
 **regulation** | [**PrivacyRegulationInput**](PrivacyRegulationInput.md)| The changed instance of this PrivacyRegulation |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

