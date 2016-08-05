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

package eu.operando.core.ose;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;
import io.swagger.model.PrivacyRegulation;
import io.swagger.model.PrivacyRegulationInput;
import java.io.IOException;
import java.util.List;
import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author sysman
 */
public class RegulationsMongo {

    private MongoClient mongo;
    private DB db;
    private DBCollection regulationTable;

    public RegulationsMongo() {
        try {
            this.mongo = new MongoClient("localhost", 27017);
            // get database
            this.db = mongo.getDB("ose");
            // get collection
            this.regulationTable = db.getCollection("regulations");
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    public boolean updateRegulation(String regId, PrivacyRegulationInput reg) {
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
        
        for(PrivacyRegulation pr : regulationList) {
            storeAction = storeRegulation(pr);
            if (storeAction != null) {
                result = false;
            }
        }
        
        return result;
    }

    public String storeRegulation(PrivacyRegulation reg) {
        String result = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(reg);
            Object obj = JSON.parse(jsonInString);
            DBObject document = (DBObject) obj;

            regulationTable.insert(document);
            ObjectId id = (ObjectId) document.get("_id");
            result = id.toString();
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
