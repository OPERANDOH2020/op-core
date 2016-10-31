package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.OSPPrivacyPolicy;
import io.swagger.model.OSPReasonPolicy;
import io.swagger.model.OSPReasonPolicyInput;
import io.swagger.model.OSPPrivacyPolicyInput;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-10-28T08:28:40.436Z")
public abstract class OSPApiService {
    public abstract Response oSPGet(String filter,SecurityContext securityContext) throws NotFoundException;
    public abstract Response oSPOspIdDelete(String ospId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response oSPOspIdGet(String ospId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response oSPOspIdPrivacyPolicyGet(String ospId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response oSPOspIdPrivacyPolicyPut(String ospId,OSPReasonPolicyInput ospPolicy,SecurityContext securityContext) throws NotFoundException;
    public abstract Response oSPOspIdPut(String ospId,OSPPrivacyPolicyInput ospPolicy,SecurityContext securityContext) throws NotFoundException;
    public abstract Response oSPPost(OSPPrivacyPolicyInput ospPolicy,SecurityContext securityContext) throws NotFoundException;
}
