package com.multi.racket.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="stadium")
public class StadiumDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "stadium_no")
	private int stadiumNo;

    private String memberId;
	
	private String stadiumName;
	private String stadiumAddr;
	private String stadiumPhone;
	private String stadiumTime;
	private int stadiumFee;
	private int stadiumStatus;
	private String stadiumContent;
	
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "stadiumNo", referencedColumnName = "stadium_no") // 외래 키 설정
    private List<StadiumFileDTO> files = new ArrayList<>();
    
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<StadiumFileDTO> files = new ArrayList<>();


	@Transient
	private List<MultipartFile> stadiumFileDTO;
	
}
