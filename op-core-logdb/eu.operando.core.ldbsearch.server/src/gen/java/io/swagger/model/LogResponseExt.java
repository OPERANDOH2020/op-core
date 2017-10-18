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
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.annotations.ApiModelProperty;

/**
 * LogResponse
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-06-09T09:43:59.324Z")
public class LogResponseExt {

	private String logDate = null;

	private String requesterType = null;

	private String requesterId = null;

	private String logPriority = null;

	private String logLevel = null;

	private String title = null;

	private String description = null;

	private String logType = null;

	private String affectedUserId = null;
	
	private String ospId = null;
	
	private ArrayList<String> arrayRequestedFields = null;
	
	private ArrayList<String> arrayGrantedFields = null;

	public ArrayList<String> getArrayRequestedFields() {
		return arrayRequestedFields;
	}

	public void setArrayRequestedFields(ArrayList<String> arrayRequestedFields) {
		this.arrayRequestedFields = arrayRequestedFields;
	}

	public ArrayList<String> getArrayGrantedFields() {
		return arrayGrantedFields;
	}

	public void setArrayGrantedFields(ArrayList<String> arrayGrantedFields) {
		this.arrayGrantedFields = arrayGrantedFields;
	}

	public String getOspId() {
		return ospId;
	}

	public void setOspId(String ospId) {
		this.ospId = ospId;
	}

	public LogResponseExt logDate(String logDate) {
		this.logDate = logDate;
		return this;
	}

	/**
	 * Date when the log was generated.
	 * 
	 * @return logDate
	 **/
	@ApiModelProperty(example = "null", value = "Date when the log was generated.")
	public String getLogDate() {
		return logDate;
	}

	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}

	public LogResponseExt requesterType(String requesterType) {
		this.requesterType = requesterType;
		return this;
	}

	/**
	 * Source type from which comes the request.
	 * 
	 * @return requesterType
	 **/
	@ApiModelProperty(example = "null", value = "Source type from which comes the request.")
	public String getRequesterType() {
		return requesterType;
	}

	public void setRequesterType(String requesterType) {
		this.requesterType = requesterType;
	}

	public LogResponseExt requesterId(String requesterId) {
		this.requesterId = requesterId;
		return this;
	}

	/**
	 * Id of the requester (e.g the id of an OSP).
	 * 
	 * @return requesterId
	 **/
	@ApiModelProperty(example = "null", value = "Id of the requester (e.g the id of an OSP).")
	public String getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(String requesterId) {
		this.requesterId = requesterId;
	}

	public LogResponseExt logPriority(String logPriority) {
		this.logPriority = logPriority;
		return this;
	}

	/**
	 * Priority level of the data to be logged.
	 * 
	 * @return logPriority
	 **/
	@ApiModelProperty(example = "null", value = "Priority level of the data to be logged.")
	public String getLogPriority() {
		return logPriority;
	}

	public void setLogPriority(String logPriority) {
		this.logPriority = logPriority;
	}

	public LogResponseExt logLevel(String logLevel) {
		this.logLevel = logLevel;
		return this;
	}

	/**
	 * Type of the data to be logged.
	 * 
	 * @return logLevel
	 **/
	@ApiModelProperty(example = "null", value = "Type of the data to be logged.")
	public String getLogLevel() {
		return logLevel;
	}

	public void setLogLevel(String logLevel) {
		this.logLevel = logLevel;
	}

	public LogResponseExt title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Subject of the logged event.
	 * 
	 * @return title
	 **/
	@ApiModelProperty(example = "null", value = "Subject of the logged event.")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LogResponseExt description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Description of the logged event.
	 * 
	 * @return description
	 **/
	@ApiModelProperty(example = "null", value = "Description of the logged event.")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LogResponseExt logType(String logType) {
		this.logType = logType;
		return this;
	}

	/**
	 * Type of the data logged.
	 * 
	 * @return logType
	 **/
	@ApiModelProperty(example = "null", value = "Type of the data logged.")
	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public LogResponseExt affectedUserId(String affectedUserId) {
		this.affectedUserId = affectedUserId;
		return this;
	}

	/**
	 * Id of the affected user.
	 * 
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
		LogResponseExt logResponse = (LogResponseExt) o;
		return Objects.equals(this.logDate, logResponse.logDate)
				&& Objects.equals(this.requesterType, logResponse.requesterType)
				&& Objects.equals(this.requesterId, logResponse.requesterId)
				&& Objects.equals(this.logPriority, logResponse.logPriority)
				&& Objects.equals(this.logLevel, logResponse.logLevel) && Objects.equals(this.title, logResponse.title)
				&& Objects.equals(this.description, logResponse.description)
				&& Objects.equals(this.logType, logResponse.logType)
				&& Objects.equals(this.affectedUserId, logResponse.affectedUserId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(logDate, requesterType, requesterId, logPriority, logLevel, title, description, logType,
				affectedUserId);
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
