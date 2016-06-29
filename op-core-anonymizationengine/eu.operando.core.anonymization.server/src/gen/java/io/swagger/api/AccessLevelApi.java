package io.swagger.api;

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
import io.swagger.api.factories.AccessLevelApiServiceFactory;
import io.swagger.model.AccessLevel;
import io.swagger.model.AccessLevelRequest;
import io.swagger.model.InlineResponse200;

@Path("/accessLevel")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the accessLevel API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-10T09:36:15.284Z")
public class AccessLevelApi  {
   private final AccessLevelApiService delegate = AccessLevelApiServiceFactory.getAccessLevelApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Creates a new access level.", notes = "Creates a new access level.", response = InlineResponse200.class, tags={ "AccessLevels",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = InlineResponse200.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse200.class) })
    public Response createAccessLevel(
        @ApiParam(value = "The Access Level data in JSON format." ,required=true) AccessLevelRequest accessLevel,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.createAccessLevel(accessLevel,securityContext);
    }
    @DELETE
    @Path("/{accessLevel_id}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Deletes an existing access level.", notes = "Deletes an existing access level.", response = void.class, tags={ "AccessLevels",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = void.class) })
    public Response deleteAccessLevel(
        @ApiParam(value = "The access level identifier number",required=true) @PathParam("accessLevel_id") String accessLevelId,
        @ApiParam(value = "ID of the access level that needs to be deleted",required=true) @PathParam("accessLevel_id") String accessLevelId2,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.deleteAccessLevel(accessLevelId,accessLevelId2,securityContext);
    }
    @GET
    @Path("/{accessLevel_id}/search")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Finds a access level by ID.", notes = "Finds a access level by ID.", response = void.class, tags={ "AccessLevels",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = void.class) })
    public Response getAccessLevel(
        @ApiParam(value = "The access level identifier number",required=true) @PathParam("accessLevel_id") String accessLevelId,
        @ApiParam(value = "ID of the access level that needs to be fetched.",required=true) @PathParam("accessLevel_id") String accessLevelId2,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getAccessLevel(accessLevelId,accessLevelId2,securityContext);
    }
    @GET
    @Path("/search")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Gets existing access levels.", notes = "Gets existing access levels.", response = AccessLevel.class, responseContainer = "List", tags={ "AccessLevels",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = AccessLevel.class, responseContainer = "List"),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = AccessLevel.class, responseContainer = "List") })
    public Response getAccessLevels(
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getAccessLevels(securityContext);
    }
    @PUT
    @Path("/{accessLevel_id}")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Updates an existing access level.", notes = "Updates an existing access level.", response = InlineResponse200.class, tags={ "AccessLevels" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation", response = InlineResponse200.class),
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse200.class) })
    public Response updateAccessLevel(
        @ApiParam(value = "The access level identifier number",required=true) @PathParam("accessLevel_id") String accessLevelId,
        @ApiParam(value = "The Access Level data in JSON format." ,required=true) AccessLevelRequest accessLevel,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.updateAccessLevel(accessLevelId,accessLevel,securityContext);
    }
}
