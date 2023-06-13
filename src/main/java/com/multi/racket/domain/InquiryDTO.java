package com.multi.racket.domain;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inquiry")
public class InquiryDTO {
	@Id
	private String inquiryNo;
	private String memberId;
	private String inqTitle;
	private String inqContent;
	private String inqDate;	
}
