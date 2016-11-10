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
//      Created By :            Panos Melas/Paul Grace
//      Created Date :          2016-10-28
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////
package io.swagger.model;

import java.util.Objects;
import io.swagger.annotations.ApiModelProperty;

/**
 * UserAccount
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-10T09:30:45.275Z")
public class UserAccount   {
  private String userid = null;

  private String fullname = null;

  private String address = null;

  private String usertype = null;

  private String emailaddress = null;

  private String city = null;

  private String country = null;

  public UserAccount userid(String userid) {
    this.userid = userid;
    return this;
  }

   /**
   * The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.
   * @return userid
  **/
  @ApiModelProperty(value = "The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.")
  public String getUserid() {
    return userid;
  }

  public void setUserid(String userid) {
    this.userid = userid;
  }

  public UserAccount fullname(String fullname) {
    this.fullname = fullname;
    return this;
  }

   /**
   * The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.
   * @return fullname
  **/
  @ApiModelProperty(value = "The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.")
  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public UserAccount address(String address) {
    this.address = address;
    return this;
  }

   /**
   * The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.
   * @return address
  **/
  @ApiModelProperty(value = "The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public UserAccount usertype(String usertype) {
    this.usertype = usertype;
    return this;
  }

   /**
   * The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.
   * @return usertype
  **/
  @ApiModelProperty(value = "The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.")
  public String getUsertype() {
    return usertype;
  }

  public void setUsertype(String usertype) {
    this.usertype = usertype;
  }

  public UserAccount emailaddress(String emailaddress) {
    this.emailaddress = emailaddress;
    return this;
  }

   /**
   * The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.
   * @return emailaddress
  **/
  @ApiModelProperty(value = "The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.")
  public String getEmailaddress() {
    return emailaddress;
  }

  public void setEmailaddress(String emailaddress) {
    this.emailaddress = emailaddress;
  }

  public UserAccount city(String city) {
    this.city = city;
    return this;
  }

   /**
   * The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.
   * @return city
  **/
  @ApiModelProperty(value = "The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.")
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public UserAccount country(String country) {
    this.country = country;
    return this;
  }

   /**
   * The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.
   * @return country
  **/
  @ApiModelProperty(value = "The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserAccount userAccount = (UserAccount) o;
    return Objects.equals(this.userid, userAccount.userid) &&
        Objects.equals(this.fullname, userAccount.fullname) &&
        Objects.equals(this.address, userAccount.address) &&
        Objects.equals(this.usertype, userAccount.usertype) &&
        Objects.equals(this.emailaddress, userAccount.emailaddress) &&
        Objects.equals(this.city, userAccount.city) &&
        Objects.equals(this.country, userAccount.country);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userid, fullname, address, usertype, emailaddress, city, country);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserAccount {\n");

    sb.append("    userid: ").append(toIndentedString(userid)).append("\n");
    sb.append("    fullname: ").append(toIndentedString(fullname)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    usertype: ").append(toIndentedString(usertype)).append("\n");
    sb.append("    emailaddress: ").append(toIndentedString(emailaddress)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
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

