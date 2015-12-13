package ifi2015.ifi_2015_projet;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
	
	@Id
	private String content;
	private String hashtag;
	
	public Message(String content, String hashtag){
		this.content=content;
		this.hashtag=hashtag;
	}
	
	public Message(){	
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}

	public String getHashtag() {
		return hashtag;
	}

	public void setHashtag(String hashtag) {
		this.hashtag = hashtag;
	}
	
	public String toString() {
		return this.content + "  " + this.hashtag;
	}
}
