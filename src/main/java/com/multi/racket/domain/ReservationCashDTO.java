package com.multi.racket.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "reservation_cash")
public class ReservationCashDTO {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
	
	@ManyToOne
    @JoinColumn(name = "reservation_id")
	private ReservationDTO reservation;
    
    @ManyToOne
    @JoinColumn(name = "cash_id")
	private CashDTO cash;

	public ReservationCashDTO() {

	}

	public ReservationCashDTO(ReservationDTO reservation, CashDTO cash) {
		super();
		this.reservation = reservation;
		this.cash = cash;
	}

	@Override
	public String toString() {
		return "ReservationCashDTO [id=" + id + ", reservation=" + reservation + ", cash=" + cash + "]";
	}

	public ReservationDTO getReservation() {
		return reservation;
	}

	public void setReservation(ReservationDTO reservation) {
		this.reservation = reservation;
	}

	public CashDTO getCash() {
		return cash;
	}

	public void setCash(CashDTO cash) {
		this.cash = cash;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
