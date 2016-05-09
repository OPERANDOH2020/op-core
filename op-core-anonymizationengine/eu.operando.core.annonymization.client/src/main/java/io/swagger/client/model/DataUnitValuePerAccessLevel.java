/*******************************************************************************
 *  * Copyright (c) 2016 {TECNALIA}.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the The MIT License (MIT).
 *  * which accompanies this distribution, and is available at
 *  * http://opensource.org/licenses/MIT
 *  *
 *  * Contributors:
 *  *    Gorka Mikel Echevarría {TECNALIA}
 *  * Initially developed in the context of OPERANDO EU project www.operando.eu
 *******************************************************************************/
package io.swagger.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-03-11T13:10:20.376Z")
public class DataUnitValuePerAccessLevel   {
  
  private String dataUnitId = null;
  private String accessLevelId = null;
  private String value = null;

  
  /**
   * Id of the data unit.
   **/
  public DataUnitValuePerAccessLevel dataUnitId(String dataUnitId) {
    this.dataUnitId = dataUnitId;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Id of the data unit.")
  @JsonProperty("dataUnitId")
  public String getDataUnitId() {
    return dataUnitId;
  }
  public void setDataUnitId(String dataUnitId) {
    this.dataUnitId = dataUnitId;
  }

  
  /**
   * Id of the access level.
   **/
  public DataUnitValuePerAccessLevel accessLevelId(String accessLevelId) {
    this.accessLevelId = accessLevelId;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Id of the access level.")
  @JsonProperty("accessLevelId")
  public String getAccessLevelId() {
    return accessLevelId;
  }
  public void setAccessLevelId(String accessLevelId) {
    this.accessLevelId = accessLevelId;
  }

  
  /**
   * Value of the data unit per the access level.
   **/
  public DataUnitValuePerAccessLevel value(String value) {
    this.value = value;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Value of the data unit per the access level.")
  @JsonProperty("value")
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }

  

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DataUnitValuePerAccessLevel dataUnitValuePerAccessLevel = (DataUnitValuePerAccessLevel) o;
    return Objects.equals(this.dataUnitId, dataUnitValuePerAccessLevel.dataUnitId) &&
        Objects.equals(this.accessLevelId, dataUnitValuePerAccessLevel.accessLevelId) &&
        Objects.equals(this.value, dataUnitValuePerAccessLevel.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataUnitId, accessLevelId, value);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DataUnitValuePerAccessLevel {\n");
    
    sb.append("    dataUnitId: ").append(toIndentedString(dataUnitId)).append("\n");
    sb.append("    accessLevelId: ").append(toIndentedString(accessLevelId)).append("\n");
    sb.append("    value: ").append(toIndentedString(value)).append("\n");
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

