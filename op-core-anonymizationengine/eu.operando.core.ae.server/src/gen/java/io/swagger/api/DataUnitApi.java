/*
   	* Copyright (c) 2016 {TECNALIA}.
    * All rights reserved. This program and the accompanying materials
    * are made available under the terms of the The MIT License (MIT).
    * which accompanies this distribution, and is available at
    * http://opensource.org/licenses/MIT
    *
    * Contributors:
    *    Gorka Benguria Elguezabal {TECNALIA}
    *    Gorka Mikel Echevarría {TECNALIA}
    * Initially developed in the context of OPERANDO EU project www.operando.eu
 */
package io.swagger.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.annotations.ApiParam;
import io.swagger.api.factories.DataUnitApiServiceFactory;
import io.swagger.model.AccessLevel;
import io.swagger.model.DataUnit;
import io.swagger.model.DataUnitRequest;
import io.swagger.model.InlineResponse200;
import io.swagger.model.InlineResponse2001;
import io.swagger.model.InlineResponse2002;

@Path("/dataUnit")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the dataUnit API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-10T09:36:15.284Z")
public class DataUnitApi  {
   private final DataUnitApiService delegate = DataUnitApiServiceFactory.getDataUnitApi();

    @POST
    @Path("/{dataunit_id}/person/{person_id}/requester/{requester_id}/accessLevel")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Sets the granted access level for a data unit related to a person and for a specific requester.", notes = "Sets the granted access level for a data unit related to a person and for a specific requester.", response = void.class, tags={ "DataUnits",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation.", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = void.class) })
    public Response addAccessLevelsByDataUnitByPersonAndRequester(
        @ApiParam(value = "The dataunit identifier number",required=true) @PathParam("dataunit_id") String dataunitId,
        @ApiParam(value = "The person identifier number",required=true) @PathParam("person_id") String personId,
        @ApiParam(value = "The requester identifier number",required=true) @PathParam("requester_id") String requesterId,
        @ApiParam(value = "The access level data in JSON format." ,required=true) AccessLevel accessLevel,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addAccessLevelsByDataUnitByPersonAndRequester(dataunitId,personId,requesterId,accessLevel,securityContext);
    }
    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Creates a new data unit.", notes = "Creates a new data unit.", response = InlineResponse2001.class, tags={ "DataUnits",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = InlineResponse2001.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse2001.class) })
    public Response createDataUnit(
        @ApiParam(value = "The Data Unit data in JSON format." ,required=true) DataUnitRequest dataUnit,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createDataUnit(dataUnit,securityContext);
    }
    @DELETE
    @Path("/{dataunit_id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Deletes an existing data unit.", notes = "Deletes an existing data unit.", response = void.class, tags={ "DataUnits",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = void.class) })
    public Response deleteDataUnit(
        @ApiParam(value = "The dataunit identifier number",required=true) @PathParam("dataunit_id") String dataunitId,
        @ApiParam(value = "ID of the data unit that needs to be deleted",required=true) @PathParam("dataunit_id") String dataunitId2,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteDataUnit(dataunitId,dataunitId2,securityContext);
    }
    @GET
    @Path("/{dataunit_id}/accessLevels/search")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets the available access levels for a specific data unit.", notes = "Gets the available access levels for a specific data unit.", response = InlineResponse2002.class, tags={ "DataUnits",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation (list of access levels' associated with the data unit).", response = InlineResponse2002.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse2002.class) })
    public Response getAccessLevelsByDataUnit(
        @ApiParam(value = "The dataunit identifier number",required=true) @PathParam("dataunit_id") String dataunitId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getAccessLevelsByDataUnit(dataunitId,securityContext);
    }
    @GET
    @Path("/{dataunit_id}/country/{country_id}/accessLevels/search")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets the granted access levels for a specific data unit according to countries laws.", notes = "Gets the granted access levels for a specific data unit according to countries laws.", response = InlineResponse2002.class, tags={ "DataUnits",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation (list of access levels' associated with the data unit).", response = InlineResponse2002.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse2002.class) })
    public Response getAccessLevelsByDataUnitAndCountry(
        @ApiParam(value = "The dataunit identifier number",required=true) @PathParam("dataunit_id") String dataunitId,
        @ApiParam(value = "The country identifier number",required=true) @PathParam("country_id") String countryId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getAccessLevelsByDataUnitAndCountry(dataunitId,countryId,securityContext);
    }
    @GET
    @Path("/{dataunit_id}/pa/accessLevels/search")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets the granted access levels for a specific data unit according the privacy authority.", notes = "Gets the granted access levels for a specific data unit according the privacy authority.", response = InlineResponse2002.class, tags={ "DataUnits",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation (list of access levels' associated with the privacy authority).", response = InlineResponse2002.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse2002.class) })
    public Response getAccessLevelsByDataUnitAndPA(
        @ApiParam(value = "The dataunit identifier number",required=true) @PathParam("dataunit_id") String dataunitId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getAccessLevelsByDataUnitAndPA(dataunitId,securityContext);
    }
    @GET
    @Path("/{dataunit_id}/person/{person_id}/requester/{requester_id}/accessLevel/search")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets the granted access level for a data unit related to a person and for a specific requester.", notes = "Gets the granted access level for a data unit related to a person and for a specific requester.", response = InlineResponse200.class, tags={ "DataUnits",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation (list of access levels' associated with the data unit).", response = InlineResponse200.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse200.class) })
    public Response getAccessLevelsByDataUnitPersonAndRequester(
        @ApiParam(value = "The dataunit identifier number",required=true) @PathParam("dataunit_id") String dataunitId,
        @ApiParam(value = "The person identifier number",required=true) @PathParam("person_id") String personId,
        @ApiParam(value = "The requester identifier number",required=true) @PathParam("requester_id") String requesterId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getAccessLevelsByDataUnitPersonAndRequester(dataunitId,personId,requesterId,securityContext);
    }
    @GET
    @Path("/{dataunit_id}/search")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds a data unit by ID.", notes = "Finds a data unit by ID.", response = void.class, tags={ "DataUnits",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = void.class) })
    public Response getDataUnit(
        @ApiParam(value = "The dataunit identifier number",required=true) @PathParam("dataunit_id") String dataunitId,
        @ApiParam(value = "ID of the data unit that needs to be fetched.",required=true) @PathParam("dataunit_id") String dataunitId2,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getDataUnit(dataunitId,dataunitId2,securityContext);
    }
    @GET
    @Path("/search")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets existing data units.", notes = "Gets existing data units.", response = DataUnit.class, responseContainer = "List", tags={ "DataUnits",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = DataUnit.class, responseContainer = "List"),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = DataUnit.class, responseContainer = "List") })
    public Response getDataUnits(
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getDataUnits(securityContext);
    }
    @POST
    @Path("/{dataunit_id}/accessLevels")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Sets the available access levels for a specific data unit.", notes = "Sets the available access levels for a specific data unit.", response = void.class, tags={ "DataUnits",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = void.class) })
    public Response setAccessLevelsByDataUnit(
        @ApiParam(value = "The dataunit identifier number",required=true) @PathParam("dataunit_id") String dataunitId,
        @ApiParam(value = "List of Access Levels." ,required=true) List<AccessLevel> accesslevels,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.setAccessLevelsByDataUnit(dataunitId,accesslevels,securityContext);
    }
    @PUT
    @Path("/{dataunit_id}/accessLevels")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Updates the available access levels for a specific data unit.", notes = "Updates the available access levels for a specific data unit.", response = void.class, tags={ "DataUnits",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = void.class) })
    public Response updateAccessLevelsByDataUnit(
        @ApiParam(value = "The dataunit identifier number",required=true) @PathParam("dataunit_id") String dataunitId,
        @ApiParam(value = "List of Access Levels." ,required=true) List<AccessLevel> accesslevels,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateAccessLevelsByDataUnit(dataunitId,accesslevels,securityContext);
    }
    @PUT
    @Path("/{dataunit_id}/person/{person_id}/requester/{requester_id}/accessLevel")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Updates the granted access level for a data unit related to a person and for a specific requester.", notes = "Updates the granted access level for a data unit related to a person and for a specific requester.", response = void.class, tags={ "DataUnits",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation.", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = void.class) })
    public Response updateAccessLevelsByDataUnitByPersonAndRequester(
        @ApiParam(value = "The dataunit identifier number",required=true) @PathParam("dataunit_id") String dataunitId,
        @ApiParam(value = "The person identifier number",required=true) @PathParam("person_id") String personId,
        @ApiParam(value = "The requester identifier number",required=true) @PathParam("requester_id") String requesterId,
        @ApiParam(value = "The access level data in JSON format." ,required=true) AccessLevel accessLevel,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateAccessLevelsByDataUnitByPersonAndRequester(dataunitId,personId,requesterId,accessLevel,securityContext);
    }
    @PUT
    @Path("/{dataunit_id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Updates an existing data unit.", notes = "Updates an existing data unit.", response = InlineResponse2001.class, tags={ "DataUnits" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = InlineResponse2001.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse2001.class) })
    public Response updateDataUnit(
        @ApiParam(value = "The dataunit identifier number",required=true) @PathParam("dataunit_id") String dataunitId,
        @ApiParam(value = "The Data Unit data in JSON format." ,required=true) DataUnitRequest dataUnit,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateDataUnit(dataunitId,dataUnit,securityContext);
    }
}
