package ifi2015.ifi_2015_projet;

import javax.persistence.*;

@Entity
@Table(name="USER")
public class User {

	@Id
	@Column(name="login")
	private String login;
	
	@Column(name = "email")
	private String email;
	//private String photo;
	/*private int facebookid;
	private Skill skill;
	private Projet projet;*/
	
	public User(){
		this.login = "testé";
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
	
/*	public int getFacebookid() {
		return facebookid;
	}
	
	public void setFacebookid(int facebookid) {
		this.facebookid = facebookid;
	}
	
	public Skill getSkill() {
		return skill;
	}
	
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	*/
	@Override
	public String toString(){
		return "login "+login;
	}
	
}
