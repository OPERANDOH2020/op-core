///////////////////////////////////////////////////////////////////////////
////
//// © University of Southampton IT Innovation Centre, 2016
////
//// Copyright in this library belongs to the University of Southampton
//// University Road, Highfield, Southampton, UK, SO17 1BJ
////
//// This software may not be used, sold, licensed, transferred, copied
//// or reproduced in whole or in part in any manner or form or in or
//// on any media by any person other than in accordance with the terms
//// of the Licence Agreement supplied with the software, or otherwise
//// without the prior written consent of the copyright owners.
////
//// This software is distributed WITHOUT ANY WARRANTY, without even the
//// implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
//// PURPOSE, except where stated in the Licence Agreement supplied with
//// the software.
////
//// Created By : Paul Grace
//// Created for Project : XIFI (http://www.fi-xifi.eu)
////
///////////////////////////////////////////////////////////////////////////
////
////  License : GNU Lesser General Public License, version 3
////
///////////////////////////////////////////////////////////////////////////
//
//package eu.operando;
//
//import com.jayway.jsonpath.Configuration;
//import com.jayway.jsonpath.JsonPath;
//import io.swagger.model.OSPDataRequest;
//import io.swagger.model.PolicyEvaluationReport;
//import java.io.IOException;
//import java.util.List;
//import net.minidev.json.JSONArray;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
///**
// *
// * @author pjg
// */
//
//
//public class PolicyEvaluationService {
//
//    private String PDB_BASEURL = "http://server02tecnalia.westeurope.cloudapp.azure.com:8096/operando/core/pdb";
//
//
//    public PolicyEvaluationService() {
//
//    }
//
//
//
//    public String evaluate(String ospId, String userId, List<OSPDataRequest> ospRequest){
//        try {
//            System.out.println("New Evaluation Request");
//            System.out.println("--------------------------------------------------");
//            System.out.println("Evaluating User Policy: " + userId);
//            System.out.println("Request from: " + ospId);
//
//            /**
//             * The response to be sent - yes/no along with a report of why something
//             * has been denied.
//             */
//            PolicyEvaluationReport rp = new PolicyEvaluationReport();
//
//            /**
//             * Get the UPP from the PDB.
//             */
//            CloseableHttpClient httpclient = HttpClients.createDefault();
//            HttpGet httpget = new HttpGet(PDB_BASEURL + userId);
////            CloseableHttpResponse response1 = httpclient.execute(httpget);
//
////            HttpEntity entity = response1.getEntity();
////            System.out.println(response1.getStatusLine().getStatusCode());
////            if(response1.getStatusLine().getStatusCode()==404) {
////                rp.setStatus("false");
////                rp.setCompliance("User has no UPP policies registered");
////                return rp.toString();
////            }
////            String msg = EntityUtils.toString(entity);
////            System.out.println(msg);
//
//            String errorString = "";
//            boolean permit = true;
//            /**
//             * Evaluate request against the UPP user access policies
//             */
//            for (OSPDataRequest r: ospRequest) {
//                String oDataURL = r.getRequestedUrl();
//                httpget = new HttpGet(oDataURL);
//                httpget.addHeader("Content-Type", "application/json");
//                CloseableHttpResponse response2 = httpclient.execute(httpget);
//                String msg = EntityUtils.toString(response2.getEntity());
//                Object document = Configuration.defaultConfiguration().jsonProvider().parse(msg);
//
////                String path = JsonPath.read(document, "$.['@odata.context']");
//                httpget = new HttpGet(path);
//                httpget.addHeader("Content-Type", "application/xml");
//                CloseableHttpResponse response3 = httpclient.execute(httpget);
//                String msg3 = EntityUtils.toString(response3.getEntity());
//
//                String Category = XSDParser.getElementDataType(oDataURL, msg3);
//                System.out.println("OSP - "+ospId+" Category - "+Category);
//                JSONArray access_policies = JsonPath.read("", "$.subscribed_osp_policies[?(@.osp_id=='"+ospId+"')].access_policies[?(@.resource=='"+ Category +"')]");
//                boolean found = false;
//                for(Object aP: access_policies) {
//                    String subject = JsonPath.read(aP, "$.subject");
//                    System.out.println("Subject evaluated - " + subject);
//                    System.out.println("Subject evaluated - " + r.getSubject());
//                    if(subject.equalsIgnoreCase(r.getSubject())) { // Check the subject
//                        found=true;
//
//                        if (JsonPath.read(aP, "$.action").toString().equalsIgnoreCase(r.getAction().name())){ // Check the action
//                            boolean perm = JsonPath.read(aP, "$.permission");
//                            if(!perm) {
//                                permit = false;
//                                errorString+="User doesn't permit ["+r.getAction().toString()+"] to ["+Category+"] data\\n";
//                            }
//                            break;
//                        }
//                        else {
//                            permit = false;
//                            errorString+="User doesn't permit ["+r.getAction().toString()+"] to ["+Category+"] data\\n";
//                        }
//                    }
//                }
//                if(!found){
//                    permit = false;
//                    errorString+="User doesn't permit Subject ["+r.getSubject().toString()+"] to perform any action on ["+Category+"] data\\n";
//                }
//            }
//
//            if(permit) {
//                rp.setStatus("true");
//                rp.setCompliance("fully complies");
//            }
//            else {
//                rp.setStatus("false");
//                rp.setCompliance(errorString);
//            }
//
//            String policyReport = rp.toString();
//            System.out.println(policyReport);
//            return policyReport;
//        } catch (IOException ex) {
//            return "error";
//        }   catch (InvalidPreferenceException ex) {
//                return "error";
//        }
//    }
//
//}
