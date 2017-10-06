package io.swagger.api;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

public abstract class ConsentApiService {
    public abstract Response consentOspIdGet(String ospId, String field, String role, SecurityContext securityContext) throws NotFoundException;
    public abstract Response consentOspIdUserIdGet(String ospId,String userId, String field, String role, SecurityContext securityContext) throws NotFoundException;
}
