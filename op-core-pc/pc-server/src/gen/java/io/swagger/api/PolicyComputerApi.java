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

import eu.operando.core.pdb.common.model.UserPreference;
import io.swagger.api.factories.PolicyComputerApiServiceFactory;

import io.swagger.annotations.ApiParam;


import io.swagger.model.ComputationResult;


import java.util.List;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/policy_computer")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the policy_computer API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class PolicyComputerApi  {
   private final PolicyComputerApiService delegate = PolicyComputerApiServiceFactory.getPolicyComputerApi();

    @POST

    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Creates the UPP when the user registers with OPERANDO.", notes = "This is a computational resource accessed via a REST POST call to carry out a computation on the provided inputs. This results in an resource that is created and stored in the Policy DB and can be accessed by the URL returned as a result of this operation. In OPERANDO, this method is called by the management console when a user registers with OPERANDO. The user simply enters privacy details and answers a general questionnaire.  ", response = ComputationResult.class, tags={ "Policy Compute", "POST" })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "UPP was created. There is no invalid response - if the method is called on the operation with an existing user_id then the server updates the record field (This is not the data storage operation). ", response = ComputationResult.class),
        @io.swagger.annotations.ApiResponse(code = 400, message = "The client inputs to the operation are incorrect or invalid. The caller should check the inputs are valid based upon the returned error message. ", response = ComputationResult.class) })
    public Response policyComputerPost(
        @ApiParam(value = "user unique identifier of the UPP to compute",required=true) @QueryParam("user_id") String userId,
        @ApiParam(value = "The set of privacy preferences. This is a JSON object with the answers to the initial questionnaire. These can be changed and updated.  " ,required=true) List<UserPreference> generalPreferences,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.policyComputerPost(userId,generalPreferences,securityContext);
    }
}
