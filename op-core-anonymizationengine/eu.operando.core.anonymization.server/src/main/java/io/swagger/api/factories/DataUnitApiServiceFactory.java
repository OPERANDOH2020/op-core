package io.swagger.api.factories;

import io.swagger.api.DataUnitApiService;
import io.swagger.api.impl.DataUnitApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-10T09:36:15.284Z")
public class DataUnitApiServiceFactory {

   private final static DataUnitApiService service = new DataUnitApiServiceImpl();

   public static DataUnitApiService getDataUnitApi()
   {
      return service;
   }
}
