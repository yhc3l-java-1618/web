package se.coredev.data;

public final class Customer {

	private final String id;
	private final String firstName;
	private final String lastName;
	private final String customerNumber;

	public Customer(String id, String firstName, String lastName, String customerNumber) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerNumber = customerNumber;
	}

	public String getId() {
		return id;
	}
	
	public Customer setId(String id){
		return new Customer(id, firstName, lastName, customerNumber);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	@Override
	public String toString() {
		return String.join(",", id, firstName, lastName, customerNumber);
	}

}
