# swagger_client.OSPApi

All URIs are relative to *http://operando.eu/policy_database*

Method | HTTP request | Description
------------- | ------------- | -------------
[**o_sp_get**](OSPApi.md#o_sp_get) | **GET** /OSP/ | Perform a search query across the collection of OSP behaviour.
[**o_sp_osp_id_delete**](OSPApi.md#o_sp_osp_id_delete) | **DELETE** /OSP/{osp-id}/ | Remove the OSPRequest entry in the database.
[**o_sp_osp_id_get**](OSPApi.md#o_sp_osp_id_get) | **GET** /OSP/{osp-id}/ | Read a given OSP behaviour policy.
[**o_sp_osp_id_privacy_policy_access_reasons_get**](OSPApi.md#o_sp_osp_id_privacy_policy_access_reasons_get) | **GET** /OSP/{osp-id}/privacy-policy/access-reasons | Get the list of access reason policy statements.
[**o_sp_osp_id_privacy_policy_access_reasons_post**](OSPApi.md#o_sp_osp_id_privacy_policy_access_reasons_post) | **POST** /OSP/{osp-id}/privacy-policy/access-reasons | Create a new access reason statement in the privacy policy.
[**o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete**](OSPApi.md#o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete) | **DELETE** /OSP/{osp-id}/privacy-policy/access-reasons/{reason-id} | Remove the AccessReason entry in the list.
[**o_sp_osp_id_privacy_policy_access_reasons_reason_id_put**](OSPApi.md#o_sp_osp_id_privacy_policy_access_reasons_reason_id_put) | **PUT** /OSP/{osp-id}/privacy-policy/access-reasons/{reason-id} | Update an access reason statement in the privacy policy.
[**o_sp_osp_id_privacy_policy_get**](OSPApi.md#o_sp_osp_id_privacy_policy_get) | **GET** /OSP/{osp-id}/privacy-policy/ | Get the current set of privacy policy statements about the usage of data for stated reasons.
[**o_sp_osp_id_privacy_policy_put**](OSPApi.md#o_sp_osp_id_privacy_policy_put) | **PUT** /OSP/{osp-id}/privacy-policy/ | Update OSP text policy entry in the database.
[**o_sp_osp_id_put**](OSPApi.md#o_sp_osp_id_put) | **PUT** /OSP/{osp-id}/ | Update OSPBehaviour entry in the database.
[**o_sp_post**](OSPApi.md#o_sp_post) | **POST** /OSP/ | Create a new OSP entry in the database.


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
api_instance = swagger_client.OSPApi()
filter = 'filter_example' # str | The query filter to be matched - ?filter={json description}

try: 
    # Perform a search query across the collection of OSP behaviour.
    api_response = api_instance.o_sp_get(filter)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling OSPApi->o_sp_get: %s\n" % e)
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

# **o_sp_osp_id_delete**
> o_sp_osp_id_delete(osp_id)

Remove the OSPRequest entry in the database.

Called when by the policy computation component when the regulator api requests that the regulation be deleted. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.OSPApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP

try: 
    # Remove the OSPRequest entry in the database.
    api_instance.o_sp_osp_id_delete(osp_id)
except ApiException as e:
    print("Exception when calling OSPApi->o_sp_osp_id_delete: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **osp_id** | **str**| The identifier number of an OSP | 

### Return type

void (empty response body)

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
api_instance = swagger_client.OSPApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP

try: 
    # Read a given OSP behaviour policy.
    api_response = api_instance.o_sp_osp_id_get(osp_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling OSPApi->o_sp_osp_id_get: %s\n" % e)
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
api_instance = swagger_client.OSPApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP

try: 
    # Get the list of access reason policy statements.
    api_response = api_instance.o_sp_osp_id_privacy_policy_access_reasons_get(osp_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling OSPApi->o_sp_osp_id_privacy_policy_access_reasons_get: %s\n" % e)
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

# **o_sp_osp_id_privacy_policy_access_reasons_post**
> o_sp_osp_id_privacy_policy_access_reasons_post(osp_id, osp_policy)

Create a new access reason statement in the privacy policy.

Called by the UI when OSP updating the policy statements 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.OSPApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP
osp_policy = swagger_client.AccessReason() # AccessReason | The first instance of this new statement.

try: 
    # Create a new access reason statement in the privacy policy.
    api_instance.o_sp_osp_id_privacy_policy_access_reasons_post(osp_id, osp_policy)
except ApiException as e:
    print("Exception when calling OSPApi->o_sp_osp_id_privacy_policy_access_reasons_post: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **osp_id** | **str**| The identifier number of an OSP | 
 **osp_policy** | [**AccessReason**](AccessReason.md)| The first instance of this new statement. | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete**
> o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete(osp_id, reason_id)

Remove the AccessReason entry in the list.

Remove an identified value. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.OSPApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP
reason_id = 'reason_id_example' # str | The identifier of a statement in a policy, is only unique to the policy.

try: 
    # Remove the AccessReason entry in the list.
    api_instance.o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete(osp_id, reason_id)
except ApiException as e:
    print("Exception when calling OSPApi->o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **osp_id** | **str**| The identifier number of an OSP | 
 **reason_id** | **str**| The identifier of a statement in a policy, is only unique to the policy. | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **o_sp_osp_id_privacy_policy_access_reasons_reason_id_put**
> o_sp_osp_id_privacy_policy_access_reasons_reason_id_put(osp_id, reason_id, osp_policy)

Update an access reason statement in the privacy policy.

Called by the UI when OSP updating the policy statements 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.OSPApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP
reason_id = 'reason_id_example' # str | The identifier of a statement in a policy, is only unique to the policy.
osp_policy = swagger_client.AccessReason() # AccessReason | The updated instance of this OSP policy statement.

try: 
    # Update an access reason statement in the privacy policy.
    api_instance.o_sp_osp_id_privacy_policy_access_reasons_reason_id_put(osp_id, reason_id, osp_policy)
except ApiException as e:
    print("Exception when calling OSPApi->o_sp_osp_id_privacy_policy_access_reasons_reason_id_put: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **osp_id** | **str**| The identifier number of an OSP | 
 **reason_id** | **str**| The identifier of a statement in a policy, is only unique to the policy. | 
 **osp_policy** | [**AccessReason**](AccessReason.md)| The updated instance of this OSP policy statement. | 

### Return type

void (empty response body)

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
api_instance = swagger_client.OSPApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP

try: 
    # Get the current set of privacy policy statements about the usage of data for stated reasons.
    api_response = api_instance.o_sp_osp_id_privacy_policy_get(osp_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling OSPApi->o_sp_osp_id_privacy_policy_get: %s\n" % e)
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

# **o_sp_osp_id_privacy_policy_put**
> o_sp_osp_id_privacy_policy_put(osp_id, osp_policy)

Update OSP text policy entry in the database.

Called when by the watchdog detects a change in the textual policy and wants to update the current policy. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.OSPApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP
osp_policy = swagger_client.OSPReasonPolicyInput() # OSPReasonPolicyInput | The changed instance of this OSPRequest

try: 
    # Update OSP text policy entry in the database.
    api_instance.o_sp_osp_id_privacy_policy_put(osp_id, osp_policy)
except ApiException as e:
    print("Exception when calling OSPApi->o_sp_osp_id_privacy_policy_put: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **osp_id** | **str**| The identifier number of an OSP | 
 **osp_policy** | [**OSPReasonPolicyInput**](OSPReasonPolicyInput.md)| The changed instance of this OSPRequest | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **o_sp_osp_id_put**
> o_sp_osp_id_put(osp_id, osp_policy)

Update OSPBehaviour entry in the database.

Called when by the policy computation component when the regulator api updates a regulation. Their new OSPRequest document is stored in the policy DB. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.OSPApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP
osp_policy = swagger_client.OSPPrivacyPolicyInput() # OSPPrivacyPolicyInput | The changed instance of this OSPRequest

try: 
    # Update OSPBehaviour entry in the database.
    api_instance.o_sp_osp_id_put(osp_id, osp_policy)
except ApiException as e:
    print("Exception when calling OSPApi->o_sp_osp_id_put: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **osp_id** | **str**| The identifier number of an OSP | 
 **osp_policy** | [**OSPPrivacyPolicyInput**](OSPPrivacyPolicyInput.md)| The changed instance of this OSPRequest | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **o_sp_post**
> str o_sp_post(osp_policy)

Create a new OSP entry in the database.

Called by the policy computation component when a new regulation is added to OPERANDO. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.OSPApi()
osp_policy = swagger_client.OSPPrivacyPolicyInput() # OSPPrivacyPolicyInput | The first instance of this OSP document

try: 
    # Create a new OSP entry in the database.
    api_response = api_instance.o_sp_post(osp_policy)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling OSPApi->o_sp_post: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **osp_policy** | [**OSPPrivacyPolicyInput**](OSPPrivacyPolicyInput.md)| The first instance of this OSP document | 

### Return type

**str**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

