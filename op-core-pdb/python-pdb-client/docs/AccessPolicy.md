# AccessPolicy

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**subject** | **str** | A description of the subject who the policies grants/doesn&#39;t grant to carry out.  | [optional] 
**permission** | **bool** | Grant or deny the subject access to the resource via the operation defined in this policy  | [optional] 
**action** | **str** | The action being carried out on the private date e.g. accessing, disclosing to a third party.   | [optional] 
**resource** | **str** | The identifier of the resource that the policy concerns (e.g. URL)  | [optional] 
**attributes** | [**list[PolicyAttribute]**](PolicyAttribute.md) | The set of context attributes attached to the policy (e.g. subject role, subject purpose)  | [optional] 

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


