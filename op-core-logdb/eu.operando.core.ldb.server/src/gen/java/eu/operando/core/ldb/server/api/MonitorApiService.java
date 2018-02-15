package eu.operando.core.ldb.server.api;

import eu.operando.core.ldb.server.api.*;
import eu.operando.core.ldb.server.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;


import java.util.List;
import eu.operando.core.ldb.server.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-10-17T16:12:53.169+02:00")
public abstract class MonitorApiService {
    public abstract Response monitorGet(SecurityContext securityContext) throws NotFoundException;
}
