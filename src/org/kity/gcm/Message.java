package org.kity.gcm;

public class Message {

	String title ;
	String body ;
	
	String data = "";

	public Message(String title, String body) {
		super();
		this.title = title;
		this.body = body;
	}
	public Message(String title, String body, String data) {
		super();
		this.title = title;
		this.body = body;
		
		this.data = data;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
}
