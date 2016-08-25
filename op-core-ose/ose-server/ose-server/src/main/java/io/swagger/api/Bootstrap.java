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
      .description("\nThe OSP enforcement component provides a set of functions that manage the interaction of OSP behaviour with the user's private data. The OSE component is largely in charge of ensuring that an OSP follows both privacy regulations and policies put in place by the user (i.e. in the OPERANDO UPPs). There are a set of events that trigger the usage of this API.\n\n1) When a new G2C OSP registers with OPERANDO via the OPERANDO console. The management console informs the OSE, which then checks that an OSP conforms with existing privacy regulations; if it breaches the regulations, the OSE returns via the management console a report describing the breaches.\n\n2) When a change of OSP privacy policy or of the OSP's privacy settings are detected by the watchdog component. The\nwatchdog component sends a message to a privacy analyst who reviews any changes. The privacy analyst may then\ninform the OSE of the new OSP information (to be checked for compliance with regulations and users' UPPs.\n\n3) When a privacy legislation is entered (or changed) via the Regulator API. The OSE checks registered OSPs for compliance with the new regulations; where there is a breach the OSP is notified either by e-mail or the console.\n")
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
