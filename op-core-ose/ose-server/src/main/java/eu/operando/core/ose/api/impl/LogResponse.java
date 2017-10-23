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
package eu.operando.core.ose.api.impl;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;


/**
 * LogResponse
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-06-09T09:43:59.324Z")
public class LogResponse   {

  private String logDate = null;
  private String requesterType = null;
  private String requesterId = null;
  private String logPriority = null;
  private String logLevel = null;
  private String title = null;
  private String description = null;
  private String logType = null;
  private String affectedUserId = null;

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
  public LogResponse requesterType(String requesterType) {
	    this.requesterType = requesterType;
	    return this;
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
  public LogResponse logPriority(String logPriority) {
	    this.logPriority = logPriority;
	    return this;
	  }

  @ApiModelProperty(example = "null", value = "Priority level of the data to be logged.")
  @JsonProperty("logPriority")
  public String getLogPriority() {
	    return logPriority;
	  }

	  public void setLogPriority(String logPriority) {
	    this.logPriority = logPriority;
	  }


  public LogResponse logLevel(String logLevel) {
	    this.logLevel = logLevel;
	    return this;
	  }

  @ApiModelProperty(example = "null", value = "Type of the data to be logged.")
  @JsonProperty("logLevel")
  public String getLogLevel() {
	    return logLevel;
	  }
  public void setLogLevel(String logLevel) {
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

  public LogResponse logType(String logType) {
	    this.logType = logType;
	    return this;
	  }

   /**
   * Type of the data logged.
   * @return logType
  **/
  @ApiModelProperty(example = "null", value = "Type of the data logged.")
  public String getLogType() {
	    return logType;
	  }

	  public void setLogType(String logType) {
	    this.logType = logType;
	  }

  public LogResponse affectedUserId(String affectedUserId) {
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
    LogResponse logResponse = (LogResponse) o;
    return Objects.equals(this.logDate, logResponse.logDate) &&
        Objects.equals(this.requesterType, logResponse.requesterType) &&
        Objects.equals(this.requesterId, logResponse.requesterId) &&
        Objects.equals(this.logPriority, logResponse.logPriority) &&
        Objects.equals(this.logLevel, logResponse.logLevel) &&
        Objects.equals(this.title, logResponse.title) &&
        Objects.equals(this.description, logResponse.description) &&
        Objects.equals(this.logType, logResponse.logType) &&
        Objects.equals(this.affectedUserId, logResponse.affectedUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(logDate, requesterType, requesterId, logPriority, logLevel, title, description, logType, affectedUserId);
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

