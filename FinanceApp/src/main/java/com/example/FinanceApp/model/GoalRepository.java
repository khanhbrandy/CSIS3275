package com.example.FinanceApp.model;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {

	//Optional<Goal> findByIdAndUserid(long id, long userid);
	List<Goal> findByName(String name);
	//Get User from Class User, reference to the User Class
	//List<Goal> findByUser(User user);
	//Delete by user Id and goal id
	void deleteByIdAndCustomer_Id(long goal_id, long customer_id);
	void deleteByCustomer_Id(long customer_id);
	List<Goal> getByCustomer_Id(long customer_id);
	Optional<Goal> findByIdAndCustomer_Id(long goal_id, long customer_id);
	Set<Goal> findByCustomer_Id(long userId);
}
