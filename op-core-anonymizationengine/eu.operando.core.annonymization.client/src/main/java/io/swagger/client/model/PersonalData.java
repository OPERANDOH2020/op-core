package io.swagger.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.DataUnitValuePerAccessLevel;
import java.util.ArrayList;
import java.util.List;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-05-10T09:36:21.749Z")
public class PersonalData   {
  
  private String id = null;
  private List<DataUnitValuePerAccessLevel> dataUnitValuesPerAccessLevel = new ArrayList<DataUnitValuePerAccessLevel>();

  
  /**
   * Id of the person.
   **/
  public PersonalData id(String id) {
    this.id = id;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "Id of the person.")
  @JsonProperty("id")
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   **/
  public PersonalData dataUnitValuesPerAccessLevel(List<DataUnitValuePerAccessLevel> dataUnitValuesPerAccessLevel) {
    this.dataUnitValuesPerAccessLevel = dataUnitValuesPerAccessLevel;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "")
  @JsonProperty("dataUnitValuesPerAccessLevel")
  public List<DataUnitValuePerAccessLevel> getDataUnitValuesPerAccessLevel() {
    return dataUnitValuesPerAccessLevel;
  }
  public void setDataUnitValuesPerAccessLevel(List<DataUnitValuePerAccessLevel> dataUnitValuesPerAccessLevel) {
    this.dataUnitValuesPerAccessLevel = dataUnitValuesPerAccessLevel;
  }

  

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PersonalData personalData = (PersonalData) o;
    return Objects.equals(this.id, personalData.id) &&
        Objects.equals(this.dataUnitValuesPerAccessLevel, personalData.dataUnitValuesPerAccessLevel);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, dataUnitValuesPerAccessLevel);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PersonalData {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    dataUnitValuesPerAccessLevel: ").append(toIndentedString(dataUnitValuesPerAccessLevel)).append("\n");
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

