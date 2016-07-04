package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * job subscription
 **/

@ApiModel(description = "job subscription")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-07-03T20:04:20.000Z")
public class OSPJobsSubscriptionRequest   {
  
  private Boolean subscribed = null;
  private String frequency = null;

  /**
   **/
  public OSPJobsSubscriptionRequest subscribed(Boolean subscribed) {
    this.subscribed = subscribed;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("subscribed")
  public Boolean getSubscribed() {
    return subscribed;
  }
  public void setSubscribed(Boolean subscribed) {
    this.subscribed = subscribed;
  }

  /**
   **/
  public OSPJobsSubscriptionRequest frequency(String frequency) {
    this.frequency = frequency;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("frequency")
  public String getFrequency() {
    return frequency;
  }
  public void setFrequency(String frequency) {
    this.frequency = frequency;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OSPJobsSubscriptionRequest oSPJobsSubscriptionRequest = (OSPJobsSubscriptionRequest) o;
    return Objects.equals(subscribed, oSPJobsSubscriptionRequest.subscribed) &&
        Objects.equals(frequency, oSPJobsSubscriptionRequest.frequency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(subscribed, frequency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OSPJobsSubscriptionRequest {\n");
    
    sb.append("    subscribed: ").append(toIndentedString(subscribed)).append("\n");
    sb.append("    frequency: ").append(toIndentedString(frequency)).append("\n");
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

