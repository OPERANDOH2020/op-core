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

import io.swagger.model.OfferRequest;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-27T11:58:50.874Z")
public abstract class OffersApiService {

	public abstract Response createOffer(OfferRequest offer, SecurityContext securityContext) throws NotFoundException;

	public abstract Response getOfferStatus(String offerId, SecurityContext securityContext) throws NotFoundException;

	public abstract Response getOffers(String ospId, String serviceWebsite, String isEnabled,
			SecurityContext securityContext) throws NotFoundException;

	public abstract Response updateOffer(String offerId, OfferRequest offer, SecurityContext securityContext)
			throws NotFoundException;

}
