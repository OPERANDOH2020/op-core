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
//      Created Date :          2016-04-28
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
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

