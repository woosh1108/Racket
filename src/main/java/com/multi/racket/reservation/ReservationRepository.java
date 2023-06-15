package com.multi.racket.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.ReservationDTO;

public interface ReservationRepository extends JpaRepository<ReservationDTO, String> {
	
}
