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
@Table(name = "court_operating_hours")
public class CourtoperatinghoursDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "court_hour_no")
	private int courtHourNo;
	@JoinColumn(name = "court_no")
	private int courtNo;
	private String courtStart;
	private String courtEnd;

	
//	/////////////////////////////////////////////////////////
//	@ManyToOne
//	@JoinColumn(name = "court_no")
//	private StadiumcourtDTO courtNo;
	///////////////////////////////////////////////////
    @OneToMany(mappedBy = "courtHourNo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationDTO> reservations = new ArrayList<>();

    @OneToMany(mappedBy = "courtHourNo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TrainingDTO> trainings = new ArrayList<>();

}
