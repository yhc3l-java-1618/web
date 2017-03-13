package se.coredev.data;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class User {

	@XmlElement
	private String id;
	@XmlElement
	private String username;
	@XmlElement(name="role")
	@XmlElementWrapper(name="roles")
	private Collection<String> roles;

	@SuppressWarnings("unused")
	private User() {}

	public User(String id, String username, Collection<String> roles) {
		this.id = id;
		this.username = username;
		this.roles = roles;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public Collection<String> getRoles() {
		return roles;
	}

	public User addRole(String role) {
		roles.add(role);
		return this;
	}

}
