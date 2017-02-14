package io.swagger.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.model.SearchRequest;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-10T09:36:15.284Z")
public abstract class PersonaldataApiService {      
      public abstract Response getPersonalData(SearchRequest searchRequest,SecurityContext securityContext) throws NotFoundException;
  
}
