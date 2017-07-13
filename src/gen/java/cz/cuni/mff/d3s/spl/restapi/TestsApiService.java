package cz.cuni.mff.d3s.spl.restapi;

import javax.ws.rs.core.Response;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-07-11T18:20:29.625+02:00")
public abstract class TestsApiService {
    public abstract Response getData(String testId
 ,String revisionId
 ) throws NotFoundException;
    public abstract Response getRevisions(String testId
 ) throws NotFoundException;
    public abstract Response getTests() throws NotFoundException;
}
