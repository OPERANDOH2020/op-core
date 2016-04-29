/*******************************************************************************
 *  * Copyright (c) 2016 {TECNALIA}.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the The MIT License (MIT).
 *  * which accompanies this distribution, and is available at
 *  * http://opensource.org/licenses/MIT
 *  *
 *  * Contributors:
 *  *    Gorka Mikel Echevarría {TECNALIA}
 *  * Initially developed in the context of OPERANDO EU project www.operando.eu
 *******************************************************************************/
package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-07T07:27:45.925Z")
public class LogRequest   {
  


  public enum RequesterTypeEnum {
    PROCESS_("Process."),
    MODULE_("Module.");

    private String value;

    RequesterTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private RequesterTypeEnum requesterType = null;
  private String requesterId = null;


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
      return value;
    }
  }

  private LogPriorityEnum logPriority = null;


  public enum LogDataTypeEnum {
    INFO("Info"),
    WARN("Warn"),
    ERROR("Error"),
    FATAL("Fatal");

    private String value;

    LogDataTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private LogDataTypeEnum logDataType = null;
  private String title = null;
  private String description = null;

  
  /**
   * Source type from which comes the request.
   **/
  public LogRequest requesterType(RequesterTypeEnum requesterType) {
    this.requesterType = requesterType;
    return this;
  }

  
  @ApiModelProperty(value = "Source type from which comes the request.")
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
  public LogRequest requesterId(String requesterId) {
    this.requesterId = requesterId;
    return this;
  }

  
  @ApiModelProperty(value = "Id of the requester (e.g the id of an OSP).")
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
  public LogRequest logPriority(LogPriorityEnum logPriority) {
    this.logPriority = logPriority;
    return this;
  }

  
  @ApiModelProperty(value = "Priority level of the data to be logged.")
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
  public LogRequest logDataType(LogDataTypeEnum logDataType) {
    this.logDataType = logDataType;
    return this;
  }

  
  @ApiModelProperty(value = "Type of the data to be logged.")
  @JsonProperty("logDataType")
  public LogDataTypeEnum getLogDataType() {
    return logDataType;
  }
  public void setLogDataType(LogDataTypeEnum logDataType) {
    this.logDataType = logDataType;
  }

  
  /**
   * Subject of the event to be logged.
   **/
  public LogRequest title(String title) {
    this.title = title;
    return this;
  }

  
  @ApiModelProperty(value = "Subject of the event to be logged.")
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  
  /**
   * Description of the event to be logged.
   **/
  public LogRequest description(String description) {
    this.description = description;
    return this;
  }

  
  @ApiModelProperty(value = "Description of the event to be logged.")
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LogRequest logRequest = (LogRequest) o;
    return Objects.equals(requesterType, logRequest.requesterType) &&
        Objects.equals(requesterId, logRequest.requesterId) &&
        Objects.equals(logPriority, logRequest.logPriority) &&
        Objects.equals(logDataType, logRequest.logDataType) &&
        Objects.equals(title, logRequest.title) &&
        Objects.equals(description, logRequest.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requesterType, requesterId, logPriority, logDataType, title, description);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogRequest {\n");
    
    sb.append("    requesterType: ").append(toIndentedString(requesterType)).append("\n");
    sb.append("    requesterId: ").append(toIndentedString(requesterId)).append("\n");
    sb.append("    logPriority: ").append(toIndentedString(logPriority)).append("\n");
    sb.append("    logDataType: ").append(toIndentedString(logDataType)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

