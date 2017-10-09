package io.swagger.api.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.operando.core.pdb.client1.ApiClient;

import eu.operando.pq.InvalidAnswerException;
import eu.operando.pq.PrivacyQuestionsService;
import eu.operando.pq.SensitivityCalculation;
import eu.operando.pq.SensitivityCalculation.Sensitivity;
import io.swagger.api.*;
import io.swagger.model.*;

import java.util.List;
import io.swagger.api.NotFoundException;
import java.util.ArrayList;
import eu.operando.core.pdb.client1.api.OSPApi;
import eu.operando.core.pdb.client1.api.UPPApi;
import eu.operando.core.pdb.client1.model.AccessReason;
import eu.operando.core.pdb.client1.model.OSPConsents;
import eu.operando.core.pdb.client1.model.OSPReasonPolicy;
import eu.operando.core.pdb.client1.model.OSPSettings;
import eu.operando.core.pdb.client1.model.UserPreference;
import eu.operando.core.pdb.client1.model.UserPrivacyPolicy;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-06-30T09:37:51.622Z")
public class QuestionsApiServiceImpl extends QuestionsApiService {

    private final UPPApi upp_api = new UPPApi();
    private final OSPApi osp_api = new OSPApi();

    private final String GENERALCATEGORY = "GENERAL";
    private String PDBURL;
    private String PC_URL;

    public QuestionsApiServiceImpl() {
        super();
        Properties props = loadDbProperties();

        if (props.getProperty("pdb.url") != null) {
            PDBURL = props.getProperty("pdb.url");
        }
        if (props.getProperty("pc.api") != null) {
            PC_URL = props.getProperty("pc.api");
        }
        ApiClient apCl = new ApiClient();
        apCl.setBasePath(PDBURL);
        upp_api.setApiClient(apCl);
        osp_api.setApiClient(apCl);
    }

