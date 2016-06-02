/*******************************************************************************
 *  * Copyright (c) 2016 {TECNALIA}.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the The MIT License (MIT).
 *  * which accompanies this distribution, and is available at
 *  * http://opensource.org/licenses/MIT
 *  *
 *  * Contributors:
 *  *    Gorka Mikel Echevarría {TECNALIA}
 *  * Initially developed in the context of OPERANDO EU project www.operando.eu
 *******************************************************************************/
package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.OfferRequest;
import io.swagger.model.InlineResponse2001;
import io.swagger.model.Error;
import io.swagger.model.InlineResponse2003;
import io.swagger.model.InlineResponse2002;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-27T11:58:50.874Z")
public abstract class OffersApiService {
  
      public abstract Response createOffer(OfferRequest offer,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response getOfferStatus(String offerId,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response getOffers(String websiteUrl,String websiteId,String ospId,String userId,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response updateOffer(String offerId,OfferRequest offer,SecurityContext securityContext)
      throws NotFoundException;
  
}
