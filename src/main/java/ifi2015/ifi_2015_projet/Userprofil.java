package ifi2015.ifi_2015_projet;

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
	private String competence;
	private String projet;
	
	public Userprofil(String login, String email, String facebookid, String twitterid, String linkedinid, String competence, String projet){
		this.login = login;
		this.email = email;
		this.facebookid = facebookid;
		this.twitterid = twitterid;
		this.linkedinid = linkedinid;
		this.competence = competence;
		this.projet = projet;
	}
	
	public void hydrater() {
		if (this.email == "" ) {
			this.setEmail("adresse@mail.com");
		}
		if (this.facebookid == "" ) {
			this.setFacebookid("facebook");
		}
		if (this.twitterid == "" ) {
			this.setTwitterid("twitter");
		}
		if (this.linkedinid == "" ) {
			this.setLinkedinid("linkedin");
		}
		if (this.competence == "" ) {
			this.setCompetence("competence");
		}
		if (this.projet == "" ) {
			this.setProjet("projet");
		}
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
	
	public String getCompetence() {
		return competence;
	}

	public void setCompetence(String competence) {
		this.competence = competence;
	}

	public String getProjet() {
		return projet;
	}

	public void setProjet(String projet) {
		this.projet = projet;
	}

	@Override
	public String toString(){
		return "login "+login;
	}
	
}