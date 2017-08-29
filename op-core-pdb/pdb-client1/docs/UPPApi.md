# UPPApi

All URIs are relative to *http://operando.eu/policy_database*

Method | HTTP request | Description
------------- | ------------- | -------------
[**userPrivacyPolicyGet**](UPPApi.md#userPrivacyPolicyGet) | **GET** /user_privacy_policy/ | Perform a search query across the collection of UPPs.
[**userPrivacyPolicyPost**](UPPApi.md#userPrivacyPolicyPost) | **POST** /user_privacy_policy/ | Create a new UPP entry in the database for the user.
[**userPrivacyPolicyUserIdDelete**](UPPApi.md#userPrivacyPolicyUserIdDelete) | **DELETE** /user_privacy_policy/{user-id}/ | Remove the UPP entry in the database for the user.
[**userPrivacyPolicyUserIdGet**](UPPApi.md#userPrivacyPolicyUserIdGet) | **GET** /user_privacy_policy/{user-id}/ | Read the user privacy policy for the given user id.
[**userPrivacyPolicyUserIdPut**](UPPApi.md#userPrivacyPolicyUserIdPut) | **PUT** /user_privacy_policy/{user-id}/ | Update UPP entry in the database for the user.


<a name="userPrivacyPolicyGet"></a>
# **userPrivacyPolicyGet**
> List&lt;UserPrivacyPolicy&gt; userPrivacyPolicyGet(filter)

Perform a search query across the collection of UPPs.

The query contains a json object (names, values) and the request returns the list of documents (UPPs) where the filter matches i.e. each document contains fields with those values. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UPPApi;


UPPApi apiInstance = new UPPApi();
String filter = "filter_example"; // String | The query filter to be matched - ?filter={json description}
try {
    List<UserPrivacyPolicy> result = apiInstance.userPrivacyPolicyGet(filter);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UPPApi#userPrivacyPolicyGet");
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

<a name="userPrivacyPolicyPost"></a>
# **userPrivacyPolicyPost**
> String userPrivacyPolicyPost(upp)

Create a new UPP entry in the database for the user.

Called when a new user is registered with operando. Their new privacy preferences are passed in the UPP document and stored in the policy DB. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UPPApi;


UPPApi apiInstance = new UPPApi();
UserPrivacyPolicy upp = new UserPrivacyPolicy(); // UserPrivacyPolicy | The first instance of this user's UPP
try {
    String result = apiInstance.userPrivacyPolicyPost(upp);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UPPApi#userPrivacyPolicyPost");
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

<a name="userPrivacyPolicyUserIdDelete"></a>
# **userPrivacyPolicyUserIdDelete**
> userPrivacyPolicyUserIdDelete(userId)

Remove the UPP entry in the database for the user.

Called when a user leaves operando. Their privacy preferences and policies are deleted from the database. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UPPApi;


UPPApi apiInstance = new UPPApi();
String userId = "userId_example"; // String | The user identifier number
try {
    apiInstance.userPrivacyPolicyUserIdDelete(userId);
} catch (ApiException e) {
    System.err.println("Exception when calling UPPApi#userPrivacyPolicyUserIdDelete");
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

<a name="userPrivacyPolicyUserIdGet"></a>
# **userPrivacyPolicyUserIdGet**
> UserPrivacyPolicy userPrivacyPolicyUserIdGet(userId)

Read the user privacy policy for the given user id.

Get a specific UPP document via the user&#39;s id. This will return the full user privacy policy document in json format. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UPPApi;


UPPApi apiInstance = new UPPApi();
String userId = "userId_example"; // String | The user identifier number
try {
    UserPrivacyPolicy result = apiInstance.userPrivacyPolicyUserIdGet(userId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling UPPApi#userPrivacyPolicyUserIdGet");
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

<a name="userPrivacyPolicyUserIdPut"></a>
# **userPrivacyPolicyUserIdPut**
> userPrivacyPolicyUserIdPut(userId, upp)

Update UPP entry in the database for the user.

Called when a user makes a change to the UPP registered with operando. Their new privacy preferences are passed in the UPP document and stored in the policy DB. 

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.UPPApi;


UPPApi apiInstance = new UPPApi();
String userId = "userId_example"; // String | The user identifier number
UserPrivacyPolicy upp = new UserPrivacyPolicy(); // UserPrivacyPolicy | The changed instance of this user's UPP
try {
    apiInstance.userPrivacyPolicyUserIdPut(userId, upp);
} catch (ApiException e) {
    System.err.println("Exception when calling UPPApi#userPrivacyPolicyUserIdPut");
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

