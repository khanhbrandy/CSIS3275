package com.example.FinanceApp.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Goal")
public class Goal {

	//Goal Class

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long id;
		
		@Column(name ="userid")
		private long userid;
		
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
		private String createdAt;



		public Goal() {}

		public Goal(long userid , String name, double amount , double currentAmount,String description, String deadline) {
			//userid
			this.userid=userid;
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
			String today = LocalDate.now().toString();
			this.createdAt = today;

		}




		public long getUserid() {
			return userid;
		}

		public void setUserid(long userid) {
			this.userid = userid;
		}

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

		public String getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(String createdAt) {
			this.createdAt = createdAt;
		}



}
