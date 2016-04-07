package io.swagger.api.factories;

import io.swagger.api.LogApiService;
import io.swagger.api.impl.LogApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-07T07:27:45.925Z")
public class LogApiServiceFactory {

   private final static LogApiService service = new LogApiServiceImpl();

   public static LogApiService getLogApi()
   {
      return service;
   }
}
