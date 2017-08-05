package cz.cuni.mff.d3s.spl.restapi.impl;

import cz.cuni.mff.d3s.spl.data.*;
import cz.cuni.mff.d3s.spl.restapi.ApiResponse;
import cz.cuni.mff.d3s.spl.restapi.NotFoundException;
import cz.cuni.mff.d3s.spl.restapi.TestsApiService;
import cz.cuni.mff.d3s.spl.utils.DataUtils;
import io.swagger.model.Data;
import io.swagger.model.Test;
import io.swagger.model.Version;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-07-11T18:20:29.625+02:00")
public class TestsApiServiceImpl extends TestsApiService {
    private Map<DataInfo, List<Revision>> data;
    public TestsApiServiceImpl(Map<DataInfo, List<Revision>> data) {
        this.data = data;
    }

    @Override
    public Response getData(String testId, String revisionId) throws NotFoundException {
        DataInfo testInfo = new DataInfo(testId);
        if (data.containsKey(testInfo)) {
            Map<String, DataSource> revisions = DataUtils.getRevisionMap(data.get(testInfo));
            if (revisions.containsKey(revisionId)) {
                DataSource revisionDataSource = revisions.get(revisionId);
                DataSnapshot revisionData = revisionDataSource.makeSnapshot();
                Data response = new Data();
                response.setUnits(revisionDataSource.getUnits());
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
        DataInfo testInfo = new DataInfo(testId);
        if (data.containsKey(testInfo)) {
            List<Version> response = new ArrayList<>();
            for (Revision r : data.get(testInfo)) {
                response.add(new Version().id(r.name).timestamp(r.timestamp));
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
        for (DataInfo test : data.keySet()) {
            Test currentTest = new Test().id(test.getId()).name(test.getName()).metadata(test.getMetadata());
            response.add(currentTest);
        }
        return Response.ok().entity(new ApiResponse(Response.Status.OK.getStatusCode(), response)).build();
    }
}
