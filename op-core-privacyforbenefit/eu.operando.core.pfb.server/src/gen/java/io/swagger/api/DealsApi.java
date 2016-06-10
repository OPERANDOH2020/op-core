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
import io.swagger.api.DealsApiService;
import io.swagger.api.factories.DealsApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.Error;
import io.swagger.model.InlineResponse200;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/deals")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the deals API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-27T11:58:50.874Z")
public class DealsApi  {
   private final DealsApiService delegate = DealsApiServiceFactory.getDealsApi();

    @DELETE
    @Path("/{deal_id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Cancel a deal.", notes = "Request from Privacy Dashboard to cancel a deal. The deal is not deleted, but updated by setting the current date value to canceled_at field.", response = String.class, tags={ "Deals",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = String.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = String.class) })
    public Response cancelDeal(
        @ApiParam(value = "The unique identifier number of a deal.",required=true) @PathParam("deal_id") String dealId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.cancelDeal(dealId,securityContext);
    }
    @GET
    @Path("/{deal_id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets the status of a given deal.", notes = "Get the status of a given deal.", response = InlineResponse200.class, tags={ "Deals",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation (the offer status)", response = InlineResponse200.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse200.class) })
    public Response dealsDealIdGet(
        @ApiParam(value = "The unique identifier number of a deal.",required=true) @PathParam("deal_id") String dealId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.dealsDealIdGet(dealId,securityContext);
    }
    @POST
    @Path("/{deal_id}/acknowledgement")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Acknoledge a deal by OSP.", notes = "This API call is used by the OSP to acknowledge a deal and approve that offer was awarded to user", response = String.class, tags={ "Deals",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = String.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = String.class) })
    public Response offerAccepted(
        @ApiParam(value = "The unique identifier number of a deal.",required=true) @PathParam("deal_id") String dealId,
        @ApiParam(value = "Osp Id.",required=true) @QueryParam("ospId") String ospId,
        @ApiParam(value = "Offer Id.",required=true) @QueryParam("offerId") String offerId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.offerAccepted(dealId,ospId,offerId,securityContext);
    }
    @POST
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Create a new deal. Indicates the user's acceptance for an offer.", notes = "Triggered by the extension to PfB service to indicate that the user has chosen to accept the offer (i.e. initiated login with Social Network button)", response = InlineResponse200.class, tags={ "Offers" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = InlineResponse200.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse200.class) })
    public Response createDeal(
    		@ApiParam(value = "The deal data in JSON format." ,required=true) DealRequest deal,
        @Context SecurityContext securityContext)
    throws NotFoundException {
    	return delegate.createDeal(deal,securityContext);
    }
}
