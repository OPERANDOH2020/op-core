package eu.operando.core.pdb.api.factories;

import io.swagger.api.UserPrivacyPolicyApiService;
import eu.operando.core.pdb.api.impl.UserPrivacyPolicyApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-20T12:05:17.950Z")
public class UserPrivacyPolicyApiServiceFactory {
    private final static UserPrivacyPolicyApiService service = new UserPrivacyPolicyApiServiceImpl();

    public static UserPrivacyPolicyApiService getUserPrivacyPolicyApi() {
        return service;
    }
}
