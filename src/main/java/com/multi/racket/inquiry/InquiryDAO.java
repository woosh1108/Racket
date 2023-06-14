package com.multi.racket.inquiry;
import java.util.List;
import com.multi.racket.domain.InquiryDTO;
public interface InquiryDAO {
	public InquiryDTO insert(InquiryDTO inquiry);
	public List<InquiryDTO> findAll();
	public InquiryDTO read(int inquiryNo);
}
