package se.coredev.repository;

import se.coredev.data.Customer;

public interface CustomerRepository {

	Customer add(Customer customer);

	Customer get(String id);

	Customer update(Customer customer);

	Customer remove(String id);

}
