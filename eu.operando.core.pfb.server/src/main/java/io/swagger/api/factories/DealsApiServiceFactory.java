package io.swagger.api.factories;

import io.swagger.api.DealsApiService;
import io.swagger.api.impl.DealsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-03-31T13:37:20.076Z")
public class DealsApiServiceFactory {

   private final static DealsApiService service = new DealsApiServiceImpl();

   public static DealsApiService getDealsApi()
   {
      return service;
   }
}
