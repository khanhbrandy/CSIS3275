package com.example.FinanceApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FinanceApp.model.Customer;
import com.example.FinanceApp.model.CustomerRepository;
import com.example.FinanceApp.model.Transaction;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TransactionController {
	@Autowired
	CustomerRepository customerRepo;
	@GetMapping("/customers/{customerId}/transactions")
	public ResponseEntity<Set<Transaction>> getCustomer(@PathVariable Long customerId) {
		try {
			Optional<Customer> customer = customerRepo.findById(customerId);
			
			if (customer.isPresent()) {
				Customer aCustomer = customer.get();
				Set<Transaction> transactions = aCustomer.getTransactions();
				return new ResponseEntity<>(transactions, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
