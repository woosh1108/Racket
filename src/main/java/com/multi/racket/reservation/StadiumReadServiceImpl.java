package com.multi.racket.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.StadiumcourtDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.repository.MatchingRepository;
import com.multi.racket.repository.ReservationRepository;
import com.multi.racket.repository.StadiumCourtRepository;
import com.multi.racket.repository.TrainingMemberlistRepository;
import com.multi.racket.repository.TrainingRepository;

@Service
public class StadiumReadServiceImpl implements StadiumReadService {
	private StadiumCourtRepository stadiumCourtRepository;
	private ReservationRepository reservationRepository;
	private TrainingRepository trainingRepository;
	private MatchingRepository matchingRepository;
	private TrainingMemberlistRepository trainingMemberlistRepository;
	

    @Autowired
	public StadiumReadServiceImpl(StadiumCourtRepository stadiumCourtRepository,
			ReservationRepository reservationRepository, TrainingRepository trainingRepository,
			MatchingRepository matchingRepository, TrainingMemberlistRepository trainingMemberlistRepository) {
		super();
		this.stadiumCourtRepository = stadiumCourtRepository;
		this.reservationRepository = reservationRepository;
		this.trainingRepository = trainingRepository;
		this.matchingRepository = matchingRepository;
		this.trainingMemberlistRepository = trainingMemberlistRepository;
	}
   

    @Override
    public StadiumcourtDTO getStadiumDetail(int courtNo) {
    	StadiumcourtDTO court = stadiumCourtRepository.findByCourtNo(courtNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid court ID: " + courtNo));
    	System.out.println("ServiceImpl: "+court);
        return court;
    }
    
   
	@Override
	public ReservationDTO getReservationDetail(int reservationNo) {
		ReservationDTO reservation = reservationRepository.findByReservationNo(reservationNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid reservation ID: " + reservationNo));
    	System.out.println("ServiceImpl: "+reservation);
		return reservation;
	}

	@Override
	public int getReservationParticipantCount(int reservationNo) {
		return matchingRepository.getParticipantCount(reservationNo); // 예약한 인원 수 조회 메서드 호출
	}

	@Override
	public TrainingDTO getTrainingDetail(int trainingNo) {
		TrainingDTO training = trainingRepository.findByTrainingNo(trainingNo)
                .orElseThrow(() -> new IllegalArgumentException("Invalid training ID: " + trainingNo));
    	System.out.println("ServiceImpl: "+training);
		return training;
	}
	
	@Override
    public int getTrainingParticipantCount(int trainingNo) {
        return trainingMemberlistRepository.getParticipantCount(trainingNo); // 예약한 인원 수 조회 메서드 호출
    }

    
}
