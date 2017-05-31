package cz.cuni.mff.d3s.spl.restapi.api;

import cz.cuni.mff.d3s.spl.restapi.factories.VersionApiServiceFactory;

import io.swagger.annotations.ApiParam;

import cz.cuni.mff.d3s.spl.restapi.model.Data;
import cz.cuni.mff.d3s.spl.restapi.model.Test;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

@Path("/version")


@io.swagger.annotations.Api(description = "the version API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-05-23T16:23:56.084+02:00")
public class VersionApi  {
   private final VersionApiService delegate = VersionApiServiceFactory.getVersionApi();

    @GET
    @Path("/{versionId}/tests")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "List of different measurements in specific version", notes = "Returns list of different measurements in the version. Each item contains metadata from JMH output, such as number of iterations or benchmarking mode.", response = Test.class, responseContainer = "List", tags={ "data", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Test.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Version not found", response = Test.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Test.class, responseContainer = "List") })
    public Response versionVersionIdTestsGet(@ApiParam(value = "ID of version (one of the values returned from `/versions` endpoint)",required=true) @PathParam("versionId") String versionId
)
    throws NotFoundException {
        return delegate.versionVersionIdTestsGet(versionId);
    }
    @GET
    @Path("/{versionId}/values/{testId}")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get data for specific test and version", notes = "", response = Data.class, tags={ "data", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Data.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Version or test not found", response = Data.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Data.class) })
    public Response versionVersionIdValuesTestIdGet(@ApiParam(value = "ID of version (one of the values returned from `/versions` endpoint)",required=true) @PathParam("versionId") String versionId
,@ApiParam(value = "ID of test in the version (one of `id` fields from `/version/{versionId}/tests` endpoint)",required=true) @PathParam("testId") String testId
)
    throws NotFoundException {
        return delegate.versionVersionIdValuesTestIdGet(versionId,testId);
    }
}
