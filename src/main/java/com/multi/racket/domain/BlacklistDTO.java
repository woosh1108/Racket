package com.multi.racket.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
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
	private int blacklistNo;
	private String memberId;
	private String blacklistReason;
	@CreatedDate
	private Timestamp blackDate;
	private Timestamp blackTime;
}
