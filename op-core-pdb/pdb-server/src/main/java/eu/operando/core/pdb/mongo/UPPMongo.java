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
package eu.operando.core.pdb.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import eu.operando.core.pdb.common.model.UserPrivacyPolicy;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author sysman
 */
public class UPPMongo {

    private MongoClient mongo;
    private UserPrivacyPolicy upp;
    private DB db;
    private DBCollection uppTable;

    public UPPMongo() {
        try {
            this.mongo = new MongoClient("mongo.integration.operando.dmz.lab.esilab.org", 27017);
            //this.mongo = new MongoClient("localhost", 27017);

            // get database
            this.db = mongo.getDB("pdb");

            // get collection
            this.uppTable = db.getCollection("upp");
            System.out.println(this.uppTable.toString());

            this.upp = new UserPrivacyPolicy();
            //} catch (UnknownHostException e) {
            //  e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    public UPPMongo(String hostname, int port) {
        try {
            this.mongo = new MongoClient(hostname, port);

            // get database
            this.db = mongo.getDB("pdb");

            // get collection
            this.uppTable = db.getCollection("upp");
            System.out.println(this.uppTable.toString());

            this.upp = new UserPrivacyPolicy();

        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteUPPById(String uppId) {
        System.out.println("deleting: " + uppId);
        boolean res = false;
        BasicDBObject searchQuery = new BasicDBObject();
        try {
            searchQuery.put("userId", uppId);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return res;
        }

        DBObject result = this.uppTable.findOne(searchQuery);

        if (result == null) {
            res = false;
        } else {
            this.uppTable.remove(result);
            res = true;
        }
        return res;
    }

    public static String toCamelCase(String inputString) {
        String result = "";
        if (inputString.length() == 0) {
            return result;
        }

        char firstChar = inputString.charAt(0);
        boolean setFlag = false;
        result = result + firstChar;
        for (int i = 1; i < inputString.length(); i++) {
            char currentChar = inputString.charAt(i);
            if (currentChar == '_') {
                setFlag = true;
                continue;
            } else {
                if (setFlag) {
                    currentChar = Character.toUpperCase(currentChar);
                    setFlag = false;
                }
            }
            result = result + currentChar;
        }
        return result;
    }

    private boolean isAValidFieldName(String key) {
        Class aClass = UserPrivacyPolicy.class;
        try {
            Field field = aClass.getDeclaredField(key);
        } catch (NoSuchFieldException ex){ 
            System.err.println("no such field found " + key);
            return false;
        }
        return true;
    }
    
    public String getUPPByFilter(String filter) {
        String result = null;
        BasicDBObject query = new BasicDBObject();

        if (filter == null) {
            return "Input error: No UPP ID provided";
        }

        System.out.println("filter expression: " + filter);

        try {
            JSONObject obj = new JSONObject(filter);
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                System.out.println("found key " + key);
                System.out.println("converting key " + toCamelCase(key));
                key = toCamelCase(key);
                if(!isAValidFieldName(key)) {
                    System.out.println("Not a valid key name found: " + key);
                    return null;
                }
                System.out.println("value " + obj.getString(key));
                query.put(key, java.util.regex.Pattern.compile(obj.getString(key)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("Query: " + query.toString());

        List<UserPrivacyPolicy> arrUPPObj = new ArrayList<UserPrivacyPolicy>();

        DBCursor cursor = this.uppTable.find(query);
        while (cursor.hasNext()) {
            BasicDBObject regObj = (BasicDBObject) cursor.next();
            System.out.println("Adding result " + regObj.toString());
            arrUPPObj.add(getUPP(regObj));
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.getSerializationConfig().enable(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING);
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

            result = mapper.writeValueAsString(arrUPPObj);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("getUPPByFilter RESULT (list): " + result);

        return result;
    }

    private UserPrivacyPolicy getUPP(DBObject regObj) {
        //System.out.println("regObj: " + regObj.toString());
        UserPrivacyPolicy prObj = null;
        try {

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            prObj = mapper.readValue(regObj.toString(), UserPrivacyPolicy.class);
            //System.out.println("prObj: " + prObj.toString());

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prObj;
    }

    /**
     * List all the records of the users who have subscribed to a given OSP in
     * the system.
     *
     * @param ospId The Operando Id of the OSP being searched for.
     * @return A list of UPP
     */
    public List<String> getUPPByOSPId(String ospId) {
        List<String> jsonInString = new ArrayList<>();

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("ospId", ospId);
        DBCursor cursor = this.uppTable.find(whereQuery);
        while (cursor.hasNext()) {
            DBObject result = cursor.next();
            if (result != null) {
                try {
                    UserPrivacyPolicy uppObj = getUPP(result);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.getSerializationConfig().enable(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING);
                    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

                    jsonInString.add(mapper.writeValueAsString(uppObj));
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonInString;
    }

    public String getUPPById(String uppId) {
        UserPrivacyPolicy uppObj;
        String jsonInString = null;

        // find
        BasicDBObject searchQuery = new BasicDBObject();
        try {
            searchQuery.put("userId", uppId);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return jsonInString;
        }

        DBObject result = this.uppTable.findOne(searchQuery);
        if (result != null) {
            uppObj = getUPP(result);
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.getSerializationConfig().enable(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING);
                mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

                jsonInString = mapper.writeValueAsString(uppObj);
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return jsonInString;
    }

    public boolean updateUPP(String regId, UserPrivacyPolicy upp) {
        boolean result = false;
        //upp.setUserPolicyID(regId);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(upp);
            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;

            BasicDBObject searchQuery = new BasicDBObject();;
            try {
//                searchQuery = new BasicDBObject().append("_id", new ObjectId(regId));
                searchQuery.put("userId", regId);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return result;
            }

            WriteResult wr = uppTable.update(searchQuery, document);

            result = wr.isUpdateOfExisting();

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String storeUPP(UserPrivacyPolicy upp) {
        String result = null;
        //upp.setUserPolicyID(uppId);
        try {
            // find if the upp already added - then reject the post
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("userId", upp.getUserId());

            DBObject dbObj = this.uppTable.findOne(searchQuery);
            if (dbObj != null) {
                return null;
            }

            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(upp);
            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;

            uppTable.insert(document);
            ObjectId id = (ObjectId) document.get("_id");
            System.out.println("stored upp in " + id.toString() + document.get("userId"));
            result = getUPPById(document.get("userId").toString());

        } catch (MongoException e) {
            result = null;
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

}