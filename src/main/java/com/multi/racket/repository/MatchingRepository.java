package com.multi.racket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.racket.domain.MatchingDTO;

public interface MatchingRepository extends JpaRepository<MatchingDTO, Integer> {
	@Query("SELECT COUNT(*) FROM MatchingDTO r WHERE r.reservationNo = :reservationNo")
    int getParticipantCount(@Param("reservationNo") int reservationNo); // 예약한 인원 수 조회 메서드 추가
	List<MatchingDTO> findByMemberId(String memberId);
	List<MatchingDTO> findAllByReservationNo(int reservationNo);
	MatchingDTO findByReservationNoAndMemberId(int reservationNo, String memberId);
}
