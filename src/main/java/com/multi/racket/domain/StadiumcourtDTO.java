package com.multi.racket.domain;

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
    @ManyToOne
    @JoinColumn(name = "stadium_no")
    private StadiumDTO stadium;
	private String courtName;

	@OneToMany(mappedBy = "court", cascade = CascadeType.ALL)
    private List<CourtoperatinghoursDTO> operatingHours;
	
	@Override
	public String toString() {
	    return "StadiumcourtDTO{" +
	            "courtNo=" + courtNo +
	            ", stadium=" + (stadium != null ? stadium.getStadiumNo() : null) +
	            ", courtName='" + courtName + '\'' +
	            '}';
	}
	
}
