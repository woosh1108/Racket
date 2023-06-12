package com.multi.racket.announcement;

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
@Table(name = "announcement")
public class AnnouncementDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int announcementNo;
	private String memberId;
	private String announcementTitle;
	private String announcementContent;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@Column(nullable = false)
	@NotNull
	@CreationTimestamp
	private Date announcementDate;
//	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
//	@Column(nullable = false)
//	@UpdateTimestamp
//	private Date announcementModifyDate;
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int announcementViews;

	public AnnouncementDTO() {

	}

	public AnnouncementDTO(int announcementNo, String memberId, String announcementTitle, String announcementContent,
			Date announcementDate, int announcementViews) {
		super();
		this.announcementNo = announcementNo;
		this.memberId = memberId;
		this.announcementTitle = announcementTitle;
		this.announcementContent = announcementContent;
		this.announcementDate = announcementDate;
		this.announcementViews = announcementViews;
	}

	@Override
	public String toString() {
		return "AnnouncementDTO [announcementNo=" + announcementNo + ", memberId=" + memberId + ", announcementTitle="
				+ announcementTitle + ", announcementContent=" + announcementContent + ", announcementDate="
				+ announcementDate + ", announcementViews=" + announcementViews + "]";
	}

	public int getAnnouncementNo() {
		return announcementNo;
	}

	public void setAnnouncementNo(int announcementNo) {
		this.announcementNo = announcementNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getAnnouncementTitle() {
		return announcementTitle;
	}

	public void setAnnouncementTitle(String announcementTitle) {
		this.announcementTitle = announcementTitle;
	}

	public String getAnnouncementContent() {
		return announcementContent;
	}

	public void setAnnouncementContent(String announcementContent) {
		this.announcementContent = announcementContent;
	}

	public Date getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}

	public int getAnnouncementViews() {
		return announcementViews;
	}

	public void setAnnouncementViews(int announcementViews) {
		this.announcementViews = announcementViews;
	}
	
}
