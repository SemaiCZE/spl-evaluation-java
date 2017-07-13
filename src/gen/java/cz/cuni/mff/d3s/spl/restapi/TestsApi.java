package cz.cuni.mff.d3s.spl.restapi;

import cz.cuni.mff.d3s.spl.restapi.factories.TestsApiServiceFactory;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Data;
import io.swagger.model.Test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/tests")


@io.swagger.annotations.Api(description = "the tests API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-07-11T18:20:29.625+02:00")
public class TestsApi  {
   private final TestsApiService delegate = TestsApiServiceFactory.getTestsApi();

    @GET
    @Path("/{testId}/revisions/{revisionId}/data")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get data for specific test and version", notes = "", response = Data.class, tags={ "data", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Data.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Version or test not found", response = Data.class),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Data.class) })
    public Response getData(@ApiParam(value = "ID of test (one of `id` fields from `/tests` endpoint)",required=true) @PathParam("testId") String testId
,@ApiParam(value = "ID of measurement version (one of the values returned from `/tests/${testId}/revisions` endpoint)",required=true) @PathParam("revisionId") String revisionId
)
    throws NotFoundException {
        return delegate.getData(testId,revisionId);
    }
    @GET
    @Path("/{testId}/revisions")
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "List of different measurement versions in specific test", notes = "Returns list of different measurements for specific test", response = String.class, responseContainer = "List", tags={ "data", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = String.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Test not found", response = String.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = String.class, responseContainer = "List") })
    public Response getRevisions(@ApiParam(value = "ID of test (one of `id` fields from `/tests` endpoint)",required=true) @PathParam("testId") String testId
)
    throws NotFoundException {
        return delegate.getRevisions(testId);
    }
    @GET
    
    
    @Produces({ "application/json" })
    @io.swagger.annotations.ApiOperation(value = "List of all available measurements (benchmarks)", notes = "Get list of all measurements which have at least one data version in a configured datastore. Each item contains required and optional metadata (for example all metadata from JMH output, such as number of iterations or benchmarking mode). Optional metadata are served as key-value pairs.", response = Test.class, responseContainer = "List", tags={ "data", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Test.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Test.class, responseContainer = "List") })
    public Response getTests()
    throws NotFoundException {
        return delegate.getTests();
    }
}
