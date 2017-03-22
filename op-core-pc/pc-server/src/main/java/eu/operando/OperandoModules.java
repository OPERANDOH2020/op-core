///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package eu.operando;
//
//import eu.operando.core.cas.client.api.DefaultApi;
//import eu.operando.core.cas.client.model.UserCredential;
//import io.swagger.client.ApiException;
//import io.swagger.client.api.LogApi;
//import io.swagger.client.model.LogRequest;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import javax.ws.rs.core.HttpHeaders;
//
///**
// *
// * @author pjg
// */
//public class OperandoModules {
//
//    private final String stHeaderName = "Service-Ticket";
//
//    /**
//     * Reusable operation to retrieve a service ticket from the AAPI.
//     * @param username The authentication username
//     * @param password The authentication password
//     * @param serviceId The service Id to get a ticket for.
//     * @return
//     */
//    public String getServiceTicket(DefaultApi aapiClient, String username, String password, String serviceId) {
//        String st = null;
//
//        UserCredential userCredential = new UserCredential();
//        userCredential.setUsername(username);
//        userCredential.setPassword(password);
//
//        try {
//            String tgt = aapiClient.aapiTicketsPost(userCredential);
//            System.out.println("pdb upp service TGT: " + tgt);
//            st = aapiClient.aapiTicketsTgtPost(tgt, serviceId);
//            System.out.println("logdb upp service ticket: " + st);
//
//        } catch (eu.operando.core.cas.client.ApiException ex) {
//
//        }
//        return st;
//    }
//
//    private boolean validateHeaderSt(HttpHeaders headers) {
//        return true;
//    }
//
//    public boolean validateHeaderSt1(DefaultApi aapiClient, LogApi logApi, String serviceID, HttpHeaders headers) {
//        if (headers != null) {
//            List<String> stHeader = headers.getRequestHeader(stHeaderName);
//            if (stHeader != null) {
//                String st = stHeader.get(0);
//                try {
//                    aapiClient.aapiTicketsStValidateGet(st, serviceID);
//                    return true;
//                } catch (eu.operando.core.cas.client.ApiException ex) {
//                    /**
//                    * Error getting the ticket - log to LogDB
//                    */
//                   logRequest(logApi, "PC - Validate Header", "username: ".concat(serviceID),
//                    "Ticket validation failed ".concat(serviceID),
//                    LogRequest.LogLevelEnum.WARN, LogRequest.LogPriorityEnum.LOW,
//                    new ArrayList<String>(Arrays.asList("PC", "validateHeaderSt1")));
//                    return false;
//                }
//            }
//        }
//        return false;
//    }
//
//    public void logRequest(LogApi logApi, String requesterId, String title, String description,
//            LogRequest.LogLevelEnum logDataType, LogRequest.LogPriorityEnum logPriority,
//            ArrayList<String> keywords) {
//
//        ArrayList<String> words = new ArrayList<String>(Arrays.asList("PDB", "UPP"));
//        for (String word : keywords) {
//            words.add(word);
//        }
//
//        LogRequest logRequest = new LogRequest();
//        logRequest.setUserId("PC");
//        logRequest.setDescription(description);
//        logRequest.setLogLevel(logDataType);
//        logRequest.setTitle(title);
//        logRequest.setLogPriority(logPriority);
//        logRequest.setRequesterId(requesterId);
//        logRequest.setRequesterType(LogRequest.RequesterTypeEnum.PROCESS);
//
//        logRequest.setKeywords(words);
//
//        try {
//            String response = logApi.lodDB(logRequest);
//        } catch (ApiException ex) {
//            System.err.println("Log failed: " + ex.getResponseBody());
//        }
//    }
//
//}
