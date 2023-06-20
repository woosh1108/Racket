package com.multi.racket.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;
import com.multi.racket.repository.CashRepository;
import com.multi.racket.repository.TrainingMemberlistRepository;
import com.multi.racket.repository.TrainingRepository;

@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {
	TrainingRepository tRepository;
	TrainingMemberlistRepository tmlRepository;
	CashRepository cashRepository;
	
	@Autowired
	public TrainingServiceImpl(TrainingRepository tRepository, TrainingMemberlistRepository tmlRepository,
			CashRepository cashRepository) {
		super();
		this.tRepository = tRepository;
		this.tmlRepository = tmlRepository;
		this.cashRepository = cashRepository;
	}
	

	// 현재 캐시 잔액 조회
	@Override
    public boolean checkSufficientBalance(String memberId, int reservationFee) {
        // 최신 total_amount 조회
        int latestTotalAmount = cashRepository.findLatestTotalAmountByMemberId(memberId);
        return latestTotalAmount >= reservationFee;
    }
	

	@Override
	public TrainingDTO training(int trainingNo) {
		return null;
	}

	@Override
	public void training_insert(String memberId, TrainingDTO training, CashDTO cash) throws Exception {
		try {
	        System.out.println("Service 성공: " + training + ", " + cash);
	        tRepository.save(training);
	        cashRepository.save(cash);
	    } catch (Exception e) {
	        System.out.println("Service 실패");
	        e.printStackTrace();
	        throw new Exception("Failed to create Reservation with Cash", e);
	    }
	}


	@Override
	public TrainingMemberlistDTO trainingMemberlist(int trainingApplyNo) {
		return null;
	}

	@Override
	public void trainingMemberlist_insert(String memberId, TrainingMemberlistDTO trainingMemberlist) {
	        tmlRepository.save(trainingMemberlist);
	}

}
