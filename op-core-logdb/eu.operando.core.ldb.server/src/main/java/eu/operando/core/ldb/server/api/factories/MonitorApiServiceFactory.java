package eu.operando.core.ldb.server.api.factories;

import eu.operando.core.ldb.server.api.MonitorApiService;
import eu.operando.core.ldb.server.api.impl.MonitorApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-03T15:31:38.137Z")
public class MonitorApiServiceFactory {
    private final static MonitorApiService service = new MonitorApiServiceImpl();

    public static MonitorApiService getMonitorApi() {
        return service;
    }
}
