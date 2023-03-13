package com.example.FinanceApp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class ReportController {
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	TransactionRepository transactionRepo;
	
	@GetMapping("report/customers/{id}/notxn")
	public ResponseEntity getNoTransactions(@PathVariable Long id, @RequestParam(required = false) String type) {
		try {
			int n_transactions = 0;
			Optional<Customer> customer = customerRepo.findById(id);
			if (customer.isPresent()) {
				Customer _customer = customer.get();
				if (type != null) {
					for(Transaction t: _customer.getTransactions()) {
						if (t.getType().equals(type)) {
							n_transactions += 1;
						}
					}
				} else {
					n_transactions = _customer.getTransactions().size();
				}
				return new ResponseEntity<>(n_transactions, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("report/customers/{id}/avgtxn")
	public ResponseEntity getAvgTransactions(@PathVariable Long id, @RequestParam(required = false) String type) {
		try {
			int n_transactions = 0;
			double totalAmount = 0;
			Optional<Customer> customer = customerRepo.findById(id);
			if (customer.isPresent()) {
				Customer _customer = customer.get();
				if (type != null) {
					for(Transaction t: _customer.getTransactions()) {
						if (t.getType().equals(type)) {
							n_transactions += 1;
							totalAmount += t.getAmount();
						}
					}
				} else {
					n_transactions = _customer.getTransactions().size();
					for(Transaction t: _customer.getTransactions()) {
						totalAmount += t.getAmount();
					}
				}
				
				double avgTransaction = totalAmount/n_transactions;
				return new ResponseEntity<>(avgTransaction, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Report Generation. Simple Report
		@GetMapping("/customers/{customer_id}/financial-report")
		public ResponseEntity<FinancialReport>  generateFinancialReport(
				 @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			        @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate, @PathVariable("customer_id") long customerId) {

		    // retrieve transactions for the specified time period
		    List<Transaction> transactions = null;
		    
			try {
				
				transactions = transactionRepo.findByCreatedAtBetweenAndCustomerId(startDate, endDate, customerId);
				if (transactions.isEmpty()) {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
				else {
					 // perform necessary calculations to generate financial report
				    double totalIncome = 0.0;
				    double totalExpenses = 0.0;
				    for (Transaction transaction : transactions) {
				        if (transaction.getAmount() > 0) {
				            totalIncome += transaction.getAmount();
				        } else {
				            totalExpenses -= transaction.getAmount();
				        }
				    }
				    double netIncome = totalIncome - totalExpenses;

				    // create FinancialReport object to return
				 // create FinancialReport object to return
				    FinancialReport financialReport = new FinancialReport(startDate, endDate, totalIncome, totalExpenses, netIncome);
				    return new ResponseEntity <FinancialReport> (financialReport, HttpStatus.OK);
				}
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		   
		}

}
