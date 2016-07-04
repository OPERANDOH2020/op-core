package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.AcApiService;
import io.swagger.api.factories.AcApiServiceFactory;

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

@Path("/ac")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the ac API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-07-03T20:04:20.000Z")
public class AcApi  {
   private final AcApiService delegate = AcApiServiceFactory.getAcApi();

    @GET
    @Path("/event/change")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It returns the list of jobs subscribed for that user", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response acEventChangeGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.acEventChangeGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/job/change")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It returns the list of jobs subscribed for that user", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response acJobChangeGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.acJobChangeGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/job/info/load")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It forces the information loading for a given job", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response acJobInfoLoadGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.acJobInfoLoadGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/job/info/update")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It forces the information updating for a given job, it only load last updates", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response acJobInfoUpdateGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.acJobInfoUpdateGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/job/list")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It returns the list of jobs available for that user", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response acJobListGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.acJobListGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/job/status")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to get the status of a job", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response acJobStatusGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.acJobStatusGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/user/rights/access")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It returns the list of jobs subscribed for that user", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response acUserRightsAccessGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.acUserRightsAccessGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/user/rights/execution")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It returns the list of jobs subscribed for that user", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response acUserRightsExecutionGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.acUserRightsExecutionGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/user/subscribed")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It returns the list of jobs subscribed for that user", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response acUserSubscribedGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.acUserSubscribedGet(requestHeader,jobId,securityContext);
    }
}
