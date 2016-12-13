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

    public void test() {
		ApiClient apiClient = new ApiClient();

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
	    logRequest.setDescription("PDB log for testing purposes");
	    logRequest.setLogDataType(LogDataTypeEnum.INFO);
	    logRequest.setTitle("PDB log");
	    logRequest.setLogPriority(LogPriorityEnum.LOW);
	    logRequest.setRequesterId("12341");
	    logRequest.setRequesterType(RequesterTypeEnum.MODULE);
	    ArrayList<String> keywords = new ArrayList<String> ();
	    keywords.add("keyword1");
	    keywords.add("keyword2");
	    keywords.add("keyword3");
		logRequest.setKeywords(keywords );

		Object postBody = logRequest;
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
