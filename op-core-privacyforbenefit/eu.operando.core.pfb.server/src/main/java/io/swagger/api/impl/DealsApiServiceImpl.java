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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.DealsApiService;
import io.swagger.api.NotFoundException;
import io.swagger.model.Deal;
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
    public Response getDealById(String dealId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response createDeal(DealRequest deal, SecurityContext securityContext)
    throws NotFoundException {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	//dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
    	Date date = Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime();
    	String strDate = dateFormat.format(date);    	
    	String strInsert = "INSERT INTO operando_privacyforbenefitdb.DEAL (USER_ID,OFFER_ID,CREATED_AT,CANCELED_AT) VALUES ('"+deal.getUserId()+"','"+deal.getOfferId()+"','"+strDate+"',null)";
    	    	
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
		finally{
			try {					
				statement.close();
				connection.close();
			} catch (SQLException e) {			
				e.printStackTrace();
			}
		}		 				          		 
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "The deal has been created successfully!")).build();
    }
	@Override
    public Response getDeals(String offerId, String userId, String createdFrom, String createdTo, String canceled, SecurityContext securityContext)
    throws NotFoundException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String strSelect = "select * from operando_privacyforbenefitdb.DEAL";    
        StringBuffer strBufferSelect = new StringBuffer(strSelect);        
        boolean boolAnd = false;        
        Deal deal;        
        ArrayList<Deal> dealsArray = new ArrayList<Deal> ();
        if (!((offerId=="") && (userId=="") && (createdFrom=="") && (createdTo=="") && (canceled==""))){
        	strBufferSelect.append(" WHERE ");
        	if ((offerId!=null)&(!offerId.equals(""))){ 
        		if (boolAnd)
        			strBufferSelect.append(" AND ");
        		strBufferSelect.append("OFFER_ID="+offerId+"");
        		boolAnd = true;
        	}
        	if ((userId!=null)&(!userId.equals(""))){ 
        		if (boolAnd)
        			strBufferSelect.append(" AND ");
        		strBufferSelect.append("USER_ID="+userId+"");
        		boolAnd = true;
        	}
        	if ((createdFrom!=null)&(!createdFrom.equals(""))){
        		if (boolAnd)
        			strBufferSelect.append(" AND ");
        		strBufferSelect.append("CREATED_AT>= '"+createdFrom+"'");
        		boolAnd = true;        		        		
        	}
        	if ((createdTo!=null)&(!createdTo.equals(""))){
        		if (boolAnd)
        			strBufferSelect.append(" AND ");
        		strBufferSelect.append("CREATED_AT<= '"+createdTo+"'");
        		boolAnd = true;
        	}
        	if ((canceled!=null)&(!canceled.equals(""))){
        		if (boolAnd)
        			strBufferSelect.append(" AND ");
        		if (canceled.equalsIgnoreCase("true"))
        			strBufferSelect.append("CANCELED_AT IS NOT NULL");
        		else
        			strBufferSelect.append("CANCELED_AT IS NULL");
        		boolAnd = true;
        	}
        	        	        		    	
        	strSelect = strBufferSelect.toString();
        	System.out.println(strSelect);
        }
        	
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		connection = DriverManager
    			    .getConnection("jdbc:mysql://localhost/operando_logdb?"
    			        + "user=root&password=root");
    		 statement = connection.createStatement();
    		 resultSet = statement
    		          .executeQuery(strSelect);
    		 Date date = null;
    		 while (resultSet.next()){
    			 deal = new Deal();
    			 deal.setId(resultSet.getString("ID"));
    			 deal.setOfferId(resultSet.getString("OFFER_ID"));
    			 deal.setUserId(resultSet.getString("USER_ID"));
    			 if ((resultSet.getString("CREATED_AT")!=null) && (resultSet.getString("CREATED_AT")!="")){
    				 try {
						date=dateFormat.parse(resultSet.getString("CREATED_AT"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				 deal.setCreatedAt(date);
    			 }
    			 if ((resultSet.getString("CANCELED_AT")!=null) && (resultSet.getString("CANCELED_AT")!="")){
    				 try {
						date=dateFormat.parse(resultSet.getString("CANCELED_AT"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				 deal.setCanceledAt(date);
    			 }    			 
    			 dealsArray.add(deal);
    		 }		 		 
    	}	
    	catch (ClassNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}        		
    	catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	finally{
    		try {
    			resultSet.close();		
    			statement.close();
    			connection.close();
    		} catch (SQLException e) {			
    			e.printStackTrace();
    		}
    	}
    	return Response.ok().entity(dealsArray).build();
        }
        
    //}
    @Override
    public Response offerAccepted(String dealId, String ospId, String offerId, SecurityContext securityContext)
    throws NotFoundException {
    	return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
}
