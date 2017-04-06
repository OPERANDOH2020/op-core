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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import eu.operando.core.cpcu.main.DataHolder;
import eu.operando.core.cpcu.main.QuestionnaireHandler;
import eu.operando.core.cpcu.questionnaires.ServiceStatement;
import eu.operando.core.cpcu.questionnaires.Statement;

/**
 * The Class Results. This class was intended to be used in the testing phase to process the results to try to notice trends
 * within the results collected. It uses averages and correlational data to output a value.
 * Never tested.
 */
public class Results {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new Results().getResultsComparison(2, 3, 3);
	}

	/**
	 * Gets the results comparison.
	 *
	 * @param index1 the index 1
	 * @param index2 the index 2
	 * @param datSize the dat size
	 * @return the results comparison
	 */
	public void getResultsComparison(Integer index1, Integer index2, Integer datSize){

		File f = new File(QuestionnaireHandler.FILE_LOC+"/users");

		for(File userFile: f.listFiles()){

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

			Document doc2 = null;
			Document doc1 = null;
			try {
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				doc1 = dBuilder.parse(new File(userFile.getAbsolutePath()+"/res"+index1+".xml"));
				doc2 = dBuilder.parse(new File(userFile.getAbsolutePath()+"/res"+index2+".xml"));
			} catch (SAXException | IOException e) {
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
//
//			Map<DataHolder, Double> preferences = processPreferences(QuestionnaireHandler.getObjectFromJSON(index1).getDataProcessor()
//					.retrieveResults(QuestionnaireHandler.getInstance().getObjectFromJSON(index1).getDataProcessor().
//							xmlToString(doc1.getDocumentElement()), false));
//
//			Map<DataHolder, Double> preferences1 = processPreferences(QuestionnaireHandler.getObjectFromJSON(index2).getDataProcessor()
//					.retrieveResults(QuestionnaireHandler.getInstance().getObjectFromJSON(index2).getDataProcessor().
//							xmlToString(doc2.getDocumentElement()), false));
//			preferences1.replaceAll((k, v) -> v/datSize);
//			
//			preferences = preferences.entrySet().stream().sorted((e1, e2) -> e1.getKey().getRank()
//					.compareTo(e2.getKey().getRank())).collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
//			
//			preferences1 = preferences1.entrySet().stream().sorted((e1, e2) -> e1.getKey().getRank()
//					.compareTo(e2.getKey().getRank())).collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
//			
//			System.out.println("----");
//			for(DataHolder key: preferences.keySet())
//				System.out.println(key + " " + preferences.get(key));
//			System.out.println();
//			for(DataHolder key: preferences1.keySet())
//				System.out.println(key + " " + preferences1.get(key));
//			System.out.println("-----");
//			List<String> data = new ArrayList<>();
//			
//			for(DataHolder pref: preferences.keySet()){
//				String s = pref.getData().split("/")[0];
//				if(!data.contains(s))
//					data.add(s);
//			}
//			createCorrelation(data, preferences, preferences1);
//			createDifference(data, preferences, preferences1);
		}
	}
	
	/**
	 * Creates the difference.
	 *
	 * @param data the data
	 * @param preferences the preferences
	 * @param preferences1 the preferences 1
	 */
	private void createDifference(List<String> data, Map<DataHolder, Double> preferences, Map<DataHolder, Double> preferences1){
		for(String d: data){
			for(DataHolder s: preferences.keySet()){
				if(s.getData().contains(d))
					for(DataHolder s1: preferences1.keySet()){
						if(s1.getData().contains(d) && s.getRank().equals(s1.getRank()))
							System.out.println(s.getData() + " difference is " + (preferences.get(s)-preferences1.get(s1)));
					}
			}
		}
	}
	
	/**
	 * Creates the correlation.
	 *
	 * @param data the data
	 * @param preferences the preferences
	 * @param preferences1 the preferences 1
	 */
	private void createCorrelation(List<String> data, Map<DataHolder, Double> preferences, Map<DataHolder, Double> preferences1){
		for(String d: data){

			double[] x = new double[preferences.size()];	
			int i = 0;
			for(DataHolder s: preferences.keySet())
				if(s.getData().contains(d)){
					x[i] = preferences.get(s);
					i++;
				}

			double[] y = new double[preferences1.size()];
			i = 0;
			for(DataHolder s: preferences.keySet())
				if(s.getData().contains(d)){
					y[i] = preferences1.get(s);
					i++;
				}

			double corr = new PearsonsCorrelation().correlation(y, x);
			System.out.println(d + "= " + corr);
		}
	}

	/**
	 * Process preferences.
	 *
	 * @param pref the pref
	 * @return the map
	 */
	public Map<DataHolder, Double> processPreferences(Map<Statement, Double> pref){

		Map<DataHolder, Double> toReturn = new HashMap<>(); 

		for(Statement st: pref.keySet()){
			ServiceStatement ast = (ServiceStatement) st;
			DataHolder input = new DataHolder(ast.getCategory()+"/"+ast.getRole(), ast.getRoleRank());
			if(!containsDataKey(toReturn, input))
				toReturn.put(input, 0.0);
			toReturn.put(input, toReturn.get(input)+pref.get(st));
		}
		return toReturn;
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
			if(key.getData().equals(d.getData()))
				return true;
		return false;
	}
}
