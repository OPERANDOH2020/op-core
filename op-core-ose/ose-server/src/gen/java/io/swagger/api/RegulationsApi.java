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
//      Created By :            Panos Melas
//      Created Date :          2016-04-28
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

package io.swagger.api;

import eu.operando.core.ose.api.factories.RegulationsApiServiceFactory;

import io.swagger.annotations.ApiParam;


import eu.operando.core.pdb.common.model.PrivacyRegulationInput;
import eu.operando.core.pdb.common.model.PrivacyRegulation;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/regulations")


@io.swagger.annotations.Api(description = "the regulations API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-25T15:37:02.222Z")
public class RegulationsApi  {
   private final RegulationsApiService delegate = RegulationsApiServiceFactory.getRegulationsApi();

    @POST
    @Path("/")


    @io.swagger.annotations.ApiOperation(value = "", notes = "Called by the Regulator API. A New regulation is entered into OPERANDO. Existing OSPs are then evaluated to see if they comply with the regulation. If not they are sent a report about how to comply.\n", response = void.class, tags={ "Privacy Legislation",  })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response. The regulation has been created.", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 409, message = "Error occured. The resource already exists, so a new resource cannot be created.", response = void.class) })
    public Response regulationsPost(
        @ApiParam(value = "The description of the new regulation." ,required=true) PrivacyRegulation regulation,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.regulationsPost(regulation,securityContext);
    }
    @PUT
    @Path("/{reg-id}/")


    @io.swagger.annotations.ApiOperation(value = "", notes = "Called by the Regulator API. A change to a regulation is entered into OPERANDO. Existing OSPs are then evaluated to see if they comply with the regulation. If not they are sent a report about how to comply.\n  \n  Pre-condition -- The regulation must have been written to the system.\n  \n", response = void.class, tags={ "Privacy Legislation" })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response. The regulation has been added.", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error. The resource does not exist to be updated.", response = void.class) })
    public Response regulationsRegIdPut(
        @ApiParam(value = "The identifier number of a regulation",required=true) @PathParam("reg-id") String regId,
        @ApiParam(value = "The description of the changed regulation." ,required=true) PrivacyRegulationInput regulation,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.regulationsRegIdPut(regId,regulation,securityContext);
    }
}
