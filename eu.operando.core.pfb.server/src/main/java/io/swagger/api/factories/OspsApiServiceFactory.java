package io.swagger.api.factories;

import io.swagger.api.OspsApiService;
import io.swagger.api.impl.OspsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-03-31T13:37:20.076Z")
public class OspsApiServiceFactory {

   private final static OspsApiService service = new OspsApiServiceImpl();

   public static OspsApiService getOspsApi()
   {
      return service;
   }
}
