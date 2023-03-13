package com.example.FinanceApp.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.FinanceApp.model.Customer;
import com.example.FinanceApp.model.Instruction;
import com.example.FinanceApp.model.Reminder;
import com.example.FinanceApp.model.ReminderRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ReminderController {

	
	@Autowired
	ReminderRepository reminderRepo;
	
	@GetMapping("/reminders")
	public ResponseEntity< List<Reminder>> getAllReminders (){
		
		try {
			List<Reminder> reminders = new ArrayList<>();
			reminderRepo.findAll().forEach(reminders::add);
			if (reminders.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity(reminders, HttpStatus.OK);
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
	
	@PostMapping("/reminders")
	public ResponseEntity<Customer> createReminder(@RequestBody Reminder reminder) {
		try {
			Reminder newReminder = new Reminder(reminder.getName(), reminder.getDescription(), reminder.getCreatedAt(),
					reminder.getDeadline(), reminder.getFrequency(),reminder.getStatus());
			reminderRepo.save(newReminder);
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/reminders/{id}")
	public ResponseEntity<Reminder> updateReminder(@PathVariable("id") long id, @RequestBody Reminder reminder) {
		Optional<Reminder> reminderData = reminderRepo.findById(id);

		if (reminderData.isPresent()) {
			Reminder _reminder = reminderData.get();
			_reminder.setName(reminder.getName());
			_reminder.setDescription(reminder.getDescription());
			_reminder.setCreatedAt(reminder.getCreatedAt());
			_reminder.setDeadline(reminder.getDeadline());
			_reminder.setFrequency(reminder.getFrequency());
			_reminder.setStatus(reminder.getStatus());

			return new ResponseEntity<>(reminderRepo.save(_reminder), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/reminders")
	public ResponseEntity<HttpStatus> deleteAllReminders() {
		try {
			reminderRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/reminders/{id}")
	public ResponseEntity<HttpStatus> deleteReminder(@PathVariable("id") long id) {
		try {
			reminderRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
