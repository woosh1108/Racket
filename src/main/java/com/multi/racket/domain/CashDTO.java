package com.multi.racket.domain;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Table(name = "cash")
public class CashDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cash_no")
	private int cashNo;
	@Column(name = "member_id")
	private String memberId;
	@Column(name = "payment_info_no")
	private int paymentInfoNo;
	@Column(name = "total_amount")
	private int totalAmount;
	@Column(name = "amount_spent")
	private int amountSpent;
	private int charging;
	@Column(name = "cash_date")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "Asia/Seoul")
	@CreationTimestamp
	private Date cashDate;
	
	public CashDTO() {
		
	}

	public CashDTO(int cashNo, String memberId, int paymentInfoNo, int totalAmount, int amountSpent, int charging,
			Date cashDate) {
		super();
		this.cashNo = cashNo;
		this.memberId = memberId;
		this.paymentInfoNo = paymentInfoNo;
		this.totalAmount = totalAmount;
		this.amountSpent = amountSpent;
		this.charging = charging;
		this.cashDate = cashDate;
	}
	
	public CashDTO(String memberId, int paymentInfoNo, int totalAmount, int charging) {
		super();
		this.memberId = memberId;
		this.paymentInfoNo = paymentInfoNo;
		this.totalAmount = totalAmount;
		this.charging = charging;
	}

	@Override
	public String toString() {
		return "CashDTO [cashNo=" + cashNo + ", memberId=" + memberId + ", paymentInfoNo=" + paymentInfoNo
				+ ", totalAmount=" + totalAmount + ", amountSpent=" + amountSpent + ", charging=" + charging
				+ ", cashDate=" + cashDate + "]";
	}

	public int getCashNo() {
		return cashNo;
	}

	public void setCashNo(int cashNo) {
		this.cashNo = cashNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getPaymentInfoNo() {
		return paymentInfoNo;
	}

	public void setPaymentInfoNo(int paymentInfoNo) {
		this.paymentInfoNo = paymentInfoNo;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getAmountSpent() {
		return amountSpent;
	}

	public void setAmountSpent(int amountSpent) {
		this.amountSpent = amountSpent;
	}

	public int getCharging() {
		return charging;
	}

	public void setCharging(int charging) {
		this.charging = charging;
	}

	public Date getCashDate() {
		return cashDate;
	}

	public void setCashDate(Date cashDate) {
		this.cashDate = cashDate;
	}
	
	public CashDTO(String memberId, int paymentInfoNo, int totalAmount, int amountSpent, int charging) {
        super();
        this.memberId = memberId;
        this.paymentInfoNo = paymentInfoNo;
        this.totalAmount = totalAmount;
        this.amountSpent = amountSpent;
        this.charging = charging;
    }
}
