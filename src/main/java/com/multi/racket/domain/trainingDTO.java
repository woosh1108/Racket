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
@Table(name = "training")
public class trainingDTO {
	@Id
	private int trainingNo;
	private String memberId;
	private int courtHourNo;
	private String trainingContent;
	private Timestamp trainingDate;
	private int trainingMax;
	private int trainingFee;
}
