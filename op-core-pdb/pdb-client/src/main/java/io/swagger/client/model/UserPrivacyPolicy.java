package io.swagger.client.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.OSPConsents;
import io.swagger.client.model.OSPSettings;
import io.swagger.client.model.UserPreference;
import java.util.ArrayList;
import java.util.List;





@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-04-19T15:50:46.998Z")
public class UserPrivacyPolicy   {
  
  private String userId = null;
  private List<UserPreference> userPreferences = new ArrayList<UserPreference>();
  private List<OSPConsents> subscribedOspPolicies = new ArrayList<OSPConsents>();
  private List<OSPSettings> subscribedOspSettings = new ArrayList<OSPSettings>();

  
  /**
   * The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.
   **/
  public UserPrivacyPolicy userId(String userId) {
    this.userId = userId;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.")
  @JsonProperty("user_id")
  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }


  /**
   * User stated preferences (questionnaire answers)
   **/
  public UserPrivacyPolicy userPreferences(List<UserPreference> userPreferences) {
    this.userPreferences = userPreferences;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "User stated preferences (questionnaire answers)")
  @JsonProperty("user_preferences")
  public List<UserPreference> getUserPreferences() {
    return userPreferences;
  }
  public void setUserPreferences(List<UserPreference> userPreferences) {
    this.userPreferences = userPreferences;
  }


  /**
   * The user policies for each OSP they subscribe to.
   **/
  public UserPrivacyPolicy subscribedOspPolicies(List<OSPConsents> subscribedOspPolicies) {
    this.subscribedOspPolicies = subscribedOspPolicies;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "The user policies for each OSP they subscribe to.")
  @JsonProperty("subscribed_osp_policies")
  public List<OSPConsents> getSubscribedOspPolicies() {
    return subscribedOspPolicies;
  }
  public void setSubscribedOspPolicies(List<OSPConsents> subscribedOspPolicies) {
    this.subscribedOspPolicies = subscribedOspPolicies;
  }


  /**
   * The user settings for each of their services
   **/
  public UserPrivacyPolicy subscribedOspSettings(List<OSPSettings> subscribedOspSettings) {
    this.subscribedOspSettings = subscribedOspSettings;
    return this;
  }
  
  @ApiModelProperty(example = "null", value = "The user settings for each of their services")
  @JsonProperty("subscribed_osp_settings")
  public List<OSPSettings> getSubscribedOspSettings() {
    return subscribedOspSettings;
  }
  public void setSubscribedOspSettings(List<OSPSettings> subscribedOspSettings) {
    this.subscribedOspSettings = subscribedOspSettings;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserPrivacyPolicy userPrivacyPolicy = (UserPrivacyPolicy) o;
    return Objects.equals(this.userId, userPrivacyPolicy.userId) &&
        Objects.equals(this.userPreferences, userPrivacyPolicy.userPreferences) &&
        Objects.equals(this.subscribedOspPolicies, userPrivacyPolicy.subscribedOspPolicies) &&
        Objects.equals(this.subscribedOspSettings, userPrivacyPolicy.subscribedOspSettings);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, userPreferences, subscribedOspPolicies, subscribedOspSettings);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserPrivacyPolicy {\n");
    
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    userPreferences: ").append(toIndentedString(userPreferences)).append("\n");
    sb.append("    subscribedOspPolicies: ").append(toIndentedString(subscribedOspPolicies)).append("\n");
    sb.append("    subscribedOspSettings: ").append(toIndentedString(subscribedOspSettings)).append("\n");
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

