package com.multi.racket.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;

public interface ReservationRepository extends JpaRepository<ReservationDTO, Integer> {
	List<ReservationDTO> findByMemberId(String memberId);
	Page<ReservationDTO> findAllByMemberId(String memberId, Pageable pageable);
	List<ReservationDTO> findByReservationDateAfter(Date reservationDate);
}
