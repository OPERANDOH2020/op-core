package eu.operando.core.annonymization.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Pair;
import io.swagger.client.model.LogRequest;
import io.swagger.client.model.LogRequest.LogDataTypeEnum;
import io.swagger.client.model.LogRequest.LogPriorityEnum;
import io.swagger.client.model.LogRequest.RequesterTypeEnum;


public class Test {

	@org.junit.Test
	public void test() {
		//fail("Not yet implemented");
		ApiClient apiClient = new ApiClient();
		 
	    byte[] postBinaryBody = null; 
	     
	    // create path and map variables 
	    String path = "/log"; 
	 
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
	 
	    //TypeRef returnType = new TypeRef<DataUnit>() {}; 
	    GenericType returnType = new GenericType<String>() {};
		

	    LogRequest logRequest = new LogRequest();
	    logRequest.setDescription("First log for testing purposes");
	    logRequest.setLogDataType(LogDataTypeEnum.INFO);
	    logRequest.setTitle("First log");
	    logRequest.setLogPriority(LogPriorityEnum.LOW);
	    logRequest.setRequesterId("1001");
	    logRequest.setRequesterType(RequesterTypeEnum.MODULE_);
	    //LogDataTypeEnum.INFO logDataTypeEnum = new LogDataTypeEnum("Info");
	    
		
		
		Object postBody = logRequest;
		try {
			String str = apiClient.invokeAPI(path,"POST", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
			System.out.println(str);
		} catch (ApiException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		
		//String str = client.invokeAPI("swagger.json", "GET", new HashMap<String, String>(), null, new HashMap<String, String>(), null, "application/json", null, new String[0]);
	}
	
	//prueba con post/dataunit

}
