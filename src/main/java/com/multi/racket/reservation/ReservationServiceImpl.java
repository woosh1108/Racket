package com.multi.racket.reservation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.repository.CashRepository;
import com.multi.racket.repository.MatchingRepository;
import com.multi.racket.repository.ReservationRepository;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
	ReservationRepository rRepository;
	CashRepository cashRepository;
	MatchingRepository mRepository;

	public ReservationServiceImpl(ReservationRepository rRepository, CashRepository cashRepository,
			MatchingRepository mRepository) {
		super();
		this.rRepository = rRepository;
		this.cashRepository = cashRepository;
		this.mRepository = mRepository;
	}

	// 현재 캐시 잔액 조회
	@Override
    public boolean checkSufficientBalance(String memberId, int reservationFee) {
        // 최신 total_amount 조회
        int latestTotalAmount = cashRepository.findLatestTotalAmountByMemberId(memberId);
        return latestTotalAmount >= reservationFee;
    }
	
	@Override
	public ReservationDTO reservation(int reservationNo) {
		return rRepository.findById(reservationNo).orElseGet(ReservationDTO::new);
	}
	
	@Override
	public void reservation_insert(String memberId, ReservationDTO reservation, CashDTO cash) throws Exception {
	    try {
	        System.out.println("Service 성공: " + reservation + ", " + cash);
	        rRepository.save(reservation);
	        cashRepository.save(cash);
	    } catch (Exception e) {
	        System.out.println("Service 실패");
	        e.printStackTrace();
	        throw new Exception("Failed to create Reservation with Cash", e);
	    }
	}

	@Override
	public MatchingDTO matching(int matchingNo) {
		return mRepository.findById(matchingNo).orElseGet(MatchingDTO::new);
	}

	@Override
	public void matching_insert(String memberId, MatchingDTO matching, CashDTO cash) throws Exception {
		 try {
		        System.out.println("Service 성공: " + matching + ", " + cash);
		        mRepository.save(matching);
		        cashRepository.save(cash);
		    } catch (Exception e) {
		        System.out.println("Service 실패");
		        e.printStackTrace();
		        throw new Exception("Failed to create Reservation with Cash", e);
		    }
	}


}
