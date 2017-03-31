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
 * The Class QuestionConfiguration.
 */
public class QuestionConfiguration extends Configuration{

	/** The question string. */
	private String questionString;
	
	/** The id. */
	private int id;
	
	/** The weight. */
	private double weight;
	
	/** The category. */
	private String category;
	
	/** The privacy rating. */
	private int privacyRanking;
	
	/** The metadata. */
	private String metadata;

	/**
	 * Gets the question string.
	 *
	 * @return the question string
	 */
	public String getQuestionString() {
		return questionString;
	}

	/**
	 * Sets the question string.
	 *
	 * @param questionString the new question string
	 */
	public void setQuestionString(String questionString) {
		this.questionString = questionString;
	}

	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Sets the weight.
	 *
	 * @param weight the new weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Gets the privacy rating.
	 *
	 * @return the privacy rating
	 */
	public int getPrivacyRanking() {
		return privacyRanking;
	}

	/**
	 * Sets the privacy rating.
	 *
	 * @param privacyRating the new privacy rating
	 */
	public void setPrivacyRanking(int privacyRanking) {
		this.privacyRanking = privacyRanking;
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

	/* (non-Javadoc)
	 * @see eu.operando.core.cpcu.servlet.Configuration#convert(org.json.simple.JSONObject)
	 */
	@Override
	public void convert(JSONObject obj) {
		setId(((Long) obj.get("id")).intValue());
		setQuestionString((String) obj.get("questionString"));
		setWeight(((Double) obj.get("weight")).doubleValue());
		setCategory((String) obj.get("category"));
		setPrivacyRanking(((Long) obj.get("privacyRanking")).intValue());
		setMetadata((String) obj.get("metadata"));
	}
	
}
