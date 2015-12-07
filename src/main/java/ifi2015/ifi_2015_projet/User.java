package ifi2015.ifi_2015_projet;

public class User {

	private String login;
	private String email;
	//private String photo;
	private int facebookid;
	private Skill skill;
	private Projet projet;

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
	
	public int getFacebookid() {
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
	
}
