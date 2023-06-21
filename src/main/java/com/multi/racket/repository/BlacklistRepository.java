package com.multi.racket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.BlacklistDTO;

public interface BlacklistRepository extends JpaRepository<BlacklistDTO, Integer> {
	
}
