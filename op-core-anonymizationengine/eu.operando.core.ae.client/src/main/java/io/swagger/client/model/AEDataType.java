/*
   	* Copyright (c) $(year) {TECNALIA}.
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
package io.swagger.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Gorka Mikel Echevarría
 * Abstract class created in order to avoi the deserialization issues that happens when trying to send a data structure
 * containing the arx Datatype class to the search operation.
 *
 */
public class AEDataType {
	@JsonProperty("STRING")
	public static final String STRING = "String";	
	@JsonProperty("DATE")
	public static final String DATE = "Date(dd.MM.yyyy)";
	@JsonProperty("DECIMAL")
	public static final String DECIMAL = "Decimal";
	@JsonProperty("INTEGER")
	public static final String INTEGER = "Integer";	
}