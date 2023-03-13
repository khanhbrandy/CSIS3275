package com.example.FinanceApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.FinanceApp.model.Customer;
import com.example.FinanceApp.model.Instruction;
import com.example.FinanceApp.model.InstructionRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class InstructionController {

	@Autowired
	InstructionRepository instructionRepo;
	
	@GetMapping("/instructions")
	public ResponseEntity<List<Instruction>> getAllInstructions(){
		try {
			List<Instruction> instructions = new ArrayList<>();
			instructionRepo.findAll().forEach(instructions::add);
			if (instructions.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity(instructions, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/instructions/{language}")
	public ResponseEntity<Instruction> getInstructionsByLanguage(@PathVariable String language){
		try {
			List<Instruction> instructions = new ArrayList<>();
			instructionRepo.findByLanguage(language).forEach(instructions::add);
			if (instructions.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity(instructions.get(0), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
