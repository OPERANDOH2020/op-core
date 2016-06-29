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
import java.util.Date;
import java.util.TimeZone;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.NotFoundException;
import io.swagger.api.OspsApiService;
import io.swagger.model.Deal;
import io.swagger.model.OSPRequest;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-27T11:58:50.874Z")
public class OspsApiServiceImpl extends OspsApiService {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
    
    @Override
    public Response getAcceptedDealsByOSP(String ospId, SecurityContext securityContext)
    throws NotFoundException {
    	System.out.println("ospId "+ospId);
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String strSelect = "select * from operando_privacyforbenefitdb.DEAL";    
        StringBuffer strBufferSelect = new StringBuffer(strSelect);                       
        Deal deal;        
        ArrayList<Deal> dealsArray = new ArrayList<Deal> ();
        if (!(ospId=="")){
        	strBufferSelect.append(" WHERE ");
        	if ((ospId!=null)&(!ospId.equals(""))){         		
        		strBufferSelect.append("OFFER_ID IN (SELECT ID FROM OFFER WHERE OSP_ID='"+ospId+"')");        		
        	}        	        	        		    	
        	strSelect = strBufferSelect.toString();
        	System.out.println(strSelect);
        }
        	
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		connection = DriverManager
    			    .getConnection("jdbc:mysql://localhost/operando_privacyforbenefitdb?"
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
    
    @Override
    public Response registerOSP(OSPRequest osp, SecurityContext securityContext)
    		throws NotFoundException {    	
    	String strInsert = "INSERT INTO operando_privacyforbenefitdb.OSP (NAME,DESCRIPTION,OSP_WEBSITE) VALUES ('"+osp.getName()+"','"+osp.getDescription()+"','"+osp.getOspWebsite()+"')";
    	System.out.println(strInsert);
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
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "The offer has been created successfully!")).build();
    }
    
}
