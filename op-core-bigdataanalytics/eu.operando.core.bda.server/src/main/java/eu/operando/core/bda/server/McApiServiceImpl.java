package eu.operando.core.bda.server;

import io.swagger.api.*;
import io.swagger.model.*;

import com.sun.jersey.multipart.FormDataParam;

import java.util.ArrayList;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

import io.swagger.api.NotFoundException;

import java.io.IOException;
import java.io.InputStream;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-07-03T20:04:20.000Z")
public class McApiServiceImpl extends McApiService {
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	@Override
    public Response mcJobSubscribeGet(RequestHeader requestHeader, String jobId, Date startDate, Date endDate, String period, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response mcJobUnSubscribeGet(RequestHeader requestHeader, String jobId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response mcOspOspIdJobsGet(BigDecimal ospId, SecurityContext securityContext)
    throws NotFoundException {
		String strSelect;
		strSelect = composeSqlQuery(ospId);    	
	    
    	//GBE added code to get db information form a properties file
		Properties props;
		props = loadDbProperties();
		
		connection = getDbConnection(props);
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(strSelect);	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(500).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Internal Server Error")).build();
			}
			 
		   OSPJobs oSPJobs=null;   
		   try {
			   oSPJobs = composeResultsFromResultSet();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(500).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Internal Server Error")).build();
			} finally{
				try {
					resultSet.close();		
					statement.close();
					connection.close();
				} catch (SQLException e) {			
					e.printStackTrace();
				}
			}
		   return Response.ok().entity(oSPJobs).build();
    }
    @Override
    public Response mcOspOspIdJobsJobIdSubscriptionPut(BigDecimal ospId, BigDecimal jobId, OSPJobsSubscriptionRequest oSPJobsSubscriptionRequest, SecurityContext securityContext)
    throws NotFoundException {
		String strSelect;
        //if (true) return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "ospid " + ospId + ", jobid " +  jobId + "OSPJobsSubscriptionRequest" +  oSPJobsSubscriptionRequest.toString())).build();
		strSelect = composeSqlQuery2(ospId, jobId, oSPJobsSubscriptionRequest);    	
        if (true) return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, strSelect)).build();
	    
    	//GBE added code to get db information form a properties file
		Properties props;
		props = loadDbProperties();
		
		connection = getDbConnection(props);
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery(strSelect);	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return Response.status(500).entity(new ApiResponseMessage(ApiResponseMessage.ERROR, "Internal Server Error")).build();
			}
			 
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "Changed")).build();
    }
    
	private Properties loadDbProperties() {
		Properties props;
		props = new Properties();
		
		InputStream fis = null;
		try {
		    fis = this.getClass().getClassLoader().getResourceAsStream("/db.properties");
		    props.load(fis);
		}     catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		return props;
	}
	
	private Connection getDbConnection (Properties props){
		Connection connection = null;
    	
    	try {
			Class.forName(props.getProperty("jdbc.driverClassName"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		try {
			connection = DriverManager
				    .getConnection(props.getProperty("jdbc.url"),
				    		props.getProperty("jdbc.username"),
				    		props.getProperty("jdbc.password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	 
		return connection;
	}

	private OSPJobs composeResultsFromResultSet() throws SQLException {
		OSPJobs oSPJobs = new OSPJobs ();   	
        BDAJob bDAJob;
        
		while (resultSet.next()){
			bDAJob = new BDAJob();
			bDAJob.setJobId(resultSet.getInt("job_Id"));
 			bDAJob.setJobName(resultSet.getString("name"));
 
			Boolean subscribed=false;
			subscribed=resultSet.getBoolean("subscribed");
			bDAJob.setSubscribed(subscribed);
			String downloadLink=resultSet.getString("downloadLink");
			bDAJob.setDownloadLink(resultSet.getString("downloadLink"));
			String status= null;
			if (subscribed) 
				if (downloadLink == null || downloadLink == "") status="pending";
				else status="completed";
			bDAJob.setStatus(status);
			oSPJobs.getJobs().add(bDAJob);
		 }
		
		/*
		 * | USER_ID  | DATED  | LOGGER | LEVEL | REQUESTERTYPE | REQUESTERID | LOGPRIORITY | KEYWORDS  | TITLE | MESSAGE 
		 */
		return oSPJobs;
	}

	
	private String composeSqlQuery(BigDecimal ospId) {
		String strSelect;
		strSelect = "SELECT * FROM usersjobs INNER JOIN jobs ON usersjobs.job_id=jobs.id WHERE user_id=\"" + ospId + "\";";    
        return strSelect;
	}	
	private String composeSqlQuery2(BigDecimal ospId, BigDecimal jobId, OSPJobsSubscriptionRequest oSPJobsSubscriptionRequest) {
		String strSelect;
		strSelect = "UPDATE usersjobs SET subscribed=" + oSPJobsSubscriptionRequest.getSubscribed() + ", frequency=\"" + oSPJobsSubscriptionRequest.getFrequency() + "\" WHERE user_id=\"" + ospId + "\" and job_id=\"" + jobId + "\";";    
        return strSelect;
	}

}
