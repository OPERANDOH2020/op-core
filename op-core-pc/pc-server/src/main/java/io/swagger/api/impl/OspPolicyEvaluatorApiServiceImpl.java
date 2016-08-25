package io.swagger.api.impl;

import com.jayway.jsonpath.JsonPath;
import io.swagger.api.*;

import io.swagger.model.OSPDataRequest;
import io.swagger.model.PolicyEvaluationReport;

import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import io.swagger.api.NotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import net.minidev.json.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class OspPolicyEvaluatorApiServiceImpl extends OspPolicyEvaluatorApiService {

	private String PDB_BASEURL = null;

    public OspPolicyEvaluatorApiServiceImpl() {
		super();
		// TODO Auto-generated constructor stub

		Properties props;
    	props = loadDbProperties();
    	
    	PDB_BASEURL = props.getProperty("pdb.baseurl");
	}
     
	private Properties loadDbProperties() {
		Properties props;
		props = new Properties();
		
		InputStream fis = null;
		try {
		    fis = this.getClass().getClassLoader().getResourceAsStream("/operando.properties");
		    props.load(fis);
		}     catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		
		return props;
	}


    @Override
    public Response ospPolicyEvaluatorPost(String userId, String ospId, List<OSPDataRequest> ospRequest, SecurityContext securityContext)
            throws NotFoundException {

        try {
            System.out.println("New Evaluation Request");
            System.out.println("--------------------------------------------------");
            System.out.println("Evaluating User Policy: " + userId);
            System.out.println("Request from: " + ospId);

           /**
            * Hard coded classification. Maps data types on privacy categories.
            */
            HashMap<String, String> dataClassMap = new HashMap<String, String>();
            dataClassMap.put("FirstName","Basic Information");
            dataClassMap.put("LastName","Basic Information");
            dataClassMap.put("Sex","Basic Information");
            dataClassMap.put("DateOfBirth","Basic Information");
            dataClassMap.put("Weight","Medical Information");
            dataClassMap.put("Height","Medical Information");

            /**
             * The response to be sent - yes/no along with a report of why something
             * has been denied.
             */
            PolicyEvaluationReport rp = new PolicyEvaluationReport();

            /**
             * Get the UPP from the PDB.
             */
            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(PDB_BASEURL + userId);
            CloseableHttpResponse response1 = httpclient.execute(httpget);

            HttpEntity entity = response1.getEntity();
            System.out.println(response1.getStatusLine().getStatusCode());
            if(response1.getStatusLine().getStatusCode()==404) {
                rp.setStatus("false");
                rp.setCompliance("User has no UPP policies registered");
                return Response.ok(rp.toString(), MediaType.APPLICATION_JSON).build();
            }
            String msg = EntityUtils.toString(entity);
            System.out.println(msg);

            String errorString = "";
            boolean permit = true;
            /**
             * Evaluate request against the UPP user access policies
             */
            for (OSPDataRequest r: ospRequest) {
                String Category = dataClassMap.get(r.getRequestedUrl());
                System.out.println("OSP - "+ospId+" Category - "+Category);
                JSONArray access_policies = JsonPath.read(msg, "$.subscribed_osp_policies[?(@.osp_id=='"+ospId+"')].access_policies[?(@.resource=='"+ Category +"')]");
                boolean found = false;
                for(Object aP: access_policies) {
                    String subject = JsonPath.read(aP, "$.subject");
                    System.out.println("Subject evaluated - " + subject);
                    System.out.println("Subject evaluated - " + r.getSubject());
                    if(subject.equalsIgnoreCase(r.getSubject())) { // Check the subject
                        found=true;

                        if (JsonPath.read(aP, "$.action").toString().equalsIgnoreCase(r.getAction().name())){ // Check the action
                            boolean perm = JsonPath.read(aP, "$.permission");
                            if(!perm) {
                                permit = false;
                                errorString+="User doesn't permit ["+r.getAction().toString()+"] to ["+Category+"] data\\n";
                            }
                            break;
                        }
                        else {
                            permit = false;
                            errorString+="User doesn't permit ["+r.getAction().toString()+"] to ["+Category+"] data\\n";
                        }
                    }
                }
                if(!found){
                    permit = false;
                    errorString+="User doesn't permit Subject ["+r.getSubject().toString()+"] to perform any action on ["+Category+"] data\\n";
                }
            }

            if(permit) {
                rp.setStatus("true");
                rp.setCompliance("fully complies");
            }
            else {
                rp.setStatus("false");
                rp.setCompliance(errorString);
            }

            String policyReport = rp.toString();
            System.out.println(policyReport);
            return Response.ok(policyReport, MediaType.APPLICATION_JSON).build();
        } catch (IOException ex) {
            return Response.serverError().build();
        }
    }
}
