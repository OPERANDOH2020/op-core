package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.PrivacySetting;
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

