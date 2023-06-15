package com.multi.racket.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="associate")
public class AssociateDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int associateNo;
	private String memberId;
	private String stadiumName;
	private String stadiumAddr;
	private String stadiumTime;
	private int stadiumFee;
	private int court;
	private int associateCount;
	@CreatedDate
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
	private String associateRegdate;
}
