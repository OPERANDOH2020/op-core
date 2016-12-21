package io.swagger.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.annotations.ApiParam;
import io.swagger.api.factories.PersonaldataApiServiceFactory;
import io.swagger.model.InlineResponse2003;

@Path("/personaldata")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the personaldata API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-10T09:36:15.284Z")
public class PersonaldataApi  {
   private final PersonaldataApiService delegate = PersonaldataApiServiceFactory.getPersonaldataApi();

   @GET
   @Path("/core/ae/personaldata/{requester_id}/search")
   @Consumes({ "application/json" })
   @Produces({ "application/json" })
   @io.swagger.annotations.ApiOperation(value = "Gets individual or colective personal data by receiving as parameter a query especifiyng the data wanted to be recovered and the requester id.", notes = "Gets individual or colective personal data by receiving as parameter a query especifiyng the data wanted to be recovered and the requester id.", response = InlineResponse2003.class, tags={ "DataUnits", })
   @io.swagger.annotations.ApiResponses(value = { 
       @io.swagger.annotations.ApiResponse(code = 200, message = "Successful operation.", response = InlineResponse2003.class),
       
       @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = InlineResponse2003.class) })
   public Response getPersonalData(@ApiParam(value = "The requester identifier number",required=true) @PathParam("requester_id") String requesterId
,@ApiParam(value = "query encapsulating the set of data units and the personal ids of the people whose personal data is wanted to be received.") @QueryParam("query") String query
,@Context SecurityContext securityContext)
   throws NotFoundException {
       return delegate.getPersonalData(requesterId,query,securityContext);
   }
}
