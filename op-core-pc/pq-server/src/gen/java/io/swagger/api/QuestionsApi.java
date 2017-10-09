package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.factories.QuestionsApiServiceFactory;
import io.swagger.annotations.ApiParam;
import java.util.List;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/questions")

@io.swagger.annotations.Api(description = "the questions API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-06-30T09:37:51.622Z")
public class QuestionsApi  {
   private final QuestionsApiService delegate = QuestionsApiServiceFactory.getQuestionsApi();

    @GET
    @Path("/{user-id}/{osp-id}")


    @io.swagger.annotations.ApiOperation(value = "Obtain a set of 9 questions related to privacy and the specified OSP.", notes = "The query contains a json object (names, values) and the request returns the list of documents (UPPs) where the filter matches i.e. each document contains fields with those values. ", response = Answerobject.class, responseContainer = "List", tags={ "Questions", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "The list of 9 generated questions for this OSP-ID.", response = Questionobject.class, responseContainer = "List"),

        @io.swagger.annotations.ApiResponse(code = 404, message = "The user/osp-id does not exist in the platform.", response = Questionobject.class, responseContainer = "List") })
    public Response questionsUserIdOspIdGet(@ApiParam(value = "The user identifier number",required=true) @PathParam("user-id") String userId
,@ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId
,@ApiParam(value = "The language for the question (EN, IT, FR etc.)",required=true) @QueryParam("language") String language
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.questionsUserIdOspIdGet(userId,ospId,language,securityContext);
    }
    @POST
    @Path("/{user-id}/{osp-id}")


    @io.swagger.annotations.ApiOperation(value = "Enter the answers to the questionnaire.", notes = "Once the questions have been answered by the user they are pushed to be  processed and the user preferences calculated and stored in the UPP. ", response = Answerobject.class, responseContainer = "List", tags={ "Questions", })
    @io.swagger.annotations.ApiResponses(value = {
        @io.swagger.annotations.ApiResponse(code = 200, message = "The set of questions successfully returned for the user.", response = Answerobject.class, responseContainer = "List"),

        @io.swagger.annotations.ApiResponse(code = 404, message = "The error failed.", response = Answerobject.class, responseContainer = "List") })
    public Response questionsUserIdOspIdPost(@ApiParam(value = "The user identifier number",required=true) @PathParam("user-id") String userId
            ,@ApiParam(value = "The identifier number of an OSP",required=true) @PathParam("osp-id") String ospId
            ,@ApiParam(value = "The answers to the questions" ,required=true) List<Questionobject> questionInput
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.questionsUserIdOspIdPost(userId, ospId, questionInput, securityContext);
    }
}
