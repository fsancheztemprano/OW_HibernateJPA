package com.openwebinars.hibernate.generacionid;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MyEntityTable {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private long id;

	private String text;
	
	public MyEntityTable() {
		
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getId() {
		return id;
	}
	
	
	
	


}