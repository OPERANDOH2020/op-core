package io.swagger.api.factories;

import io.swagger.api.MonitorApiService;
import eu.operando.core.ose.api.impl.MonitorApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-03T15:31:38.137Z")
public class MonitorApiServiceFactory {
    private final static MonitorApiService service = new MonitorApiServiceImpl();

    public static MonitorApiService getMonitorApi() {
        return service;
    }
}
