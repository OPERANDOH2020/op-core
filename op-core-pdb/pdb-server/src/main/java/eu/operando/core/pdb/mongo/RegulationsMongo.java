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
import com.mongodb.MongoCommandException;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.util.JSON;
import eu.operando.core.pdb.common.model.PrivacyRegulation;
import eu.operando.core.pdb.common.model.PrivacyRegulationInput;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
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
    private MongoCollection<Document> regCollection;

    public RegulationsMongo() {

        //this.mongo = new MongoClient("mongo.integration.operando.dmz.lab.esilab.org", 27017);
        this.mongo = new MongoClient("localhost", 27017);

        initialiseCollections();
    }

    /**
     *
     * @param hostname
     * @param port
     */
    public RegulationsMongo(String hostname, int port) {

        mongo = new MongoClient(hostname, port);

        initialiseCollections();
    }

    /**
     *
     */
    private void initialiseCollections() {
        MongoDatabase database;

        // get database
        database = mongo.getDatabase("pdb");

        // get collection
        regCollection = database.getCollection("regulations");
        //this.mongo.close();
    }

    /**
     *
     * @param regId
     * @return
     */
    public boolean deleteRegulationById(String regId) {
        boolean ret = false;

        try {
            Bson filter = new Document("_id", new ObjectId(regId));
            DeleteResult result = regCollection.deleteOne(filter);
            ret = result.wasAcknowledged();
        } catch (MongoWriteException ex) {
            ex.printStackTrace();
        } catch (MongoWriteConcernException ex) {
            ex.printStackTrace();
        } catch (MongoException ex) {
            ex.printStackTrace();
        }

        return ret;
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
        String nKey = key.split("\\.")[0];
        Class aClass = PrivacyRegulation.class;
        try {
            Field field = aClass.getDeclaredField(nKey);
        } catch (NoSuchFieldException ex) {
            System.err.println("no such field found " + nKey);
            return false;
        }
        return true;
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
                System.out.println("converting key " + toCamelCase(key));
                String cKey = toCamelCase(key);
                if (!isAValidFieldName(cKey)) {
                    System.out.println("Not a valid key name found: " + cKey);
                    return null;
                }
                System.out.println("value " + obj.getString(key));
                query.put(cKey, java.util.regex.Pattern.compile(obj.getString(key)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("Query: " + query.toString());

        List<PrivacyRegulation> arrPRObj = new ArrayList<PrivacyRegulation>();

        List<Document> documents = regCollection.find(query).into(new ArrayList<Document>());
        //System.out.println("FOUND " + documents.size());

        for (Document document : documents) {
            //System.out.println("DOCUMENT ++:" + document.toString());
            PrivacyRegulation ospObj = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                ospObj = mapper.readValue(document.toJson(), PrivacyRegulation.class);
                //add policy id
                ospObj.setRegId(document.get("_id").toString());

                arrPRObj.add(ospObj);

            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
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

        System.out.println("GET Regulations by filter RESULT (list): " + result);

        return result;
    }

    public String getRegulationById(String regId) {
        String jsonInString = null;
        Bson filter = null;
        try {
            filter = new Document("_id", new ObjectId(regId));
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return null;
        }

        List<Document> result = (List<Document>) regCollection.find(filter).into(new ArrayList<Document>());

        if (!result.isEmpty()) {
            Document doc = result.get(0);

            PrivacyRegulation ospObj = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                ospObj = mapper.readValue(doc.toJson(), PrivacyRegulation.class);
                //add policy id
                ospObj.setRegId(doc.get("_id").toString());
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //ospObj.setOspPolicyId(ospId);
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.getSerializationConfig().enable(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING);
                mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

                jsonInString = mapper.writeValueAsString(ospObj);
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonInString;
    }

    public boolean updateRegulation(String regId, PrivacyRegulationInput regulation) {
        boolean result = false;
        Bson filter = null;
        try {
            filter = new Document("_id", new ObjectId(regId));
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return result;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(regulation);
            Document doc = Document.parse(jsonInString);

            try {
                UpdateResult ur = regCollection.replaceOne(filter, doc);
                result = ur.wasAcknowledged();
            } catch (MongoWriteException ex) {
                ex.printStackTrace();
            } catch (MongoWriteConcernException ex) {
                ex.printStackTrace();
            } catch (MongoException ex) {
                ex.printStackTrace();
            }

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ObjectId storeRegulationDirect(PrivacyRegulationInput regulation) {
        ObjectId result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(regulation);
            Document doc = Document.parse(jsonInString);
            regCollection.insertOne(doc);
            result = (ObjectId) doc.get("_id");

        } catch (MongoWriteException ex) {
            ex.printStackTrace();
        } catch (MongoWriteConcernException ex) {
            ex.printStackTrace();
        } catch (MongoCommandException ex) {
            ex.printStackTrace();
        } catch (MongoException e) {
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
        ObjectId id = storeRegulationDirect(reg);
        return id.toString();
    }

}
