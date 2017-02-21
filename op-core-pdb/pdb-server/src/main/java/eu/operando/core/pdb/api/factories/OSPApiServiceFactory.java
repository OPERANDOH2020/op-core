package eu.operando.core.pdb.api.factories;

import io.swagger.api.OSPApiService;
import eu.operando.core.pdb.api.impl.OSPApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-20T12:05:17.950Z")
public class OSPApiServiceFactory {
    private final static OSPApiService service = new OSPApiServiceImpl();

    public static OSPApiService getOSPApi() {
        return service;
    }
}
