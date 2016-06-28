package io.swagger.api.factories;

import io.swagger.api.PolicyComputerApiService;
import io.swagger.api.impl.PolicyComputerApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class PolicyComputerApiServiceFactory {

   private final static PolicyComputerApiService service = new PolicyComputerApiServiceImpl();

   public static PolicyComputerApiService getPolicyComputerApi()
   {
      return service;
   }
}
