package userprofil.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Userprofil {

	@Id
	private String login;
	private String email;
	private String facebookid;
	private String twitterid;
	private String linkedinid;
	
	public Userprofil(String login, String email, String facebookid, String twitterid, String linkedinid){
		this.login = login;
		this.email = email;
		this.facebookid = facebookid;
		this.twitterid = twitterid;
		this.linkedinid = linkedinid;
	}
	
	public Userprofil(){
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFacebookid() {
		return facebookid;
	}
	
	public void setFacebookid(String facebookid) {
		this.facebookid = facebookid;
	}
	
	public String getTwitterid() {
		return twitterid;
	}
	
	public void setTwitterid(String twitterid) {
		this.twitterid = twitterid;
	}
	
	public String getLinkedinid() {
		return linkedinid;
	}
	
	public void setLinkedinid(String linkedinid) {
		this.linkedinid = linkedinid;
	}
	
	@Override
	public String toString(){
		return "login "+login;
	}
	
}
