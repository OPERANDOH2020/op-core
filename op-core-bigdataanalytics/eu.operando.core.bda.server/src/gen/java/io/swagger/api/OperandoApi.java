package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.OperandoApiService;
import io.swagger.api.factories.OperandoApiServiceFactory;

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

@Path("/operando")

@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the operando API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-23T18:47:18.805Z")
public class OperandoApi  {
   private final OperandoApiService delegate = OperandoApiServiceFactory.getOperandoApi();

    @GET
    @Path("/bda/ac/event/change")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It returns the list of jobs subscribed for that user", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response operandoBdaAcEventChangeGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.operandoBdaAcEventChangeGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/bda/ac/job/change")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It returns the list of jobs subscribed for that user", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response operandoBdaAcJobChangeGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.operandoBdaAcJobChangeGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/bda/ac/job/info/load")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It forces the information loading for a given job", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response operandoBdaAcJobInfoLoadGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.operandoBdaAcJobInfoLoadGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/bda/ac/job/info/update")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It forces the information updating for a given job, it only load last updates", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response operandoBdaAcJobInfoUpdateGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.operandoBdaAcJobInfoUpdateGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/bda/ac/job/list")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It returns the list of jobs available for that user", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response operandoBdaAcJobListGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.operandoBdaAcJobListGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/bda/ac/job/status")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to get the status of a job", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response operandoBdaAcJobStatusGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.operandoBdaAcJobStatusGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/bda/ac/user/rights/access")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It returns the list of jobs subscribed for that user", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response operandoBdaAcUserRightsAccessGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.operandoBdaAcUserRightsAccessGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/bda/ac/user/rights/execution")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It returns the list of jobs subscribed for that user", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response operandoBdaAcUserRightsExecutionGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.operandoBdaAcUserRightsExecutionGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/bda/ac/user/subscribed")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "It returns the list of jobs subscribed for that user", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response operandoBdaAcUserSubscribedGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to get the status about",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.operandoBdaAcUserSubscribedGet(requestHeader,jobId,securityContext);
    }
}
