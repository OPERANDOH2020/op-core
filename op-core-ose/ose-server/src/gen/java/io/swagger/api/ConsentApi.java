package io.swagger.api;

import io.swagger.api.factories.ConsentApiServiceFactory;

import io.swagger.annotations.ApiParam;
import eu.operando.core.pdb.common.model.UserConsents;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/consent")


@io.swagger.annotations.Api(description = "the consent API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-01-18T15:15:52.371Z")
public class ConsentApi  {
   private final ConsentApiService delegate = ConsentApiServiceFactory.getConsentApi();

    @GET
    @Path("/{osp-id}")


    @io.swagger.annotations.ApiOperation(value = "", notes = "Retrieve the consent report for an OSP. This is the anonymized list of users subscribed to this OSP, and the consents they have given for this service to access their personal data. ", response = UserConsents.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response. The consent report is returned.", response = UserConsents.class, responseContainer = "List"),

        @io.swagger.annotations.ApiResponse(code = 404, message = "The OSP does not exist, and a consent report is not available.", response = UserConsents.class, responseContainer = "List") })
    public Response consentOspIdGet(@ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId
,@ApiParam(value = "") @QueryParam("field") String field
,@ApiParam(value = "") @QueryParam("role") String role
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.consentOspIdGet(ospId,field,role,securityContext);
    }

        @GET
    @Path("/{osp-id}/{user-id}")


    @io.swagger.annotations.ApiOperation(value = "", notes = "Retrieve the consent report for an OSP for a given user id.  ", response = UserConsents.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response. The consent report is returned.", response = UserConsents.class, responseContainer = "List"),

        @io.swagger.annotations.ApiResponse(code = 404, message = "The OSP does not exist, and a consent report is not available.", response = UserConsents.class, responseContainer = "List") })
    public Response consentOspIdUserIdGet(@ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId
,@ApiParam(value = "The user identifier number",required=true) @PathParam("user-id") String userId
,@ApiParam(value = "") @QueryParam("field") String field
,@ApiParam(value = "") @QueryParam("role") String role
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.consentOspIdUserIdGet(ospId,userId,field,role,securityContext);
    }
}
