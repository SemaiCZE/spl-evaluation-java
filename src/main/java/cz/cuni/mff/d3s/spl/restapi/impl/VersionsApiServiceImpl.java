package cz.cuni.mff.d3s.spl.restapi.impl;

import cz.cuni.mff.d3s.spl.restapi.api.ApiResponseMessage;
import cz.cuni.mff.d3s.spl.restapi.api.VersionsApiService;

import cz.cuni.mff.d3s.spl.restapi.api.NotFoundException;

import javax.ws.rs.core.Response;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-05-23T16:23:56.084+02:00")
public class VersionsApiServiceImpl extends VersionsApiService {
    @Override
    public Response versionsGet() throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}
