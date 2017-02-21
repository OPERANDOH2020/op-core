# swagger_client.UPPApi

All URIs are relative to *http://operando.eu/policy_database*

Method | HTTP request | Description
------------- | ------------- | -------------
[**user_privacy_policy_get**](UPPApi.md#user_privacy_policy_get) | **GET** /user_privacy_policy/ | Perform a search query across the collection of UPPs.
[**user_privacy_policy_post**](UPPApi.md#user_privacy_policy_post) | **POST** /user_privacy_policy/ | Create a new UPP entry in the database for the user.
[**user_privacy_policy_user_id_delete**](UPPApi.md#user_privacy_policy_user_id_delete) | **DELETE** /user_privacy_policy/{user-id}/ | Remove the UPP entry in the database for the user.
[**user_privacy_policy_user_id_get**](UPPApi.md#user_privacy_policy_user_id_get) | **GET** /user_privacy_policy/{user-id}/ | Read the user privacy policy for the given user id.
[**user_privacy_policy_user_id_put**](UPPApi.md#user_privacy_policy_user_id_put) | **PUT** /user_privacy_policy/{user-id}/ | Update UPP entry in the database for the user.


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
api_instance = swagger_client.UPPApi()
filter = 'filter_example' # str | The query filter to be matched - ?filter={json description}

try: 
    # Perform a search query across the collection of UPPs.
    api_response = api_instance.user_privacy_policy_get(filter)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling UPPApi->user_privacy_policy_get: %s\n" % e)
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
api_instance = swagger_client.UPPApi()
upp = swagger_client.UserPrivacyPolicy() # UserPrivacyPolicy | The first instance of this user's UPP

try: 
    # Create a new UPP entry in the database for the user.
    api_response = api_instance.user_privacy_policy_post(upp)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling UPPApi->user_privacy_policy_post: %s\n" % e)
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
api_instance = swagger_client.UPPApi()
user_id = 'user_id_example' # str | The user identifier number

try: 
    # Remove the UPP entry in the database for the user.
    api_instance.user_privacy_policy_user_id_delete(user_id)
except ApiException as e:
    print("Exception when calling UPPApi->user_privacy_policy_user_id_delete: %s\n" % e)
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
api_instance = swagger_client.UPPApi()
user_id = 'user_id_example' # str | The user identifier number

try: 
    # Read the user privacy policy for the given user id.
    api_response = api_instance.user_privacy_policy_user_id_get(user_id)
    pprint(api_response)
except ApiException as e:
    print("Exception when calling UPPApi->user_privacy_policy_user_id_get: %s\n" % e)
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
api_instance = swagger_client.UPPApi()
user_id = 'user_id_example' # str | The user identifier number
upp = swagger_client.UserPrivacyPolicy() # UserPrivacyPolicy | The changed instance of this user's UPP

try: 
    # Update UPP entry in the database for the user.
    api_instance.user_privacy_policy_user_id_put(user_id, upp)
except ApiException as e:
    print("Exception when calling UPPApi->user_privacy_policy_user_id_put: %s\n" % e)
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

