package se.coredev.resource;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

import se.coredev.data.Message;

@Component
@Path("/messages")
public final class MessageResource {

	private static final Map<String, Message> messages = new HashMap<>();

	@Context
	private UriInfo uriInfo;
	
	// CREATE
	@POST
	public Response createMessage(String content) {
		Message message = new Message(UUID.randomUUID().toString(), content);
		messages.put(message.getId(), message);

		URI location = uriInfo.getAbsolutePathBuilder().path(message.getId()).build();
		return Response.created(location).build();
//		return Response.status(Status.CREATED).header("Location", "messages/" + message.getId()).build();
	}

	// READ
	@GET
	@Path("{id}")
	public Response getMessage(@PathParam("id") String id) {
		if (messages.containsKey(id)) {
			return Response.ok(messages.get(id).toString()).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	// UPDATE
	@PUT
	@Path("{id}")
	public Response updateMessage(@PathParam("id") String id, String content) {
		if (messages.containsKey(id)) {
			messages.put(id, new Message(id, content));
			return Response.noContent().build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteMessage(@PathParam("id") String id) {
		if (messages.containsKey(id)) {
			messages.remove(id);
			return Response.noContent().build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

}
