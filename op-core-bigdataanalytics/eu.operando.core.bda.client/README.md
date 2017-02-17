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
import io.swagger.client.api.DefaultApi;

import java.io.File;
import java.util.*;

public class DefaultApiExample {

    public static void main(String[] args) {
        
        DefaultApi apiInstance = new DefaultApi();
        RequestHeader requestHeader = new RequestHeader(); // RequestHeader | Identification of the requesting end user
        String jobId = "jobId_example"; // String | Identification of the job to get the status about
        try {
            apiInstance.operandoBdaAcEventChangeGet(requestHeader, jobId);
        } catch (ApiException e) {
            System.err.println("Exception when calling DefaultApi#operandoBdaAcEventChangeGet");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://localhost:8080*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DefaultApi* | [**operandoBdaAcEventChangeGet**](docs/DefaultApi.md#operandoBdaAcEventChangeGet) | **GET** /operando/bda/ac/event/change | 
*DefaultApi* | [**operandoBdaAcJobChangeGet**](docs/DefaultApi.md#operandoBdaAcJobChangeGet) | **GET** /operando/bda/ac/job/change | 
*DefaultApi* | [**operandoBdaAcJobInfoLoadGet**](docs/DefaultApi.md#operandoBdaAcJobInfoLoadGet) | **GET** /operando/bda/ac/job/info/load | 
*DefaultApi* | [**operandoBdaAcJobInfoUpdateGet**](docs/DefaultApi.md#operandoBdaAcJobInfoUpdateGet) | **GET** /operando/bda/ac/job/info/update | 
*DefaultApi* | [**operandoBdaAcJobListGet**](docs/DefaultApi.md#operandoBdaAcJobListGet) | **GET** /operando/bda/ac/job/list | 
*DefaultApi* | [**operandoBdaAcJobStatusGet**](docs/DefaultApi.md#operandoBdaAcJobStatusGet) | **GET** /operando/bda/ac/job/status | 
*DefaultApi* | [**operandoBdaAcUserRightsAccessGet**](docs/DefaultApi.md#operandoBdaAcUserRightsAccessGet) | **GET** /operando/bda/ac/user/rights/access | 
*DefaultApi* | [**operandoBdaAcUserRightsExecutionGet**](docs/DefaultApi.md#operandoBdaAcUserRightsExecutionGet) | **GET** /operando/bda/ac/user/rights/execution | 
*DefaultApi* | [**operandoBdaAcUserSubscribedGet**](docs/DefaultApi.md#operandoBdaAcUserSubscribedGet) | **GET** /operando/bda/ac/user/subscribed | 


## Documentation for Models

 - [RequestHeader](docs/RequestHeader.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author



