package com.example.FinanceApp.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder, Long>{
	
	Optional<Reminder> findById(Long id);
	List<Reminder> findByName(String name);
	List<Reminder> findByDeadline(String deadline);
	List<Reminder> findByStatus(String status);

}
