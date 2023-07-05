package com.multi.racket.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.BlacklistDTO;

public interface BlacklistRepository extends JpaRepository<BlacklistDTO, Integer> {
	List<BlacklistDTO> findByBlackDateBefore(LocalDate currentDate);
	
}
