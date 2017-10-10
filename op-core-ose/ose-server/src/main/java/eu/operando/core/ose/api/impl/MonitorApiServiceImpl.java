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
//      Created Date :          2017-03-21
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

package eu.operando.core.ose.api.impl;

import io.swagger.api.*;
import io.swagger.api.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 * Implementation of the monitor API method - that is used by
 * an Operando PSP platform admin to determine if the server is running
 * or not.
 */

public class MonitorApiServiceImpl extends MonitorApiService {

    /**
     * HTTP GET method to retrieve a HTTP status code that
     * @param securityContext The JAX-RS context information passed to the method implementation.
     * @return A HTTP response with a status code and status body message.
     * @throws NotFoundException
     */

    @Override
    public Response monitorGet(SecurityContext securityContext) throws NotFoundException {
        /**
         * Simple method to respond with the stated response message and a HTTP
         * 200 OK code.
         */
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "The service is currently running")).build();
    }
}
