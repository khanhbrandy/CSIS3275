package com.example.PersonalFinanceApp.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface GoalRepository extends JpaRepository<Goal, Long> {

	    Optional<Goal> findByIdAndUser_Id(long id, long userId);
		List<Goal> findByName(String name);
		//Get User from Class User, reference to the User Class
		//List<Goal> findByUser(User user);
		//Delete by user Id and goal id
		void deleteByIdAndUser_id(long goal_id, long user_id);
		void deleteByUserId(long userId);
		List<Goal> getbyUser_id(long user_id);

}
