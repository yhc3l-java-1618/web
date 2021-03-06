package se.coredev.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import se.coredev.resource.CustomerResource;
import se.coredev.resource.TestResource;

@Component
public final class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(CustomerResource.class);
		register(TestResource.class);
	}
}
