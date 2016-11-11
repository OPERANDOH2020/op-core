package io.swagger.api;



import io.swagger.model.OSPReasonPolicy;
import io.swagger.api.NotFoundException;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-11-07T12:10:42.971Z")
public abstract class OspComplianceCheckerApiService {
    public abstract Response ospComplianceCheckerPost(String ospId,String regId,OSPReasonPolicy ospRequest,SecurityContext securityContext) throws NotFoundException;
}
