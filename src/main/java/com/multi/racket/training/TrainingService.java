package com.multi.racket.training;

import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;

public interface TrainingService {
	
	// 현재 캐시 잔액 확인
	public boolean checkSufficientBalance(String memberId, int reservationFee);
	
	// 구장번호로 강습하기 상세조회
	public TrainingDTO training(int trainingNo);

	// 강습하기 등록
	public void training_insert(String memberId,TrainingDTO training, CashDTO cash) throws Exception ;

	// 강습 참가하기 상세조회
	public TrainingMemberlistDTO trainingMemberlist(int trainingApplyNo);

	// 강습 참가하기 등록
	public void trainingMemberlist_insert(String memberId, TrainingMemberlistDTO trainingMemberlist);

}
