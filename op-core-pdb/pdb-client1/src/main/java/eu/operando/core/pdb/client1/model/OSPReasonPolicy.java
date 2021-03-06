/*
 * Policy DB
 * The Policy Database that stores three types of documents in dedicated collections.   1) User Privacy Policy. Each OPERANDO user has one UPP document describing their privacy policies for each of the OSP services they are subscribed to. The UPP contains the current B2C privacy settings for a subscribed to OSP. The UPP contains the users privacy preferences. The UPP contains the G2C access policies for the OSP with justification for access.   2) The legislation policies. The regulations entered into OPERANDO using the OPERANDO regulation API.   3) The OSP descriptions and data requests. For each OSP its privacy policy, its access control rules, and the data it requests (as a workflow). For B2C, the set of privacy settings is stored. 
 *
 * OpenAPI spec version: 1.0.0
 * Contact: support@operando.eu
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package eu.operando.core.pdb.client1.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import eu.operando.core.pdb.client1.model.AccessReason;
import java.util.ArrayList;
import java.util.List;

/**
 * OSPReasonPolicy
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2017-07-13T14:33:54.425Z")
public class OSPReasonPolicy {
  @SerializedName("osp_policy_id")
  private String ospPolicyId = null;

  @SerializedName("policies")
  private List<AccessReason> policies = new ArrayList<AccessReason>();

  public OSPReasonPolicy ospPolicyId(String ospPolicyId) {
    this.ospPolicyId = ospPolicyId;
    return this;
  }

   /**
   * Get ospPolicyId
   * @return ospPolicyId
  **/
  @ApiModelProperty(example = "null", value = "")
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
  @ApiModelProperty(example = "null", value = "The list of access reasons to use a particular data subject types data ")
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
    OSPReasonPolicy osPReasonPolicy = (OSPReasonPolicy) o;
    return Objects.equals(this.ospPolicyId, osPReasonPolicy.ospPolicyId) &&
        Objects.equals(this.policies, osPReasonPolicy.policies);
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

