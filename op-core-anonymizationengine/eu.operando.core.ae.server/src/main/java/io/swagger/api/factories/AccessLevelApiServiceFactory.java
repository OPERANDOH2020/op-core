package io.swagger.api.factories;

import io.swagger.api.AccessLevelApiService;
import io.swagger.api.impl.AccessLevelApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-10T09:36:15.284Z")
public class AccessLevelApiServiceFactory {

   private final static AccessLevelApiService service = new AccessLevelApiServiceImpl();

   public static AccessLevelApiService getAccessLevelApi()
   {
      return service;
   }
}
