/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.operando.core.pdb;

import com.sun.jersey.api.client.GenericType;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Pair;
import io.swagger.client.model.LogRequest;
import io.swagger.client.model.LogRequest.LogDataTypeEnum;
import io.swagger.client.model.LogRequest.LogPriorityEnum;
import io.swagger.client.model.LogRequest.RequesterTypeEnum;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pjg
 */
public class LogDBCall {

    public LogDBCall() {

    }

    public void test() {
        System.out.println("Calling ldb");
		ApiClient apiClient = new ApiClient();
                apiClient.setBasePath("http://integration.operando.esilab.org:8090");
	    byte[] postBinaryBody = null;

	    // create path and map variables
	    String path = "/operando/core/ldb/log";

	    // query params
	    List<Pair> queryParams = new ArrayList<Pair>();
	    Map<String, String> headerParams = new HashMap<String, String>();
	    Map<String, Object> formParams = new HashMap<String, Object>();

	    final String[] accepts = {
	      "application/json", "application/xml"
	    };
	    final String accept = apiClient.selectHeaderAccept(accepts);

	    final String[] contentTypes = {

	    };
	    final String contentType = apiClient.selectHeaderContentType(contentTypes);

	    String[] authNames = new String[] {  };

	    GenericType<String> returnType = new GenericType<String>() {};


	    LogRequest logRequest = new LogRequest();
	    logRequest.setUserId("003");
	    logRequest.setDescription("UPP GET");
	    logRequest.setLogDataType(LogDataTypeEnum.INFO);
	    logRequest.setTitle("Log on 07/12");
	    logRequest.setLogPriority(LogPriorityEnum.LOW);
	    logRequest.setRequesterId("1007");
	    logRequest.setRequesterType(RequesterTypeEnum.MODULE);
	    ArrayList<String> keywords = new ArrayList<String> ();
	    keywords.add("keywordA");
	    keywords.add("keywordB");
	    keywords.add("keywordC");
		logRequest.setKeywords(keywords );

		Object postBody = logRequest;
		System.out.println(postBody);
		String str = "";
		try {
			str = apiClient.invokeAPI(path,"POST", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
		} catch (ApiException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		System.out.println(str);
    }

}
