/*
 * Policy DB
 * The Policy Database that stores three types of documents in dedicated collections.   1) User Privacy Policy. Each OPERANDO user has one UPP document describing their privacy policies for each of the OSP services they are subscribed to. The UPP contains the current B2C privacy settings for a subscribed to OSP. The UPP contains the users privacy preferences. The UPP contains the G2C access policies for the OSP with justification for access.   2) The legislation policies. The regulations entered into OPERANDO using the OPERANDO regulation API.   3) The OSP descriptions and data requests. For each OSP its privacy policy, its access control rules, and the data it requests (as a workflow). For B2C, the set of privacy settings is stored. 
 *
 * OpenAPI spec version: 1.0.0
 * Contact: support@operando.eu
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package eu.operando.core.pdb.client1.api;

import eu.operando.core.pdb.client1.ApiCallback;
import eu.operando.core.pdb.client1.ApiClient;
import eu.operando.core.pdb.client1.ApiException;
import eu.operando.core.pdb.client1.ApiResponse;
import eu.operando.core.pdb.client1.Configuration;
import eu.operando.core.pdb.client1.Pair;
import eu.operando.core.pdb.client1.ProgressRequestBody;
import eu.operando.core.pdb.client1.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import eu.operando.core.pdb.client1.model.AccessReason;
import eu.operando.core.pdb.client1.model.OSPPrivacyPolicyInput;
import eu.operando.core.pdb.client1.model.PrivacyRegulation;
import eu.operando.core.pdb.client1.model.PrivacyRegulationInput;
import eu.operando.core.pdb.client1.model.UserPrivacyPolicy;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class POSTApi {
    private ApiClient apiClient;

    public POSTApi() {
        this(Configuration.getDefaultApiClient());
    }

    public POSTApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /* Build call for oSPOspIdPrivacyPolicyAccessReasonsPost */
    private com.squareup.okhttp.Call oSPOspIdPrivacyPolicyAccessReasonsPostCall(String ospId, AccessReason ospPolicy, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = ospPolicy;
        
        // create path and map variables
        String localVarPath = "/OSP/{osp-id}/privacy-policy/access-reasons".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "osp-id" + "\\}", apiClient.escapeString(ospId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call oSPOspIdPrivacyPolicyAccessReasonsPostValidateBeforeCall(String ospId, AccessReason ospPolicy, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'ospId' is set
        if (ospId == null) {
            throw new ApiException("Missing the required parameter 'ospId' when calling oSPOspIdPrivacyPolicyAccessReasonsPost(Async)");
        }
        
        // verify the required parameter 'ospPolicy' is set
        if (ospPolicy == null) {
            throw new ApiException("Missing the required parameter 'ospPolicy' when calling oSPOspIdPrivacyPolicyAccessReasonsPost(Async)");
        }
        
        
        com.squareup.okhttp.Call call = oSPOspIdPrivacyPolicyAccessReasonsPostCall(ospId, ospPolicy, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Create a new access reason statement in the privacy policy.
     * Called by the UI when OSP updating the policy statements 
     * @param ospId The identifier number of an OSP (required)
     * @param ospPolicy The first instance of this new statement. (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void oSPOspIdPrivacyPolicyAccessReasonsPost(String ospId, AccessReason ospPolicy) throws ApiException {
        oSPOspIdPrivacyPolicyAccessReasonsPostWithHttpInfo(ospId, ospPolicy);
    }

    /**
     * Create a new access reason statement in the privacy policy.
     * Called by the UI when OSP updating the policy statements 
     * @param ospId The identifier number of an OSP (required)
     * @param ospPolicy The first instance of this new statement. (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> oSPOspIdPrivacyPolicyAccessReasonsPostWithHttpInfo(String ospId, AccessReason ospPolicy) throws ApiException {
        com.squareup.okhttp.Call call = oSPOspIdPrivacyPolicyAccessReasonsPostValidateBeforeCall(ospId, ospPolicy, null, null);
        return apiClient.execute(call);
    }

    /**
     * Create a new access reason statement in the privacy policy. (asynchronously)
     * Called by the UI when OSP updating the policy statements 
     * @param ospId The identifier number of an OSP (required)
     * @param ospPolicy The first instance of this new statement. (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call oSPOspIdPrivacyPolicyAccessReasonsPostAsync(String ospId, AccessReason ospPolicy, final ApiCallback<Void> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = oSPOspIdPrivacyPolicyAccessReasonsPostValidateBeforeCall(ospId, ospPolicy, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /* Build call for oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut */
    private com.squareup.okhttp.Call oSPOspIdPrivacyPolicyAccessReasonsReasonIdPutCall(String ospId, String reasonId, AccessReason ospPolicy, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = ospPolicy;
        
        // create path and map variables
        String localVarPath = "/OSP/{osp-id}/privacy-policy/access-reasons/{reason-id}".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "osp-id" + "\\}", apiClient.escapeString(ospId.toString()))
        .replaceAll("\\{" + "reason-id" + "\\}", apiClient.escapeString(reasonId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call oSPOspIdPrivacyPolicyAccessReasonsReasonIdPutValidateBeforeCall(String ospId, String reasonId, AccessReason ospPolicy, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'ospId' is set
        if (ospId == null) {
            throw new ApiException("Missing the required parameter 'ospId' when calling oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut(Async)");
        }
        
        // verify the required parameter 'reasonId' is set
        if (reasonId == null) {
            throw new ApiException("Missing the required parameter 'reasonId' when calling oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut(Async)");
        }
        
        // verify the required parameter 'ospPolicy' is set
        if (ospPolicy == null) {
            throw new ApiException("Missing the required parameter 'ospPolicy' when calling oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut(Async)");
        }
        
        
        com.squareup.okhttp.Call call = oSPOspIdPrivacyPolicyAccessReasonsReasonIdPutCall(ospId, reasonId, ospPolicy, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Update an access reason statement in the privacy policy.
     * Called by the UI when OSP updating the policy statements 
     * @param ospId The identifier number of an OSP (required)
     * @param reasonId The identifier of a statement in a policy, is only unique to the policy. (required)
     * @param ospPolicy The updated instance of this OSP policy statement. (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void oSPOspIdPrivacyPolicyAccessReasonsReasonIdPut(String ospId, String reasonId, AccessReason ospPolicy) throws ApiException {
        oSPOspIdPrivacyPolicyAccessReasonsReasonIdPutWithHttpInfo(ospId, reasonId, ospPolicy);
    }

    /**
     * Update an access reason statement in the privacy policy.
     * Called by the UI when OSP updating the policy statements 
     * @param ospId The identifier number of an OSP (required)
     * @param reasonId The identifier of a statement in a policy, is only unique to the policy. (required)
     * @param ospPolicy The updated instance of this OSP policy statement. (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> oSPOspIdPrivacyPolicyAccessReasonsReasonIdPutWithHttpInfo(String ospId, String reasonId, AccessReason ospPolicy) throws ApiException {
        com.squareup.okhttp.Call call = oSPOspIdPrivacyPolicyAccessReasonsReasonIdPutValidateBeforeCall(ospId, reasonId, ospPolicy, null, null);
        return apiClient.execute(call);
    }

    /**
     * Update an access reason statement in the privacy policy. (asynchronously)
     * Called by the UI when OSP updating the policy statements 
     * @param ospId The identifier number of an OSP (required)
     * @param reasonId The identifier of a statement in a policy, is only unique to the policy. (required)
     * @param ospPolicy The updated instance of this OSP policy statement. (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call oSPOspIdPrivacyPolicyAccessReasonsReasonIdPutAsync(String ospId, String reasonId, AccessReason ospPolicy, final ApiCallback<Void> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = oSPOspIdPrivacyPolicyAccessReasonsReasonIdPutValidateBeforeCall(ospId, reasonId, ospPolicy, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /* Build call for oSPPost */
    private com.squareup.okhttp.Call oSPPostCall(OSPPrivacyPolicyInput ospPolicy, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = ospPolicy;
        
        // create path and map variables
        String localVarPath = "/OSP/".replaceAll("\\{format\\}","json");

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call oSPPostValidateBeforeCall(OSPPrivacyPolicyInput ospPolicy, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'ospPolicy' is set
        if (ospPolicy == null) {
            throw new ApiException("Missing the required parameter 'ospPolicy' when calling oSPPost(Async)");
        }
        
        
        com.squareup.okhttp.Call call = oSPPostCall(ospPolicy, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Create a new OSP entry in the database.
     * Called by the policy computation component when a new regulation is added to OPERANDO. 
     * @param ospPolicy The first instance of this OSP document (required)
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public String oSPPost(OSPPrivacyPolicyInput ospPolicy) throws ApiException {
        ApiResponse<String> resp = oSPPostWithHttpInfo(ospPolicy);
        return resp.getData();
    }

    /**
     * Create a new OSP entry in the database.
     * Called by the policy computation component when a new regulation is added to OPERANDO. 
     * @param ospPolicy The first instance of this OSP document (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<String> oSPPostWithHttpInfo(OSPPrivacyPolicyInput ospPolicy) throws ApiException {
        com.squareup.okhttp.Call call = oSPPostValidateBeforeCall(ospPolicy, null, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Create a new OSP entry in the database. (asynchronously)
     * Called by the policy computation component when a new regulation is added to OPERANDO. 
     * @param ospPolicy The first instance of this OSP document (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call oSPPostAsync(OSPPrivacyPolicyInput ospPolicy, final ApiCallback<String> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = oSPPostValidateBeforeCall(ospPolicy, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for regulationsPost */
    private com.squareup.okhttp.Call regulationsPostCall(PrivacyRegulationInput regulation, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = regulation;
        
        // create path and map variables
        String localVarPath = "/regulations/".replaceAll("\\{format\\}","json");

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call regulationsPostValidateBeforeCall(PrivacyRegulationInput regulation, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'regulation' is set
        if (regulation == null) {
            throw new ApiException("Missing the required parameter 'regulation' when calling regulationsPost(Async)");
        }
        
        
        com.squareup.okhttp.Call call = regulationsPostCall(regulation, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Create a new legislation entry in the database.
     * Called by the policy computation component when a new regulation is added to OPERANDO. 
     * @param regulation The first instance of this regulation document (required)
     * @return PrivacyRegulation
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public PrivacyRegulation regulationsPost(PrivacyRegulationInput regulation) throws ApiException {
        ApiResponse<PrivacyRegulation> resp = regulationsPostWithHttpInfo(regulation);
        return resp.getData();
    }

    /**
     * Create a new legislation entry in the database.
     * Called by the policy computation component when a new regulation is added to OPERANDO. 
     * @param regulation The first instance of this regulation document (required)
     * @return ApiResponse&lt;PrivacyRegulation&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<PrivacyRegulation> regulationsPostWithHttpInfo(PrivacyRegulationInput regulation) throws ApiException {
        com.squareup.okhttp.Call call = regulationsPostValidateBeforeCall(regulation, null, null);
        Type localVarReturnType = new TypeToken<PrivacyRegulation>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Create a new legislation entry in the database. (asynchronously)
     * Called by the policy computation component when a new regulation is added to OPERANDO. 
     * @param regulation The first instance of this regulation document (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call regulationsPostAsync(PrivacyRegulationInput regulation, final ApiCallback<PrivacyRegulation> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = regulationsPostValidateBeforeCall(regulation, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<PrivacyRegulation>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for userPrivacyPolicyPost */
    private com.squareup.okhttp.Call userPrivacyPolicyPostCall(UserPrivacyPolicy upp, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = upp;
        
        // create path and map variables
        String localVarPath = "/user_privacy_policy/".replaceAll("\\{format\\}","json");

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] {  };
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }
    
    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call userPrivacyPolicyPostValidateBeforeCall(UserPrivacyPolicy upp, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'upp' is set
        if (upp == null) {
            throw new ApiException("Missing the required parameter 'upp' when calling userPrivacyPolicyPost(Async)");
        }
        
        
        com.squareup.okhttp.Call call = userPrivacyPolicyPostCall(upp, progressListener, progressRequestListener);
        return call;

        
        
        
        
    }

    /**
     * Create a new UPP entry in the database for the user.
     * Called when a new user is registered with operando. Their new privacy preferences are passed in the UPP document and stored in the policy DB. 
     * @param upp The first instance of this user&#39;s UPP (required)
     * @return String
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public String userPrivacyPolicyPost(UserPrivacyPolicy upp) throws ApiException {
        ApiResponse<String> resp = userPrivacyPolicyPostWithHttpInfo(upp);
        return resp.getData();
    }

    /**
     * Create a new UPP entry in the database for the user.
     * Called when a new user is registered with operando. Their new privacy preferences are passed in the UPP document and stored in the policy DB. 
     * @param upp The first instance of this user&#39;s UPP (required)
     * @return ApiResponse&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<String> userPrivacyPolicyPostWithHttpInfo(UserPrivacyPolicy upp) throws ApiException {
        com.squareup.okhttp.Call call = userPrivacyPolicyPostValidateBeforeCall(upp, null, null);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Create a new UPP entry in the database for the user. (asynchronously)
     * Called when a new user is registered with operando. Their new privacy preferences are passed in the UPP document and stored in the policy DB. 
     * @param upp The first instance of this user&#39;s UPP (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call userPrivacyPolicyPostAsync(UserPrivacyPolicy upp, final ApiCallback<String> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = userPrivacyPolicyPostValidateBeforeCall(upp, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<String>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
