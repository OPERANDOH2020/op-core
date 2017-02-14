
# PrivacyRegulation

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**regId** | **String** |  |  [optional]
**legislationSector** | **String** | The identifier of the set of legislation rules this rule belongs to e.g. the UK data protection act.  | 
**privateInformationSource** | **String** | The source of the private data |  [optional]
**privateInformationType** | [**PrivateInformationTypeEnum**](#PrivateInformationTypeEnum) | The type of private information; e.g. is it information that identifies the user (e.g. id number)? is it location information about the user? Is it about their activities?  |  [optional]
**action** | **String** | The action being carried out on the data |  [optional]
**requiredConsent** | [**RequiredConsentEnum**](#RequiredConsentEnum) | The type of consent that must be followed |  [optional]


<a name="PrivateInformationTypeEnum"></a>
## Enum: PrivateInformationTypeEnum
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


<a name="RequiredConsentEnum"></a>
## Enum: RequiredConsentEnum
Name | Value
---- | -----
IN | &quot;opt-in&quot;
OUT | &quot;opt-out&quot;



