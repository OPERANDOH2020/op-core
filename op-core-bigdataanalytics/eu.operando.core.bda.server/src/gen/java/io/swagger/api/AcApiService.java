package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.RequestHeader;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-07-03T20:04:20.000Z")
public abstract class AcApiService {
      public abstract Response acEventChangeGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response acJobChangeGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response acJobInfoLoadGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response acJobInfoUpdateGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response acJobListGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response acJobStatusGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response acUserRightsAccessGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response acUserRightsExecutionGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response acUserSubscribedGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
}
