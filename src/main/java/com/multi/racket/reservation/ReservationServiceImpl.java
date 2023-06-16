package com.multi.racket.reservation;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;
import com.multi.racket.repository.CashRepository;
import com.multi.racket.repository.ReservationCashRepository;
import com.multi.racket.repository.ReservationRepository;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
	ReservationRepository rRepository;
	CashRepository cashRepository;
	ReservationCashRepository rcashRepository;
	

	public ReservationServiceImpl(ReservationRepository rRepository,
			CashRepository cashRepository, ReservationCashRepository rcashRepository) {
		super();
		
		this.rRepository = rRepository;
		this.cashRepository = cashRepository;
		this.rcashRepository = rcashRepository;
	}

	@Override
	public ReservationDTO reservation(int reservationNo) {
		return rRepository.findById(reservationNo).orElseGet(ReservationDTO::new);
	}
	
	@Override
	public void reservation_insert(ReservationDTO reservation, CashDTO cash) throws Exception {
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
		return null;
	}

	@Override
	public MatchingDTO matching_insert(MatchingDTO matching) {
		return null;
	}

	@Override
	public TrainingDTO training(int trainingNo) {
		return null;
	}

	@Override
	public TrainingDTO training_insert(TrainingDTO training) {
		return null;

	}

	@Override
	public TrainingMemberlistDTO trainingMemberlist(int trainingApplyNo) {
		return null;
	}

	@Override
	public TrainingMemberlistDTO trainingMemberlist_insert(TrainingMemberlistDTO trainingMemberlist) {
		return null;
	}

}
