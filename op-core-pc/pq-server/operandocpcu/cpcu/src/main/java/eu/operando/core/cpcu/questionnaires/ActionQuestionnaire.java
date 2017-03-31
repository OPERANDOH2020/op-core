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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import eu.operando.core.cpcu.main.AbstractQuestionnaire;
import eu.operando.core.cpcu.main.DataHolder;

/**
 * The Class ActionQuestionnaire. This Questionnaire is similar to Service, but includes actions (read from a file).
 * This was going to be used for testing users and the statements, however the tests were never done.
 * It takes in the actions and concatenates them with the statements created in the Service Questionnaire and outputs
 * ALL of them to the Statements Engine.
 */
public class ActionQuestionnaire extends AbstractQuestionnaire {

	/**
	 * Instantiates a new action questionnaire.
	 *
	 * @param type The type of the Questionnaire. This is used as the name of the Questionniare
	 * @param dependantOn This identfies which questionnaire this questionnaire is dependant on (using internal ID).n
	 * @param dataProcessor The processor used as the I/O for the Questionnaire.
	 * @param metadata The metadata for the questionnaire.
	 */
	public ActionQuestionnaire(String type,Integer dependantOn, DefaultDataProcessor dataProcessor, String metadata) {
		super(type, dependantOn, dataProcessor, metadata);
	}

	/* (non-Javadoc)
	 * @see com.initialDevelopment.CPCU.Questionnaire#getStatements(java.lang.Double, java.lang.String, int)
	 */
	@Override
	public String getStatements(Double noOfQuestions, int largestRatingValue, Map<String,Double> preferences) throws Exception {

		List<DataHolder> data = getDataProcessor().retrieveData().stream()
				.sorted((data1, data2) -> data1.getRank().compareTo(data2.getRank()))
				.collect(Collectors.toList());

		List<DataHolder> roles = getDataProcessor().retriveRoles().stream()
				.sorted((data1, data2) -> data1.getRank().compareTo(data2.getRank()))
				.collect(Collectors.toList());

		List<DataHolder> actions = getDataProcessor().retrieveActions().stream()
				.sorted((data1, data2) -> data1.getRank().compareTo(data2.getRank()))
				.collect(Collectors.toList()); 

		preferences.get(getDataProcessor().getPreferenceCategory());

		List<ActionStatement> statements = createActionsStatementPipeline(roles, data, actions);

		Map<String, List<Statement>> questionnaire = new HashMap<>();

		for(ActionStatement st: statements){
			if(!questionnaire.keySet().contains(st.getCategory()))
				questionnaire.put(st.getCategory(), new ArrayList<Statement>());
			questionnaire.get(st.getCategory()).add(st);
		}

		return getDataProcessor().writeStatementsToJSON(questionnaire, getMetadata(), getType());

	}

	/**
	 * Creates the actions statement pipeline. This is used in the processing of the data to call the functions that alter the data. In this case, the statements
	 * just need to be built.
	 *
	 * @param roles the list of roles
	 * @param datas the list of data
	 * @param actions list of the actions
	 * @return the list of Action Statements
	 */
	private List<ActionStatement> createActionsStatementPipeline(List<DataHolder> roles, List<DataHolder> datas, List<DataHolder> actions){

		List<ActionStatement> statements = new ArrayList<>();

		for(DataHolder role: roles){
			for(DataHolder data: datas){
				for(DataHolder action: actions){
					statements.add((ActionStatement) new ActionStatement(role.getData() + " can " + action.getData() + " " + data.getData() + " data", data.getData()
							, data.getRank(),role, action).setMetadata(getDataProcessor().createMetadata(role, data, action)));
				}
			}
		}
		return statements;
	}


	/* (non-Javadoc)
	 * @see com.initialDevelopment.CPCU.Questionnaire#getDataProcessor()
	 */
	@Override
	public ActionDataProcessor getDataProcessor(){
		return (ActionDataProcessor) processor;
	}

	/* (non-Javadoc)
	 * @see eu.operando.core.cpcu.main.Questionnaire#processResults(java.util.Map, int, int)
	 */
	@Override
	public Map<DataHolder, Double> processResults(Map<Statement, Double> results, int maxStatements, int largestRatingValue) {

		Map<DataHolder, Double> toReturn = new HashMap<>();

		for(Statement key: results.keySet()){
			toReturn.put(new DataHolder(key.getStatementString(), 0), results.get(key));
		}
		return toReturn;	

	}
}
