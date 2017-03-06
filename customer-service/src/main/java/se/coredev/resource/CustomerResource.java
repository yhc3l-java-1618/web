package se.coredev.resource;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import static se.coredev.data.CustomerParser.*;
import se.coredev.data.Customer;
import se.coredev.repository.CustomerRepository;

@Component
@Path("/customers")
public final class CustomerResource {

	private final CustomerRepository repository;

	public CustomerResource(CustomerRepository repository) {
		this.repository = repository;
	}

	@POST
	public Response addCustomer(String customerString) {
		Customer customer = repository.add(fromString(customerString));
		return Response.status(Status.CREATED).header("Location", "customers/" + customer.getId()).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getCustomerAsXml(@PathParam("id") String id) {
		Customer customer = repository.get(id);
		return customer == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(toXmlString(customer)).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getCustomer(@PathParam("id") String id) {
		Customer customer = repository.get(id);
		return customer == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(customer.toString()).build();
	}

}
