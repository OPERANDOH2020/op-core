/*
   	* Copyright (c) 2017 {TECNALIA}.
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
package io.swagger.client.model;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * OSPRequest
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-06-10T07:22:24.300Z")
public class OSPRequest   {
  
  private String name = null;
  private String description = null;
  private String ospWebsite = null;

  
  /**
   * Name of the OSP.
   **/
  public OSPRequest name(String name) {
    this.name = name;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Name of the OSP.")
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }


  /**
   * Description of the OSP.
   **/
  public OSPRequest description(String description) {
    this.description = description;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Description of the OSP.")
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }


  /**
   * Website of the OSP.
   **/
  public OSPRequest ospWebsite(String ospWebsite) {
    this.ospWebsite = ospWebsite;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Website of the OSP.")
  @JsonProperty("osp_website")
  public String getOspWebsite() {
    return ospWebsite;
  }
  public void setOspWebsite(String ospWebsite) {
    this.ospWebsite = ospWebsite;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OSPRequest oSPRequest = (OSPRequest) o;
    return Objects.equals(this.name, oSPRequest.name) &&
        Objects.equals(this.description, oSPRequest.description) &&
        Objects.equals(this.ospWebsite, oSPRequest.ospWebsite);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, ospWebsite);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OSPRequest {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    ospWebsite: ").append(toIndentedString(ospWebsite)).append("\n");
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

