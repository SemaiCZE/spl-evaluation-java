package cz.cuni.mff.d3s.spl.restapi.impl;

import cz.cuni.mff.d3s.spl.BenchmarkRun;
import cz.cuni.mff.d3s.spl.DataSnapshot;
import cz.cuni.mff.d3s.spl.DataSource;
import cz.cuni.mff.d3s.spl.data.Revision;
import cz.cuni.mff.d3s.spl.restapi.*;

import java.util.ArrayList;
import java.util.List;
import cz.cuni.mff.d3s.spl.restapi.NotFoundException;

import java.io.InputStream;
import java.util.Set;
import java.util.Map;

import cz.cuni.mff.d3s.spl.utils.DataUtils;
import io.swagger.model.Data;
import io.swagger.model.Test;
import org.wso2.msf4j.formparam.FormDataParam;
import org.wso2.msf4j.formparam.FileInfo;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-07-11T18:20:29.625+02:00")
public class TestsApiServiceImpl extends TestsApiService {
    private Map<String, List<Revision>> data;
    public TestsApiServiceImpl(Map<String, List<Revision>> data) {
        this.data = data;
    }

    @Override
    public Response getData(String testId, String revisionId) throws NotFoundException {
        if (data.containsKey(testId)) {
            Map<String, DataSource> revisions = DataUtils.getRevisionMap(data.get(testId));
            if (revisions.containsKey(revisionId)) {
                DataSnapshot revisionData = revisions.get(revisionId).makeSnapshot();
                Data response = new Data();
                response.setUnits("??");
                for (BenchmarkRun run : revisionData.getRuns()) {
                    List<Double> runData = new ArrayList<>();
                    run.getSamples().forEach(runData::add);
                    response.getData().add(runData);
                }
                return Response.ok().entity(new ApiResponse(Response.Status.OK.getStatusCode(), response)).build();
            } else {
                return Response
                        .status(Response.Status.NOT_FOUND)
                        .entity(new ApiResponse(Response.Status.NOT_FOUND.getStatusCode(), "Revision not found."))
                        .build();
            }
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ApiResponse(Response.Status.NOT_FOUND.getStatusCode(), "Test not found."))
                    .build();
        }
    }

    @Override
    public Response getRevisions(String testId) throws NotFoundException {
        if (data.containsKey(testId)) {
            List<String> response = new ArrayList<>();
            for (Revision r : data.get(testId)) {
                response.add(r.name);
            }
            return Response.ok().entity(new ApiResponse(Response.Status.OK.getStatusCode(), response)).build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity(new ApiResponse(Response.Status.NOT_FOUND.getStatusCode(), "Test not found."))
                    .build();
        }
    }

    @Override
    public Response getTests() throws NotFoundException {
        List<Test> response = new ArrayList<>();
        for (String test : data.keySet()) {
            Test currentTest = new Test().id(test).name(test.substring(0, test.indexOf('@')));
            response.add(currentTest);
        }
        return Response.ok().entity(new ApiResponse(Response.Status.OK.getStatusCode(), response)).build();
    }
}
