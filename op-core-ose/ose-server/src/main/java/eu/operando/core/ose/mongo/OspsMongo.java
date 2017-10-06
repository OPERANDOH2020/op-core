///////////////////////////////////////////////////////////////////////////
////
//// © University of Southampton IT Innovation Centre, 2016
////
//// Copyright in this software belongs to University of Southampton
//// IT Innovation Centre of Gamma House, Enterprise Road,
//// Chilworth Science Park, Southampton, SO16 7NS, UK.
////
//// This software may not be used, sold, licensed, transferred, copied
//// or reproduced in whole or in part in any manner or form or in or
//// on any media by any person other than in accordance with the terms
//// of the Licence Agreement supplied with the software, or otherwise
//// without the prior written consent of the copyright owners.
////
//// This software is distributed WITHOUT ANY WARRANTY, without even the
//// implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
//// PURPOSE, except where stated in the Licence Agreement supplied with
//// the software.
////
////      Created By :            Panos Melas
////      Created Date :          2016-04-28
////      Created for Project :   OPERANDO
////
///////////////////////////////////////////////////////////////////////////
//package eu.operando.core.ose.mongo;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.DBCursor;
//import com.mongodb.DBObject;
//import com.mongodb.MongoClient;
//import com.mongodb.MongoException;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import com.mongodb.util.JSON;
////import eu.operando.core.pdb.client.ApiClient;
////import eu.operando.core.pdb.client.ApiException;
//import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;
//import eu.operando.core.pdb.common.model.OSPDataRequest;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import org.bson.Document;
//import org.codehaus.jackson.JsonGenerationException;
//import org.codehaus.jackson.map.DeserializationConfig;
//import org.codehaus.jackson.map.JsonMappingException;
//import org.codehaus.jackson.map.ObjectMapper;
//
///**
// * The set of operations used by the OSE component to access information stored
// * in the Mongo DB instance of the policy database (PDB).
// */
//public class OspsMongo {
//
//    private MongoClient mongo;
//
//    private MongoCollection<Document> ospsPSCollection;
//    private MongoCollection<Document> ospsPPCollection;
//    private MongoCollection<Document> uppCollection;
//
//    private String uppBasePath = "http://localhost:8080/policy_database";
//
//    private DB db;
//    private DB db2;
//    /**
//     * Set of Privacy Settings Records - may be deprecated later.
//     */
//    private DBCollection ospPSTable;
//
//    /**
//     * The User Privacy Policy records.
//     */
//    private DBCollection uppTable;
//
//    /**
//     * The OSP Privacy Policy reason records.
//     */
//    private DBCollection ospPPTable;
//
//    public OspsMongo(String hostname, int port) {
//        mongo = new MongoClient(hostname, port);
//        initialiseCollections();
//    }
//
//    public OspsMongo() {
//        try {
//            this.mongo = new MongoClient("localhost", 27017);
//            // get the OSE specific mongo database
//            this.db = mongo.getDB("ose");
//            // get the PDB database
//            this.db2 = mongo.getDB("pdb");
//
//            // get the upp collection of records
//            this.uppTable = db2.getCollection("upp");
//            // get the ps settings collection of records
//            this.ospPSTable = db.getCollection("osp_ps");
//            // get the OSP
//            this.ospPPTable = db2.getCollection("pp");
//        } catch (MongoException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void initialiseCollections() {
//        MongoDatabase oseDatabase;
//        MongoDatabase pdbDatabase;
//
//        // get database
//        oseDatabase = mongo.getDatabase("ose");
//        pdbDatabase = mongo.getDatabase("pdb");
//
//        // get collection
//        ospsPSCollection = oseDatabase.getCollection("osp_ps");
//        ospsPPCollection = oseDatabase.getCollection("pp");
//
//        uppCollection = pdbDatabase.getCollection("upp");
//
//        //this.mongo.close();
//    }
//
//    /**
//     * Return the list of OSP identifiers who fall into the named sector.
//     *
//     * @param sector The sector type e.g. healthcare. Return the list of
//     * OPERANDO OSP ids.
//     */
//    public List<String> getOSPbySector(String sector) {
//        List<String> listOSPs = new ArrayList<String>();
//        BasicDBObject whereQuery = new BasicDBObject();
//        whereQuery.put("sector", sector);
//
//        DBCursor cursor = this.ospPPTable.find(whereQuery);
//        System.out.println("DB = " + cursor);
//        while (cursor.hasNext()) {
//            DBObject result = cursor.next();
//            if (result != null) {
//                System.out.println(result);
//                eu.operando.core.pdb.common.model.OSPReasonPolicy ospObj = getOSPReasonPolicy(result);
//                listOSPs.add(ospObj.getOspPolicyId());
//            }
//        }
//        return listOSPs;
//    }
//
//    /**
//     * Get the set of users who have subscribed to an OSP
//     *
//     * @param ospId The OPERANDO ID of the OSP
//     * @return The list of operando user ids.
//     */
//    public List<String> getUserIdsSubscribedToOSP(String ospId) {
//        List<String> listUsers = new ArrayList<String>();
//        BasicDBObject whereQuery = new BasicDBObject();
//        whereQuery.put("subscribedOspPolicies.ospId", ospId);
//        System.out.println(ospId);
//        DBCursor cursor = this.uppTable.find(whereQuery);
//        System.out.println("DB = " + cursor);
//        while (cursor.hasNext()) {
//            DBObject result = cursor.next();
//            if (result != null) {
//                System.out.println(result);
//                eu.operando.core.pdb.common.model.UserPrivacyPolicy uppObj = getUPP(result);
//                listUsers.add(uppObj.getUserId());
//
//            }
//        }
//
//        return listUsers;
//    }
//
//    private eu.operando.core.pdb.common.model.OSPReasonPolicy getOSPReasonPolicy(DBObject regObj) {
//        //System.out.println("regObj: " + regObj.toString());
//        eu.operando.core.pdb.common.model.OSPReasonPolicy prObj = null;
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            prObj = mapper.readValue(regObj.toString(), eu.operando.core.pdb.common.model.OSPReasonPolicy.class);
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return prObj;
//    }
//
//    private eu.operando.core.pdb.common.model.UserPrivacyPolicy getUPP(DBObject regObj) {
//        //System.out.println("regObj: " + regObj.toString());
//        eu.operando.core.pdb.common.model.UserPrivacyPolicy prObj = null;
//        try {
//
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            prObj = mapper.readValue(regObj.toString(), eu.operando.core.pdb.common.model.UserPrivacyPolicy.class);
//            //System.out.println("prObj: " + prObj.toString());
//
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return prObj;
//    }
//
//    public boolean storeOsps(String ospId, OSPPrivacyPolicy ospPolicy) {
//        boolean result = true;
//
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            String jsonInString = mapper.writeValueAsString(ospPolicy);
//            Object obj = JSON.parse(jsonInString);
//            DBObject document = (DBObject) obj;
//
//            ospPSTable.insert(document);
//        } catch (MongoException e) {
//            result = false;
//            e.printStackTrace();
//        } catch (JsonGenerationException e) {
//            e.printStackTrace();
//        } catch (JsonMappingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
////    public String ospsOspIdPrivacySettingsGet(String ospId, String userId) {
////        List<PrivacySetting> listPS = new ArrayList<PrivacySetting>();
////        String jsonInString = null;
////
////        System.out.println("GOT: " + ospId + " " + userId);
////
////        // find
////        BasicDBObject searchQuery = new BasicDBObject();
////        searchQuery.put("osp_id", ospId);
////        DBObject result = this.ospPSTable.findOne(searchQuery);
////        if (result != null) {
////            try {
////                System.out.println("FB_LIST: " + result.get("osp_settings").toString());
////
////                JSONArray array = new JSONArray(result.get("osp_settings").toString());
////
////                ObjectMapper mapper = new ObjectMapper();
////                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
////
////                for (int i = 0; i < array.length(); i++) {
////                    PrivacySetting psObj = mapper.readValue(array.getJSONObject(i).toString(), PrivacySetting.class);
////                    listPS.add(psObj);
////                    System.out.println(psObj);
////                }
////
////                List<PrivacySetting> userPSList = getUserOSPSettings(ospId, userId);
////                System.out.println("userPSList: " + userPSList);
////
////                //Arrays.deepEquals(listPS, userPSList);
////                //jsonInString = mapper.writeValueAsString(uppObj);
////            } catch (JSONException e) {
////                e.printStackTrace();
////            } catch (JsonGenerationException e) {
////                e.printStackTrace();
////            } catch (JsonMappingException e) {
////                e.printStackTrace();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
////
////        return jsonInString;
////    }
//
////    List<PrivacySetting> getUserOSPSettings(String ospId, String userId) {
////        List<PrivacySetting> listPS = new ArrayList<PrivacySetting>();
////
////        UserPrivacyPolicy upp = null;
////
////        //ApiClient defaultClient = Configuration.getDefaultApiClient();
////        ApiClient apiClient = new ApiClient();
////        apiClient.setBasePath(uppBasePath);
////
////        GETApi apiInstance = new GETApi(apiClient);
////
////        try {
////            upp = apiInstance.userPrivacyPolicyUserIdGet(userId);
////            System.out.println("UPP: " + upp.toString());
////
////        } catch (ApiException e) {
////            System.err.println("Exception when calling GETApi");
////            e.printStackTrace();
////        }
////
////        for (OSPSettings ospSetting : upp.getSubscribedOspSettings()) {
////            if (ospSetting.getOspId().equals(ospId)) {
////                System.out.println("osp_id match found for " + ospId);
////                listPS = (List<PrivacySetting>) (Object) ospSetting.getOspSettings();
////                return listPS;
////            }
////        }
////
////        return null;
////    }
//
////    UserPrivacyPolicy getUserPrivacyPolicy(String userId) {
////
////        UserPrivacyPolicy upp = null;
////
////        ApiClient apiClient = new ApiClient();
////        apiClient.setBasePath(uppBasePath);
////
////        GETApi apiInstance = new GETApi(apiClient);
////
////        try {
////            upp = apiInstance.userPrivacyPolicyUserIdGet(userId);
////            System.out.println("UPP: " + upp.toString());
////
////        } catch (ApiException e) {
////            System.err.println("Exception when calling GETApi");
////            e.printStackTrace();
////        }
////
////        return upp;
////    }
//
////    boolean updateUserPrivacyPolicy(String userId, UserPrivacyPolicy upp) {
////        ApiClient apiClient = new ApiClient();
////        apiClient.setBasePath(uppBasePath);
////        PUTApi apiInstance = new PUTApi(apiClient);
////        try {
////            apiInstance.userPrivacyPolicyUserIdPut(userId, upp);
////            return true;
////        } catch (ApiException e) {
////            System.err.println("Exception when calling PUTApi");
////            e.printStackTrace();
////        }
////
////        return false;
////    }
//
////    public boolean ospsOspIdPrivacySettingsPut(String ospId, String userId, List<PrivacySetting> ospSettings) {
////        boolean result = false;
////        BasicDBObject searchQuery;
////
////        try {
////            searchQuery = new BasicDBObject().append("ospId", ospId);
////        } catch (IllegalArgumentException e) {
////            e.printStackTrace();
////            return result;
////        }
////
////        UserPrivacyPolicy upp = getUserPrivacyPolicy(userId);
////        List<PrivacySetting> userPrivacySettings = null;
////
////        List<OSPSettings> uppOspSettings = upp.getSubscribedOspSettings();
////
////        int idx = 0;
////        for (OSPSettings ospSetting : uppOspSettings) {
////            idx++;
////            if (ospSetting.getOspId().equals(ospId)) {
////                System.out.println("osp_id match found for " + ospId);
////                userPrivacySettings = (List<PrivacySetting>) (Object) ospSetting.getOspSettings();
////                break;
////            }
////        }
////
////        //TODO check userPrivacySettings with ospSettings
////        //update upp with new OSPSettings
////        OSPSettings updatedOspSetting = new OSPSettings();
////        updatedOspSetting.setOspId(ospId);
////        updatedOspSetting.setOspSettings(ospSettings);
////        uppOspSettings.set(idx, updatedOspSetting);
////        upp.setSubscribedOspSettings(uppOspSettings);
////        updateUserPrivacyPolicy(userId, upp);
////
////        return result;
////
////    }
//
//    public boolean updatePrivacytext(Long ospId, String ospPrivacyText) {
//        return false;
//    }
//
//    public boolean updateWorkflow(Long ospId, OSPDataRequest ospWorkflow) {
//        return false;
//    }
//
//}
