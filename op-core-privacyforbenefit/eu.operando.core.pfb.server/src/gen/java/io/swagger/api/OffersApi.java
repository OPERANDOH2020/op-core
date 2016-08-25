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
import io.swagger.api.OffersApiService;
import io.swagger.api.factories.OffersApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.OfferRequest;
import io.swagger.model.InlineResponse2001;
import io.swagger.model.Error;
import io.swagger.model.InlineResponse2003;
import io.swagger.model.InlineResponse2002;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/offers")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the offers API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-27T11:58:50.874Z")
public class OffersApi  {
   private final OffersApiService delegate = OffersApiServiceFactory.getOffersApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Creates a new offer for an OSP .", notes = "Request from Administration Console to create a new offer for an OSP.", response = InlineResponse2001.class, tags={ "Offers",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = InlineResponse2001.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse2001.class) })
    public Response createOffer(
        @ApiParam(value = "The offer data in JSON format." ,required=true) OfferRequest offer,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createOffer(offer,securityContext);
    }
    @GET
    @Path("/{offer_id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets the status of a given offer.", notes = "Get the status of a given offer.", response = InlineResponse2003.class, tags={ "Offers",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation (the offer status)", response = InlineResponse2003.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse2003.class) })
    public Response getOfferStatus(
        @ApiParam(value = "The offer identifier number",required=true) @PathParam("offer_id") String offerId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getOfferStatus(offerId,securityContext);
    }
    @GET
    @Path("/search")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Search offers in databse", notes = "Extension or management console send request to PfB service to get a list of offers by specific search terms (e.g Gets offers related to a website  when signup page is detected)", response = InlineResponse2002.class, tags={ "Offers",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = InlineResponse2002.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse2002.class) })
    public Response getOffers(
        @ApiParam(value = "ID of the OSP to list of all offers created by specific OSP..") @QueryParam("osp_id") String ospId,
        @ApiParam(value = "URL of the website to get offers related to specific URL") @QueryParam("service_website") String serviceWebsite,
        @ApiParam(value = "Indicates if the offer is enabled or not (1/0)") @QueryParam("is_enabled") String isEnabled,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getOffers(ospId,serviceWebsite,isEnabled,securityContext);
    } 
    @PUT
    @Path("/{offer_id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Update an offer for an OSP .", notes = "Request from Administration Console to update existing offer. (limited to specific fields - TBD).", response = InlineResponse2003.class, tags={ "Offers" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation (the offer status)", response = InlineResponse2003.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse2003.class) })
    public Response updateOffer(
        @ApiParam(value = "The offer identifier number",required=true) @PathParam("offer_id") String offerId,
        @ApiParam(value = "The offer data in JSON format." ,required=true) OfferRequest offer,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateOffer(offerId,offer,securityContext);
    }
}
