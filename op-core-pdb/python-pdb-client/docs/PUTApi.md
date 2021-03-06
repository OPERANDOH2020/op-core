# swagger_client.PUTApi

All URIs are relative to *http://operando.eu/policy_database*

Method | HTTP request | Description
------------- | ------------- | -------------
[**o_sp_osp_id_privacy_policy_put**](PUTApi.md#o_sp_osp_id_privacy_policy_put) | **PUT** /OSP/{osp-id}/privacy-policy/ | Update OSP text policy entry in the database.
[**o_sp_osp_id_put**](PUTApi.md#o_sp_osp_id_put) | **PUT** /OSP/{osp-id}/ | Update OSPBehaviour entry in the database.
[**regulations_reg_id_put**](PUTApi.md#regulations_reg_id_put) | **PUT** /regulations/{reg-id}/ | Update PrivacyRegulation entry in the database.
[**user_privacy_policy_user_id_put**](PUTApi.md#user_privacy_policy_user_id_put) | **PUT** /user_privacy_policy/{user-id}/ | Update UPP entry in the database for the user.


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
api_instance = swagger_client.PUTApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP
osp_policy = swagger_client.OSPReasonPolicyInput() # OSPReasonPolicyInput | The changed instance of this OSPRequest

try: 
    # Update OSP text policy entry in the database.
    api_instance.o_sp_osp_id_privacy_policy_put(osp_id, osp_policy)
except ApiException as e:
    print("Exception when calling PUTApi->o_sp_osp_id_privacy_policy_put: %s\n" % e)
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
api_instance = swagger_client.PUTApi()
osp_id = 'osp_id_example' # str | The identifier number of an OSP
osp_policy = swagger_client.OSPPrivacyPolicyInput() # OSPPrivacyPolicyInput | The changed instance of this OSPRequest

try: 
    # Update OSPBehaviour entry in the database.
    api_instance.o_sp_osp_id_put(osp_id, osp_policy)
except ApiException as e:
    print("Exception when calling PUTApi->o_sp_osp_id_put: %s\n" % e)
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
api_instance = swagger_client.PUTApi()
reg_id = 'reg_id_example' # str | The identifier number of a regulation
regulation = swagger_client.PrivacyRegulationInput() # PrivacyRegulationInput | The changed instance of this PrivacyRegulation

try: 
    # Update PrivacyRegulation entry in the database.
    api_instance.regulations_reg_id_put(reg_id, regulation)
except ApiException as e:
    print("Exception when calling PUTApi->regulations_reg_id_put: %s\n" % e)
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

# **user_privacy_policy_user_id_put**
> user_privacy_policy_user_id_put(user_id, upp)

Update UPP entry in the database for the user.

Called when a user makes a change to the UPP registered with operando. Their new privacy preferences are passed in the UPP document and stored in the policy DB. 

### Example 
```python
from __future__ import print_statement
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

# create an instance of the API class
api_instance = swagger_client.PUTApi()
user_id = 'user_id_example' # str | The user identifier number
upp = swagger_client.UserPrivacyPolicy() # UserPrivacyPolicy | The changed instance of this user's UPP

try: 
    # Update UPP entry in the database for the user.
    api_instance.user_privacy_policy_user_id_put(user_id, upp)
except ApiException as e:
    print("Exception when calling PUTApi->user_privacy_policy_user_id_put: %s\n" % e)
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **user_id** | **str**| The user identifier number | 
 **upp** | [**UserPrivacyPolicy**](UserPrivacyPolicy.md)| The changed instance of this user&#39;s UPP | 

### Return type

void (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

