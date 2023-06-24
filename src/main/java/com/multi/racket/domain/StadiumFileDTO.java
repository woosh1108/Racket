package com.multi.racket.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stadiumFile")
public class StadiumFileDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stadiumFileNo;
	@JoinColumn(name = "stadium_no")
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