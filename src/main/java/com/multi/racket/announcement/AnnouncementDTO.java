package com.multi.racket.announcement;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

@DynamicInsert
@Entity
@Table(name = "announcement")
@Component
public class AnnouncementDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int announcementNo;
	@NotNull
	private String memberId;
	private String announcementTitle;
	private String announcementContent;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@Column(nullable = false, name = "announcement_date")
	@NotNull
	@CreationTimestamp
	private Date announcementDate;
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
	@Column(name = "announcement_modify_date")
	@UpdateTimestamp
	private Date announcementModifyDate;
	@ColumnDefault("0")
	private Integer announcementViews = 0;
	@Transient
	@JoinColumn(name = "announcement_no")
	@OneToMany(fetch = FetchType.EAGER)
	private List<MultipartFile> announcementFileDTO;

	public AnnouncementDTO() {

	}

	public AnnouncementDTO(int announcementNo, String memberId, String announcementTitle, String announcementContent,
			Date announcementDate, Date announcementModifyDate, Integer announcementViews,
			List<MultipartFile> announcementFileDTO) {
		super();
		this.announcementNo = announcementNo;
		this.memberId = memberId;
		this.announcementTitle = announcementTitle;
		this.announcementContent = announcementContent;
		this.announcementDate = announcementDate;
		this.announcementModifyDate = announcementModifyDate;
		this.announcementViews = announcementViews;
		this.announcementFileDTO = announcementFileDTO;
	}

	@Override
	public String toString() {
		return "AnnouncementDTO [announcementNo=" + announcementNo + ", memberId=" + memberId + ", announcementTitle="
				+ announcementTitle + ", announcementContent=" + announcementContent + ", announcementDate="
				+ announcementDate + ", announcementModifyDate=" + announcementModifyDate + ", announcementViews="
				+ announcementViews + ", announcementFileDTO=" + announcementFileDTO + "]";
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

	public Date getAnnouncementModifyDate() {
		return announcementModifyDate;
	}

	public void setAnnouncementModifyDate(Date announcementModifyDate) {
		this.announcementModifyDate = announcementModifyDate;
	}

	public Integer getAnnouncementViews() {
		return announcementViews;
	}

	public void setAnnouncementViews(Integer announcementViews) {
		this.announcementViews = announcementViews;
	}

	public List<MultipartFile> getAnnouncementFileDTO() {
		return announcementFileDTO;
	}

	public void setAnnouncementFileDTO(List<MultipartFile> announcementFileDTO) {
		this.announcementFileDTO = announcementFileDTO;
	}

}
