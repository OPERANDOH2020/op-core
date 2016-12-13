package eu.operando.core.pdb.api.factories;

import io.swagger.api.RegulationsApiService;
import eu.operando.core.pdb.api.impl.RegulationsApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-10-28T08:28:40.436Z")
public class RegulationsApiServiceFactory {
    private final static RegulationsApiService service = new RegulationsApiServiceImpl();

    public static RegulationsApiService getRegulationsApi() {
        return service;
    }
}
