package com.example.FinanceApp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.FinanceApp.model.Customer;
import com.example.FinanceApp.model.CustomerRepository;
import com.example.FinanceApp.model.FinancialReport;
import com.example.FinanceApp.model.Transaction;
import com.example.FinanceApp.model.TransactionRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TransactionController {
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	TransactionRepository transactionRepo;
	
	@GetMapping("/customers/{customerId}/transactions")
	public ResponseEntity<Set<Transaction>> getTransactions(@PathVariable Long customerId) {
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
	
	@PostMapping("/customers/{customerId}/transactions")
	public ResponseEntity<Customer> createTransaction(@PathVariable Long customerId, @RequestBody Transaction transaction) {
		try {
			Optional<Customer> customer = customerRepo.findById(customerId);
			Transaction newTransaction = new Transaction(transaction.getAmount(), transaction.getCategory(), transaction.getType(), transaction.getNote());
			if (customer.isPresent()) {
				Customer aCustomer = customer.get();
				aCustomer.getTransactions().add(newTransaction);
				newTransaction.setCustomer(aCustomer);
				customerRepo.save(aCustomer);
			}
			
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/customers/{customerId}/transactions/{id}")
	public ResponseEntity<Customer> updateTransaction(@PathVariable("id") long id, @PathVariable("customerId") long customerId,
			@RequestBody Transaction transaction) {
		Optional<Customer> customer = customerRepo.findById(customerId);

		if (customer.isPresent()) {
			Customer aCustomer = customer.get();
			for (Transaction t : aCustomer.getTransactions()) {
				if (t.getId() == id ) {
					t.setAmount(transaction.getAmount());
		        	t.setCategory(transaction.getCategory());
		        	t.setType(transaction.getType());
		        	t.setNote(transaction.getNote());
				}
			}
			return new ResponseEntity<>(customerRepo.save(aCustomer), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Delete Customers transaction
	@DeleteMapping("/customers/{customerId}/transactions/{id}")
	public ResponseEntity<HttpStatus> deleteTransaction(@PathVariable("id") long id, @PathVariable("customerId") long customerId) {
		try {
			Optional<Customer> customer = customerRepo.findById(customerId);
			if (customer.isPresent()) {
				Optional<Transaction> transaction = transactionRepo.findById(id);
				if (transaction.isPresent()) {
					transactionRepo.deleteById(id);
				}
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}
