package com.example.FinanceApp.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder, Long>{
	
	Optional<Reminder> findById(Long id);
	List<Reminder> findByName(String name);
	List<Reminder> findByDeadline(String deadline);
	List<Reminder> findByStatus(String status);
	List<Reminder> getByCustomer_Id(Long customer_id);
	Optional<Reminder> findByIdAndCustomer_Id(long reminder_id, long customer_id);
	List<Reminder> findByCustomer_Id(long userId);
	
	//void deleteByIdAndCustomer_Id(long reminder_id, long customer_id);
	//void deleteByCustomer_Id(long customer_id);
}
