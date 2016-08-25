package io.swagger.api.factories;

import io.swagger.api.OspPolicyEvaluatorApiService;
import io.swagger.api.impl.OspPolicyEvaluatorApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class OspPolicyEvaluatorApiServiceFactory {

   private final static OspPolicyEvaluatorApiService service = new OspPolicyEvaluatorApiServiceImpl();

   public static OspPolicyEvaluatorApiService getOspPolicyEvaluatorApi()
   {
      return service;
   }
}
