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
import io.swagger.client.api.DELETEApi;

import java.io.File;
import java.util.*;

public class DELETEApiExample {

    public static void main(String[] args) {
        
        DELETEApi apiInstance = new DELETEApi();
        String ospId = "ospId_example"; // String | The identifier number of an OSP
        try {
            apiInstance.oSPOspIdDelete(ospId);
        } catch (ApiException e) {
            System.err.println("Exception when calling DELETEApi#oSPOspIdDelete");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *http://operando.eu/policy_database*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DELETEApi* | [**oSPOspIdDelete**](docs/DELETEApi.md#oSPOspIdDelete) | **DELETE** /OSP/{osp-id}/ | Remove the OSPRequest entry in the database.
*DELETEApi* | [**regulationsRegIdDelete**](docs/DELETEApi.md#regulationsRegIdDelete) | **DELETE** /regulations/{reg-id}/ | Remove the PrivacyRegulation entry in the database.
*DELETEApi* | [**userPrivacyPolicyUserIdDelete**](docs/DELETEApi.md#userPrivacyPolicyUserIdDelete) | **DELETE** /user_privacy_policy/{user-id}/ | Remove the UPP entry in the database for the user.
*GETApi* | [**oSPGet**](docs/GETApi.md#oSPGet) | **GET** /OSP/ | Perform a search query across the collection of OSP behaviour.
*GETApi* | [**oSPOspIdGet**](docs/GETApi.md#oSPOspIdGet) | **GET** /OSP/{osp-id}/ | Read a given OSP behaviour policy.
*GETApi* | [**oSPOspIdPrivacyPolicyGet**](docs/GETApi.md#oSPOspIdPrivacyPolicyGet) | **GET** /OSP/{osp-id}/privacy-policy/ | Get the current set of privacy policy statements about the usage of data for stated reasons.
*GETApi* | [**regulationsGet**](docs/GETApi.md#regulationsGet) | **GET** /regulations/ | Perform a search query across the collection of regulation.
*GETApi* | [**regulationsRegIdGet**](docs/GETApi.md#regulationsRegIdGet) | **GET** /regulations/{reg-id}/ | Read a given legislation with its ID.
*GETApi* | [**userPrivacyPolicyGet**](docs/GETApi.md#userPrivacyPolicyGet) | **GET** /user_privacy_policy/ | Perform a search query across the collection of UPPs.
*GETApi* | [**userPrivacyPolicyUserIdGet**](docs/GETApi.md#userPrivacyPolicyUserIdGet) | **GET** /user_privacy_policy/{user-id}/ | Read the user privacy policy for the given user id.
*LegislationApi* | [**regulationsGet**](docs/LegislationApi.md#regulationsGet) | **GET** /regulations/ | Perform a search query across the collection of regulation.
*LegislationApi* | [**regulationsPost**](docs/LegislationApi.md#regulationsPost) | **POST** /regulations/ | Create a new legislation entry in the database.
*LegislationApi* | [**regulationsRegIdDelete**](docs/LegislationApi.md#regulationsRegIdDelete) | **DELETE** /regulations/{reg-id}/ | Remove the PrivacyRegulation entry in the database.
*LegislationApi* | [**regulationsRegIdGet**](docs/LegislationApi.md#regulationsRegIdGet) | **GET** /regulations/{reg-id}/ | Read a given legislation with its ID.
*LegislationApi* | [**regulationsRegIdPut**](docs/LegislationApi.md#regulationsRegIdPut) | **PUT** /regulations/{reg-id}/ | Update PrivacyRegulation entry in the database.
*OSPApi* | [**oSPGet**](docs/OSPApi.md#oSPGet) | **GET** /OSP/ | Perform a search query across the collection of OSP behaviour.
*OSPApi* | [**oSPOspIdDelete**](docs/OSPApi.md#oSPOspIdDelete) | **DELETE** /OSP/{osp-id}/ | Remove the OSPRequest entry in the database.
*OSPApi* | [**oSPOspIdGet**](docs/OSPApi.md#oSPOspIdGet) | **GET** /OSP/{osp-id}/ | Read a given OSP behaviour policy.
*OSPApi* | [**oSPOspIdPrivacyPolicyGet**](docs/OSPApi.md#oSPOspIdPrivacyPolicyGet) | **GET** /OSP/{osp-id}/privacy-policy/ | Get the current set of privacy policy statements about the usage of data for stated reasons.
*OSPApi* | [**oSPOspIdPrivacyPolicyPut**](docs/OSPApi.md#oSPOspIdPrivacyPolicyPut) | **PUT** /OSP/{osp-id}/privacy-policy/ | Update OSP text policy entry in the database.
*OSPApi* | [**oSPOspIdPut**](docs/OSPApi.md#oSPOspIdPut) | **PUT** /OSP/{osp-id}/ | Update OSPBehaviour entry in the database.
*OSPApi* | [**oSPPost**](docs/OSPApi.md#oSPPost) | **POST** /OSP/ | Create a new OSP entry in the database.
*POSTApi* | [**oSPPost**](docs/POSTApi.md#oSPPost) | **POST** /OSP/ | Create a new OSP entry in the database.
*POSTApi* | [**regulationsPost**](docs/POSTApi.md#regulationsPost) | **POST** /regulations/ | Create a new legislation entry in the database.
*POSTApi* | [**userPrivacyPolicyPost**](docs/POSTApi.md#userPrivacyPolicyPost) | **POST** /user_privacy_policy/ | Create a new UPP entry in the database for the user.
*PUTApi* | [**oSPOspIdPrivacyPolicyPut**](docs/PUTApi.md#oSPOspIdPrivacyPolicyPut) | **PUT** /OSP/{osp-id}/privacy-policy/ | Update OSP text policy entry in the database.
*PUTApi* | [**oSPOspIdPut**](docs/PUTApi.md#oSPOspIdPut) | **PUT** /OSP/{osp-id}/ | Update OSPBehaviour entry in the database.
*PUTApi* | [**regulationsRegIdPut**](docs/PUTApi.md#regulationsRegIdPut) | **PUT** /regulations/{reg-id}/ | Update PrivacyRegulation entry in the database.
*PUTApi* | [**userPrivacyPolicyUserIdPut**](docs/PUTApi.md#userPrivacyPolicyUserIdPut) | **PUT** /user_privacy_policy/{user-id}/ | Update UPP entry in the database for the user.
*UPPApi* | [**userPrivacyPolicyGet**](docs/UPPApi.md#userPrivacyPolicyGet) | **GET** /user_privacy_policy/ | Perform a search query across the collection of UPPs.
*UPPApi* | [**userPrivacyPolicyPost**](docs/UPPApi.md#userPrivacyPolicyPost) | **POST** /user_privacy_policy/ | Create a new UPP entry in the database for the user.
*UPPApi* | [**userPrivacyPolicyUserIdDelete**](docs/UPPApi.md#userPrivacyPolicyUserIdDelete) | **DELETE** /user_privacy_policy/{user-id}/ | Remove the UPP entry in the database for the user.
*UPPApi* | [**userPrivacyPolicyUserIdGet**](docs/UPPApi.md#userPrivacyPolicyUserIdGet) | **GET** /user_privacy_policy/{user-id}/ | Read the user privacy policy for the given user id.
*UPPApi* | [**userPrivacyPolicyUserIdPut**](docs/UPPApi.md#userPrivacyPolicyUserIdPut) | **PUT** /user_privacy_policy/{user-id}/ | Update UPP entry in the database for the user.


## Documentation for Models

 - [AccessPolicy](docs/AccessPolicy.md)
 - [AccessReason](docs/AccessReason.md)
 - [OSPConsents](docs/OSPConsents.md)
 - [OSPDataRequest](docs/OSPDataRequest.md)
 - [OSPPrivacyPolicy](docs/OSPPrivacyPolicy.md)
 - [OSPPrivacyPolicyInput](docs/OSPPrivacyPolicyInput.md)
 - [OSPReasonPolicy](docs/OSPReasonPolicy.md)
 - [OSPReasonPolicyInput](docs/OSPReasonPolicyInput.md)
 - [OSPSettings](docs/OSPSettings.md)
 - [PrivacyRegulation](docs/PrivacyRegulation.md)
 - [PrivacyRegulationInput](docs/PrivacyRegulationInput.md)
 - [UserPreference](docs/UserPreference.md)
 - [UserPrivacyPolicy](docs/UserPrivacyPolicy.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issue.

## Author

support@operando.eu

