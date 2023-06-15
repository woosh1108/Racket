package com.multi.racket.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class ReservationDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_no")
	private int reservationNo;
	@Column(name = "member_id")
	private String memberId;
	@Column(name = "court_hour_no")
	private int courtHourNo;
	@Column(name = "reservation_content")
	private String reservationContent;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@Column(name = "reservation_date")
	private Date reservationDate;
	@Column(name = "reservation_status")
	private String reservationStatus;
	@Column(name = "reservation_met")
	private int reservationMet;
	@Column(name = "grade_setting")
	private int gradeSetting;
	@Column(name = "people_num")
	private int peopleNum;

	public ReservationDTO() {

	}

	public ReservationDTO(int reservationNo, String memberId, int courtHourNo, String reservationContent,
			Date reservationDate, String reservationStatus, int reservationMet, int gradeSetting, int peopleNum) {
		super();
		this.reservationNo = reservationNo;
		this.memberId = memberId;
		this.courtHourNo = courtHourNo;
		this.reservationContent = reservationContent;
		this.reservationDate = reservationDate;
		this.reservationStatus = reservationStatus;
		this.reservationMet = reservationMet;
		this.gradeSetting = gradeSetting;
		this.peopleNum = peopleNum;
	}

	@Override
	public String toString() {
		return "ReservationDTO [reservationNo=" + reservationNo + ", memberId=" + memberId + ", courtHourNo="
				+ courtHourNo + ", reservationContent=" + reservationContent + ", reservationDate=" + reservationDate
				+ ", reservationStatus=" + reservationStatus + ", reservationMet=" + reservationMet + ", gradeSetting="
				+ gradeSetting + ", peopleNum=" + peopleNum + "]";
	}

	public int getReservationNo() {
		return reservationNo;
	}

	public void setReservationNo(int reservationNo) {
		this.reservationNo = reservationNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getCourtHourNo() {
		return courtHourNo;
	}

	public void setCourtHourNo(int courtHourNo) {
		this.courtHourNo = courtHourNo;
	}

	public String getReservationContent() {
		return reservationContent;
	}

	public void setReservationContent(String reservationContent) {
		this.reservationContent = reservationContent;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	public String getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(String reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public int getReservationMet() {
		return reservationMet;
	}

	public void setReservationMet(int reservationMet) {
		this.reservationMet = reservationMet;
	}

	public int getGradeSetting() {
		return gradeSetting;
	}

	public void setGradeSetting(int gradeSetting) {
		this.gradeSetting = gradeSetting;
	}

	public int getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(int peopleNum) {
		this.peopleNum = peopleNum;
	}

}