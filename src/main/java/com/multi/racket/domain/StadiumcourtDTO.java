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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stadium_court")
public class StadiumcourtDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "court_no")
	private int courtNo;
	private String courtName;

	/////////////////////////////////////////////////////////
	@ManyToOne
    @JoinColumn(name = "stadium_no")
    private StadiumDTO stadiumNo;
	///////////////////////////////////////////////////
    @OneToMany(mappedBy = "courtNo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourtoperatinghoursDTO> operatingHours = new ArrayList<>();
    
    
    
	public StadiumcourtDTO(int courtNo, String courtName, StadiumDTO stadiumNo) {
		super();
		this.courtNo = courtNo;
		this.courtName = courtName;
		this.stadiumNo = stadiumNo;
	}



	public StadiumcourtDTO(int courtNo, String courtName) {
		super();
		this.courtNo = courtNo;
		this.courtName = courtName;
	}
	
    
}