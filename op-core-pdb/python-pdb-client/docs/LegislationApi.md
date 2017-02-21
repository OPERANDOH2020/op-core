# swagger_client.LegislationApi

All URIs are relative to *http://operando.eu/policy_database*

Method | HTTP request | Description
------------- | ------------- | -------------
[**regulations_get**](LegislationApi.md#regulations_get) | **GET** /regulations/ | Perform a search query across the collection of regulation.
[**regulations_post**](LegislationApi.md#regulations_post) | **POST** /regulations/ | Create a new legislation entry in the database.
[**regulations_reg_id_delete**](LegislationApi.md#regulations_reg_id_delete) | **DELETE** /regulations/{reg-id}/ | Remove the PrivacyRegulation entry in the database.
[**regulations_reg_id_get**](LegislationApi.md#regulations_reg_id_get) | **GET** /regulations/{reg-id}/ | Read a given legislation with its ID.
[**regulations_reg_id_put**](LegislationApi.md#regulations_reg_id_put) | **PUT** /regulations/{reg-id}/ | Update PrivacyRegulation entry in the database.


# **regulations_get**
> list[PrivacyRegulation] regulations_get(filter)

Perform a search query across the collection of regulation.

The query contains a json object (names, values) and the request returns  the list of documents (regulations) where the filter matches i.e. the  document contains fields with those values. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.LegislationApi()
filter = 'filter_example' # str | The query filter to be matched - ?filter={json description}

try: 
    # Perform a search query across the collection of regulation.
    api_response = api_instance.regulations_get(filter)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling LegislationApi->regulations_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | **str**| The query filter to be matched - ?filter&#x3D;{json description} | 

### Return type

[**list[PrivacyRegulation]**](PrivacyRegulation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **regulations_post**
> PrivacyRegulation regulations_post(regulation)

Create a new legislation entry in the database.

Called by the policy computation component when a new regulation is added to OPERANDO. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.LegislationApi()
regulation = swagger_client.PrivacyRegulationInput() # PrivacyRegulationInput | The first instance of this regulation document

try: 
    # Create a new legislation entry in the database.
    api_response = api_instance.regulations_post(regulation)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling LegislationApi->regulations_post: %s\n" % e)
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

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **regulations_reg_id_delete**
> regulations_reg_id_delete(reg_id)

Remove the PrivacyRegulation entry in the database.

Called when by the policy computation component when the regulator api requests that the regulation be deleted. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.LegislationApi()
reg_id = 'reg_id_example' # str | The identifier number of a regulation

try: 
    # Remove the PrivacyRegulation entry in the database.
    api_instance.regulations_reg_id_delete(reg_id)
except ApiException as e:
    print("Exception when calling LegislationApi->regulations_reg_id_delete: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **reg_id** | **str**| The identifier number of a regulation | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **regulations_reg_id_get**
> PrivacyRegulation regulations_reg_id_get(reg_id)

Read a given legislation with its ID.

Get a specific legislation document via the id. This will return the  full legislation document in json format. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.LegislationApi()
reg_id = 'reg_id_example' # str | The identifier number of a regulation

try: 
    # Read a given legislation with its ID.
    api_response = api_instance.regulations_reg_id_get(reg_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling LegislationApi->regulations_reg_id_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **reg_id** | **str**| The identifier number of a regulation | 

### Return type

[**PrivacyRegulation**](PrivacyRegulation.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **regulations_reg_id_put**
> regulations_reg_id_put(reg_id, regulation)

Update PrivacyRegulation entry in the database.

Called when by the policy computation component when the regulator api updates a regulation. Their new PrivacyRegulation document is stored in the policy DB. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.LegislationApi()
reg_id = 'reg_id_example' # str | The identifier number of a regulation
regulation = swagger_client.PrivacyRegulationInput() # PrivacyRegulationInput | The changed instance of this PrivacyRegulation

try: 
    # Update PrivacyRegulation entry in the database.
    api_instance.regulations_reg_id_put(reg_id, regulation)
except ApiException as e:
    print("Exception when calling LegislationApi->regulations_reg_id_put: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **reg_id** | **str**| The identifier number of a regulation | 
 **regulation** | [**PrivacyRegulationInput**](PrivacyRegulationInput.md)| The changed instance of this PrivacyRegulation | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

