package cz.cuni.mff.d3s.spl.restapi;

import cz.cuni.mff.d3s.spl.restapi.*;
import io.swagger.model.*;

import org.wso2.msf4j.formparam.FormDataParam;
import org.wso2.msf4j.formparam.FileInfo;

import java.util.List;
import cz.cuni.mff.d3s.spl.restapi.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-07-11T18:20:29.625+02:00")
public abstract class TestsApiService {
    public abstract Response getData(String testId
 ,String revisionId
 ) throws NotFoundException;
    public abstract Response getRevisions(String testId
 ) throws NotFoundException;
    public abstract Response getTests() throws NotFoundException;
}
