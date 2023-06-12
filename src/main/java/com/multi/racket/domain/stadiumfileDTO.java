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
@Table(name = "stadiumfile")
public class stadiumfileDTO {
	@Id
	private int stadiumFileNo;
	private int stadiumNo;
	private String fileOriginalname;
	private String fileStorename;
	private String stadiumFileno;
}
