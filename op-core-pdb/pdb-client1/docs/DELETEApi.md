# DELETEApi

All URIs are relative to *http://operando.eu/policy_database*

Method | HTTP request | Description
------------- | ------------- | -------------
[**oSPOspIdDelete**](DELETEApi.md#oSPOspIdDelete) | **DELETE** /OSP/{osp-id}/ | Remove the OSPRequest entry in the database.
[**oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete**](DELETEApi.md#oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete) | **DELETE** /OSP/{osp-id}/privacy-policy/access-reasons/{reason-id} | Remove the AccessReason entry in the list.
[**regulationsRegIdDelete**](DELETEApi.md#regulationsRegIdDelete) | **DELETE** /regulations/{reg-id}/ | Remove the PrivacyRegulation entry in the database.
[**userPrivacyPolicyUserIdDelete**](DELETEApi.md#userPrivacyPolicyUserIdDelete) | **DELETE** /user_privacy_policy/{user-id}/ | Remove the UPP entry in the database for the user.


<a name="oSPOspIdDelete"></a>
# **oSPOspIdDelete**
> oSPOspIdDelete(ospId)

Remove the OSPRequest entry in the database.

Called when by the policy computation component when the regulator api requests that the regulation be deleted. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DELETEApi;


DELETEApi apiInstance = new DELETEApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
try {
    apiInstance.oSPOspIdDelete(ospId);
} catch (ApiException e) {
    System.err.println("Exception when calling DELETEApi#oSPOspIdDelete");
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

<a name="oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete"></a>
# **oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete**
> oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete(ospId, reasonId)

Remove the AccessReason entry in the list.

Remove an identified value. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DELETEApi;


DELETEApi apiInstance = new DELETEApi();
String ospId = "ospId_example"; // String | The identifier number of an OSP
String reasonId = "reasonId_example"; // String | The identifier of a statement in a policy, is only unique to the policy.
try {
    apiInstance.oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete(ospId, reasonId);
} catch (ApiException e) {
    System.err.println("Exception when calling DELETEApi#oSPOspIdPrivacyPolicyAccessReasonsReasonIdDelete");
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

<a name="regulationsRegIdDelete"></a>
# **regulationsRegIdDelete**
> regulationsRegIdDelete(regId)

Remove the PrivacyRegulation entry in the database.

Called when by the policy computation component when the regulator api requests that the regulation be deleted. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DELETEApi;


DELETEApi apiInstance = new DELETEApi();
String regId = "regId_example"; // String | The identifier number of a regulation
try {
    apiInstance.regulationsRegIdDelete(regId);
} catch (ApiException e) {
    System.err.println("Exception when calling DELETEApi#regulationsRegIdDelete");
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

<a name="userPrivacyPolicyUserIdDelete"></a>
# **userPrivacyPolicyUserIdDelete**
> userPrivacyPolicyUserIdDelete(userId)

Remove the UPP entry in the database for the user.

Called when a user leaves operando. Their privacy preferences and policies are deleted from the database. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DELETEApi;


DELETEApi apiInstance = new DELETEApi();
String userId = "userId_example"; // String | The user identifier number
try {
    apiInstance.userPrivacyPolicyUserIdDelete(userId);
} catch (ApiException e) {
    System.err.println("Exception when calling DELETEApi#userPrivacyPolicyUserIdDelete");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **String**| The user identifier number |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

