package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.ComputationResult;
import java.math.BigDecimal;
import io.swagger.client.model.PrivacyRegulation;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for PrivacyRegulationsApi
 */
public class PrivacyRegulationsApiTest {

    private final PrivacyRegulationsApi api = new PrivacyRegulationsApi();

    
    /**
     * 
     *
     * Called by the Regulator API after a   new regulation is entered into OPERANDO. The regulation is first created and stored by the policy DB. Existing UPPs are then evaluated to see if they comply with the regulation. The report is then created at /regulations/{reg_id}/report in the policy db and the url is returned to the user in order that they can retrieve this report. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void regulationsRegIdPostTest() throws ApiException {
        BigDecimal regId = null;
        // ComputationResult response = api.regulationsRegIdPost(regId);

        // TODO: test validations
    }
    
    /**
     * 
     *
     * Called by the Regulator API. An updated regulation is entered into OPERANDO. The regulation is stored in the policy DB. Existing UPPs are then evaluated to see if they comply with the regulation. The report is then updated at /regulations/{reg_id}/report in the policy db and the url is returned to the user in order that they can retrieve this report. Pre-condition -- The regulation must have been written to the system. 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void regulationsRegIdPutTest() throws ApiException {
        BigDecimal regId = null;
        List<PrivacyRegulation> regulation = null;
        // ComputationResult response = api.regulationsRegIdPut(regId, regulation);

        // TODO: test validations
    }
    
}
