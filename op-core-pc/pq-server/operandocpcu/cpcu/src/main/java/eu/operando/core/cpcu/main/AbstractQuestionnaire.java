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

import java.util.Map;

import eu.operando.core.cpcu.questionnaires.DefaultDataProcessor;
import eu.operando.core.cpcu.questionnaires.Statement;

/**
 * The Class Questionnaire. Defines the base for a Questionnaire within the system. All classLoader classes (defined in Questionnaires.json) must extend this class.
 */
public abstract class AbstractQuestionnaire {

	/** The type of the Questionnaire. This is used as the name of the Questionniare. */
	private String type;
	
	/** This identfies which questionnaire this questionnaire is dependant on (using internal ID). */
	private Integer dependantOn;
	
	/** The metadata for the questionnaire. */
	private String metadata;
	
	/** The processor used as the I/O for the Questionnaire. */
	public DefaultDataProcessor processor;
	
	/**
	 * Instantiates a new questionnaire.
	 *
	 * @param type The type of the Questionnaire. This is used as the name of the Questionniare
	 * @param dependantOn This identfies which questionnaire this questionnaire is dependant on (using internal ID).n
	 * @param processor The processor used as the I/O for the Questionnaire.
	 * @param metadata The metadata for the questionnaire.
	 */
	public AbstractQuestionnaire(String type, Integer dependantOn, DefaultDataProcessor processor, String metadata){
		this.type = type;
		this.dependantOn = dependantOn;
		this.metadata = metadata;
		this.processor = processor;
	}
	
	/**
	 * Gets the statements to be used by the XForms Engine. There is several Stages of pre-processing in the later 
	 * Questionnaires in order to generate useful Statements.
	 *
	 * @param noOfQuestions the no of questions
	 * @param largestRatingValue the largest rating value of the questionnaire (used in questionnaire processing)
	 * @return the statements to be used by the XForms Engine.
	 * @throws Exception the exception
	 */
	public abstract String getStatements(Double noOfQuestions, int largestRatingValue, Map<String,Double> preferences) throws Exception;
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Gets the dependancies of the Questionnaire
	 *
	 * @return the dependancy
	 */
	public Integer getDependancy(){
		return dependantOn;
	}
	
	/**
	 * Gets the metadata of the Questionnaire
	 *
	 * @return the metadata
	 */
	public String getMetadata(){
		return metadata;
	}

	/**
	 * Gets the data processor. Abstract so custom Data processors can be implemented
	 *
	 * @return the data processor
	 */
	public abstract DefaultDataProcessor getDataProcessor();

	/**
	 * Process results to Return the Preference values to be stored. This allows the Questionnaires to calculate the preferences 
	 * differently according to the specification of the class. The Default Questionnaire only calculates a weighted average 
	 *
	 * @param results the results, as a Map between Statement and result
	 * @param maxStatements the max statements
	 * @param largestRatingValue the largest rating value within the Questionnaire
	 */
	public abstract Map<DataHolder, Double> processResults(Map<Statement, Double> results, int maxStatements, int largestRatingValue);
	
}
