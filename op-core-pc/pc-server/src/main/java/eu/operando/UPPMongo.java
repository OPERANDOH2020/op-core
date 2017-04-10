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
package eu.operando;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import eu.operando.core.pdb.common.model.UserPrivacyPolicy;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.SerializationConfig;

/**
 *
 * @author sysman
 */
public class UPPMongo {

    private MongoClient mongo;
    private DB db;
    private DBCollection uppTable;

    public UPPMongo(String hostname, int port) {
        try {
            this.mongo = new MongoClient(hostname, port);

            // get database
            this.db = mongo.getDB("pdb");

            // get collection
            this.uppTable = db.getCollection("upp");
            System.out.println(this.uppTable.toString());

        } catch (MongoException e) {
            e.printStackTrace();
        }
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

}
