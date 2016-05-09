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
package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-03-16T08:19:16.233Z")
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

  
  @ApiModelProperty(value = "Id of the data unit.")
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

  
  @ApiModelProperty(value = "Id of the access level.")
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

  
  @ApiModelProperty(value = "Value of the data unit per the access level.")
  @JsonProperty("value")
  public String getValue() {
    return value;
  }
  public void setValue(String value) {
    this.value = value;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DataUnitValuePerAccessLevel dataUnitValuePerAccessLevel = (DataUnitValuePerAccessLevel) o;
    return Objects.equals(dataUnitId, dataUnitValuePerAccessLevel.dataUnitId) &&
        Objects.equals(accessLevelId, dataUnitValuePerAccessLevel.accessLevelId) &&
        Objects.equals(value, dataUnitValuePerAccessLevel.value);
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

