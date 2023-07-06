package com.multi.racket.reservation;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.ReservationDTO;

public interface ReservationService {

	// 현재 캐시 잔액 확인
	public boolean checkSufficientBalance(String memberId, int reservationFee);
	
	// 구장번호로 예약하기 상세조회
	public ReservationDTO reservation(int reservationNo);

	// 예약하기 등록
	public void reservation_insert(String memberId, ReservationDTO reservation, CashDTO cash) throws Exception;

	// 예약 참가하기 상세조회
	public MatchingDTO matching(int matchingNo);

	// 예약 참가하기 등록
	public void matching_insert(String memberId, MatchingDTO matching, CashDTO cash, ReservationDTO reservation) throws Exception;

	// 예약 목록 조회
	public Page<ReservationDTO> reservationlist(int pageNo);

	// 예약 목록 검색
	public Page<ReservationDTO> searchReservations(String type, String keyword, int pageNo, int pageSize);

    // reservation_date가 지난 예약 데이터의 상태를 "경기종료"로 수정하는 메서드
    void updateExpiredReservations(LocalDate currentDate);

    public List<ReservationDTO> getAllReservationInfo();

    // 해당하는 예약 명단에 내 아이디가 있는지
    public boolean existsByMemberIdAndReservationNo(String memberId, int reservationNo);

}
