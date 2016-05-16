package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.OSPConsents;
import io.swagger.model.OSPSettings;
import io.swagger.model.UserPreference;
import java.util.ArrayList;
import java.util.List;






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

  
  @ApiModelProperty(value = "The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.")
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

  
  @ApiModelProperty(value = "User stated preferences (questionnaire answers)")
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

  
  @ApiModelProperty(value = "The user policies for each OSP they subscribe to.")
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

  
  @ApiModelProperty(value = "The user settings for each of their services")
  @JsonProperty("subscribed_osp_settings")
  public List<OSPSettings> getSubscribedOspSettings() {
    return subscribedOspSettings;
  }
  public void setSubscribedOspSettings(List<OSPSettings> subscribedOspSettings) {
    this.subscribedOspSettings = subscribedOspSettings;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserPrivacyPolicy userPrivacyPolicy = (UserPrivacyPolicy) o;
    return Objects.equals(userId, userPrivacyPolicy.userId) &&
        Objects.equals(userPreferences, userPrivacyPolicy.userPreferences) &&
        Objects.equals(subscribedOspPolicies, userPrivacyPolicy.subscribedOspPolicies) &&
        Objects.equals(subscribedOspSettings, userPrivacyPolicy.subscribedOspSettings);
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
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

