package com.multi.racket.domain;


import java.util.Date;

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
@Table(name = "blacklist")
public class BlacklistDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int blacklistNo;
	private String memberId;
	private String blacklistReason;
	@CreatedDate
	private Date blackDate;
	private Date blackTime;
}
