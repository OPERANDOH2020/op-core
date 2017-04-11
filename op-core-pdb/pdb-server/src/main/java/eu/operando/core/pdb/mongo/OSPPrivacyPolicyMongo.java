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
import com.mongodb.MongoClient;
import com.mongodb.MongoCommandException;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import eu.operando.core.pdb.common.model.AccessReason;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicyInput;
import eu.operando.core.pdb.common.model.OSPReasonPolicy;
import eu.operando.core.pdb.common.model.OSPReasonPolicyInput;
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
public class OSPPrivacyPolicyMongo {

    private MongoClient mongo;

    private MongoCollection<Document> ospCollection;
    private MongoCollection<Document> ospPPCollection;

    public OSPPrivacyPolicyMongo() {

        //mongo = new MongoClient("mongo.integration.operando.dmz.lab.esilab.org", 27017);
        mongo = new MongoClient("localhost", 27017);

        initialiseCollections();
    }

    /**
     *
     * @param hostname
     * @param port
     */
    public OSPPrivacyPolicyMongo(String hostname, int port) {

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
        ospCollection = database.getCollection("osp");
        ospPPCollection = database.getCollection("pp");
        //this.mongo.close();
    }

    /**
     *
     * @param ospId
     * @return
     */
    public boolean deleteOSPById(String ospId) {
        boolean ret = false;

        try {
            Bson filter = new Document("_id", new ObjectId(ospId));
            DeleteResult result = ospCollection.deleteOne(filter);
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

    /**
     *
     * @param inputString
     * @return
     */
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

    /**
     *
     * @param key
     * @return
     */
    private boolean isAValidFieldName(String key) {
        String nKey = key.split("\\.")[0];
        Class aClass = OSPPrivacyPolicy.class;
        try {
            Field field = aClass.getDeclaredField(nKey);
        } catch (NoSuchFieldException ex) {
            System.err.println("no such field found " + nKey);
            return false;
        }
        return true;
    }

    /**
     *
     * @param filter
     * @return
     */
    public String getOSPByFilter(String filter) {
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

        List<OSPPrivacyPolicy> arrPRObj = new ArrayList<OSPPrivacyPolicy>();

        List<Document> documents = ospCollection.find(query).into(new ArrayList<Document>());
        //System.out.println("FOUND " + documents.size());

        for (Document document : documents) {
            //System.out.println("DOCUMENT ++:" + document.toString());
            OSPPrivacyPolicy ospObj = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                ospObj = mapper.readValue(document.toJson(), OSPPrivacyPolicy.class);
                //add policy id
                ospObj.setOspPolicyId(document.get("_id").toString());

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

        System.out.println("GET OSP by filter RESULT (list): " + result);

        return result;
    }

    /**
     *
     * @param ospId
     * @return
     */
    public String getOSPById(String ospId) {
        String jsonInString = null;
        Bson filter = null;
        try {
            filter = new Document("_id", new ObjectId(ospId));
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return null;
        }
        //FindIterable<Document> res = ospCollection.find(filter);
        List<Document> result = (List<Document>) ospCollection.find(filter).into(new ArrayList<Document>());

        if (!result.isEmpty()) {
            Document doc = result.get(0);

            OSPPrivacyPolicy ospObj = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                ospObj = mapper.readValue(doc.toJson(), OSPPrivacyPolicy.class);
                //add policy id
                ospObj.setOspPolicyId(doc.get("_id").toString());
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

    /**
     *
     * @param ospId
     * @param ospPolicyInput
     * @return
     */
    public boolean updateReasonPolicyOSP(String ospId, OSPReasonPolicyInput ospPolicyInput) {
        boolean result = false;
        Bson filter = null;
        try {
            filter = new Document("ospPolicyId", ospId);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return result;
        }

        // create the actual osp reason policy object
        OSPReasonPolicy ospPolicy = new OSPReasonPolicy();
        ospPolicy.setOspPolicyId(ospId);
        ospPolicy.setPolicies(ospPolicyInput.getPolicies());

        System.out.println("update privacy policies " + ospPolicy.toString());

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(ospPolicy);
            Document doc = Document.parse(jsonInString);
            System.out.println("doc: " + doc.toString());
            try {
                System.out.println("INSIDE");
                UpdateOptions options = new UpdateOptions().upsert(true);
                UpdateResult ur = ospPPCollection.replaceOne(filter, doc, options);
                result = ur.wasAcknowledged();
                System.out.println("RESULT: " + result + " -> " + ur.toString());
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

    /**
     *
     * @param ospId
     * @param ospPolicy
     * @return
     */
    public boolean updateOSP(String ospId, OSPPrivacyPolicyInput ospPolicy) {
        boolean result = false;
        Bson filter = null;
        try {
            filter = new Document("_id", new ObjectId(ospId));
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return result;
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(ospPolicy);
            Document doc = Document.parse(jsonInString);

            try {
                UpdateResult ur = ospCollection.replaceOne(filter, doc);
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

    /**
     *
     * @param policy
     * @return
     */
    public String storeOSP(OSPPrivacyPolicyInput policy) {
        String result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(policy);
            Document doc = Document.parse(jsonInString);
            ospCollection.insertOne(doc);
            result = doc.get("_id").toString();

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

    /**
     *
     * @param ospId
     * @return
     */
    public String getPolicyOSPById(String ospId) {
        String jsonInString = null;
        Bson filter = null;
        try {
            filter = new Document("ospPolicyId", ospId);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return null;
        }
        
        System.out.println("getPolicyOSPById(" + ospId + ");");
        
        List<Document> result = (List<Document>) ospPPCollection.find(filter).into(new ArrayList<Document>());

        if (!result.isEmpty()) {
            Document doc = result.get(0);

            OSPReasonPolicy ospReasonObj = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                ospReasonObj = mapper.readValue(doc.toJson(), OSPReasonPolicy.class);
                //add policy id
                //ospReasonObj.setOspPolicyId(doc.get("_id").toString());
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //ospReasonObj.setOspPolicyId(ospId);
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.getSerializationConfig().enable(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING);
                mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

                jsonInString = mapper.writeValueAsString(ospReasonObj);
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("GET OSP Reason Policy RESULT: " + jsonInString);
        return jsonInString;
    }

    /**
     *
     * @param ospPolicyId
     * @return
     */
    public String getOSPAccessReasonsById(String ospPolicyId) {
        String jsonInString = null;
        Bson filter = null;
        try {
            filter = new Document("ospPolicyId", ospPolicyId);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return null;
        }

        System.out.println("getAccessReasonsById(" + ospPolicyId + ");");

        List<Document> result = (List<Document>) ospPPCollection.find(filter).into(new ArrayList<Document>());

        if (!result.isEmpty()) {
            Document doc = result.get(0);

            OSPReasonPolicy ospReasonObj = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                ospReasonObj = mapper.readValue(doc.toJson(), OSPReasonPolicy.class);
                //add policy id
                //ospReasonObj.setOspPolicyId(doc.get("_id").toString());
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //ospReasonObj.setOspPolicyId(ospPolicyId);
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.getSerializationConfig().enable(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING);
                mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

                jsonInString = mapper.writeValueAsString(ospReasonObj);
                System.out.println("REASON POLICIES: " + jsonInString);
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("GET OSP AccessReasons RESULT (list): " + jsonInString);
        return jsonInString;
    }

    /**
     *
     * @param ospPolicyId
     * @param accessReason
     * @return
     */
    public boolean privacyPolicyAccessReasonsPost(String ospPolicyId, AccessReason accessReason) {
        Boolean ret = null;
        System.out.println("accessReasonIdPost(" + ospPolicyId + ");");

        // find
        Bson filter = null;
        try {
            filter = new Document("ospPolicyId", ospPolicyId);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return ret;
        }

        List<Document> result = (List<Document>) ospPPCollection.find(filter).into(new ArrayList<Document>());

        if (!result.isEmpty()) {
            Document doc = result.get(0);

            OSPReasonPolicy ospReasonObj = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                ospReasonObj = mapper.readValue(doc.toJson(), OSPReasonPolicy.class);
                //add policy id
                //ospReasonObj.setOspPolicyId(doc.get("_id").toString());
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //ospReasonObj.setOspPolicyId(ospPolicyId);
            ospReasonObj.getPolicies().add(accessReason);

            try {
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(ospReasonObj);
                doc = Document.parse(jsonInString);

                try {
                    UpdateResult ur = ospPPCollection.replaceOne(filter, doc);
                    ret = ur.wasAcknowledged();
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
        }

        return ret;
    }

    /**
     *
     * @param ospPolicyId
     * @param reasonId
     * @return
     */
    public boolean accessReasonIdDelete(String ospPolicyId, String reasonId) {
        boolean ret = false;
        Bson filter = null;
        try {
            filter = new Document("ospPolicyId", ospPolicyId);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return ret;
        }

        System.out.println("accessReasonIdDelete(" + ospPolicyId + ", " + reasonId + ");");

        List<Document> result = (List<Document>) ospPPCollection.find(filter).into(new ArrayList<Document>());

        if (!result.isEmpty()) {
            Document doc = result.get(0);

            OSPReasonPolicy ospReasonObj = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                ospReasonObj = mapper.readValue(doc.toJson(), OSPReasonPolicy.class);
                //add policy id
                //ospReasonObj.setOspPolicyId(doc.get("_id").toString());
                List<AccessReason> arList = new ArrayList<AccessReason>();
                for (AccessReason ar : ospReasonObj.getPolicies()) {
                    if (!ar.getReasonid().endsWith(reasonId)) {
                        arList.add(ar);
                    }
                }

                ospReasonObj.setPolicies(arList);

            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(ospReasonObj);
                doc = Document.parse(jsonInString);

                try {
                    UpdateResult ur = ospPPCollection.replaceOne(filter, doc);
                    ret = ur.wasAcknowledged();
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
        }
        return ret;
    }

    /**
     *
     * @param ospPolicyId
     * @param reasonId
     * @param accessReason
     * @return
     */
    public boolean accessReasonIdUpdate(String ospPolicyId, String reasonId,
            AccessReason accessReason) {
        boolean ret = false;
        Bson filter = null;
        try {
            filter = new Document("ospPolicyId", ospPolicyId);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return ret;
        }

        System.out.println("accessReasonIdDelete(" + ospPolicyId + ", " + reasonId + ");");

        List<Document> result = (List<Document>) ospPPCollection.find(filter).into(new ArrayList<Document>());

        if (!result.isEmpty()) {
            Document doc = result.get(0);

            OSPReasonPolicy ospReasonObj = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                ospReasonObj = mapper.readValue(doc.toJson(), OSPReasonPolicy.class);
                //add policy id
                //ospReasonObj.setOspPolicyId(doc.get("_id").toString());
                List<AccessReason> arList = new ArrayList<AccessReason>();
                for (AccessReason ar : ospReasonObj.getPolicies()) {
                    if (ar.getReasonid().endsWith(reasonId)) {
                        arList.add(accessReason);
                    } else {
                        arList.add(ar);
                    }
                }

                ospReasonObj.setPolicies(arList);

            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                ObjectMapper mapper = new ObjectMapper();
                String jsonInString = mapper.writeValueAsString(ospReasonObj);
                doc = Document.parse(jsonInString);

                try {
                    UpdateResult ur = ospPPCollection.replaceOne(filter, doc);
                    ret = ur.wasAcknowledged();
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
        }
        return ret;
    }

}
