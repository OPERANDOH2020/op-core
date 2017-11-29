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
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

package io.swagger.api;

import io.swagger.api.factories.MonitorApiServiceFactory;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

/**
 * Generated code that defines the monitor API method - that is used by
 * an Operando PSP platform admin to determine if the server is running
 * or not.
 */
@Path("/monitor")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "The OSE Monitoring API")
public class MonitorApi  {
   private final MonitorApiService delegate = MonitorApiServiceFactory.getMonitorApi();

    /**
     * HTTP GET method to retrieve a HTTP status code that
     * @param securityContext
     * @return A HTTP response with a status code and status body message.
     * @throws NotFoundException
     */
    @GET
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Monitor the OSE server", notes = "Monitor the execution status of the OSE server.", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "The service is currently running.", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 404, message = "The service isn't currently running. ", response = void.class) })
    public Response monitorGet(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.monitorGet(securityContext);
    }

    @GET
    @Path("/start")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Start the OSE server", notes = "Start the execution status of the OSE server.", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "The service is started.", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 404, message = "The service isn't currently running. ", response = void.class) })
    public Response startGet(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.startGet(securityContext);
    }
}
