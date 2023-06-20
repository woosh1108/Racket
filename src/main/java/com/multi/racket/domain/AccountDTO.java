package com.multi.racket.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class AccountDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountNo;
	@ManyToOne
    @JoinColumn(name = "stadium")
    private StadiumDTO stadiumDto;
	private int stadiumNo;
	private String bankName;
	private String accountNum;
	private String accountHolder;
}
