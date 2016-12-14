package io.swagger.api;

import io.swagger.api.NotFoundException;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-12T13:34:24.407Z")
public abstract class ConsentApiService {
    public abstract Response consentOspIdGet(String ospId,SecurityContext securityContext) throws NotFoundException;
}
