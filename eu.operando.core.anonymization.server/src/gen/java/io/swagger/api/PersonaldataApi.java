/*******************************************************************************
 *  * Copyright (c) 2016 {TECNALIA}.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the The MIT License (MIT).
 *  * which accompanies this distribution, and is available at
 *  * http://opensource.org/licenses/MIT
 *  *
 *  * Contributors:
 *  *    Gorka Mikel Echevarría {TECNALIA}
 *  * Initially developed in the context of OPERANDO EU project www.operando.eu
 *******************************************************************************/
package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.PersonaldataApiService;
import io.swagger.api.factories.PersonaldataApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.Error;
import io.swagger.model.RequestedDataUnitsByPersons;
import io.swagger.model.InlineResponse2003;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/personaldata")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the personaldata API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-03-16T08:19:16.233Z")
public class PersonaldataApi  {
   private final PersonaldataApiService delegate = PersonaldataApiServiceFactory.getPersonaldataApi();

    @GET
    @Path("/{requester-id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets individual or colective personal data by receiving as parameters an array of personal ids, a requester id and an array of data units whose values are wanted to be recovered.", notes = "Gets individual or colective personal data by receiving as parameters an array of personal ids, a requester id and an array of data units whose values are wanted to be recovered.", response = InlineResponse2003.class, tags={ "DataUnits" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation.", response = InlineResponse2003.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse2003.class) })

    public Response getPersonalData(
@ApiParam(value = "The requester identifier number",required=true) @PathParam("requester-id") String requesterId,
@ApiParam(value = "object encapsulating the set of data units and the personal ids of the people whose personal data is wanted to be received." ,required=true) RequestedDataUnitsByPersons requestedDataUnitsPersons,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getPersonalData(requesterId,requestedDataUnitsPersons,securityContext);
    }
}
