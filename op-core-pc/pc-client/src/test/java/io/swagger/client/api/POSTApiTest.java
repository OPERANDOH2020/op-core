package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.PolicyEvaluationReport;
import io.swagger.client.model.ComputationResult;
import io.swagger.client.model.UserPreference;
import io.swagger.client.model.OSPDataRequest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for POSTApi
 */
public class POSTApiTest {

    private final POSTApi api = new POSTApi();

    
    /**
     * Evaluate a set of OSP requests against the current legislation
     *
     * Method to run a new OSP registers with OPERANDO; or a an OSP changes its policy. The computational resource reads the sector information for the OSP (from the policy db) and then finds all the relevant regulations. Given the policy input, it then checks that this against the discovered regulation. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void ospComplianceCheckerPostTest() throws ApiException {
        String ospId = null;
        Object ospRequest = null;
        // PolicyEvaluationReport response = api.ospComplianceCheckerPost(ospId, ospRequest);

        // TODO: test validations
    }
    
    /**
     * Computes the UPP policy for a specific G2C OSP.
     *
     * This is a computational resource accessed via a REST POST call to carry out a computation on the provided inputs. This results in an resource that is created and stored in the Policy DB and can be accessed by the URL returned as a result of this operation. In OPERANDO, this method is called by the management console when a user subscribes to a new OSP service. The user simply enters answers the specific OSP questionnaire. This will create a simple UPP for that OSP and store this policy within the larger UPP record. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void ospPolicyComputerPostTest() throws ApiException {
        String userId = null;
        String ospId = null;
        List<UserPreference> ospPrefs = null;
        // ComputationResult response = api.ospPolicyComputerPost(userId, ospId, ospPrefs);

        // TODO: test validations
    }
    
    /**
     * Evaluate a set of OSP requests against the User Privacy Policy
     *
     * Called by the Rights Management Component when evaluating if an OSP request or set of requests matches the user preferences stored in the user&#39;s UPP. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void ospPolicyEvaluatorPostTest() throws ApiException {
        String userId = null;
        String ospId = null;
        List<OSPDataRequest> ospRequest = null;
        // PolicyEvaluationReport response = api.ospPolicyEvaluatorPost(userId, ospId, ospRequest);

        // TODO: test validations
    }
    
    /**
     * Creates the UPP when the user registers with OPERANDO.
     *
     * This is a computational resource accessed via a REST POST call to carry out a computation on the provided inputs. This results in an resource that is created and stored in the Policy DB and can be accessed by the URL returned as a result of this operation. In OPERANDO, this method is called by the management console when a user registers with OPERANDO. The user simply enters privacy details and answers a general questionnaire.  
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void policyComputerPostTest() throws ApiException {
        String userId = null;
        List<UserPreference> generalPreferences = null;
        // ComputationResult response = api.policyComputerPost(userId, generalPreferences);

        // TODO: test validations
    }
    
}
