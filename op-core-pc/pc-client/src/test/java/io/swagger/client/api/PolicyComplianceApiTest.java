package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.PolicyEvaluationReport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for PolicyComplianceApi
 */
public class PolicyComplianceApiTest {

    private final PolicyComplianceApi api = new PolicyComplianceApi();

    
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
    
}
