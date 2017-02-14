# DefaultApi

All URIs are relative to *http://localhost:8080*

Method | HTTP request | Description
------------- | ------------- | -------------
[**operandoBdaAcEventChangeGet**](DefaultApi.md#operandoBdaAcEventChangeGet) | **GET** /operando/bda/ac/event/change | 
[**operandoBdaAcJobChangeGet**](DefaultApi.md#operandoBdaAcJobChangeGet) | **GET** /operando/bda/ac/job/change | 
[**operandoBdaAcJobInfoLoadGet**](DefaultApi.md#operandoBdaAcJobInfoLoadGet) | **GET** /operando/bda/ac/job/info/load | 
[**operandoBdaAcJobInfoUpdateGet**](DefaultApi.md#operandoBdaAcJobInfoUpdateGet) | **GET** /operando/bda/ac/job/info/update | 
[**operandoBdaAcJobListGet**](DefaultApi.md#operandoBdaAcJobListGet) | **GET** /operando/bda/ac/job/list | 
[**operandoBdaAcJobStatusGet**](DefaultApi.md#operandoBdaAcJobStatusGet) | **GET** /operando/bda/ac/job/status | 
[**operandoBdaAcUserRightsAccessGet**](DefaultApi.md#operandoBdaAcUserRightsAccessGet) | **GET** /operando/bda/ac/user/rights/access | 
[**operandoBdaAcUserRightsExecutionGet**](DefaultApi.md#operandoBdaAcUserRightsExecutionGet) | **GET** /operando/bda/ac/user/rights/execution | 
[**operandoBdaAcUserSubscribedGet**](DefaultApi.md#operandoBdaAcUserSubscribedGet) | **GET** /operando/bda/ac/user/subscribed | 


<a name="operandoBdaAcEventChangeGet"></a>
# **operandoBdaAcEventChangeGet**
> operandoBdaAcEventChangeGet(requestHeader, jobId)



It returns the list of jobs subscribed for that user

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
RequestHeader requestHeader = new RequestHeader(); // RequestHeader | Identification of the requesting end user
String jobId = "jobId_example"; // String | Identification of the job to get the status about
try {
    apiInstance.operandoBdaAcEventChangeGet(requestHeader, jobId);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#operandoBdaAcEventChangeGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestHeader** | [**RequestHeader**](RequestHeader.md)| Identification of the requesting end user |
 **jobId** | **String**| Identification of the job to get the status about |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="operandoBdaAcJobChangeGet"></a>
# **operandoBdaAcJobChangeGet**
> operandoBdaAcJobChangeGet(requestHeader, jobId)



It returns the list of jobs subscribed for that user

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
RequestHeader requestHeader = new RequestHeader(); // RequestHeader | Identification of the requesting end user
String jobId = "jobId_example"; // String | Identification of the job to get the status about
try {
    apiInstance.operandoBdaAcJobChangeGet(requestHeader, jobId);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#operandoBdaAcJobChangeGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestHeader** | [**RequestHeader**](RequestHeader.md)| Identification of the requesting end user |
 **jobId** | **String**| Identification of the job to get the status about |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="operandoBdaAcJobInfoLoadGet"></a>
# **operandoBdaAcJobInfoLoadGet**
> operandoBdaAcJobInfoLoadGet(requestHeader, jobId)



It forces the information loading for a given job

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
RequestHeader requestHeader = new RequestHeader(); // RequestHeader | Identification of the requesting end user
String jobId = "jobId_example"; // String | Identification of the job to get the status about
try {
    apiInstance.operandoBdaAcJobInfoLoadGet(requestHeader, jobId);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#operandoBdaAcJobInfoLoadGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestHeader** | [**RequestHeader**](RequestHeader.md)| Identification of the requesting end user |
 **jobId** | **String**| Identification of the job to get the status about |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="operandoBdaAcJobInfoUpdateGet"></a>
# **operandoBdaAcJobInfoUpdateGet**
> operandoBdaAcJobInfoUpdateGet(requestHeader, jobId)



It forces the information updating for a given job, it only load last updates

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
RequestHeader requestHeader = new RequestHeader(); // RequestHeader | Identification of the requesting end user
String jobId = "jobId_example"; // String | Identification of the job to get the status about
try {
    apiInstance.operandoBdaAcJobInfoUpdateGet(requestHeader, jobId);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#operandoBdaAcJobInfoUpdateGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestHeader** | [**RequestHeader**](RequestHeader.md)| Identification of the requesting end user |
 **jobId** | **String**| Identification of the job to get the status about |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="operandoBdaAcJobListGet"></a>
# **operandoBdaAcJobListGet**
> operandoBdaAcJobListGet(requestHeader, jobId)



It returns the list of jobs available for that user

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
RequestHeader requestHeader = new RequestHeader(); // RequestHeader | Identification of the requesting end user
String jobId = "jobId_example"; // String | Identification of the job to get the status about
try {
    apiInstance.operandoBdaAcJobListGet(requestHeader, jobId);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#operandoBdaAcJobListGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestHeader** | [**RequestHeader**](RequestHeader.md)| Identification of the requesting end user |
 **jobId** | **String**| Identification of the job to get the status about |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="operandoBdaAcJobStatusGet"></a>
# **operandoBdaAcJobStatusGet**
> operandoBdaAcJobStatusGet(requestHeader, jobId)



Allows to get the status of a job

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
RequestHeader requestHeader = new RequestHeader(); // RequestHeader | Identification of the requesting end user
String jobId = "jobId_example"; // String | Identification of the job to get the status about
try {
    apiInstance.operandoBdaAcJobStatusGet(requestHeader, jobId);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#operandoBdaAcJobStatusGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestHeader** | [**RequestHeader**](RequestHeader.md)| Identification of the requesting end user |
 **jobId** | **String**| Identification of the job to get the status about |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="operandoBdaAcUserRightsAccessGet"></a>
# **operandoBdaAcUserRightsAccessGet**
> operandoBdaAcUserRightsAccessGet(requestHeader, jobId)



It returns the list of jobs subscribed for that user

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
RequestHeader requestHeader = new RequestHeader(); // RequestHeader | Identification of the requesting end user
String jobId = "jobId_example"; // String | Identification of the job to get the status about
try {
    apiInstance.operandoBdaAcUserRightsAccessGet(requestHeader, jobId);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#operandoBdaAcUserRightsAccessGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestHeader** | [**RequestHeader**](RequestHeader.md)| Identification of the requesting end user |
 **jobId** | **String**| Identification of the job to get the status about |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="operandoBdaAcUserRightsExecutionGet"></a>
# **operandoBdaAcUserRightsExecutionGet**
> operandoBdaAcUserRightsExecutionGet(requestHeader, jobId)



It returns the list of jobs subscribed for that user

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
RequestHeader requestHeader = new RequestHeader(); // RequestHeader | Identification of the requesting end user
String jobId = "jobId_example"; // String | Identification of the job to get the status about
try {
    apiInstance.operandoBdaAcUserRightsExecutionGet(requestHeader, jobId);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#operandoBdaAcUserRightsExecutionGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestHeader** | [**RequestHeader**](RequestHeader.md)| Identification of the requesting end user |
 **jobId** | **String**| Identification of the job to get the status about |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="operandoBdaAcUserSubscribedGet"></a>
# **operandoBdaAcUserSubscribedGet**
> operandoBdaAcUserSubscribedGet(requestHeader, jobId)



It returns the list of jobs subscribed for that user

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
RequestHeader requestHeader = new RequestHeader(); // RequestHeader | Identification of the requesting end user
String jobId = "jobId_example"; // String | Identification of the job to get the status about
try {
    apiInstance.operandoBdaAcUserSubscribedGet(requestHeader, jobId);
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#operandoBdaAcUserSubscribedGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requestHeader** | [**RequestHeader**](RequestHeader.md)| Identification of the requesting end user |
 **jobId** | **String**| Identification of the job to get the status about |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

