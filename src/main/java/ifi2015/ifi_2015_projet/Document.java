package ifi2015.ifi_2015_projet;

import java.io.File;

public class Document {
	
	private String name;
	private File UUID;
	private Doctype type;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public File getUUID() {
		return UUID;
	}
	
	public void setUUID(File uUID) {
		UUID = uUID;
	}
	
	public Doctype getType() {
		return type;
	}
	
	public void setType(Doctype type) {
		this.type = type;
	}
	
}
