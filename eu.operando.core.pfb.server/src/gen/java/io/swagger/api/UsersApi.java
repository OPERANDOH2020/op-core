package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.UsersApiService;
import io.swagger.api.factories.UsersApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.Error;
import io.swagger.model.InlineResponse2005;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/users")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the users API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-03-31T13:37:20.076Z")
public class UsersApi  {
   private final UsersApiService delegate = UsersApiServiceFactory.getUsersApi();

    @GET
    @Path("/{user_id}/deals")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Returns list of offers accepted by user.", notes = "Request from privacy dashboard to get list of offers accepted by user (and acknowledged by OSP).", response = InlineResponse2005.class, tags={ "Users", "Deals" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation (list of offers accepted by user).", response = InlineResponse2005.class),
        
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse2005.class) })

    public Response getAcceptedDealsByUser(
@ApiParam(value = "The user identifier number",required=true) @PathParam("user_id") String userId,
@ApiParam(value = "filter deals by category / status" ) String status,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getAcceptedDealsByUser(userId,status,securityContext);
    }
}
