package io.swagger.api.factories;

import io.swagger.api.OutApiService;
import io.swagger.api.impl.OutApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-07-03T20:04:20.000Z")
public class OutApiServiceFactory {

   private final static OutApiService service = new OutApiServiceImpl();

   public static OutApiService getOutApi()
   {
      return service;
   }
}
