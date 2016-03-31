package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.Error;
import io.swagger.model.InlineResponse200;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-03-31T13:37:20.076Z")
public abstract class DealsApiService {
  
      public abstract Response cancelDeal(String dealId,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response dealsDealIdGet(String dealId,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response offerAccepted(String dealId,String ospId,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response requestOffer(String userId,String offerId,SecurityContext securityContext)
      throws NotFoundException;
  
}
