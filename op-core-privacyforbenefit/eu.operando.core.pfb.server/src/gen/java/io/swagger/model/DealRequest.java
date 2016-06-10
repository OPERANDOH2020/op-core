package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-10T07:14:29.764Z")
public class DealRequest   {
  
  private String offerId = null;
  private String userId = null;

  /**
   * Unique ID of the offer.
   **/
  public DealRequest offerId(String offerId) {
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
  public DealRequest userId(String userId) {
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


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DealRequest dealRequest = (DealRequest) o;
    return Objects.equals(offerId, dealRequest.offerId) &&
        Objects.equals(userId, dealRequest.userId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(offerId, userId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DealRequest {\n");
    
    sb.append("    offerId: ").append(toIndentedString(offerId)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
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

