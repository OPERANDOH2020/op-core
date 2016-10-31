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


package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.OSPReasonPolicyInput;
import io.swagger.client.model.OSPPrivacyPolicyInput;
import io.swagger.client.model.PrivacyRegulationInput;
import io.swagger.client.model.UserPrivacyPolicy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for PUTApi
 */
public class PUTApiTest {

    private final PUTApi api = new PUTApi();

    
    /**
     * Update OSP text policy entry in the database.
     *
     * Called when by the watchdog detects a change in the textual policy and wants to update the current policy. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void oSPOspIdPrivacyPolicyPutTest() throws ApiException {
        String ospId = null;
        OSPReasonPolicyInput ospPolicy = null;
        // api.oSPOspIdPrivacyPolicyPut(ospId, ospPolicy);

        // TODO: test validations
    }
    
    /**
     * Update OSPBehaviour entry in the database.
     *
     * Called when by the policy computation component when the regulator api updates a regulation. Their new OSPRequest document is stored in the policy DB. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void oSPOspIdPutTest() throws ApiException {
        String ospId = null;
        OSPPrivacyPolicyInput ospPolicy = null;
        // api.oSPOspIdPut(ospId, ospPolicy);

        // TODO: test validations
    }
    
    /**
     * Update PrivacyRegulation entry in the database.
     *
     * Called when by the policy computation component when the regulator api updates a regulation. Their new PrivacyRegulation document is stored in the policy DB. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void regulationsRegIdPutTest() throws ApiException {
        String regId = null;
        PrivacyRegulationInput regulation = null;
        // api.regulationsRegIdPut(regId, regulation);

        // TODO: test validations
    }
    
    /**
     * Update UPP entry in the database for the user.
     *
     * Called when a user makes a change to the UPP registered with operando. Their new privacy preferences are passed in the UPP document and stored in the policy DB. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void userPrivacyPolicyUserIdPutTest() throws ApiException {
        String userId = null;
        UserPrivacyPolicy upp = null;
        // api.userPrivacyPolicyUserIdPut(userId, upp);

        // TODO: test validations
    }
    
}
