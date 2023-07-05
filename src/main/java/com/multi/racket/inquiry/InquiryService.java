package com.multi.racket.inquiry;
import com.multi.racket.domain.InquiryDTO;
import com.multi.racket.domain.MemberDTO;
public interface InquiryService {
	public InquiryDTO insert(InquiryDTO dto);
	public InquiryDTO read(int inquiryNo);
	public InquiryDTO reply(InquiryDTO dto);
	public void delete(int inquiryNo);
	public void update(InquiryDTO dto);
	public MemberDTO updatecash(String id, int updatecash);
}
