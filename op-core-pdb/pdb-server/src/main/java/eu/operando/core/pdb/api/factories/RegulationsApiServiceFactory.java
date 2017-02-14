package eu.operando.core.pdb.api.factories;

import io.swagger.api.RegulationsApiService;
import eu.operando.core.pdb.api.impl.RegulationsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-19T10:59:55.638Z")
public class RegulationsApiServiceFactory {
    private final static RegulationsApiService service = new RegulationsApiServiceImpl();

    public static RegulationsApiService getRegulationsApi() {
        return service;
    }
}
