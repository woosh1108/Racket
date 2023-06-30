package com.multi.racket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;

public interface ReservationRepository extends JpaRepository<ReservationDTO, Integer> {
	Optional<ReservationDTO> findByReservationNo(int reservationNo);

}
