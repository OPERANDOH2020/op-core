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
package eu.operando.core.cpcu.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import eu.operando.core.cpcu.exceptions.ConfigNotInitialised;
import eu.operando.core.cpcu.exceptions.NoSuchQuestionnaireException;
import eu.operando.core.cpcu.exceptions.NoSuchServiceException;
import eu.operando.core.cpcu.main.DataHolder;
import eu.operando.core.cpcu.main.QuestionnaireHandler;
import eu.operando.core.cpcu.questionnaires.ActionStatement;
import eu.operando.core.cpcu.questionnaires.ServiceStatement;
import eu.operando.core.cpcu.questionnaires.Statement;
import eu.operando.core.cpcu.servlet.configurations.QuestionConfiguration;
import eu.operando.core.cpcu.servlet.configurations.QuestionnaireConfiguration;
import eu.operando.core.cpcu.servlet.configurations.ServiceConfiguration;
import eu.operando.core.cpcu.servlet.springwrappers.Category;
import eu.operando.core.cpcu.servlet.springwrappers.XFResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}

@RestController
@RequestMapping("/api")
@Api(value = "/api", description = "Contains the API operations for accessing, editing and deleting Questions, "
        + "Questionnaires and Services outside of the normal Application operation.")
class MainAPIController {

    QuestionnaireHandler handler;

    MainAPIController() {
        handler = QuestionnaireHandler.getInstance();
    }

