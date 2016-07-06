package io.swagger.api.factories;

import io.swagger.api.AcApiService;
import io.swagger.api.impl.AcApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-07-06T21:13:34.473Z")
public class AcApiServiceFactory {

   private final static AcApiService service = new AcApiServiceImpl();

   public static AcApiService getAcApi()
   {
      return service;
   }
}
