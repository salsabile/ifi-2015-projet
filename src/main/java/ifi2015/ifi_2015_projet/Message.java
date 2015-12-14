package ifi2015.ifi_2015_projet;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message {
	
	@Id
	private String content;
	private String login;
	private ArrayList<String> hashtags;
	
	public Message(String content, String login){
		this.content=content;
		this.login=login;
		this.hashtags = new ArrayList<String>();
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
	
	public ArrayList<String> getHashtags() {
		return hashtags;
	}

	public void setHashtags(ArrayList<String> hashtags) {
		this.hashtags = hashtags;
	}
	
	public void addHashtag(String hashtag) {
		this.hashtags.add(hashtag);
	}

	public String toString() {
		return this.content;
	}
}
