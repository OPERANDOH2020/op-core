package io.swagger.api.factories;

import io.swagger.api.OperandoApiService;
import io.swagger.api.impl.OperandoApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-23T18:47:18.805Z")
public class OperandoApiServiceFactory {

   private final static OperandoApiService service = new OperandoApiServiceImpl();

   public static OperandoApiService getOperandoApi()
   {
      return service;
   }
}
