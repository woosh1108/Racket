package com.multi.racket.domain;

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
@Table(name = "paymentinfo")
public class PaymentInfoDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentInfoNo;
	private String memberId;
	@CreatedDate
	private String paymentInfoRegdate;
	private int paymentInfoAmount;
	private String paymentInfoMethod;
}
