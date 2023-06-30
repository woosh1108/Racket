package com.multi.racket.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trainingMemberlist")
public class TrainingMemberlistDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "training_apply_no")
	private int trainingApplyNo;
	@Column(name = "training_no")
	private int trainingNo;
	@Column(name = "member_id")
	private String memberId;
	@Column(name = "training_date")
	private Date trainingDate;
}
