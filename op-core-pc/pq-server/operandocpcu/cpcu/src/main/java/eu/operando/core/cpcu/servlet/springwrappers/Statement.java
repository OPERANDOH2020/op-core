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

package eu.operando.core.cpcu.servlet.springwrappers;

import javax.annotation.Generated;

/**
 * The Class Statement.
 */
@Generated("org.jsonschema2pojo")
public class Statement {

    /** The metadata. */
    private String metadata;
    
    /** The weight. */
    private Double weight;
    
    /** The statement string. */
    private String statementString;
    
    /** The privacy ranking. */
    private Integer privacyRanking;
    
    /** The rating. */
    private Integer rating;
    
    /** The role. */
    private String role;
    
    /** The role rank. */
    private Integer roleRank;
    
    /** The action rank. */
    private Integer actionRank;
    
	/** The action. */
	private String action;
    
    /**
     * Gets the action rank.
     *
     * @return the action rank
     */
    public Integer getActionRank() {
		return actionRank;
	}

	/**
	 * Sets the action rank.
	 *
	 * @param actionRank the new action rank
	 */
	public void setActionRank(Integer actionRank) {
		this.actionRank = actionRank;
	}

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the action.
	 *
	 * @param action the new action
	 */
	public void setAction(String action) {
		this.action = action;
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
     * Gets the weight.
     *
     * @return the weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Sets the weight.
     *
     * @param weight the new weight
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * Gets the statement string.
     *
     * @return the statement string
     */
    public String getStatementString() {
        return statementString;
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
     * Gets the privacy ranking.
     *
     * @return the privacy ranking
     */
    public Integer getPrivacyRanking() {
        return privacyRanking;
    }

    /**
     * Sets the privacy ranking.
     *
     * @param privacyRanking the new privacy ranking
     */
    public void setPrivacyRanking(Integer privacyRanking) {
        this.privacyRanking = privacyRanking;
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
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Gets the role rank.
	 *
	 * @return the role rank
	 */
	public Integer getRoleRank() {
		return roleRank;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role){
		this.role = role;
	}
	
	/**
	 * Sets the role rank.
	 *
	 * @param roleRank the new role rank
	 */
	public void setRoleRank(Integer roleRank){
		this.roleRank = roleRank;
	}
}
