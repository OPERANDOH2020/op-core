package io.swagger.api.factories;

import io.swagger.api.OspPolicyComputerApiService;
import io.swagger.api.impl.OspPolicyComputerApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class OspPolicyComputerApiServiceFactory {

   private final static OspPolicyComputerApiService service = new OspPolicyComputerApiServiceImpl();

   public static OspPolicyComputerApiService getOspPolicyComputerApi()
   {
      return service;
   }
}
