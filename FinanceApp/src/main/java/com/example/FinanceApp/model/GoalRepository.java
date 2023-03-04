package com.example.FinanceApp.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {

	Optional<Goal> findByIdAndUserid(long id, long userid);
	List<Goal> findByName(String name);
	//Get User from Class User, reference to the User Class
	//List<Goal> findByUser(User user);
	//Delete by user Id and goal id
	void deleteByIdAndUserid(long goal_id, long user_id);
	void deleteByUserid(long userId);
	List<Goal> getByUserid(long user_id);
}
