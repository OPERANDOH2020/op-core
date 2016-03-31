package io.swagger.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-03-31T13:37:26.696Z")
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
  
  @ApiModelProperty(example = "null", value = "Unique ID of the deal.")
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
  
  @ApiModelProperty(example = "null", value = "Unique ID of the offer.")
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
  
  @ApiModelProperty(example = "null", value = "Unique ID of the user.")
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
  
  @ApiModelProperty(example = "null", value = "Date when the deal was created (accepted).")
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
  
  @ApiModelProperty(example = "null", value = "Date when the deal was canceled.")
  @JsonProperty("canceled_at")
  public Date getCanceledAt() {
    return canceledAt;
  }
  public void setCanceledAt(Date canceledAt) {
    this.canceledAt = canceledAt;
  }

  

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Deal deal = (Deal) o;
    return Objects.equals(this.id, deal.id) &&
        Objects.equals(this.offerId, deal.offerId) &&
        Objects.equals(this.userId, deal.userId) &&
        Objects.equals(this.createdAt, deal.createdAt) &&
        Objects.equals(this.canceledAt, deal.canceledAt);
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

