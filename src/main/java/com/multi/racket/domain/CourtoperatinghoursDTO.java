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
@Table(name = "courtoperatinghours")
public class CourtoperatinghoursDTO {
	@Id
	private int courtHourNo;
	private int courtNo;
	private String courtStart;
	private String courtEnd;
}
