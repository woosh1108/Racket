package com.multi.racket.training;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;

public interface TrainingService {
	
	// 현재 캐시 잔액 확인
	public boolean checkSufficientBalance(String memberId, int reservationFee);
	
	// 강습하기 등록
	public void training_insert(String memberId,TrainingDTO training, CashDTO cash) throws Exception ;

	// 강습 참가하기 등록
	public void trainingMemberlist_insert(String memberId, TrainingMemberlistDTO trainingMemberlist, CashDTO cash, TrainingDTO training) throws Exception;

	// 강습 목록 조회
	public Page<TrainingDTO> traininglist(int pageNo);
	
	// 예약 목록 검색
	public Page<TrainingDTO> searchTrainings(String type, String keyword, int pageNo, int pageSize);

	// training_date가 지난 예약 데이터의 상태를 "강습종료"로 수정하는 메서드
    public void updateExpiredTrainings(LocalDate currentDate);
    
    // 해당하는 예약 명단에 내 아이디가 있는지
    public boolean existsByMemberIdAndTrainingNo(String memberId, int trainingNo);

}
