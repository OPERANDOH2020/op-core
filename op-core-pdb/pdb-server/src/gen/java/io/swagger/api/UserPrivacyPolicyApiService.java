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
//      Created Date :          2016-10-28
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

package io.swagger.api;

import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import eu.operando.core.pdb.common.model.UserPrivacyPolicy;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.HttpHeaders;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2017-02-20T12:05:17.950Z")
public abstract class UserPrivacyPolicyApiService {
    public abstract Response userPrivacyPolicyGet(String filter,SecurityContext securityContext,HttpHeaders headers) throws NotFoundException;
    public abstract Response userPrivacyPolicyPost(UserPrivacyPolicy upp,SecurityContext securityContext,HttpHeaders headers) throws NotFoundException;
    public abstract Response userPrivacyPolicyUserIdDelete(String userId,SecurityContext securityContext,HttpHeaders headers) throws NotFoundException;
    public abstract Response userPrivacyPolicyUserIdGet(String userId,SecurityContext securityContext,HttpHeaders headers) throws NotFoundException;
    public abstract Response userPrivacyPolicyUserIdPut(String userId,UserPrivacyPolicy upp,SecurityContext securityContext,HttpHeaders headers) throws NotFoundException;
}
