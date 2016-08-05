package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.OspComplianceCheckerApiService;
import io.swagger.api.factories.OspComplianceCheckerApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

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

@Path("/osp_compliance_checker")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the osp_compliance_checker API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class OspComplianceCheckerApi  {
   private final OspComplianceCheckerApiService delegate = OspComplianceCheckerApiServiceFactory.getOspComplianceCheckerApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Evaluate a set of OSP requests against the current legislation", notes = "Method to run a new OSP registers with OPERANDO; or a an OSP changes its policy. The computational resource reads the sector information for the OSP (from the policy db) and then finds all the relevant regulations. Given the policy input, it then checks that this against the discovered regulation. ", response = PolicyEvaluationReport.class, tags={ "Policy Compliance", "POST" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Compliance was successful and a report is produced. The report indicates if the request(s) complies with legislation. Where there is a breach, the report indicates the reason for the breach.", response = PolicyEvaluationReport.class),
        @io.swagger.annotations.ApiResponse(code = 400, message = "The client inputs to the operation are incorrect or invalid. The caller should check the inputs are valid based upon the returned error message. ", response = PolicyEvaluationReport.class) })
    public Response ospComplianceCheckerPost(
        @ApiParam(value = "OSP unique identifier",required=true) @QueryParam("osp_id") String ospId,
        @ApiParam(value = "The set of requests that the OSP will carry out on a user's private data" ,required=true) Object ospRequest,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ospComplianceCheckerPost(ospId,ospRequest,securityContext);
    }
}
