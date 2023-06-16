package com.multi.racket.inquiry;
import java.util.List;
import com.multi.racket.domain.InquiryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class PageDTO {
	public List<InquiryDTO> list;
	public int totalPageNumber;
	
}
