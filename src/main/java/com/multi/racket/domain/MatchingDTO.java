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

@Data
@AllArgsConstructor	
@NoArgsConstructor
@Entity
@Table(name = "matching")
public class MatchingDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "match_no")
	private int matchNo;
	@Column(name = "reservation_no")
	private int reservationNo;
	@Column(name = "member_id")
	private String memberId;
	@Column(name = "match_date")
	private Timestamp matchDate;
}
