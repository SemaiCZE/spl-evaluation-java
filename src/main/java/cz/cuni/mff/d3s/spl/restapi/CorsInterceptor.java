package cz.cuni.mff.d3s.spl.restapi;

import org.wso2.msf4j.Interceptor;
import org.wso2.msf4j.Request;
import org.wso2.msf4j.Response;
import org.wso2.msf4j.ServiceMethodInfo;

public class CorsInterceptor implements Interceptor {

	@Override
	public boolean preCall(Request request, Response response, ServiceMethodInfo serviceMethodInfo) throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");

		if (request.getHeader("Origin") != null && !request.getHeader("Origin").isEmpty()) {
			response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
		}
		return true;
	}

	@Override
	public void postCall(Request request, int status, ServiceMethodInfo smi) throws Exception {
	}

}