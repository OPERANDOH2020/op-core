package io.swagger.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public abstract class MonitorApiService {
    public abstract Response monitorGet(SecurityContext securityContext) throws NotFoundException;
}
