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
//      Created By :            Robbie Anderson
//      Created Date :          2016-09-02
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////


/*
 * @author ra16 <ra16@it-innovation.soton.ac.uk
 */
package eu.operando.core.cpcu.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import eu.operando.core.cpcu.api.ConfigurationAPI;
import eu.operando.core.cpcu.exceptions.NoSuchQuestionnaireException;
import eu.operando.core.cpcu.exceptions.NoSuchServiceException;
import eu.operando.core.cpcu.exceptions.QuestionnaireDependancyError;
import eu.operando.core.cpcu.questionnaires.DefaultDataProcessor;
import eu.operando.core.cpcu.questionnaires.Statement;

/**
 * QuestionnaireHandler is the main control point for the Questionnaires,
 * dealing with the displaying and collecting of results to be passed into
 * different parts of the system.
 */
public class QuestionnaireHandler {

    /**
     * The Constant directory location.
     */
    public static final String FILE_LOC = "webapps/operandocpcu/WEB-INF/classes/";

    /**
     * The Constant config directory location.
     */
    public static final String CONFIG_FILE_LOC = FILE_LOC + "config/";

    /**
     * The Constant user directory location.
     */
    public static final String USER_FILE_LOC = FILE_LOC + "users/";

    /**
     * The Constant question pool directory location.
     */
    public static final String QUESTIONPOOL_FILE_LOC = FILE_LOC + "questionpools/";

    /**
     * The Constant base package used within the Reflection Libraries.
     */
    public static final String BASE_PACKAGE = "eu.operando.core.cpcu.";

    /**
     * The logger.
     */
    public static final Logger logger = Logger.getLogger(QuestionnaireHandler.class);

    /**
     * The Constant NO_OF_QUESTIONS per category.
     */
    public static final Double NO_OF_QUESTIONS = 3.0;

    /**
     * The Constant LARGEST_RATING. As ratings are currently 1-5, this is set to
     * 5
     */
    public static final Integer LARGEST_RATING = 5;

    /**
     * The json input.
     */
    private volatile JSONArray jsonInput;

    /**
     * The api.
     */
    private ConfigurationAPI api;

