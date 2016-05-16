package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.ComputationResult;
import java.math.BigDecimal;
import io.swagger.model.PrivacyRegulation;
import java.util.List;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-13T11:11:54.739Z")
public abstract class RegulationsApiService {
  
      public abstract Response regulationsRegIdPost(BigDecimal regId,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response regulationsRegIdPut(BigDecimal regId,List<PrivacyRegulation> regulation,SecurityContext securityContext)
      throws NotFoundException;
  
}
