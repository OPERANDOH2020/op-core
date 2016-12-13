/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2016
//
// Copyright in this software belongs to University of Southampton
// IT Innovation Centre of Gamma House, Enterprise Road,
// Chilworth Science Park, Southampton, SO16 7NS, UK.
//
// This software may not be used, sold, licensed, transferred, copied
// or reproduced in whole or in part in any manner or form or in or
// on any media by any person other than in accordance with the terms
// of the Licence Agreement supplied with the software, or otherwise
// without the prior written consent of the copyright owners.
//
// This software is distributed WITHOUT ANY WARRANTY, without even the
// implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
// PURPOSE, except where stated in the Licence Agreement supplied with
// the software.
//
//      Created By :            Panos Melas
//      Created Date :          2016-04-28
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

package eu.operando.core.ose.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.util.JSON;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.GETApi;
import io.swagger.client.api.PUTApi;
import io.swagger.model.OSPSettings;
import io.swagger.model.UserPrivacyPolicy;
import io.swagger.model.OSPDataRequest;
import io.swagger.model.OSPPrivacyPolicy;
import io.swagger.model.PrivacySetting;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

/**
 *
 * @author sysman
 */
public class OspsMongo {

    private MongoClient mongo;
    private DB db;
    private DBCollection ospPSTable;
    private String uppBasePath = "http://localhost:8080/policy_database";

    public OspsMongo() {
        try {
            this.mongo = new MongoClient("localhost", 27017);
            // get database
            this.db = mongo.getDB("ose");
            // get collection
            this.ospPSTable = db.getCollection("osp_ps");
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    public boolean storeOsps(String ospId, OSPPrivacyPolicy ospPolicy) {
        boolean result = true;

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(ospPolicy);
            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;

            ospPSTable.insert(document);
        } catch (MongoException e) {
            result = false;
            e.printStackTrace();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String ospsOspIdPrivacySettingsGet(String ospId, String userId) {
        List<PrivacySetting> listPS = new ArrayList<PrivacySetting>();
        String jsonInString = null;

        System.out.println("GOT: " + ospId + " " + userId);

        // find 
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("osp_id", ospId);
        DBObject result = this.ospPSTable.findOne(searchQuery);
        if (result != null) {
            try {
                System.out.println("FB_LIST: " + result.get("osp_settings").toString());

                JSONArray array = new JSONArray(result.get("osp_settings").toString());

                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                for (int i = 0; i < array.length(); i++) {
                    PrivacySetting psObj = mapper.readValue(array.getJSONObject(i).toString(), PrivacySetting.class);
                    listPS.add(psObj);
                    System.out.println(psObj);
                }

                List<PrivacySetting> userPSList = getUserOSPSettings(ospId, userId);
                System.out.println("userPSList: " + userPSList);

                //Arrays.deepEquals(listPS, userPSList);
                //jsonInString = mapper.writeValueAsString(uppObj);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsonInString;
    }

    List<PrivacySetting> getUserOSPSettings(String ospId, String userId) {
        List<PrivacySetting> listPS = new ArrayList<PrivacySetting>();

        UserPrivacyPolicy upp = null;

        //ApiClient defaultClient = Configuration.getDefaultApiClient();
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(uppBasePath);

        GETApi apiInstance = new GETApi(apiClient);

        try {
            upp = apiInstance.userPrivacyPolicyUserIdGet(userId);
            System.out.println("UPP: " + upp.toString());

        } catch (ApiException e) {
            System.err.println("Exception when calling GETApi");
            e.printStackTrace();
        }

        for (OSPSettings ospSetting : upp.getSubscribedOspSettings()) {
            if (ospSetting.getOspId().equals(ospId)) {
                System.out.println("osp_id match found for " + ospId);
                listPS = (List<PrivacySetting>) (Object) ospSetting.getOspSettings();
                return listPS;
            }
        }

        return null;
    }

    UserPrivacyPolicy getUserPrivacyPolicy(String userId) {

        UserPrivacyPolicy upp = null;

        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(uppBasePath);

        GETApi apiInstance = new GETApi(apiClient);

        try {
            upp = apiInstance.userPrivacyPolicyUserIdGet(userId);
            System.out.println("UPP: " + upp.toString());

        } catch (ApiException e) {
            System.err.println("Exception when calling GETApi");
            e.printStackTrace();
        }

        return upp;
    }

    boolean updateUserPrivacyPolicy(String userId, UserPrivacyPolicy upp) {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(uppBasePath);
        PUTApi apiInstance = new PUTApi(apiClient);
        try {
            apiInstance.userPrivacyPolicyUserIdPut(userId, upp);
            return true;
        } catch (ApiException e) {
            System.err.println("Exception when calling PUTApi");
            e.printStackTrace();
        }

        return false;
    }

    public boolean ospsOspIdPrivacySettingsPut(String ospId, String userId, List<PrivacySetting> ospSettings) {
        boolean result = false;
        BasicDBObject searchQuery;

        try {
            searchQuery = new BasicDBObject().append("ospId", ospId);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return result;
        }

        UserPrivacyPolicy upp = getUserPrivacyPolicy(userId);
        List<PrivacySetting> userPrivacySettings = null;

        List<OSPSettings> uppOspSettings = upp.getSubscribedOspSettings();

        int idx = 0;
        for (OSPSettings ospSetting : uppOspSettings) {
            idx++;
            if (ospSetting.getOspId().equals(ospId)) {
                System.out.println("osp_id match found for " + ospId);
                userPrivacySettings = (List<PrivacySetting>) (Object) ospSetting.getOspSettings();
                break;
            }
        }

        //TODO check userPrivacySettings with ospSettings
        
        //update upp with new OSPSettings
        OSPSettings updatedOspSetting = new OSPSettings();
        updatedOspSetting.setOspId(ospId);
        updatedOspSetting.setOspSettings(ospSettings);
        uppOspSettings.set(idx, updatedOspSetting);
        upp.setSubscribedOspSettings(uppOspSettings);
        updateUserPrivacyPolicy(userId, upp);
        
        return result;

    }

    public boolean updatePrivacytext(Long ospId, String ospPrivacyText) {
        return false;
    }

    public boolean updateWorkflow(Long ospId, OSPDataRequest ospWorkflow) {
        return false;
    }

}
