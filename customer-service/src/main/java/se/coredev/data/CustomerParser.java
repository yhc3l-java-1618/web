package se.coredev.data;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;

public final class CustomerParser {

	public static Customer fromString(String customerString) {

		String[] parts = customerString.split(",");
		String id = parts[0];
		String firstName = parts[1];
		String lastName = parts[2];
		String customerNumber = parts[3];

		return new Customer(id, firstName, lastName, customerNumber);
	}

	public static Customer fromXmlString(String customerCustomer) throws Exception {

		Element root = new Builder().build(customerCustomer, null).getRootElement();
		String id = root.getFirstChildElement("id").getValue();
		String firstName = root.getFirstChildElement("firstName").getValue();
		String lastName = root.getFirstChildElement("lastName").getValue();
		String customerNumber = root.getFirstChildElement("customerNumber").getValue();

		return new Customer(id, firstName, lastName, customerNumber);
	}

	public static String toXmlString(Customer customer) {
		
		Element root = new Element("customer");
		root.appendChild(createElement("id", customer.getId()));
		root.appendChild(createElement("firstName", customer.getFirstName()));
		root.appendChild(createElement("lastName", customer.getLastName()));
		root.appendChild(createElement("customerNumber", customer.getCustomerNumber()));

		return new Document(root).toXML();
	}

	private static Element createElement(String name, String value) {
		Element element = new Element(name);
		element.appendChild(value);
		return element;
	}
}
