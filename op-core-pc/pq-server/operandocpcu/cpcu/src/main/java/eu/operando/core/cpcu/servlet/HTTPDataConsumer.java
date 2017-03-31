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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import eu.operando.core.cpcu.servlet.springwrappers.OSPPP;
import eu.operando.core.cpcu.servlet.springwrappers.SubscribedOspSetting;
import eu.operando.core.cpcu.servlet.springwrappers.UPP;
import eu.operando.core.cpcu.servlet.springwrappers.UserPreference;

/**
 * The Class HTTPDataConsumer.
 */
public class HTTPDataConsumer {
	
	/**
	 * Access HTTP endpoint using GET.
	 *
	 * @param <T> the generic type
	 * @param url the url
	 * @param c the c
	 * @return the t
	 */
	private <T> T accessHTTP(String url, Class<T> c){
		RestTemplate restTemplate = restTemplate(new RestTemplateBuilder());
		return restTemplate.getForObject(url, c);
	}
	
	/**
	 * Post to HTTP endpoint.
	 *
	 * @param <T> the generic type
	 * @param data the data
	 * @param url the url
	 * @param c the c
	 */
	private <T> void postHTTP(Object data, String url, Class<T> c){
		RestTemplate restTemplate = restTemplate(new RestTemplateBuilder());
		restTemplate.postForEntity(url, data, c);
	}
	
	/**
	 * Rest template.
	 *
	 * @param builder the builder
	 * @return the rest template
	 */
	private RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	/**
	 * Consume preference. Gets the preferences from the endpoint
	 * TODO:
	 *
	 * @param username the username
	 * @return the list of preferences
	 */
	public List<UserPreference> consumePreference(String username){
		UPP upp = accessHTTP(username, UPP.class);
		return upp.getUserPrivacyPolicy().getUserPreferences();
	}
	
	/**
	 * Posts preferences to the endpoint.
	 * 
	 *
	 * @param username the username
	 * @param results the new preferences returned from the Application
	 */
	public void postPreferences(String username, Map<String,Double> results){
		//TODO: depends on your implementation!
	}
	
	/**
	 * Gets the subscribed OSP.
	 *
	 * @param username the username
	 * @return the subscribed OSP
	 */
	private List<Integer> getSubscribedOSP(String username){
		UPP upp = accessHTTP(username, UPP.class);
		List<Integer> returnArray = new ArrayList<>();
		
		for(SubscribedOspSetting setting: upp.getUserPrivacyPolicy().getSubscribedOspSettings())
			returnArray.add(Integer.parseInt(setting.getOspId()));
		
		return returnArray;
	}
	
	/**
	 * Validate OSPID.
	 *
	 * @param id the id
	 * @param username the username
	 * @return true, if successful
	 */
	public boolean validateOSPID(int id, String username){
		return getSubscribedOSP(username).contains(id);
	}
	
	/**
	 * Gets the OSP data.
	 * TODO:
	 *
	 * @param id the id
	 * @return the OSP data
	 */
	//Get roles and data - (partly not) implemented into the API, so I wont write the method for it.
	public void getOSPData(int id){
		OSPPP osp = accessHTTP(id+"", OSPPP.class);
		//...
	}
}