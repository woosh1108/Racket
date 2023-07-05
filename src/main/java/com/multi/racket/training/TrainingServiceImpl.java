package com.multi.racket.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public boolean checkSufficientBalance(String memberId, int trainingFee) {
        // 최신 total_amount 조회
        int latestTotalAmount = cashRepository.findLatestTotalAmountByMemberId(memberId);
        return latestTotalAmount >= trainingFee;
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
	public void trainingMemberlist_insert(String memberId, TrainingMemberlistDTO trainingMemberlist, CashDTO cash) throws Exception {
		try {
	        System.out.println("Service 성공: " + trainingMemberlist + ", " + cash);
	        tmlRepository.save(trainingMemberlist);
	        cashRepository.save(cash);
	    } catch (Exception e) {
	        System.out.println("Service 실패");
	        e.printStackTrace();
	        throw new Exception("Failed to create Reservation with Cash", e);
	    }
	}

	@Override
	public Page<TrainingDTO> traininglist(int pageNo) {
		int pageSize = 10; // 페이지당 표시할 데이터 수

		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("trainingNo").descending());
		return tRepository.findAll(pageable);
	}


	@Override
	public Page<TrainingDTO> searchTrainings(String type, String keyword, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("trainingNo").descending());

		if (type.equals("grade")) {
			return tRepository.findByTrainingGradeContaining(keyword, pageable);
		} else if (type.equals("min")) {
			int minFee = Integer.parseInt(keyword);
			return tRepository.findByTrainingFeeGreaterThanEqual(minFee, pageable);
		} else if (type.equals("max")) {
			int maxFee = Integer.parseInt(keyword);
			return tRepository.findByTrainingFeeLessThanEqual(maxFee, pageable);
		} else {
			return Page.empty(); // 빈 페이지 반환
		}
	}

}
