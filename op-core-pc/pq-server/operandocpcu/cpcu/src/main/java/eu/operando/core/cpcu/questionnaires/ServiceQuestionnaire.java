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
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import eu.operando.core.cpcu.main.AbstractQuestionnaire;
import eu.operando.core.cpcu.main.DataHolder;
import eu.operando.core.cpcu.main.QuestionnaireHandler;
import eu.operando.core.cpcu.processing.SlidingDoor;

/**
 * The Class ServiceQuestionnaire.
 */
public class ServiceQuestionnaire extends AbstractQuestionnaire{
	
	/**
	 * Instantiates a new Service questionnaire.
	 *
	 * @param type The type of the Questionnaire. This is used as the name of the Questionniare
	 * @param index is the internal ID of the questionnaire
	 * @param dependantOn This identfies which questionnaire this questionnaire is dependant on (using internal ID).n
	 * @param processor The processor used as the I/O for the Questionnaire.
	 * @param metadata The metadata for the questionnaire.
	 */
	public ServiceQuestionnaire(String type, Integer index, DefaultDataProcessor processor, String meta) {
		super(type, index, processor, meta);
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

				List<ServiceStatement> statements = createServiceStatementPipeline(roles, data, noOfQuestions.intValue()
						, preferences.get(getDataProcessor().getPreferenceCategory()));

				Map<String, List<Statement>> questionnaire = new HashMap<>();

				for(ServiceStatement st: statements){
					if(!questionnaire.keySet().contains(st.getCategory()))
						questionnaire.put(st.getCategory(), new ArrayList<Statement>());
					questionnaire.get(st.getCategory()).add(st);
				}

				return getDataProcessor().writeStatementsToJSON(questionnaire, getMetadata(), getType());
	}
	
	/**
	 * Creates the ServiceStatement pipeline, which will initialise the methods within the pipeline in turn.
	 *
	 * @param roles the List of roles
	 * @param data the List of data
	 * @param maxStatements the max statements allowed in the questionnaire
	 * @param privacyResult the privacy result obtained from the preferences file
	 * @return the List of ServiceStatements to be displayed
	 */
	public List<ServiceStatement> createServiceStatementPipeline(List<DataHolder> roles, List<DataHolder> data, Integer maxStatements, Double privacyResult){
		//Stored by Map<Data, List<Role>>
		Map<DataHolder, List<DataHolder>> toRemove = new HashMap<>();

		for(DataHolder r: data)
			toRemove.put(r,	new ArrayList<DataHolder>());

		//Pipeline editors
		Double d = calculateRoleReduction(data, roles, maxStatements);

		reduceRoles(toRemove, data, roles, privacyResult, d);

		provideFinalReduction(toRemove, data, roles, d, maxStatements, privacyResult);

		List<ServiceStatement> statements = new ArrayList<>(); 

		//Generate the statements from the data, using the toRemove map as guidelines on what not to generate
		data.forEach(str -> 
			roles.forEach(role -> {
				if(!toRemove.containsKey(str))
					statements.add((ServiceStatement) new ServiceStatement(role + " can access " + str + " data", str.getData(), str.getRank(), role)
							.setMetadata(getDataProcessor().createMetadata(role, str)));
				else {
					if(!toRemove.get(str).contains(role))
						statements.add((ServiceStatement) new ServiceStatement(role + " can access " + str + " data", str.getData(), str.getRank(), role)
								.setMetadata(getDataProcessor().createMetadata(role, str)));
				}
			})
		);
		return statements;
	}

