package io.swagger.api.factories;

import eu.operando.core.bda.server.McApiServiceImpl;
import io.swagger.api.McApiService;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-07-06T21:13:34.473Z")
public class McApiServiceFactory {

   private final static McApiService service = new McApiServiceImpl();

   public static McApiService getMcApi()
   {
      return service;
   }
}
