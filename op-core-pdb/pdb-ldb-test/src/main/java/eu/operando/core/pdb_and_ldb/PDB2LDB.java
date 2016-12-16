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
     * search LogDB for a particular log, e.g. searching for testUser. Upon 
     * successful completion the response should include 2 logDB messages.
     *
     * @param basePath
     */
    public int getLogs() {
        int val = 0;
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
            val = response.getData().size();
        } catch (io.swagger.client.ApiException e) {
            System.err.println("Exception when calling getLogs");
            e.printStackTrace();
        }
        return val;
    }

    /**
     * This is a simple call to PDB service in order to leave a trace on lobDB
     * service. The call is to get the UPP from a non existent userId. The call
     * will leave two traces on lobDB server, which should be traceable from a 
     * search log for that userId.
     *
     * @param filter
     */
    UserPrivacyPolicy getUPP() throws eu.operando.core.pdb.client.ApiException {
        String basePath = "http://integration.operando.esilab.org:8096/operando/core/pdb";
        basePath = "http://localhost:8080/pdb";

        eu.operando.core.pdb.client.ApiClient apiClient = new eu.operando.core.pdb.client.ApiClient();
        apiClient.setBasePath(basePath);

        GETApi apiInstance = new GETApi(apiClient);

        UserPrivacyPolicy response = apiInstance.userPrivacyPolicyUserIdGet(this.testUser);
        
        return response;
    }

}
