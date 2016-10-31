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
import io.swagger.client.model.OSPPrivacyPolicy;
import io.swagger.client.model.OSPReasonPolicy;
import io.swagger.client.model.OSPReasonPolicyInput;
import io.swagger.client.model.OSPPrivacyPolicyInput;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for OSPApi
 */
public class OSPApiTest {

    private final OSPApi api = new OSPApi();

    
    /**
     * Perform a search query across the collection of OSP behaviour.
     *
     * The query contains a json object (names, values) and the request returns the list of documents (regulations) where the filter matches i.e. the document contains fields with those values. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void oSPGetTest() throws ApiException {
        String filter = null;
        // List<OSPPrivacyPolicy> response = api.oSPGet(filter);

        // TODO: test validations
    }
    
    /**
     * Remove the OSPRequest entry in the database.
     *
     * Called when by the policy computation component when the regulator api requests that the regulation be deleted. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void oSPOspIdDeleteTest() throws ApiException {
        String ospId = null;
        // api.oSPOspIdDelete(ospId);

        // TODO: test validations
    }
    
    /**
     * Read a given OSP behaviour policy.
     *
     * Get a specific OSP document via the id. This will return the full OSP document in json format. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void oSPOspIdGetTest() throws ApiException {
        String ospId = null;
        // OSPPrivacyPolicy response = api.oSPOspIdGet(ospId);

        // TODO: test validations
    }
    
    /**
     * Get the current set of privacy policy statements about the usage of data for stated reasons.
     *
     * This is a machine readable version of a G2C privacy policy statement entered using the OSP Admin dashboard; and retrieved by both the OSP &amp; PSP analyst dashboard for display purposes and also by the OSE component for checking regulation compliance.  
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void oSPOspIdPrivacyPolicyGetTest() throws ApiException {
        String ospId = null;
        // OSPReasonPolicy response = api.oSPOspIdPrivacyPolicyGet(ospId);

        // TODO: test validations
    }
    
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
     * Create a new OSP entry in the database.
     *
     * Called by the policy computation component when a new regulation is added to OPERANDO. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void oSPPostTest() throws ApiException {
        OSPPrivacyPolicyInput ospPolicy = null;
        // api.oSPPost(ospPolicy);

        // TODO: test validations
    }
    
}
