package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.Error;
import io.swagger.model.AccessLevel;
import io.swagger.model.InlineResponse2001;
import io.swagger.model.DataUnitRequest;
import io.swagger.model.InlineResponse2002;
import io.swagger.model.InlineResponse200;
import io.swagger.model.DataUnit;
import java.util.List;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-10T09:36:15.284Z")
public abstract class DataUnitApiService {
  
      public abstract Response addAccessLevelsByDataUnitByPersonAndRequester(String dataunitId,String personId,String requesterId,AccessLevel accessLevel,SecurityContext securityContext)
      throws NotFoundException;
  
      public abstract Response createDataUnit(DataUnitRequest dataUnit,SecurityContext securityContext)
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
  
      public abstract Response updateDataUnit(String dataunitId,DataUnitRequest dataUnit,SecurityContext securityContext)
      throws NotFoundException;
  
}
