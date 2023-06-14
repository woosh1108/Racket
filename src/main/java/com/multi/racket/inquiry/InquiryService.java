package com.multi.racket.inquiry;
import java.util.List;
import com.multi.racket.domain.InquiryDTO;
public interface InquiryService {
	public InquiryDTO insert(InquiryDTO dto);
	public List<InquiryDTO> findAll();
	public InquiryDTO read(int inquiryNo);
}
