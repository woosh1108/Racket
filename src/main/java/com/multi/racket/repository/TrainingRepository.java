package com.multi.racket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.TrainingDTO;

public interface TrainingRepository extends JpaRepository<TrainingDTO, Integer> {
	Optional<TrainingDTO> findByTrainingNo(int trainingNo);
}
