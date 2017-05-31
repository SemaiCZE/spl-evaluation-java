package cz.cuni.mff.d3s.spl.restapi.factories;

import cz.cuni.mff.d3s.spl.restapi.api.VersionsApiService;
import cz.cuni.mff.d3s.spl.restapi.impl.VersionsApiServiceImpl;

public class VersionsApiServiceFactory {
    private final static VersionsApiService service = new VersionsApiServiceImpl();

    public static VersionsApiService getVersionsApi() {
        return service;
    }
}
