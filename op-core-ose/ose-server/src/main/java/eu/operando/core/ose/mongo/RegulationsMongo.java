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
import com.mongodb.MongoCommandException;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteConcernException;
import com.mongodb.MongoWriteException;
import com.mongodb.WriteResult;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.util.JSON;
import eu.operando.core.pdb.common.model.PrivacyRegulation;
import eu.operando.core.pdb.common.model.PrivacyRegulationInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author sysman
 */
public class RegulationsMongo {

    private MongoClient mongo;
    private MongoCollection<Document> regulationsCollection;

    private DB db;
    private DBCollection regulationTable;

    public RegulationsMongo() {
        try {
            this.mongo = new MongoClient("mongo.integration.operando.dmz.lab.esilab.org", 27017);
            //this.mongo = new MongoClient("localhost", 27017);
            // get database
            this.db = mongo.getDB("pdb");
            // get collection
            this.regulationTable = db.getCollection("regulations");
        } catch (MongoException e) {
            e.printStackTrace();
        }
        initialiseCollections();
    }

    public RegulationsMongo(String hostname, int port) {
        mongo = new MongoClient(hostname, port);
        initialiseCollections();
    }

    private void initialiseCollections() {
        MongoDatabase pdbDatabase;

        // get database
        pdbDatabase = mongo.getDatabase("pdb");

        regulationsCollection = pdbDatabase.getCollection("regulations");

        //this.mongo.close();
    }

    private eu.operando.core.pdb.common.model.PrivacyRegulation getRegulation(DBObject regObj) {
        //System.out.println("regObj: " + regObj.toString());
        eu.operando.core.pdb.common.model.PrivacyRegulation prObj = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            prObj = mapper.readValue(regObj.toString(), eu.operando.core.pdb.common.model.PrivacyRegulation.class);
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
     *
     * @param regId
     * @param reg
     * @return
     */
    public List<PrivacyRegulation> getRegulations(String data, String sector) {
        List<PrivacyRegulation> regs = new ArrayList<PrivacyRegulation>();

        // Get all the regs related to that action in that sector
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        obj.add(new BasicDBObject("legislation_sector", sector));
        obj.add(new BasicDBObject("private_information_type", data));
        andQuery.put("$and", obj);

        List<Document> documents = regulationsCollection.find(andQuery).into(new ArrayList<Document>());
        for (Document document : documents) {
            PrivacyRegulation prObj = null;
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                prObj = mapper.readValue(document.toJson(), PrivacyRegulation.class);
                regs.add(prObj);
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return regs;
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
                UpdateResult ur = regulationsCollection.replaceOne(filter, doc);
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

    public boolean updateRegulationD(String regId, PrivacyRegulationInput reg) {
        boolean result = false;
        //reg.setRegId(regId);
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(reg);
            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;

            BasicDBObject searchQuery = new BasicDBObject().append("regId", regId);
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

    public boolean storeRegulationList(List<PrivacyRegulation> regulationList) {
        boolean result = true;
        String storeAction = null;

        for (PrivacyRegulation pr : regulationList) {
            storeAction = storeRegulation(pr);
            if (storeAction != null) {
                result = false;
            }
        }

        return result;
    }

    /**
     *
     * @param reg
     * @return
     */
    public String storeRegulation(PrivacyRegulation reg) {
        String result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(reg);
            Document doc = Document.parse(jsonInString);
            regulationsCollection.insertOne(doc);
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

}
