package io.swagger.api;

import io.swagger.model.*;


import java.util.List;
import javax.validation.constraints.NotNull;


import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-06-30T09:37:51.622Z")
public abstract class QuestionsApiService {
    public abstract Response questionsUserIdOspIdGet(String userId, String ospId,  @NotNull String language, SecurityContext securityContext) throws NotFoundException;
    public abstract Response questionsUserIdOspIdPost(String userId, String ospId, List<Questionobject> questionInput,SecurityContext securityContext) throws NotFoundException;
}
