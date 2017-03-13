package se.coredev.resource;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;

import se.coredev.data.User;

@Component
@Path("users")
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
public final class UserResource {

	@Context
	private UriInfo uriinfo;

	private Map<String, User> users;

	public UserResource() {
		this.users = new HashMap<>();
	}

	@POST
	public Response addUser(User user) {
		String id = UUID.randomUUID().toString();
		user = new User(id, user.getUsername(), user.getRoles());

		users.put(id, user);

		URI location = uriinfo.getAbsolutePathBuilder().path(id).build();
		return Response.created(location).build();
	}

	@GET
	@Path("{id}")
	public User getUser(@PathParam("id") String id){
		if (users.containsKey(id)){
			return users.get(id);
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}

}
