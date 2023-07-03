package com.multi.racket.domain;

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
@Table(name = "stadium_file")
public class StadiumFileDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stadium_file_no")
	private int stadiumFileNo;
	private int stadiumNo;
	private String fileOriginalname;
	private String fileStorename;
	private String stadiumFileNum;
	
	public StadiumFileDTO(int stadiumNo, String fileOriginalname, String fileStorename, String stadiumFileNum) {
		super();
		this.stadiumNo = stadiumNo;
		this.fileOriginalname = fileOriginalname;
		this.fileStorename = fileStorename;
		this.stadiumFileNum = stadiumFileNum;
	}

	public StadiumFileDTO(String fileOriginalname, String fileStorename, String stadiumFileNum) {
		super();
		this.fileOriginalname = fileOriginalname;
		this.fileStorename = fileStorename;
		this.stadiumFileNum = stadiumFileNum;
	}

}