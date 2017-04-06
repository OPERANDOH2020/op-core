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
package eu.operando.core.cpcu.servlet.configurations;

import org.json.simple.JSONObject;

import eu.operando.core.cpcu.servlet.configurations.Configuration;

/**
 * A factory for creating Configuration objects.
 */
public class ConfigurationFactory {

	
	/**
	 * Convert and create. 
	 * Cannot remove Unchecked Type cast, despite the fact T must be a superset of Configuration. So Casts are safe.
	 *
	 * @param <T> the generic type
	 * @param obj the obj
	 * @param ident the ident
	 * @return the t
	 */
	public <T extends Configuration> T convertAndCreate(JSONObject obj, String ident){
		switch(ident){
		case "qn":
			return handle(obj, (T) new QuestionnaireConfiguration());
		case "qs":
			return handle(obj, (T) new QuestionConfiguration());
		case "se":
			return handle(obj, (T) new ServiceConfiguration());
		}
		return null;
	}
	
	/**
	 * Converts from JSONObject to Configuration type T.
	 *
	 * @param <T> the generic type
	 * @param obj the JSONObject
	 * @param config the Configuration Type
	 * @return the t
	 */
	private <T extends Configuration> T handle(JSONObject obj, T config){
		config.convert(obj);
		return config;
	}
	
}
