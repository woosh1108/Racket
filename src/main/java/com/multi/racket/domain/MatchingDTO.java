package com.multi.racket.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

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
	private int matchNo;
	private int reservationNo;
	private String memberId;
	@CreatedDate
	private Timestamp matchDate;
}
