package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.AccessPolicy;
import java.util.ArrayList;
import java.util.List;






public class OSPConsents   {
  
  private String ospId = null;
  private List<AccessPolicy> accessPolicies = new ArrayList<AccessPolicy>();

  
  /**
   * The unique ID of the OSP user is subscribed to and these consent policies concern. \n
   **/
  public OSPConsents ospId(String ospId) {
    this.ospId = ospId;
    return this;
  }

  
  @ApiModelProperty(value = "The unique ID of the OSP user is subscribed to and these consent policies concern. \n")
  @JsonProperty("osp_id")
  public String getOspId() {
    return ospId;
  }
  public void setOspId(String ospId) {
    this.ospId = ospId;
  }

  
  /**
   * OSP access policies
   **/
  public OSPConsents accessPolicies(List<AccessPolicy> accessPolicies) {
    this.accessPolicies = accessPolicies;
    return this;
  }

  
  @ApiModelProperty(value = "OSP access policies")
  @JsonProperty("access_policies")
  public List<AccessPolicy> getAccessPolicies() {
    return accessPolicies;
  }
  public void setAccessPolicies(List<AccessPolicy> accessPolicies) {
    this.accessPolicies = accessPolicies;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OSPConsents oSPConsents = (OSPConsents) o;
    return Objects.equals(ospId, oSPConsents.ospId) &&
        Objects.equals(accessPolicies, oSPConsents.accessPolicies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ospId, accessPolicies);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OSPConsents {\n");
    
    sb.append("    ospId: ").append(toIndentedString(ospId)).append("\n");
    sb.append("    accessPolicies: ").append(toIndentedString(accessPolicies)).append("\n");
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
