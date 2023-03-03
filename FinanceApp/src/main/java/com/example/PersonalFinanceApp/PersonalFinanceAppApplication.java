package com.example.PersonalFinanceApp;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.PersonalFinanceApp.model.Transaction;
import com.example.PersonalFinanceApp.model.TransactionRepository;


@SpringBootApplication
public class PersonalFinanceAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalFinanceAppApplication.class, args);
	}

	@Bean
	ApplicationRunner init(TransactionRepository transactionRepo) {
		return (arg) -> {
			transactionRepo.save(new Transaction(50, "food", "expense", "at Walmart", 1));
			transactionRepo.save(new Transaction(120, "clothing", "expense", "at Aritzia", 1));
			transactionRepo.save(new Transaction(650, "scholarship", "income", "from Douglas", 1));
			transactionRepo.save(new Transaction(30, "gasoline", "expense", "at 6th Ave", 2));
			transactionRepo.save(new Transaction(170, "food", "expense", "at Save-on", 2));
			transactionRepo.findAll().forEach(System.out::println);
		};
	}

}
