package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.OspPolicyEvaluatorApiService;
import io.swagger.api.factories.OspPolicyEvaluatorApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.OSPDataRequest;
import java.util.List;
import io.swagger.model.PolicyEvaluationReport;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/osp_policy_evaluator")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the osp_policy_evaluator API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class OspPolicyEvaluatorApi  {
   private final OspPolicyEvaluatorApiService delegate = OspPolicyEvaluatorApiServiceFactory.getOspPolicyEvaluatorApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Evaluate a set of OSP requests against the User Privacy Policy", notes = "Called by the Rights Management Component when evaluating if an OSP request or set of requests matches the user preferences stored in the user's UPP. ", response = PolicyEvaluationReport.class, tags={ "Policy Evaluation", "POST" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Evaluation was successful and a report is produced. The report indicates if the request(s) is allowed. Where not allowed, the report indicates. ", response = PolicyEvaluationReport.class),
        @io.swagger.annotations.ApiResponse(code = 400, message = "The client inputs to the operation are incorrect or invalid. The caller should check the inputs are valid based upon the returned error message. ", response = PolicyEvaluationReport.class) })
    public Response ospPolicyEvaluatorPost(
        @ApiParam(value = "Unique identifier representing a specific user",required=true) @QueryParam("user_id") String userId,
        @ApiParam(value = "OSP unique identifier",required=true) @QueryParam("osp_id") String ospId,
        @ApiParam(value = "The set of requests that the OSP wants to perform on a user's private data" ,required=true) List<OSPDataRequest> ospRequest,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ospPolicyEvaluatorPost(userId,ospId,ospRequest,securityContext);
    }
}
