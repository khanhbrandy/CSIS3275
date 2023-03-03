package com.example.PersonalFinanceApp.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	Optional<Transaction> findById(Long id); 
	List<Transaction> findByCategory(String category);
	List<Transaction> findByType(String type);
}
