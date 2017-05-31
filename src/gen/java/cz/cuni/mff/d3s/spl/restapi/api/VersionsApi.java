package cz.cuni.mff.d3s.spl.restapi.api;

import cz.cuni.mff.d3s.spl.restapi.factories.VersionsApiServiceFactory;

import cz.cuni.mff.d3s.spl.restapi.model.Version;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/versions")


@io.swagger.annotations.Api(description = "the versions API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-05-23T16:23:56.084+02:00")
public class VersionsApi  {
   private final VersionsApiService delegate = VersionsApiServiceFactory.getVersionsApi();

    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "List of all available data versions", notes = "Get list of all data versions that are measured and saved in configured datastore. Each version is listed only once.", response = Version.class, responseContainer = "List", tags={ "data", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Version.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Version.class, responseContainer = "List") })
    public Response versionsGet()
    throws NotFoundException {
        return delegate.versionsGet();
    }
}
