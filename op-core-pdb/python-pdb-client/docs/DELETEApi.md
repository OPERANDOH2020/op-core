# swagger_client.DELETEApi

All URIs are relative to *http://operando.eu/policy_database*

Method | HTTP request | Description
------------- | ------------- | -------------
[**o_sp_osp_id_delete**](DELETEApi.md#o_sp_osp_id_delete) | **DELETE** /OSP/{osp-id}/ | Remove the OSPRequest entry in the database.
[**o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete**](DELETEApi.md#o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete) | **DELETE** /OSP/{osp-id}/privacy-policy/access-reasons/{reason-id} | Remove the AccessReason entry in the list.
[**regulations_reg_id_delete**](DELETEApi.md#regulations_reg_id_delete) | **DELETE** /regulations/{reg-id}/ | Remove the PrivacyRegulation entry in the database.
[**user_privacy_policy_user_id_delete**](DELETEApi.md#user_privacy_policy_user_id_delete) | **DELETE** /user_privacy_policy/{user-id}/ | Remove the UPP entry in the database for the user.


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
api_instance = swagger_client.DELETEApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP

try: 
    # Remove the OSPRequest entry in the database.
    api_instance.o_sp_osp_id_delete(osp_id)
except ApiException as e:
    print("Exception when calling DELETEApi->o_sp_osp_id_delete: %s\n" % e)
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
api_instance = swagger_client.DELETEApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP
reason_id = 'reason_id_example' # str | The identifier of a statement in a policy, is only unique to the policy.

try: 
    # Remove the AccessReason entry in the list.
    api_instance.o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete(osp_id, reason_id)
except ApiException as e:
    print("Exception when calling DELETEApi->o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete: %s\n" % e)
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
api_instance = swagger_client.DELETEApi()
reg_id = 'reg_id_example' # str | The identifier number of a regulation

try: 
    # Remove the PrivacyRegulation entry in the database.
    api_instance.regulations_reg_id_delete(reg_id)
except ApiException as e:
    print("Exception when calling DELETEApi->regulations_reg_id_delete: %s\n" % e)
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

# **user_privacy_policy_user_id_delete**
> user_privacy_policy_user_id_delete(user_id)

Remove the UPP entry in the database for the user.

Called when a user leaves operando. Their privacy preferences and policies are deleted from the database. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.DELETEApi()
user_id = 'user_id_example' # str | The user identifier number

try: 
    # Remove the UPP entry in the database for the user.
    api_instance.user_privacy_policy_user_id_delete(user_id)
except ApiException as e:
    print("Exception when calling DELETEApi->user_privacy_policy_user_id_delete: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **user_id** | **str**| The user identifier number | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

