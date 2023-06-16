package com.multi.racket.domain;

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
@Table(name="stadium")
public class StadiumDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stadiumNo;
	private String memberId;
	private String stadiumName;
	private String stadiumAddr;
	private String stadiumTime;
	private String stadiumFee;
	private int stadiumStatus;
	private String stadiumContent;
}