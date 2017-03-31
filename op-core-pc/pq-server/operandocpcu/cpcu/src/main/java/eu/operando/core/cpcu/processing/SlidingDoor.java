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
package eu.operando.core.cpcu.processing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Set;

import eu.operando.core.cpcu.main.DataHolder;
import eu.operando.core.cpcu.questionnaires.Statement;


/**
 * The Class SlidingDoor.
 */
public class SlidingDoor {

	/**
	 * Builds the sliding door policy to select the questions from the pool of questions.
	 *
	 * @param mappedQuestions the mapping containing all the questions in the Statement pool
	 * @param results the results obtained in the previous questionnaire
	 * @param noOfQuestions the no of questions in each String
	 * @param largestRatingValue the largest rating value used in the questionnaire
	 * @return the Statement map containing the questions to be displayed
	 */
	public static Map<DataHolder, List<Statement>> selectStatements(Map<DataHolder, List<Statement>> mappedQuestions, 
			Map<String,Double> results, Double noOfQuestions, Integer largestRatingValue){

		mappedQuestions.keySet()
		.forEach(key -> Collections.sort(mappedQuestions.get(key)));
		Map<DataHolder,List<Statement>> selectedQuestions = new HashMap<>();

		for(Entry<String, Double> key: results.entrySet()){
			DataHolder datakey = new DataHolder(key.getKey(), 1);
			if(!selectedQuestions.containsKey(key.getKey()))
				selectedQuestions.put(new DataHolder(key.getKey(), 0), new ArrayList<Statement>());
			
			if(doesContain(mappedQuestions.keySet(), key.getKey())){
				
				Double startpos = Math.ceil(mappedQuestions.get(datakey).size()*(key.getValue()/largestRatingValue));
				//calculates the distances either side of the starting position, which should be in the middle of the door.
				
				Double dir1 = Math.ceil(noOfQuestions/2);	//distance to search above the startpos (median)
				Double dir2 = Math.floor(noOfQuestions/2);	//distance to search below the startpos (median)
				
				//This calculates which of the distances is larger and will switch them depending on the result obtained previously.
				if(key.getValue() < 0.5 && dir1 != dir2){
					if(dir1 < dir2)
						swapDoubles(dir1, dir2);
				} else if(key.getValue() > 0.5 && dir1 != dir2) {
					if(dir2 < dir1)
						swapDoubles(dir1, dir2);
				}

				Integer questionNo = noOfQuestions.intValue();	
				//Look to see if the door goes off either end of the list, and if it does then adjusts the other direction value.
				if(startpos+dir2 > mappedQuestions.get(datakey).size())
					dir1 += (startpos+dir2)-mappedQuestions.get(datakey).size();
				if(startpos - dir1 < 0)
					dir2 -= (startpos - dir1);

				//Gets the questions from the lists, will iterate over the distance compared to the starting position to get the questions.
				for(Double i = startpos-1; i >= 0  && questionNo != 0; i--){
					addIndexToQuestions(mappedQuestions, selectedQuestions, questionNo, datakey, i);
					questionNo--;
				}

				for(Double i = startpos; i < mappedQuestions.get(datakey).size() && questionNo != 0; i++){
					addIndexToQuestions(mappedQuestions, selectedQuestions, questionNo, datakey, i);
					questionNo--;
				}
			}
		}
		return selectedQuestions;
	}

	/**
	 * Does contain.
	 *
	 * @param set the set
	 * @param data the data
	 * @return true, if successful
	 */
	private static boolean doesContain(Set<DataHolder> set, String data){
		for(DataHolder d: set){
			if(d.getData().equals(data))
				return true;
		}
		return false;
	}
	
	/**
	 * Swap two variables.
	 *
	 * @param dir1 the first variable
	 * @param dir2 the second variable
	 */
	private static void swapDoubles(Double dir1, Double dir2){
		dir1 = dir1+dir2;
		dir2 = dir1-dir2;
		dir1 = dir1-dir2;
	}

	/**
	 * gets the index specified for a specific key's Statement list and adds it to the the Map for selected Questions.
	 *
	 * @param mappedQuestions the mapping containing all the questions in the Statement pool
	 * @param selectedQuestions the currently selected questions to be displayed to the user
	 * @param questionNo the number of questions for each String
	 * @param key the current key being searched
	 * @param index the the index within the key's list of questions.
	 */
	private static synchronized void addIndexToQuestions(Map<DataHolder, List<Statement>> mappedQuestions, 
			Map<DataHolder, List<Statement>> selectedQuestions, Integer questionNo, 
			DataHolder key, Double index){

		List<Statement> qu = selectedQuestions.get(key);
		qu.add(mappedQuestions.get(key).get(index.intValue()));
		questionNo--;
	}
}
