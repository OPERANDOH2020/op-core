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

package eu.operando.core.pdb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import io.swagger.model.UserPrivacyPolicy;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

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
    	//GBE added to externalize db properties
    	Properties props;
    	props = loadDbProperties();

        try {
        	//this.mongo = new MongoClient("localhost", 27017);
        	this.mongo = new MongoClient(props.getProperty("mongo.host"), Integer.parseInt(props.getProperty("mongo.port")));
            // get database
            this.db = mongo.getDB("pdb");
            // get collection
            this.uppTable = db.getCollection("upp");

            this.upp = new UserPrivacyPolicy();
            //} catch (UnknownHostException e) {
            //  e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteUPPById(String uppId) {
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

    public String getUPPByFilter(String filter) {
        String result = null;
        BasicDBObject query = new BasicDBObject();
        
        System.out.println("filter expression: " + filter);

        try {
            JSONObject obj = new JSONObject(filter);
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                System.out.println("found key " + key);
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

    public boolean updateUPP(String uppId, UserPrivacyPolicy upp) {
        boolean result = false;
        //upp.setUserPolicyID(regId);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(upp);
            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;

            //BasicDBObject searchQuery;
            BasicDBObject searchQuery = new BasicDBObject();
            try {
                //searchQuery = new BasicDBObject().append("_id", new ObjectId(regId));
                searchQuery.put("userId", uppId);
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
    
    
	private Properties loadDbProperties() {
		Properties props;
		props = new Properties();
		
		InputStream fis = null;
		try {
		    fis = this.getClass().getClassLoader().getResourceAsStream("/db.properties");
		    props.load(fis);
		}     catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}		
		return props;
	}	

}
