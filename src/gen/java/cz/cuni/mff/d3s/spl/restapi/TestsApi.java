package cz.cuni.mff.d3s.spl.restapi;

import io.swagger.model.*;
import cz.cuni.mff.d3s.spl.restapi.TestsApiService;
import cz.cuni.mff.d3s.spl.restapi.factories.TestsApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Data;
import io.swagger.model.Test;
import io.swagger.model.Version;

import java.util.List;
import cz.cuni.mff.d3s.spl.restapi.NotFoundException;

import java.io.InputStream;

import org.wso2.msf4j.formparam.FormDataParam;
import org.wso2.msf4j.formparam.FileInfo;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/tests")


@io.swagger.annotations.Api(description = "the tests API")

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
    @io.swagger.annotations.ApiOperation(value = "List of different measurement versions in specific test", notes = "Returns list of different measurements for specific test. The versions are returned from oldest to newest according to version timestamp. If the timestamps matches, resulting order is from lexicographical comparison of version ids.", response = Version.class, responseContainer = "List", tags={ "data", })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Success", response = Version.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Test not found", response = Version.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 500, message = "Internal server error", response = Version.class, responseContainer = "List") })
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
