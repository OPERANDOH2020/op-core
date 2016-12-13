/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2016
//
// Copyright in this software belongs to University of Southampton
// IT Innovation Centre of Gamma House, Enterprise Road,
// Chilworth Science Park, Southampton, SO16 7NS, UK.
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
//      Created By :            Panos Melas
//      Created Date :          2016-04-28
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

package eu.operando.core.pdb.common.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;






public class AccessPolicy   {
  
  private String subject = null;
  private Boolean permission = null;


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
      return value;
    }
  }

  private ActionEnum action = null;
  private String resource = null;
  private List<PolicyAttribute> attributes = new ArrayList<PolicyAttribute>();

  
  /**
   * A description of the subject who the policies grants/doesn't grant to carry out.\n
   **/
  public AccessPolicy subject(String subject) {
    this.subject = subject;
    return this;
  }

  
  @ApiModelProperty(value = "A description of the subject who the policies grants/doesn't grant to carry out.\n")
  @JsonProperty("subject")
  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }

  
  /**
   * Grant or deny the subject access to the resource via the operation defined in this policy\n
   **/
  public AccessPolicy permission(Boolean permission) {
    this.permission = permission;
    return this;
  }

  
  @ApiModelProperty(value = "Grant or deny the subject access to the resource via the operation defined in this policy\n")
  @JsonProperty("permission")
  public Boolean getPermission() {
    return permission;
  }
  public void setPermission(Boolean permission) {
    this.permission = permission;
  }

  
  /**
   * The action being carried out on the private date e.g. accessing, disclosing to a third party. \n
   **/
  public AccessPolicy action(ActionEnum action) {
    this.action = action;
    return this;
  }

  
  @ApiModelProperty(value = "The action being carried out on the private date e.g. accessing, disclosing to a third party. \n")
  @JsonProperty("action")
  public ActionEnum getAction() {
    return action;
  }
  public void setAction(ActionEnum action) {
    this.action = action;
  }

  
  /**
   * The identifier of the resource that the policy concerns (e.g. URL)\n
   **/
  public AccessPolicy resource(String resource) {
    this.resource = resource;
    return this;
  }

  
  @ApiModelProperty(value = "The identifier of the resource that the policy concerns (e.g. URL)\n")
  @JsonProperty("resource")
  public String getResource() {
    return resource;
  }
  public void setResource(String resource) {
    this.resource = resource;
  }

  
  /**
   * The set of context attributes attached to the policy (e.g. subject role, subject purpose)\n
   **/
  public AccessPolicy attributes(List<PolicyAttribute> attributes) {
    this.attributes = attributes;
    return this;
  }

  
  @ApiModelProperty(value = "The set of context attributes attached to the policy (e.g. subject role, subject purpose)\n")
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
    AccessPolicy accessPolicy = (AccessPolicy) o;
    return Objects.equals(subject, accessPolicy.subject) &&
        Objects.equals(permission, accessPolicy.permission) &&
        Objects.equals(action, accessPolicy.action) &&
        Objects.equals(resource, accessPolicy.resource) &&
        Objects.equals(attributes, accessPolicy.attributes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subject, permission, action, resource, attributes);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccessPolicy {\n");
    
    sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
    sb.append("    permission: ").append(toIndentedString(permission)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    resource: ").append(toIndentedString(resource)).append("\n");
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

