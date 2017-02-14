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
//      Created By :            Panos Melas
//      Created Date :          2016-04-28
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

package eu.operando.core.ose.services;

/**
 * A standard response class containing result information to be returned
 * by the web service API.
 *
 */
public class OSPAPIResponse {

    /**
     * Any response message to be returned.
     */
    public String errorMessage;

    /**
     * Status code.
     */
    public int status;

    /**
     * Castable result object - reused across different functions.
     */
    public Object dataResult;

    public OSPAPIResponse(String error, int status, Object data) {
        this.dataResult = data;
        this.errorMessage = error;
        this.status = status;
    }
}
