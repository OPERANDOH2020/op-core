/*
   	* Copyright (c) 2016 {TECNALIA}.
    * All rights reserved. This program and the accompanying materials
    * are made available under the terms of the The MIT License (MIT).
    * which accompanies this distribution, and is available at
    * http://opensource.org/licenses/MIT
    *
    * Contributors:
    *    Gorka Benguria Elguezabal {TECNALIA}
    *    Gorka Mikel Echevarría {TECNALIA}
    * Initially developed in the context of OPERANDO EU project www.operando.eu
 */
package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.DataUnitValuePerAccessLevel;
import java.util.ArrayList;
import java.util.List;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-10T09:36:15.284Z")
public class PersonalData   {
  
  private String id = null;
  private List<DataUnitValuePerAccessLevel> dataUnitValuesPerAccessLevel = new ArrayList<DataUnitValuePerAccessLevel>();

  
  /**
   * Id of the person.
   **/
  public PersonalData id(String id) {
    this.id = id;
    return this;
  }

  
  @ApiModelProperty(value = "Id of the person.")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   **/
  public PersonalData dataUnitValuesPerAccessLevel(List<DataUnitValuePerAccessLevel> dataUnitValuesPerAccessLevel) {
    this.dataUnitValuesPerAccessLevel = dataUnitValuesPerAccessLevel;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("dataUnitValuesPerAccessLevel")
  public List<DataUnitValuePerAccessLevel> getDataUnitValuesPerAccessLevel() {
    return dataUnitValuesPerAccessLevel;
  }
  public void setDataUnitValuesPerAccessLevel(List<DataUnitValuePerAccessLevel> dataUnitValuesPerAccessLevel) {
    this.dataUnitValuesPerAccessLevel = dataUnitValuesPerAccessLevel;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalData personalData = (PersonalData) o;
    return Objects.equals(id, personalData.id) &&
        Objects.equals(dataUnitValuesPerAccessLevel, personalData.dataUnitValuesPerAccessLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dataUnitValuesPerAccessLevel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalData {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    dataUnitValuesPerAccessLevel: ").append(toIndentedString(dataUnitValuesPerAccessLevel)).append("\n");
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

