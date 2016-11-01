package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.RequestEvaluation;
import java.util.ArrayList;
import java.util.List;




/**
 * PolicyEvaluationReport
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-01T07:44:32.102Z")
public class PolicyEvaluationReport   {
  private String status = null;

  private String compliance = null;

  private List<RequestEvaluation> evaluations = new ArrayList<RequestEvaluation>();

  public PolicyEvaluationReport status(String status) {
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

  public PolicyEvaluationReport compliance(String compliance) {
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

  public PolicyEvaluationReport evaluations(List<RequestEvaluation> evaluations) {
    this.evaluations = evaluations;
    return this;
  }

  public PolicyEvaluationReport addEvaluationsItem(RequestEvaluation evaluationsItem) {
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
    PolicyEvaluationReport policyEvaluationReport = (PolicyEvaluationReport) o;
    return Objects.equals(this.status, policyEvaluationReport.status) &&
        Objects.equals(this.compliance, policyEvaluationReport.compliance) &&
        Objects.equals(this.evaluations, policyEvaluationReport.evaluations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, compliance, evaluations);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PolicyEvaluationReport {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    compliance: ").append(toIndentedString(compliance)).append("\n");
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

