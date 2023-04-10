package com.example.FinanceApp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FinanceApp.model.Customer;
import com.example.FinanceApp.model.CustomerRepository;
import com.example.FinanceApp.model.Instruction;
import com.example.FinanceApp.model.Reminder;
import com.example.FinanceApp.model.ReminderRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ReminderController {

	
	@Autowired
	ReminderRepository reminderRepo;
	
	@Autowired
	CustomerRepository customerRepo;
	
	@GetMapping("/{user_id}/reminders")
	public ResponseEntity< List<Reminder>> getAllReminders (@PathVariable("user_id") long user_id){
		
		try {
			List<Reminder> reminders = new ArrayList<>();
			reminderRepo.getByCustomer_Id(user_id).forEach(reminders::add);
			if (reminders.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity(reminders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{user_id}/reminders/{rId}")
	public ResponseEntity< Reminder> getAllReminders (@PathVariable("user_id") long user_id,@PathVariable("rId") long rId){
		
		try {
			Optional<Reminder> reminder = reminderRepo.findByIdAndCustomer_Id(rId,user_id); 
			
			if (reminder.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity(reminder, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/reminders/name/{name}")
	public ResponseEntity< List<Reminder>> getReminderByName(@PathVariable("name") String name){
		
		try {
			List<Reminder> reminders = new ArrayList<>();
			reminderRepo.findByName(name).forEach(reminders::add);
			if (reminders.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity(reminders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/reminders/status/{status}")
	public ResponseEntity< List<Reminder>> getReminderByStatus(@PathVariable("status") String status){
		
		try {
			List<Reminder> reminders = new ArrayList<>();
			reminderRepo.findByStatus(status).forEach(reminders::add);
			if (reminders.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity(reminders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/reminders/deadline/{deadline}")
	public ResponseEntity< List<Reminder>> getReminderByDeadline(@PathVariable("deadline") String deadline){
		
		try {
			List<Reminder> reminders = new ArrayList<>();
			reminderRepo.findByDeadline(deadline).forEach(reminders::add);
			if (reminders.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity(reminders, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/{user_id}/reminders")
	public ResponseEntity<Customer> createReminder(@PathVariable("user_id") long user_id,@RequestBody Reminder reminder) {
		try {
			Reminder newReminder = new Reminder(reminder.getName(), reminder.getDescription(), reminder.getAmount(), reminder.getCreatedAt(),
					reminder.getDeadline(), reminder.getFrequency(),reminder.getStatus());
			Optional<Customer> user = customerRepo.findById(user_id);
			if(user.isPresent()) {
				Customer customer = user.get();
				newReminder.setCustomer(customer);
				reminderRepo.save(newReminder);
				return new ResponseEntity<>(HttpStatus.CREATED);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/{user_id}/reminders/{id}")
	public ResponseEntity<Reminder> updateReminder(@PathVariable("user_id") long user_id,@PathVariable("id") long id, @RequestBody Reminder reminder) {
		
		Optional<Customer> user = customerRepo.findById(user_id);
		if(user.isPresent()) {
			Customer customer = user.get();
			Optional<Reminder> reminderData = reminderRepo.findByIdAndCustomer_Id(id,user_id);
			if (reminderData.isPresent()) {
				Reminder _reminder = reminderData.get();
				_reminder.setName(reminder.getName());
				_reminder.setDescription(reminder.getDescription());
				_reminder.setCreatedAt(reminder.getCreatedAt());
				_reminder.setDeadline(reminder.getDeadline());
				_reminder.setFrequency(reminder.getFrequency());
				_reminder.setStatus(reminder.getStatus());
				_reminder.setCustomer(customer);
				return new ResponseEntity<>(reminderRepo.save(_reminder), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
	}
	
	@DeleteMapping("/{user_id}/reminders")
	public ResponseEntity<HttpStatus> deleteAllReminders(@PathVariable("user_id") long user_id) {
		try {
			Optional<Customer> user = customerRepo.findById(user_id);
			if(user.isPresent()) {
				Customer customer = user.get();
				List<Reminder> reminders = reminderRepo.findByCustomer_Id(user_id);
				if(!reminders.isEmpty()) {

					reminders.forEach(
				            (reminder) -> { reminderRepo.deleteById(reminder.getId()); });
					
				}
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{user_id}/reminders/{id}")
	public ResponseEntity<HttpStatus> deleteReminder(@PathVariable("user_id") long user_id,@PathVariable("id") long id) {
		try {
			Optional<Customer> user = customerRepo.findById(user_id);
			if(user.isPresent()) {
				Customer customer = user.get();
				Optional<Reminder> reminder = reminderRepo.findByIdAndCustomer_Id(id,user_id);
				if(!reminder.isEmpty()) {
					reminderRepo.deleteById(id);
					return new ResponseEntity<>(HttpStatus.OK);
				}
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
