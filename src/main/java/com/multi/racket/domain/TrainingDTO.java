package com.multi.racket.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "training")
public class TrainingDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "training_no")
	private int trainingNo;
	@Column(name = "member_id")
	private String memberId;
	@Column(name = "court_hour_no")
	private int courtHourNo;
	@Column(name = "training_content")
	private String trainingContent;
	@Column(name = "training_date")
	private Timestamp trainingDate;
	@Column(name = "training_max")
	private int trainingMax;
	@Column(name = "training_fee")
	private int trainingFee;
	
	public TrainingDTO() {
		
	}
	
	public TrainingDTO(int trainingNo, String memberId, int courtHourNo, String trainingContent, Timestamp trainingDate,
			int trainingMax, int trainingFee) {
		super();
		this.trainingNo = trainingNo;
		this.memberId = memberId;
		this.courtHourNo = courtHourNo;
		this.trainingContent = trainingContent;
		this.trainingDate = trainingDate;
		this.trainingMax = trainingMax;
		this.trainingFee = trainingFee;
	}

	@Override
	public String toString() {
		return "TrainingDTO [trainingNo=" + trainingNo + ", memberId=" + memberId + ", courtHourNo=" + courtHourNo
				+ ", trainingContent=" + trainingContent + ", trainingDate=" + trainingDate + ", trainingMax="
				+ trainingMax + ", trainingFee=" + trainingFee + "]";
	}
	
	public int getTrainingNo() {
		return trainingNo;
	}
	public void setTrainingNo(int trainingNo) {
		this.trainingNo = trainingNo;
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
	public String getTrainingContent() {
		return trainingContent;
	}
	public void setTrainingContent(String trainingContent) {
		this.trainingContent = trainingContent;
	}
	public Timestamp getTrainingDate() {
		return trainingDate;
	}
	public void setTrainingDate(Timestamp trainingDate) {
		this.trainingDate = trainingDate;
	}
	public int getTrainingMax() {
		return trainingMax;
	}
	public void setTrainingMax(int trainingMax) {
		this.trainingMax = trainingMax;
	}
	public int getTrainingFee() {
		return trainingFee;
	}
	public void setTrainingFee(int trainingFee) {
		this.trainingFee = trainingFee;
	}
}
