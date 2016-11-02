package io.swagger.model;

import java.util.Objects;
import io.swagger.annotations.ApiModelProperty;




/**
 * RequestEvaluation
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-01T07:44:32.102Z")
public class RequestEvaluation   {
  private String datauser = null;

  private String datafield = null;

  private String action = null;

  private Boolean result = null;

  public RequestEvaluation datauser(String datauser) {
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

  public RequestEvaluation datafield(String datafield) {
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

  public RequestEvaluation action(String action) {
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

  public RequestEvaluation result(Boolean result) {
    this.result = result;
    return this;
  }

   /**
   * Whether the request type is granted based on the UPP
   * @return result
  **/
  @ApiModelProperty(value = "Whether the request type is granted based on the UPP ")
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
    RequestEvaluation requestEvaluation = (RequestEvaluation) o;
    return Objects.equals(this.datauser, requestEvaluation.datauser) &&
        Objects.equals(this.datafield, requestEvaluation.datafield) &&
        Objects.equals(this.action, requestEvaluation.action) &&
        Objects.equals(this.result, requestEvaluation.result);
  }

  @Override
  public int hashCode() {
    return Objects.hash(datauser, datafield, action, result);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("RequestEvaluation {\n");

    sb.append("    datauser: ").append(toIndentedString(datauser)).append("\n");
    sb.append("    datafield: ").append(toIndentedString(datafield)).append("\n");
    sb.append("    action: ").append(toIndentedString(action)).append("\n");
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

