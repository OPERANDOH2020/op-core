package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.McApiService;
import io.swagger.api.factories.McApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.RequestHeader;
import java.util.Date;
import io.swagger.model.OSPJobs;
import java.math.BigDecimal;
import io.swagger.model.OSPJobsSubscriptionRequest;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/mc")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the mc API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-07-06T21:13:34.473Z")
public class McApi  {
   private final McApiService delegate = McApiServiceFactory.getMcApi();

    @GET
    @Path("/job/subscribe")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Allows to subscribe to a job for its execution. <br> <ul> <li>If no start date is specified it start inmediately. </li> <li>If no end date is specified it executes forever. </li> <li>If no periodicity is specified it executes just one time.  </li> </ul>", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response mcJobSubscribeGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to be subscribed",required=true) @QueryParam("jobId") String jobId,
        @ApiParam(value = "Identification of the job subscribtion starting date") @QueryParam("startDate") Date startDate,
        @ApiParam(value = "Identification of the job subscribtion end date") @QueryParam("endDate") Date endDate,
        @ApiParam(value = "Identification of job execution periodicity -> yearly, monthly, weekly, ...") @QueryParam("period") String period,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.mcJobSubscribeGet(requestHeader,jobId,startDate,endDate,period,securityContext);
    }
    @GET
    @Path("/job/unSubscribe")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "unSubscribe to a job for its execution. <br>", response = void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = void.class) })
    public Response mcJobUnSubscribeGet(
        @ApiParam(value = "Identification of the requesting end user" ,required=true) RequestHeader requestHeader,
        @ApiParam(value = "Identification of the job to be unSubscribed",required=true) @QueryParam("jobId") String jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.mcJobUnSubscribeGet(requestHeader,jobId,securityContext);
    }
    @GET
    @Path("/osp/{osp_id}/jobs")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Function that returns all the jobs avaliable to the requester detailing those suscribed", response = OSPJobs.class, tags={ "Management Console",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "the osp_id exists", response = OSPJobs.class),
        @io.swagger.annotations.ApiResponse(code = 404, message = "the osp_id does not exist", response = OSPJobs.class) })
    public Response mcOspOspIdJobsGet(
        @ApiParam(value = "The unqiue regulation ID for the new regulation created.",required=true) @PathParam("osp_id") BigDecimal ospId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.mcOspOspIdJobsGet(ospId,securityContext);
    }
    @GET
    @Path("/osp/{osp_id}/jobs/{job_id}/subscription")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Function that allows to modify the subscription to a job", response = OSPJobs.class, tags={ "Management Console",  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "the job subscription", response = OSPJobs.class),
        @io.swagger.annotations.ApiResponse(code = 404, message = "the osp_id or job_id does not exist", response = OSPJobs.class) })
    public Response mcOspOspIdJobsJobIdSubscriptionGet(
        @ApiParam(value = "The unique ID for the osp.",required=true) @PathParam("osp_id") BigDecimal ospId,
        @ApiParam(value = "The unique ID for the job.",required=true) @PathParam("job_id") BigDecimal jobId,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.mcOspOspIdJobsJobIdSubscriptionGet(ospId,jobId,securityContext);
    }
    @PUT
    @Path("/osp/{osp_id}/jobs/{job_id}/subscription")
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "Function that allows to modify the subscription to a job", response = void.class, tags={ "Management Console" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "the job subscription added/modified", response = void.class),
        @io.swagger.annotations.ApiResponse(code = 404, message = "the osp_id or job_id does not exist", response = void.class) })
    public Response mcOspOspIdJobsJobIdSubscriptionPut(
        @ApiParam(value = "The unique ID for the osp.",required=true) @PathParam("osp_id") BigDecimal ospId,
        @ApiParam(value = "The unique ID for the job.",required=true) @PathParam("job_id") BigDecimal jobId,
        @ApiParam(value = "user to add to the system" ,required=true) OSPJobsSubscriptionRequest oSPJobsSubscriptionRequest,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.mcOspOspIdJobsJobIdSubscriptionPut(ospId,jobId,oSPJobsSubscriptionRequest,securityContext);
    }
}
