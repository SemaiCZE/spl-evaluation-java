package cz.cuni.mff.d3s.spl.restapi.factories;

import cz.cuni.mff.d3s.spl.restapi.TestsApiService;
import cz.cuni.mff.d3s.spl.restapi.impl.TestsApiServiceImpl;

public class TestsApiServiceFactory {
    private static TestsApiService service = null;

    public static void setService(TestsApiService service) {
        TestsApiServiceFactory.service = service;
    }

    public static TestsApiService getTestsApi() {
        return service;
    }
}
