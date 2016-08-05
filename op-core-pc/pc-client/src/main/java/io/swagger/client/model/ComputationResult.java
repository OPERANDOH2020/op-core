package io.swagger.client.model;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * ComputationResult
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-06-27T09:46:52.939Z")
public class ComputationResult   {
  
  private String status = null;
  private String user = null;
  private String url = null;

  
  /**
   * {Created, Updated, failed} 
   **/
  public ComputationResult status(String status) {
    this.status = status;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "{Created, Updated, failed} ")
  @JsonProperty("status")
  public String getStatus() {
    return status;
  }
  public void setStatus(String status) {
    this.status = status;
  }


  /**
   * The user id of the computation
   **/
  public ComputationResult user(String user) {
    this.user = user;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "The user id of the computation")
  @JsonProperty("user")
  public String getUser() {
    return user;
  }
  public void setUser(String user) {
    this.user = user;
  }


  /**
   * The URL of the computed/updated URL.
   **/
  public ComputationResult url(String url) {
    this.url = url;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "The URL of the computed/updated URL.")
  @JsonProperty("url")
  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ComputationResult computationResult = (ComputationResult) o;
    return Objects.equals(this.status, computationResult.status) &&
        Objects.equals(this.user, computationResult.user) &&
        Objects.equals(this.url, computationResult.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(status, user, url);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ComputationResult {\n");
    
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    user: ").append(toIndentedString(user)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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

