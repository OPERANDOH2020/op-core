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
package eu.operando.core.cpcu.questionnaires;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import eu.operando.core.cpcu.main.AbstractQuestionnaire;
import eu.operando.core.cpcu.main.DataHolder;
import eu.operando.core.cpcu.processing.SlidingDoor;

/**
 * The Class DefaultQuestionnaire.
 */
public class DefaultQuestionnaire extends AbstractQuestionnaire {

	/**
	 * Instantiates a new default questionnaire.
	 *
	 * @param type The type of the Questionnaire. This is used as the name of the Questionniare
	 * @param index This identfies which questionnaire this questionnaire is dependant on (using internal ID).n
	 * @param processor The processor used as the I/O for the Questionnaire.
	 * @param meta the meta
	 */
	public DefaultQuestionnaire(String type, Integer index, DefaultDataProcessor processor, String meta) {
		super(type, index, processor, meta);
	}

	/* (non-Javadoc)
	 * @see com.initialDevelopment.CPCU.Questionnaire#getStatements(java.lang.Double, java.lang.String, int)
	 */
	@Override
	public String getStatements(Double noOfQuestions, int largestRatingValue, Map<String,Double> preferences) throws Exception {
		Map<DataHolder, List<Statement>> questionPool = getDataProcessor()
				.retrieveQuestionPool();

		if("Privacy".equalsIgnoreCase(getType())){
				questionPool = createPrivacyStatementsPipeline(questionPool,
						preferences, noOfQuestions, largestRatingValue);
		}

		return getDataProcessor().writeStatementsToJSONDataHolder(questionPool, getMetadata(), getType());
	}

	/**
	 * select questionnaire will create a new questionnaire.
	 *
	 * @param mappedQuestions the mapped questions
	 * @param results the results obtained from the last Questionnaire
	 * @param noOfQuestions the no of questions in each String
	 * @param largestRatingValue the largest rating value used in the questionnaire
	 * @return The Statement map containing the the questionnaire
	 */
	private Map<DataHolder, List<Statement>> createPrivacyStatementsPipeline(Map<DataHolder, List<Statement>> mappedQuestions, 
			Map<String,Double> results, Double noOfQuestions, Integer largestRatingValue){
		return SlidingDoor.selectStatements(mappedQuestions, results , noOfQuestions, largestRatingValue);
	}
	
	
	/**
	 * Collect and weight results.
	 *
	 * @param questionnaire the questionnaire
	 * @return the map
	 */
	public Map<DataHolder, Double> collectAndWeightResults(Map<Statement, Double> questionnaire){
		Map<DataHolder, Double> results = new HashMap<>();
		Map<DataHolder, Double> weightSum = new HashMap<>();

		questionnaire.keySet().forEach(q -> {
			weightSum.put(new DataHolder(q.getCategory(), q.getPrivacyRanking()), q.getWeight()+lookupCategory(weightSum, 
					new DataHolder(q.getCategory(), q.getPrivacyRanking())));
			results.put(new DataHolder(q.getCategory(), q.getPrivacyRanking()), (questionnaire.get(q)*q.getWeight()) + lookupCategory(results, 
					new DataHolder(q.getCategory(), q.getPrivacyRanking())));
		});

		results.keySet().forEach(key -> {
			Double lookup = lookupCategory(weightSum, key);
			if(lookup==0.0) 
				results.put(key, lookupCategory(results, key));
			else 
				results.put(key, lookupCategory(results, key)/lookup);
		});

		return results;
	}

	/**
	 * Lookup String, will return the value contained in a specific key for a specific map.
	 *
	 * @param questionnaire the questionnaire Map to search
	 * @param string the key to look for
	 * @return The value contained in the key
	 */
	public static Double lookupCategory(Map<DataHolder, Double> questionnaire, DataHolder string){
		if(questionnaire.get(string)==null)
			return 0.0;
		return questionnaire.get(string);
	}
	
	/* (non-Javadoc)
	 * @see com.initialDevelopment.CPCU.Questionnaire#getDataProcessor()
	 */
	@Override
	public DefaultDataProcessor getDataProcessor(){
		return processor;
	}

	/* (non-Javadoc)
	 * @see eu.operando.core.cpcu.main.Questionnaire#processResults(java.util.Map, int, int)
	 */
	@Override
	public Map<DataHolder, Double> processResults(Map<Statement, Double> results, int maxStatements, int largestRatingValue) {
		return collectAndWeightResults(results);
		
	}

}
