package com.multi.racket.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class ReservationDTO {
	@Id
	private int reservationNo;
	private String memberId;
	private int courtHourNo;
	private String reservationContent;
	private Timestamp reservationDate;
	private String reservationStatus;
	private int reservationMet;
	private int gradeSetting;
	private int peopleNum;
}
