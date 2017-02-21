# swagger_client.POSTApi

All URIs are relative to *http://operando.eu/policy_database*

Method | HTTP request | Description
------------- | ------------- | -------------
[**o_sp_osp_id_privacy_policy_access_reasons_post**](POSTApi.md#o_sp_osp_id_privacy_policy_access_reasons_post) | **POST** /OSP/{osp-id}/privacy-policy/access-reasons | Create a new access reason statement in the privacy policy.
[**o_sp_osp_id_privacy_policy_access_reasons_reason_id_put**](POSTApi.md#o_sp_osp_id_privacy_policy_access_reasons_reason_id_put) | **PUT** /OSP/{osp-id}/privacy-policy/access-reasons/{reason-id} | Update an access reason statement in the privacy policy.
[**o_sp_post**](POSTApi.md#o_sp_post) | **POST** /OSP/ | Create a new OSP entry in the database.
[**regulations_post**](POSTApi.md#regulations_post) | **POST** /regulations/ | Create a new legislation entry in the database.
[**user_privacy_policy_post**](POSTApi.md#user_privacy_policy_post) | **POST** /user_privacy_policy/ | Create a new UPP entry in the database for the user.


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
api_instance = swagger_client.POSTApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP
osp_policy = swagger_client.AccessReason() # AccessReason | The first instance of this new statement.

try: 
    # Create a new access reason statement in the privacy policy.
    api_instance.o_sp_osp_id_privacy_policy_access_reasons_post(osp_id, osp_policy)
except ApiException as e:
    print("Exception when calling POSTApi->o_sp_osp_id_privacy_policy_access_reasons_post: %s\n" % e)
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
api_instance = swagger_client.POSTApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP
reason_id = 'reason_id_example' # str | The identifier of a statement in a policy, is only unique to the policy.
osp_policy = swagger_client.AccessReason() # AccessReason | The updated instance of this OSP policy statement.

try: 
    # Update an access reason statement in the privacy policy.
    api_instance.o_sp_osp_id_privacy_policy_access_reasons_reason_id_put(osp_id, reason_id, osp_policy)
except ApiException as e:
    print("Exception when calling POSTApi->o_sp_osp_id_privacy_policy_access_reasons_reason_id_put: %s\n" % e)
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
api_instance = swagger_client.POSTApi()
osp_policy = swagger_client.OSPPrivacyPolicyInput() # OSPPrivacyPolicyInput | The first instance of this OSP document

try: 
    # Create a new OSP entry in the database.
    api_response = api_instance.o_sp_post(osp_policy)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling POSTApi->o_sp_post: %s\n" % e)
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
api_instance = swagger_client.POSTApi()
regulation = swagger_client.PrivacyRegulationInput() # PrivacyRegulationInput | The first instance of this regulation document

try: 
    # Create a new legislation entry in the database.
    api_response = api_instance.regulations_post(regulation)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling POSTApi->regulations_post: %s\n" % e)
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

# **user_privacy_policy_post**
> str user_privacy_policy_post(upp)

Create a new UPP entry in the database for the user.

Called when a new user is registered with operando. Their new privacy preferences are passed in the UPP document and stored in the policy DB. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.POSTApi()
upp = swagger_client.UserPrivacyPolicy() # UserPrivacyPolicy | The first instance of this user's UPP

try: 
    # Create a new UPP entry in the database for the user.
    api_response = api_instance.user_privacy_policy_post(upp)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling POSTApi->user_privacy_policy_post: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **upp** | [**UserPrivacyPolicy**](UserPrivacyPolicy.md)| The first instance of this user&#39;s UPP | 

### Return type

**str**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

