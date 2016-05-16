package io.swagger.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.PolicyAttribute;
import java.util.ArrayList;
import java.util.List;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-04-19T15:50:46.998Z")
public class OSPDataRequest   {
  
  private String requesterId = null;
  private String subject = null;
  private String requestedUrl = null;


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
  private List<PolicyAttribute> attributes = new ArrayList<PolicyAttribute>();

  
  /**
   * Id of the requester (typically the id of an OSP).
   **/
  public OSPDataRequest requesterId(String requesterId) {
    this.requesterId = requesterId;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Id of the requester (typically the id of an OSP).")
  @JsonProperty("requester_id")
  public String getRequesterId() {
    return requesterId;
  }
  public void setRequesterId(String requesterId) {
    this.requesterId = requesterId;
  }


  /**
   * A description of the subject who the policies grants/doesn't grant to carry out.\n
   **/
  public OSPDataRequest subject(String subject) {
    this.subject = subject;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "A description of the subject who the policies grants/doesn't grant to carry out.\n")
  @JsonProperty("subject")
  public String getSubject() {
    return subject;
  }
  public void setSubject(String subject) {
    this.subject = subject;
  }


  /**
   * The Requested URL of the data.\n
   **/
  public OSPDataRequest requestedUrl(String requestedUrl) {
    this.requestedUrl = requestedUrl;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "The Requested URL of the data.\n")
  @JsonProperty("requested_url")
  public String getRequestedUrl() {
    return requestedUrl;
  }
  public void setRequestedUrl(String requestedUrl) {
    this.requestedUrl = requestedUrl;
  }


  /**
   * The action being carried out on the private date e.g. accessing, disclosing to a third party. \n
   **/
  public OSPDataRequest action(ActionEnum action) {
    this.action = action;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "The action being carried out on the private date e.g. accessing, disclosing to a third party. \n")
  @JsonProperty("action")
  public ActionEnum getAction() {
    return action;
  }
  public void setAction(ActionEnum action) {
    this.action = action;
  }


  /**
   * The set of context attributes attached to the policy (e.g. subject role, subject purpose)\n
   **/
  public OSPDataRequest attributes(List<PolicyAttribute> attributes) {
    this.attributes = attributes;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "The set of context attributes attached to the policy (e.g. subject role, subject purpose)\n")
  @JsonProperty("attributes")
  public List<PolicyAttribute> getAttributes() {
    return attributes;
  }
  public void setAttributes(List<PolicyAttribute> attributes) {
    this.attributes = attributes;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OSPDataRequest oSPDataRequest = (OSPDataRequest) o;
    return Objects.equals(this.requesterId, oSPDataRequest.requesterId) &&
        Objects.equals(this.subject, oSPDataRequest.subject) &&
        Objects.equals(this.requestedUrl, oSPDataRequest.requestedUrl) &&
        Objects.equals(this.action, oSPDataRequest.action) &&
        Objects.equals(this.attributes, oSPDataRequest.attributes);
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

