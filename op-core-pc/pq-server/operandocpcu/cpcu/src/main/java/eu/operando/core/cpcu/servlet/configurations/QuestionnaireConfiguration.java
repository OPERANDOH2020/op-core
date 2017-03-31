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

/**
 * The Class QuestionnaireConfiguration.
 */
public class QuestionnaireConfiguration extends Configuration {

	/** The id. */
	public int id;
	
	/** The type. */
	public String type;
	
	/** The dependancies. */
	public int dependancies;
	
	/** The processor. */
	public String processor;
	
	/** The class loader. */
	public String classLoader;
	
	/** The metadata. */
	public String metadata;
	
	public boolean generatedQuestions;

	public boolean getGeneratedQuestions() {
		return generatedQuestions;
	}

	public void setGeneratedQuestions(boolean generatedQuestions) {
		this.generatedQuestions = generatedQuestions;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the dependancies.
	 *
	 * @return the dependancies
	 */
	public int getDependancies() {
		return dependancies;
	}

	/**
	 * Sets the dependancies.
	 *
	 * @param dependancies the new dependancies
	 */
	public void setDependancies(int dependancies) {
		this.dependancies = dependancies;
	}

	/**
	 * Gets the processor.
	 *
	 * @return the processor
	 */
	public String getProcessor() {
		return processor;
	}

	/**
	 * Sets the processor.
	 *
	 * @param processor the new processor
	 */
	public void setProcessor(String processor) {
		this.processor = processor;
	}

	/**
	 * Gets the class loader.
	 *
	 * @return the class loader
	 */
	public String getClassLoader() {
		return classLoader;
	}

	/**
	 * Sets the class loader.
	 *
	 * @param classLoader the new class loader
	 */
	public void setClassLoader(String classLoader) {
		this.classLoader = classLoader;
	}

	/**
	 * Gets the metadata.
	 *
	 * @return the metadata
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * Sets the metadata.
	 *
	 * @param metadata the new metadata
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/* (non-Javadoc)
	 * @see eu.operando.core.cpcu.servlet.Configuration#convert(org.json.simple.JSONObject)
	 */
	@Override
	public void convert(JSONObject obj) {
		setId(((Long) obj.get("id")).intValue());
		setType((String) obj.get("type"));
		setDependancies(((Long) obj.get("dependancies")).intValue());
		setProcessor((String) obj.get("processor"));
		setClassLoader((String) obj.get("classLoader"));
		setMetadata((String) obj.get("metadata"));
		setGeneratedQuestions((Boolean) obj.get("generatedQuestions"));
	}
	
	
}
