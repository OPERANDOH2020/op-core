# UserPreference

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**informationtype** | **str** | The type of private information; e.g. is it information that identifies the user (e.g. id number)? is it location information about the user? Is it about their activities?  | [optional] 
**category** | **str** | The category of the service invading the privacy of the user.  | [optional] 
**preference** | **str** | The user&#39;s privacy preference. High means they are sensitive to disclosing private information. Medium they have concerns; and low means they have few privacy concerns with this question.  | [optional] 
**role** | **str** | The role of a person or service that the private information is being disclosed to or used by. This is an optional parameter in the case where users drill down to more detailed preferences.  | [optional] 
**action** | **str** | The action being carried out on the private date e.g. accessing, disclosing to a third party. This is an optional parameter in the case where users drill down to more detailed preferences.   | [optional] 
**purpose** | **str** | The purpose for which the service is using the private data. This is an optional parameter in the case where users drill down to more detailed preferences.  | [optional] 
**recipient** | **str** | The recipient of any disclosed privacy information. This is an optional parameter in the case where users drill down to more detailed preferences.  | [optional] 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


