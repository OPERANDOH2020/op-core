package io.swagger.api;

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

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-07-03T20:04:20.000Z")
public abstract class McApiService {
      public abstract Response mcJobSubscribeGet(RequestHeader requestHeader,String jobId,Date startDate,Date endDate,String period,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response mcJobUnSubscribeGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response mcOspOspIdJobsGet(BigDecimal ospId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response mcOspOspIdJobsJobIdSubscriptionPut(BigDecimal ospId,BigDecimal jobId,OSPJobsSubscriptionRequest oSPJobsSubscriptionRequest,SecurityContext securityContext)
      throws NotFoundException;
}