    /**
     * The handler.
     */
    private static QuestionnaireHandler handler;

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        QuestionnaireHandler.getInstance().beginQuestionnaire(0, "rob1", -1);
    }

    /**
     * Gets the single instance of QuestionnaireHandler.
     *
     * @return single instance of QuestionnaireHandler
     */
    public static QuestionnaireHandler getInstance() {
        if (handler == null) {
            handler = new QuestionnaireHandler();
        }
        return handler;
    }

    /**
     * Instantiates a new questionnaire handler.
     */
    private QuestionnaireHandler() {
        reloadApp();
        this.api = new ConfigurationAPI(this);
        getLogger().info("API has been successfully started");
    }

    /**
     * Gets the questionnaire corresponding to the Index passed. The session
     * information is used to create the correct file location for the users
     * files.
     *
     * @param index the index of the questionnaire to access (Internal ID)
     * @param session the session of the current user
     * @param serviceID the service ID
     * @return the questionnaire corresponding to the index/ID passed
     * @throws Exception the exception
     * @throws IllegalArgumentException the illegal argument exception
     * @throws SecurityException the security exception
     */
    public AbstractQuestionnaire getQuestionnaire(int index, String session, int serviceID) throws Exception {
        return loadQuestionnaire(index, session, serviceID);
    }

    /**
     * Gets the JSON object from JSON Array. Uses the index passed as the ID,
     * and then finds the Object with the corresponding ID in the Array
     *
     * @param index the index of the Questionnaire
     * @return the JSON object from JSON Array
     * @throws NoSuchQuestionnaireException the no such questionnaire exception
     */
    private JSONObject getJSONObjectFromJSON(int index) throws NoSuchQuestionnaireException {
        for (Object obj : jsonInput) {
            JSONObject o = (JSONObject) obj;
            getLogger().info("getJSONObjectFromJSON " + o.toJSONString());
            if (index == ((Long) o.get("id")).intValue()) {
                getLogger().info("getJSONObjectFromJSON match " + o.toString());
                return o;
            }
        }
        getLogger().info("getJSONObjectFromJSON ex no such Q");
        throw new NoSuchQuestionnaireException();
    }

    /**
     * Reload app. This reload the currently loaded configuration. Should also
     * be concurrency safe, as Access is serialised.
     */
    public void reloadApp() {
        jsonInput = initialiseArray();

    }

    /**
     * Initialise JSON Array, catching errors if any occur. Will read the
     * Questionnaires config file and make sure there is no errors with the
     * loaded Questionnaires.
     *
     * @return the JSON array
     */
    private JSONArray initialiseArray() {
        if (checkDirectories()) {
            JSONParser parser = new JSONParser();
            File f = new File(CONFIG_FILE_LOC + "Questionnaires.json");
            try {
                return (JSONArray) parser.parse(new InputStreamReader(new FileInputStream(f.getPath())));
            } catch (IOException e) {
                getLogger().info("Attempted to read Questionniares.json at " + FILE_LOC + ", but an I/O exception was thrown. " + e);
            } catch (ParseException e) {
                getLogger().info("Attempted to read Questionniares.json at " + FILE_LOC + ", but the config file was not correctly formatted. " + e);
            }
            getLogger().info(jsonInput.size() + " Questionnaires have been loaded.");
        }
        getLogger().info("There was an error initialising the JSON Array");
        return null;
    }

    /**
     * Check the main directories exist in the project. If they dont, false is
     * returned and the Array (in initialiseArray) is set to null. DOES NOT
     * check the files themselves, only the directories exist, so NullPointer is
     * not thrown.
     *
     * @return true, if all directories exist.
     */
    private boolean checkDirectories() {
        if (!new File(FILE_LOC).exists()) {
            getLogger().info("The base File " + FILE_LOC + " cannot be found. System will not run until this is resolved .");
            return false;
        }
        if (!new File(FILE_LOC + BASE_PACKAGE.replace('.', '/')).exists()) {
            getLogger().info("The base package " + FILE_LOC + BASE_PACKAGE.replace('.', '/') + " cannot be found. System will not run until this is resolved.");
            return false;
        }
        if (!new File(CONFIG_FILE_LOC + "Questionnaires.json").exists()) {
            getLogger().info("The config file at " + CONFIG_FILE_LOC + "Questionnaires.json" + " cannot be found. System will not run until this is resolved.");
            return false;
        }
        return true;
    }

    /**
     * Load questionnaire. This loads and initialises the classes for the
     * Questionnaire to function, namely @See
     * eu.operando.core.questionnaires.DefaultDataProcessor and
     *
     * @See eu.operando.core.questionnaires.DefaultQuestionnaires. However, this
     * is dependant on the Configuration, as other classes may be loaded.
     *
     * @param index the index of the questionnaire to access (Internal ID)
     * @param session the session of the current user
     * @param serviceID the service ID
     * @return the questionnaire corresponding to the index/ID passed
     * @throws Exception the error that occured
     */
    private AbstractQuestionnaire loadQuestionnaire(int index, String session, int serviceID) throws Exception {
        JSONObject obj = getJSONObjectFromJSON(index);
        if (((Long) obj.get("dependancies")).intValue() != -1) {
            String dependancyType = (String) getJSONObjectFromJSON(((Long) obj.get("dependancies")).intValue()).get("type");
            System.out.println(dependancyType);
            if (!doDependanciesExist(dependancyType)) {
                throw new QuestionnaireDependancyError();
            }
        }
        DefaultDataProcessor dp;
        if (!"Default".equals(obj.get("processor")) && !"".equals(obj.get("processor"))) {
            dp = (DefaultDataProcessor) Class.forName(BASE_PACKAGE + "questionnaires." + (String) obj.get("processor") + "DataProcessor")
                    .getConstructor(String.class, Integer.class, String[].class, int.class).newInstance(obj.get("type"), ((Long) obj.get("id")).intValue(), getServiceData(serviceID), serviceID);
        } else {
            dp = new DefaultDataProcessor((String) obj.get("type"), ((Long) obj.get("id")).intValue(), getServiceData(serviceID), serviceID);
        }

        //This creates the questionnaire in the specific index, using reflection library to read in the information from the config file.
        return (AbstractQuestionnaire) Class.forName(BASE_PACKAGE + "questionnaires." + obj.get("classLoader") + "Questionnaire")
                .getConstructor(String.class, Integer.class, DefaultDataProcessor.class, String.class)
                .newInstance(obj.get("type"),
                        "".equals(obj.get("dependancies")) ? null : ((Long) obj.get("dependancies")).intValue(), dp, obj.get("metadata"));
    }

    /**
     * Gets the service data from the config file.private TODO: Replace with the
     * method validateOSPID from HTTPDataConsumer - However, the service data
     * must be returned IN THE SAME FORMAT as the array returned, or the
     * DataProcessor methods will have to be changed to support the NEW schema
     * you create. (This data is passed into the dataprocessor (in constructor)
     * with serveral getter methods within DataProcessor to handle getting the
     * data from this array) Currently only a location is passed, but this will
     * have to be changed (probably) to the actual data lists. Should be a
     * simple enough re-write.
     *
     * @param serviceID the service ID
     * @return the service data with the location of the data to be accessed
     * @throws NoSuchServiceException the no such service exception
     */
    private String[] getServiceData(int serviceID) throws NoSuchServiceException {
        JSONParser parser = new JSONParser();
        JSONArray array = null;
        try {
            array = (JSONArray) parser.parse(new FileReader(CONFIG_FILE_LOC + "Service.json"));
        } catch (FileNotFoundException e) {
            getLogger().info("Could not find Service.json in the directory " + CONFIG_FILE_LOC + " " + e);
        } catch (IOException e) {
            getLogger().info("Could not read Services.json " + e);
        } catch (ParseException e) {
            getLogger().info("Services.json is not formatted correctly " + e);
        }
        //Finds the first instance with the correct ID - will not break if there is two services with the same id
        for (Object o : array) {
            if (serviceID <= -1) {
                return null;
            }
            JSONObject obj = (JSONObject) o;
            if (((Long) obj.get("id")).intValue() == serviceID) {
                try {
                    return new String[]{
                        (String) obj.get("aquisitionMethod"),
                        (String) obj.get("preference"),
                        (String) obj.get("roleLocation"),
                        (String) obj.get("dataLocation")
                    };
                } catch (NullPointerException e) {
                    getLogger().info("The Service " + serviceID + " could not be parsed. Once of the fields is null " + e);
                } catch (ClassCastException e) {
                    getLogger().info("The Service " + serviceID + " could not be parsed. Once of the fields is not a string " + e);
                }
            }
        }
        throw new NoSuchServiceException();
        //Check if user is subscribed to this service?

        //A specific category still needs to be focused on however, so the service data needs to provide a Category it best relates to.
        //Or a combination of categories and weights? so it can be calculated from the preferences.
        //Get service list data and find the files relating to that (or load it in from a database, which will be implemented eventually)
        //Return the 'Files' (could also just be the location of the services to be accessed, which is then passed to the DataProcessor)
        //This would most likely be a location in a SQL table which the proceesor can then access, process and pass back to the questionnaire.
    }

    /**
     * Begin questionnaire. This one of the public methods to interface with the
     * Web components of the Application. Starts a user accessing a
     * questionnaire.
     *
     * @param questionnaireType the index of the questionnaire to access
     * (Internal ID)
     * @param session the session of the current user
     * @param serviceID the ID of the service currently being used
     * @return the string to be used by the XForms engine
     */
    public String beginQuestionnaire(int questionnaireType, String session, int serviceID) {
        try {
            if (jsonInput == null) {
                getLogger().info("An error occured on startup, the application did not start correctly.");
                return generateResponse("An error has occured during startup, and is preventing request handling", session);
            }

            getLogger().info("Servicing " + session + " client accessing " + getJSONObjectFromJSON(questionnaireType).get("type") + " Questionnaire");
            AbstractQuestionnaire q = getQuestionnaire(questionnaireType, session, serviceID);
            if (q != null) {
                getLogger().info("Client " + session + " successfully accessed loaded Questionnaire, " + q.getType() + ". Statements are now being Generated.");
                return generateResponse("", q.getStatements(NO_OF_QUESTIONS, LARGEST_RATING,
                        //Checks to make sure if there is any dependencies, they are loaded into the system
                        q.getDependancy() <= -1 ? null : retrievePreferences(q.getDependancy(), serviceID, getUserDataFile(session))), session);
            }
            getLogger().info("An error occured while handling request from " + session + " for Questionnaire"
                    + getJSONObjectFromJSON(questionnaireType).get("type"));
        } catch (Exception e) {
            getLogger().info("An error occured whilst handling a read request from " + session + " [Error]: " + e);
            return generateResponse(e.toString(), session);
        }
        return generateResponse("An unidentified error occured", session);
    }

    /**
     * Get questionnaire preferences. This one of the public methods to
     * interface with the Web components of the Application. Starts a user
     * accessing a questionnaire.
     *
     * @param session the session of the current user
     * @param serviceID the ID of the service currently being used
     * @return the string to be used by the XForms engine
     */
    public String getUserPreferences(String session, int serviceID) {
        try {
            if (jsonInput == null) {
                getLogger().info("An error occured on startup, the application did not start correctly.");
                return generateResponse("An error has occured during startup, and is preventing request handling", session);
            }

            String serviceIdentifier = Integer.toString(serviceID);
            if (serviceID <= -1) {
                serviceIdentifier = "";
            }
            //AbstractQuestionnaire q = getQuestionnaire(1, session, serviceID);
            getLogger().info("Servicing " + session + " client accessing preferences");

            File privacyPrefsFile = new File(getUserDataFile(session) + "/"
                    + serviceIdentifier + getJSONObjectFromJSON(1).get("type") + "preferences.json");

            if (privacyPrefsFile.exists()) {
                JSONParser parser = new JSONParser();
                JSONArray resultArray = (JSONArray) parser.parse(new FileReader(privacyPrefsFile));

                getLogger().info("Array result size: " + resultArray.size());
                return generatePreferencesResponse("", resultArray.toJSONString(), session);
            }
            getLogger().info("An error occured while handling request from " + session + " for preference results");
        } catch (Exception e) {
            getLogger().info("An error occured whilst handling a read request from " + session + " [Error]: " + e);
            return generateResponse(e.toString(), session);
        }
        return generateResponse("An unidentified error occured", session);
    }

    /**
     * Gets the user data file.
     *
     * @param session the session of the current user
     * @return the user data file
     */
    private String getUserDataFile(String session) {
        File f = new File(USER_FILE_LOC + session);
        if (!f.exists()) {
            f.mkdirs();
        }
        return USER_FILE_LOC + session + "/";
    }

    /**
     * Do dependencies exist for the Questionnaire. This only checks to see if
     * the Questionnaire exists within the Configuration It does NOT check to
     * see if the preferences file exists.
     *
     * @param type the type
     * @return true, the dependancy exists
     */
    private boolean doDependanciesExist(String type) {
        for (Object obj : jsonInput) {
            JSONObject o = (JSONObject) obj;
            if (o.get("type").equals(type)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieve preferences from the User File. This method will need to be
     * re-implemented to hook into the system TODO:This method will be replaced
     * with the method in HTTPDataConsumer
     *
     * @param index the index of the questionnaire to access (Internal ID)
     * @param fileLoc the file location of the Users data file
     * @return the map between the Category and the result
     * @throws Exception the exception
     */
    private Map<String, Double> retrievePreferences(Integer index, int serviceID, String fileLoc) throws Exception {
        String serviceIdentifer = Integer.toString(serviceID);
        if (serviceID <= -1) {
            serviceIdentifer = "";
        }
        getLogger().info("retrievePreferences " + index + " " + fileLoc);
        JSONParser parser = new JSONParser();
        Map<String, Double> result = new HashMap<>();
        try {
            getLogger().info("FileReader " + getJSONObjectFromJSON(index).get("type"));
            JSONArray array = (JSONArray) parser.parse(new FileReader(fileLoc + serviceIdentifer + getJSONObjectFromJSON(index).get("type") + "preferences.json"));

            for (Object o : array) {
                JSONObject obj = (JSONObject) o;
                result.put((String) obj.get("Category"), (Double) obj.get("Result"));
            }
        } catch (IOException e) {
            getLogger().info("Attempted to read preferences at " + fileLoc + ", but an I/O exception was thrown. " + e);
            throw new QuestionnaireDependancyError();
        } catch (ParseException e1) {
            getLogger().info("Attempted to read preferences at " + fileLoc + ", but a ParseException was thrown. " + e1);
            throw e1;
        }
        return result;
    }

    /**
     * Write preferences to the file. TODO:This method will be replaced with the
     * one in HTTPDataConsumer when implementing into OPERANDO
     *
     * @param results the map between the Category and result. This is run post
     * result processing.
     * @param session the session
     * @param serviceID the service ID
     * @param type the type
     */
    private void writePreferences(Map<DataHolder, Double> results, String session, int serviceID, String type) {
        getLogger().info("writePreferences");
        String serviceIdentifer = Integer.toString(serviceID);
        if (serviceID <= -1) {
            serviceIdentifer = "";
        }
        String fileLoc = getUserDataFile(session);
        if (!new File(fileLoc).exists()) {
            new File(fileLoc).mkdirs();
        }
        JSONArray array = new JSONArray();

        results.keySet().forEach(key -> {
            JSONObject obj = new JSONObject();

            obj.put("Category", key.getData());
            obj.put("Result", results.get(key));

            array.add(obj);
        });
        try {
            getLogger().info("writePreferences to: " + serviceIdentifer + type);
            FileWriter writer = new FileWriter(new File(fileLoc + serviceIdentifer + type + "preferences.json"));
            writer.write(array.toJSONString());
            writer.close();
        } catch (Exception e) {
            getLogger().error("An Exception occured when processing " + fileLoc + serviceIdentifer + type + "preferences.json" + ".", e);
        }
    }

    /**
     * Gets the logger for the Application. All Logs are marked with the session
     * and user they are accessing if an error occurs with processing a request.
     *
     * @return the logger
     */
    public static Logger getLogger() {
        return logger;
    }

    /**
     * Process results. Called from the Spring Application, and will process the
     * results obtained and return a response according to the action taken.
     *
     * @param results the results
     * @param type the index of the questionnaire to access (Internal ID)
     * @param session the session of the current user
     * @param serviceID the service ID
     * @return A JSON string representing the response
     */
    public String processResults(Map<Statement, Double> results, int type, String session, int serviceID) {
        AbstractQuestionnaire q = null;
        try {
            getLogger().info("processResuts ");
            q = getQuestionnaire(type, session, serviceID);
            getLogger().info("processResuts 1");
            if (q != null) {
                getLogger().info("processResuts 2");
                writePreferences(q.processResults(results, 10, 5), session, serviceID, q.getType());
                getLogger().info("Request from " + session + " has been successfully handled");
                return generateResponse("Successfully submitted", session);
            }
        } catch (Exception e) {
            getLogger().info("An error occured whilst handling a write request from " + session + " " + e);
            return generateResponse(e.getMessage(), session);
        }
        return generateResponse("An unidentified error occured", session);
    }

    /**
     * Generates a JSON response wrapper for the content passed to the method.
     *
     * @param e the exception
     * @param session the session of the current user
     * @return the JSON string
     */
    private String generateResponse(String e, String session) {
        return "{\"response\": { \"error\":\"" + e + "\",\"session\":\"" + session + "\",\"questionnaire\":{}}}";
    }

    /**
     * Generates a JSON response wrapper for the content passed to the method.
     *
     * @param e the exception
     * @param content the content (in JSON form)
     * @param session the session of the current user
     * @return the JSON string
     */
    private String generateResponse(String e, String content, String session) {
        return "{\"response\": { \"error\":\"" + e + "\",\"session\":\"" + session + "\",\"questionnaire\":" + content + "}}";
    }

    private String generatePreferencesResponse(String e, String content, String session) {
        return "{\"response\": { \"error\":\"" + e + "\",\"session\":\"" + session + "\",\"preferences\":" + content + "}}";
    }

    /**
     * Gets the config API.
     *
     * @return the config API
     */
    public ConfigurationAPI getConfigAPI() {
        return api;
    }

}
