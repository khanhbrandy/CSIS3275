package com.example.FinanceApp;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import com.example.FinanceApp.model.Goal;
import com.example.FinanceApp.model.GoalRepository;

import com.example.FinanceApp.model.Customer;
import com.example.FinanceApp.model.CustomerRepository;
import com.example.FinanceApp.model.Transaction;

@SpringBootApplication
public class FinanceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceAppApplication.class, args);
	}
	
	@Bean // right after calling Main method (server started), this Bean method would be called automatically
	ApplicationRunner init(CustomerRepository customerRepo, GoalRepository goalRepository) {
		return (arg) -> {
			// Customer 1
			Customer customer1 = new Customer("Brandy", 30, "brandy@douglascollege.ca", "Student", "Vietnam", "brandy", "123456");
			Transaction transaction1 = new Transaction(60, "food", "expense", "at Walmart");
			customer1.getTransactions().add(transaction1);
			transaction1.setCustomer(customer1);
			Transaction transaction2 = new Transaction(2000, "salary", "income", "side-business");
			customer1.getTransactions().add(transaction2);
			transaction2.setCustomer(customer1);
			customerRepo.save(customer1);
			
			// Customer 2
			Customer customer2 = new Customer("Juan", 30, "juan@douglascollege.ca", "Student", "Columbia", "juan", "123456");
			Transaction transaction3 = new Transaction(140, "clothing", "expense", "at Zara");
			customer2.getTransactions().add(transaction3);
			transaction3.setCustomer(customer2);
			Transaction transaction4 = new Transaction(750, "housing", "expense", "rent");
			customer2.getTransactions().add(transaction4);
			transaction4.setCustomer(customer2);
			customerRepo.save(customer2);
			Transaction transaction5 = new Transaction(2500, "salary", "income", "part-time job");
			customer2.getTransactions().add(transaction5);
			transaction5.setCustomer(customer2);
			customerRepo.save(customer2);
			
			// Customer 3
			Customer customer3 = new Customer("Sumit", 37, "sumit@douglascollege.ca", "Student", "India", "sumit", "123456");
			Transaction transaction6 = new Transaction(90, "food", "expense", "at Save-on");
			customer3.getTransactions().add(transaction6);
			transaction6.setCustomer(customer3);
			Transaction transaction7 = new Transaction(780, "housing", "expense", "rent");
			customer3.getTransactions().add(transaction7);
			transaction7.setCustomer(customer3);
			customerRepo.save(customer3);
			Transaction transaction8 = new Transaction(3000, "investment", "income", "real estate");
			customer3.getTransactions().add(transaction8);
			transaction8.setCustomer(customer3);
			customerRepo.save(customer3);
			
			// Customer 4
			Customer customer4 = new Customer("Akash", 28, "akash@douglascollege.ca", "Student", "India", "akash", "123456");
			Transaction transaction9 = new Transaction(66, "food", "expense", "at TnT");
			customer4.getTransactions().add(transaction9);
			transaction9.setCustomer(customer4);
			Transaction transaction10 = new Transaction(2800, "salary", "income", "freelance");
			customer4.getTransactions().add(transaction10);
			transaction10.setCustomer(customer4);
			customerRepo.save(customer4);
			
			//Some test Goals
			Set <Goal> goalsCustomer1 = new HashSet<>();
			Set <Goal> goalsCustomer2 = new HashSet<>();
			Goal goal1 = new Goal("House",200000,70000,"Money for my house","2025-12-03");
			goal1.setCustomer(customer1);
			Goal goal2 = new Goal("Car",65000,8000, "For my dream Car","2024-05-30");
			goal2.setCustomer(customer1);
			goalsCustomer1.add(goal1);
			goalsCustomer1.add(goal2);
			customer1.setGoal(goalsCustomer1);
			
			
			Goal goal3 = new Goal("House",500000,100000,"Money for my house","2039-03-01");
			goal3.setCustomer(customer2);
			goalsCustomer2.add(goal3);
			customer2.setGoal(goalsCustomer2);
			
			goalRepository.save(goal1);
			goalRepository.save(goal2);
			goalRepository.save(goal3);
			goalRepository.findAll().forEach(System.out::println);
			
		};
	}

}
