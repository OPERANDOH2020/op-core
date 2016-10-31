
# OSPDataRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**requesterId** | **String** | Id of the requester (typically the id of an OSP). |  [optional]
**subject** | **String** | A description of the subject who the policies grants/doesn&#39;t grant to carry out.  |  [optional]
**requestedUrl** | **String** | The Requested URL of the data.  |  [optional]
**action** | [**ActionEnum**](#ActionEnum) | The action being carried out on the private date e.g. accessing, disclosing to a third party.   |  [optional]
**attributes** | [**List&lt;HttpsrawGithubusercontentComOPERANDOH2020opApiDocmasterdefinitionsPolicyAttributeYamlPolicyAttribute&gt;**](HttpsrawGithubusercontentComOPERANDOH2020opApiDocmasterdefinitionsPolicyAttributeYamlPolicyAttribute.md) | The set of context attributes attached to the policy (e.g. subject role, subject purpose)  |  [optional]


<a name="ActionEnum"></a>
## Enum: ActionEnum
Name | Value
---- | -----
COLLECT | &quot;Collect&quot;
ACCESS | &quot;Access&quot;
USE | &quot;Use&quot;
DISCLOSE | &quot;Disclose&quot;
ARCHIVE | &quot;Archive&quot;



