/*
   	* Copyright (c) 2017 {TECNALIA}.
    * All rights reserved. This program and the accompanying materials
    * are made available under the terms of the The MIT License (MIT).
    * which accompanies this distribution, and is available at
    * http://opensource.org/licenses/MIT
    *
    * Contributors:
    *    Gorka Benguria Elguezabal {TECNALIA}
    *    Gorka Mikel Echevarría {TECNALIA}
    * Initially developed in the context of OPERANDO EU project www.operando.eu
 */


package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;

/**
 * LogRequest
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-13T14:16:25.334Z")
public class LogRequest   {
  @JsonProperty("userId")
  private String userId = null;

  /**
   * Source type from which comes the request.
   */
  public enum RequesterTypeEnum {
    PROCESS("PROCESS"),
    
    MODULE("MODULE");

    private String value;

    RequesterTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static RequesterTypeEnum fromValue(String text) {
      for (RequesterTypeEnum b : RequesterTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("requesterType")
  private RequesterTypeEnum requesterType = null;

  @JsonProperty("requesterId")
  private String requesterId = null;

  /**
   * Priority level of the data to be logged.
   */
  public enum LogPriorityEnum {
    LOW("LOW"),
    
    NORMAL("NORMAL"),
    
    HIGH("HIGH"),
    
    CRITICAL("CRITICAL");

    private String value;

    LogPriorityEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static LogPriorityEnum fromValue(String text) {
      for (LogPriorityEnum b : LogPriorityEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("logPriority")
  private LogPriorityEnum logPriority = null;

  /**
   * Type of the data to be logged.
   */
  public enum LogDataTypeEnum {
    INFO("INFO"),
    
    WARN("WARN"),
    
    ERROS("ERROS"),
    
    FATAL("FATAL");

    private String value;

    LogDataTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static LogDataTypeEnum fromValue(String text) {
      for (LogDataTypeEnum b : LogDataTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("logDataType")
  private LogDataTypeEnum logDataType = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("keywords")
  private List<String> keywords = new ArrayList<String>();

  public LogRequest userId(String userId) {
    this.userId = userId;
    return this;
  }

   /**
   * Id of the user.
   * @return userId
  **/
  @ApiModelProperty(value = "Id of the user.")
  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public LogRequest requesterType(RequesterTypeEnum requesterType) {
    this.requesterType = requesterType;
    return this;
  }

   /**
   * Source type from which comes the request.
   * @return requesterType
  **/
  @ApiModelProperty(value = "Source type from which comes the request.")
  public RequesterTypeEnum getRequesterType() {
    return requesterType;
  }

  public void setRequesterType(RequesterTypeEnum requesterType) {
    this.requesterType = requesterType;
  }

  public LogRequest requesterId(String requesterId) {
    this.requesterId = requesterId;
    return this;
  }

   /**
   * Id of the requester (e.g the id of an OSP).
   * @return requesterId
  **/
  @ApiModelProperty(value = "Id of the requester (e.g the id of an OSP).")
  public String getRequesterId() {
    return requesterId;
  }

  public void setRequesterId(String requesterId) {
    this.requesterId = requesterId;
  }

  public LogRequest logPriority(LogPriorityEnum logPriority) {
    this.logPriority = logPriority;
    return this;
  }

   /**
   * Priority level of the data to be logged.
   * @return logPriority
  **/
  @ApiModelProperty(value = "Priority level of the data to be logged.")
  public LogPriorityEnum getLogPriority() {
    return logPriority;
  }

  public void setLogPriority(LogPriorityEnum logPriority) {
    this.logPriority = logPriority;
  }

  public LogRequest logDataType(LogDataTypeEnum logDataType) {
    this.logDataType = logDataType;
    return this;
  }

   /**
   * Type of the data to be logged.
   * @return logDataType
  **/
  @ApiModelProperty(value = "Type of the data to be logged.")
  public LogDataTypeEnum getLogDataType() {
    return logDataType;
  }

  public void setLogDataType(LogDataTypeEnum logDataType) {
    this.logDataType = logDataType;
  }

  public LogRequest title(String title) {
    this.title = title;
    return this;
  }

   /**
   * Subject of the event to be logged.
   * @return title
  **/
  @ApiModelProperty(value = "Subject of the event to be logged.")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LogRequest description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Description of the event to be logged.
   * @return description
  **/
  @ApiModelProperty(value = "Description of the event to be logged.")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LogRequest keywords(List<String> keywords) {
    this.keywords = keywords;
    return this;
  }

  public LogRequest addKeywordsItem(String keywordsItem) {
    this.keywords.add(keywordsItem);
    return this;
  }

   /**
   * Array of keywords to facilitate search
   * @return keywords
  **/
  @ApiModelProperty(value = "Array of keywords to facilitate search")
  public List<String> getKeywords() {
    return keywords;
  }

  public void setKeywords(List<String> keywords) {
    this.keywords = keywords;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LogRequest logRequest = (LogRequest) o;
    return Objects.equals(this.userId, logRequest.userId) &&
        Objects.equals(this.requesterType, logRequest.requesterType) &&
        Objects.equals(this.requesterId, logRequest.requesterId) &&
        Objects.equals(this.logPriority, logRequest.logPriority) &&
        Objects.equals(this.logDataType, logRequest.logDataType) &&
        Objects.equals(this.title, logRequest.title) &&
        Objects.equals(this.description, logRequest.description) &&
        Objects.equals(this.keywords, logRequest.keywords);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, requesterType, requesterId, logPriority, logDataType, title, description, keywords);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogRequest {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    requesterType: ").append(toIndentedString(requesterType)).append("\n");
    sb.append("    requesterId: ").append(toIndentedString(requesterId)).append("\n");
    sb.append("    logPriority: ").append(toIndentedString(logPriority)).append("\n");
    sb.append("    logDataType: ").append(toIndentedString(logDataType)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    keywords: ").append(toIndentedString(keywords)).append("\n");
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

