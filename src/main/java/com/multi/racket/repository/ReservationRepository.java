package com.multi.racket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.ReservationDTO;

public interface ReservationRepository extends JpaRepository<ReservationDTO, Integer> {
}
