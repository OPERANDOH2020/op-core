package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * ComplianceEvaluation
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-07T12:10:42.971Z")
public class ComplianceEvaluation   {
  private String datauser = null;

  private String datafield = null;

  private String action = null;

  private String reason = null;

  private Boolean result = null;

  public ComplianceEvaluation datauser(String datauser) {
    this.datauser = datauser;
    return this;
  }

   /**
   * The user of the data in the request to be evaluated 
   * @return datauser
  **/
  @ApiModelProperty(value = "The user of the data in the request to be evaluated ")
  public String getDatauser() {
    return datauser;
  }

  public void setDatauser(String datauser) {
    this.datauser = datauser;
  }

  public ComplianceEvaluation datafield(String datafield) {
    this.datafield = datafield;
    return this;
  }

   /**
   * The oData field requested by the data user. 
   * @return datafield
  **/
  @ApiModelProperty(value = "The oData field requested by the data user. ")
  public String getDatafield() {
    return datafield;
  }

  public void setDatafield(String datafield) {
    this.datafield = datafield;
  }

  public ComplianceEvaluation action(String action) {
    this.action = action;
    return this;
  }

   /**
   * The usage type of the data 
   * @return action
  **/
  @ApiModelProperty(value = "The usage type of the data ")
  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public ComplianceEvaluation reason(String reason) {
    this.reason = reason;
    return this;
  }

   /**
   * The reason for this privacy policy action 
   * @return reason
  **/
  @ApiModelProperty(value = "The reason for this privacy policy action ")
  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public ComplianceEvaluation result(Boolean result) {
    this.result = result;
    return this;
  }

   /**
   * Whether the request complies with the regulation 
   * @return result
  **/
  @ApiModelProperty(value = "Whether the request complies with the regulation ")
  public Boolean getResult() {
    return result;
  }

  public void setResult(Boolean result) {
    this.result = result;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComplianceEvaluation complianceEvaluation = (ComplianceEvaluation) o;
    return Objects.equals(this.datauser, complianceEvaluation.datauser) &&
        Objects.equals(this.datafield, complianceEvaluation.datafield) &&
        Objects.equals(this.action, complianceEvaluation.action) &&
        Objects.equals(this.reason, complianceEvaluation.reason) &&
        Objects.equals(this.result, complianceEvaluation.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(datauser, datafield, action, reason, result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComplianceEvaluation {\n");
    
    sb.append("    datauser: ").append(toIndentedString(datauser)).append("\n");
    sb.append("    datafield: ").append(toIndentedString(datafield)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
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

