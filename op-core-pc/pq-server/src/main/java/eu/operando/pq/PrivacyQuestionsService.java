/*
 * The MIT License
 *
 * Copyright 2017 University of Southampton IT Innovation Centre.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package eu.operando.pq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import eu.operando.core.pdb.client1.model.AccessReason;
//import eu.operando.core.pdb.common.model.AccessReason;
import io.swagger.model.Questionobject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Reusable questionnaire implementation. Called by the Web Service Rest
 * endpoint.
 * @author pjg
 */
public class PrivacyQuestionsService {

    private static final String GENERALRESOURCES = "questions.json";
    private static final String MEDICALRESOURCES = "medical.json";
    private static final String PERSRESOURCES = "personal.json";
    private static final String ECONOMICRESOURCES = "economic.json";
    private static final String LOCATIONRESOURCES = "location.json";

    private static final int MAXCATEGORIES = 2;
    private static final int MAXROLES = 3;


    /**
     * Load the questions from the given resource file in JAR/WAR and
     * turn then into JSON string.
     * @return The list of questions held in the file.
     */
    private List<Questionobject> getQuestionsFromFile(String filename) {
        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(filename);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);

            ObjectMapper mapper = new ObjectMapper();

            //JSON from String to Object
            return mapper.readValue(content,  new TypeReference<List<Questionobject>>() { });

        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AccessReason> getReasonsFromFile(String filename) {
        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(filename);
            String content = CharStreams.toString(new InputStreamReader(fis, Charsets.UTF_8));
            Closeables.closeQuietly(fis);

            ObjectMapper mapper = new ObjectMapper();

            //JSON from String to Object
            return mapper.readValue(content,  new TypeReference<List<AccessReason>>() { });

        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get the three general questions.
     * @param language
     * @return
     */
    public List<Questionobject> getGeneralQuestions(String language) {
        switch (language) {
            case "EN":
                return getQuestionsFromFile(GENERALRESOURCES);
            default:
                return getQuestionsFromFile(GENERALRESOURCES);
        }

    }

    /**
     * Get the three general questions.
     * @param language
     * @return
     */
    public List<Questionobject> getDefaultQuestions(String language) {
        List<Questionobject> respQuestions = new ArrayList<>();
        switch (language) {
            case "EN":
                respQuestions.addAll(getQuestionsFromFile(PERSRESOURCES));
                break;
            default:
                respQuestions.addAll(getQuestionsFromFile(PERSRESOURCES));
                break;
        }
        return respQuestions;
    }

    /**
     * A string can be of the following categories.
     * MEDICAL, PERSONAL, ECONOMIC, LOCATION
     * @return
     */
    private String convertToCategory(String inputString){
        if(inputString.toLowerCase().contains("medical")) {
            return "MEDICAL";
        }
        else if(inputString.toLowerCase().contains("contact")) {
            return "PERSONAL";
        }
        if(inputString.toLowerCase().contains("personal")) {
            return "PERSONAL";
        }
        if(inputString.toLowerCase().contains("financial")) {
            return "FINANCIAL";
        }
        if(inputString.toLowerCase().contains("location")) {
            return "LOCATION";
        }
        return "PERSONAL";
    }

    /**
     * Quick fix - needs to take into account language and data type.
     */
    private String getCatfromDataType(String dType, String language) {

        switch (dType.toLowerCase()) {
            case "medical":
                return MEDICALRESOURCES;
            case "personal":
                return PERSRESOURCES;
            case "financial":
                return ECONOMICRESOURCES;
            case "location":
                return LOCATIONRESOURCES;
        }
        return null;
    }

    /**
     * Get the question to do with the roles in the reason policy.
     * @param policies
     * @param language
     * @return
     */
    public List<Questionobject> getTrustQuestions(List<AccessReason> policies, String language) {
        List<Questionobject> respQuestions = new ArrayList<>();

        /**
         * Select the questions from
         */
        List<String> roles = new ArrayList<>();
        for (AccessReason reason: policies) {
            roles.add(reason.getDatauser());
        }

        /**
         * relevant to these.
         */
        Map<String, Long> map = roles.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        List<Map.Entry<String, Long>> result = map.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(MAXROLES)
            .collect(Collectors.toList());

        for(int i=0; i<result.size(); i++) {
            Map.Entry<String, Long> get = result.get(i);
            String pop = get.getKey();
            Questionobject newQuestion = new Questionobject();

            // needs some language translation replacement here
            newQuestion.setQuestion("To what extent do you trust a " + pop + " with your personal data");
            newQuestion.setCategory("TRUST");
            newQuestion.setMeta("Strongly trust, trust, distrust, strongly distrust");
            newQuestion.setWeight(pop);
            newQuestion.setScore("String");
            respQuestions.add(newQuestion);
        }

        return respQuestions;
    }


    /**
     * From the list of access reason polices - examine the data types used
     * and then select up to 2 privacy categories related to these policies
     * and generate a set of questions based on these two types.
     * @param policies
     * @param language
     * @return
     */
    public List<Questionobject> getCategoryQuestions(List<AccessReason> policies, String language) {

        List<Questionobject> respQuestions = new ArrayList<>();

        /**
         * Get the list of data subjects and align them with one of the
         * current privacy categories.
         */

        List<String> dataCategories = new ArrayList<>();
        for (AccessReason reason: policies) {
            dataCategories.add(convertToCategory(reason.getDatatype()));
        }

        /**
         * Get the most popular categories in the list and add questions
         * relevant to these.
         */
        Map<String, Long> map = dataCategories.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        List<Map.Entry<String, Long>> result = map.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .limit(MAXCATEGORIES)
            .collect(Collectors.toList());

        for(int i=0; i<result.size(); i++) {
            Map.Entry<String, Long> get = result.get(i);
            String pop = get.getKey();
            String catfromDataType = getCatfromDataType(pop, language);
            respQuestions.addAll(getQuestionsFromFile(catfromDataType));
        }

        return respQuestions;
    }
}
