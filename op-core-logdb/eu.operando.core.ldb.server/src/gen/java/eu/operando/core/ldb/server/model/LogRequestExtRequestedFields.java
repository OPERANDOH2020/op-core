/*
 * Operando LogDB server component
 * The Operando LogDB server centralises the logging          activities of the Operando platform.
 *
 * OpenAPI spec version: 1.0.0
 * Contact: esilab@tecnalia.org
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package eu.operando.core.ldb.server.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;

/**
 * LogRequestExtRequestedFields
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-10-09T16:21:27.816+02:00")
public class LogRequestExtRequestedFields   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("dob")
  private String dob = null;

  @JsonProperty("address")
  private String address = null;

  public LogRequestExtRequestedFields name(String name) {
    this.name = name;
    return this;
  }

   /**
   * name of the requested field.
   * @return name
  **/
  @JsonProperty("name")
  @ApiModelProperty(value = "name of the requested field.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LogRequestExtRequestedFields dob(String dob) {
    this.dob = dob;
    return this;
  }

   /**
   * dob of the requested field.
   * @return dob
  **/
  @JsonProperty("dob")
  @ApiModelProperty(value = "dob of the requested field.")
  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public LogRequestExtRequestedFields address(String address) {
    this.address = address;
    return this;
  }

   /**
   * address of the requested field.
   * @return address
  **/
  @JsonProperty("address")
  @ApiModelProperty(value = "address of the requested field.")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LogRequestExtRequestedFields logRequestExtRequestedFields = (LogRequestExtRequestedFields) o;
    return Objects.equals(this.name, logRequestExtRequestedFields.name) &&
        Objects.equals(this.dob, logRequestExtRequestedFields.dob) &&
        Objects.equals(this.address, logRequestExtRequestedFields.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, dob, address);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogRequestExtRequestedFields {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    dob: ").append(toIndentedString(dob)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
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
