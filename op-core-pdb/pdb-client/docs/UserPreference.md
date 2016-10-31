
# UserPreference

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**informationtype** | [**InformationtypeEnum**](#InformationtypeEnum) | The type of private information; e.g. is it information that identifies the user (e.g. id number)? is it location information about the user? Is it about their activities?  |  [optional]
**category** | [**CategoryEnum**](#CategoryEnum) | The category of the service invading the privacy of the user.  |  [optional]
**preference** | [**PreferenceEnum**](#PreferenceEnum) | The user&#39;s privacy preference. High means they are sensitive to disclosing private information. Medium they have concerns; and low means they have few privacy concerns with this question.  |  [optional]
**role** | **String** | The role of a person or service that the private information is being disclosed to or used by. This is an optional parameter in the case where users drill down to more detailed preferences.  |  [optional]
**action** | [**ActionEnum**](#ActionEnum) | The action being carried out on the private date e.g. accessing, disclosing to a third party. This is an optional parameter in the case where users drill down to more detailed preferences.   |  [optional]
**purpose** | **String** | The purpose for which the service is using the private data. This is an optional parameter in the case where users drill down to more detailed preferences.  |  [optional]
**recipient** | **String** | The recipient of any disclosed privacy information. This is an optional parameter in the case where users drill down to more detailed preferences.  |  [optional]


<a name="InformationtypeEnum"></a>
## Enum: InformationtypeEnum
Name | Value
---- | -----
IDENTIFICATION | &quot;Identification&quot;
SHARED_IDENTIFICATION | &quot;Shared Identification&quot;
GEOGRAPHIC | &quot;Geographic&quot;
TEMPORAL | &quot;Temporal&quot;
NETWORK_AND_RELATIONSHIPS | &quot;Network and relationships&quot;
OBJECTS | &quot;Objects&quot;
BEHAVIOURAL | &quot;Behavioural&quot;
BELIEFS | &quot;Beliefs&quot;
MEASUREMENTS | &quot;Measurements&quot;


<a name="CategoryEnum"></a>
## Enum: CategoryEnum
Name | Value
---- | -----
HEALTHCARE | &quot;Healthcare&quot;
FINANCE | &quot;Finance&quot;
WEB | &quot;Web&quot;
SOCIAL_NETWORK | &quot;Social Network&quot;


<a name="PreferenceEnum"></a>
## Enum: PreferenceEnum
Name | Value
---- | -----
HIGH | &quot;High&quot;
MEDIUM | &quot;Medium&quot;
LOW | &quot;Low&quot;


<a name="ActionEnum"></a>
## Enum: ActionEnum
Name | Value
---- | -----
COLLECT | &quot;Collect&quot;
ACCESS | &quot;Access&quot;
USE | &quot;Use&quot;
DISCLOSE | &quot;Disclose&quot;
ARCHIVE | &quot;Archive&quot;



