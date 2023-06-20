package com.multi.racket.reservation;

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
	public void matching_insert(String memberId, MatchingDTO matching, CashDTO cash) throws Exception;

}
