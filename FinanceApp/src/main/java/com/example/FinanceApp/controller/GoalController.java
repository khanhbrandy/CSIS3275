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

import com.example.FinanceApp.model.Goal;
import com.example.FinanceApp.model.GoalRepository;

@CrossOrigin(origins = "http:/localhost:8081") // For Vue.JS
@RestController
@RequestMapping("/api")

public class GoalController {
	@Autowired
	GoalRepository goalRepository;

	// Get all the goals for an user
	@GetMapping("/users/{user_id}/goals")
	public ResponseEntity<List<Goal>> getAllGoals(@PathVariable("user_id") long user_id) {
		try {
			List<Goal> allGoals = new ArrayList<Goal>();
			// To-do
			allGoals = goalRepository.getByUserid(user_id);
			if (allGoals.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else
				return new ResponseEntity<>(allGoals, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//Create a new Goal for a given user
	@PostMapping("/users/{user_id}/goals")
	public ResponseEntity<Goal> createGoal(@PathVariable("user_id") long user_id, @RequestBody Goal newGoal) {
		try {
			Goal new_goal = goalRepository.save(new Goal(user_id, newGoal.getName(), newGoal.getAmount(),
					newGoal.getCurrentAmount(), newGoal.getDescription(), newGoal.getDeadline()));
			return new ResponseEntity<Goal>(new_goal, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//Update/modify a Goal
@PutMapping("/users/{user_id}/goals/{goal_id}")
public ResponseEntity <Goal> updatedGoal(@PathVariable("user_id") long user_id,@PathVariable("goal_id") long goal_id, @RequestBody Goal updated_Goal ){
try {
	Optional<Goal> goalData = goalRepository.findByIdAndUserid(goal_id , user_id);
	if(goalData.isPresent()) {
		Goal _goal = goalData.get();
		_goal.setName(updated_Goal.getName());
		_goal.setAmount(updated_Goal.getAmount());
		_goal.setCurrentAmount(updated_Goal.getCurrentAmount());
		_goal.setDeadline(updated_Goal.getDeadline());
		_goal.setDescription(updated_Goal.getDescription());
		_goal.setUserid(user_id);
		//Check, should it be when the user modify it?
		_goal.setCreatedAt(updated_Goal.getCreatedAt());
		return new ResponseEntity<Goal>(goalRepository.save(_goal),HttpStatus.OK);

	}
	else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}catch(Exception e) {
	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
}
}

@DeleteMapping("/users/{users_id}/goals/{goal_id}")
public ResponseEntity<HttpStatus> deleteGoal(@PathVariable("user_id") long user_id,@PathVariable("goal_id") long goal_id) {
	try {
		goalRepository.deleteByIdAndUserid( goal_id, user_id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

//Delete All goals for an specific user
@DeleteMapping("/users/{users_id}/goals")
public ResponseEntity<HttpStatus> deleteAllGoals(@PathVariable("users_id") long userId) {
	try {
		goalRepository.deleteByUserid(userId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}




}
