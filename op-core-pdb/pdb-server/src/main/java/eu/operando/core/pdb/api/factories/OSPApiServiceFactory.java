package eu.operando.core.pdb.api.factories;

import io.swagger.api.OSPApiService;
import eu.operando.core.pdb.api.impl.OSPApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-19T10:59:55.638Z")
public class OSPApiServiceFactory {
    private final static OSPApiService service = new OSPApiServiceImpl();

    public static OSPApiService getOSPApi() {
        return service;
    }
}
