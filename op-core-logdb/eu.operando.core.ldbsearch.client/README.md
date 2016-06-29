# swagger-java-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-java-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-java-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.LogSearchApi;

import java.io.File;
import java.util.*;

public class LogSearchApiExample {

    public static void main(String[] args) {
        
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
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://localhost:8080/operando/core/logdbsearch*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*LogSearchApi* | [**getLogs**](docs/LogSearchApi.md#getLogs) | **GET** /log/search | Search logs in database


## Documentation for Models

 - [Error](docs/Error.md)
 - [InlineResponse200](docs/InlineResponse200.md)
 - [LogResponse](docs/LogResponse.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author



