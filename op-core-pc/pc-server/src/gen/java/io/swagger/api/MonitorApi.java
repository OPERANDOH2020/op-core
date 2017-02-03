package io.swagger.api;

import io.swagger.api.MonitorApiService;
import io.swagger.api.factories.MonitorApiServiceFactory;

import io.swagger.api.NotFoundException;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/monitor")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the monitor API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-03T15:31:38.137Z")
public class MonitorApi  {
   private final MonitorApiService delegate = MonitorApiServiceFactory.getMonitorApi();

    @GET

    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Ping the server", notes = "Monitor the PC server. ", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response. The service is up.", response = void.class),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error occured. The service isn't there. ", response = void.class) })
    public Response monitorGet(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.monitorGet(securityContext);
    }
}
