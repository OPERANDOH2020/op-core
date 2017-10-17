package eu.operando.core.ldb.server.api;

import eu.operando.core.ldb.server.model.*;
import eu.operando.core.ldb.server.api.LogApiService;
import eu.operando.core.ldb.server.api.factories.LogApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import eu.operando.core.ldb.server.model.LogRequestExt;

import java.util.List;
import eu.operando.core.ldb.server.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/log")


@io.swagger.annotations.Api(description = "the log API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-10-17T16:12:53.169+02:00")
public class LogApi  {
   private final LogApiService delegate = LogApiServiceFactory.getLogApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Inserts received data to the database.", notes = "Inserts received data to the database by using Log4j.", response = String.class, tags={ "Log", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Unexpected error", response = String.class) })
    public Response log(@ApiParam(value = "The request data in JSON format to be inserted in the database by using Log4j" ,required=true) LogRequestExt request
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.log(request,securityContext);
    }
    @POST
    @Path("/logTicket")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Inserts received data to the database.", notes = "Inserts received data to the database by using Log4j.", response = void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "service-ticket")
    }, tags={ "Log", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Succesful operation. ", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 400, message = "The client inputs to the operation are incorrect or invalid. The caller should check the inputs are valid based upon the returned error message. ", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Error. The service ticket failed to validate. ", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 415, message = "The operation consumes json messages. Please, check that the sent message is in json format. ", response = void.class) })
    public Response logTicket(@ApiParam(value = "The request data in JSON format to be inserted in the database by using Log4j" ,required=true) LogRequestExt request
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.logTicket(request,securityContext);
    }
}
