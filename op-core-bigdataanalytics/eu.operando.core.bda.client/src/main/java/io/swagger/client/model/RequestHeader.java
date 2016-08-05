package io.swagger.client.model;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;


/**
 * RequestHeader
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-06-23T18:56:53.980Z")
public class RequestHeader   {
  
  private String requestId = null;
  private String requesterId = null;
  private String requesterComponentId = null;
  private String proxyId = null;
  private Date requestDateTime = null;

  
  /**
   **/
  public RequestHeader requestId(String requestId) {
    this.requestId = requestId;
    return this;
  }
  
  @ApiModelProperty(example = "null", required = true, value = "")
  @JsonProperty("requestId")
  public String getRequestId() {
    return requestId;
  }
  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }


  /**
   **/
  public RequestHeader requesterId(String requesterId) {
    this.requesterId = requesterId;
    return this;
  }
  
  @ApiModelProperty(example = "null", required = true, value = "")
  @JsonProperty("requesterId")
  public String getRequesterId() {
    return requesterId;
  }
  public void setRequesterId(String requesterId) {
    this.requesterId = requesterId;
  }


  /**
   **/
  public RequestHeader requesterComponentId(String requesterComponentId) {
    this.requesterComponentId = requesterComponentId;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "")
  @JsonProperty("requesterComponentId")
  public String getRequesterComponentId() {
    return requesterComponentId;
  }
  public void setRequesterComponentId(String requesterComponentId) {
    this.requesterComponentId = requesterComponentId;
  }


  /**
   **/
  public RequestHeader proxyId(String proxyId) {
    this.proxyId = proxyId;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "")
  @JsonProperty("proxyId")
  public String getProxyId() {
    return proxyId;
  }
  public void setProxyId(String proxyId) {
    this.proxyId = proxyId;
  }


  /**
   **/
  public RequestHeader requestDateTime(Date requestDateTime) {
    this.requestDateTime = requestDateTime;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "")
  @JsonProperty("requestDateTime")
  public Date getRequestDateTime() {
    return requestDateTime;
  }
  public void setRequestDateTime(Date requestDateTime) {
    this.requestDateTime = requestDateTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RequestHeader requestHeader = (RequestHeader) o;
    return Objects.equals(this.requestId, requestHeader.requestId) &&
        Objects.equals(this.requesterId, requestHeader.requesterId) &&
        Objects.equals(this.requesterComponentId, requestHeader.requesterComponentId) &&
        Objects.equals(this.proxyId, requestHeader.proxyId) &&
        Objects.equals(this.requestDateTime, requestHeader.requestDateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestId, requesterId, requesterComponentId, proxyId, requestDateTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RequestHeader {\n");
    
    sb.append("    requestId: ").append(toIndentedString(requestId)).append("\n");
    sb.append("    requesterId: ").append(toIndentedString(requesterId)).append("\n");
    sb.append("    requesterComponentId: ").append(toIndentedString(requesterComponentId)).append("\n");
    sb.append("    proxyId: ").append(toIndentedString(proxyId)).append("\n");
    sb.append("    requestDateTime: ").append(toIndentedString(requestDateTime)).append("\n");
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

