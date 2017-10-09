/////////////////////////////////////////////////////////////////////////
//
// � University of Southampton IT Innovation Centre, 2016
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
//      Created By :            Paul Grace
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////




import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import eu.operando.core.pdb.client1.ApiClient;
import eu.operando.core.pdb.client1.ApiException;
import eu.operando.core.pdb.client1.api.OSPApi;
import eu.operando.core.pdb.client1.model.OSPReasonPolicyInput;
import io.swagger.model.Questionobject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Set of unit tests for the using the /user_privacy_policy endpoint of the
 * PDB module.
 *
 * Check that UPP records are created, updated and deleted correctly.
 */
public class GeneralQuestionTests {

    private static String PQ_URL = null;
    private final OSPApi osp_api = new OSPApi();
     private String PDBURL ;
    /**
     * Load the configuration properties from the resource file in JAR/WAR and
     * turn then into JAVA properties class.
     * @return The list of JAVA properties reflecting the configuration.
     */
    private Properties loadDbProperties() {
        Properties props;
        props = new Properties();

        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream("operando.properties");
            props.load(fis);
        }
        catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("Error reading Configuration properties file");

            // Add logging code to log an error configuring the API on startup
        }
        return props;
    }

    public GeneralQuestionTests() {
        Properties props = loadDbProperties();

        if (props.getProperty("pdb.osp") != null) {
            PQ_URL = props.getProperty("pq.url");
        }
        if (props.getProperty("pdb.url") != null) {
            PDBURL = props.getProperty("pdb.url");
        }
        ApiClient apCl = new ApiClient();
        apCl.setBasePath(PDBURL);
        osp_api.setApiClient(apCl);
    }

    /**
     * Json get Question.
     * @param ospid
     * @param userid
     * @return
     */
    private String getQuestion(String ospid, String userid) {

        try {
            String getQuestions = PQ_URL +"/" + userid + "/" + ospid + "?language=en";
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(getQuestions);
            CloseableHttpResponse response1 = httpclient.execute(httpget);

            HttpEntity entity = response1.getEntity();
            System.out.println(response1.getStatusLine().getStatusCode());
            if(response1.getStatusLine().getStatusCode()==404) {
                return null;
            }
            String getResponse = EntityUtils.toString(entity);
            return getResponse;
        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("Error getting questions");
            return null;
        }
    }

    private String postQuestion(String ospid, String userid, String content) {

        try {
            String getQuestions = PQ_URL +"/" + userid + "/" + ospid;
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost post = new HttpPost(getQuestions);
            post.addHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(content));
            CloseableHttpResponse response1 = httpclient.execute(post);

            HttpEntity entity = response1.getEntity();
            System.out.println(response1.getStatusLine().getStatusCode());
            if(response1.getStatusLine().getStatusCode()!= 200) {
                return null;
            }
            String getResponse = EntityUtils.toString(entity);
            return getResponse;
        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("Error getting questions");
            return null;
        }
    }

    private String answerQuestions(List<Questionobject> questions, int[] answers) {
        try {
            int index = 0;
            for(Questionobject ques: questions) {
                ques.setScore("" + answers[index++]);
            }
            ObjectMapper objMap = new ObjectMapper();
            return objMap.writeValueAsString(questions);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(GeneralQuestionTests.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public OSPReasonPolicyInput getReasonsFromFile(String filename) {
        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(filename);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);

            ObjectMapper mapper = new ObjectMapper();

            //JSON from String to Object
            return mapper.readValue(content,  OSPReasonPolicyInput.class);

        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadPolicy(String ospId, String fileName) {
        OSPReasonPolicyInput policies = getReasonsFromFile(fileName);
        try {
            osp_api.oSPOspIdPrivacyPolicyPut(ospId, policies);
        } catch (ApiException ex) {
            Logger.getLogger(GeneralQuestionTests.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {

            GeneralQuestionTests odpdb = new GeneralQuestionTests();
//            odpdb.loadPolicy("596588bcee35880033ddc4d0", "reasonPolicy.json");

            String jsonResponse = odpdb.getQuestion("59b5f442ee35880033ded8cc", "301");
            System.out.println(jsonResponse + "\n");

            ObjectMapper objMap = new ObjectMapper();
            List<Questionobject> questions = objMap.readValue(jsonResponse, new TypeReference<List<Questionobject>>() { });

            String quesResponse = odpdb.answerQuestions(questions, new int[]{3,1,1,3,3,3,4,2,1,1,1,4,1});
            System.out.println(quesResponse + "\n");

            String postAnswers = odpdb.postQuestion("59b5f442ee35880033ded8cc", "pjgrace", quesResponse);
            System.out.println(postAnswers + "\n");

//            quesResponse = odpdb.answerQuestions(questions, new int[]{2,2,1,3,3,3,4,2,1,1,1,4});
//            System.out.println(quesResponse + "\n");
//
//            postAnswers = odpdb.postQuestion("59b1ffdeee35880033ffa600", "pjgrace", quesResponse);
//            System.out.println(postAnswers + "\n");
//
//            quesResponse = odpdb.answerQuestions(questions, new int[]{2,4,4,3,3,3,4,2,1,1,1,4});
//            System.out.println(quesResponse + "\n");
//
//            postAnswers = odpdb.postQuestion("59b1ffdeee35880033ffa600", "pjgrace", quesResponse);
//            System.out.println(postAnswers + "\n");
        } catch (IOException ex) {
            Logger.getLogger(GeneralQuestionTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
