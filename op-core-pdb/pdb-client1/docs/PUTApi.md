# PUTApi

All URIs are relative to *http://operando.eu/policy_database*

Method | HTTP request | Description
------------- | ------------- | -------------
[**oSPOspIdPrivacyPolicyPut**](PUTApi.md#oSPOspIdPrivacyPolicyPut) | **PUT** /OSP/{osp-id}/privacy-policy/ | Update OSP text policy entry in the database.
[**oSPOspIdPut**](PUTApi.md#oSPOspIdPut) | **PUT** /OSP/{osp-id}/ | Update OSPBehaviour entry in the database.
[**regulationsRegIdPut**](PUTApi.md#regulationsRegIdPut) | **PUT** /regulations/{reg-id}/ | Update PrivacyRegulation entry in the database.
[**userPrivacyPolicyUserIdPut**](PUTApi.md#userPrivacyPolicyUserIdPut) | **PUT** /user_privacy_policy/{user-id}/ | Update UPP entry in the database for the user.


<a name="oSPOspIdPrivacyPolicyPut"></a>
# **oSPOspIdPrivacyPolicyPut**
> oSPOspIdPrivacyPolicyPut(ospId, ospPolicy)

Update OSP text policy entry in the database.

Called when by the watchdog detects a change in the textual policy and wants to update the current policy. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PUTApi;


PUTApi apiInstance = new PUTApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
OSPReasonPolicyInput ospPolicy = new OSPReasonPolicyInput(); // OSPReasonPolicyInput | The changed instance of this OSPRequest
try {
    apiInstance.oSPOspIdPrivacyPolicyPut(ospId, ospPolicy);
} catch (ApiException e) {
    System.err.println("Exception when calling PUTApi#oSPOspIdPrivacyPolicyPut");
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
//import io.swagger.client.api.PUTApi;


PUTApi apiInstance = new PUTApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
OSPPrivacyPolicyInput ospPolicy = new OSPPrivacyPolicyInput(); // OSPPrivacyPolicyInput | The changed instance of this OSPRequest
try {
    apiInstance.oSPOspIdPut(ospId, ospPolicy);
} catch (ApiException e) {
    System.err.println("Exception when calling PUTApi#oSPOspIdPut");
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

<a name="regulationsRegIdPut"></a>
# **regulationsRegIdPut**
> regulationsRegIdPut(regId, regulation)

Update PrivacyRegulation entry in the database.

Called when by the policy computation component when the regulator api updates a regulation. Their new PrivacyRegulation document is stored in the policy DB. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PUTApi;


PUTApi apiInstance = new PUTApi();
String regId = "regId_example"; // String | The identifier number of a regulation
PrivacyRegulationInput regulation = new PrivacyRegulationInput(); // PrivacyRegulationInput | The changed instance of this PrivacyRegulation
try {
    apiInstance.regulationsRegIdPut(regId, regulation);
} catch (ApiException e) {
    System.err.println("Exception when calling PUTApi#regulationsRegIdPut");
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

<a name="userPrivacyPolicyUserIdPut"></a>
# **userPrivacyPolicyUserIdPut**
> userPrivacyPolicyUserIdPut(userId, upp)

Update UPP entry in the database for the user.

Called when a user makes a change to the UPP registered with operando. Their new privacy preferences are passed in the UPP document and stored in the policy DB. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.PUTApi;


PUTApi apiInstance = new PUTApi();
String userId = "userId_example"; // String | The user identifier number
UserPrivacyPolicy upp = new UserPrivacyPolicy(); // UserPrivacyPolicy | The changed instance of this user's UPP
try {
    apiInstance.userPrivacyPolicyUserIdPut(userId, upp);
} catch (ApiException e) {
    System.err.println("Exception when calling PUTApi#userPrivacyPolicyUserIdPut");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **String**| The user identifier number |
 **upp** | [**UserPrivacyPolicy**](UserPrivacyPolicy.md)| The changed instance of this user&#39;s UPP |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

