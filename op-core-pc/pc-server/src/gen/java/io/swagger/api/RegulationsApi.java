package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.RegulationsApiService;
import io.swagger.api.factories.RegulationsApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.ComputationResult;
import java.math.BigDecimal;
import io.swagger.model.PrivacyRegulation;
import java.util.List;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/regulations")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the regulations API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class RegulationsApi  {
   private final RegulationsApiService delegate = RegulationsApiServiceFactory.getRegulationsApi();

    @POST
    @Path("/{reg_id}/")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Called by the Regulator API after a   new regulation is entered into OPERANDO. The regulation is first created and stored by the policy DB. Existing UPPs are then evaluated to see if they comply with the regulation. The report is then created at /regulations/{reg_id}/report in the policy db and the url is returned to the user in order that they can retrieve this report. ", response = ComputationResult.class, tags={ "Privacy Regulations",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response. The regulation has evaluated and report produced.", response = ComputationResult.class),
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error occured. The resource resource doesn't exist to evaluate. ", response = ComputationResult.class) })
    public Response regulationsRegIdPost(
        @ApiParam(value = "The unqiue regulation ID for the new regulation created.",required=true) @PathParam("reg_id") BigDecimal regId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.regulationsRegIdPost(regId,securityContext);
    }
    @PUT
    @Path("/{reg_id}/")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Called by the Regulator API. An updated regulation is entered into OPERANDO. The regulation is stored in the policy DB. Existing UPPs are then evaluated to see if they comply with the regulation. The report is then updated at /regulations/{reg_id}/report in the policy db and the url is returned to the user in order that they can retrieve this report. Pre-condition -- The regulation must have been written to the system. ", response = ComputationResult.class, tags={ "Privacy Regulations", "PUT" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response. The regulation has evaluated and report produced.", response = ComputationResult.class),
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error occured. The resource resource doesn't exist to evaluate. ", response = ComputationResult.class) })
    public Response regulationsRegIdPut(
        @ApiParam(value = "The unqiue regulation ID for the changed regulation.",required=true) @PathParam("reg_id") BigDecimal regId,
        @ApiParam(value = "The description of the changed regulation." ,required=true) List<PrivacyRegulation> regulation,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.regulationsRegIdPut(regId,regulation,securityContext);
    }
}
