package com.example.FinanceApp.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "Goal")
public class Goal {

	//Goal Class

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		//Many goals would have a single user
		@ManyToOne(fetch = FetchType.EAGER, optional = false)
		//convention here is name = nameoftable_fieldname
		@JoinColumn(name = "Customer_id", nullable = false) 
		@JsonIgnore
		private Customer customer;

//		@Column(name ="userid")
//		private long userid;
		
		@Column(name= "amount")
		private double amount;
		
		@Column(name="currentAmount")
		private double currentAmount;
		
		@Column(name="name")
		private String name;
		
		@Column(name="description")
		private String description;
		
		@Column(name="deadline")
		private String deadline;
		
		@Column(name="createdAt")
		private LocalDate createdAt;



		public Goal() {}

		public Goal(String name , double amount, double currentAmount , String description,String deadline) {
			// Total amount of the goal
			this.amount=amount;
			//Name of the goal
			this.name = name;
			//Money the user already have for the goal
			this.currentAmount = currentAmount;
			//Goal Description by the user
			this.description = description;
			this.deadline = deadline;
			// Set the creation time.
			this.createdAt = LocalDate.now();

		}




//		public long getUserid() {
//			return userid;
//		}
//
//		public void setUserid(long userid) {
//			this.userid = userid;
//		}

		public double getCurrentAmount() {
			return currentAmount;
		}

		public void setCurrentAmount(double currentAmount) {
			this.currentAmount = currentAmount;
		}

		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getDeadline() {
			return deadline;
		}
		public void setDeadline(String deadline) {
			this.deadline = deadline;
		}

		public LocalDate getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(LocalDate createdAt) {
			this.createdAt = createdAt;
		}

		public void setCustomer(Customer customer ) {
			this.customer = customer;
			
		}
		public Customer getCustomer() {
			return this.customer;
		}



}
