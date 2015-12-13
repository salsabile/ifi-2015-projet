package ifi2015.ifi_2015_projet;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
	
	@Id
	private String content;
	
	public Message(String content){
		this.content=content;
	}
	
	public Message(){	
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String toString() {
		return this.content;
	}
}
