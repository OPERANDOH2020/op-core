package io.swagger.api.factories;

import io.swagger.api.AcApiService;
import io.swagger.api.impl.AcApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-07-03T20:04:20.000Z")
public class AcApiServiceFactory {

   private final static AcApiService service = new AcApiServiceImpl();

   public static AcApiService getAcApi()
   {
      return service;
   }
}
