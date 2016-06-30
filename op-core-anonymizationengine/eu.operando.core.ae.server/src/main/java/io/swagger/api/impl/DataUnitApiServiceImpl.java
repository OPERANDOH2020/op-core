package io.swagger.api.impl;

import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import io.swagger.api.ApiResponseMessage;
import io.swagger.api.DataUnitApiService;
import io.swagger.api.NotFoundException;
import io.swagger.model.AccessLevel;
import io.swagger.model.DataUnitRequest;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-05-10T09:36:15.284Z")
public class DataUnitApiServiceImpl extends DataUnitApiService {
    
    @Override
    public Response addAccessLevelsByDataUnitByPersonAndRequester(String dataunitId, String personId, String requesterId, AccessLevel accessLevel, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response createDataUnit(DataUnitRequest dataUnit, SecurityContext securityContext)
    throws NotFoundException {    	
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "The data unit has been created correctly!")).build();
    }
    
    @Override
    public Response deleteDataUnit(String dataunitId, String dataunitId2, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response getAccessLevelsByDataUnit(String dataunitId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response getAccessLevelsByDataUnitAndCountry(String dataunitId, String countryId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response getAccessLevelsByDataUnitAndPA(String dataunitId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response getAccessLevelsByDataUnitPersonAndRequester(String dataunitId, String personId, String requesterId, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response getDataUnit(String dataunitId, String dataunitId2, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response getDataUnits(SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response setAccessLevelsByDataUnit(String dataunitId, List<AccessLevel> accesslevels, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response updateAccessLevelsByDataUnit(String dataunitId, List<AccessLevel> accesslevels, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response updateAccessLevelsByDataUnitByPersonAndRequester(String dataunitId, String personId, String requesterId, AccessLevel accessLevel, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
    @Override
    public Response updateDataUnit(String dataunitId, DataUnitRequest dataUnit, SecurityContext securityContext)
    throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    
}
