package com.example.FinanceApp.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {
	@Id // make this field the PK of the table
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "createdAt")
	private String createdAt;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private int age;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "profession")
	private String profession;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Transaction> transactions = new HashSet<>();

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setSections(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	public Customer() {} 
	public Customer(String name, int age, String email, String profession, String country, String username, String password) {
		this.setCreatedAt();
		this.name = name;
		this.age = age;
		this.email = email;
		this.profession = profession;
		this.country = country;
		this.username = username;
		this.password = password;
	}
	
	public void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
		transaction.setCustomer(this);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
