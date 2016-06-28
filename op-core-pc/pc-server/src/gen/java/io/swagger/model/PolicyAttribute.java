package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class PolicyAttribute   {
  
  private String attributeName = null;
  private String attributeValue = null;

  /**
   **/
  public PolicyAttribute attributeName(String attributeName) {
    this.attributeName = attributeName;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("attribute_name")
  public String getAttributeName() {
    return attributeName;
  }
  public void setAttributeName(String attributeName) {
    this.attributeName = attributeName;
  }

  /**
   **/
  public PolicyAttribute attributeValue(String attributeValue) {
    this.attributeValue = attributeValue;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("attribute_value")
  public String getAttributeValue() {
    return attributeValue;
  }
  public void setAttributeValue(String attributeValue) {
    this.attributeValue = attributeValue;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PolicyAttribute policyAttribute = (PolicyAttribute) o;
    return Objects.equals(attributeName, policyAttribute.attributeName) &&
        Objects.equals(attributeValue, policyAttribute.attributeValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attributeName, attributeValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PolicyAttribute {\n");
    
    sb.append("    attributeName: ").append(toIndentedString(attributeName)).append("\n");
    sb.append("    attributeValue: ").append(toIndentedString(attributeValue)).append("\n");
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

