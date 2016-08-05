package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

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

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-07-06T21:13:34.473Z")
public class McApiServiceImpl extends McApiService {
    @Override
    public Response mcJobSubscribeGet(RequestHeader requestHeader, String jobId, Date startDate, Date endDate, String period, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response mcJobUnSubscribeGet(RequestHeader requestHeader, String jobId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response mcOspOspIdJobsGet(BigDecimal ospId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response mcOspOspIdJobsJobIdSubscriptionGet(BigDecimal ospId, BigDecimal jobId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response mcOspOspIdJobsJobIdSubscriptionPut(BigDecimal ospId, BigDecimal jobId, OSPJobsSubscriptionRequest oSPJobsSubscriptionRequest, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
