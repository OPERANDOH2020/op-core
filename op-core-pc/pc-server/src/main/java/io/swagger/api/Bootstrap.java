package io.swagger.api;

import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.*;

import io.swagger.models.auth.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Bootstrap extends HttpServlet {
  @Override
  public void init(ServletConfig config) throws ServletException {
    Info info = new Info()
      .title("Swagger Server")
      .description("Provides a set of functions that provide analysis and computation of privacy policies. The following describe the overall behaviours of the component  1) Evaluate if a particular data request, or sequence of data requests from an OSP complies with the user's privacy policy;   2) Evaluate if the behaviour of an OSP complies with the appropriate privacy regulations;  3) Compute and manage the UPP of a user. Where a new user subscribes to the service, create the new UPP; where an existing user subscribes to a new service, or updates their privacy preferences recompute the UPP. ")
      .termsOfService("http://www.operando.eu/terms/")
      .contact(new Contact()
        .email("support@operando.eu"))
      .license(new License()
        .name("MIT")
        .url("http://opensource.org/licenses/MIT"));

    ServletContext context = config.getServletContext();
    Swagger swagger = new Swagger().info(info);

    new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
  }
}
