package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-03-31T13:37:20.076Z")
public class OSP   {
  
  private String id = null;
  private String name = null;
  private String description = null;
  private String ospWebsite = null;

  
  /**
   * Id of the OSP.
   **/
  public OSP id(String id) {
    this.id = id;
    return this;
  }

  
  @ApiModelProperty(value = "Id of the OSP.")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   * Name of the OSP.
   **/
  public OSP name(String name) {
    this.name = name;
    return this;
  }

  
  @ApiModelProperty(value = "Name of the OSP.")
  @JsonProperty("name")
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }

  
  /**
   * Description of the OSP.
   **/
  public OSP description(String description) {
    this.description = description;
    return this;
  }

  
  @ApiModelProperty(value = "Description of the OSP.")
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }

  
  /**
   * Website of the OSP.
   **/
  public OSP ospWebsite(String ospWebsite) {
    this.ospWebsite = ospWebsite;
    return this;
  }

  
  @ApiModelProperty(value = "Website of the OSP.")
  @JsonProperty("osp_website")
  public String getOspWebsite() {
    return ospWebsite;
  }
  public void setOspWebsite(String ospWebsite) {
    this.ospWebsite = ospWebsite;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OSP OSP = (OSP) o;
    return Objects.equals(id, OSP.id) &&
        Objects.equals(name, OSP.name) &&
        Objects.equals(description, OSP.description) &&
        Objects.equals(ospWebsite, OSP.ospWebsite);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, ospWebsite);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OSP {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    ospWebsite: ").append(toIndentedString(ospWebsite)).append("\n");
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

