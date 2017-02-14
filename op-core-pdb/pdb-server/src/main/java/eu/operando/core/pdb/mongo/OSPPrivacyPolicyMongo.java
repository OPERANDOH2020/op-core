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
import eu.operando.core.pdb.common.model.AccessReason;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicyInput;
import eu.operando.core.pdb.common.model.OSPReasonPolicy;
import eu.operando.core.pdb.common.model.OSPReasonPolicyInput;
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
public class OSPPrivacyPolicyMongo {

    private MongoClient mongo;
    private DB db;
    private DBCollection ospTable;
    private DBCollection ospPPTable;
    private DBCollection ospRPTable;

    public OSPPrivacyPolicyMongo() {
        try {
            //this.mongo = new MongoClient("mongo.integration.operando.dmz.lab.esilab.org", 27017);
            this.mongo = new MongoClient("localhost", 27017);

            // get database
            this.db = mongo.getDB("pdb");
            // get collection
            this.ospTable = db.getCollection("osp");
            this.ospPPTable = db.getCollection("pp");
            this.ospRPTable = db.getCollection("ar");

            //} catch (UnknownHostException e) {
            //    e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }
    
    public OSPPrivacyPolicyMongo(String hostname, int port) {
        try {
            this.mongo = new MongoClient(hostname, port);

            // get database
            this.db = mongo.getDB("pdb");
            
            // get collection
            this.ospTable = db.getCollection("osp");
            this.ospPPTable = db.getCollection("pp");
            this.ospRPTable = db.getCollection("ar");
            
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteOSPById(String regId) {
        boolean res = false;
        BasicDBObject searchQuery = new BasicDBObject();

        try {
            searchQuery.put("_id", new ObjectId(regId));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return res;
        }
        DBObject result = this.ospTable.findOne(searchQuery);

        if (result == null) {
            res = false;
        } else {
            this.ospTable.remove(result);
            res = true;
        }
        return res;
    }

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
                System.out.println("value " + obj.getString(key));
                query.put(key, java.util.regex.Pattern.compile(obj.getString(key)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println("Query: " + query.toString());

        List<OSPPrivacyPolicy> arrPRObj = new ArrayList<OSPPrivacyPolicy>();

        DBCursor cursor = this.ospTable.find(query);

        while (cursor.hasNext()) {
            BasicDBObject regObj = (BasicDBObject) cursor.next();
            System.out.println("Adding result " + regObj.toString() + "obj id:" + regObj.getObjectId("_id").toString());
            arrPRObj.add(getOSP(regObj));
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

    private OSPPrivacyPolicy getOSP(DBObject regObj) {
        OSPPrivacyPolicy ospObj = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ospObj = mapper.readValue(regObj.toString(), OSPPrivacyPolicy.class);
            //add policy id
            ospObj.setOspPolicyId(regObj.get("_id").toString());

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ospObj;
    }

    private OSPReasonPolicy getOSPReasonPolicy(DBObject regObj) {
        OSPReasonPolicy ospObj = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            ospObj = mapper.readValue(regObj.toString(), OSPReasonPolicy.class);
            //add policy id
            //ospObj.setOspPolicyId(regObj.get("_id").toString());

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ospObj;
    }

    public String getOSPById(String ospId) {
        OSPPrivacyPolicy ospObj;
        String jsonInString = null;
        System.out.println("getOSPById(" + ospId + ");");

        // find
        BasicDBObject searchQuery = new BasicDBObject();
        try {
            searchQuery.put("_id", new ObjectId(ospId));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return jsonInString;
        }

        DBObject result = this.ospTable.findOne(searchQuery);

        if (result != null) {

            ospObj = getOSP(result);
            // add policy id
            ospObj.setOspPolicyId(ospId);

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

    public String getOSPReasonPolicyById(String ospId) {
        OSPReasonPolicy ospObj;
        String jsonInString = null;
        System.out.println("getOSPReasonPolicyById(" + ospId + ");");

        // find
        BasicDBObject searchQuery = new BasicDBObject();
        try {
            searchQuery.put("ospPolicyId", new ObjectId(ospId));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return jsonInString;
        }

        DBObject result = this.ospTable.findOne(searchQuery);

        if (result != null) {

            ospObj = getOSPReasonPolicy(result);
            // add policy id
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

    public boolean updateOSP(String ospId, OSPPrivacyPolicyInput regulation) {
        boolean result = false;
        //regulation.setOSPPolicyID(ospId);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(regulation);
            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;

            BasicDBObject searchQuery;

            try {
                searchQuery = new BasicDBObject().append("_id", new ObjectId(ospId));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return result;
            }

            WriteResult wr = ospTable.update(searchQuery, document);

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

    public String storeOSP(OSPPrivacyPolicyInput policy) {
        String result = null;
        //regulation.setOSPPolicyID(ospId);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(policy);
            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;

            ospTable.insert(document);
            ObjectId id = (ObjectId) document.get("_id");
            result = getOSPById(id.toString());
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

    public boolean updatePolicyOSP(String ospId, OSPReasonPolicyInput ospPolicyInput) {
        boolean result = false;
        OSPReasonPolicy ospPolicy = new OSPReasonPolicy();
        ospPolicy.setOspPolicyId(ospId);
        ospPolicy.setPolicies(ospPolicyInput.getPolicies());

        System.out.println("update privacy policies");
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(ospPolicy);
            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;

            BasicDBObject searchQuery = new BasicDBObject();
            try {
                searchQuery.put("ospPolicyId", ospId);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                return result;
            }

            WriteResult wr = ospPPTable.update(searchQuery, document, true, false);

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

    private OSPReasonPolicy getReasonPolicy(DBObject regObj) {
        //System.out.println("regObj: " + regObj.toString());
        OSPReasonPolicy prObj = null;
        try {

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            prObj = mapper.readValue(regObj.toString(), OSPReasonPolicy.class);
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

    private List<AccessReason> getAccessReasons(DBObject regObj) {
        //System.out.println("regObj: " + regObj.toString());
        AccessReason arObj = null;
        try {

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            arObj = mapper.readValue(regObj.toString(), AccessReason.class);
            //System.out.println("prObj: " + prObj.toString());

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getPolicyOSPById(String ospId) {
        OSPReasonPolicy ospRPObj;
        String jsonInString = null;
        System.out.println("getPolicyOSPById(" + ospId + ");");

        // find
        BasicDBObject searchQuery = new BasicDBObject();
        try {
            searchQuery.put("ospPolicyId", ospId);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return jsonInString;
        }

        DBObject result = this.ospPPTable.findOne(searchQuery);

        if (result != null) {

            ospRPObj = getReasonPolicy(result);

            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.getSerializationConfig().enable(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING);
                mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

                jsonInString = mapper.writeValueAsString(ospRPObj);
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsonInString;
    }

    public String getOSPAccessReasonsById(String ospId) {
        OSPReasonPolicy ospRPObj;
        String jsonInString = null;
        System.out.println("getAccessReasonsById(" + ospId + ");");

        // find
        BasicDBObject searchQuery = new BasicDBObject();
        try {
            searchQuery.put("ospPolicyId", ospId);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return jsonInString;
        }

        DBObject result = this.ospPPTable.findOne(searchQuery);

        if (result != null) {

            ospRPObj = getReasonPolicy(result);

            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.getSerializationConfig().enable(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING);
                mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);

                jsonInString = mapper.writeValueAsString(ospRPObj.getPolicies());
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return jsonInString;
    }

    public boolean privacyPolicyAccessReasonsPost(String ospId, AccessReason accessReason) {
        Boolean ret = null;
        OSPReasonPolicy ospObj = null;
        String jsonInString = null;
        System.out.println("accessReasonIdDelete(" + ospId + ");");

        // find
        BasicDBObject searchQuery = new BasicDBObject();
        try {
            searchQuery.put("ospPolicyId", ospId);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ret;
        }

        DBObject result = this.ospPPTable.findOne(searchQuery);

        if (result != null) {
            ospObj = getOSPReasonPolicy(result);
            ospObj.getPolicies().add(accessReason);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonInString = mapper.writeValueAsString(ospObj);
            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;

            WriteResult wr = this.ospPPTable.update(searchQuery, document);

            ret = wr.isUpdateOfExisting();

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;

    }

    public boolean accessReasonIdDelete(String ospId, String reasonId) {

        boolean ret = false;

        OSPReasonPolicy ospObj = null;
        String jsonInString = null;
        System.out.println("accessReasonIdDelete(" + ospId + ", " + reasonId + ");");

        // find
        BasicDBObject searchQuery = new BasicDBObject();
        try {
            searchQuery.put("ospPolicyId", ospId);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ret;
        }

        DBObject result = this.ospPPTable.findOne(searchQuery);

        if (result != null) {

            ospObj = getOSPReasonPolicy(result);
            List<AccessReason> arList = new ArrayList<AccessReason>();
            for (AccessReason ar : ospObj.getPolicies()) {
                if (!ar.getReasonid().endsWith(reasonId)) {
                    arList.add(ar);
                }
            }

            ospObj.setPolicies(arList);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonInString = mapper.writeValueAsString(ospObj);
            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;

            WriteResult wr = this.ospPPTable.update(searchQuery, document);

            ret = wr.isUpdateOfExisting();

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public boolean accessReasonIdUpdate(String ospId, String reasonId,
            AccessReason accessReason) {

        boolean ret = false;

        OSPReasonPolicy ospObj = null;
        String jsonInString = null;
        System.out.println("accessReasonIDUpdate(" + ospId + ", " + reasonId + ");");

        // find
        BasicDBObject searchQuery = new BasicDBObject();
        try {
            searchQuery.put("ospPolicyId", ospId);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ret;
        }

        DBObject result = this.ospPPTable.findOne(searchQuery);

        if (result != null) {

            ospObj = getOSPReasonPolicy(result);
            List<AccessReason> arList = new ArrayList<AccessReason>();
            for (AccessReason ar : ospObj.getPolicies()) {
                if (ar.getReasonid().endsWith(reasonId)) {
                    arList.add(accessReason);
                } else {
                    arList.add(ar);
                }
            }
            ospObj.setPolicies(arList);
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonInString = mapper.writeValueAsString(ospObj);
            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;

            WriteResult wr = this.ospPPTable.update(searchQuery, document);

            ret = wr.isUpdateOfExisting();

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
