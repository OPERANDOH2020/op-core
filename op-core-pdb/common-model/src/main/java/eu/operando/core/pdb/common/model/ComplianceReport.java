/////////////////////////////////////////////////////////////////////////
//
// � University of Southampton IT Innovation Centre, 2016
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
package eu.operando.core.pdb.common.model;

import java.util.Objects;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;




/**
 * ComplianceReport
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-07T12:10:42.971Z")
public class ComplianceReport   {
  private String status = null;

  private String regulation = null;

  private List<ComplianceEvaluation> evaluations = new ArrayList<ComplianceEvaluation>();

  public ComplianceReport status(String status) {
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

  public ComplianceReport regulation(String regulation) {
    this.regulation = regulation;
    return this;
  }

   /**
   * The identifier of the regulation this compliance report is for
   * @return regulation
  **/
  @ApiModelProperty(value = "The identifier of the regulation this compliance report is for")
  public String getRegulation() {
    return regulation;
  }

  public void setRegulation(String regulation) {
    this.regulation = regulation;
  }

  public ComplianceReport evaluations(List<ComplianceEvaluation> evaluations) {
    this.evaluations = evaluations;
    return this;
  }

  public ComplianceReport addEvaluationsItem(ComplianceEvaluation evaluationsItem) {
    this.evaluations.add(evaluationsItem);
    return this;
  }

   /**
   * Get evaluations
   * @return evaluations
  **/
  @ApiModelProperty(value = "")
  public List<ComplianceEvaluation> getEvaluations() {
    return evaluations;
  }

  public void setEvaluations(List<ComplianceEvaluation> evaluations) {
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
    ComplianceReport complianceReport = (ComplianceReport) o;
    return Objects.equals(this.status, complianceReport.status) &&
        Objects.equals(this.regulation, complianceReport.regulation) &&
        Objects.equals(this.evaluations, complianceReport.evaluations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, regulation, evaluations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComplianceReport {\n");

    sb.append("    status: ").append(toIndentedString(status)).append(",\n");
    sb.append("    regulation: ").append(toIndentedString(regulation)).append(",\n");
    sb.append("    evaluations: ").append(toIndentedString(evaluations)).append("\n");
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

