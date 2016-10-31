package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.PrivacyRegulation;
import io.swagger.model.PrivacyRegulationInput;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-10-28T08:28:40.436Z")
public abstract class RegulationsApiService {
    public abstract Response regulationsGet(String filter,SecurityContext securityContext) throws NotFoundException;
    public abstract Response regulationsPost(PrivacyRegulationInput regulation,SecurityContext securityContext) throws NotFoundException;
    public abstract Response regulationsRegIdDelete(String regId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response regulationsRegIdGet(String regId,SecurityContext securityContext) throws NotFoundException;
    public abstract Response regulationsRegIdPut(String regId,PrivacyRegulationInput regulation,SecurityContext securityContext) throws NotFoundException;
}
