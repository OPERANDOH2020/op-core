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
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.RequestEvaluation;
import java.util.ArrayList;
import java.util.List;




/**
 * PolicyEvaluationReport
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-01T07:44:32.102Z")
public class UserPolicyEvaluationReport   {
  private String status = null;
  private String id = null;
  private String compliance = null;

  private List<RequestEvaluation> evaluations = new ArrayList<RequestEvaluation>();

  public UserPolicyEvaluationReport status(String status) {
    this.status = status;
    return this;
  }

   /**
   * Request status success or error
   * @return status
  **/
  @ApiModelProperty(value = "Request status success or error")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @ApiModelProperty(value = "User id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public UserPolicyEvaluationReport compliance(String compliance) {
    this.compliance = compliance;
    return this;
  }

   /**
   * Overall compliance result - three values - NO_POLICY, PREFS_CONFLICT, VALID.
   * @return compliance
  **/
  @ApiModelProperty(value = "Overall compliance result - three values - NO_POLICY, PREFS_CONFLICT, VALID.")
  public String getCompliance() {
    return compliance;
  }

  public void setCompliance(String compliance) {
    this.compliance = compliance;
  }

  public UserPolicyEvaluationReport evaluations(List<RequestEvaluation> evaluations) {
    this.evaluations = evaluations;
    return this;
  }

  public UserPolicyEvaluationReport addEvaluationsItem(RequestEvaluation evaluationsItem) {
    this.evaluations.add(evaluationsItem);
    return this;
  }

   /**
   * Get evaluations
   * @return evaluations
  **/
  @ApiModelProperty(value = "")
  public List<RequestEvaluation> getEvaluations() {
    return evaluations;
  }

  public void setEvaluations(List<RequestEvaluation> evaluations) {
    this.evaluations = evaluations;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserPolicyEvaluationReport policyEvaluationReport = (UserPolicyEvaluationReport) o;
    return Objects.equals(this.status, policyEvaluationReport.status) &&
        Objects.equals(this.compliance, policyEvaluationReport.compliance) &&
            Objects.equals(this.id, policyEvaluationReport.id) &&
        Objects.equals(this.evaluations, policyEvaluationReport.evaluations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, compliance, evaluations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    sb.append("    \"id\": \"").append(toIndentedString(id)).append("\",\n");
    sb.append("    \"status\": \"").append(toIndentedString(status)).append("\",\n");
    sb.append("    \"compliance\": \"").append(toIndentedString(compliance)).append("\",\n");
    sb.append("    \"evaluations\": ").append(toIndentedString(evaluations)).append("\n");
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

