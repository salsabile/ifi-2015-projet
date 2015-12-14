package ifi2015.ifi_2015_projet;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
	
	@Id
	private String content;
	private String login;
	
	public Message(String content, String login){
		this.content=content;
		this.login=login;
	}
	
	public Message(){	
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String toString() {
		return this.content;
	}
}
