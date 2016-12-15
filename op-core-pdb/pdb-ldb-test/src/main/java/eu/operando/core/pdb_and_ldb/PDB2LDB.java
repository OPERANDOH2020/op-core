/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.operando.core.pdb_and_ldb;

import eu.operando.core.pdb.client.api.GETApi;
import eu.operando.core.pdb.common.model.UserPrivacyPolicy;
import io.swagger.client.ApiClient;
import io.swagger.client.api.LogSearchApi;
import io.swagger.client.model.InlineResponse200;
import io.swagger.client.model.LogResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author sysman
 */
public class PDB2LDB {

    private final LogSearchApi api = new LogSearchApi();
    private String testUser = "xxxxxx";
    
    
    /**
     * search LogDB for a particular log, e.g.
     *
     * @param basePath
     */
    public void getLogs() {
        Random random = new Random();
        this.testUser = String.format("%09d", random.nextInt(1000000000));
        
        String dateFrom = "";
        String dateTo = "";
        String logLevel = "";
        String requesterType = "";
        String requesterId = "userPrivacyPolicyUserIdGet";
        String logPriority = "";
        String title = this.testUser;
        List<String> keyWords = new ArrayList<String>(Arrays.asList(""));
        
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("http://integration.operando.esilab.org:8091/operando/core/ldbsearch");
        LogSearchApi api = new LogSearchApi(apiClient);

        try {
            InlineResponse200 response = api.getLogs(dateFrom, dateTo, logLevel, requesterType, requesterId, logPriority, title, keyWords);
            for (LogResponse logResp : response.getData()) {
                System.out.println("LOG line:" + logResp.toString());
            }
        } catch (io.swagger.client.ApiException e) {
            System.err.println("Exception when calling getLogs");
            e.printStackTrace();
        }

        // TODO: test validations
    }

    /**
     * This is a simple call to PDB service in order to leave a trace on lobDB
     * service.
     *
     * @param filter
     */
    void getServiceProviders() throws eu.operando.core.pdb.client.ApiException {
        //curl -v http://integration.operando.esilab.org:8096/operando/core/pdb/OSP/?filter=%7B%27policyText%27:%27%27%7D
        String basePath = "http://integration.operando.esilab.org:8096/operando/core/pdb/OSP";
        basePath = "http://localhost:8080/pdb";
        String filter = "%7B%27policyText%27:%27%27%7D";

        eu.operando.core.pdb.client.ApiClient apiClient = new eu.operando.core.pdb.client.ApiClient();
        apiClient.setBasePath(basePath);

        GETApi apiInstance = new GETApi(apiClient);

        UserPrivacyPolicy response = apiInstance.userPrivacyPolicyUserIdGet(this.testUser);
    }

}
