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





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-03-31T13:37:26.696Z")
public class OSP   {
  
  private String id = null;
  private String name = null;
  private String description = null;
  private String ospWebsite = null;

  
  /**
   * Id of the OSP.
   **/
  public OSP id(String id) {
    this.id = id;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Id of the OSP.")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   * Name of the OSP.
   **/
  public OSP name(String name) {
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
  public OSP description(String description) {
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
  public OSP ospWebsite(String ospWebsite) {
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
    OSP OSP = (OSP) o;
    return Objects.equals(this.id, OSP.id) &&
        Objects.equals(this.name, OSP.name) &&
        Objects.equals(this.description, OSP.description) &&
        Objects.equals(this.ospWebsite, OSP.ospWebsite);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, ospWebsite);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OSP {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
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

