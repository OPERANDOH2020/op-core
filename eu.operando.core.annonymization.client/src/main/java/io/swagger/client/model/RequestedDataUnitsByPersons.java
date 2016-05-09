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
import java.util.ArrayList;
import java.util.List;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-03-11T13:10:20.376Z")
public class RequestedDataUnitsByPersons   {
  
  private List<String> dataUnitIds = new ArrayList<String>();
  private List<String> personIds = new ArrayList<String>();

  
  /**
   * Ids of the data units.
   **/
  public RequestedDataUnitsByPersons dataUnitIds(List<String> dataUnitIds) {
    this.dataUnitIds = dataUnitIds;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Ids of the data units.")
  @JsonProperty("dataUnitIds")
  public List<String> getDataUnitIds() {
    return dataUnitIds;
  }
  public void setDataUnitIds(List<String> dataUnitIds) {
    this.dataUnitIds = dataUnitIds;
  }

  
  /**
   * Ids of the persons.
   **/
  public RequestedDataUnitsByPersons personIds(List<String> personIds) {
    this.personIds = personIds;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Ids of the persons.")
  @JsonProperty("personIds")
  public List<String> getPersonIds() {
    return personIds;
  }
  public void setPersonIds(List<String> personIds) {
    this.personIds = personIds;
  }

  

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestedDataUnitsByPersons requestedDataUnitsByPersons = (RequestedDataUnitsByPersons) o;
    return Objects.equals(this.dataUnitIds, requestedDataUnitsByPersons.dataUnitIds) &&
        Objects.equals(this.personIds, requestedDataUnitsByPersons.personIds);
  }

  @Override
  public int hashCode() {
    return Objects.hash(dataUnitIds, personIds);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequestedDataUnitsByPersons {\n");
    
    sb.append("    dataUnitIds: ").append(toIndentedString(dataUnitIds)).append("\n");
    sb.append("    personIds: ").append(toIndentedString(personIds)).append("\n");
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

