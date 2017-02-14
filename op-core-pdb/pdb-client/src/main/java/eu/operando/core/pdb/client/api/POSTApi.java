/**
 * Policy DB
 * The Policy Database that stores three types of documents in dedicated collections.   1) User Privacy Policy. Each OPERANDO user has one UPP document describing their privacy policies for each of the OSP services they are subscribed to. The UPP contains the current B2C privacy settings for a subscribed to OSP. The UPP contains the users privacy preferences. The UPP contains the G2C access policies for the OSP with justification for access.   2) The legislation policies. The regulations entered into OPERANDO using the OPERANDO regulation API.   3) The OSP descriptions and data requests. For each OSP its privacy policy, its access control rules, and the data it requests (as a workflow). For B2C, the set of privacy settings is stored. 
 *
 * OpenAPI spec version: 1.0.0
 * Contact: support@operando.eu
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package eu.operando.core.pdb.client.api;

import eu.operando.core.pdb.client.ApiCallback;
import eu.operando.core.pdb.client.ApiClient;
import eu.operando.core.pdb.client.ApiException;
import eu.operando.core.pdb.client.ApiResponse;
import eu.operando.core.pdb.client.Configuration;
import eu.operando.core.pdb.client.Pair;
import eu.operando.core.pdb.client.ProgressRequestBody;
import eu.operando.core.pdb.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import eu.operando.core.pdb.common.model.OSPPrivacyPolicyInput;
import eu.operando.core.pdb.common.model.PrivacyRegulationInput;
import eu.operando.core.pdb.common.model.PrivacyRegulation;
import eu.operando.core.pdb.common.model.UserPrivacyPolicy;

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

    /* Build call for oSPPost */
    private com.squareup.okhttp.Call oSPPostCall(OSPPrivacyPolicyInput ospPolicy, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = ospPolicy;
        
        // verify the required parameter 'ospPolicy' is set
        if (ospPolicy == null) {
            throw new ApiException("Missing the required parameter 'ospPolicy' when calling oSPPost(Async)");
        }
        

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

    /**
     * Create a new OSP entry in the database.
     * Called by the policy computation component when a new regulation is added to OPERANDO. 
     * @param ospPolicy The first instance of this OSP document (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void oSPPost(OSPPrivacyPolicyInput ospPolicy) throws ApiException {
        oSPPostWithHttpInfo(ospPolicy);
    }

    /**
     * Create a new OSP entry in the database.
     * Called by the policy computation component when a new regulation is added to OPERANDO. 
     * @param ospPolicy The first instance of this OSP document (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> oSPPostWithHttpInfo(OSPPrivacyPolicyInput ospPolicy) throws ApiException {
        com.squareup.okhttp.Call call = oSPPostCall(ospPolicy, null, null);
        return apiClient.execute(call);
    }

    /**
     * Create a new OSP entry in the database. (asynchronously)
     * Called by the policy computation component when a new regulation is added to OPERANDO. 
     * @param ospPolicy The first instance of this OSP document (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call oSPPostAsync(OSPPrivacyPolicyInput ospPolicy, final ApiCallback<Void> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = oSPPostCall(ospPolicy, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
    /* Build call for regulationsPost */
    private com.squareup.okhttp.Call regulationsPostCall(PrivacyRegulationInput regulation, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = regulation;
        
        // verify the required parameter 'regulation' is set
        if (regulation == null) {
            throw new ApiException("Missing the required parameter 'regulation' when calling regulationsPost(Async)");
        }
        

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
        com.squareup.okhttp.Call call = regulationsPostCall(regulation, null, null);
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

        com.squareup.okhttp.Call call = regulationsPostCall(regulation, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<PrivacyRegulation>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for userPrivacyPolicyPost */
    private com.squareup.okhttp.Call userPrivacyPolicyPostCall(UserPrivacyPolicy upp, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = upp;
        
        // verify the required parameter 'upp' is set
        if (upp == null) {
            throw new ApiException("Missing the required parameter 'upp' when calling userPrivacyPolicyPost(Async)");
        }
        

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

    /**
     * Create a new UPP entry in the database for the user.
     * Called when a new user is registered with operando. Their new privacy preferences are passed in the UPP document and stored in the policy DB. 
     * @param upp The first instance of this user&#39;s UPP (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public void userPrivacyPolicyPost(UserPrivacyPolicy upp) throws ApiException {
        userPrivacyPolicyPostWithHttpInfo(upp);
    }

    /**
     * Create a new UPP entry in the database for the user.
     * Called when a new user is registered with operando. Their new privacy preferences are passed in the UPP document and stored in the policy DB. 
     * @param upp The first instance of this user&#39;s UPP (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Void> userPrivacyPolicyPostWithHttpInfo(UserPrivacyPolicy upp) throws ApiException {
        com.squareup.okhttp.Call call = userPrivacyPolicyPostCall(upp, null, null);
        return apiClient.execute(call);
    }

    /**
     * Create a new UPP entry in the database for the user. (asynchronously)
     * Called when a new user is registered with operando. Their new privacy preferences are passed in the UPP document and stored in the policy DB. 
     * @param upp The first instance of this user&#39;s UPP (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call userPrivacyPolicyPostAsync(UserPrivacyPolicy upp, final ApiCallback<Void> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = userPrivacyPolicyPostCall(upp, progressListener, progressRequestListener);
        apiClient.executeAsync(call, callback);
        return call;
    }
}
