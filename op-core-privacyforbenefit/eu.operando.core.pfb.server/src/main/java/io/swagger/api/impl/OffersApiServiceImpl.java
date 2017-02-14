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
import io.swagger.api.NotFoundException;
import io.swagger.api.OffersApiService;
import io.swagger.model.Deal;
import io.swagger.model.Offer;
import io.swagger.model.OfferRequest;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-27T11:58:50.874Z")
public class OffersApiServiceImpl extends OffersApiService {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
    
    @Override
    public Response createOffer(OfferRequest offer, SecurityContext securityContext)
    throws NotFoundException {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	//dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
    	Date date = offer.getExpirationDate();
    	String strDate = dateFormat.format(date);
    	String strInsert = "INSERT INTO operando_privacyforbenefitdb.OFFER (OSP_ID,TITLE,DESCRIPTION,SERVICE_WEBSITE,IS_ENABLED,OSP_CALLBACK_URL,EXPIRATION_DATE) VALUES ('"+offer.getOspId()+"','"+offer.getTitle()+"','"+offer.getDescription()+"','"+offer.getServiceWebsite()+"','"+offer.getIsEnabled()+"','"+offer.getOspCallbackUrl()+"','"+strDate+"')";
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
    
    @Override
    public Response getOfferStatus(String offerId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "The offer status")).build();
    }
    
    /* (non-Javadoc)
     * @see io.swagger.api.OffersApiService#getOffers(java.lang.String, java.lang.String, java.lang.String, java.lang.String, javax.ws.rs.core.SecurityContext)
     */
    @Override
    public Response getOffers(String ospId, String serviceWebsite, String isEnabled, SecurityContext securityContext)
    throws NotFoundException {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
		String strSelect = "select * from operando_privacyforbenefitdb.OFFER";    
        StringBuffer strBufferSelect = new StringBuffer(strSelect);        
        boolean boolAnd = false;        
        Offer offer;        
        ArrayList<Offer> offersArray = new ArrayList<Offer> ();
        if (!((ospId=="") && (serviceWebsite=="") && (isEnabled==""))){
        	strBufferSelect.append(" WHERE ");
        	if ((ospId!=null)&(!ospId.equals(""))){ 
        		if (boolAnd)
        			strBufferSelect.append(" AND ");
        		strBufferSelect.append("OSP_ID= '"+ospId+"'");
        		boolAnd = true;
        	}
        	if ((serviceWebsite!=null)&(!serviceWebsite.equals(""))){ 
        		if (boolAnd)
        			strBufferSelect.append(" AND ");
        		strBufferSelect.append("SERVICE_WEBSITE='"+serviceWebsite+"'");
        		boolAnd = true;
        	}
        	int intIsEnabled;
        	if ((isEnabled!=null)&(!isEnabled.equals(""))){
        		intIsEnabled = new Integer(isEnabled).intValue();
        		if (boolAnd)
        			strBufferSelect.append(" AND ");
        		strBufferSelect.append("IS_ENABLED= "+intIsEnabled);
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
    			 offer = new Offer();
    			 offer.setId(resultSet.getString("ID"));
    			 offer.setOspId(resultSet.getString("OSP_ID"));
    			 offer.setTitle(resultSet.getString("TITLE"));
    			 offer.setDescription(resultSet.getString("DESCRIPTION"));
    			 offer.setServiceWebsite(resultSet.getString("SERVICE_WEBSITE"));
    			 offer.setOspCallbackUrl(resultSet.getString("OSP_CALLBACK_URL"));
    			 if ((resultSet.getString("EXPIRATION_DATE")!=null) && (resultSet.getString("EXPIRATION_DATE")!="")){
    				 try {
						date=dateFormat.parse(resultSet.getString("EXPIRATION_DATE"));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}    				 
    			 }
    			 offer.setExpirationDate(date);
    			 offer.setIsEnabled(resultSet.getInt("IS_ENABLED"));
    			 
    			 
    			     			 
    			 offersArray.add(offer);
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
    	return Response.ok().entity(offersArray).build();
    }        
    
    
    @Override
    public Response updateOffer(String offerId, OfferRequest offer, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "The offer has been updated succesfully")).build();
    }
    
}
