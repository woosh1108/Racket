package com.multi.racket.reservation;

import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;

public interface ReservationService {
	// 구장번호로 예약하기 상세조회
	public ReservationDTO reservation(int reservationNo);
	
	// 예약하기 등록
	public void reservation_insert(ReservationDTO reservation, CashDTO cash) throws Exception;

	// 예약 참가하기 상세조회
	public MatchingDTO matching(int matchingNo);

	// 예약 참가하기 등록
	public MatchingDTO matching_insert(MatchingDTO matching);

	// 구장번호로 강습하기 상세조회
	public TrainingDTO training(int trainingNo);

	// 강습하기 등록
	public TrainingDTO training_insert(TrainingDTO training);

	// 강습 참가하기 상세조회
	public TrainingMemberlistDTO trainingMemberlist(int trainingApplyNo);

	// 강습 참가하기 등록
	public TrainingMemberlistDTO trainingMemberlist_insert(TrainingMemberlistDTO trainingMemberlist);

}
