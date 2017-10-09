/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2016
//
// Copyright in this library belongs to the University of Southampton
// University Road, Highfield, Southampton, UK, SO17 1BJ
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
// Created By : Paul Grace
// Created for Project : OPERANDO (http://www.operando.eu)
//
/////////////////////////////////////////////////////////////////////////
//
//  License : GNU Lesser General Public License, version 3
//
/////////////////////////////////////////////////////////////////////////

package eu.operando;

/**
 * Exception that occurs when a method concerns a OSP id that does not currently
 * exist in the OPERANDO PSP platform database.
 *
 * @author pjg
 */
public class UnknownOSPException extends Exception {

    /**
     * Exception that adds a configuration error message for clarity.
     * @param errorMsg The specific error explanation.
     * @param excep The caught exception.
     */
    public UnknownOSPException(final String errorMsg, final Exception excep) {
        super(errorMsg, excep);
    }
    /**
     * Contains the UnknownUserException error message without keeping the prior
     * caught exception data.
     * @param errorMsg The error message to attach to the exception.
     */
    public UnknownOSPException(final String errorMsg) {
        super(errorMsg);
    }
}
