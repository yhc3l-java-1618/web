package se.coredev.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import se.coredev.resource.MessageResource;
import se.coredev.resource.NumberFormatExceptionMapper;
import se.coredev.resource.TestContext;
import se.coredev.resource.UserResource;

@Component
public final class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(MessageResource.class);
		register(TestContext.class);
		register(NumberFormatExceptionMapper.class);
		register(UserResource.class);
	}
}
