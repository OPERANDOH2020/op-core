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
package io.swagger.api.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import io.swagger.api.OffersApiService;
import io.swagger.model.Offer;
import io.swagger.model.OfferRequest;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-27T11:58:50.874Z")
public class OffersApiServiceImpl extends OffersApiService {
    
    @Override
    public Response createOffer(OfferRequest offer, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "The offer has been created succesfully")).build();
    }
    
    @Override
    public Response getOfferStatus(String offerId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "The offer status")).build();
    }
    
    @Override
    public Response getOffers(String websiteUrl, String websiteId, String ospId, String userId, SecurityContext securityContext)
    throws NotFoundException {
    	System.out.println("websiteUrl "+websiteUrl);
        // for demo purposes,it returns an array with the offers related to facebook
    	Offer offer = new Offer ();
    	offer.setId("1");
    	offer.setDescription("Get a 5€ coupon for your first purchase if you log into the application via facebook.");
    	offer.setIsEnabled(true);
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
    	String strExpirationDate = "31/12/2016 23:59:59";
    	try {
			offer.setExpirationDate(dateFormat.parse(strExpirationDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	offer.setOspCallbackUrl("https://www.ospname.com/");
    	offer.setOspId("ospid");
    	offer.setServiceWebsite("https://www.ospname.com/");
    	offer.setTitle("Get a 5€ coupon for your first purchase");
    	ArrayList offersArray = new ArrayList ();
    	offersArray.add(offer);
        return Response.ok().entity(offersArray).build();
    }
    
    @Override
    public Response updateOffer(String offerId, OfferRequest offer, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "The offer has been updated succesfully")).build();
    }
    
}
