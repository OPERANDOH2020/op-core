package io.swagger.api.factories;

import io.swagger.api.ConsentApiService;
import io.swagger.api.impl.ConsentApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-01-18T15:15:52.371Z")
public class ConsentApiServiceFactory {
    private final static ConsentApiService service = new ConsentApiServiceImpl();

    public static ConsentApiService getConsentApi() {
        return service;
    }
}
