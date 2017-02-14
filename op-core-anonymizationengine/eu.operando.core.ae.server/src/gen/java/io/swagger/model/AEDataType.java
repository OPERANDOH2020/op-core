package io.swagger.model;

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