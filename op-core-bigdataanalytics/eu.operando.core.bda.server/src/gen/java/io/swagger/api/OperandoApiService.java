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

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-23T18:47:18.805Z")
public abstract class OperandoApiService {
      public abstract Response operandoBdaAcEventChangeGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response operandoBdaAcJobChangeGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response operandoBdaAcJobInfoLoadGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response operandoBdaAcJobInfoUpdateGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response operandoBdaAcJobListGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response operandoBdaAcJobStatusGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response operandoBdaAcUserRightsAccessGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response operandoBdaAcUserRightsExecutionGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response operandoBdaAcUserSubscribedGet(RequestHeader requestHeader,String jobId,SecurityContext securityContext)
      throws NotFoundException;
}
