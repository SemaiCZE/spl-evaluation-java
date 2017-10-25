package cz.cuni.mff.d3s.spl.visualization;

import org.apache.commons.io.IOUtils;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;
import java.io.IOException;
import java.io.InputStream;


@Path("/")
public class WebVisualizer {

	@GET
	@Path("/{file : [a-zA-Z_0-9.-]*}")
	public Response explicitFiles(@PathParam("file") String requestedFile) {
		return serveFile(requestedFile);
	}

	@GET
	@Path("/{dir : [a-zA-Z_0-9]*}/{file : [a-zA-Z_0-9.-]*}")
	public Response explicitFiles(@PathParam("dir") String dir, @PathParam("file") String requestedFile) {
		return serveFile(dir + "/" + requestedFile);
	}

	@GET
	@Path("/{dir1 : [a-zA-Z_0-9]*}/{dir2 : [a-zA-Z_0-9]*}/{file : [a-zA-Z_0-9.-]*}")
	public Response explicitFiles(@PathParam("dir1") String dir1, @PathParam("dir2") String dir2, @PathParam("file") String requestedFile) {
		return serveFile(dir1 + "/" + dir2 + "/" + requestedFile);
	}

	@GET
	public Response defaultFile() {
		return serveFile("index.html");
	}

	private Response serveFile(String fileName) {
		try {
			InputStream content = getClass().getResourceAsStream("/cz/cuni/mff/d3s/spl/visualization/" + fileName);
			return Response.ok().entity(content).build();
		} catch (NullPointerException e) {
			return Response.status(404).entity("<h1>Not Found</h1>").build();
		}
	}
}