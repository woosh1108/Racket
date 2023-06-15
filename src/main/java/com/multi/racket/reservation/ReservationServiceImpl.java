package com.multi.racket.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.multi.racket.domain.MatchingDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
	ReservationDAO dao;

	@Autowired
	public ReservationServiceImpl(ReservationDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public ReservationDTO reservation(int reservationNo) {
		return null;
	}

	@Override
	public ReservationDTO reservation_insert(ReservationDTO reservation) {
		System.out.println("service : " + reservation);
		return dao.reservation_insert(reservation);
	}

	@Override
	public MatchingDTO matching(int matchingNo) {
		return null;
	}

	@Override
	public MatchingDTO matching_insert(MatchingDTO matching) {
		return dao.matching_insert(matching);
	}

	@Override
	public TrainingDTO training(int trainingNo) {
		return dao.training(trainingNo);
	}

	@Override
	public TrainingDTO training_insert(TrainingDTO training) {
		return dao.training_insert(training);
	}

	@Override
	public TrainingMemberlistDTO trainingMemberlist(int trainingApplyNo) {
		return dao.trainingMemberlist(trainingApplyNo);
	}

	@Override
	public TrainingMemberlistDTO trainingMemberlist_insert(TrainingMemberlistDTO trainingMemberlist) {
		return dao.trainingMemberlist_insert(trainingMemberlist);
	}

}
