package io.swagger.client.api;

import io.swagger.client.ApiException;
import io.swagger.client.model.InlineResponse200;
import io.swagger.client.model.Error;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for LogSearchApi
 */
public class LogSearchApiTest {

    private final LogSearchApi api = new LogSearchApi();

    
    /**
     * Search logs in database
     *
     * Search logs in database by specifying a filter.
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getLogsTest() throws ApiException {
        String dateFrom = null;
        String dateTo = null;
        String logLevel = null;
        String requesterType = null;
        String requesterId = null;
        String logPriority = null;
        String title = null;
        List<String> keyWords = null;
        // InlineResponse200 response = api.getLogs(dateFrom, dateTo, logLevel, requesterType, requesterId, logPriority, title, keyWords);

        // TODO: test validations
    }
    
}
