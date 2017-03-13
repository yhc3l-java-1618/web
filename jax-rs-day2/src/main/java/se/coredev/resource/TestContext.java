package se.coredev.resource;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import javax.ws.rs.core.UriInfo;

@Path("test")
public final class TestContext {

	@Context
	private HttpHeaders headers;

	@Context
	private UriInfo uriInfo;

	@GET
	@Path("one/{id}")
	public String getMessage1(@PathParam("id") String id) {
		if ("1001".equals(id)) {
			return "Hello one!";
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}

	@GET
	@Path("two/{id}")
	public String getMessage2(@PathParam("id") String id) {
		int idNumber = Integer.parseInt(id);
		if (1001 == idNumber) {
			return "Hello two!";
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	@GET
	@Path("three/{id}")
	public String getMessage3(@PathParam("id") String id) {
		int idNumber = Integer.parseInt(id);
		if (1001 == idNumber) {
			return "Hello two!";
		}
		throw new MessageNotFoundException();
	}

	@GET
	@Path("uri-builder")
	public String testUriBuilder() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nPath:").append(uriInfo.getPath());
		builder.append("\nBase uri:").append(uriInfo.getBaseUri());
		builder.append("\nAbsolute path:").append(uriInfo.getAbsolutePath());
		builder.append("\nRequest uri:").append(uriInfo.getRequestUri());
		builder.append("\nPath segments:").append(uriInfo.getPathSegments());
		return builder.toString();
	}

	@GET
	@Path("uri-info")
	public String testUriInfo() {
		StringBuilder builder = new StringBuilder();
		uriInfo.getQueryParameters().forEach((k, v) -> {
			builder.append("Key:").append(k).append(" Value(s:)").append(v).append("\n");
		});

		return builder.toString();
	}

	@GET
	@Path("/secret-message")
	public Response getSecretMessage(@HeaderParam("token") String token) {
		if ("k0k0sk4k4".equals(token)) {
			return Response.ok("Hello Sir!").build();
		}
		return Response.status(Status.UNAUTHORIZED).build();
	}

	@GET
	public String testHeaders() {
		// String accept = request.getHeader("Accept");
		return headers.getHeaderString("Accept");
	}

}
