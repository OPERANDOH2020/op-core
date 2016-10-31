package io.swagger.api.factories;

import io.swagger.api.OSPApiService;
import io.swagger.api.impl.OSPApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-10-28T08:28:40.436Z")
public class OSPApiServiceFactory {
    private final static OSPApiService service = new OSPApiServiceImpl();

    public static OSPApiService getOSPApi() {
        return service;
    }
}
