/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2016
//
// Copyright in this library belongs to the University of Southampton
// University Road, Highfield, Southampton, UK, SO17 1BJ
//
// This software may not be used, sold, licensed, transferred, copied
// or reproduced in whole or in part in any manner or form or in or
// on any media by any person other than in accordance with the terms
// of the Licence Agreement supplied with the software, or otherwise
// without the prior written consent of the copyright owners.
//
// This software is distributed WITHOUT ANY WARRANTY, without even the
// implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
// PURPOSE, except where stated in the Licence Agreement supplied with
// the software.
//
// Created By : Paul Grace
// Created for Project : OPERANDO (http://www.operando.eu)
//
/////////////////////////////////////////////////////////////////////////
//
//  License : GNU Lesser General Public License, version 3
//
/////////////////////////////////////////////////////////////////////////
package io.swagger.api;

import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.*;


import javax.servlet.http.HttpServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public class Bootstrap extends HttpServlet {
  @Override
  public void init(ServletConfig config) throws ServletException {
    Info info = new Info()
      .title("Policy Computation Server")
      .description("Provides a set of functions that provide analysis and computation of privacy policies. The following describe the overall behaviours of the component  1) Evaluate if a particular data request, or sequence of data requests from an OSP complies with the user's privacy policy;   2) Evaluate if the behaviour of an OSP complies with the appropriate privacy regulations;  3) Compute and manage the UPP of a user. Where a new user subscribes to the service, create the new UPP; where an existing user subscribes to a new service, or updates their privacy preferences recompute the UPP. ")
      .termsOfService("http://www.operando.eu/terms/")
      .contact(new Contact()
        .email("support@operando.eu"))
      .license(new License()
        .name("GNU Lesser General Public License, version 3")
        .url("https://www.gnu.org/licenses/lgpl-3.0.en.html"));

    ServletContext context = config.getServletContext();
    Swagger swagger = new Swagger().info(info);

    new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
  }
}
