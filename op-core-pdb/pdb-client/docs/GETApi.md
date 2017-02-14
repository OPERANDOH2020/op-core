# GETApi

All URIs are relative to *http://operando.eu/policy_database*

Method | HTTP request | Description
------------- | ------------- | -------------
[**oSPGet**](GETApi.md#oSPGet) | **GET** /OSP/ | Perform a search query across the collection of OSP behaviour.
[**oSPOspIdGet**](GETApi.md#oSPOspIdGet) | **GET** /OSP/{osp-id}/ | Read a given OSP behaviour policy.
[**oSPOspIdPrivacyPolicyGet**](GETApi.md#oSPOspIdPrivacyPolicyGet) | **GET** /OSP/{osp-id}/privacy-policy/ | Get the current set of privacy policy statements about the usage of data for stated reasons.
[**regulationsGet**](GETApi.md#regulationsGet) | **GET** /regulations/ | Perform a search query across the collection of regulation.
[**regulationsRegIdGet**](GETApi.md#regulationsRegIdGet) | **GET** /regulations/{reg-id}/ | Read a given legislation with its ID.
[**userPrivacyPolicyGet**](GETApi.md#userPrivacyPolicyGet) | **GET** /user_privacy_policy/ | Perform a search query across the collection of UPPs.
[**userPrivacyPolicyUserIdGet**](GETApi.md#userPrivacyPolicyUserIdGet) | **GET** /user_privacy_policy/{user-id}/ | Read the user privacy policy for the given user id.


<a name="oSPGet"></a>
# **oSPGet**
> List&lt;OSPPrivacyPolicy&gt; oSPGet(filter)

Perform a search query across the collection of OSP behaviour.

The query contains a json object (names, values) and the request returns the list of documents (regulations) where the filter matches i.e. the document contains fields with those values. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GETApi;


GETApi apiInstance = new GETApi();
String filter = "filter_example"; // String | The query filter to be matched - ?filter={json description}
try {
    List<OSPPrivacyPolicy> result = apiInstance.oSPGet(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GETApi#oSPGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | **String**| The query filter to be matched - ?filter&#x3D;{json description} |

### Return type

[**List&lt;OSPPrivacyPolicy&gt;**](OSPPrivacyPolicy.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="oSPOspIdGet"></a>
# **oSPOspIdGet**
> OSPPrivacyPolicy oSPOspIdGet(ospId)

Read a given OSP behaviour policy.

Get a specific OSP document via the id. This will return the full OSP document in json format. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GETApi;


GETApi apiInstance = new GETApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
try {
    OSPPrivacyPolicy result = apiInstance.oSPOspIdGet(ospId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GETApi#oSPOspIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ospId** | **String**| The identifier number of an OSP |

### Return type

[**OSPPrivacyPolicy**](OSPPrivacyPolicy.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="oSPOspIdPrivacyPolicyGet"></a>
# **oSPOspIdPrivacyPolicyGet**
> OSPReasonPolicy oSPOspIdPrivacyPolicyGet(ospId)

Get the current set of privacy policy statements about the usage of data for stated reasons.

This is a machine readable version of a G2C privacy policy statement entered using the OSP Admin dashboard; and retrieved by both the OSP &amp; PSP analyst dashboard for display purposes and also by the OSE component for checking regulation compliance.  

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GETApi;


GETApi apiInstance = new GETApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
try {
    OSPReasonPolicy result = apiInstance.oSPOspIdPrivacyPolicyGet(ospId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GETApi#oSPOspIdPrivacyPolicyGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ospId** | **String**| The identifier number of an OSP |

### Return type

[**OSPReasonPolicy**](OSPReasonPolicy.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="regulationsGet"></a>
# **regulationsGet**
> List&lt;PrivacyRegulation&gt; regulationsGet(filter)

Perform a search query across the collection of regulation.

The query contains a json object (names, values) and the request returns  the list of documents (regulations) where the filter matches i.e. the  document contains fields with those values. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GETApi;


GETApi apiInstance = new GETApi();
String filter = "filter_example"; // String | The query filter to be matched - ?filter={json description}
try {
    List<PrivacyRegulation> result = apiInstance.regulationsGet(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GETApi#regulationsGet");
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

<a name="regulationsRegIdGet"></a>
# **regulationsRegIdGet**
> PrivacyRegulation regulationsRegIdGet(regId)

Read a given legislation with its ID.

Get a specific legislation document via the id. This will return the  full legislation document in json format. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GETApi;


GETApi apiInstance = new GETApi();
String regId = "regId_example"; // String | The identifier number of a regulation
try {
    PrivacyRegulation result = apiInstance.regulationsRegIdGet(regId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GETApi#regulationsRegIdGet");
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

<a name="userPrivacyPolicyGet"></a>
# **userPrivacyPolicyGet**
> List&lt;UserPrivacyPolicy&gt; userPrivacyPolicyGet(filter)

Perform a search query across the collection of UPPs.

The query contains a json object (names, values) and the request returns the list of documents (UPPs) where the filter matches i.e. each document contains fields with those values. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GETApi;


GETApi apiInstance = new GETApi();
String filter = "filter_example"; // String | The query filter to be matched - ?filter={json description}
try {
    List<UserPrivacyPolicy> result = apiInstance.userPrivacyPolicyGet(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GETApi#userPrivacyPolicyGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | **String**| The query filter to be matched - ?filter&#x3D;{json description} |

### Return type

[**List&lt;UserPrivacyPolicy&gt;**](UserPrivacyPolicy.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="userPrivacyPolicyUserIdGet"></a>
# **userPrivacyPolicyUserIdGet**
> UserPrivacyPolicy userPrivacyPolicyUserIdGet(userId)

Read the user privacy policy for the given user id.

Get a specific UPP document via the user&#39;s id. This will return the full user privacy policy document in json format. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.GETApi;


GETApi apiInstance = new GETApi();
String userId = "userId_example"; // String | The user identifier number
try {
    UserPrivacyPolicy result = apiInstance.userPrivacyPolicyUserIdGet(userId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling GETApi#userPrivacyPolicyUserIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **String**| The user identifier number |

### Return type

[**UserPrivacyPolicy**](UserPrivacyPolicy.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

