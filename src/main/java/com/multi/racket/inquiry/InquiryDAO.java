package com.multi.racket.inquiry;
import com.multi.racket.domain.InquiryDTO;
public interface InquiryDAO {
	public InquiryDTO insert(InquiryDTO inquiry);
	public InquiryDTO read(int inquiryNo);
	public InquiryDTO reply(InquiryDTO reply);
	public void delete(int inquiryNo);
}
