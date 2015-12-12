package ifi2015.ifi_2015_projet;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Message implements Serializable{
	
	@Id
	private String content;
	
	//private Hashtag hashtag;
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
	
	/*public Hashtag getHashtag() {
		return hashtag;
	}
	
	public void setHashtag(Hashtag hashtag) {
		this.hashtag = hashtag;
	}*/
	
	public String toString(){
		return " "+ content+"  "+hashtag;
	}
	
	
}
