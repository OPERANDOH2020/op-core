package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-03-30T12:50:20.171Z")
public class PrivacySetting   {
  
  private Long id = null;
  private String description = null;
  private String name = null;
  private String settingKey = null;
  private String settingValue = null;

  
  /**
   * PrivacySetting Unique Identifier
   **/
  public PrivacySetting id(Long id) {
    this.id = id;
    return this;
  }

  
  @ApiModelProperty(value = "PrivacySetting Unique Identifier")
  @JsonProperty("id")
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  
  /**
   * Description of the setting
   **/
  public PrivacySetting description(String description) {
    this.description = description;
    return this;
  }

  
  @ApiModelProperty(value = "Description of the setting")
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  
  /**
   * Short name of the setting(e.g. visibility)
   **/
  public PrivacySetting name(String name) {
    this.name = name;
    return this;
  }

  
  @ApiModelProperty(value = "Short name of the setting(e.g. visibility)")
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   * Targeted setting key
   **/
  public PrivacySetting settingKey(String settingKey) {
    this.settingKey = settingKey;
    return this;
  }

  
  @ApiModelProperty(value = "Targeted setting key")
  @JsonProperty("setting_key")
  public String getSettingKey() {
    return settingKey;
  }
  public void setSettingKey(String settingKey) {
    this.settingKey = settingKey;
  }

  
  /**
   * Targeted setting value
   **/
  public PrivacySetting settingValue(String settingValue) {
    this.settingValue = settingValue;
    return this;
  }

  
  @ApiModelProperty(value = "Targeted setting value")
  @JsonProperty("setting_value")
  public String getSettingValue() {
    return settingValue;
  }
  public void setSettingValue(String settingValue) {
    this.settingValue = settingValue;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PrivacySetting privacySetting = (PrivacySetting) o;
    return Objects.equals(id, privacySetting.id) &&
        Objects.equals(description, privacySetting.description) &&
        Objects.equals(name, privacySetting.name) &&
        Objects.equals(settingKey, privacySetting.settingKey) &&
        Objects.equals(settingValue, privacySetting.settingValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, name, settingKey, settingValue);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PrivacySetting {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    settingKey: ").append(toIndentedString(settingKey)).append("\n");
    sb.append("    settingValue: ").append(toIndentedString(settingValue)).append("\n");
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

