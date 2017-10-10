package eu.operando.core.ldb.server.api.impl;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.UserCredential;

import javax.ws.rs.Priorities;

//this do not work @PreMatching 
@Secured
@Provider
//@PreMatching 
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    //private static final String AUTHENTICATION_SCHEME = "Bearer";
    private static final String AUTHENTICATION_SCHEME = "";
    //private static final String AUTHENTICATION_HEADER = HttpHeaders.AUTHORIZATION;
    private static final String AUTHENTICATION_HEADER = "service-ticket";
    // GBE maybe there is a way to get the value from swagger ops
    
    //@Override
    public void filter(ContainerRequestContext requestContext) throws IOException  {

    	System.out.println("Entering authentication filter ");

        // Get the Authorization header from the request
        String authenticationHeader =
                requestContext.getHeaderString(AUTHENTICATION_HEADER);

        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authenticationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        // Extract the token from the Authorization header
        String token = authenticationHeader;
                            //.substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            // Validate the token
            validateToken(token);

        } catch (Exception e) {
            abortWithUnauthorized(requestContext);
        }
        
    	System.out.println("Successful Validation ");        
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // Authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                    .startsWith(AUTHENTICATION_SCHEME.toLowerCase());
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        // Abort the filter chain with a 401 status code
        // The "WWW-Authenticate" header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE, AUTHENTICATION_SCHEME)
                        .build());
    }

    DefaultApi aapiDefaultClient;
	String logdbSId = "/operando/core/ldb";
	static String aapiBasePath = "http://integration.operando.esilab.org:8135/operando/interfaces/aapi";

	private void validateToken(String token) throws ApiException {
        // Check if it was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid
    	System.out.println("Validates Token using aapi client, token: " + token);
    	
    	ApiClient aapiClient=new ApiClient();
    	aapiClient.setBasePath(aapiBasePath);
    	aapiDefaultClient = new DefaultApi(aapiClient);
    	aapiDefaultClient.aapiTicketsStValidateGet(token, logdbSId);	
    }

	String ldbLoginName = "string";
	String ldbLoginPassword = "string";
	String stHeaderName = "service-ticket";
	private String getServiceTicket(String username, String password, String serviceId) {
		String st = null;

		UserCredential userCredential = new UserCredential();
		userCredential.setUsername(username);
		userCredential.setPassword(password);

		try {
			String tgt = aapiDefaultClient.aapiTicketsPost(userCredential);
			System.out.println("logdb service TGT: " + tgt);
			st = aapiDefaultClient.aapiTicketsTgtPost(tgt, serviceId);
			System.out.println("logdb service ticket: " + st);

		} catch (ApiException ex) {
			ex.printStackTrace();
		}
		return st;
	}
    
}
