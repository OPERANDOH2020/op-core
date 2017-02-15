/*
   	* Copyright (c) 2017 {TECNALIA}.
    * All rights reserved. This program and the accompanying materials
    * are made available under the terms of the The MIT License (MIT).
    * which accompanies this distribution, and is available at
    * http://opensource.org/licenses/MIT
    *
    * Contributors:
    *    Gorka Benguria Elguezabal {TECNALIA}
    *    Gorka Mikel Echevarría {TECNALIA}
    * Initially developed in the context of OPERANDO EU project www.operando.eu
 */
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
