package com.example.PersonalFinanceApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.PersonalFinanceApp.model.Transaction;
import com.example.PersonalFinanceApp.model.TransactionRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TransactionController {
	@Autowired
	TransactionRepository transactionRepo;

	@GetMapping("/transactions")
	public ResponseEntity<List<Transaction>> getAllTransactions(@RequestParam(required = false) String type) {

		try {
			List<Transaction> transactions = new ArrayList<>();
			if (type == null) {
				transactionRepo.findAll().forEach(transactions::add);
			} else {
				transactionRepo.findByType(type).forEach(transactions::add);
			}
			if (transactions.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity(transactions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/transactions/{id}")
	public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
		try {
			Optional<Transaction> transaction = transactionRepo.findById(id);
			if (transaction.isPresent()) {
				return new ResponseEntity<>(transaction.get(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/transactions")
	public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
		try {
			Transaction newTransaction = new Transaction(transaction.getAmount(), transaction.getCategory(),
					transaction.getType(), transaction.getNote(), transaction.getUserId());
			transactionRepo.save(newTransaction);
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/transactions/{id}")
	public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") long id, @RequestBody Transaction transaction) {
		Optional<Transaction> transactionData = transactionRepo.findById(id);

		if (transactionData.isPresent()) {
			Transaction _transaction = transactionData.get();
			_transaction.setAmount(transaction.getAmount());
			_transaction.setCategory(transaction.getCategory());
			_transaction.setType(transaction.getType());
			_transaction.setNote(transaction.getNote());
			_transaction.setUserId(transaction.getUserId());
			return new ResponseEntity<>(transactionRepo.save(_transaction), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/transactions")
	public ResponseEntity<HttpStatus> deleteAllTransactions() {
		try {
			transactionRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/transactions/{id}")
	public ResponseEntity<HttpStatus> deleteTransaction(@PathVariable("id") long id) {
		try {
			transactionRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
