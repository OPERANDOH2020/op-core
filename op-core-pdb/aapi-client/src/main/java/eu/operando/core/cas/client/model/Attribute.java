/*
 * eu.operando.interfaces.aapi
 * Operandos AS interfaces
 *
 * OpenAPI spec version: 0.0.1
 * Contact: kpatsak@gmail.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package eu.operando.core.cas.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Attribute
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-01-19T14:42:13.376Z")
public class Attribute {
  @SerializedName("attr_name")
  private String attrName = null;

  @SerializedName("attr_value")
  private String attrValue = null;

  public Attribute attrName(String attrName) {
    this.attrName = attrName;
    return this;
  }

   /**
   * Get attrName
   * @return attrName
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getAttrName() {
    return attrName;
  }

  public void setAttrName(String attrName) {
    this.attrName = attrName;
  }

  public Attribute attrValue(String attrValue) {
    this.attrValue = attrValue;
    return this;
  }

   /**
   * Get attrValue
   * @return attrValue
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getAttrValue() {
    return attrValue;
  }

  public void setAttrValue(String attrValue) {
    this.attrValue = attrValue;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Attribute attribute = (Attribute) o;
    return Objects.equals(this.attrName, attribute.attrName) &&
        Objects.equals(this.attrValue, attribute.attrValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attrName, attrValue);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Attribute {\n");
    
    sb.append("    attrName: ").append(toIndentedString(attrName)).append("\n");
    sb.append("    attrValue: ").append(toIndentedString(attrValue)).append("\n");
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
