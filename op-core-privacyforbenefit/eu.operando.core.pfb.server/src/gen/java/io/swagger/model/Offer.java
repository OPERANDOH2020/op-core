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
package io.swagger.model;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-16T10:39:31.522Z")
public class Offer   {
  
  private String id = null;
  private String ospId = null;
  private String title = null;
  private String description = null;
  private String serviceWebsite = null;
  private Integer isEnabled = null;
  private String ospCallbackUrl = null;
  private Date expirationDate = null;

  /**
   * Id of the offer.
   **/
  public Offer id(String id) {
    this.id = id;
    return this;
  }

  
  @ApiModelProperty(value = "Id of the offer.")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Id of the OSP to which belongs the offer.
   **/
  public Offer ospId(String ospId) {
    this.ospId = ospId;
    return this;
  }

  
  @ApiModelProperty(value = "Id of the OSP to which belongs the offer.")
  @JsonProperty("osp_id")
  public String getOspId() {
    return ospId;
  }
  public void setOspId(String ospId) {
    this.ospId = ospId;
  }

  /**
   * Description of the offer.
   **/
  public Offer title(String title) {
    this.title = title;
    return this;
  }

  
  @ApiModelProperty(value = "Description of the offer.")
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Description of the offer.
   **/
  public Offer description(String description) {
    this.description = description;
    return this;
  }

  
  @ApiModelProperty(value = "Description of the offer.")
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Website of the offering.
   **/
  public Offer serviceWebsite(String serviceWebsite) {
    this.serviceWebsite = serviceWebsite;
    return this;
  }

  
  @ApiModelProperty(value = "Website of the offering.")
  @JsonProperty("service_website")
  public String getServiceWebsite() {
    return serviceWebsite;
  }
  public void setServiceWebsite(String serviceWebsite) {
    this.serviceWebsite = serviceWebsite;
  }

  /**
   * Indication whether the offer is enabled or not.
   **/
  public Offer isEnabled(Integer isEnabled) {
    this.isEnabled = isEnabled;
    return this;
  }

  
  @ApiModelProperty(value = "Indication whether the offer is enabled or not.")
  @JsonProperty("is_enabled")
  public Integer getIsEnabled() {
    return isEnabled;
  }
  public void setIsEnabled(Integer isEnabled) {
    this.isEnabled = isEnabled;
  }

  /**
   * Callback url.
   **/
  public Offer ospCallbackUrl(String ospCallbackUrl) {
    this.ospCallbackUrl = ospCallbackUrl;
    return this;
  }

  
  @ApiModelProperty(value = "Callback url.")
  @JsonProperty("osp_callback_url")
  public String getOspCallbackUrl() {
    return ospCallbackUrl;
  }
  public void setOspCallbackUrl(String ospCallbackUrl) {
    this.ospCallbackUrl = ospCallbackUrl;
  }

  /**
   * Date when the offer expires.
   **/
  public Offer expirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
    return this;
  }

  
  @ApiModelProperty(value = "Date when the offer expires.")
  @JsonProperty("expiration_date")
  public Date getExpirationDate() {
    return expirationDate;
  }
  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Offer offer = (Offer) o;
    return Objects.equals(id, offer.id) &&
        Objects.equals(ospId, offer.ospId) &&
        Objects.equals(title, offer.title) &&
        Objects.equals(description, offer.description) &&
        Objects.equals(serviceWebsite, offer.serviceWebsite) &&
        Objects.equals(isEnabled, offer.isEnabled) &&
        Objects.equals(ospCallbackUrl, offer.ospCallbackUrl) &&
        Objects.equals(expirationDate, offer.expirationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ospId, title, description, serviceWebsite, isEnabled, ospCallbackUrl, expirationDate);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Offer {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    ospId: ").append(toIndentedString(ospId)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    serviceWebsite: ").append(toIndentedString(serviceWebsite)).append("\n");
    sb.append("    isEnabled: ").append(toIndentedString(isEnabled)).append("\n");
    sb.append("    ospCallbackUrl: ").append(toIndentedString(ospCallbackUrl)).append("\n");
    sb.append("    expirationDate: ").append(toIndentedString(expirationDate)).append("\n");
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

