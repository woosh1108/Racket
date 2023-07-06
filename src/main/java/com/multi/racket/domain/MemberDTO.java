package com.multi.racket.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NonNull;

@Data
@Entity
@Table(name = "member2")
@DynamicInsert
@DynamicUpdate
public class MemberDTO {
	@Id
	@NonNull
	@Column(name = "member_id")
	private String memberId;
	@NonNull
	private String memberPass;
	@NonNull
	private String memberName;
	@NonNull
	private String memberAge;
	@NonNull
	private String memberPhone;
	@NonNull
	private String memberNick;
	@NonNull
	private String memberGender;
	@NonNull
	private String memberAddr;
	@NonNull
	private String memberGrade;
	@NonNull
	private Integer memberAuth;
	@NonNull
	private Integer memberStatus;
	@NonNull
	private String memberEmail;
	@NonNull
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@CreatedDate
	private Date memberReg;
	private int totalAmount;
	public MemberDTO() {
		super();
	}

	public MemberDTO(@NonNull String memberId, @NonNull String memberPass, @NonNull String memberName,
			@NonNull String memberAge, @NonNull String memberPhone, @NonNull String memberNick,
			@NonNull String memberGender, @NonNull String memberAddr, @NonNull String memberGrade,
			@NonNull Integer memberAuth, @NonNull Integer memberStatus, @NonNull String memberEmail,
			@NonNull Date memberReg) {
		super();
		this.memberId = memberId;
		this.memberPass = memberPass;
		this.memberName = memberName;
		this.memberAge = memberAge;
		this.memberPhone = memberPhone;
		this.memberNick = memberNick;
		this.memberGender = memberGender;
		this.memberAddr = memberAddr;
		this.memberGrade = memberGrade;
		this.memberAuth = memberAuth;
		this.memberStatus = memberStatus;
		this.memberEmail = memberEmail;
		this.memberReg = memberReg;
	}

	@Override
	public String toString() {
		return "MemberDTO [memberId=" + memberId + ", memberPass=" + memberPass + ", memberName=" + memberName
				+ ", memberAge=" + memberAge + ", memberPhone=" + memberPhone + ", memberNick=" + memberNick
				+ ", memberGender=" + memberGender + ", memberAddr=" + memberAddr + ", memberGrade=" + memberGrade
				+ ", memberAuth=" + memberAuth + ", memberStatus=" + memberStatus + ", memberEmail=" + memberEmail
				+ ", memberReg=" + memberReg + "]";
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPass() {
		return memberPass;
	}

	public void setMemberPass(String memberPass) {
		this.memberPass = memberPass;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberAge() {
		return memberAge;
	}

	public void setMemberAge(String memberAge) {
		if(memberAge != null && memberAge != "") {
			this.memberAge = memberAge;
		}
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		if(memberPhone != null && memberPhone != "") {
			this.memberPhone = memberPhone;
		}
	}

	public String getMemberNick() {
		return memberNick;
	}

	public void setMemberNick(String memberNick) {
		if(memberNick != null && memberNick != "") {
			this.memberNick = memberNick;
		}
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public String getMemberAddr() {
		return memberAddr;
	}

	public void setMemberAddr(String memberAddr) {
		if(memberAddr != null && memberAddr != "") {
			this.memberAddr = memberAddr;
		}
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	public Integer getMemberAuth() {
		return memberAuth;
	}

	public void setMemberAuth(Integer memberAuth) {
		this.memberAuth = memberAuth;
	}

	public Integer getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public Date getMemberReg() {
		return memberReg;
	}

	public void setMemberReg(Date memberReg) {
		this.memberReg = memberReg;
	}

}