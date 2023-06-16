package com.multi.racket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.CashDTO;

public interface CashRepository extends JpaRepository<CashDTO, String> {
	
	
}
