package eu.operando.core.pdb.api.factories;

import io.swagger.api.RegulationsApiService;
import eu.operando.core.pdb.api.impl.RegulationsApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-20T12:05:17.950Z")
public class RegulationsApiServiceFactory {
    private final static RegulationsApiService service = new RegulationsApiServiceImpl();

    public static RegulationsApiService getRegulationsApi() {
        return service;
    }
}
