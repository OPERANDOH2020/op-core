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

import io.swagger.api.OspComplianceCheckerApiService;
import io.swagger.api.factories.OspComplianceCheckerApiServiceFactory;

import io.swagger.annotations.ApiParam;

import io.swagger.model.OSPReasonPolicy;
import io.swagger.model.ComplianceReport;

import io.swagger.api.NotFoundException;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/osp_compliance_checker")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the osp_compliance_checker API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-07T12:10:42.971Z")
public class OspComplianceCheckerApi  {
   private final OspComplianceCheckerApiService delegate = OspComplianceCheckerApiServiceFactory.getOspComplianceCheckerApi();

    @POST

    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Evaluate a set of OSP requests against the current legislation", notes = "Method to run a new OSP registers with OPERANDO; or a an OSP changes its policy. The computational resource reads the sector information for the OSP (from the policy db) and then finds all the relevant regulations. Given the policy input, it then checks that this against the discovered regulation. ", response = ComplianceReport.class, tags={ "Policy Compliance","POST", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Compliance was successful and a report is produced. The report indicates if the request(s) complies with legislation. Where there is a breach, the report indicates the reason for the breach.", response = ComplianceReport.class),

        @io.swagger.annotations.ApiResponse(code = 400, message = "The client inputs to the operation are incorrect or invalid. The caller should check the inputs are valid based upon the returned error message. ", response = ComplianceReport.class) })
    public Response ospComplianceCheckerPost(@ApiParam(value = "OSP unique identifier.",required=true) @QueryParam("osp_id") String ospId
,@ApiParam(value = "The regulation to check compliance with.",required=true) @QueryParam("reg_id") String regId
,@ApiParam(value = "The set of requests that the OSP will carry out on a user's private data" ,required=true) OSPReasonPolicy ospRequest
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ospComplianceCheckerPost(ospId,regId,ospRequest,securityContext);
    }
}
