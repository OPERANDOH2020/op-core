package io.swagger.client.model;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * LogResponse
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-06-06T13:46:28.240Z")
public class LogResponse   {
  
  private String logDate = null;

  /**
   * Source type from which comes the request.
   */
  public enum RequesterTypeEnum {
    PROCESS("Process"),
    MODULE("Module");

    private String value;

    RequesterTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }
  }

  private RequesterTypeEnum requesterType = null;
  private String requesterId = null;

  /**
   * Priority level of the data to be logged.
   */
  public enum LogPriorityEnum {
    LOW("Low"),
    NORMAL("Normal"),
    HIGH("High"),
    CRITICAL("Critical");

    private String value;

    LogPriorityEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }
  }

  private LogPriorityEnum logPriority = null;

  /**
   * Type of the data to be logged.
   */
  public enum LogLevelEnum {
    INFO("Info"),
    WARN("Warn"),
    ERROR("Error"),
    FATAL("Fatal");

    private String value;

    LogLevelEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }
  }

  private LogLevelEnum logLevel = null;
  private String title = null;
  private String description = null;

  
  /**
   * Date when the log was generated.
   **/
  public LogResponse logDate(String logDate) {
    this.logDate = logDate;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Date when the log was generated.")
  @JsonProperty("logDate")
  public String getLogDate() {
    return logDate;
  }
  public void setLogDate(String logDate) {
    this.logDate = logDate;
  }


  /**
   * Source type from which comes the request.
   **/
  public LogResponse requesterType(RequesterTypeEnum requesterType) {
    this.requesterType = requesterType;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Source type from which comes the request.")
  @JsonProperty("requesterType")
  public RequesterTypeEnum getRequesterType() {
    return requesterType;
  }
  public void setRequesterType(RequesterTypeEnum requesterType) {
    this.requesterType = requesterType;
  }


  /**
   * Id of the requester (e.g the id of an OSP).
   **/
  public LogResponse requesterId(String requesterId) {
    this.requesterId = requesterId;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Id of the requester (e.g the id of an OSP).")
  @JsonProperty("requesterId")
  public String getRequesterId() {
    return requesterId;
  }
  public void setRequesterId(String requesterId) {
    this.requesterId = requesterId;
  }


  /**
   * Priority level of the data to be logged.
   **/
  public LogResponse logPriority(LogPriorityEnum logPriority) {
    this.logPriority = logPriority;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Priority level of the data to be logged.")
  @JsonProperty("logPriority")
  public LogPriorityEnum getLogPriority() {
    return logPriority;
  }
  public void setLogPriority(LogPriorityEnum logPriority) {
    this.logPriority = logPriority;
  }


  /**
   * Type of the data to be logged.
   **/
  public LogResponse logLevel(LogLevelEnum logLevel) {
    this.logLevel = logLevel;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Type of the data to be logged.")
  @JsonProperty("logLevel")
  public LogLevelEnum getLogLevel() {
    return logLevel;
  }
  public void setLogLevel(LogLevelEnum logLevel) {
    this.logLevel = logLevel;
  }


  /**
   * Subject of the logged event.
   **/
  public LogResponse title(String title) {
    this.title = title;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Subject of the logged event.")
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }


  /**
   * Description of the logged event.
   **/
  public LogResponse description(String description) {
    this.description = description;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Description of the logged event.")
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LogResponse logResponse = (LogResponse) o;
    return Objects.equals(this.logDate, logResponse.logDate) &&
        Objects.equals(this.requesterType, logResponse.requesterType) &&
        Objects.equals(this.requesterId, logResponse.requesterId) &&
        Objects.equals(this.logPriority, logResponse.logPriority) &&
        Objects.equals(this.logLevel, logResponse.logLevel) &&
        Objects.equals(this.title, logResponse.title) &&
        Objects.equals(this.description, logResponse.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(logDate, requesterType, requesterId, logPriority, logLevel, title, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogResponse {\n");
    
    sb.append("    logDate: ").append(toIndentedString(logDate)).append("\n");
    sb.append("    requesterType: ").append(toIndentedString(requesterType)).append("\n");
    sb.append("    requesterId: ").append(toIndentedString(requesterId)).append("\n");
    sb.append("    logPriority: ").append(toIndentedString(logPriority)).append("\n");
    sb.append("    logLevel: ").append(toIndentedString(logLevel)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

