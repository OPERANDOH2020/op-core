# LogSearchApi

All URIs are relative to *http://localhost:8080/operando/core/logdbsearch*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getLogs**](LogSearchApi.md#getLogs) | **GET** /log/search | Search logs in database


<a name="getLogs"></a>
# **getLogs**
> InlineResponse200 getLogs(dateFrom, dateTo, logLevel, requesterType, requesterId, logPriority, title, keyWords)

Search logs in database

Search logs in database by specifying a filter.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.LogSearchApi;


LogSearchApi apiInstance = new LogSearchApi();
String dateFrom = "dateFrom_example"; // String | Date from which wanted to be recovered the logs.
String dateTo = "dateTo_example"; // String | Date to which wanted to be recovered the logs.
String logLevel = "logLevel_example"; // String | Log level wanted to be recovered.
String requesterType = "requesterType_example"; // String | Type of the requester that originated the log entry.
String requesterId = "requesterId_example"; // String | Id of the requester that originated the log entry.
String logPriority = "logPriority_example"; // String | Priority of the log messages to be recovered.
String title = "title_example"; // String | Title of the log messages to be recovered.
List<String> keyWords = Arrays.asList("keyWords_example"); // List<String> | Keywords to perform the search.
try {
    InlineResponse200 result = apiInstance.getLogs(dateFrom, dateTo, logLevel, requesterType, requesterId, logPriority, title, keyWords);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling LogSearchApi#getLogs");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **dateFrom** | **String**| Date from which wanted to be recovered the logs. | [optional]
 **dateTo** | **String**| Date to which wanted to be recovered the logs. | [optional]
 **logLevel** | **String**| Log level wanted to be recovered. | [optional]
 **requesterType** | **String**| Type of the requester that originated the log entry. | [optional]
 **requesterId** | **String**| Id of the requester that originated the log entry. | [optional]
 **logPriority** | **String**| Priority of the log messages to be recovered. | [optional]
 **title** | **String**| Title of the log messages to be recovered. | [optional]
 **keyWords** | [**List&lt;String&gt;**](String.md)| Keywords to perform the search. | [optional]

### Return type

[**InlineResponse200**](InlineResponse200.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

