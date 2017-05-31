package cz.cuni.mff.d3s.spl.restapi.api;

import javax.ws.rs.core.Response;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaMSF4JServerCodegen", date = "2017-05-23T16:23:56.084+02:00")
public abstract class VersionsApiService {
    public abstract Response versionsGet() throws NotFoundException;
}
