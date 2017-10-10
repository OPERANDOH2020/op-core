package eu.operando.core.ldb.server.api;

import eu.operando.core.ldb.server.api.*;
import eu.operando.core.ldb.server.model.*;

import eu.operando.core.ldb.server.model.LogRequestExt;

import java.util.List;
import eu.operando.core.ldb.server.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-10-09T16:21:27.816+02:00")
public abstract class LogApiService {
    public abstract Response log(LogRequestExt request,SecurityContext securityContext) throws NotFoundException;
    public abstract Response logTicket(LogRequestExt request,SecurityContext securityContext) throws NotFoundException;
}
