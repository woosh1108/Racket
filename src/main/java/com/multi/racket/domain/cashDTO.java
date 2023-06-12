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
@Table(name = "cash")
public class cashDTO {
	@Id
	private int cashNo;
	private int paymentInfoNo;
	private String memberId;
	private int totalAmount;
	private int amountSpent;
	private int field;
}
