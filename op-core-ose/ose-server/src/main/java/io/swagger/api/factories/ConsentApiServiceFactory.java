package io.swagger.api.factories;

import io.swagger.api.ConsentApiService;
import io.swagger.api.impl.ConsentApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-12T13:34:24.407Z")
public class ConsentApiServiceFactory {
    private final static ConsentApiService service = new ConsentApiServiceImpl();

    public static ConsentApiService getConsentApi() {
        return service;
    }
}
