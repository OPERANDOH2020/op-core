package io.swagger.api.factories;

import io.swagger.api.UserPrivacyPolicyApiService;
import io.swagger.api.impl.UserPrivacyPolicyApiServiceImpl;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-10-28T08:28:40.436Z")
public class UserPrivacyPolicyApiServiceFactory {
    private final static UserPrivacyPolicyApiService service = new UserPrivacyPolicyApiServiceImpl();

    public static UserPrivacyPolicyApiService getUserPrivacyPolicyApi() {
        return service;
    }
}
