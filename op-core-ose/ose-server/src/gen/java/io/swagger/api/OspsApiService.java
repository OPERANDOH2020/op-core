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


import eu.operando.core.pdb.common.model.OSPDataRequest;
import eu.operando.core.pdb.common.model.OSPPrivacyPolicy;
import eu.operando.core.pdb.common.model.PrivacySetting;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-04-25T15:37:02.222Z")
public abstract class OspsApiService {

      public abstract Response ospsOspIdPrivacySettingsGet(String ospId,String userId,SecurityContext securityContext)
      throws NotFoundException;

      public abstract Response ospsOspIdPrivacySettingsPut(String ospId,String userId,List<PrivacySetting> ospSettings,SecurityContext securityContext)
      throws NotFoundException;

      public abstract Response ospsOspIdPrivacytextPut(String ospId,String ospPrivacyText,SecurityContext securityContext)
      throws NotFoundException;

      public abstract Response ospsOspIdPut(String ospId,OSPPrivacyPolicy ospPolicy,SecurityContext securityContext)
      throws NotFoundException;

      public abstract Response ospsOspIdWorkflowsPut(String ospId,OSPDataRequest ospWorkflow,SecurityContext securityContext)
      throws NotFoundException;

      public abstract Response ospsOspIdAuditGet(String ospId, String start, String end, SecurityContext securityContext)
      throws NotFoundException;

}
