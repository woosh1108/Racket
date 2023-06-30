package com.multi.racket.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "reservation_no")
	private int reservationNo;

	@ManyToOne
    @JoinColumn(name = "member_id")
    private MemberDTO member;

	@ManyToOne
    @JoinColumn(name = "court_hour_no")
    private CourtoperatinghoursDTO courtOperatingHours;
	
	private String reservationContent;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@CreatedDate
	private Date reservationDate;
	private String reservationStatus;
	private String reservationMet;
	private int gradeSetting;
	private String reservationGender;
	private int reservationFee;
	private int peopleNum;

	@Override
    public String toString() {
        return "ReservationDTO{" +
                "reservationNo=" + reservationNo +
                ", member=" + (member != null ? member.getMemberId() : null) +
                ", courtOperatingHours=" + (courtOperatingHours != null ? courtOperatingHours.getCourtHourNo() : null) +
                ", reservationContent='" + reservationContent + '\'' +
                ", reservationDate=" + reservationDate +
                ", reservationStatus='" + reservationStatus + '\'' +
                ", reservationMet='" + reservationMet + '\'' +
                ", gradeSetting=" + gradeSetting +
                ", reservationGender='" + reservationGender + '\'' +
                ", reservationFee=" + reservationFee +
                ", peopleNum=" + peopleNum +
                '}';
    }
}
