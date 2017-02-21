# UserPrivacyPolicy

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**user_id** | **str** | The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP. | [optional] 
**user_preferences** | [**list[UserPreference]**](UserPreference.md) | User stated preferences (questionnaire answers) | [optional] 
**subscribed_osp_policies** | [**list[OSPConsents]**](OSPConsents.md) | The user policies for each OSP they subscribe to. | [optional] 
**subscribed_osp_settings** | [**list[OSPSettings]**](OSPSettings.md) | The user settings for each of their services | [optional] 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


