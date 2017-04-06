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

import eu.operando.core.cpcu.main.DataHolder;

/**
 * The Class ActionStatement.
 */
public class ActionStatement extends ServiceStatement{

	/** The action. */
	private DataHolder action;
	
	/**
	 * Instantiates a new action statement.
	 *
	 * @param question the question
	 * @param category the category
	 * @param privacyRanking the privacy ranking
	 * @param role the role
	 * @param action the action
	 */
	public ActionStatement(String question, String category, Integer privacyRanking, DataHolder role, DataHolder action) {
		super(question, category, privacyRanking, role);
		this.action = action;
	}
	
	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public DataHolder getAction(){
		return action;
	}

}
