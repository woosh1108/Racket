package com.multi.racket.inquiry;
import java.util.List;
import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.InquiryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class PageDTO {
	public List<InquiryDTO> list;
	public int totalPageNumber;
	public List<CashDTO> cashlist;
	
	public PageDTO(List<InquiryDTO> list, int totalPageNumber) {
		super();
		this.list = list;
		this.totalPageNumber = totalPageNumber;
	}

	public PageDTO(int totalPageNumber, List<CashDTO> cashlist) {
		super();
		this.totalPageNumber = totalPageNumber;
		this.cashlist = cashlist;
	}
	
	
	
	
	
}
