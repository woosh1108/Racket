package com.multi.racket.inquiry;
import com.multi.racket.domain.CashDTO;
import com.multi.racket.domain.InquiryDTO;
import com.multi.racket.domain.MemberDTO;
public interface InquiryDAO {
	public InquiryDTO insert(InquiryDTO inquiry);
	public InquiryDTO read(int inquiryNo);
	public InquiryDTO reply(InquiryDTO reply);
	public void delete(int inquiryNo);
	public void update(InquiryDTO dto);
	public MemberDTO updatecash(String id, int updatecash);
	public CashDTO insert(CashDTO cash);
	public MemberDTO update(String id, int cash);
}
