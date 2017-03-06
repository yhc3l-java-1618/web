package se.coredev.data;

public final class Message {

	private final String id;
	private final String content;

	public Message(String id, String content) {
		this.id = id;
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return String.format("%s, %s", id, content);
	}

}
