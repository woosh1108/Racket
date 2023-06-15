package com.multi.racket.reservation;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.domain.TrainingMemberlistDTO;
import com.multi.racket.domain.MatchingDTO;

@Repository
public class ReservationDAOImpl implements ReservationDAO {
	private EntityManager entityManager;
	
	@Autowired
	public ReservationDAOImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	@Override
	public ReservationDTO reservation(int reservationNo) {
		ReservationDTO reservation = entityManager.find(ReservationDTO.class, reservationNo);
		return reservation;
	}

	@Override
	public ReservationDTO reservation_insert(ReservationDTO reservation) {
		entityManager.persist(reservation);
		System.out.println("dao : "+reservation);
		return reservation;
	}

	@Override
	public MatchingDTO matching(int matchingNo) {
		MatchingDTO matching = entityManager.find(MatchingDTO.class, matchingNo);
		return matching;
	}

	@Override
	public MatchingDTO matching_insert(MatchingDTO matching) {
		entityManager.persist(matching);
		return matching;
	}

	@Override
	public TrainingDTO training(int trainingNo) {
		TrainingDTO training = entityManager.find(TrainingDTO.class, trainingNo);
		return training;
	}

	@Override
	public TrainingDTO training_insert(TrainingDTO training) {
		entityManager.persist(training);
		System.out.println("dao : "+training);
		return training;
	}

	@Override
	public TrainingMemberlistDTO trainingMemberlist(int trainingApplyNo) {
		TrainingMemberlistDTO trainingMemberlist = entityManager.find(TrainingMemberlistDTO.class, trainingApplyNo);
		return trainingMemberlist;
	}

	@Override
	public TrainingMemberlistDTO trainingMemberlist_insert(TrainingMemberlistDTO trainingMemberlist) {
		entityManager.persist(trainingMemberlist);
		System.out.println("dao : "+trainingMemberlist);
		return trainingMemberlist;
	}

}
