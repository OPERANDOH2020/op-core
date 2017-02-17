
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

//package io.swagger.model;
//
//import java.util.Objects;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import io.swagger.annotations.ApiModelProperty;
//
//
//
//
//
//@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
//public class PolicyEvaluationReport   {
//
//  private String status = null;
//  private String compliance = null;
//
//  /**
//   * Request status success or error
//   **/
//  public PolicyEvaluationReport status(String status) {
//    this.status = status;
//    return this;
//  }
//
//
//  @ApiModelProperty(value = "Request status success or error")
//  @JsonProperty("status")
//  public String getStatus() {
//    return status;
//  }
//  public void setStatus(String status) {
//    this.status = status;
//  }
//
//  /**
//   * Message stating the reasons any request failed e.g. breaks the user's privacy preferences.
//   **/
//  public PolicyEvaluationReport compliance(String compliance) {
//    this.compliance = compliance;
//    return this;
//  }
//
//
//  @ApiModelProperty(value = "Message stating the reasons any request failed e.g. breaks the user's privacy preferences.")
//  @JsonProperty("compliance")
//  public String getCompliance() {
//    return compliance;
//  }
//  public void setCompliance(String compliance) {
//    this.compliance = compliance;
//  }
//
//
//  @Override
//  public boolean equals(Object o) {
//    if (this == o) {
//      return true;
//    }
//    if (o == null || getClass() != o.getClass()) {
//      return false;
//    }
//    PolicyEvaluationReport policyEvaluationReport = (PolicyEvaluationReport) o;
//    return Objects.equals(status, policyEvaluationReport.status) &&
//        Objects.equals(compliance, policyEvaluationReport.compliance);
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(status, compliance);
//  }
//
//  @Override
//  public String toString() {
//    StringBuilder sb = new StringBuilder();
//    sb.append("{\n");
//
//    sb.append("    \"status\": \"").append(toIndentedString(status)).append("\",\n");
//    sb.append("    \"compliance\": \"").append(toIndentedString(compliance)).append("\"\n");
//    sb.append("}");
//    return sb.toString();
//  }
//
//  /**
//   * Convert the given object to string with each line indented by 4 spaces
//   * (except the first line).
//   */
//  private String toIndentedString(Object o) {
//    if (o == null) {
//      return "null";
//    }
//    return o.toString().replace("\n", "\n    ");
//  }
//}
//
