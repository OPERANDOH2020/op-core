package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.api.NotFoundException;



import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-01-18T15:15:52.371Z")
public class ConsentApiServiceImpl extends ConsentApiService {
    @Override
    public Response consentOspIdGet(String ospId, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
