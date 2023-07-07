package com.multi.racket.reservation;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.racket.domain.CourtoperatinghoursDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumcourtDTO;
import com.multi.racket.domain.TrainingDTO;
import com.multi.racket.repository.CourtOperatingHoursRepository;
import com.multi.racket.repository.MatchingRepository;
import com.multi.racket.repository.MemberRepository;
import com.multi.racket.repository.ReservationRepository;
import com.multi.racket.repository.StadiumCourtRepository;
import com.multi.racket.repository.StadiumRepository;
import com.multi.racket.repository.TrainingMemberlistRepository;
import com.multi.racket.repository.TrainingRepository;

@Service
public class StadiumReadServiceImpl implements StadiumReadService {
	private StadiumRepository stadiumRepository;
	private StadiumCourtRepository stadiumCourtRepository;
	private ReservationRepository reservationRepository;
	private TrainingRepository trainingRepository;
	private MatchingRepository matchingRepository;
	private TrainingMemberlistRepository trainingMemberlistRepository;
	private CourtOperatingHoursRepository cohRepository;
	private MemberRepository memberRepository;
	
	@Autowired
	public StadiumReadServiceImpl(StadiumRepository stadiumRepository, StadiumCourtRepository stadiumCourtRepository,
			ReservationRepository reservationRepository, TrainingRepository trainingRepository,
			MatchingRepository matchingRepository, TrainingMemberlistRepository trainingMemberlistRepository,
			CourtOperatingHoursRepository cohRepository, MemberRepository memberRepository) {
		super();
		this.stadiumRepository = stadiumRepository;
		this.stadiumCourtRepository = stadiumCourtRepository;
		this.reservationRepository = reservationRepository;
		this.trainingRepository = trainingRepository;
		this.matchingRepository = matchingRepository;
		this.trainingMemberlistRepository = trainingMemberlistRepository;
		this.cohRepository = cohRepository;
		this.memberRepository = memberRepository;
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
		int participantCount = matchingRepository.getParticipantCount(reservationNo); // 예약한 인원 수 조회 메서드 호출
		System.out.println("예약인원: "+participantCount);
		return participantCount;
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
		int participantCount = trainingMemberlistRepository.getParticipantCount(trainingNo); // 예약한 인원 수 조회 메서드 호출
		System.out.println("강습인원: "+participantCount);
		return participantCount;
    }


	@Override
	public List<CourtoperatinghoursDTO> getHourtslistByCourtNo(int courtNo) {
	    List<CourtoperatinghoursDTO> hourlist = cohRepository.findAllByCourtNo(courtNo);
	    return hourlist;
	}
	
	public StadiumDTO findStadiumByStadiumNo(int stadiumNo) {
        return stadiumRepository.findByStadiumNo(stadiumNo)
                .orElseThrow(() -> new EntityNotFoundException("Stadium not found with stadiumNo: " + stadiumNo));
    }
	
	public StadiumcourtDTO findStadiumcourtByCourtNo(int courtNo) {
        return stadiumCourtRepository.findByCourtNo(courtNo)
                .orElseThrow(() -> new EntityNotFoundException("Stadiumcourt not found with courtNo: " + courtNo));
    }
	
	public CourtoperatinghoursDTO findCourtoperatinghoursByCourtHourNo(int courtHourNo) {
        return cohRepository.findByCourtHourNo(courtHourNo)
                .orElseThrow(() -> new EntityNotFoundException("Courtoperatinghours not found with courtHourNo: " + courtHourNo));
    }

	public MemberDTO findMemberByMemberId(String memberId) {
        return memberRepository.findByMemberId(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Courtoperatinghours not found with memberId: " + memberId));
    }
    
}
