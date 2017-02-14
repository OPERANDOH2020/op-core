package io.swagger.api;

import io.swagger.api.ConsentApiService;
import io.swagger.api.factories.ConsentApiServiceFactory;

import io.swagger.annotations.ApiParam;

import io.swagger.model.UserConsents;

import io.swagger.api.NotFoundException;



import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/consent")


@io.swagger.annotations.Api(description = "the consent API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-12T13:34:24.407Z")
public class ConsentApi  {
   private final ConsentApiService delegate = ConsentApiServiceFactory.getConsentApi();

    @GET
    @Path("/{osp-id}/")


    @io.swagger.annotations.ApiOperation(value = "", notes = "Retrieve the consent report for an OSP. This is the list of users subscribed to this OSP, and the consents they have given for this service to access their personal data. ", response = UserConsents.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response. The consent report is returned.", response = UserConsents.class, responseContainer = "List"),

        @io.swagger.annotations.ApiResponse(code = 404, message = "Error response. The OSP does not exist, and a consent report is not available.", response = UserConsents.class, responseContainer = "List") })
    public Response consentOspIdGet(@ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.consentOspIdGet(ospId,securityContext);
    }
}
