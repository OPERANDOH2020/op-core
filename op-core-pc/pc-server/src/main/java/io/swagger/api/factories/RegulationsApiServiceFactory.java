package io.swagger.api.factories;

import io.swagger.api.RegulationsApiService;
import io.swagger.api.impl.RegulationsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class RegulationsApiServiceFactory {

   private final static RegulationsApiService service = new RegulationsApiServiceImpl();

   public static RegulationsApiService getRegulationsApi()
   {
      return service;
   }
}
