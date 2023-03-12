package com.example.FinanceApp.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	Optional<Transaction> findById(Long id);
	
	List<Transaction> findByCreatedAtBetweenAndCustomerId(LocalDate startDate, LocalDate endDate, Long Customerid) ;
	
}
