package com.multi.racket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.TrainingMemberlistDTO;

public interface TrainingMemberlistRepository extends JpaRepository<TrainingMemberlistDTO, String> {
}
