package com.multi.racket.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stadiumfile")
public class StadiumFileDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stadiumFileNo;
	@ManyToOne
    @JoinColumn(name = "stadium_file")
    private StadiumDTO stadiumDto;
	private int stadiumNo;
	private String fileOriginalname;
	private String fileStorename;
	private String stadiumFileNum;
}