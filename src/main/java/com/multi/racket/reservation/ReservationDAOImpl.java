package com.multi.racket.reservation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;
import com.multi.racket.repository.CashRepository;
import com.multi.racket.repository.MatchingRepository;
import com.multi.racket.repository.ReservationCashRepository;
import com.multi.racket.repository.ReservationRepository;
import com.multi.racket.repository.TrainingMemberlistRepository;
import com.multi.racket.repository.TrainingRepository;

//@Repository
public class ReservationDAOImpl implements ReservationDAO {
	ReservationRepository rRepository;
	MatchingRepository mRepository;
	TrainingRepository tRepository;
	TrainingMemberlistRepository tmRepository;
	CashRepository cashRepository;
	ReservationCashRepository rcashRepository;

	@Autowired
	public ReservationDAOImpl(ReservationRepository rRepository, MatchingRepository mRepository,
			TrainingRepository tRepository, TrainingMemberlistRepository tmRepository, CashRepository cashRepository,
			ReservationCashRepository rcashRepository) {
		super();
		this.rRepository = rRepository;
		this.mRepository = mRepository;
		this.tRepository = tRepository;
		this.tmRepository = tmRepository;
		this.cashRepository = cashRepository;
		this.rcashRepository = rcashRepository;
	}

	@Override
	public ReservationDTO reservation(int reservationNo) {
		//Optional<ReservationDTO> reservation = rRepository.findById(reservationNo).orElse(null);
		return null;
	}

	@Override
	public void reservation_insert(ReservationDTO reservation, CashDTO cash) {
		rRepository.save(reservation);
		cashRepository.save(cash);
//		if (cash.getTotalAmount() >= reservation.getReservationFee()) {
//			rRepository.save(reservation);
//			cashRepository.save(cash);
//		} else {
//			// 잔액 부족 시 처리 로직을 구현 (모달 창 띄우기 등)
//			throw new InsufficientBalanceException("잔액이 부족합니다.");
//		}
//		rRepository.save(reservation);
//		System.out.println("dao : " + reservation);
//		return reservation;
	}

	@Override
	public MatchingDTO matching(int matchingNo) {
		//Optional<MatchingDTO> matching = mRepository.findById(matchingNo).orElse(null);
		return null;
	}

	@Override
	public MatchingDTO matching_insert(MatchingDTO matching) {
		mRepository.save(matching);
		return matching;
	}

	@Override
	public TrainingDTO training(int trainingNo) {
		// Optional<TrainingDTO> training = tRepository.findById(trainingNo).orElse(null);
		return null;
	}

	@Override
	public TrainingDTO training_insert(TrainingDTO training) {
		tRepository.save(training);
		return training;
	}

	@Override
	public TrainingMemberlistDTO trainingMemberlist(int trainingApplyNo) {
		//Optional<TrainingMemberlistDTO> trainingMemberlist = tmRepository.findById(trainingApplyNo).orElse(null);
		return null;
	}

	@Override
	public TrainingMemberlistDTO trainingMemberlist_insert(TrainingMemberlistDTO trainingMemberlist) {
		tmRepository.save(trainingMemberlist);
		return trainingMemberlist;
	}

}
