/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2016
//
// Copyright in this software belongs to University of Southampton
// IT Innovation Centre of Gamma House, Enterprise Road,
// Chilworth Science Park, Southampton, SO16 7NS, UK.
//
// This software may not be used, sold, licensed, transferred, copied
// or reproduced in whole or in part in any manner or form or in or
// on any media by any person other than in accordance with the terms
// of the Licence Agreement supplied with the software, or otherwise
// without the prior written consent of the copyright owners.
//
// This software is distributed WITHOUT ANY WARRANTY, without even the
// implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
// PURPOSE, except where stated in the Licence Agreement supplied with
// the software.
//
//      Created By :            Panos Melas
//      Created Date :          2016-10-28
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

package eu.operando.core.pdb.common.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import eu.operando.core.pdb.common.model.AccessReason;
import java.util.ArrayList;
import java.util.List;




/**
 * OSPReasonPolicyInput
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-10-28T08:28:40.436Z")
public class OSPReasonPolicyInput   {
  private List<AccessReason> policies = new ArrayList<AccessReason>();

  public OSPReasonPolicyInput policies(List<AccessReason> policies) {
    this.policies = policies;
    return this;
  }

  public OSPReasonPolicyInput addPoliciesItem(AccessReason policiesItem) {
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
    OSPReasonPolicyInput oSPReasonPolicyInput = (OSPReasonPolicyInput) o;
    return Objects.equals(this.policies, oSPReasonPolicyInput.policies);
  }

  @Override
  public int hashCode() {
    return Objects.hash(policies);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OSPReasonPolicyInput {\n");
    
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

