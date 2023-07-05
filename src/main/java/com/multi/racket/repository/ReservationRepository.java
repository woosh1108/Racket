	package com.multi.racket.repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.multi.racket.domain.ReservationDTO;

public interface ReservationRepository extends JpaRepository<ReservationDTO, Integer> {
	Optional<ReservationDTO> findByReservationNo(int reservationNo);
	
	List<ReservationDTO> findByMemberId(String memberId);
    Page<ReservationDTO> findAllByMemberId(String memberId, Pageable pageable);
    List<ReservationDTO> findByReservationDateAfter(Date reservationDate);
    
    // 예약 목록 조회
    Page<ReservationDTO> findAllByReservationStatus(String ReservationStatus, Pageable pageable);

    // 예약 목록 검색
	Page<ReservationDTO> findByReservationMetContaining(String keyword, Pageable pageable);
	Page<ReservationDTO> findByReservationGenderContaining(String keyword, Pageable pageable);
	Page<ReservationDTO> findByGradeSettingContaining(String keyword, Pageable pageable);

	List<ReservationDTO> findByReservationDateBeforeAndReservationStatus(LocalDate currentDate, String string);
	
	@Modifying
    @Query("UPDATE ReservationDTO r SET r.reservationStatus = :status WHERE r.reservationNo = :reservationNo")
    void updateReservationStatus(@Param("reservationNo") int reservationNo, @Param("status") String status);
	
}
