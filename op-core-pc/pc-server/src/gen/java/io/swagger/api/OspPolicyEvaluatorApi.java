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
package io.swagger.api;

import io.swagger.api.OspPolicyEvaluatorApiService;
import io.swagger.api.factories.OspPolicyEvaluatorApiServiceFactory;

import io.swagger.annotations.ApiParam;


import io.swagger.model.OSPDataRequest;
import java.util.UUID;
import io.swagger.model.PolicyEvaluationReport;

import java.util.List;
import io.swagger.api.NotFoundException;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/osp_policy_evaluator")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the osp_policy_evaluator API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-13T11:11:54.739Z")
public class OspPolicyEvaluatorApi  {
   private final OspPolicyEvaluatorApiService delegate = OspPolicyEvaluatorApiServiceFactory.getOspPolicyEvaluatorApi();

    @POST

    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Evaluate a set of OSP requests against the User Privacy Policy", notes = "Called by the Rights Management Component when evaluating if an OSP\nrequest or set of requests matches the user preferences stored in the\nuser's UPP.\n", response = PolicyEvaluationReport.class, tags={ "Policy Evaluation", "POST" })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Evaluation was successful and a report is produced. The report indicates if the request(s) is allowed. Where not allowed, the report indicates.\n", response = PolicyEvaluationReport.class),
        @io.swagger.annotations.ApiResponse(code = 400, message = "The client inputs to the operation are incorrect or invalid. The caller should check the inputs are valid based upon the returned error message.\n", response = PolicyEvaluationReport.class) })
    public Response ospPolicyEvaluatorPost(
        @ApiParam(value = "Unique identifier representing a specific user",required=true) @QueryParam("user_id") UUID userId,
        @ApiParam(value = "OSP unique identifier",required=true) @QueryParam("osp_id") String ospId,
        @ApiParam(value = "The set of requests that the OSP wants to perform on a user's private data" ,required=true) List<OSPDataRequest> ospRequest,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ospPolicyEvaluatorPost(userId,ospId,ospRequest,securityContext);
    }
}
