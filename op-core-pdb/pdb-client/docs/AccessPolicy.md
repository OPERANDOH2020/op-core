
# AccessPolicy

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**subject** | **String** | A description of the subject who the policies grants/doesn&#39;t grant to carry out.  |  [optional]
**permission** | **Boolean** | Grant or deny the subject access to the resource via the operation defined in this policy  |  [optional]
**action** | [**ActionEnum**](#ActionEnum) | The action being carried out on the private date e.g. accessing, disclosing to a third party.   |  [optional]
**resource** | **String** | The identifier of the resource that the policy concerns (e.g. URL)  |  [optional]
**attributes** | [**List&lt;PolicyAttribute&gt;**](PolicyAttribute.md) | The set of context attributes attached to the policy (e.g. subject role, subject purpose)  |  [optional]


<a name="ActionEnum"></a>
## Enum: ActionEnum
Name | Value
---- | -----
COLLECT | &quot;Collect&quot;
ACCESS | &quot;Access&quot;
USE | &quot;Use&quot;
DISCLOSE | &quot;Disclose&quot;
ARCHIVE | &quot;Archive&quot;



