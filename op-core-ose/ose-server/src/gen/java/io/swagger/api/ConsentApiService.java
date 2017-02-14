package io.swagger.api;

import io.swagger.api.NotFoundException;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-01-18T15:15:52.371Z")
public abstract class ConsentApiService {
    public abstract Response consentOspIdGet(String ospId,SecurityContext securityContext) throws NotFoundException;
}
