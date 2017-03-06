package se.coredev.repository.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import se.coredev.data.Customer;
import se.coredev.repository.CustomerRepository;

@Component
public final class InMemoryCustomerRepository implements CustomerRepository {

	private final Map<String, Customer> customers = new HashMap<>();

	@Override
	public Customer add(Customer customer) {

		String id = UUID.randomUUID().toString();
		customer = customer.setId(id);
		customers.put(id, customer);

		return customer;
	}

	@Override
	public Customer get(String id) {
		return customers.get(id);
	}

	@Override
	public Customer update(Customer customer) {
		return customers.replace(customer.getId(), customer);
	}

	@Override
	public Customer remove(String id) {
		return customers.remove(id);
	}

}
