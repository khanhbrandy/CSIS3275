package com.example.FinanceApp;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.FinanceApp.model.Goal;
import com.example.FinanceApp.model.GoalRepository;

@SpringBootApplication
public class FinanceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceAppApplication.class, args);
	}
	@Bean
	ApplicationRunner init(GoalRepository goalRepository) {
		return args -> {

			goalRepository.save(new Goal(001,"House",200000,70000,"Money for my house","2025-12-03"));
			goalRepository.save(new Goal(001,"Car",65000, 8000,"For my dream Car","2024-05-30"));
			goalRepository.save(new Goal(002,"House",500000,100000,"Money for my house","2039-03-01"));
			goalRepository.findAll().forEach(System.out::println);
		};
	}

}