	/**
	 * Calculates how the role data set can be reduced to create a more user friendly questionnaire.
	 *
	 * @param toRemove the Map containing the data/role combinations to remove
	 * @param data the List of data
	 * @param roles the List of roles
	 * @param maxStatements the max statements allowed in the questionnaire
	 * @return the number of roles that can be taken out.
	 */
	private Double calculateRoleReduction(List<DataHolder> data, 
			List<DataHolder> roles, Integer maxStatements){
		List<DataHolder> currentRoles = new ArrayList<>(roles);
		Double noToRemove = 0.0;
		while(currentRoles.size()*data.size() >= maxStatements){
			Integer extractWindowSize = (int) Math.ceil(currentRoles.size()/4.0);
			Integer startFrom = (int) Math.ceil((currentRoles.size()/2.0) - (extractWindowSize/2.0));

			if(currentRoles.size()==2 || (currentRoles.size()*data.size())-data.size() < maxStatements)
				break;
			for(int i = startFrom; i < startFrom+ extractWindowSize; i++){
				//toRemove.putForAllCategories(currentRoles.get(i))
				noToRemove++;
				currentRoles.remove(currentRoles.get(i));
			}
		}
		return noToRemove;
	}

	/**
	 * Reduces the roles by adding them to toRemove Map. Uses the sliding policy to decide which statements to remove.
	 *
	 * @param toRemove the Map containing the data/role combinations to remove
	 * @param data the List of data
	 * @param roles the List of roles
	 * @param reduceValue the value to reduce the roles by
	 * @param privacyResult the privacy result obtained from the preferences file
	 * @param noToRemove the number of roles to remove
	 */
	private void reduceRoles(Map<DataHolder, List<DataHolder>> toRemove, 
			List<DataHolder> data, List<DataHolder> roles, Double privacyResult, Double noToRemove){

		Map<DataHolder, List<Statement>> statements = new HashMap<>();
		for(DataHolder dataCategories: data){
			statements.put(dataCategories, new ArrayList<Statement>());
			for(DataHolder role: roles)
				statements.get(dataCategories).add(new ServiceStatement(role + " can access " + dataCategories + " data"
						, dataCategories.getData(), role.getRank(), role));
		}

		this.useSlidingDoor(statements, data, noToRemove, toRemove, privacyResult);
	}

	/**
	 * Uses the sliding door to filter the statements passed in statements by the privacyRanking obtained previously.
	 *
	 * @param statements the Map of statements to input into the SlidingDoor
	 * @param data the List of data
	 * @param noToRemove the number of roles to remove
	 * @param toRemove the Map containing the data/role combinations to remove
	 * @param privacyResult the privacy result obtained from the preferences filed
	 */
	private void useSlidingDoor(Map<DataHolder, List<Statement>> statements, List<DataHolder> data, 
			Double noToRemove, Map<DataHolder, List<DataHolder>> toRemove, Double privacyResult){
		//Adds the result for the PrivacyCategory (pulled from preferences) to all of the ServiceCategories, so when its inputted into the 
		//sliding door policy only the PrivacyCategory is used for every ServiceCategory.
		Map<String, Double> result = new HashMap<>();
		for(DataHolder d: data)
			result.put(d.getData(), privacyResult);
		
		Map<DataHolder, List<Statement>>  returnedStatements = SlidingDoor.selectStatements(statements, result, noToRemove , 5);
		
		for(DataHolder returnedData: returnedStatements.keySet()){
			toRemove.put(returnedData, new ArrayList<DataHolder>());
			for(Statement st: returnedStatements.get(returnedData)){
				ServiceStatement tst =  (ServiceStatement) st;
				toRemove.get(returnedData).add(new DataHolder(tst.getRole(), tst.getRoleRank()));
			}
		}
	}

	/**
	 * Provide final reduction on toRemove, making sure that the no of questions is close to maxStatements as possible.
	 *
	 * @param toRemove the Map containing the data/role combinations to remove
	 * @param data the List of data
	 * @param roles the List of roles
	 * @param noToRemove the number of roles to remove
	 * @param maxStatements the max statements allowed in a single questionnaire
	 * @param privacyResult the privacy result obtained from the preferences filed
	 */
	private void provideFinalReduction(Map<DataHolder, List<DataHolder>> toRemove, 
			List<DataHolder> data, List<DataHolder> roles, Double noToRemove, Integer maxStatements, Double privacyResult){
		
		Map<DataHolder, List<Statement>> statements = new HashMap<>();
		int i = 0;
		
		while((roles.size()-noToRemove)*data.size()-i>maxStatements && !checkForTwoPoints(toRemove, roles)){
			DataHolder d = data.get(data.size()-1-i);
			statements.put(d, new ArrayList<Statement>());
			for(DataHolder r: roles){
				if(!toRemove.get(d).contains(r)){
					statements.get(d).add(new ServiceStatement(r + " can access " + d + " data"
							, d.getData(), r.getRank(), r));
				}
			}
			i++;
		}
		if(statements.size()!=0)
			this.useSlidingDoor(statements, data, 1.0, toRemove, privacyResult);
		
	}

