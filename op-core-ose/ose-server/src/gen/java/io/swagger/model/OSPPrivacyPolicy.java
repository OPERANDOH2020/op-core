package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.AccessPolicy;
import io.swagger.model.OSPDataRequest;
import java.util.ArrayList;
import java.util.List;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-25T15:37:02.222Z")
public class OSPPrivacyPolicy   {
  
  private String ospPolicyId = null;
  private String policyText = null;
  private String policyUrl = null;
  private List<OSPDataRequest> workflow = new ArrayList<OSPDataRequest>();
  private List<AccessPolicy> policies = new ArrayList<AccessPolicy>();

  
  /**
   **/
  public OSPPrivacyPolicy ospPolicyId(String ospPolicyId) {
    this.ospPolicyId = ospPolicyId;
    return this;
  }

  
  @ApiModelProperty(value = "")
  @JsonProperty("osp_policy_id")
  public String getOspPolicyId() {
    return ospPolicyId;
  }
  public void setOspPolicyId(String ospPolicyId) {
    this.ospPolicyId = ospPolicyId;
  }

  
  /**
   * The content of the OSP privacy policy, textually described and published. It is\na the full text adverstised by the OSP.\n
   **/
  public OSPPrivacyPolicy policyText(String policyText) {
    this.policyText = policyText;
    return this;
  }

  
  @ApiModelProperty(value = "The content of the OSP privacy policy, textually described and published. It is\na the full text adverstised by the OSP.\n")
  @JsonProperty("policy_text")
  public String getPolicyText() {
    return policyText;
  }
  public void setPolicyText(String policyText) {
    this.policyText = policyText;
  }

  
  /**
   * The url location of the privacy policy of the OSP\n
   **/
  public OSPPrivacyPolicy policyUrl(String policyUrl) {
    this.policyUrl = policyUrl;
    return this;
  }

  
  @ApiModelProperty(value = "The url location of the privacy policy of the OSP\n")
  @JsonProperty("policy_url")
  public String getPolicyUrl() {
    return policyUrl;
  }
  public void setPolicyUrl(String policyUrl) {
    this.policyUrl = policyUrl;
  }

  
  /**
   * The sequence of requests that this OSP makes (simple ordered array list\nin this version). The requests describes the operations that the OSP may\ncarry out on the data. This can be used for compliance checking and computation\nof user policies.\n
   **/
  public OSPPrivacyPolicy workflow(List<OSPDataRequest> workflow) {
    this.workflow = workflow;
    return this;
  }

  
  @ApiModelProperty(value = "The sequence of requests that this OSP makes (simple ordered array list\nin this version). The requests describes the operations that the OSP may\ncarry out on the data. This can be used for compliance checking and computation\nof user policies.\n")
  @JsonProperty("workflow")
  public List<OSPDataRequest> getWorkflow() {
    return workflow;
  }
  public void setWorkflow(List<OSPDataRequest> workflow) {
    this.workflow = workflow;
  }

  
  /**
   * The list of rights that the OSP intends to follow e.g. give X access to Y data for Z purpose. This\ninformation can then be used in calculation of the policy and in compliance checking.\n
   **/
  public OSPPrivacyPolicy policies(List<AccessPolicy> policies) {
    this.policies = policies;
    return this;
  }

  
  @ApiModelProperty(value = "The list of rights that the OSP intends to follow e.g. give X access to Y data for Z purpose. This\ninformation can then be used in calculation of the policy and in compliance checking.\n")
  @JsonProperty("policies")
  public List<AccessPolicy> getPolicies() {
    return policies;
  }
  public void setPolicies(List<AccessPolicy> policies) {
    this.policies = policies;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OSPPrivacyPolicy oSPPrivacyPolicy = (OSPPrivacyPolicy) o;
    return Objects.equals(ospPolicyId, oSPPrivacyPolicy.ospPolicyId) &&
        Objects.equals(policyText, oSPPrivacyPolicy.policyText) &&
        Objects.equals(policyUrl, oSPPrivacyPolicy.policyUrl) &&
        Objects.equals(workflow, oSPPrivacyPolicy.workflow) &&
        Objects.equals(policies, oSPPrivacyPolicy.policies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ospPolicyId, policyText, policyUrl, workflow, policies);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OSPPrivacyPolicy {\n");
    
    sb.append("    ospPolicyId: ").append(toIndentedString(ospPolicyId)).append("\n");
    sb.append("    policyText: ").append(toIndentedString(policyText)).append("\n");
    sb.append("    policyUrl: ").append(toIndentedString(policyUrl)).append("\n");
    sb.append("    workflow: ").append(toIndentedString(workflow)).append("\n");
    sb.append("    policies: ").append(toIndentedString(policies)).append("\n");
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

