package io.swagger.api.factories;

import io.swagger.api.OspComplianceCheckerApiService;
import io.swagger.api.impl.OspComplianceCheckerApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class OspComplianceCheckerApiServiceFactory {

   private final static OspComplianceCheckerApiService service = new OspComplianceCheckerApiServiceImpl();

   public static OspComplianceCheckerApiService getOspComplianceCheckerApi()
   {
      return service;
   }
}
