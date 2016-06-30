package io.swagger.api.factories;

import io.swagger.api.PersonaldataApiService;
import io.swagger.api.impl.PersonaldataApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-10T09:36:15.284Z")
public class PersonaldataApiServiceFactory {

   private final static PersonaldataApiService service = new PersonaldataApiServiceImpl();

   public static PersonaldataApiService getPersonaldataApi()
   {
      return service;
   }
}
