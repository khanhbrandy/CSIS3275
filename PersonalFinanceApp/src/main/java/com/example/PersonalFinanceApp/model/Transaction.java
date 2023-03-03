package com.example.PersonalFinanceApp.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	@Id // make this field the PK of the table
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "amount")
	private double amount;
	@Column(name = "createdAt")
	private String createdAt;
	@Column(name = "category")
	private String category; // income or expense
	@Column(name = "type")
	private String type;
	@Column(name = "note")
	private String note;
	@Column(name = "userId")
	private long userId;
	
	

	public Transaction() {
	}

	public Transaction(double amount, String category, String type, String note, long userId) {
		this.amount = amount;
		this.setCreatedAt();
		this.category = category;
		this.type = type;
		this.note = note;
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt() {
		LocalDateTime current = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String formattedDateTime = current.format(formatter);
		this.createdAt = formattedDateTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	
}
