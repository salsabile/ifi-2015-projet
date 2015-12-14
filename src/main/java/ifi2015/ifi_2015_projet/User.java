package ifi2015.ifi_2015_projet;

import javax.persistence.*;

@Entity
@Table(name="USER")
public class User {

	@Id
	@Column(name="login")
	private String login;
	private String pass;
	private String xsessionId;
	
	public User(){
	
	}
	
	public User(String xSessionId){
		this.xsessionId = xSessionId;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getSession() {
		return xsessionId;
	}
	
	public void setSession(String xsessionId) {
		this.xsessionId = xsessionId;
	}
	
	/*public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}*/
	
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
