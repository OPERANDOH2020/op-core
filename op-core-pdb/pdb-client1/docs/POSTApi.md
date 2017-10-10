# POSTApi

All URIs are relative to *http://operando.eu/policy_database*

Method | HTTP request | Description
------------- | ------------- | -------------
[**oSPOspIdPrivacyPolicyAccessReasonsPost**](POSTApi.md#oSPOspIdPrivacyPolicyAccessReasonsPost) | **POST** /OSP/{osp-id}/privacy-policy/access-reasons | Create a new access reason statement in the privacy policy.
[**oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut**](POSTApi.md#oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut) | **PUT** /OSP/{osp-id}/privacy-policy/access-reasons/{reason-id} | Update an access reason statement in the privacy policy.
[**oSPPost**](POSTApi.md#oSPPost) | **POST** /OSP/ | Create a new OSP entry in the database.
[**regulationsPost**](POSTApi.md#regulationsPost) | **POST** /regulations/ | Create a new legislation entry in the database.
[**userPrivacyPolicyPost**](POSTApi.md#userPrivacyPolicyPost) | **POST** /user_privacy_policy/ | Create a new UPP entry in the database for the user.


<a name="oSPOspIdPrivacyPolicyAccessReasonsPost"></a>
# **oSPOspIdPrivacyPolicyAccessReasonsPost**
> oSPOspIdPrivacyPolicyAccessReasonsPost(ospId, ospPolicy)

Create a new access reason statement in the privacy policy.

Called by the UI when OSP updating the policy statements 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.POSTApi;


POSTApi apiInstance = new POSTApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
AccessReason ospPolicy = new AccessReason(); // AccessReason | The first instance of this new statement.
try {
    apiInstance.oSPOspIdPrivacyPolicyAccessReasonsPost(ospId, ospPolicy);
} catch (ApiException e) {
    System.err.println("Exception when calling POSTApi#oSPOspIdPrivacyPolicyAccessReasonsPost");
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

<a name="oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut"></a>
# **oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut**
> oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut(ospId, reasonId, ospPolicy)

Update an access reason statement in the privacy policy.

Called by the UI when OSP updating the policy statements 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.POSTApi;


POSTApi apiInstance = new POSTApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
String reasonId = "reasonId_example"; // String | The identifier of a statement in a policy, is only unique to the policy.
AccessReason ospPolicy = new AccessReason(); // AccessReason | The updated instance of this OSP policy statement.
try {
    apiInstance.oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut(ospId, reasonId, ospPolicy);
} catch (ApiException e) {
    System.err.println("Exception when calling POSTApi#oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut");
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

<a name="oSPPost"></a>
# **oSPPost**
> String oSPPost(ospPolicy)

Create a new OSP entry in the database.

Called by the policy computation component when a new regulation is added to OPERANDO. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.POSTApi;


POSTApi apiInstance = new POSTApi();
OSPPrivacyPolicyInput ospPolicy = new OSPPrivacyPolicyInput(); // OSPPrivacyPolicyInput | The first instance of this OSP document
try {
    String result = apiInstance.oSPPost(ospPolicy);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling POSTApi#oSPPost");
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

<a name="regulationsPost"></a>
# **regulationsPost**
> PrivacyRegulation regulationsPost(regulation)

Create a new legislation entry in the database.

Called by the policy computation component when a new regulation is added to OPERANDO. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.POSTApi;


POSTApi apiInstance = new POSTApi();
PrivacyRegulationInput regulation = new PrivacyRegulationInput(); // PrivacyRegulationInput | The first instance of this regulation document
try {
    PrivacyRegulation result = apiInstance.regulationsPost(regulation);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling POSTApi#regulationsPost");
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

<a name="userPrivacyPolicyPost"></a>
# **userPrivacyPolicyPost**
> String userPrivacyPolicyPost(upp)

Create a new UPP entry in the database for the user.

Called when a new user is registered with operando. Their new privacy preferences are passed in the UPP document and stored in the policy DB. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.POSTApi;


POSTApi apiInstance = new POSTApi();
UserPrivacyPolicy upp = new UserPrivacyPolicy(); // UserPrivacyPolicy | The first instance of this user's UPP
try {
    String result = apiInstance.userPrivacyPolicyPost(upp);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling POSTApi#userPrivacyPolicyPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **upp** | [**UserPrivacyPolicy**](UserPrivacyPolicy.md)| The first instance of this user&#39;s UPP |

### Return type

**String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

