package com.example.FinanceApp.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructionRepository extends JpaRepository<Instruction, Long> {

	Optional<Instruction> findById(Long id);
	List<Instruction> findByLanguage(String language);
}
