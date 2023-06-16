package com.multi.racket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.ReservationCashDTO;

public interface ReservationCashRepository extends JpaRepository<ReservationCashDTO, String> {
	
}
