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

/**
 * The Class Statement.
 */
public class Statement implements Comparable<Statement>{

	/** The question. */
	private String statementString;
	
	/** The weight. */
	private double weight;
	
	/** The category. */
	private String category;
	
	/** The privacy ranking. */
	private Integer privacyRanking;
	
	/** The metadata. */
	private String metadata;
	
	/** The rating. */
	private Integer rating;
	
	/**
	 * Instantiates a new statement.
	 *
	 * @param question the statement String
	 * @param weight the weight of the statement
	 * @param category the category the statement resides in
	 * @param privacyRanking the privacy ranking of the statement
	 */
	public Statement(String question, double weight, String category, Integer privacyRanking){
		this.statementString = question;
		this.weight = weight;
		this.category = category;
		this.privacyRanking = privacyRanking;
	}
		
	/**
	 * Sets the metadata.
	 *
	 * @param data the data
	 * @return the statement
	 */
	public Statement setMetadata(String data){
		this.metadata = data;
		return this;
	}
	
	/**
	 * Gets the metadata.
	 *
	 * @return the metadata
	 */
	public String getMetadata(){
		return metadata;
	}
	
	/**
	 * Gets the question.
	 *
	 * @return the question
	 */
	public String getStatementString() {
		return statementString;
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
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory(){
		return category;
	}

	/**
	 * Gets the privacy ranking.
	 *
	 * @return the privacy ranking
	 */
	public Integer getPrivacyRanking(){
		return privacyRanking;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return statementString;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Statement q){
	     return getPrivacyRanking() - q.getPrivacyRanking();
	}

	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public Integer getRating() {
		return rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(Integer rating) {
		this.rating = rating;
	}

	/**
	 * Sets the statement string.
	 *
	 * @param statementString the new statement string
	 */
	public void setStatementString(String statementString) {
		this.statementString = statementString;
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
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Sets the privacy ranking.
	 *
	 * @param privacyRanking the new privacy ranking
	 */
	public void setPrivacyRanking(Integer privacyRanking) {
		this.privacyRanking = privacyRanking;
	}

}
