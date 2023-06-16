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
@Table(name = "blacklist")
public class BlacklistDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "blacklist_no")
	private int blacklistNo;
	@Column(name = "member_id")
	private String memberId;
	@Column(name = "blacklist_reason")
	private String blacklistReason;
	@Column(name = "black_date")
	private Timestamp blackDate;
	@Column(name = "black_time")
	private Timestamp blackTime;
}
