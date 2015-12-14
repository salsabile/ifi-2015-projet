package ifi2015.ifi_2015_projet;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DOCUMENT")
public class Document {
	
	@Id
	private String filename;
	
	public Document(String filename) {
		this.filename = filename;
	}
	
	public Document(){
	}
	
	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
}
