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

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * A privacy rule that reflects a given privacy legislation as described\nby a particular set of laws in a given jurisdiction.\n
 **/

@ApiModel(description = "A privacy rule that reflects a given privacy legislation as described\nby a particular set of laws in a given jurisdiction.\n")

public class PrivacyRegulationInput   {
  
  private String legislationSector = null;
  private String privateInformationSource = null;


  public enum PrivateInformationTypeEnum {
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

    PrivateInformationTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private PrivateInformationTypeEnum privateInformationType = null;
  private String action = null;


  public enum RequiredConsentEnum {
    IN("opt-in"),
    OUT("opt-out");

    private String value;

    RequiredConsentEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return value;
    }
  }

  private RequiredConsentEnum requiredConsent = null;

  
  /**
   * The identifier of the set of legislation rules this rule\nbelongs to e.g. the UK data protection act.\n
   **/
  public PrivacyRegulationInput legislationSector(String legislationSector) {
    this.legislationSector = legislationSector;
    return this;
  }

  
  @ApiModelProperty(required = true, value = "The identifier of the set of legislation rules this rule\nbelongs to e.g. the UK data protection act.\n")
  @JsonProperty("legislation_sector")
  public String getLegislationSector() {
    return legislationSector;
  }
  public void setLegislationSector(String legislationSector) {
    this.legislationSector = legislationSector;
  }

  
  /**
   * The source of the private data
   **/
  public PrivacyRegulationInput privateInformationSource(String privateInformationSource) {
    this.privateInformationSource = privateInformationSource;
    return this;
  }

  
  @ApiModelProperty(value = "The source of the private data")
  @JsonProperty("private_information_source")
  public String getPrivateInformationSource() {
    return privateInformationSource;
  }
  public void setPrivateInformationSource(String privateInformationSource) {
    this.privateInformationSource = privateInformationSource;
  }

  
  /**
   * The type of private information; e.g. is it information that identifies the user (e.g. id number)? is it location information about the user? Is it about their activities?\n
   **/
  public PrivacyRegulationInput privateInformationType(PrivateInformationTypeEnum privateInformationType) {
    this.privateInformationType = privateInformationType;
    return this;
  }

  
  @ApiModelProperty(value = "The type of private information; e.g. is it information that identifies the user (e.g. id number)? is it location information about the user? Is it about their activities?\n")
  @JsonProperty("private_information_type")
  public PrivateInformationTypeEnum getPrivateInformationType() {
    return privateInformationType;
  }
  public void setPrivateInformationType(PrivateInformationTypeEnum privateInformationType) {
    this.privateInformationType = privateInformationType;
  }

  
  /**
   * The action being carried out on the data
   **/
  public PrivacyRegulationInput action(String action) {
    this.action = action;
    return this;
  }

  
  @ApiModelProperty(value = "The action being carried out on the data")
  @JsonProperty("action")
  public String getAction() {
    return action;
  }
  public void setAction(String action) {
    this.action = action;
  }

  
  /**
   * The type of consent that must be followed
   **/
  public PrivacyRegulationInput requiredConsent(RequiredConsentEnum requiredConsent) {
    this.requiredConsent = requiredConsent;
    return this;
  }

  
  @ApiModelProperty(value = "The type of consent that must be followed")
  @JsonProperty("required_consent")
  public RequiredConsentEnum getRequiredConsent() {
    return requiredConsent;
  }
  public void setRequiredConsent(RequiredConsentEnum requiredConsent) {
    this.requiredConsent = requiredConsent;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PrivacyRegulationInput privacyRegulationInput = (PrivacyRegulationInput) o;
    return Objects.equals(legislationSector, privacyRegulationInput.legislationSector) &&
        Objects.equals(privateInformationSource, privacyRegulationInput.privateInformationSource) &&
        Objects.equals(privateInformationType, privacyRegulationInput.privateInformationType) &&
        Objects.equals(action, privacyRegulationInput.action) &&
        Objects.equals(requiredConsent, privacyRegulationInput.requiredConsent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(legislationSector, privateInformationSource, privateInformationType, action, requiredConsent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PrivacyRegulationInput {\n");
    
    sb.append("    legislation_sector: ").append(toIndentedString(legislationSector)).append("\n");
    sb.append("    private_information_source: ").append(toIndentedString(privateInformationSource)).append("\n");
    sb.append("    private_information_type: ").append(toIndentedString(privateInformationType)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    required_consent: ").append(toIndentedString(requiredConsent)).append("\n");
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