	/**
	 * Checks to make sure there is two data points in each data category in toRemove, if there is only two in each category then
	 * provideFinalRedictions cannot take place.
	 *
	 * @param toRemove the Map containing the data/role combinations to remove
	 * @param roles the List of roles
	 * @return true, if every category has exactly two results otherwise false
	 */
	private boolean checkForTwoPoints(Map<DataHolder, List<DataHolder>> toRemove, List<DataHolder> roles){
		for(DataHolder key : toRemove.keySet())
			if(roles.size() - toRemove.get(key).size() !=2)
				return false;
		return true;
	}
	

	/**
	 * Predict total results.
	 *
	 * @param results the results
	 * @param allRoles the all roles
	 * @param allData the all data
	 * @return the map
	 */
	public Map<String, Map<DataHolder, Double>> predictTotalResults(Map<Statement, Double> results, 
			List<DataHolder> allRoles, List<DataHolder> allData){

		Map<String, Map<DataHolder, Double>> newResults = new HashMap<>();

		
		allData.stream().forEach(data -> 
			newResults.put(data.getData(), new HashMap<DataHolder, Double>()));
		
		results.keySet().forEach(st -> {
			ServiceStatement statement = (ServiceStatement) st; 
			newResults.get(statement.getCategory()).put(
						new DataHolder(statement.getRole(), statement.getRoleRank()), results.get(statement));
		});

		Map<DataHolder, SimpleRegression> linearEquations = new HashMap<>();
		for(int i = 0; i < allData.size(); i++){
			Map<DataHolder, Double> currentRoles = newResults.get(allData.get(i).getData());
			SimpleRegression regression = new SimpleRegression();
			for(DataHolder data: currentRoles.keySet())
				regression.addData(data.getRank(), currentRoles.get(data));
			
			linearEquations.put(allData.get(i), regression);
		}
		
		allData.forEach(data -> 
			allRoles.forEach(role -> {
				if(!containsDataKey(newResults.get(data.getData()), role)){
					SimpleRegression equ = linearEquations.get(data);
					Double predictedResult = equ.predict(role.getRank());
					if(predictedResult < 0.0)
						predictedResult = 0.0;
					else if(predictedResult > 5.0)
						predictedResult = 5.0;
					newResults.get(data.getData()).put(role, predictedResult);
				}
			})
		);
		return newResults;
	}
	
	/**
	 * Contains data key.
	 *
	 * @param map the map
	 * @param d the d
	 * @return true, if successful
	 */
	private boolean containsDataKey(Map<DataHolder, Double> map, DataHolder d){
		for(DataHolder key: map.keySet())
			if(key.equals(d))
				return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see com.initialDevelopment.CPCU.Questionnaire#getDataProcessor()
	 */
	@Override
	public ServiceDataProcessor getDataProcessor() {
		return (ServiceDataProcessor) processor;
	}

	@Override
	public Map<DataHolder, Double> processResults(Map<Statement, Double> results, int maxStatements, int largestRatingValue) {
		
		Map<String, Map<DataHolder, Double>> finalResults = predictTotalResults(results, 
				getDataProcessor().retriveRoles(), getDataProcessor().retrieveData());
		
		//calculate results
		Map<DataHolder, Double> toReturn = new HashMap<>();
		
		for(Entry<String, Map<DataHolder, Double>> key: finalResults.entrySet()){
			for(DataHolder holder: key.getValue().keySet()){
				toReturn.put(new DataHolder(key.getKey()+">"+holder, 0), key.getValue().get(holder));
			}
		}
		return toReturn;
		
	}
	
}