package se.coredev.web;

public final class Message {

	private final Integer id;
	private final String content;

	public Message(Integer id, String content) {
		this.id = id;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
