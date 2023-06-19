package com.multi.racket.inquiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.multi.racket.domain.InquiryDTO;
@Service
@Transactional
public class InquiryServiceImpl implements InquiryService {
	InquiryDAO dao;
	
	@Autowired
	public InquiryServiceImpl(InquiryDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public InquiryDTO insert(InquiryDTO inquiry) {
		return dao.insert(inquiry);
	}

	@Override
	public InquiryDTO read(int inquiryNo) {
		return dao.read(inquiryNo);
	}

	@Override
	public InquiryDTO reply(InquiryDTO reply) {
		return dao.reply(reply);
	}

	@Override
	public void delete(int inquiryNo) {
		dao.delete(inquiryNo);
	}

}
