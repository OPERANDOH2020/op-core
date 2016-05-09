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

import io.swagger.model.Error;
import io.swagger.model.AccessLevel;
import io.swagger.model.DataUnit;
import io.swagger.model.InlineResponse2001;
import io.swagger.model.InlineResponse2002;
import io.swagger.model.InlineResponse200;
import java.util.List;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-03-16T08:19:16.233Z")
public abstract class DataUnitApiService {
  
      public abstract Response addAccessLevelsByDataUnitByPersonAndRequester(String dataunitId,String personId,String requesterId,AccessLevel accessLevel,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response createDataUnit(DataUnit dataUnit,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response deleteDataUnit(String dataunitId,String dataunitId2,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response getAccessLevelsByDataUnit(String dataunitId,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response getAccessLevelsByDataUnitAndCountry(String dataunitId,String countryId,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response getAccessLevelsByDataUnitAndPA(String dataunitId,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response getAccessLevelsByDataUnitPersonAndRequester(String dataunitId,String personId,String requesterId,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response getDataUnit(String dataunitId,String dataunitId2,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response getDataUnits(SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response setAccessLevelsByDataUnit(String dataunitId,List<AccessLevel> accesslevels,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response updateAccessLevelsByDataUnit(String dataunitId,List<AccessLevel> accesslevels,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response updateAccessLevelsByDataUnitByPersonAndRequester(String dataunitId,String personId,String requesterId,AccessLevel accessLevel,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response updateDataUnit(String dataunitId,DataUnit dataUnit,SecurityContext securityContext)
      throws NotFoundException;
  
}
