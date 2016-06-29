
# LogResponse

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**logDate** | **String** | Date when the log was generated. |  [optional]
**requesterType** | [**RequesterTypeEnum**](#RequesterTypeEnum) | Source type from which comes the request. |  [optional]
**requesterId** | **String** | Id of the requester (e.g the id of an OSP). |  [optional]
**logPriority** | [**LogPriorityEnum**](#LogPriorityEnum) | Priority level of the data to be logged. |  [optional]
**logLevel** | [**LogLevelEnum**](#LogLevelEnum) | Type of the data to be logged. |  [optional]
**title** | **String** | Subject of the logged event. |  [optional]
**description** | **String** | Description of the logged event. |  [optional]


<a name="RequesterTypeEnum"></a>
## Enum: RequesterTypeEnum
Name | Value
---- | -----
PROCESS_ | &quot;Process.&quot;
MODULE_ | &quot;Module.&quot;


<a name="LogPriorityEnum"></a>
## Enum: LogPriorityEnum
Name | Value
---- | -----
LOW | &quot;Low&quot;
NORMAL | &quot;Normal&quot;
HIGH | &quot;High&quot;
CRITICAL | &quot;Critical&quot;


<a name="LogLevelEnum"></a>
## Enum: LogLevelEnum
Name | Value
---- | -----
INFO | &quot;Info&quot;
WARN | &quot;Warn&quot;
ERROR | &quot;Error&quot;
FATAL | &quot;Fatal&quot;



