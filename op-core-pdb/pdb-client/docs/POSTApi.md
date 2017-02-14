# POSTApi

All URIs are relative to *http://operando.eu/policy_database*

Method | HTTP request | Description
------------- | ------------- | -------------
[**oSPPost**](POSTApi.md#oSPPost) | **POST** /OSP/ | Create a new OSP entry in the database.
[**regulationsPost**](POSTApi.md#regulationsPost) | **POST** /regulations/ | Create a new legislation entry in the database.
[**userPrivacyPolicyPost**](POSTApi.md#userPrivacyPolicyPost) | **POST** /user_privacy_policy/ | Create a new UPP entry in the database for the user.


<a name="oSPPost"></a>
# **oSPPost**
> oSPPost(ospPolicy)

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
    apiInstance.oSPPost(ospPolicy);
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

null (empty response body)

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
> userPrivacyPolicyPost(upp)

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
    apiInstance.userPrivacyPolicyPost(upp);
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

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

