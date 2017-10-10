# OSPApi

All URIs are relative to *http://operando.eu/policy_database*

Method | HTTP request | Description
------------- | ------------- | -------------
[**oSPGet**](OSPApi.md#oSPGet) | **GET** /OSP/ | Perform a search query across the collection of OSP behaviour.
[**oSPOspIdDelete**](OSPApi.md#oSPOspIdDelete) | **DELETE** /OSP/{osp-id}/ | Remove the OSPRequest entry in the database.
[**oSPOspIdGet**](OSPApi.md#oSPOspIdGet) | **GET** /OSP/{osp-id}/ | Read a given OSP behaviour policy.
[**oSPOspIdPrivacyPolicyAccessReasonsGet**](OSPApi.md#oSPOspIdPrivacyPolicyAccessReasonsGet) | **GET** /OSP/{osp-id}/privacy-policy/access-reasons | Get the list of access reason policy statements.
[**oSPOspIdPrivacyPolicyAccessReasonsPost**](OSPApi.md#oSPOspIdPrivacyPolicyAccessReasonsPost) | **POST** /OSP/{osp-id}/privacy-policy/access-reasons | Create a new access reason statement in the privacy policy.
[**oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete**](OSPApi.md#oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete) | **DELETE** /OSP/{osp-id}/privacy-policy/access-reasons/{reason-id} | Remove the AccessReason entry in the list.
[**oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut**](OSPApi.md#oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut) | **PUT** /OSP/{osp-id}/privacy-policy/access-reasons/{reason-id} | Update an access reason statement in the privacy policy.
[**oSPOspIdPrivacyPolicyGet**](OSPApi.md#oSPOspIdPrivacyPolicyGet) | **GET** /OSP/{osp-id}/privacy-policy/ | Get the current set of privacy policy statements about the usage of data for stated reasons.
[**oSPOspIdPrivacyPolicyPut**](OSPApi.md#oSPOspIdPrivacyPolicyPut) | **PUT** /OSP/{osp-id}/privacy-policy/ | Update OSP text policy entry in the database.
[**oSPOspIdPut**](OSPApi.md#oSPOspIdPut) | **PUT** /OSP/{osp-id}/ | Update OSPBehaviour entry in the database.
[**oSPPost**](OSPApi.md#oSPPost) | **POST** /OSP/ | Create a new OSP entry in the database.


<a name="oSPGet"></a>
# **oSPGet**
> List&lt;OSPPrivacyPolicy&gt; oSPGet(filter)

Perform a search query across the collection of OSP behaviour.

The query contains a json object (names, values) and the request returns the list of documents (regulations) where the filter matches i.e. the document contains fields with those values. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.OSPApi;


OSPApi apiInstance = new OSPApi();
String filter = "filter_example"; // String | The query filter to be matched - ?filter={json description}
try {
    List<OSPPrivacyPolicy> result = apiInstance.oSPGet(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling OSPApi#oSPGet");
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

<a name="oSPOspIdDelete"></a>
# **oSPOspIdDelete**
> oSPOspIdDelete(ospId)

Remove the OSPRequest entry in the database.

Called when by the policy computation component when the regulator api requests that the regulation be deleted. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.OSPApi;


OSPApi apiInstance = new OSPApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
try {
    apiInstance.oSPOspIdDelete(ospId);
} catch (ApiException e) {
    System.err.println("Exception when calling OSPApi#oSPOspIdDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ospId** | **String**| The identifier number of an OSP |

### Return type

null (empty response body)

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
//import io.swagger.client.api.OSPApi;


OSPApi apiInstance = new OSPApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
try {
    OSPPrivacyPolicy result = apiInstance.oSPOspIdGet(ospId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling OSPApi#oSPOspIdGet");
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

<a name="oSPOspIdPrivacyPolicyAccessReasonsGet"></a>
# **oSPOspIdPrivacyPolicyAccessReasonsGet**
> List&lt;OSPReasonPolicy&gt; oSPOspIdPrivacyPolicyAccessReasonsGet(ospId)

Get the list of access reason policy statements.

List of policy statements.  

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.OSPApi;


OSPApi apiInstance = new OSPApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
try {
    List<OSPReasonPolicy> result = apiInstance.oSPOspIdPrivacyPolicyAccessReasonsGet(ospId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling OSPApi#oSPOspIdPrivacyPolicyAccessReasonsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ospId** | **String**| The identifier number of an OSP |

### Return type

[**List&lt;OSPReasonPolicy&gt;**](OSPReasonPolicy.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="oSPOspIdPrivacyPolicyAccessReasonsPost"></a>
# **oSPOspIdPrivacyPolicyAccessReasonsPost**
> oSPOspIdPrivacyPolicyAccessReasonsPost(ospId, ospPolicy)

Create a new access reason statement in the privacy policy.

Called by the UI when OSP updating the policy statements 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.OSPApi;


OSPApi apiInstance = new OSPApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
AccessReason ospPolicy = new AccessReason(); // AccessReason | The first instance of this new statement.
try {
    apiInstance.oSPOspIdPrivacyPolicyAccessReasonsPost(ospId, ospPolicy);
} catch (ApiException e) {
    System.err.println("Exception when calling OSPApi#oSPOspIdPrivacyPolicyAccessReasonsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ospId** | **String**| The identifier number of an OSP |
 **ospPolicy** | [**AccessReason**](AccessReason.md)| The first instance of this new statement. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete"></a>
# **oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete**
> oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete(ospId, reasonId)

Remove the AccessReason entry in the list.

Remove an identified value. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.OSPApi;


OSPApi apiInstance = new OSPApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
String reasonId = "reasonId_example"; // String | The identifier of a statement in a policy, is only unique to the policy.
try {
    apiInstance.oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete(ospId, reasonId);
} catch (ApiException e) {
    System.err.println("Exception when calling OSPApi#oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ospId** | **String**| The identifier number of an OSP |
 **reasonId** | **String**| The identifier of a statement in a policy, is only unique to the policy. |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut"></a>
# **oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut**
> oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut(ospId, reasonId, ospPolicy)

Update an access reason statement in the privacy policy.

Called by the UI when OSP updating the policy statements 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.OSPApi;


OSPApi apiInstance = new OSPApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
String reasonId = "reasonId_example"; // String | The identifier of a statement in a policy, is only unique to the policy.
AccessReason ospPolicy = new AccessReason(); // AccessReason | The updated instance of this OSP policy statement.
try {
    apiInstance.oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut(ospId, reasonId, ospPolicy);
} catch (ApiException e) {
    System.err.println("Exception when calling OSPApi#oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ospId** | **String**| The identifier number of an OSP |
 **reasonId** | **String**| The identifier of a statement in a policy, is only unique to the policy. |
 **ospPolicy** | [**AccessReason**](AccessReason.md)| The updated instance of this OSP policy statement. |

### Return type

null (empty response body)

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
//import io.swagger.client.api.OSPApi;


OSPApi apiInstance = new OSPApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
try {
    OSPReasonPolicy result = apiInstance.oSPOspIdPrivacyPolicyGet(ospId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling OSPApi#oSPOspIdPrivacyPolicyGet");
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

<a name="oSPOspIdPrivacyPolicyPut"></a>
# **oSPOspIdPrivacyPolicyPut**
> oSPOspIdPrivacyPolicyPut(ospId, ospPolicy)

Update OSP text policy entry in the database.

Called when by the watchdog detects a change in the textual policy and wants to update the current policy. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.OSPApi;


OSPApi apiInstance = new OSPApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
OSPReasonPolicyInput ospPolicy = new OSPReasonPolicyInput(); // OSPReasonPolicyInput | The changed instance of this OSPRequest
try {
    apiInstance.oSPOspIdPrivacyPolicyPut(ospId, ospPolicy);
} catch (ApiException e) {
    System.err.println("Exception when calling OSPApi#oSPOspIdPrivacyPolicyPut");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ospId** | **String**| The identifier number of an OSP |
 **ospPolicy** | [**OSPReasonPolicyInput**](OSPReasonPolicyInput.md)| The changed instance of this OSPRequest |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="oSPOspIdPut"></a>
# **oSPOspIdPut**
> oSPOspIdPut(ospId, ospPolicy)

Update OSPBehaviour entry in the database.

Called when by the policy computation component when the regulator api updates a regulation. Their new OSPRequest document is stored in the policy DB. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.OSPApi;


OSPApi apiInstance = new OSPApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
OSPPrivacyPolicyInput ospPolicy = new OSPPrivacyPolicyInput(); // OSPPrivacyPolicyInput | The changed instance of this OSPRequest
try {
    apiInstance.oSPOspIdPut(ospId, ospPolicy);
} catch (ApiException e) {
    System.err.println("Exception when calling OSPApi#oSPOspIdPut");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ospId** | **String**| The identifier number of an OSP |
 **ospPolicy** | [**OSPPrivacyPolicyInput**](OSPPrivacyPolicyInput.md)| The changed instance of this OSPRequest |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="oSPPost"></a>
# **oSPPost**
> String oSPPost(ospPolicy)

Create a new OSP entry in the database.

Called by the policy computation component when a new regulation is added to OPERANDO. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.OSPApi;


OSPApi apiInstance = new OSPApi();
OSPPrivacyPolicyInput ospPolicy = new OSPPrivacyPolicyInput(); // OSPPrivacyPolicyInput | The first instance of this OSP document
try {
    String result = apiInstance.oSPPost(ospPolicy);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling OSPApi#oSPPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ospPolicy** | [**OSPPrivacyPolicyInput**](OSPPrivacyPolicyInput.md)| The first instance of this OSP document |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

