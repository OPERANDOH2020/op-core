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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

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
   * Logging level.
   */
  public enum LogLevelEnum {
    INFO("INFO"),

    WARN("WARN"),

    ERROR("ERROR"),

    FATAL("FATAL");

    private String value;

    LogLevelEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static LogLevelEnum fromValue(String text) {
      for (LogLevelEnum b : LogLevelEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("logLevel")
  private LogLevelEnum logLevel = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("keywords")
  private List<String> keywords = new ArrayList<String>();

  /**
   * Type of the data logged.
   */
  public enum LogTypeEnum {
    
    DATA_ACCESS("DATA_ACCESS"),
    
    
    SYSTEM("SYSTEM"),
    
    
    NOTIFICATION("NOTIFICATION"),
    
   
    OTHER("OTHER");

    private String value;

    LogTypeEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }
  }

  @JsonProperty("logType")
  private LogTypeEnum logType = null;

  @JsonProperty("affectedUserId")
  private String affectedUserId = null;

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

  public LogRequest logLevel(LogLevelEnum logLevel) {
    this.logLevel = logLevel;
    return this;
  }

   /**
   * Type of the data to be logged.
   * @return logLevel
  **/
  @ApiModelProperty(value = "Type of the data to be logged.")
  public LogLevelEnum getLogLevel() {
    return logLevel;
  }

  public void setLogLevel(LogLevelEnum logLevel) {
    this.logLevel = logLevel;
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

  public LogRequest logType(LogTypeEnum logType) {
    this.logType = logType;
    return this;
  }

   /**
   * Type of the data logged.
   * @return logType
  **/
  @ApiModelProperty(example = "null", value = "Type of the data logged.")
  public LogTypeEnum getLogType() {
    return logType;
  }

  public void setLogType(LogTypeEnum logType) {
    this.logType = logType;
  }

  public LogRequest affectedUserId(String affectedUserId) {
    this.affectedUserId = affectedUserId;
    return this;
  }

   /**
   * Id of the affected user.
   * @return affectedUserId
  **/
  @ApiModelProperty(example = "null", value = "Id of the affected user.")
  public String getAffectedUserId() {
    return affectedUserId;
  }

  public void setAffectedUserId(String affectedUserId) {
    this.affectedUserId = affectedUserId;
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
        Objects.equals(this.logLevel, logRequest.logLevel) &&
        Objects.equals(this.title, logRequest.title) &&
        Objects.equals(this.description, logRequest.description) &&
        Objects.equals(this.keywords, logRequest.keywords) &&
        Objects.equals(this.logType, logRequest.logType) &&
        Objects.equals(this.affectedUserId, logRequest.affectedUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, requesterType, requesterId, logPriority, logLevel, title, description, keywords, logType, affectedUserId);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogRequest {\n");

    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    requesterType: ").append(toIndentedString(requesterType)).append("\n");
    sb.append("    requesterId: ").append(toIndentedString(requesterId)).append("\n");
    sb.append("    logPriority: ").append(toIndentedString(logPriority)).append("\n");
    sb.append("    logLevel: ").append(toIndentedString(logLevel)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    keywords: ").append(toIndentedString(keywords)).append("\n");
    sb.append("    logType: ").append(toIndentedString(logType)).append("\n");
    sb.append("    affectedUserId: ").append(toIndentedString(affectedUserId)).append("\n");
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

