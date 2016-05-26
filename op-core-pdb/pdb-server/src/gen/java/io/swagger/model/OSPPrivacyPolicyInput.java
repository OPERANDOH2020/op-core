package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.AccessPolicy;
import io.swagger.model.OSPDataRequest;
import java.util.ArrayList;
import java.util.List;






public class OSPPrivacyPolicyInput   {
  
  private String policyText = null;
  private String policyUrl = null;
  private List<OSPDataRequest> workflow = new ArrayList<OSPDataRequest>();
  private List<AccessPolicy> policies = new ArrayList<AccessPolicy>();

  
  /**
   * The content of the OSP privacy policy, textually described and published. It is\na the full text adverstised by the OSP.\n
   **/
  public OSPPrivacyPolicyInput policyText(String policyText) {
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
  public OSPPrivacyPolicyInput policyUrl(String policyUrl) {
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
  public OSPPrivacyPolicyInput workflow(List<OSPDataRequest> workflow) {
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
  public OSPPrivacyPolicyInput policies(List<AccessPolicy> policies) {
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
    OSPPrivacyPolicyInput oSPPrivacyPolicyInput = (OSPPrivacyPolicyInput) o;
    return Objects.equals(policyText, oSPPrivacyPolicyInput.policyText) &&
        Objects.equals(policyUrl, oSPPrivacyPolicyInput.policyUrl) &&
        Objects.equals(workflow, oSPPrivacyPolicyInput.workflow) &&
        Objects.equals(policies, oSPPrivacyPolicyInput.policies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(policyText, policyUrl, workflow, policies);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OSPPrivacyPolicyInput {\n");
    
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

