package io.swagger.api.factories;

import io.swagger.api.OffersApiService;
import io.swagger.api.impl.OffersApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-03-31T13:37:20.076Z")
public class OffersApiServiceFactory {

   private final static OffersApiService service = new OffersApiServiceImpl();

   public static OffersApiService getOffersApi()
   {
      return service;
   }
}
