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
 * The Class Response.
 */
@Generated("org.jsonschema2pojo")
public class Response {

    /** The error. */
    private String error;
    
    /** The session. */
    private String session;
    
    /** The questionnaire. */
    private Questionnaire questionnaire;
    

    /**
     * Gets the error.
     *
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * Sets the error.
     *
     * @param error the new error
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * Gets the session.
     *
     * @return the session
     */
    public String getSession() {
        return session;
    }

    /**
     * Sets the session.
     *
     * @param session the new session
     */
    public void setSession(String session) {
        this.session = session;
    }

    /**
     * Gets the questionnaire.
     *
     * @return the questionnaire
     */
    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    /**
     * Sets the questionnaire.
     *
     * @param questionnaire the new questionnaire
     */
    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

}
