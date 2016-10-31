package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.UserPrivacyPolicy;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-10-28T08:28:40.436Z")
public abstract class UserPrivacyPolicyApiService {
    public abstract Response userPrivacyPolicyGet(String filter,SecurityContext securityContext) throws NotFoundException;
    public abstract Response userPrivacyPolicyPost(UserPrivacyPolicy upp,SecurityContext securityContext) throws NotFoundException;
    public abstract Response userPrivacyPolicyUserIdDelete(String userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response userPrivacyPolicyUserIdGet(String userId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response userPrivacyPolicyUserIdPut(String userId,UserPrivacyPolicy upp,SecurityContext securityContext) throws NotFoundException;
}
