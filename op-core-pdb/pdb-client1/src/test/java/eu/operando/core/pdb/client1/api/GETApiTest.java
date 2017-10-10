/*
 * Policy DB
 * The Policy Database that stores three types of documents in dedicated collections.   1) User Privacy Policy. Each OPERANDO user has one UPP document describing their privacy policies for each of the OSP services they are subscribed to. The UPP contains the current B2C privacy settings for a subscribed to OSP. The UPP contains the users privacy preferences. The UPP contains the G2C access policies for the OSP with justification for access.   2) The legislation policies. The regulations entered into OPERANDO using the OPERANDO regulation API.   3) The OSP descriptions and data requests. For each OSP its privacy policy, its access control rules, and the data it requests (as a workflow). For B2C, the set of privacy settings is stored. 
 *
 * OpenAPI spec version: 1.0.0
 * Contact: support@operando.eu
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package eu.operando.core.pdb.client1.api;

import eu.operando.core.pdb.client1.api.GETApi;
import eu.operando.core.pdb.client1.ApiException;
import eu.operando.core.pdb.client1.model.OSPPrivacyPolicy;
import eu.operando.core.pdb.client1.model.OSPReasonPolicy;
import eu.operando.core.pdb.client1.model.PrivacyRegulation;
import eu.operando.core.pdb.client1.model.UserPrivacyPolicy;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for GETApi
 */
@Ignore
public class GETApiTest {

    private final GETApi api = new GETApi();

    
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
        List<OSPPrivacyPolicy> response = api.oSPGet(filter);

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
        OSPPrivacyPolicy response = api.oSPOspIdGet(ospId);

        // TODO: test validations
    }
    
    /**
     * Get the list of access reason policy statements.
     *
     * List of policy statements.  
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void oSPOspIdPrivacyPolicyAccessReasonsGetTest() throws ApiException {
        String ospId = null;
        List<OSPReasonPolicy> response = api.oSPOspIdPrivacyPolicyAccessReasonsGet(ospId);

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
        OSPReasonPolicy response = api.oSPOspIdPrivacyPolicyGet(ospId);

        // TODO: test validations
    }
    
    /**
     * Perform a search query across the collection of regulation.
     *
     * The query contains a json object (names, values) and the request returns  the list of documents (regulations) where the filter matches i.e. the  document contains fields with those values. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void regulationsGetTest() throws ApiException {
        String filter = null;
        List<PrivacyRegulation> response = api.regulationsGet(filter);

        // TODO: test validations
    }
    
    /**
     * Read a given legislation with its ID.
     *
     * Get a specific legislation document via the id. This will return the  full legislation document in json format. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void regulationsRegIdGetTest() throws ApiException {
        String regId = null;
        PrivacyRegulation response = api.regulationsRegIdGet(regId);

        // TODO: test validations
    }
    
    /**
     * Perform a search query across the collection of UPPs.
     *
     * The query contains a json object (names, values) and the request returns the list of documents (UPPs) where the filter matches i.e. each document contains fields with those values. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void userPrivacyPolicyGetTest() throws ApiException {
        String filter = null;
        List<UserPrivacyPolicy> response = api.userPrivacyPolicyGet(filter);

        // TODO: test validations
    }
    
    /**
     * Read the user privacy policy for the given user id.
     *
     * Get a specific UPP document via the user&#39;s id. This will return the full user privacy policy document in json format. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void userPrivacyPolicyUserIdGetTest() throws ApiException {
        String userId = null;
        UserPrivacyPolicy response = api.userPrivacyPolicyUserIdGet(userId);

        // TODO: test validations
    }
    
}
