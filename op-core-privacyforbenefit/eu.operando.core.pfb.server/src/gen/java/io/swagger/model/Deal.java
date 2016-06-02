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
import java.util.Date;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-27T11:58:50.874Z")
public class Deal   {
  
  private String id = null;
  private String offerId = null;
  private String userId = null;
  private Date createdAt = null;
  private Date canceledAt = null;

  
  /**
   * Unique ID of the deal.
   **/
  public Deal id(String id) {
    this.id = id;
    return this;
  }

  
  @ApiModelProperty(value = "Unique ID of the deal.")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   * Unique ID of the offer.
   **/
  public Deal offerId(String offerId) {
    this.offerId = offerId;
    return this;
  }

  
  @ApiModelProperty(value = "Unique ID of the offer.")
  @JsonProperty("offer_id")
  public String getOfferId() {
    return offerId;
  }
  public void setOfferId(String offerId) {
    this.offerId = offerId;
  }

  
  /**
   * Unique ID of the user.
   **/
  public Deal userId(String userId) {
    this.userId = userId;
    return this;
  }

  
  @ApiModelProperty(value = "Unique ID of the user.")
  @JsonProperty("user_id")
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }

  
  /**
   * Date when the deal was created (accepted).
   **/
  public Deal createdAt(Date createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  
  @ApiModelProperty(value = "Date when the deal was created (accepted).")
  @JsonProperty("created_at")
  public Date getCreatedAt() {
    return createdAt;
  }
  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  
  /**
   * Date when the deal was canceled.
   **/
  public Deal canceledAt(Date canceledAt) {
    this.canceledAt = canceledAt;
    return this;
  }

  
  @ApiModelProperty(value = "Date when the deal was canceled.")
  @JsonProperty("canceled_at")
  public Date getCanceledAt() {
    return canceledAt;
  }
  public void setCanceledAt(Date canceledAt) {
    this.canceledAt = canceledAt;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Deal deal = (Deal) o;
    return Objects.equals(id, deal.id) &&
        Objects.equals(offerId, deal.offerId) &&
        Objects.equals(userId, deal.userId) &&
        Objects.equals(createdAt, deal.createdAt) &&
        Objects.equals(canceledAt, deal.canceledAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, offerId, userId, createdAt, canceledAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Deal {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    offerId: ").append(toIndentedString(offerId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    canceledAt: ").append(toIndentedString(canceledAt)).append("\n");
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