    /**
     * Load the configuration properties from the resource file in JAR/WAR and
     * turn then into JAVA properties class.
     *
     * @return The list of JAVA properties reflecting the configuration.
     */
    private Properties loadDbProperties() {
        Properties props;
        props = new Properties();

        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream("operando.properties");
            props.load(fis);
        } catch (IOException e) {
            // Display to console for debugging purposes.
            System.err.println("Error reading Configuration properties file");

            // Add logging code to log an error configuring the API on startup
        }
        return props;
    }

    @Override
    public Response questionsUserIdOspIdGet(String userId, String ospId, String language, SecurityContext securityContext) throws NotFoundException {
        System.out.println("user: "+ userId);
        System.out.println("osp: "+ ospId);
        System.out.println("language: "+ language);
        try {
            boolean answeredGeneral = false;
//            try {
//                /**
//                 * Get the UPP of the user. If there is a preference with
//                 * GENERAL don't ask the GENERAL question again.
//                 */
//                UserPrivacyPolicy response = upp_api.userPrivacyPolicyUserIdGet(userId);
//                System.out.println("upp = " + response);
//                if (response != null) {
//                    List<UserPreference> userPreferences = response.getUserPreferences();
//                    for (UserPreference pref : userPreferences) {
//                        System.out.println("pref = " + pref.getCategory());
//                        if (pref.getCategory().equalsIgnoreCase(GENERALCATEGORY)) {
//                            answeredGeneral = true;
//                            System.out.println("Found general");
//                            break;
//                        }
//                    }
//                }
//            } catch (eu.operando.core.pdb.client1.ApiException ex) {
//                /**
//                 * Default option where the UPP hasn't been created yet.
//                 */
//                ex.printStackTrace();
//                answeredGeneral = false;
//            }

            PrivacyQuestionsService serv = new PrivacyQuestionsService();
            List<Questionobject> questions = new ArrayList<>();

            /**
             * First get the set of 3 general questions
             */
            if (!answeredGeneral) {
                questions.addAll(serv.getGeneralQuestions(language));
            }

            try {
                /**
                 * From the OSP get the data categories. First get the OSP
                 * reason policy. Then extract the data categories and the roles
                 * from it.
                 */
                System.out.println("osp: " + osp_api);
                OSPReasonPolicy response = osp_api.oSPOspIdPrivacyPolicyGet(ospId);
                System.out.println("Response: " + response);
                List<AccessReason> policies = response.getPolicies();

                List<Questionobject> catQuestions = serv.getCategoryQuestions(policies, language);
                questions.addAll(catQuestions);

                List<Questionobject> trustQuestions = serv.getTrustQuestions(policies, language);
                questions.addAll(trustQuestions);
            } catch (eu.operando.core.pdb.client1.ApiException ex) {
                /**
                 * Default option - is to return questions about Personal and
                 * Contact
                 */
                ex.printStackTrace();
                questions.addAll(serv.getDefaultQuestions(language));
            }

            /**
             * Write the questions to JSON so they can be returned as method
             * response.
             */
            ObjectMapper objectMapper = new ObjectMapper();
            String arrayToJson = objectMapper.writeValueAsString(questions);

            return Response.ok().entity(arrayToJson).build();
        } catch (JsonProcessingException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Could not find questions")).build();
        }
    }

    @Override
    public Response questionsUserIdOspIdPost(String userId, String osp, List<Questionobject> questionInput, SecurityContext securityContext) throws NotFoundException {
        System.out.println("User id: " + userId);
        try {
            List<Answerobject> answers = new ArrayList<Answerobject>();
            Map<String, List<Questionobject>> inputs = new HashMap<String, List<Questionobject>>();
            for (Questionobject inputQuestion : questionInput) {
                String category = inputQuestion.getCategory();
                List<Questionobject> cats = inputs.get(category);
                if (cats == null) {
                    cats = new ArrayList<Questionobject>();
                    inputs.put(category, cats);
                }
                cats.add(inputQuestion);
            }

            List<UserPreference> prefs = new ArrayList<>();

            for (Map.Entry<String, List<Questionobject>> entry : inputs.entrySet()) {
                String key = entry.getKey();
                if (key.equalsIgnoreCase("GENERAL")) {
                    Sensitivity score = SensitivityCalculation.westinGeneralSensitivityIndex(entry.getValue());
                    Answerobject answer = new Answerobject();
                    answer.setCategory(key);
                    answer.score("" + score.getValue());
                    answers.add(answer);
                    UserPreference newPref = new UserPreference();
                    newPref.setCategory(key);
                    newPref.setAction("ALL");
                    newPref.setRole("ALL");
                    newPref.setInformationtype("ALL");
                    newPref.setPreference(answer.getScore());
                    prefs.add(newPref);
                } else if (key.equalsIgnoreCase("TRUST")) {
                    List<Questionobject> value = entry.getValue();
                    for (Questionobject questionTrust : value) {
                        Answerobject answer = new Answerobject();
                        answer.setCategory(key);
                        answer.setRoles(questionTrust.getWeight());
                        answer.score("" + questionTrust.getScore());
                        answers.add(answer);
                        UserPreference newPref = new UserPreference();
                        newPref.setCategory(key);
                        newPref.setAction("ALL");
                        newPref.setRole(questionTrust.getWeight());
                        newPref.setInformationtype("ALL");
                        prefs.add(newPref);
                        newPref.setPreference(answer.getScore());
                    }
                } else {
                    Sensitivity score = SensitivityCalculation.CategorySensitivityIndex(entry.getValue());
                    Answerobject answer = new Answerobject();
                    answer.setCategory(key);
                    answer.score("" + score.getValue());
                    answers.add(answer);
                    UserPreference newPref = new UserPreference();
                    newPref.setCategory(key);
                    newPref.setAction("ALL");
                    newPref.setRole("ALL");
                    newPref.setInformationtype("ALL");
                    prefs.add(newPref);
                    newPref.setPreference(answer.getScore());
                }

            }

            // Store the answers in the user preferences of the UPP
            UserPrivacyPolicy upp = null;
            boolean putOrPost = false;
            try {
                /**
                 * Get the UPP of the user. If there is a preference with
                 * GENERAL don't ask the GENERAL question again.
                 */
                upp = upp_api.userPrivacyPolicyUserIdGet(userId);
            } catch (eu.operando.core.pdb.client1.ApiException ex) {
                System.out.println("user is not found " + ex.getMessage());
                System.out.println("create new UPP for " + userId);
                upp = new UserPrivacyPolicy();
                upp.setUserId(userId);
                upp.setSubscribedOspPolicies(new ArrayList<OSPConsents>());
                upp.setSubscribedOspSettings(new ArrayList<OSPSettings>());
                putOrPost = true;
            }
            try {
                upp.setUserPreferences(prefs);
                if(putOrPost)
                    upp_api.userPrivacyPolicyPost(upp);
                else
                    upp_api.userPrivacyPolicyUserIdPut(userId, upp);
                System.out.println("upp for " + userId + " is updated");
            } catch (eu.operando.core.pdb.client1.ApiException ex) {
                System.out.println("exception " + ex.getMessage());
                return Response.status(Response.Status.NOT_FOUND).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Could not find user id")).build();
            }

            // Process the General Questions; Query for answers with category general
            ObjectMapper objectMapper = new ObjectMapper();
            String arrayToString = objectMapper.writeValueAsString(answers);

            computePC(userId, osp);

            return Response.status(Response.Status.OK).entity(arrayToString).build();
        } catch (InvalidAnswerException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Invalid answer input")).build();
        } catch (JsonProcessingException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Servier Error" + ex.getMessage())).build();
        }
    }

    /**
     * Invoke the PC API to compute the UPP for a user for this new OSP policy.
     * Called when a user subscribes to a new OSP
     * @param userId The OperandoID of the user subscribing.
     * @param ospId The OperandoID of the OSP to subscribe to.
     * @return The result message string (info from the HTTP call).
     */
    public boolean computePC(String userId, String ospId) {

        try {
            String urlInv = PC_URL + "/osp_policy_computer?user_id=" + userId +"&osp_id=" + ospId;
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpget = new HttpPost(urlInv);
            httpget.setHeader("Content-Type", "application/json");
            CloseableHttpResponse response1 = httpclient.execute(httpget);

            HttpEntity entity = response1.getEntity();
            System.out.println(response1.getStatusLine().getStatusCode());
            if(response1.getStatusLine().getStatusCode()!= 200) {
                return false;
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
