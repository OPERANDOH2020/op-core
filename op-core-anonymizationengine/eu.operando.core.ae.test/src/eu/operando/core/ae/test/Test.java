package eu.operando.core.ae.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.JUnitCore;

import com.sun.jersey.api.client.GenericType;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.Pair;
import io.swagger.client.model.DataUnit;
import io.swagger.client.model.DataUnitValuePerAccessLevel;

public class Test {	

	@org.junit.Test
	public void postInvocationTest() {		
		ApiClient apiClient = new ApiClient();
		 
	    byte[] postBinaryBody = null; 
	     
	    // create path and map variables 
	    String path = "/operando/core/ae/dataUnit"; 
	 
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
	 	    
	    GenericType returnType = new GenericType<String>() {};
				
		DataUnit dataUnit = new DataUnit();
		List<DataUnitValuePerAccessLevel> valuesPerAccessLevel = new ArrayList<>();
		DataUnitValuePerAccessLevel dataUnitValuePerAccessLevel = new DataUnitValuePerAccessLevel();
		dataUnitValuePerAccessLevel.setDataUnitId("1");
		dataUnitValuePerAccessLevel.setAccessLevelId("0");
		dataUnitValuePerAccessLevel.setValue("Complete");
		valuesPerAccessLevel.add(0, dataUnitValuePerAccessLevel);
		dataUnit.setId("1");
		dataUnit.setDescription("Education");		
		
		Object postBody = dataUnit;
		try {
			String str = apiClient.invokeAPI(path,"POST", queryParams, postBody, headerParams, formParams, accept, contentType, authNames, returnType);
			System.out.println(str);			
		} catch (ApiException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
				
	}
	@org.junit.Test	
	public void getInvocationTest() {
		ApiClient apiClient = new ApiClient();
		Object localVarPostBody = null;
		String requester_id = "1";
		
		String path = "/operando/core/ae/personaldata/{requester_id}/search".replaceAll("\\{" + "requester_id" + "\\}", 
	    		apiClient.escapeString(requester_id)); 	    	

	    // query params
	    List<Pair> localVarQueryParams = new ArrayList<Pair>();
	    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
	    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


	    localVarFormParams.put("query", "operando_personaldata_view");
	    //localVarQueryParams.addAll(apiClient.parameterToPairs("csv","query", "operando_personaldata_view"));
	    localVarQueryParams = apiClient.parameterToPairs("csv", "query", "operando_personaldata_view"); 
	    
	    final String[] localVarAccepts = {
	      "application/xml", "application/json"
	    };
	    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

	    final String[] localVarContentTypes = {
	      
	    };
	    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

	    String[] localVarAuthNames = new String[] {  };

	    GenericType returnType = new GenericType<String>() {};
	     try {
	    	 String str = apiClient.invokeAPI(path, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, returnType);
	    	 System.out.println(str);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 				
	}
	public static void main(String[] args) throws Exception {                    
	       JUnitCore.main(
	         "eu.operando.core.ae.test.Test");            
	}
}
