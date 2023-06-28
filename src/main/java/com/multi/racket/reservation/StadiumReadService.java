package com.multi.racket.reservation;

import com.multi.racket.domain.ReservationDTO;
import com.multi.racket.domain.StadiumcourtDTO;
import com.multi.racket.domain.TrainingDTO;

public interface StadiumReadService {
	StadiumcourtDTO getStadiumDetail(int courtNo);
	ReservationDTO getReservationDetail(int reservationNo);
	int getReservationParticipantCount(int reservationNo);
	TrainingDTO getTrainingDetail(int trainingNo);
	int getTrainingParticipantCount(int reservationNo);
}
