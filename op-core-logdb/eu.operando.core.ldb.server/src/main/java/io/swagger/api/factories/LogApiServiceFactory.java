package io.swagger.api.factories;

import io.swagger.api.LogApiService;
import io.swagger.api.impl.LogApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-16T12:28:19.935Z")
public class LogApiServiceFactory {

   private final static LogApiService service = new LogApiServiceImpl();

   public static LogApiService getLogApi()
   {
      return service;
   }
}
