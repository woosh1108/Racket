package com.multi.racket.bulletin_board;



import java.sql.Date;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
@DynamicInsert
@Entity
@Table(name = "bulletin_board")
@Component
public class BulletinBoardDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bbNo;
	@NotNull
	private String memberId;
	private String bbTitle;
	private String bbContent;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@Column(nullable = false)
	@NotNull
	@CreationTimestamp
	private Date bbDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@Column(name = "bb_modify_date")
	@UpdateTimestamp
	private Date bbModifyDate=null;
	@ColumnDefault("0")
	private Integer bbViews=0;
	

	public BulletinBoardDTO() {

	}

	public BulletinBoardDTO(int bbNo, String memberId, String bbTitle, String bbContent, Date bbDate, Date bbModifyDate,
			Integer bbViews) {
		super();
		this.bbNo = bbNo;
		this.memberId = memberId;
		this.bbTitle = bbTitle;
		this.bbContent = bbContent;
		this.bbDate = bbDate;
		this.bbModifyDate = bbModifyDate;
		this.bbViews = bbViews;
	}

	@Override
	public String toString() {
		return "BulletinBoardDTO [bbNo=" + bbNo + ", memberId=" + memberId + ", bbTitle=" + bbTitle + ", bbContent="
				+ bbContent + ", bbDate=" + bbDate + ", bbModifyDate=" + bbModifyDate + ", bbViews=" + bbViews + "]";
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

	public String getBbTitle() {
		return bbTitle;
	}

	public void setBbTitle(String bbTitle) {
		this.bbTitle = bbTitle;
	}

	public String getBbContent() {
		return bbContent;
	}

	public void setBbContent(String bbContent) {
		this.bbContent = bbContent;
	}

	public Date getBbDate() {
		return bbDate;
	}

	public void setBbDate(Date bbDate) {
		this.bbDate = bbDate;
	}

	public Date getBbModifyDate() {
		return bbModifyDate;
	}

	public void setBbModifyDate(Date bbModifyDate) {
		this.bbModifyDate = bbModifyDate;
	}

	public Integer getBbViews() {
		return bbViews;
	}

	public void setBbViews(Integer bbViews) {
		this.bbViews = bbViews;
	}
	
}
