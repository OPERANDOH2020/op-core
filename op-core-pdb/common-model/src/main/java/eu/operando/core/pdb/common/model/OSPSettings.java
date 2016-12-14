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

package eu.operando.core.pdb.common.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;






public class OSPSettings   {
  
  private String ospId = null;
  private List<PrivacySetting> ospSettings = new ArrayList<PrivacySetting>();

  
  /**
   * The unique ID of the OSP user is subscribed to and these settings concern. \n
   **/
  public OSPSettings ospId(String ospId) {
    this.ospId = ospId;
    return this;
  }

  
  @ApiModelProperty(value = "The unique ID of the OSP user is subscribed to and these settings concern. \n")
  @JsonProperty("osp_id")
  public String getOspId() {
    return ospId;
  }
  public void setOspId(String ospId) {
    this.ospId = ospId;
  }

  
  /**
   * The list of privacy settings at an OSP
   **/
  public OSPSettings ospSettings(List<PrivacySetting> ospSettings) {
    this.ospSettings = ospSettings;
    return this;
  }

  
  @ApiModelProperty(value = "The list of privacy settings at an OSP")
  @JsonProperty("osp_settings")
  public List<PrivacySetting> getOspSettings() {
    return ospSettings;
  }
  public void setOspSettings(List<PrivacySetting> ospSettings) {
    this.ospSettings = ospSettings;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OSPSettings oSPSettings = (OSPSettings) o;
    return Objects.equals(ospId, oSPSettings.ospId) &&
        Objects.equals(ospSettings, oSPSettings.ospSettings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ospId, ospSettings);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OSPSettings {\n");
    
    sb.append("    ospId: ").append(toIndentedString(ospId)).append("\n");
    sb.append("    ospSettings: ").append(toIndentedString(ospSettings)).append("\n");
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

