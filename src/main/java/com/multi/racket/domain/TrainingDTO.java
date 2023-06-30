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
@Table(name = "training")
public class TrainingDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "training_no")
	private int trainingNo;
	
	@ManyToOne
    @JoinColumn(name = "member_id")
    private MemberDTO member;
	
	@ManyToOne
    @JoinColumn(name = "court_hour_no")
    private CourtoperatinghoursDTO courtOperatingHours;
	
	private String trainingContent;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@CreatedDate
	private Date trainingDate;

	private int trainingMax;
	private int trainingFee;
	private String trainingGrade;
	
	@Override
    public String toString() {
        return "TrainingDTO{" +
                "trainingNo=" + trainingNo +
                ", member=" + (member != null ? member.getMemberId() : null) +
                ", courtOperatingHours=" + (courtOperatingHours != null ? courtOperatingHours.getCourtHourNo() : null) +
                ", trainingContent='" + trainingContent + '\'' +
                ", trainingDate=" + trainingDate +
                ", trainingMax=" + trainingMax +
                ", trainingFee=" + trainingFee +
                ", trainingGrade=" + trainingGrade +
                '}';
    }

}
