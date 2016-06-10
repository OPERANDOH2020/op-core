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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.DealsApiService;
import io.swagger.api.NotFoundException;
import io.swagger.model.DealRequest;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-27T11:58:50.874Z")
public class DealsApiServiceImpl extends DealsApiService {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
    
    @Override
    public Response cancelDeal(String dealId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response dealsDealIdGet(String dealId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response offerAccepted(String dealId, String ospId, String offerId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response createDeal(DealRequest deal, SecurityContext securityContext)
    throws NotFoundException {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String strActualDate = dateFormat.format(date);
    	String strInsert = "INSERT INTO DEAL (USER_ID,OFFER_ID,CREATED_AT,CANCELED_AT) VALUES ('"+deal.getUserId()+"','"+deal.getOfferId()+"','"+strActualDate+"','')";
    	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager
				    .getConnection("jdbc:mysql://localhost/operando_privacyforbenefitdb?"
				        + "user=root&password=root");
			statement = connection.createStatement();
			statement.execute(strInsert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 		 				          		 
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "The deal has been created successfully!")).build();
    }
    
}
