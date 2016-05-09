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

import io.swagger.model.InlineResponse200;
import io.swagger.model.Error;
import io.swagger.model.AccessLevel;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-03-16T08:19:16.233Z")
public abstract class AccessLevelApiService {
  
      public abstract Response createAccessLevel(AccessLevel accessLevel,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response deleteAccessLevel(String accessLevelId,String accessLevelId2,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response getAccessLevel(String accessLevelId,String accessLevelId2,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response getAccessLevels(SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response updateAccessLevel(String accessLevelId,AccessLevel accessLevel,SecurityContext securityContext)
      throws NotFoundException;
  
}
