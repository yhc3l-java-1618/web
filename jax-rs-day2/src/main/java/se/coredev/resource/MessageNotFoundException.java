package se.coredev.resource;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

public class MessageNotFoundException extends WebApplicationException {

	private static final long serialVersionUID = -5037747266667601658L;

	public MessageNotFoundException() {
		super(Status.NOT_FOUND);
	}

}
