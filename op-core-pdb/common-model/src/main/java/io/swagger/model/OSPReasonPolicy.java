package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.AccessReason;
import java.util.ArrayList;
import java.util.List;




/**
 * OSPReasonPolicy
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-10-28T08:28:40.436Z")
public class OSPReasonPolicy   {
  private String ospPolicyId = null;

  private List<AccessReason> policies = new ArrayList<AccessReason>();

  public OSPReasonPolicy ospPolicyId(String ospPolicyId) {
    this.ospPolicyId = ospPolicyId;
    return this;
  }

   /**
   * Get ospPolicyId
   * @return ospPolicyId
  **/
  @ApiModelProperty(value = "")
  public String getOspPolicyId() {
    return ospPolicyId;
  }

  public void setOspPolicyId(String ospPolicyId) {
    this.ospPolicyId = ospPolicyId;
  }

  public OSPReasonPolicy policies(List<AccessReason> policies) {
    this.policies = policies;
    return this;
  }

  public OSPReasonPolicy addPoliciesItem(AccessReason policiesItem) {
    this.policies.add(policiesItem);
    return this;
  }

   /**
   * The list of access reasons to use a particular data subject types data 
   * @return policies
  **/
  @ApiModelProperty(value = "The list of access reasons to use a particular data subject types data ")
  public List<AccessReason> getPolicies() {
    return policies;
  }

  public void setPolicies(List<AccessReason> policies) {
    this.policies = policies;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OSPReasonPolicy oSPReasonPolicy = (OSPReasonPolicy) o;
    return Objects.equals(this.ospPolicyId, oSPReasonPolicy.ospPolicyId) &&
        Objects.equals(this.policies, oSPReasonPolicy.policies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ospPolicyId, policies);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OSPReasonPolicy {\n");
    
    sb.append("    ospPolicyId: ").append(toIndentedString(ospPolicyId)).append("\n");
    sb.append("    policies: ").append(toIndentedString(policies)).append("\n");
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

