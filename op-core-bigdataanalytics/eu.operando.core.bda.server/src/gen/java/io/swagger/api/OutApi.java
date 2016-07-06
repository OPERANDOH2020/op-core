package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.OutApiService;
import io.swagger.api.factories.OutApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.RequestHeader;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/out")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the out API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-07-06T21:13:34.473Z")
public class OutApi  {
   private final OutApiService delegate = OutApiServiceFactory.getOutApi();

    @GET
    @Path("/report/download")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "provides a link to download a report", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response outReportDownloadGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.outReportDownloadGet(requestHeader,jobId,securityContext);
    }
}
