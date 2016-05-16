/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2016
//
// Copyright in this software belongs to University of Southampton
// IT Innovation Centre of Gamma House, Enterprise Road,
// Chilworth Science Park, Southampton, SO16 7NS, UK.
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
//      Created By :            Panos Melas
//      Created Date :          2016-04-28
//      Created for Project :   OPERANDO
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
      .title("Swagger Server")
      .description("The Policy Database that stores three types of documents in dedicated\ncollections.\n\n\n1) User Privacy Policy. Each OPERANDO user has one UPP document describing\ntheir privacy policies for each of the OSP services they are subscribed to.\nThe UPP contains the current B2C privacy settings for a subscribed to OSP.\nThe UPP contains the users privacy preferences. The UPP contains the G2C\naccess policies for the OSP with justification for access.\n\n\n2) The legislation policies. The regulations entered into OPERANDO using the\nOPERANDO regulation API.\n\n\n3) The OSP descriptions and data requests. For each OSP its privacy policy,\nits access control rules, and the data it requests (as a workflow). For B2C,\nthe set of privacy settings is stored.\n")
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
