package io.swagger.api.factories;

import io.swagger.api.QuestionsApiService;
import io.swagger.api.impl.QuestionsApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-06-30T09:37:51.622Z")
public class QuestionsApiServiceFactory {
    private final static QuestionsApiService service = new QuestionsApiServiceImpl();

    public static QuestionsApiService getQuestionsApi() {
        return service;
    }
}
