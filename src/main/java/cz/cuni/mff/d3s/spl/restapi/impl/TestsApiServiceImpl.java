package cz.cuni.mff.d3s.spl.restapi.impl;

import cz.cuni.mff.d3s.spl.restapi.*;

import java.util.List;
import cz.cuni.mff.d3s.spl.restapi.NotFoundException;

import java.io.InputStream;

import org.wso2.msf4j.formparam.FormDataParam;
import org.wso2.msf4j.formparam.FileInfo;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-07-11T18:20:29.625+02:00")
public class TestsApiServiceImpl extends TestsApiService {
    private String arg;
    public TestsApiServiceImpl(String arg) {
        this.arg = arg;
    }

    @Override
    public Response getData(String testId
, String revisionId
 ) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getRevisions(String testId
 ) throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
    @Override
    public Response getTests() throws NotFoundException {
        // do some magic!
        return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, this.arg)).build();
    }
}
