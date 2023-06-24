package com.multi.racket.bulletin_board_reply;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

@Entity
@Table(name = "bulletin_board_reply")
public class BulletinBoardReplyDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bbReplyNo;
	private int bbNo;
	@NotNull
	private String memberId;
	private String bbReplyContent;
	@JsonFormat(pattern = "YYYY-MM-DD HH:MM:SS", timezone = "Asia/Seoul")
	@Column(nullable = false)
	@NotNull
	@CreationTimestamp
	private Date bbReplyWriteDate;
	@JsonFormat(pattern = "YYYY-MM-DD HH:MM:SS", timezone = "Asia/Seoul")
	@Column(name = "bb_reply_modify_date")
	@UpdateTimestamp
	private Date bbReplyModifyDate;

	public BulletinBoardReplyDTO() {

	}

	public BulletinBoardReplyDTO(int bbReplyNo, int bbNo, String memberId, String bbReplyContent, Date bbReplyWriteDate,
			Date bbReplyModifyDate) {
		super();
		this.bbReplyNo = bbReplyNo;
		this.bbNo = bbNo;
		this.memberId = memberId;
		this.bbReplyContent = bbReplyContent;
		this.bbReplyWriteDate = bbReplyWriteDate;
		this.bbReplyModifyDate = bbReplyModifyDate;
	}

	@Override
	public String toString() {
		return "BulletinBoardReplyDTO [bbReplyNo=" + bbReplyNo + ", bbNo=" + bbNo + ", memberId=" + memberId
				+ ", bbReplyContent=" + bbReplyContent + ", bbReplyWriteDate=" + bbReplyWriteDate
				+ ", bbReplyModifyDate=" + bbReplyModifyDate + "]";
	}

	public int getBbReplyNo() {
		return bbReplyNo;
	}

	public void setBbReplyNo(int bbReplyNo) {
		this.bbReplyNo = bbReplyNo;
	}

	public int getBbNo() {
		return bbNo;
	}

	public void setBbNo(int bbNo) {
		this.bbNo = bbNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getBbReplyContent() {
		return bbReplyContent;
	}

	public void setBbReplyContent(String bbReplyContent) {
		this.bbReplyContent = bbReplyContent;
	}

	public Date getBbReplyWriteDate() {
		return bbReplyWriteDate;
	}

	public void setBbReplyWriteDate(Date bbReplyWriteDate) {
		this.bbReplyWriteDate = bbReplyWriteDate;
	}

	public Date getBbReplyModifyDate() {
		return bbReplyModifyDate;
	}

	public void setBbReplyModifyDate(Date bbReplyModifyDate) {
		this.bbReplyModifyDate = bbReplyModifyDate;
	}

}
