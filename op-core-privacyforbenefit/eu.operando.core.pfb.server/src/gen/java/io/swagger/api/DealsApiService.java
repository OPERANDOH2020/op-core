package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.Error;
import io.swagger.model.InlineResponse200;
import io.swagger.model.DealRequest;
import io.swagger.model.InlineResponse2001;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-10T08:21:47.616Z")
public abstract class DealsApiService {
      public abstract Response cancelDeal(String dealId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response createDeal(DealRequest deal,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response getDealById(String dealId,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response getDeals(String offerId,String userId,String createdFrom,String createdTo,String canceled,SecurityContext securityContext)
      throws NotFoundException;
      public abstract Response offerAccepted(String dealId,String ospId,String offerId,SecurityContext securityContext)
      throws NotFoundException;
}
