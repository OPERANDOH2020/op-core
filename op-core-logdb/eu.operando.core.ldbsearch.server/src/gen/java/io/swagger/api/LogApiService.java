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
package io.swagger.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-06T10:10:57.937Z")
public abstract class LogApiService {
	public abstract Response getLogs(String dateFrom, String dateTo, String logLevel, String requesterType,
			String requesterId, String logPriority, String title, String keyWords, String logType,
			String affectedUserId, SecurityContext securityContext) throws NotFoundException;

	public Response getLogsExt(String dateFrom, String dateTo, String logLevel, String requesterType,
			String requesterId, String logPriority, String title, String keyWords, String logType,
			String affectedUserId, String ospId, SecurityContext securityContext) throws NotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
