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

package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModelProperty;






public class UserPreference   {
  

  @JsonFormat(shape= JsonFormat.Shape.OBJECT)
  public enum InformationTypeEnum {
    IDENTIFICATION("Identification"),
    SHARED_IDENTIFICATION("Shared Identification"),
    GEOGRAPHIC("Geographic"),
    TEMPORAL("Temporal"),
    NETWORK_AND_RELATIONSHIPS("Network and relationships"),
    OBJECTS("Objects"),
    BEHAVIOURAL("Behavioural"),
    BELIEFS("Beliefs"),
    MEASUREMENTS("Measurements");

    private String value;

    InformationTypeEnum(String value) {
      this.value = value;
    }

    //@Override
    @JsonValue
    public String toString() {
      return value;
    }
    
    /*@JsonValue
    public String getValue() {
      return value;
    }*/
  }

  private InformationTypeEnum informationType = null;


  public enum CategoryEnum {
    HEALTHCARE("Healthcare"),
    FINANCE("Finance"),
    WEB("Web"),
    SOCIAL_NETWORK("Social Network");

    private String value;

    CategoryEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private CategoryEnum category = null;


  public enum PreferenceEnum {
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private String value;

    PreferenceEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private PreferenceEnum preference = null;
  private String role = null;


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
  private String purpose = null;
  private String recipient = null;

  
  /**
   * The type of private information; e.g. is it information that identifies the user (e.g. id number)? is it location information about the user? Is it about their activities?\n
   **/
  public UserPreference informationType(InformationTypeEnum informationType) {
    this.informationType = informationType;
    return this;
  }

  
  @ApiModelProperty(value = "The type of private information; e.g. is it information that identifies the user (e.g. id number)? is it location information about the user? Is it about their activities?\n")
  @JsonProperty("information_type")
  public InformationTypeEnum getInformationType() {
    return informationType;
  }
  public void setInformationType(InformationTypeEnum informationType) {
    this.informationType = informationType;
  }

  
  /**
   * The category of the service invading the privacy of the user.\n
   **/
  public UserPreference category(CategoryEnum category) {
    this.category = category;
    return this;
  }

  
  @ApiModelProperty(value = "The category of the service invading the privacy of the user.\n")
  @JsonProperty("category")
  public CategoryEnum getCategory() {
    return category;
  }
  public void setCategory(CategoryEnum category) {
    this.category = category;
  }

  
  /**
   * The user's privacy preference. High means they are sensitive to disclosing private information. Medium they have concerns; and low means they have few privacy concerns with this question.\n
   **/
  public UserPreference preference(PreferenceEnum preference) {
    this.preference = preference;
    return this;
  }

  
  @ApiModelProperty(value = "The user's privacy preference. High means they are sensitive to disclosing private information. Medium they have concerns; and low means they have few privacy concerns with this question.\n")
  @JsonProperty("preference")
  public PreferenceEnum getPreference() {
    return preference;
  }
  public void setPreference(PreferenceEnum preference) {
    this.preference = preference;
  }

  
  /**
   * The role of a person or service that the private information is being disclosed to or used by. This is an optional parameter in the case where users drill down to more detailed preferences.\n
   **/
  public UserPreference role(String role) {
    this.role = role;
    return this;
  }

  
  @ApiModelProperty(value = "The role of a person or service that the private information is being disclosed to or used by. This is an optional parameter in the case where users drill down to more detailed preferences.\n")
  @JsonProperty("role")
  public String getRole() {
    return role;
  }
  public void setRole(String role) {
    this.role = role;
  }

  
  /**
   * The action being carried out on the private date e.g. accessing, disclosing to a third party. This is an optional parameter in the case where users drill down to more detailed preferences. \n
   **/
  public UserPreference action(ActionEnum action) {
    this.action = action;
    return this;
  }

  
  @ApiModelProperty(value = "The action being carried out on the private date e.g. accessing, disclosing to a third party. This is an optional parameter in the case where users drill down to more detailed preferences. \n")
  @JsonProperty("action")
  public ActionEnum getAction() {
    return action;
  }
  public void setAction(ActionEnum action) {
    this.action = action;
  }

  
  /**
   * The purpose for which the service is using the private data. This is an optional parameter in the case where users drill down to more detailed preferences.\n
   **/
  public UserPreference purpose(String purpose) {
    this.purpose = purpose;
    return this;
  }

  
  @ApiModelProperty(value = "The purpose for which the service is using the private data. This is an optional parameter in the case where users drill down to more detailed preferences.\n")
  @JsonProperty("purpose")
  public String getPurpose() {
    return purpose;
  }
  public void setPurpose(String purpose) {
    this.purpose = purpose;
  }

  
  /**
   * The recipient of any disclosed privacy information. This is an optional parameter in the case where users drill down to more detailed preferences.\n
   **/
  public UserPreference recipient(String recipient) {
    this.recipient = recipient;
    return this;
  }

  
  @ApiModelProperty(value = "The recipient of any disclosed privacy information. This is an optional parameter in the case where users drill down to more detailed preferences.\n")
  @JsonProperty("recipient")
  public String getRecipient() {
    return recipient;
  }
  public void setRecipient(String recipient) {
    this.recipient = recipient;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserPreference userPreference = (UserPreference) o;
    return Objects.equals(informationType, userPreference.informationType) &&
        Objects.equals(category, userPreference.category) &&
        Objects.equals(preference, userPreference.preference) &&
        Objects.equals(role, userPreference.role) &&
        Objects.equals(action, userPreference.action) &&
        Objects.equals(purpose, userPreference.purpose) &&
        Objects.equals(recipient, userPreference.recipient);
  }

  @Override
  public int hashCode() {
    return Objects.hash(informationType, category, preference, role, action, purpose, recipient);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserPreference {\n");
    
    sb.append("    informationType: ").append(toIndentedString(informationType)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    preference: ").append(toIndentedString(preference)).append("\n");
    sb.append("    role: ").append(toIndentedString(role)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    purpose: ").append(toIndentedString(purpose)).append("\n");
    sb.append("    recipient: ").append(toIndentedString(recipient)).append("\n");
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

