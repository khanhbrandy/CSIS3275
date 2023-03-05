package com.example.FinanceApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Language")
public class Language {
	@Id // make this field the PK of the table
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "languageName")
	private String languageName;
	
	public Language() {}
	
	public Language(String languageName) {
		this.languageName = languageName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	
}
