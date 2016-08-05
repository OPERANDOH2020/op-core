package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.OSPDataRequest;
import io.swagger.client.model.PolicyEvaluationReport;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for PolicyEvaluationApi
 */
public class PolicyEvaluationApiTest {

    private final PolicyEvaluationApi api = new PolicyEvaluationApi();

    
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
    
}
