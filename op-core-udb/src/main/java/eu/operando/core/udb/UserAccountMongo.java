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
//      Created By :            Panos Melas/Paul Grace
//      Created Date :          2016-10-28
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

package eu.operando.core.udb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import io.swagger.model.UserAccount;
import java.io.IOException;
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
 * Set of Mongo operations to update and manage the user accounts in
 * the Mongo database.
 *
 * The user account information is stored in a mongo collection called 'udb'
 */
public class UserAccountMongo {

    private MongoClient mongo;
    private DB db;
    private DBCollection userTable;

    /**
     * Set up the client interactions with the UDB collection in the mongo
     * database.
     */
    public UserAccountMongo() {
        try {
            this.mongo = new MongoClient("localhost", 27017);

            // get database
            this.db = mongo.getDB("udb");

            // get collection
            this.userTable = db.getCollection("users");

        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove a user account from the 'udb' collection.
     * @param userId the operando user id
     * @return whether the user account object was deleted from the collection.
     */
    public boolean deleteUserById(String userId) {
        boolean res = false;
        BasicDBObject searchQuery = new BasicDBObject();
        try {
            searchQuery.put("userid", userId);
        } catch (IllegalArgumentException e) {
            return res;
        }

        DBObject result = this.userTable.findOne(searchQuery);

        if (result == null) {
            res = false;
        } else {
            this.userTable.remove(result);
            res = true;
        }
        return res;
    }

    /**
     * Search the collection of users with a given json fileter and return
     * a JSON list of the results.
     * @param filter the query in json format.
     * @return A list of json objects that match the query. This is a single json document of this result.
     */
    public String getUserByFilter(String filter) {
        String result = null;
        BasicDBObject query = new BasicDBObject();

        System.out.println("filter expression: " + filter);

        try {
            JSONObject obj = new JSONObject(filter);
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                query.put(key, java.util.regex.Pattern.compile(obj.getString(key)));
            }
        } catch (JSONException e) {
            return null;
        }

        System.out.println("Query: " + query.toString());

        List<UserAccount> arrUPPObj = new ArrayList<>();

        DBCursor cursor = this.userTable.find(query);
        boolean found = false;
        while (cursor.hasNext()) {
            BasicDBObject regObj = (BasicDBObject) cursor.next();
            arrUPPObj.add(getUser(regObj));
            found = true;
        }

        if (!found) {
            return null;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.getSerializationConfig().enable(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING);
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

            result = mapper.writeValueAsString(arrUPPObj);
        } catch (JsonMappingException e) {
            result = null;
        } catch (IOException e) {
            result = null;
        }

        System.out.println("getUPPByFilter RESULT (list): " + result);

        return result;
    }

    /**
     * Get a user object from the database.
     * @param regObj The object to retrieve
     * @return The user account object in java object format.
     */
    private UserAccount getUser(DBObject regObj) {
        //System.out.println("regObj: " + regObj.toString());
        UserAccount prObj = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            prObj = mapper.readValue(regObj.toString(), UserAccount.class);

        } catch (JsonGenerationException e) {
        } catch (JsonMappingException e) {
        } catch (IOException e) {
        }
        return prObj;
    }

    /**
     * Retrieve a user account from the database using the operando id.
     * @param userId The unique Operando ID.
     * @return The json version of the user account object.
     */
    public String getUserById(String userId) {
        UserAccount uppObj;
        String jsonInString = null;

        // find
        BasicDBObject searchQuery = new BasicDBObject();
        try {
            searchQuery.put("userid", userId);
        } catch (IllegalArgumentException e) {
            return jsonInString;
        }

        DBObject result = this.userTable.findOne(searchQuery);
        if (result != null) {
            uppObj = getUser(result);
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.getSerializationConfig().enable(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING);
                mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

                jsonInString = mapper.writeValueAsString(uppObj);
            } catch (JsonMappingException e) {
            } catch (IOException e) {
            }

        }
        return jsonInString;
    }

    /**
     * Update the user account information in the object in the collection.
     * @param userId The id of the user
     * @param userAcc The updated account information.
     * @return Whether the object was updated or not.
     */
    public boolean updateUser(String userId, UserAccount userAcc) {
        boolean result = false;
        //upp.setUserPolicyID(regId);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(userAcc);
            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;

            BasicDBObject searchQuery;
            try {
                searchQuery = new BasicDBObject();
                searchQuery.put("userid", userId);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return result;
            }
            WriteResult wr = userTable.update(searchQuery, document);
            result = wr.isUpdateOfExisting();

        } catch (JsonGenerationException e) {
            result = false;
        } catch (JsonMappingException e) {
            result = false;
        } catch (IOException e) {
            result = false;
        }
        return result;
    }

    /**
     * Store a new user account in the collection.
     * @param userAcc The user to add
     * @return Error message
     */
    public String storeUser(UserAccount userAcc) {
        String result = null;

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(userAcc);
            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;
            result = getUserByFilter("{\"userid\": \"" + document.get("userid") + "\"}");
            if (result != null) {
                return null;
            }
            userTable.insert(document);
            ObjectId id = (ObjectId) document.get("_id");
            System.out.println("stored user in " + id.toString() + document.get("userid"));
            result = getUserById(document.get("userid").toString());

        } catch (MongoException e) {
            result = null;
        } catch (JsonGenerationException e) {
            result = null;
        } catch (JsonMappingException e) {
            result = null;
        } catch (IOException e) {
            result = null;
        }
        return result;
    }

}
