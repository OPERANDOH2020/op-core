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

package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * AccessReason
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-10-28T08:28:40.436Z")
public class AccessReason   {
  private String datauser = null;

  private String datasubjecttype = null;

  private String datatype = null;

  private String reason = null;

  public AccessReason datauser(String datauser) {
    this.datauser = datauser;
    return this;
  }

   /**
   * Who is using the data. 
   * @return datauser
  **/
  @ApiModelProperty(value = "Who is using the data. ")
  public String getDatauser() {
    return datauser;
  }

  public void setDatauser(String datauser) {
    this.datauser = datauser;
  }

  public AccessReason datasubjecttype(String datasubjecttype) {
    this.datasubjecttype = datasubjecttype;
    return this;
  }

   /**
   * What is the type or group of the user this private data concerns e.g. patient, helper etc. 
   * @return datasubjecttype
  **/
  @ApiModelProperty(value = "What is the type or group of the user this private data concerns e.g. patient, helper etc. ")
  public String getDatasubjecttype() {
    return datasubjecttype;
  }

  public void setDatasubjecttype(String datasubjecttype) {
    this.datasubjecttype = datasubjecttype;
  }

  public AccessReason datatype(String datatype) {
    this.datatype = datatype;
    return this;
  }

   /**
   * What is the type of data this refers to e.g. e-mail address, medical record etc. 
   * @return datatype
  **/
  @ApiModelProperty(value = "What is the type of data this refers to e.g. e-mail address, medical record etc. ")
  public String getDatatype() {
    return datatype;
  }

  public void setDatatype(String datatype) {
    this.datatype = datatype;
  }

  public AccessReason reason(String reason) {
    this.reason = reason;
    return this;
  }

   /**
   * Usage of this information if for what purpose e.g. marketing, healthcare delivery. 
   * @return reason
  **/
  @ApiModelProperty(value = "Usage of this information if for what purpose e.g. marketing, healthcare delivery. ")
  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccessReason accessReason = (AccessReason) o;
    return Objects.equals(this.datauser, accessReason.datauser) &&
        Objects.equals(this.datasubjecttype, accessReason.datasubjecttype) &&
        Objects.equals(this.datatype, accessReason.datatype) &&
        Objects.equals(this.reason, accessReason.reason);
  }

  @Override
  public int hashCode() {
    return Objects.hash(datauser, datasubjecttype, datatype, reason);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AccessReason {\n");
    
    sb.append("    datauser: ").append(toIndentedString(datauser)).append("\n");
    sb.append("    datasubjecttype: ").append(toIndentedString(datasubjecttype)).append("\n");
    sb.append("    datatype: ").append(toIndentedString(datatype)).append("\n");
    sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
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