    //Service API
    @ApiOperation(value = "Read Services", notes = "Returns the JSON representation of the currently accessible services. If the parameters are filled then a "
            + "specific service can be returned.", produces = "application/json")
    @RequestMapping(value = "/se", method = RequestMethod.GET)
    ResponseEntity<List<ServiceConfiguration>> getServices(@ApiParam(value = "The String to search by, when searching for a specific service", required = false)
            @RequestParam(value = "search", required = false) String search,
            @ApiParam(value = "The field to search for the String. This can be any valid field in the Service.", required = false)
            @RequestParam(value = "field", required = false) String field) {
        if (search == null || field == null) {
            try {
                return new ResponseEntity<>(handler.getConfigAPI().getServices(), HttpStatus.ACCEPTED);
            } catch (IOException | ParseException | ConfigNotInitialised e) {
                QuestionnaireHandler.getLogger().error("An error occured when reading the services " + e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            try {
                return new ResponseEntity<>(handler.getConfigAPI().searchServices(search, field), HttpStatus.ACCEPTED);
            } catch (IOException | ParseException e) {
                QuestionnaireHandler.getLogger().error("An error occured when searching the services " + e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (NoSuchServiceException | NoSuchFieldException | ConfigNotInitialised e) {
                QuestionnaireHandler.getLogger().error("An error occured when searching the services " + e);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    @ApiOperation(value = "Reads a specific Service", notes = "Gets a specific Service as a JSON String", produces = "application/json")
    @RequestMapping(value = "/se/{serviceID}", method = RequestMethod.GET)
    ResponseEntity<List<ServiceConfiguration>> getSpecificService(@PathVariable int serviceID) {
        try {
            return new ResponseEntity<>(handler.getConfigAPI().searchServices(Integer.toString(serviceID), "ID"), HttpStatus.CREATED);
        } catch (IOException | ParseException e) {
            QuestionnaireHandler.getLogger().error("An error occured when getting a specific the service " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoSuchServiceException | NoSuchFieldException | ConfigNotInitialised e) {
            QuestionnaireHandler.getLogger().error("An error occured when reading the services. Could not find the requested resource. " + e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Creates a Service", notes = "Creates a new Service and adds it to the accessible Services")
    @RequestMapping(value = "/se", method = RequestMethod.POST)
    ResponseEntity<?> createService(@ApiParam(value = "The ServiceConfiguration containing the parameters to create a new Service", required = true)
            @RequestBody ServiceConfiguration sc) {
        try {
            handler.getConfigAPI().addService(sc);
        } catch (Exception e) {
            QuestionnaireHandler.getLogger().error("An error occured when creating a service " + e);
            return new ResponseEntity<>(new String(e.getMessage()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Updates a Service", notes = "Updates a specific service with the data submitted to this operation.")
    @RequestMapping(value = "/se/{serviceID}", method = RequestMethod.POST)
    ResponseEntity<?> updateService(@ApiParam(value = "The ServiceConfiguration containing the parameters to create a new Service", required = true)
            @RequestBody ServiceConfiguration sc,
            @ApiParam(value = "The ServiceID of the service to be updated", required = true) @PathVariable int serviceID) {
        try {
            handler.getConfigAPI().updateService(serviceID, sc);
        } catch (Exception e) {
            QuestionnaireHandler.getLogger().error("An error occured when updating a service " + e);
            return new ResponseEntity<>(new String(e.getMessage()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Deletes a Service", notes = "Deletes a specific service from the accessible services")
    @RequestMapping(value = "/se/{serviceID}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteService(@ApiParam(value = "The ServiceID to be deleted", required = true) @PathVariable int serviceID) {

        try {
            handler.getConfigAPI().deleteService(serviceID);
        } catch (Exception e) {
            QuestionnaireHandler.getLogger().error("An error occured when deleting a service " + e);
            return new ResponseEntity<>(new String(e.getMessage()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Questionnaire API
    @ApiOperation(value = "Reads Questionnaires", notes = "Gets the accessible questionnaires as a JSON String. "
            + "If optional parameters are filled, then a specific Questionnaire can be returned. Must be correctly capitalised to return"
            + "the correct result. Will return a JSON Array if multiple Objects satisfy the predicate.")
    @RequestMapping(value = "/qu", method = RequestMethod.GET)
    ResponseEntity<List<QuestionnaireConfiguration>> getQuestionnaires(@ApiParam(value = "The String to search by, when searching for a specific Questionnaire", required = false)
            @RequestParam(value = "search", required = false) String search,
            @ApiParam(value = "The field to search for the String. This can be any valid field in the Questionnaire.", required = false)
            @RequestParam(value = "field", required = false) String field) {
        if (search == null || field == null) {
            try {
                return new ResponseEntity<>(handler.getConfigAPI().getQuestionnaire(), HttpStatus.ACCEPTED);
            } catch (IOException | ParseException | ConfigNotInitialised e) {
                QuestionnaireHandler.getLogger().error("An error occured when getting the questionnaires Configuration " + e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            try {
                return new ResponseEntity<>(handler.getConfigAPI().searchQuestionnaires(search, field), HttpStatus.ACCEPTED);
            } catch (IOException | ParseException e) {
                QuestionnaireHandler.getLogger().error("An error occured when searching the questionnaires " + e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (NoSuchQuestionnaireException | NoSuchFieldException | ConfigNotInitialised e) {
                QuestionnaireHandler.getLogger().error("An error occured when searching the questionnaires " + e);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
    }

    @ApiOperation(value = "Retrieves a Question pool", notes = "Gets the entire Question pool for a given "
            + "Questionnaire. NOTE: This returns all the Questions, not a subset (as done in the Application)",
             produces = "application/json")
    @RequestMapping(value = "/qp/{questionnaireID}", method = RequestMethod.GET)
    ResponseEntity<List<QuestionConfiguration>> getQuestionPool(@PathVariable int questionnaireID) {
        try {
            return new ResponseEntity<>(handler.getConfigAPI().getQP(questionnaireID), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            QuestionnaireHandler.getLogger().error("An error occured when getting a specific question pool " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/qp", method = RequestMethod.GET)
    ResponseEntity<List<QuestionConfiguration>> getQuestionpl() {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CrossOrigin
    @ApiOperation(value = "Reads a specific Questionnaire", notes = "Gets a specific Questionnaire as a JSON String")
    @RequestMapping(value = "/qu/{questionnaireID}", method = RequestMethod.GET)
    ResponseEntity<List<QuestionnaireConfiguration>> getSpecificQuestionnaire(@PathVariable int questionnaireID) {
        try {
            return new ResponseEntity<>(handler.getConfigAPI().searchQuestionnaires(Integer.toString(questionnaireID), "ID"), HttpStatus.CREATED);
        } catch (IOException | ParseException e) {
            QuestionnaireHandler.getLogger().error("An error occured when getting a specific questionnaire " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoSuchQuestionnaireException | NoSuchFieldException | ConfigNotInitialised e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Creates a Questionnaire", notes = "Creates a new Questionnaire and adds it to the accessible Questionnaires")
    @RequestMapping(value = "/qu", method = RequestMethod.POST)
    ResponseEntity<?> createQuestionnaire(@ApiParam(value = "The QuestionnaireConfiguration which defines the parameters to create a new Questionnaire", required = true)
            @RequestBody QuestionnaireConfiguration qc) {
        System.out.println(qc);
        try {
            handler.getConfigAPI().createQuestionnaire(qc);
        } catch (Exception e) {
            QuestionnaireHandler.getLogger().error("An error occured when creating a specific questionnaire " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Updates a Questionnaire", notes = "Updates a new Questionnaire and adds it back to the accessible Questionnaires")
    @RequestMapping(value = "/qu/{questionnaireID}", method = RequestMethod.POST)
    ResponseEntity<?> updateQuestionniare(@ApiParam(value = "The QuestionnaireConfiguration which defines the parameters to create a new Questionnaire", required = true)
            @RequestBody QuestionnaireConfiguration qc,
            @ApiParam(value = "The Questionnaire ID to be updated") @PathVariable int questionnaireID) {
        try {
            handler.getConfigAPI().updateQuestionnaire(qc, questionnaireID);
        } catch (Exception e) {
            QuestionnaireHandler.getLogger().error("An error occured when updating a specific questionnaire " + e);
            return new ResponseEntity<>(new String(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Deletes a Question", notes = "Deletes a specific Question within a Questionnaire")
    @RequestMapping(value = "/qp/{questionnaireID}/{questionID}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteQuestion(@ApiParam(value = "The questionnaireID of the Questionnaire that contains the Question", required = true) @PathVariable String questionnaireID,
            @ApiParam(value = "The questionID which identifies the Question to be deleted", required = true) @PathVariable String questionID) {

        try {
            handler.getConfigAPI().deleteQuestion(Integer.parseInt(questionnaireID), Integer.parseInt(questionID));
        } catch (Exception e) {
            QuestionnaireHandler.getLogger().error("An error occured when deleting a specific question " + e);
            return new ResponseEntity<>(new String(e.getMessage()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Updates a Question", notes = "Updates a specfic Question within a Questionnaire with the given JSON Data")
    @RequestMapping(value = "/qp/{questionnaireID}/{questionID}", method = RequestMethod.POST)
    ResponseEntity<?> updateQuestion(@ApiParam(value = "The questionnaireID of the Questionnaire that contains the Question", required = true) @PathVariable String questionnaireID,
            @ApiParam(value = "The questionID which identifies the Question to be updated", required = true) @PathVariable String questionID,
            @ApiParam(value = "The QuestionnaireConfiguration which defines the parameters to create a new Questionnaire", required = true) @RequestBody QuestionConfiguration qc) {

        try {
            handler.getConfigAPI().updateQuestion(Integer.parseInt(questionnaireID), Integer.parseInt(questionID), qc);
        } catch (Exception e) {
            e.printStackTrace();
            QuestionnaireHandler.getLogger().error("An error occured when updating a specific question " + e);
            return new ResponseEntity<>(new String(e.getMessage()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Adds a Question", notes = " Adds a new Question to a specfic Questionnaire")
    @RequestMapping(value = "/qp/{questionnaireID}", method = RequestMethod.POST)
    ResponseEntity<?> addQuestion(@ApiParam(value = "The questionnaireID of the Questionnaire that contains the Question", required = true) @PathVariable String questionnaireID,
            @ApiParam(value = "The QuestionnaireConfiguration which defines the parameters to create a new Questionnaire", required = true) @RequestBody QuestionConfiguration qc) {

        try {
            handler.getConfigAPI().addQuestion(Integer.parseInt(questionnaireID), qc);
        } catch (Exception e) {
            QuestionnaireHandler.getLogger().error("An error occured when adding a specific question " + e);
            return new ResponseEntity<>(new String(e.getMessage()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Verifies an ID is valid", notes = "Checks through the Questionnaire List to make sure the ID is valid")
    @RequestMapping(value = "/qpid/{questionnaireID}/{validateID}", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<?> validateQuestionID(@PathVariable int questionnaireID, @PathVariable int validateID) {
        String val;
        try {
            val = handler.getConfigAPI().isValidQuestionID(questionnaireID, validateID);
        } catch (Exception e) {
            QuestionnaireHandler.getLogger().error("An error occured when verifying if an ID is valid" + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(val, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Verifies an ID is valid", notes = "Checks through the Questionnaire List to make sure the ID is valid")
    @RequestMapping(value = "/quid/{validateID}", method = RequestMethod.GET, produces = "application/json")
    ResponseEntity<?> validateQuestionnaireID(@PathVariable int validateID) {
        String val;
        try {
            val = handler.getConfigAPI().isValidQuestionnaireID(validateID);
        } catch (Exception e) {
            QuestionnaireHandler.getLogger().error("An error occured when verifying the Questionnaire ID is valid " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(val, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Deletes a Questionnaire", notes = "Deletes a specific Questionnaire")
    @RequestMapping(value = "/qu/{questionnaireID}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteQuestionnaire(@ApiParam(value = "The questionnaireID of the Questionnaire that contains the Question to be deleted", required = true)
            @PathVariable int questionnaireID) {
        try {
            handler.getConfigAPI().deleteQuestionnaire(questionnaireID);
        } catch (Exception e) {
            QuestionnaireHandler.getLogger().error("An error occured when deleting a specific questionnaire " + e);
            return new ResponseEntity<>(new String(e.getCause().getMessage()), null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Reload", notes = "Reloads the Active configuration within the Application, this means that any changes made within the API"
            + " are then accessible to the CPCU Application.")
    @RequestMapping(value = "/reload", method = RequestMethod.GET)
    ResponseEntity<?> reloadApp() {
        QuestionnaireHandler.getInstance().reloadApp();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Display the Admin Panel", notes = "This displays a UI in which the Questionnaires and Questions can be altered")
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    ModelAndView showPage() {
        String url = "http://localhost:8080/xforms-filter/html/adminpanel.html";
        return new ModelAndView("redirect:" + url);
    }
}

@RestController
@RequestMapping("/cpcu")
@Api(value = "/cpcu", description = "Contains the operations used in the CPCU processing within OPERANDO")
class ApplicationController {

    @RequestMapping(value = "/xxf/{userID}/{serviceID}/{questionnaireID}", method = RequestMethod.GET)
    @ResponseBody
    ModelAndView xForms(@PathVariable int questionnaireID, @PathVariable int serviceID, @PathVariable String userID) {
        String url = "http://localhost:8080/xforms-filter/xf/pollxml.jsp?questionnaireID=" + questionnaireID + "&serviceID=" + serviceID + "&userID=" + userID;
        return new ModelAndView("redirect:" + url);
    }

    @RequestMapping(value = "/{userID}/{serviceID}/{questionnaireID}", method = RequestMethod.GET)
    ResponseEntity<?> getApplicationQuestionnaire(@PathVariable String userID, @PathVariable int serviceID, @PathVariable int questionnaireID) {
        String s = QuestionnaireHandler.getInstance().beginQuestionnaire(questionnaireID, userID, serviceID);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        return new ResponseEntity<>(s, httpHeaders, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{userID}/{serviceID}", method = RequestMethod.GET)
    ResponseEntity<?> getApplicationQuestionnaireResult(@PathVariable String userID, @PathVariable int serviceID) {
        String s = QuestionnaireHandler.getInstance().getUserPreferences(userID, serviceID);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        return new ResponseEntity<>(s, httpHeaders, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{userID}/{serviceID}/{questionnaireID}", method = RequestMethod.POST, consumes = "application/json")
    ResponseEntity<?> submitQuestionnaire(@RequestBody XFResponse response, @PathVariable String userID,
            @PathVariable int serviceID, @PathVariable int questionnaireID) {
        QuestionnaireHandler.getLogger().info("POST questionnaire: " + response.toString());
        String s = QuestionnaireHandler.getInstance().processResults(getResults(response), questionnaireID, userID, serviceID);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("content-type", "application/json");
        return new ResponseEntity<>(s, httpHeaders, HttpStatus.ACCEPTED);
    }

    /**
     * Gets the results out of the XFResponse instance, and returns a The map
     * containing the Statement and its result. To do this, we must iterate
     * through the Categories and get out the Spring wrapper version of
     * Statement (eu.operando.core.cpcu.servlet.springwrappers.Statement), which
     * is used by spring to marshall and convert it into a Correct Statement
     * which can be returned. Spring refuses to use the generic statement as the
     * marshaller, so a new class had to be created to be able to do this.
     *
     * @param response the response
     * @return the results
     */
    private Map<Statement, Double> getResults(XFResponse response) {

        Map<Statement, Double> results = new HashMap<>();

        for (Category cat : response.getResponse().getQuestionnaire().getCategory()) {
            for (eu.operando.core.cpcu.servlet.springwrappers.Statement st : cat.getStatements()) {
                results.put(createStatement(st, response.getResponse().getQuestionnaire().getType(), cat), st.getRating().doubleValue());
            }
        }
        return results;
    }

    //If new Statements are added, they also need to be added to this, as it works out what type of statement the questionnaire takes and returns that type.
    //Takes in the SpringWrapper statement, (as spring woudn't inject the values into the normal statement......) and is then converted into correct statement
    private Statement createStatement(eu.operando.core.cpcu.servlet.springwrappers.Statement st, String type, Category cat) {
        switch (Integer.parseInt(type)) {
            case 2:
                return new ServiceStatement(st.getStatementString(), cat.getTitle(), st.getPrivacyRanking(), new DataHolder(st.getRole(), st.getRoleRank()));
            case 3:
                return new ActionStatement(st.getStatementString(), cat.getTitle(), st.getPrivacyRanking(), new DataHolder(st.getRole(), st.getRoleRank()),
                        new DataHolder(st.getAction(), st.getActionRank()));
            default:
                return new Statement(st.getStatementString(), st.getWeight(), cat.getTitle(), st.getPrivacyRanking());
        }

    }
}
