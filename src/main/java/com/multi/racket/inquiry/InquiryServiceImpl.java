package com.multi.racket.inquiry;
import java.util.List;
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
	public List<InquiryDTO> findAll() {
		return dao.findAll();
	}

	@Override
	public InquiryDTO read(int inquiryNo) {
		return dao.read(inquiryNo);
	}

}
