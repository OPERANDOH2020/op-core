//////////////////////////////////////////////////////////////////////////
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
import eu.operando.core.pdb.common.model.PrivacyRegulation;
import eu.operando.core.pdb.common.model.PrivacyRegulationInput;
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
 *
 * @author sysman
 */
public class RegulationsMongo {

    private MongoClient mongo;
    private PrivacyRegulation regulation;
    private DB db;
    private DBCollection regulationTable;

    public RegulationsMongo() {
        try {
            this.mongo = new MongoClient("mongo.integration.operando.dmz.lab.esilab.org", 27017);
            // this.mongo = new MongoClient("localhost", 27017);
            // get database
            this.db = mongo.getDB("pdb");
            // get collection
            this.regulationTable = db.getCollection("regulations");

            this.regulation = new PrivacyRegulation();
            //} catch (UnknownHostException e) {
            //    e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteRegulationById(String regId) {
        boolean res = false;
        BasicDBObject searchQuery = new BasicDBObject();
        try {
            searchQuery.put("_id", new ObjectId(regId));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return res;
        }

        DBObject result = this.regulationTable.findOne(searchQuery);

        if (result == null) {
            res = false;
        } else {
            this.regulationTable.remove(result);
            res = true;
        }
        return res;
    }

    public String getRegulationByFilter(String filter) {
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

        List<PrivacyRegulation> arrPRObj = new ArrayList<PrivacyRegulation>();

        DBCursor cursor = this.regulationTable.find(query);
        while (cursor.hasNext()) {
            BasicDBObject regObj = (BasicDBObject) cursor.next();
            System.out.println("Adding result " + regObj.toString());
            arrPRObj.add(getRegulation(regObj));
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.getSerializationConfig().enable(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING);
            mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

            result = mapper.writeValueAsString(arrPRObj);
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("RESULT (list): " + result);

        return result;
    }

    private PrivacyRegulation getRegulation(DBObject regObj) {
        PrivacyRegulation prObj = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            prObj = mapper.readValue(regObj.toString(), PrivacyRegulation.class);
            prObj.setRegId(regObj.get("_id").toString());

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prObj;
    }

    public String getRegulationById(String regId) {
        PrivacyRegulation prObj;
        String jsonInString = null;
        System.out.println("Searching for " + regId);

        // find
        BasicDBObject searchQuery = new BasicDBObject();
        try {
            searchQuery.put("_id", new ObjectId(regId));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return jsonInString;
        }

        DBObject result = this.regulationTable.findOne(searchQuery);
        if (result != null) {
            prObj = getRegulation(result);
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.getSerializationConfig().enable(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING);
                mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

                jsonInString = mapper.writeValueAsString(prObj);
                result.removeField("_id");
                System.out.println("FOOOOO " + mapper.writeValueAsString(result));
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsonInString;
    }

    public boolean updateRegulation(String regId, PrivacyRegulationInput reg) {
        boolean result = false;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(reg);
            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;
            BasicDBObject searchQuery;

            try {
                searchQuery = new BasicDBObject().append("_id", new ObjectId(regId));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return result;
            }

            WriteResult wr = regulationTable.update(searchQuery, document);

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

    public ObjectId storeRegulationDirect(PrivacyRegulationInput reg) {
        ObjectId result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            //mapper.getSerializationConfig().enable(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING);
            //mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

            String jsonInString = mapper.writeValueAsString(reg);

            System.out.println("jsonInString: " + jsonInString);

            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;

            regulationTable.insert(document);
            result = (ObjectId) document.get("_id");
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

    public String storeRegulation(PrivacyRegulationInput reg) {
        String result = null;
        ObjectId id = storeRegulationDirect(reg);
        if (id != null) {
            result = getRegulationById(id.toString());
        }
        return result;
    }

}
