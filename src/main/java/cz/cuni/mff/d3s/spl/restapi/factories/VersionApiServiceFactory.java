package cz.cuni.mff.d3s.spl.restapi.factories;

import cz.cuni.mff.d3s.spl.restapi.api.VersionApiService;
import cz.cuni.mff.d3s.spl.restapi.impl.VersionApiServiceImpl;

public class VersionApiServiceFactory {
    private final static VersionApiService service = new VersionApiServiceImpl();

    public static VersionApiService getVersionApi() {
        return service;
    }
}
