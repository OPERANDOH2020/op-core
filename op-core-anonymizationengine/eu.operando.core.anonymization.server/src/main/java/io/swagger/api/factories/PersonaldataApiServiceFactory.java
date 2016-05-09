/*******************************************************************************
 *  * Copyright (c) 2016 {TECNALIA}.
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the The MIT License (MIT).
 *  * which accompanies this distribution, and is available at
 *  * http://opensource.org/licenses/MIT
 *  *
 *  * Contributors:
 *  *    Gorka Mikel Echevarría {TECNALIA}
 *  * Initially developed in the context of OPERANDO EU project www.operando.eu
 *******************************************************************************/
package io.swagger.api.factories;

import io.swagger.api.PersonaldataApiService;
import io.swagger.api.impl.PersonaldataApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-03-16T08:19:16.233Z")
public class PersonaldataApiServiceFactory {

   private final static PersonaldataApiService service = new PersonaldataApiServiceImpl();

   public static PersonaldataApiService getPersonaldataApi()
   {
      return service;
   }
}
