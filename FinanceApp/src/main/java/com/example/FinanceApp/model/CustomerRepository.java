package com.example.FinanceApp.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findById(Long id);
	List<Customer> findByName(String name); 
	Optional<Customer> findByUsername(String username); 
	List<Customer> findByNameContaining(String substring); 
}

