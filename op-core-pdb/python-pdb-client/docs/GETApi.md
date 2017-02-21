# swagger_client.GETApi

All URIs are relative to *http://operando.eu/policy_database*

Method | HTTP request | Description
------------- | ------------- | -------------
[**o_sp_get**](GETApi.md#o_sp_get) | **GET** /OSP/ | Perform a search query across the collection of OSP behaviour.
[**o_sp_osp_id_get**](GETApi.md#o_sp_osp_id_get) | **GET** /OSP/{osp-id}/ | Read a given OSP behaviour policy.
[**o_sp_osp_id_privacy_policy_access_reasons_get**](GETApi.md#o_sp_osp_id_privacy_policy_access_reasons_get) | **GET** /OSP/{osp-id}/privacy-policy/access-reasons | Get the list of access reason policy statements.
[**o_sp_osp_id_privacy_policy_get**](GETApi.md#o_sp_osp_id_privacy_policy_get) | **GET** /OSP/{osp-id}/privacy-policy/ | Get the current set of privacy policy statements about the usage of data for stated reasons.
[**regulations_get**](GETApi.md#regulations_get) | **GET** /regulations/ | Perform a search query across the collection of regulation.
[**regulations_reg_id_get**](GETApi.md#regulations_reg_id_get) | **GET** /regulations/{reg-id}/ | Read a given legislation with its ID.
[**user_privacy_policy_get**](GETApi.md#user_privacy_policy_get) | **GET** /user_privacy_policy/ | Perform a search query across the collection of UPPs.
[**user_privacy_policy_user_id_get**](GETApi.md#user_privacy_policy_user_id_get) | **GET** /user_privacy_policy/{user-id}/ | Read the user privacy policy for the given user id.


# **o_sp_get**
> list[OSPPrivacyPolicy] o_sp_get(filter)

Perform a search query across the collection of OSP behaviour.

The query contains a json object (names, values) and the request returns the list of documents (regulations) where the filter matches i.e. the document contains fields with those values. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.GETApi()
filter = 'filter_example' # str | The query filter to be matched - ?filter={json description}

try: 
    # Perform a search query across the collection of OSP behaviour.
    api_response = api_instance.o_sp_get(filter)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling GETApi->o_sp_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | **str**| The query filter to be matched - ?filter&#x3D;{json description} | 

### Return type

[**list[OSPPrivacyPolicy]**](OSPPrivacyPolicy.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **o_sp_osp_id_get**
> OSPPrivacyPolicy o_sp_osp_id_get(osp_id)

Read a given OSP behaviour policy.

Get a specific OSP document via the id. This will return the full OSP document in json format. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.GETApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP

try: 
    # Read a given OSP behaviour policy.
    api_response = api_instance.o_sp_osp_id_get(osp_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling GETApi->o_sp_osp_id_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **osp_id** | **str**| The identifier number of an OSP | 

### Return type

[**OSPPrivacyPolicy**](OSPPrivacyPolicy.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **o_sp_osp_id_privacy_policy_access_reasons_get**
> list[OSPReasonPolicy] o_sp_osp_id_privacy_policy_access_reasons_get(osp_id)

Get the list of access reason policy statements.

List of policy statements.  

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.GETApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP

try: 
    # Get the list of access reason policy statements.
    api_response = api_instance.o_sp_osp_id_privacy_policy_access_reasons_get(osp_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling GETApi->o_sp_osp_id_privacy_policy_access_reasons_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **osp_id** | **str**| The identifier number of an OSP | 

### Return type

[**list[OSPReasonPolicy]**](OSPReasonPolicy.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **o_sp_osp_id_privacy_policy_get**
> OSPReasonPolicy o_sp_osp_id_privacy_policy_get(osp_id)

Get the current set of privacy policy statements about the usage of data for stated reasons.

This is a machine readable version of a G2C privacy policy statement entered using the OSP Admin dashboard; and retrieved by both the OSP & PSP analyst dashboard for display purposes and also by the OSE component for checking regulation compliance.  

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.GETApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP

try: 
    # Get the current set of privacy policy statements about the usage of data for stated reasons.
    api_response = api_instance.o_sp_osp_id_privacy_policy_get(osp_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling GETApi->o_sp_osp_id_privacy_policy_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **osp_id** | **str**| The identifier number of an OSP | 

### Return type

[**OSPReasonPolicy**](OSPReasonPolicy.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

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
api_instance = swagger_client.GETApi()
filter = 'filter_example' # str | The query filter to be matched - ?filter={json description}

try: 
    # Perform a search query across the collection of regulation.
    api_response = api_instance.regulations_get(filter)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling GETApi->regulations_get: %s\n" % e)
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
api_instance = swagger_client.GETApi()
reg_id = 'reg_id_example' # str | The identifier number of a regulation

try: 
    # Read a given legislation with its ID.
    api_response = api_instance.regulations_reg_id_get(reg_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling GETApi->regulations_reg_id_get: %s\n" % e)
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

# **user_privacy_policy_get**
> list[UserPrivacyPolicy] user_privacy_policy_get(filter)

Perform a search query across the collection of UPPs.

The query contains a json object (names, values) and the request returns the list of documents (UPPs) where the filter matches i.e. each document contains fields with those values. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.GETApi()
filter = 'filter_example' # str | The query filter to be matched - ?filter={json description}

try: 
    # Perform a search query across the collection of UPPs.
    api_response = api_instance.user_privacy_policy_get(filter)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling GETApi->user_privacy_policy_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **filter** | **str**| The query filter to be matched - ?filter&#x3D;{json description} | 

### Return type

[**list[UserPrivacyPolicy]**](UserPrivacyPolicy.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **user_privacy_policy_user_id_get**
> UserPrivacyPolicy user_privacy_policy_user_id_get(user_id)

Read the user privacy policy for the given user id.

Get a specific UPP document via the user's id. This will return the full user privacy policy document in json format. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.GETApi()
user_id = 'user_id_example' # str | The user identifier number

try: 
    # Read the user privacy policy for the given user id.
    api_response = api_instance.user_privacy_policy_user_id_get(user_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling GETApi->user_privacy_policy_user_id_get: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **user_id** | **str**| The user identifier number | 

### Return type

[**UserPrivacyPolicy**](UserPrivacyPolicy.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

