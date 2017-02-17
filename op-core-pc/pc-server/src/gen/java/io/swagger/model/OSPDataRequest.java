/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2016
//
// Copyright in this library belongs to the University of Southampton
// University Road, Highfield, Southampton, UK, SO17 1BJ
//
// This software may not be used, sold, licensed, transferred, copied
// or reproduced in whole or in part in any manner or form or in or
// on any media by any person other than in accordance with the terms
// of the Licence Agreement supplied with the software, or otherwise
// without the prior written consent of the copyright owners.
//
// This software is distributed WITHOUT ANY WARRANTY, without even the
// implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
// PURPOSE, except where stated in the Licence Agreement supplied with
// the software.
//
// Created By : Paul Grace
// Created for Project : OPERANDO (http://www.operando.eu)
//
/////////////////////////////////////////////////////////////////////////
//
//  License : GNU Lesser General Public License, version 3
//
/////////////////////////////////////////////////////////////////////////
package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.PolicyAttribute;
import java.util.ArrayList;
import java.util.List;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class OSPDataRequest   {

  private String requesterId = null;
  private String subject = null;
  private String requestedUrl = null;

  /**
   * The action being carried out on the private date e.g. accessing, disclosing to a third party.
   */
  public enum ActionEnum {
    COLLECT("Collect"),

        ACCESS("Access"),

        USE("Use"),

        DISCLOSE("Disclose"),

        ARCHIVE("Archive");
    private String value;

    ActionEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }
  }

  private ActionEnum action = null;
  private List<PolicyAttribute> attributes = new ArrayList<PolicyAttribute>();

  /**
   * Id of the requester (typically the id of an OSP).
   **/
  public OSPDataRequest requesterId(String requesterId) {
    this.requesterId = requesterId;
    return this;
  }


  @ApiModelProperty(value = "Id of the requester (typically the id of an OSP).")
  @JsonProperty("requester_id")
  public String getRequesterId() {
    return requesterId;
  }
  public void setRequesterId(String requesterId) {
    this.requesterId = requesterId;
  }

  /**
   * A description of the subject who the policies grants/doesn't grant to carry out.
   **/
  public OSPDataRequest subject(String subject) {
    this.subject = subject;
    return this;
  }


  @ApiModelProperty(value = "A description of the subject who the policies grants/doesn't grant to carry out. ")
  @JsonProperty("subject")
  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }

  /**
   * The Requested URL of the data.
   **/
  public OSPDataRequest requestedUrl(String requestedUrl) {
    this.requestedUrl = requestedUrl;
    return this;
  }


  @ApiModelProperty(value = "The Requested URL of the data. ")
  @JsonProperty("requested_url")
  public String getRequestedUrl() {
    return requestedUrl;
  }
  public void setRequestedUrl(String requestedUrl) {
    this.requestedUrl = requestedUrl;
  }

  /**
   * The action being carried out on the private date e.g. accessing, disclosing to a third party.
   **/
  public OSPDataRequest action(ActionEnum action) {
    this.action = action;
    return this;
  }


  @ApiModelProperty(value = "The action being carried out on the private date e.g. accessing, disclosing to a third party.  ")
  @JsonProperty("action")
  public ActionEnum getAction() {
    return action;
  }
  public void setAction(ActionEnum action) {
    this.action = action;
  }

  /**
   * The set of context attributes attached to the policy (e.g. subject role, subject purpose)
   **/
  public OSPDataRequest attributes(List<PolicyAttribute> attributes) {
    this.attributes = attributes;
    return this;
  }


  @ApiModelProperty(value = "The set of context attributes attached to the policy (e.g. subject role, subject purpose) ")
  @JsonProperty("attributes")
  public List<PolicyAttribute> getAttributes() {
    return attributes;
  }
  public void setAttributes(List<PolicyAttribute> attributes) {
    this.attributes = attributes;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OSPDataRequest oSPDataRequest = (OSPDataRequest) o;
    return Objects.equals(requesterId, oSPDataRequest.requesterId) &&
        Objects.equals(subject, oSPDataRequest.subject) &&
        Objects.equals(requestedUrl, oSPDataRequest.requestedUrl) &&
        Objects.equals(action, oSPDataRequest.action) &&
        Objects.equals(attributes, oSPDataRequest.attributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requesterId, subject, requestedUrl, action, attributes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OSPDataRequest {\n");

    sb.append("    requesterId: ").append(toIndentedString(requesterId)).append("\n");
    sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
    sb.append("    requestedUrl: ").append(toIndentedString(requestedUrl)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    attributes: ").append(toIndentedString(attributes)).append("\n");
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

