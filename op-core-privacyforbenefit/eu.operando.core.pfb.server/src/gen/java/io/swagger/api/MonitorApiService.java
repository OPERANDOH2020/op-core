package io.swagger.api;

import io.swagger.api.NotFoundException;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-03T15:31:38.137Z")
public abstract class MonitorApiService {
    public abstract Response monitorGet(SecurityContext securityContext) throws NotFoundException;
}
