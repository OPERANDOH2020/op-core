package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.OspPolicyComputerApiService;
import io.swagger.api.factories.OspPolicyComputerApiServiceFactory;

import io.swagger.annotations.ApiParam;

import com.sun.jersey.multipart.FormDataParam;

import io.swagger.model.ComputationResult;
import io.swagger.model.UserPreference;
import java.util.List;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/osp_policy_computer")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@io.swagger.annotations.Api(description = "the osp_policy_computer API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-06-27T09:46:42.374Z")
public class OspPolicyComputerApi  {
   private final OspPolicyComputerApiService delegate = OspPolicyComputerApiServiceFactory.getOspPolicyComputerApi();

    @POST
    
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Computes the UPP policy for a specific G2C OSP.", notes = "This is a computational resource accessed via a REST POST call to carry out a computation on the provided inputs. This results in an resource that is created and stored in the Policy DB and can be accessed by the URL returned as a result of this operation. In OPERANDO, this method is called by the management console when a user subscribes to a new OSP service. The user simply enters answers the specific OSP questionnaire. This will create a simple UPP for that OSP and store this policy within the larger UPP record. ", response = ComputationResult.class, tags={ "Policy Compute", "POST" })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "UPP was created. There is no invalid response - if the method is called on the operation with an existing user_id then the server redirects to the PUT behaviour, rather than return an unnecessary error (This is not the data storage operation). ", response = ComputationResult.class),
        @io.swagger.annotations.ApiResponse(code = 400, message = "The client inputs to the operation are incorrect or invalid. The caller should check the inputs are valid based upon the returned error message. ", response = ComputationResult.class) })
    public Response ospPolicyComputerPost(
        @ApiParam(value = "user unique identifier of the UPP to compute",required=true) @QueryParam("user_id") String userId,
        @ApiParam(value = "user unique identifier of the UPP to compute",required=true) @QueryParam("osp_id") String ospId,
        @ApiParam(value = "The set of privacy preferences. This is a JSON object with the answers to the specific questionnaire. These can be changed and updated.  " ,required=true) List<UserPreference> ospPrefs,
        @Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.ospPolicyComputerPost(userId,ospId,ospPrefs,securityContext);
    }
}
