package com.multi.racket.reservation;

import java.util.List;

import com.multi.racket.domain.CourtoperatinghoursDTO;
import com.multi.racket.domain.MemberDTO;
import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.StadiumDTO;
import com.multi.racket.domain.StadiumcourtDTO;
import com.multi.racket.domain.TrainingDTO;

public interface StadiumReadService {
	StadiumcourtDTO getStadiumDetail(int courtNo);
	ReservationDTO getReservationDetail(int reservationNo);
	int getReservationParticipantCount(int reservationNo);
	TrainingDTO getTrainingDetail(int trainingNo);
	int getTrainingParticipantCount(int reservationNo);
	
	public List<CourtoperatinghoursDTO> getHourtslistByCourtNo(int courtNo);
	
	public StadiumDTO findStadiumByStadiumNo(int stadiumNo);
	public StadiumcourtDTO findStadiumcourtByCourtNo(int courtNo);
	public CourtoperatinghoursDTO findCourtoperatinghoursByCourtHourNo(int courtHourNo);
	public MemberDTO findMemberByMemberId(String memberId);
}
