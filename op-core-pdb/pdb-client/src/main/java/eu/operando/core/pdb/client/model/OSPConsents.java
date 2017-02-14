/**
 * Policy DB
 * The Policy Database that stores three types of documents in dedicated collections.   1) User Privacy Policy. Each OPERANDO user has one UPP document describing their privacy policies for each of the OSP services they are subscribed to. The UPP contains the current B2C privacy settings for a subscribed to OSP. The UPP contains the users privacy preferences. The UPP contains the G2C access policies for the OSP with justification for access.   2) The legislation policies. The regulations entered into OPERANDO using the OPERANDO regulation API.   3) The OSP descriptions and data requests. For each OSP its privacy policy, its access control rules, and the data it requests (as a workflow). For B2C, the set of privacy settings is stored. 
 *
 * OpenAPI spec version: 1.0.0
 * Contact: support@operando.eu
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package eu.operando.core.pdb.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import eu.operando.core.pdb.common.model.AccessPolicy;
import java.util.ArrayList;
import java.util.List;


/**
 * OSPConsents
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-10-28T10:18:38.563Z")
public class OSPConsents   {
  @SerializedName("osp_id")
  private String ospId = null;

  @SerializedName("access_policies")
  private List<AccessPolicy> accessPolicies = new ArrayList<AccessPolicy>();

  public OSPConsents ospId(String ospId) {
    this.ospId = ospId;
    return this;
  }

   /**
   * The unique ID of the OSP user is subscribed to and these consent policies concern.  
   * @return ospId
  **/
  @ApiModelProperty(example = "null", value = "The unique ID of the OSP user is subscribed to and these consent policies concern.  ")
  public String getOspId() {
    return ospId;
  }

  public void setOspId(String ospId) {
    this.ospId = ospId;
  }

  public OSPConsents accessPolicies(List<AccessPolicy> accessPolicies) {
    this.accessPolicies = accessPolicies;
    return this;
  }

  public OSPConsents addAccessPoliciesItem(AccessPolicy accessPoliciesItem) {
    this.accessPolicies.add(accessPoliciesItem);
    return this;
  }

   /**
   * OSP access policies
   * @return accessPolicies
  **/
  @ApiModelProperty(example = "null", value = "OSP access policies")
  public List<AccessPolicy> getAccessPolicies() {
    return accessPolicies;
  }

  public void setAccessPolicies(List<AccessPolicy> accessPolicies) {
    this.accessPolicies = accessPolicies;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OSPConsents oSPConsents = (OSPConsents) o;
    return Objects.equals(this.ospId, oSPConsents.ospId) &&
        Objects.equals(this.accessPolicies, oSPConsents.accessPolicies);
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
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

