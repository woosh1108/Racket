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
@Table(name = "trainingmemberlist")
public class trainingMemberlistDTO {
	@Id
	private int trainingApplyNo;
	private int trainingNo;
	private String memberId;
	private Timestamp trainingDate;
}
